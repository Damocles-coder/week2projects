package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smoothstack.utopia.entities.Booking;

@Repository
public class BookingDAO extends BaseDAO<Booking> {

	public BookingDAO(Connection conn) {
		super(conn);
	}

	public void update(Booking booking) throws ClassNotFoundException, SQLException {
		save("update booking set is_active=? where id=?", new Object[] { booking.isActive(), booking.getId() });
	}

	public int create(Booking booking) throws ClassNotFoundException, SQLException {
		return saveReturnPK("insert into booking(is_active,confirmation_code) values(?,?)",
				new Object[] { booking.isActive(), booking.getConfirmationCode() });
	}

	/**
	 * not utilized yet
	 */
	@Override
	protected List<Booking> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Booking> array = new ArrayList<Booking>();
		while (rs.next()) {
			array.add(new Booking(rs.getInt("id"), rs.getBoolean("is_active"), rs.getString("confirmation_code")));
		}
		return array;
	}

	public Booking read(int userId, int flightId) throws SQLException, ClassNotFoundException {
		return get("select b.* from flight f join flight_bookings fb on fb.flight_id=f.id "
				+ "join booking b on b.id=fb.booking_id " + "join booking_user ub on b.id=ub.booking_id "
				+ "where f.id=? and ub.user_id=?", new Object[] { flightId, userId }).get(0);
	}

}
