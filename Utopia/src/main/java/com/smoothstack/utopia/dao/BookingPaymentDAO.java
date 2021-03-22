package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.entities.Booking;
import com.smoothstack.utopia.entities.BookingPayment;

public class BookingPaymentDAO extends BaseDAO<BookingPayment> {

	public BookingPaymentDAO(Connection conn) {
		super(conn);
	}

	public void create(BookingPayment bp) throws ClassNotFoundException, SQLException{
		save("insert into booking_payment(booking_id,stripe_id,refunded),values(?,?,?)", 
				new Object[] {bp.getBookingId(), bp.getStripeId(), bp.isRefunded()});
	}
	
	/**
	 * not utilized yet
	 */
	@Override
	protected List<BookingPayment> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookingPayment> array = new ArrayList<BookingPayment>();
		while(rs.next()) {
			array.add(new BookingPayment(rs.getInt("booking_id"), rs.getString("stripe_id"), rs.getBoolean("refunded")));
		}
		return array;
	}

}
