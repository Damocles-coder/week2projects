package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.entities.FlightRoute;

public class FlightRouteDao extends BaseDAO<FlightRoute> {

	public FlightRouteDao(Connection conn) {
		super(conn);
	}

	@Override
	public List<FlightRoute> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		List<FlightRoute> array = new ArrayList<FlightRoute>();
		while (rs.next()) {
			array.add(new FlightRoute(rs.getInt("flight.id"), rs.getInt("route_id"), rs.getInt("airplane_id"), 
					rs.getTimestamp("departure_time").toLocalDateTime(), 
					rs.getTimestamp("arrival_time").toLocalDateTime(), rs.getInt("reserved_seats"),
					rs.getFloat("seat_price"),rs.getString("origin_id"), rs.getString("destination_id")));
		}
		return array;
	}

}
