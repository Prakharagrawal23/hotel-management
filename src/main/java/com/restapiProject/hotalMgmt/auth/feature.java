package com.restapiProject.hotalMgmt.auth;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class feature {
	private final JdbcTemplate jdbcTemplate = new JdbcTemplate();
	private final RowMapper<User> userRowMapper = (rs,rowNum) -> {
		User u = new User();
		u.setId(rs.getInt("id"));
		u.setUsername(rs.getString("username"));
		u.setPassword(rs.getString("password"));
		u.setRole(rs.getString("role"));
		return u;

	};
}
