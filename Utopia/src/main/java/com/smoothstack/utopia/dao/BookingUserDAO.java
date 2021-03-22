package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.smoothstack.utopia.entities.BookingUser;

public class BookingUserDAO extends BaseDAO<BookingUser> {

	public BookingUserDAO(Connection conn) {
		super(conn);
	}
	
	public void create(BookingUser bu) throws ClassNotFoundException, SQLException{
		save("insert into booking_user(booking_id,user_id) values(?,?)", new Object[] {bu.getBookingId(), bu.getUserId()});
	}
	
	/**
	 * not utilized yet
	 */
	@Override
	protected List<BookingUser> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookingUser> array = new ArrayList<BookingUser>();
		while(rs.next()) {
			array.add(new BookingUser(rs.getInt("booking_id"), rs.getInt("user_id")));
		}
		return array;
	}

}
