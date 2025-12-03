package com.restapiProject.hotalMgmt.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restapiProject.hotalMgmt.dto.HotelDto;
import com.restapiProject.hotalMgmt.model.Hotal;
import com.restapiProject.hotalMgmt.service.HotalMgmtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotalMgmtService hotelServices;

    public HotelController(HotalMgmtService hotelServices) {
        this.hotelServices = hotelServices;
    }

    // DTO to Entity
    private Hotal dtoToEntity(HotelDto dto) {
        Hotal h = new Hotal();
        h.setId(dto.getId());
        h.setName(dto.getName());
        h.setAddress(dto.getAddress());
        h.setTotalRoom(dto.getTotalRoom());
        h.setAvailableRoom(dto.getAvailableRoom());
        h.setPricePerNight(dto.getPricePerNight());
        return h;
    }

    // Entity to DTO
    private HotelDto entityToDto(Hotal h) {
        HotelDto dto = new HotelDto();
        dto.setId(h.getId());
        dto.setName(h.getName());
        dto.setAddress(h.getAddress());
        dto.setTotalRoom(h.getTotalRoom());
        dto.setAvailableRoom(h.getAvailableRoom());
        dto.setPricePerNight(h.getPricePerNight());
        return dto;
    }

    // GET all hotels
    @GetMapping
    public ResponseEntity<List<HotelDto>> getAll() {
        List<HotelDto> dtos = hotelServices.getAllHotels()
                                           .stream()
                                           .map(this::entityToDto)
                                           .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET hotel by ID
    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getById(@PathVariable Long id) {
        Hotal h = hotelServices.getHotelById(id);
        return ResponseEntity.ok(entityToDto(h));
    }

    // POST create hotel
    @PostMapping
    public ResponseEntity<HotelDto> create(@Valid @RequestBody HotelDto dto) {
        Hotal h = dtoToEntity(dto);
        Hotal created = hotelServices.createHotel(h);
        URI location = URI.create("/api/hotels/" + created.getId());
        return ResponseEntity.created(location).body(entityToDto(created));
    }

    // PUT update hotel
    @PutMapping("/{id}")
    public ResponseEntity<HotelDto> update(@PathVariable Long id, @Valid @RequestBody HotelDto dto) {
        Hotal h = dtoToEntity(dto);
        Hotal updated = hotelServices.updateHotel(id, h);
        return ResponseEntity.ok(entityToDto(updated));
    }

    // DELETE hotel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hotelServices.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @GetMapping("/search")
    public ResponseEntity<List<HotelDto>> searchByname(@RequestParam(name="name") String name){
    	List<Hotal> hotels = hotelServices.searchHotelByName(name);
    	
    	
//    	if(name == null) {
//    		return ResponseEntity.badRequest().body("please enter the name");
//    	}
    	List<HotelDto> dtos = hotels.stream()
    								.map(this::entityToDto)
    								.collect(Collectors.toList());
    	
    	return ResponseEntity.ok(dtos);
    }
}
