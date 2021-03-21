package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.smoothstack.utopia.entities.Booking;

public class BookingDAO extends BaseDAO<Booking> {

	public BookingDAO(Connection conn) {
		super(conn);
	}

	public void update(Booking booking) throws ClassNotFoundException, SQLException {
		save("update booking set is_active=? where id=?", new Object[] {booking.isActive(),booking.getId()});
	}
	
	/**
	 * not utilized yet
	 */
	@Override
	protected List extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		return null;
	}

}
