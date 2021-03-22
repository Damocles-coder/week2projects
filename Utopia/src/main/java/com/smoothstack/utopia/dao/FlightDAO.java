package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.entities.Flight;

/**
 * @author dyltr
 * 
 */
public class FlightDAO extends BaseDAO<Flight> {

	public FlightDAO(Connection conn) {
		super(conn);
	}
	
	public void create(Flight flight) throws ClassNotFoundException, SQLException {
		save("insert into flight(id,route_id,airplane_id,departure_time,arrival_time,"
		+ "reserved_seats,reserved_seats2,reserved_seats3,seat_price) values(?,?,?,?,?,?,?,?,?)", new Object[] {flight.getId(), 
		flight.getRouteId(), flight.getAirplaneId(), 
		flight.getDeparture(), flight.getArrival(),flight.getReservedSeats(), 
		flight.getReservedSeats2(), flight.getReservedSeats3(), flight.getSeatPrice()});
	}
	
	public void update(Flight flight) throws ClassNotFoundException, SQLException {
		save("update flight set route_id=?, airplane_id=?,arrival_time=?, departure_time=?,reserved_seats=?,reserved_seats2=?,reserved_seats3=?,"
				+ "seat_price=? where id=?",
				new Object[] { flight.getRouteId(), flight.getAirplaneId(), flight.getArrival(),
		flight.getDeparture(), flight.getReservedSeats(),
		flight.getReservedSeats2(), flight.getReservedSeats3(), flight.getSeatPrice(), flight.getId() });
	}
	
	public void delete(int id) throws ClassNotFoundException, SQLException {
		save("delete from flight where id=?", new Object[]{id});
	}
	
	public Flight read(int id) throws ClassNotFoundException, SQLException {
		return get("select * from flight where id=?", new Object[] {id}).get(0);
	}

	@Override
	protected List<Flight> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Flight> array = new ArrayList<Flight>();
		while(rs.next()) {
			array.add(new Flight(rs.getInt("id"), rs.getInt("route_id"), rs.getInt("airplane_id"),
					rs.getTimestamp("departure_time").toLocalDateTime(), 
					rs.getTimestamp("arrival_time").toLocalDateTime(), rs.getInt("reserved_seats"),
					rs.getInt("reserved_seats2"),rs.getInt("reserved_seats3"),
					rs.getFloat("seat_price")));
		}
		return array;
	}

}
