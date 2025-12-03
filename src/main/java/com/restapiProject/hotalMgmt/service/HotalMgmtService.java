package com.restapiProject.hotalMgmt.service;

import java.util.List;

import com.restapiProject.hotalMgmt.model.Hotal;

public interface HotalMgmtService {
    Hotal createHotel(Hotal hotel);
    Hotal getHotelById(Long id);
    List<Hotal> getAllHotels();
    Hotal updateHotel(Long id, Hotal hotel);
    void deleteHotel(Long id);
    List<Hotal> searchHotelByName(String name);
}
