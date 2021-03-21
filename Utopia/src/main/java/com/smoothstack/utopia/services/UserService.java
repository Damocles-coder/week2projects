package com.smoothstack.utopia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.smoothstack.utopia.dao.BookingDAO;
import com.smoothstack.utopia.entities.Booking;
import com.smoothstack.utopia.entities.User;
import com.smoothstack.utopia.jdbc.Util;

/**
 * @author dyltr
 * Services Users and bookings
 */
public class UserService {
	Util util;
	
	public UserService(Util util) {
		this.util = util;
	}
	
	public boolean updateBooking(Booking booking) throws SQLException {
		Connection conn=null;
		try {
			conn = util.getConnection();
			BookingDAO b1 = new BookingDAO(conn);
			b1.update(booking);
			conn.commit();
			return true;
		}
		catch(Exception e) {
			return false;
		}
		finally {
			if (conn!=null) {
				conn.close();
			}
		}
	}
	
	public List<User> readUser(){
		
		return null;
	}
}
