package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.entities.Flight;

public class RouteDAO extends BaseDAO<Flight> {

	public RouteDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Not used
	 */
	@Override
	protected List<Flight> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Flight> array = new ArrayList<Flight>();
		while(rs.next()) {
			array.add(new Flight(rs.getInt("id"), rs.getInt("route_id"), rs.getInt("airplane_id"),
					LocalDateTime.parse(rs.getString("departure_time")), 
					LocalDateTime.parse(rs.getString("arival_time")), rs.getInt("reserved_seats"),
					rs.getFloat("seat_price")));
		}
		return null;
	}

}
