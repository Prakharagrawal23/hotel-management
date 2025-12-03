package com.restapiProject.hotalMgmt.service.copy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.restapiProject.hotalMgmt.exception.ResourcesNotFoundException;
import com.restapiProject.hotalMgmt.model.Hotal;
import com.restapiProject.hotalMgmt.repository.HotelDao;
import com.restapiProject.hotalMgmt.service.hotalmgmtservicesimpl;

public class hotalmgmtservicesimplTest {
	@Mock
	private HotelDao dao;

	@InjectMocks
	private hotalmgmtservicesimpl service;

	private Hotal hotel1;
	private Hotal hotel2;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		hotel1 = new Hotal(1L, "Hotel A", "Address A", 10, 5, new BigDecimal("100.0"));
		hotel2 = new Hotal(2L, "Hotel B", "Address B", 20, 10, new BigDecimal("200.0"));
	}

	@Test
	void testCreateHotel() {
		when(dao.save(any(Hotal.class))).thenReturn(hotel1);

		Hotal result = service.createHotel(hotel1);

		assertNotNull(result);
		assertEquals("Hotel A", result.getName());
		verify(dao, times(1)).save(hotel1); // ensure that the service actually called Dao once
											// behaviroal check
	}

	@Test
	void testGetHotelById_Found() {
		when(dao.findById(1L)).thenReturn(Optional.of(hotel1));

		Hotal result = service.getHotelById(1L);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Hotel A", result.getName());
	}

	@Test
	void testGetHotelById_NotFound() {
		when(dao.findById(99L)).thenReturn(Optional.empty());

		assertThrows(ResourcesNotFoundException.class, () -> service.getHotelById(99L));
	}

	@Test
	void testGetAllHotels() {
		when(dao.findAll(0, 100)).thenReturn(Arrays.asList(hotel1, hotel2));

		List<Hotal> result = service.getAllHotels();

		assertEquals(2, result.size());
		verify(dao, times(1)).findAll(0, 100);
	}

	@Test
	void testUpdateHotel_Success() {
		when(dao.findById(1L)).thenReturn(Optional.of(hotel1));
		when(dao.update(any(Hotal.class))).thenReturn(1);

		Hotal updated = new Hotal(1L, "Hotel Updated", "New Address", 15, 7, new BigDecimal("150.0"));
		Hotal result = service.updateHotel(1L, updated);

		assertAll("Update Hotel Properties", 
				() -> assertEquals("Hotel Updated", result.getName()),
				() -> assertEquals("New Address", result.getAddress()), 
				() -> assertEquals(15, result.getTotalRoom()),
				() -> assertEquals(7, result.getAvailableRoom()),
				() -> assertEquals(new BigDecimal("150.0"), result.getPricePerNight()));

		verify(dao, times(1)).update(any(Hotal.class));
	}

	@Test
	void testUpdateHotel_Failure() {
		when(dao.findById(1L)).thenReturn(Optional.of(hotel1));
		when(dao.update(any(Hotal.class))).thenReturn(0);

		Hotal updated = new Hotal(1L, "Hotel Updated", "New Address", 15, 7, new BigDecimal("150.0"));

		assertThrows(RuntimeException.class, () -> service.updateHotel(1L, updated));
		verify(dao,times(1)).update(any(Hotal.class));
	}
	
	@Test
    void testDeleteHotel_Success() {
        when(dao.findById(1L)).thenReturn(Optional.of(hotel1));
        when(dao.deleteById(1L)).thenReturn(1);

        assertDoesNotThrow(() -> service.deleteHotel(1L));
        verify(dao, times(2)).deleteById(1L); // called twice in your service
    }
	
	@Test
	void testDeleteHotel_Failure() {
	    when(dao.findById(1L)).thenReturn(Optional.of(hotel1));
	    when(dao.deleteById(1L)).thenReturn(0);

	    RuntimeException exception = assertThrows(RuntimeException.class,
	            () -> service.deleteHotel(1L));

	    assertEquals("Deletion failed for hotel id: 1", exception.getMessage());
	    verify(dao, times(1)).deleteById(1L);
	}
	
//	@Test
//    void testSearchHotelByName_Found() {
//        when(dao.searchByName("Hotel A")).thenReturn(Arrays.asList(hotel1));
//
//        List<Hotal> result = service.searchHotelByName("Hotel A");
//
//        assertEquals(1, result.size());
//        assertEquals("Hotel A", result.get(0).getName());
//    }
//
//    @Test
//    void testSearchHotelByName_NotFound() {
//        when(dao.searchByName("Unknown")).thenReturn(Arrays.asList());
//
//        assertThrows(ResourcesNotFoundException.class, () -> service.searchHotelByName("Unknown"));
//    }
//
//    @Test
//    void testSearchHotelByName_Null() {
//        when(dao.findAll(0, 100)).thenReturn(Arrays.asList(hotel1, hotel2));
//
//        List<Hotal> result = service.searchHotelByName(null);
//
//        assertEquals(2, result.size());
//    }
    
    @ParameterizedTest
    @ValueSource(strings = {"Hotel A","Hotel B","Hotel C"})
    void testParameterizedHotelNames(String name) {
    	Hotal hotel = new Hotal();
    	hotel.setName(name);
    	assertNotNull(hotel.getName());
    	assertTrue(hotel.getName().startsWith("Hotel"));
    }
}
