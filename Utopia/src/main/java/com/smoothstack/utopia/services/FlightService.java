package com.smoothstack.utopia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.smoothstack.utopia.dao.AirportDAO;
import com.smoothstack.utopia.dao.FlightRouteDAO;
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
	
	public FlightService (Util util) {
		this.util = util;
	}
	
	public List<FlightRoute> getFlightList(){
		return null;
	}
	
	public List<FlightRoute> getFlightListFiltered(){
		return null;
	}
	
	public String getFlightInfo(FlightRoute fr) {
		Flight f = fr.getFlight();
		Route r = fr.getRoute();
		StringBuilder b1 = new StringBuilder("You have chosen to view the Flight with Flight Id: "+f.getId());
		b1.append(" and Departure Airport: "+r.getSource());
		b1.append(" and Arrival Airport: "+r.getDestination()+".\n\n");
		b1.append("Departure Airport: "+r.getSource());
		b1.append(" | Arrival Airport: "+r.getDestination()+"\n");
		b1.append("Departure Date: "+f.getDeparture().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		b1.append(" | Departure Time: "+ f.getDeparture().format(DateTimeFormatter.ofPattern("HH:mm"))+"\n\n");
		//Doing reserved seats for now
		b1.append("Reserved seats: "+f.getReservedSeats());
		return b1.toString();
	}

	public boolean changeSeats(Flight flight) {
		return false;	
	}
	
	public boolean createAirport(Airport airport) throws SQLException {
		//create operation in AirportDao
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirportDAO a1 = new AirportDAO(conn);
			a1.create(airport);
			conn.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
			return false;
		}
		finally {
			if (conn!=null) {
				conn.close();
			}
		}
		return true;
	}
	
	public Airport readAirport(String id) throws SQLException {
		Connection conn = null;
		Airport airport;
		try {
			conn = util.getConnection();
			AirportDAO a1 = new AirportDAO(conn);
			airport = a1.readByCode(id).get(0);
			conn.commit();
			return airport;
		}
		catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
			return null;
		}
		finally {
			if (conn!=null) {
				conn.close();
			}
		}
	}
	
	public boolean updateAirport(Airport newAirport) throws SQLException {
		//update airport
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirportDAO a1 = new AirportDAO(conn);
			a1.update(newAirport);
			conn.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
			return false;
		}
		finally {
			if (conn!=null) {
				conn.close();
			}
		}
		return true;
	}
	
	public boolean deleteAirport(String id) throws SQLException {
		//delete operation in AirportDao
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirportDAO a1 = new AirportDAO(conn);
			a1.delete(new Airport(id,null));
			conn.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
			return false;
		}
		finally {
			if (conn!=null) {
				conn.close();
			}
		}
		return true;
	}
	
	public List<Airport> readAllAirport() throws SQLException {
		Connection conn = null;
		List<Airport> airports;
		//read operation does not change data so there is no need to roll back
		try {
			conn = util.getConnection();
			AirportDAO a1 = new AirportDAO(conn);
			airports = a1.readAll();
			return airports;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			if (conn!=null) {
				conn.close();
			}
		}
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
	
	public boolean addSeat(FlightStatus modified, int change) {
		//change seating by set number
		//gets the flight object from flight status
		//change seats using getter and setter
		return false;
	}
	
	public FlightStatus readSeat(int flightId) {
		//read operation in FlightStatusDAO
		return null;
	}
	
	public boolean updateSeat(FlightStatus changed) {
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
