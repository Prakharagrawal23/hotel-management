package com.restapiProject.hotalMgmt.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.restapiProject.hotalMgmt.model.Hotal;

public class HotalRowMapper implements RowMapper<Hotal> {
	// helper method - tell spring how to convert one row of sql result into java
	// object
	@Override
	public Hotal mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Hotal h = new Hotal();
		h.setId(rs.getLong("id"));
		h.setName(rs.getString("name"));
		h.setAddress(rs.getString("address"));
		h.setTotalRoom(rs.getInt("total_room"));
		h.setAvailableRoom(rs.getInt("available_room"));
		h.setPricePerNight(rs.getBigDecimal("price_per_night"));
		java.sql.Timestamp created = rs.getTimestamp("created_at");
		if (created != null) {
			h.setCreatedAt(created.toLocalDateTime());
		}

		java.sql.Timestamp updated = rs.getTimestamp("updated_at");
		if (updated != null) {
			h.setUpdatedAt(updated.toLocalDateTime());
		}

		return h;
	}

}
