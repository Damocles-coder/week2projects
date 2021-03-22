package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.entities.Booking;
import com.smoothstack.utopia.entities.BookingPayment;
import com.smoothstack.utopia.entities.Passenger;

public class PassengerDAO extends BaseDAO<Passenger> {

	public PassengerDAO(Connection conn) {
		super(conn);
	}

	public void create(Passenger p) throws ClassNotFoundException, SQLException{
		save("insert into passenger(booking_id,given_name,family_name,dob,gender,address) values(?,?,?,?,?,?)", 
				new Object[] {p.getBookingId(),p.getGivenName(),p.getFamilyName(),p.getDob(),p.getGender(),p.getAddress()});
	}
	
	/**
	 * not utilized yet
	 */
	@Override
	protected List<Passenger> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Passenger> array = new ArrayList<Passenger>();
		while(rs.next()) {
			array.add(new Passenger(rs.getInt("id"), rs.getInt("booking_id"), rs.getString("given_name"), rs.getString("family_name"), 
					rs.getDate("dob").toLocalDate(), rs.getString("gender"), rs.getString("address")));
		}
		return array;
	}

}
