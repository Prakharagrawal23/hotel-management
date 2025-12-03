package com.restapiProject.hotalMgmt.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.restapiProject.hotalMgmt.exception.ResourcesNotFoundException;
import com.restapiProject.hotalMgmt.model.Hotal;
import com.restapiProject.hotalMgmt.repository.HotelDao;

@Service
public class hotalmgmtservicesimpl implements HotalMgmtService {
	private final HotelDao dao;

	public hotalmgmtservicesimpl(HotelDao dao) {
		this.dao = dao;
	}

	@Override
	public Hotal createHotel(Hotal hotel) {
		return dao.save(hotel);
	}

	@Override
	public Hotal getHotelById(Long id) {
		return dao.findById(id)
				.orElseThrow(()-> new ResourcesNotFoundException("hotel not found with id :"+id));
	}

	@Override
	public List<Hotal> getAllHotels() {
		return dao.findAll(0, 100); // or expose offset/limit via controller
	}

	@Override
	public Hotal updateHotel(Long id, Hotal hotel) {
		Hotal existing = getHotelById(id);
		if (existing == null) {
			throw new RuntimeException("Hotel not found with id: " + id);
		}

		existing.setName(hotel.getName());
		existing.setAddress(hotel.getAddress());
		existing.setTotalRoom(hotel.getTotalRoom());
		existing.setAvailableRoom(hotel.getAvailableRoom());
		existing.setPricePerNight(hotel.getPricePerNight());

		int row = dao.update(existing);
		if (row <= 0) {
			throw new RuntimeException("Update failed for hotel id: " + id);
		}

		return existing;
	}

	@Override
	public void deleteHotel(Long id) {
		getHotelById(id);
		int rows = dao.deleteById(id);
		if (rows <= 0) {
			throw new RuntimeException("Deletion failed for hotel id: " + id);
		}
		dao.deleteById(id);
	}

	@Override
	public List<Hotal> searchHotelByName(String name) {
		
		if(name == null) {
			return dao.findAll(0, 100);
		}
		
		List<Hotal> hotals = dao.searchByName(name);
		
		if(hotals.isEmpty()) {
			throw new ResourcesNotFoundException("no hotels found for name:"+ name);
		}
		// TODO Auto-generated method stub
		return dao.searchByName(name);
	}

}
