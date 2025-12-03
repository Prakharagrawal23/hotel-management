// src/main/java/com/restapiProject/hotalMgmt/auth/AuthController.java
package com.restapiProject.hotalMgmt.auth;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> userMap) {
        String username = userMap.get("username");
        String password = userMap.get("password");
        String role = userMap.getOrDefault("role", "ROLE_USER");

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return "username and password are required";
        }

        if (userRepository.findByUsername(username) != null) {
            return "username already exists";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        userRepository.save(user);
        return "user registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> userMap) {
        String username = userMap.get("username");
        String password = userMap.get("password");

        User user = userRepository.findByUsername(username);
        if (user == null) return "user not found";

        if (!passwordEncoder.matches(password, user.getPassword())) return "Invalid Credentials";

        return jwtUtil.generateToken(user.getUsername());
    }
}
