package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.smoothstack.utopia.entities.BookingPayment;

public class BookingPaymentDAO extends BaseDAO<BookingPayment> {

	public BookingPaymentDAO(Connection conn) {
		super(conn);
	}

	public void create(BookingPayment bp) throws ClassNotFoundException, SQLException {
		save("insert into booking_payment(booking_id,stripe_id,refunded) values(?,?,?)",
				new Object[] { bp.getBookingId(), bp.getStripeId(), bp.isRefunded() });
	}

	/**
	 * not utilized yet
	 */
	@Override
	protected List<BookingPayment> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookingPayment> array = new ArrayList<BookingPayment>();
		while (rs.next()) {
			array.add(
					new BookingPayment(rs.getInt("booking_id"), rs.getString("stripe_id"), rs.getBoolean("refunded")));
		}
		return array;
	}

	public BookingPayment read(int userId, int flightId) throws ClassNotFoundException, SQLException {
		return get(
				"select bp.* from flight f join flight_bookings fb on fb.flight_id=f.id "
						+ "join booking b on b.id=fb.booking_id " + "join booking_user ub on b.id=ub.booking_id "
						+ "join booking_payment bp on bp.booking_id=b.id " + "where f.id=? and ub.user_id=?",
				new Object[] { flightId, userId }).get(0);
	}

	public void update(BookingPayment bp) throws ClassNotFoundException, SQLException {
		save("update booking_payment set refunded=? where booking_id=?",
				new Object[] { bp.isRefunded(), bp.getBookingId() });
	}

}
