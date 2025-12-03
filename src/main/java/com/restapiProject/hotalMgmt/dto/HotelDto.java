package com.restapiProject.hotalMgmt.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;

public class HotelDto {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Total room count is required")
    @Min(value = 1, message = "Total room must be >= 1")
    private Integer totalRoom;

    @NotNull(message = "Available room count is required")
    @Min(value = 0, message = "Available room must be >= 0")
    private Integer availableRoom;

    @NotNull(message = "Price per night is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price per night must be greater than 0")
    private BigDecimal pricePerNight;

    public HotelDto() {}

    public HotelDto(Long id, String name, String address, Integer totalRoom, Integer availableRoom, BigDecimal pricePerNight) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.totalRoom = totalRoom;
        this.availableRoom = availableRoom;
        this.pricePerNight = pricePerNight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotalRoom() {
        return totalRoom;
    }

    public void setTotalRoom(Integer totalRoom) {
        this.totalRoom = totalRoom;
    }

    public Integer getAvailableRoom() {
        return availableRoom;
    }

    public void setAvailableRoom(Integer availableRoom) {
        this.availableRoom = availableRoom;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return "HotelDto [id=" + id + ", name=" + name + ", address=" + address + ", totalRoom=" + totalRoom
                + ", availableRoom=" + availableRoom + ", pricePerNight=" + pricePerNight + "]";
    }
}
