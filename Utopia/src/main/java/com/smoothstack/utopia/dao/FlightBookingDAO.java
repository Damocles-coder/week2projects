package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.entities.Booking;
import com.smoothstack.utopia.entities.FlightBooking;

public class FlightBookingDAO extends BaseDAO<FlightBooking> {

	public FlightBookingDAO(Connection conn) {
		super(conn);
	}
	
	public void create(FlightBooking fb) throws ClassNotFoundException, SQLException{
		save("insert into flight_bookings(flight_id,booking_id) values(?,?)", new Object[] {fb.getflightId(), fb.getBookingId()});
	}
	
	/**
	 * not utilized yet
	 */
	@Override
	protected List<FlightBooking> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<FlightBooking> array = new ArrayList<FlightBooking>();
		while(rs.next()) {
			array.add(new FlightBooking(rs.getInt("flight_id"), rs.getInt("booking_id")));
		}
		return array;
	}

}
