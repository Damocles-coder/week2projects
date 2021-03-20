package com.smoothstack.utopia.services;

import java.sql.SQLException;
import java.util.List;

import com.smoothstack.utopia.dao.FlightRouteDao;
import com.smoothstack.utopia.entities.Airport;
import com.smoothstack.utopia.entities.Flight;
import com.smoothstack.utopia.entities.FlightRoute;
import com.smoothstack.utopia.entities.FlightStatus;
import com.smoothstack.utopia.entities.Passenger;
import com.smoothstack.utopia.entities.Route;
import com.smoothstack.utopia.jdbc.Util;

/**
 * @author dyltr
 * Services anything with a dependency on flight
 */
public class FlightService {
	Util util;
	FlightRouteDao dao;
	
	public FlightService (Util util) {
		this.util = util;
		try {
			dao = new FlightRouteDao(util.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<FlightRoute> getFlightList(){
		return null;
	}

	public boolean changeSeats(Flight flight) {
		return false;	
	}
	
	public boolean createAirport(Airport airport) {
		//create operation in AirportDao
		return false;
	}
	
	public Airport readAirport(String id) {
		//read operation in AirportDao
		return null;
	}
	
	public boolean updateAirport(String id, Airport newAirport) {
		//update operation in AirportDao
		return false;
	}
	
	public boolean deleteAirport(String id) {
		//delete operation in AirportDao
		//automatically delete flights and routes associated with them
		return false;
	}
	
	public boolean createPassenger(Passenger passenger) {
		//create operation in Passenger
		//can only create when booking already exists
		//get flight from booking joins
		//modify flight object
		//update flight object
//		try {
//			
//		}
//		catch(SQLException|ClassNotFoundException e) {
//			
//		}
		return false;
	}
	
	public List<Passenger> readPassenger(int id) {
		//read operation in Passenger
		return null;
	}
	
	public boolean updatePassenger(int id, Passenger passenger) {
		//update operation in AirportDao
		return false;
	}
	
	public boolean deletePassenger(int id) {
		//delete operation in Passenger
		//you can have a booking with no passengers
		//get flight from booking joins
		//modify flight object
		//deleteSeat(flightStatus,1)
		return false;
	}
	
	public boolean createSeat(FlightStatus modified, int change) {
		//change seating by set number
		//gets the flight object from flight status
		//change seats using getter and setter
		return false;
	}
	
	public FlightStatus readSeat(int flightId) {
		//read operation in FlightStatusDAO
		return null;
	}
	
	public boolean updatSeat(FlightStatus changed) {
		//update operation in AirportDao
		//gets flight object from flight status
		//checks if requested reserved seats is less than or equal to capacity
		//Saves flight in flight DAO
		return false;
	}
	
	public boolean deleteSeat(FlightStatus modified, int change) {
		//same as add seat, but checks if seats does not fall under 0
		return false;
	}
}
