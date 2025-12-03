package com.restapiProject.hotalMgmt.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.restapiProject.hotalMgmt.model.Hotal;
import com.restapiProject.hotalMgmt.util.HotalRowMapper;

@Repository  // Marks this class as a DAO component for Spring's component scanning
public class HotelDaoImpl implements HotelDao {

    private final JdbcTemplate jdbcTemplate;

    public HotelDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Im plement methods from HotelDao here...
    @Override
    public Hotal save(Hotal hotal) {
        String sql = "INSERT INTO hotals (name, address, total_room, available_room, price_per_night) " +
                     "VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, hotal.getName());
            ps.setString(2, hotal.getAddress());
            ps.setInt(3, hotal.getTotalRoom());
            ps.setInt(4, hotal.getAvailableRoom());
            ps.setBigDecimal(5, hotal.getPricePerNight());
            return ps;
        }, keyHolder);

        
        Number key = keyHolder.getKey();
        if (key != null) {
            hotal.setId(key.longValue());
        }

        return hotal;
    }

    @Override
    public Optional<Hotal> findById(Long id) {
        String sql = "SELECT * FROM hotals WHERE id = ?";
        try {
            Hotal hotal = jdbcTemplate.queryForObject(sql, new HotalRowMapper(), id);
            return Optional.ofNullable(hotal);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


	@Override
	public List<Hotal> findAll(int offset, int limit) {
		  String sql = "SELECT * FROM hotals ORDER BY id LIMIT ? OFFSET ?";
		  return jdbcTemplate.query(sql, new HotalRowMapper(), limit, offset);
	}

	@Override
	public int update(Hotal hotal) {
		String sql = "UPDATE hotals SET name = ?, address = ?, total_room = ?, available_room = ?, price_per_night = ? WHERE id = ?";
		
		return jdbcTemplate.update(sql,
				hotal.getName(),
				hotal.getAddress(),
				hotal.getTotalRoom(),
				hotal.getAvailableRoom(),
				hotal.getPricePerNight(),
				hotal.getId()
				);
	}

	@Override
	public int deleteById(Long id) {
	    String sql = "DELETE FROM hotals WHERE id = ?";
	    return jdbcTemplate.update(sql, id);
	}
	
	@Override
	public List<Hotal> searchByName(String name){
		String sql = "SELECT * FROM hotals where LOWER(name) LIKE LOWER(?)";
		String searchPattern = "%"+name+"%";
		
		return jdbcTemplate.query(sql, new HotalRowMapper(), searchPattern);
	}

}
