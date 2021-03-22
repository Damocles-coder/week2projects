package com.smoothstack.utopia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.smoothstack.utopia.dao.BookingDAO;
import com.smoothstack.utopia.dao.UserDAO;
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
	
	public boolean createTraveler(User user) throws SQLException {
		Connection conn=null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			UserDAO u1 = new UserDAO(conn);
			u1.createTraveler(user);
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
	
	public boolean createEmployee(User user) throws SQLException {
		Connection conn=null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			UserDAO u1 = new UserDAO(conn);
			u1.createEmployee(user);
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
	
	public boolean deleteEmployee(String email) throws SQLException {
		Connection conn=null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			UserDAO u1 = new UserDAO(conn);
			u1.deleteEmployee(email);
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
	
	public boolean deleteTraveler(String email) throws SQLException {
		Connection conn=null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			UserDAO u1 = new UserDAO(conn);
			u1.deleteTraveler(email);
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
	
	public boolean updateBooking(Booking booking) throws SQLException {
		Connection conn=null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
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
	
	public List<User> readAllTravelers() throws SQLException{
		Connection conn=null;
		List<User> array;
		try {
			conn = util.getConnection();
			UserDAO u1 = new UserDAO(conn);
			array=u1.readAllTravelers();
			return array;
		}
		catch(Exception e) {
			return null;
		}
		finally {
			if (conn!=null) {
				conn.close();
			}
		}
	}
	
	public List<User> readAllEmployees() throws SQLException{
		Connection conn=null;
		List<User> array;
		try {
			conn = util.getConnection();
			UserDAO u1 = new UserDAO(conn);
			array=u1.readAllEmployee();
			return array;
		}
		catch(Exception e) {
			return null;
		}
		finally {
			if (conn!=null) {
				conn.close();
			}
		}
	}

	public boolean checkKey(int key) throws SQLException {
		Connection conn=null;
		User user;
		try {
			conn = util.getConnection();
			UserDAO u1 = new UserDAO(conn);
			user=u1.read(key);
			if (user!=null) {
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			if (conn!=null) {
				conn.close();
			}
		}
		System.out.println("User does not Exist");
		return false;
	}

	public User read(String email) throws SQLException {
		Connection conn=null;
		User user;
		try {
			conn = util.getConnection();
			UserDAO u1 = new UserDAO(conn);
			user=u1.read(email);
			return user;
		}
		catch(Exception e) {
			return null;
		}
		finally {
			if (conn!=null) {
				conn.close();
			}
		}
	}

	public boolean updateUser(User user) throws SQLException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			UserDAO u1 = new UserDAO(conn);
			u1.update(user);
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
}
