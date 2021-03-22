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
	 * @param id 
	 * @return List of all FlightRoute objects associated with any user ids
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<FlightRoute> readAllFilter(int id) throws ClassNotFoundException, SQLException {
		return get("select f.*, r.origin_id, r.destination_id, o.city as origin_city, d.city as destination_city "
				+ "from flight f join route r on f.route_id = r.id "
				+ "join airport o on r.origin_id = o.iata_id "
				+ "join airport d on r.destination_id = d.iata_id "
				+ "join flight_bookings fb on f.id=fb.flight_id "
				+ "join booking_user bu on bu.booking_id=fb.booking_id "
				+ "where bu.user_id=?",new Object[] {id});
	}
	
	/**
	 * @return List of all FlightRoute objects
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<FlightRoute> readAll() throws ClassNotFoundException, SQLException {
		return get("select f.*, r.origin_id, r.destination_id, o.city as origin_city, d.city as destination_city "
				+ "from flight f join route r on f.route_id = r.id "
				+ "join airport o on r.origin_id = o.iata_id "
				+ "join airport d on r.destination_id = d.iata_id",new Object[] {});
	}

	@Override
	public List<FlightRoute> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		List<FlightRoute> array = new ArrayList<FlightRoute>();
		while (rs.next()) {
			array.add(new FlightRoute(rs.getInt("id"), rs.getInt("route_id"), rs.getInt("airplane_id"), 
					rs.getTimestamp("departure_time").toLocalDateTime(), 
					rs.getTimestamp("arrival_time").toLocalDateTime(), rs.getInt("f.reserved_seats"),
					rs.getFloat("f.seat_price"),rs.getString("origin_id"), rs.getString("destination_id"),
					rs.getString("origin_city"),rs.getString("destination_city")));
		}
		return array;
	}

}
