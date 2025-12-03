package com.restapiProject.hotalMgmt.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Hotal {

	private Long id;
	private String name;
	private String address;
	private int totalRoom;
	private int availableRoom;
	private BigDecimal pricePerNight;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Hotal(Long id, String name, String address, int totalRoom, int availableRoom, BigDecimal pricePerNight,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
//		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.totalRoom = totalRoom;
		this.availableRoom = availableRoom;
		this.pricePerNight = pricePerNight;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Hotal(Long id, String name, String address, int totalRoom, int availableRoom, BigDecimal pricePerNight
			) {
//		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.totalRoom = totalRoom;
		this.availableRoom = availableRoom;
		this.pricePerNight = pricePerNight;
		
	}

//	public Hotal(long l, String string, String string2, int i, int j, BigDecimal bigDecimal) {
//	}

	public Hotal() {}
	
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

	public int getTotalRoom() {
		return totalRoom;
	}

	public void setTotalRoom(int totalRoom) {
		this.totalRoom = totalRoom;
	}

	public int getAvailableRoom() {
		return availableRoom;
	}

	public void setAvailableRoom(int availableRoom) {
		this.availableRoom = availableRoom;
	}

	public BigDecimal getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(BigDecimal pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Hotal [id=" + id + ", name=" + name + ", address=" + address + ", totalRoom=" + totalRoom
				+ ", availableRoom=" + availableRoom + ", pricePerNight=" + pricePerNight + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
