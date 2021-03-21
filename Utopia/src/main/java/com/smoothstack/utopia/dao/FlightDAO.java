package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.entities.Flight;

public class FlightDAO extends BaseDAO<Flight> {

	public FlightDAO(Connection conn) {
		super(conn);
	}
	
	public void update(Flight flight) throws ClassNotFoundException, SQLException {
		save("update flight set route_id=?, arrival_time=?, departure_time=? where "
				+ "id=?",new Object[] { flight.getRouteId(),flight.getArrival(),
						flight.getDeparture(), flight.getId() });
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
