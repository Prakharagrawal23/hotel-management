package com.restapiProject.hotalMgmt.repository.copy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

import com.restapiProject.hotalMgmt.model.Hotal;
import com.restapiProject.hotalMgmt.repository.HotelDaoImpl;
import com.restapiProject.hotalMgmt.util.HotalRowMapper;

public class HotelDaoImplTest {

	@Mock
	private JdbcTemplate jdbcTemplate;

	@InjectMocks
	private HotelDaoImpl hotelDao;
	private Hotal hotel1;
	private Hotal hotel2;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		hotel1 = new Hotal(1L, "Hotel A", "Address A", 10, 5, new BigDecimal("100.0"));
		hotel2 = new Hotal(2L, "Hotel B", "Address B", 20, 10, new BigDecimal("200.0"));
	}

	@Test
	void testSave() {
		doAnswer(invocation -> {
			KeyHolder keyHolder = invocation.getArgument(1);
			keyHolder.getKeyList().add(Map.of("GENERATED_KEY", 1L));
			return 1;
		}).when(jdbcTemplate).update(any(), any(KeyHolder.class));

		Hotal result = hotelDao.save(hotel1);
		assertNotNull(result);
		assertNotNull(result.getId());
	}

	@Test
	void testFindBy_Found() {
	    when(jdbcTemplate.queryForObject(anyString(), any(HotalRowMapper.class), eq(1L)))
	        .thenReturn(hotel1);

	    Optional<Hotal> result = hotelDao.findById(1L);
	    assertTrue(result.isPresent());
	    assertEquals("Hotel A", result.get().getName());
	}




	@Test
	void testFindBy_NotFound() {
		when(jdbcTemplate.query(anyString(), any(HotalRowMapper.class), eq(1L))).thenReturn(Arrays.asList(hotel1));
		Optional<Hotal> result = hotelDao.findById(1L);
		assertFalse(result.isPresent());
	}



	@Test
	void testFindAll() {
	    when(jdbcTemplate.query(anyString(), any(HotalRowMapper.class), eq(5), eq(0)))
	        .thenReturn(Arrays.asList(hotel1, hotel2));

	    List<Hotal> result = hotelDao.findAll(0, 5);
	    assertEquals(2, result.size());
	}

	
	@Test
	void testUpdate() {
		when (jdbcTemplate.update(anyString(),
				anyString(),anyString(),anyInt(),anyInt(),any(BigDecimal.class),anyLong()))
				.thenReturn(1); // 1 row affected 
		int rows = hotelDao.update(hotel1);
		assertEquals(1, rows);
		
	}
	
	@Test
	void testDelete() {
		when(jdbcTemplate.update(anyString(),anyLong())).thenReturn(1);
		int rows=hotelDao.deleteById(1L);
		assertEquals(1,rows);
	}
	
	// parameterized test 
	@ParameterizedTest
	@ValueSource(strings = {"Hotel X","Hotel Y","Hotel Z"})
	void testParameterizedHotelNames(String name) {
		Hotal hotel=new Hotal();
		hotel.setName(name);
		assertNotNull(hotel.getName());
		assertTrue(hotel.getName().startsWith("Hotel"));
		//run the test multiple times -values - in valuesource -3
		//For each name - create a new Hotel,set the name , varify name is not null and start with Hotel
	}
	
	@Disabled("Example skipped Dao test")
	@Test
	void testDiableExample() {
		fail("This DAO test is disabled and skipped");
		//test was enables - it would have been failed
		//learning how to skip test
	}
}
