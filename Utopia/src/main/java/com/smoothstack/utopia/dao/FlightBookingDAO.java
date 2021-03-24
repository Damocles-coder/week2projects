package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smoothstack.utopia.entities.FlightBooking;

@Repository
public class FlightBookingDAO extends BaseDAO<FlightBooking> {

	public FlightBookingDAO(Connection conn) {
		super(conn);
	}

	public void create(FlightBooking fb) throws ClassNotFoundException, SQLException {
		save("insert into flight_bookings(flight_id,booking_id) values(?,?)",
				new Object[] { fb.getflightId(), fb.getBookingId() });
	}

	@Override
	protected List<FlightBooking> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<FlightBooking> array = new ArrayList<FlightBooking>();
		while (rs.next()) {
			array.add(new FlightBooking(rs.getInt("flight_id"), rs.getInt("booking_id"), rs.getInt("class_id")));
		}
		return array;
	}

	public FlightBooking read(int userId, int flightId) throws ClassNotFoundException, SQLException {
		return get("select fb.* from flight_bookings fb join flight f on fb.flight_id=f.id "
				+ "join booking b on fb.booking_id=b.id " + "join booking_user bu on bu.booking_id=b.id "
				+ "where bu.user_id=? and f.id=?", new Object[] { userId, flightId }).get(0);
	}

}
