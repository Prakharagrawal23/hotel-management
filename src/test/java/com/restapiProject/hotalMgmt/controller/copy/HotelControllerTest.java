package com.restapiProject.hotalMgmt.controller.copy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapiProject.hotalMgmt.dto.HotelDto;
import com.restapiProject.hotalMgmt.model.Hotal;
import com.restapiProject.hotalMgmt.service.HotalMgmtService;
import com.restapiProject.hotalMgmt.controller.HotelController;

public class HotelControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HotalMgmtService hotelService;

    @InjectMocks
    private HotelController hotelController;

    private Hotal hotel1;
    private Hotal hotel2;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(hotelController).build();
        // standalone setup -> do not want to load spring context , only wire only the controller and its dependencies 
        
        
        hotel1 = new Hotal(1L, "Hotel A", "Address A", 10, 5, new BigDecimal("100.0"));
        hotel2 = new Hotal(2L, "Hotel B", "Address B", 20, 10, new BigDecimal("200.0"));
    }

    @Test
    void testGetAllHotels() throws Exception {
        when(hotelService.getAllHotels()).thenReturn(Arrays.asList(hotel1, hotel2));

        mockMvc.perform(get("/api/hotels"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()").value(2))
               .andExpect(jsonPath("$[0].name").value("Hotel A"))
               .andExpect(jsonPath("$[1].name").value("Hotel B"));
        verify(hotelService,times(1)).getAllHotels();
    }

    @Test
    void testGetHotelById() throws Exception {
        when(hotelService.getHotelById(1L)).thenReturn(hotel1);

        mockMvc.perform(get("/api/hotels/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Hotel A"))
               .andExpect(jsonPath("$.address").value("Address A"));
        verify(hotelService,times(1)).getHotelById(1L);
    }

    @Test
    void testCreateHotel() throws Exception {
        HotelDto dto = new HotelDto();
        dto.setName("Hotel C");
        dto.setAddress("Address C");
        dto.setTotalRoom(30);
        dto.setAvailableRoom(20);
        dto.setPricePerNight(new BigDecimal("300.0"));

        Hotal created = new Hotal(3L, "Hotel C", "Address C", 30, 20, new BigDecimal("300.0"));
        when(hotelService.createHotel(any(Hotal.class))).thenReturn(created);

        mockMvc.perform(post("/api/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
               .andExpect(status().isCreated())
               .andExpect(header().string("Location", "/api/hotels/3"))
               .andExpect(jsonPath("$.id").value(3))
               .andExpect(jsonPath("$.name").value("Hotel C"));
        verify(hotelService, times(1)).createHotel(any(Hotal.class));
    }

    @Test
    void testUpdateHotel() throws Exception {
        HotelDto dto = new HotelDto();
        dto.setName("Hotel Updated");
        dto.setAddress("New Address");
        dto.setTotalRoom(15);
        dto.setAvailableRoom(7);
        dto.setPricePerNight(new BigDecimal("150.0"));

        Hotal updated = new Hotal(1L, "Hotel Updated", "New Address", 15, 7, new BigDecimal("150.0"));
        when(hotelService.updateHotel(eq(1L), any(Hotal.class))).thenReturn(updated);

        mockMvc.perform(put("/api/hotels/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Hotel Updated"))
               .andExpect(jsonPath("$.address").value("New Address"));
        
        verify(hotelService, times(1)).createHotel(any(Hotal.class));
    }

    @Test
    void testDeleteHotel() throws Exception {
        mockMvc.perform(delete("/api/hotels/1"))
               .andExpect(status().isNoContent());
        verify(hotelService, times(1)).deleteHotel(1L);
    }

    @Test
    void testSearchHotelByName() throws Exception {
        when(hotelService.searchHotelByName("Hotel A")).thenReturn(List.of(hotel1));

        mockMvc.perform(get("/api/hotels/search").param("name", "Hotel A"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()").value(1))
               .andExpect(jsonPath("$[0].name").value("Hotel A"));
    }
}
