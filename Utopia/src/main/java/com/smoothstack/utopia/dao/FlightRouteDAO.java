package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.smoothstack.utopia.entities.FlightRoute;

public class FlightRouteDAO extends BaseDAO<FlightRoute> {

	public FlightRouteDAO(Connection conn) {
		super(conn);
	}
	
	/**
	 * @return List of all FlightRoute objects associated with any agents
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<FlightRoute> readAllFilter() throws ClassNotFoundException, SQLException {
		return get("select f.id as flight_id, r.id as route_id, f.airplane_id as airplane_id, "
				+ "f.departure_time as departure_time, f.arrival_time as arrival_time, o.iata_id "
				+ "as origin_id, d.iata_id as destination_id, o.city as origin_city, d.city as destination_city "
				+ "from airport o join route r on r.origin_id=o.iata_id "
				+ "join airport d on r.destination_id=d.iata_id "
				+ "join flight f on r.id=f.route_id "
				+ "join flight_booking fb f.id = fb.flight_id "
				+ "join booking_agent ba on ba.booking_id = fb.booking_id",new Object[] {});
	}
	
	/**
	 * @return List of all FlightRoute objects
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<FlightRoute> readAll() throws ClassNotFoundException, SQLException {
		return get("select f.id as flight_id, r.id as route_id, f.airplane_id as airplane_id, "
				+ "f.departure_time as departure_time, f.arrival_time as arrival_time, o.iata_id "
				+ "as origin_id, d.iata_id as destination_id, o.city as origin_city, d.city as destination_city "
				+ "from airport o join route r on r.origin_id=o.iata_id "
				+ "join airport d on r.destination_id=d.iata_id "
				+ "join flight f on r.id=f.route_id",new Object[] {});
	}

	@Override
	public List<FlightRoute> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		List<FlightRoute> array = new ArrayList<FlightRoute>();
		while (rs.next()) {
			array.add(new FlightRoute(rs.getInt("flight_id"), rs.getInt("route_id"), rs.getInt("airplane_id"), 
					rs.getTimestamp("departure_time").toLocalDateTime(), 
					rs.getTimestamp("arrival_time").toLocalDateTime(), rs.getInt("reserved_seats"),
					rs.getFloat("seat_price"),rs.getString("origin_id"), rs.getString("destination_id"),
					rs.getString("origin_city"),rs.getString("destination_city")));
		}
		return array;
	}

}
