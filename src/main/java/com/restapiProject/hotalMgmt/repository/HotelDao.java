package com.restapiProject.hotalMgmt.repository;

import java.util.List;
import java.util.Optional;

import com.restapiProject.hotalMgmt.model.Hotal;

public interface HotelDao {
	Hotal save(Hotal hotel);
	Optional<Hotal> findById(Long id);
	List<Hotal> findAll(int offset, int limit);
	int update(Hotal hotal);
	int deleteById(Long id);
	List<Hotal> searchByName(String name);
}
