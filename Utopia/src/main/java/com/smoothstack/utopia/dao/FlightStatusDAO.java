package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.entities.Flight;
import com.smoothstack.utopia.entities.FlightRoute;
import com.smoothstack.utopia.entities.FlightStatus;

public class FlightStatusDAO extends BaseDAO<FlightStatus> {

	public FlightStatusDAO(Connection conn) {
		super(conn);
	}

	
	public FlightStatus read(Flight flight) throws ClassNotFoundException, SQLException {
		return get("select from flight_status where flight_id=?", new Object[] {flight.getId()}).get(0);
	}
	
	@Override
	public List<FlightStatus> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		List<FlightStatus> array = new ArrayList<FlightStatus>();
		while (rs.next()) {
			array.add(new FlightStatus(new Flight(rs.getInt("flight_id"), rs.getInt("route_id"), rs.getInt("airplane_id"), 
					rs.getTimestamp("departure_time").toLocalDateTime(), 
					rs.getTimestamp("arrival_time").toLocalDateTime(), rs.getInt("reserved_seats"),
					rs.getFloat("seat_price")), rs.getInt("max_capacity"), rs.getInt("passenger_count"), rs.getInt("available_seats")));
		}
		return array;
	}

}
