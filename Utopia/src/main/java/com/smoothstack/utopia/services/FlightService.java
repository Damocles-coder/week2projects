package com.smoothstack.utopia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.smoothstack.utopia.dao.AirplaneTypeDAO;
import com.smoothstack.utopia.dao.AirportDAO;
import com.smoothstack.utopia.dao.BookingDAO;
import com.smoothstack.utopia.dao.BookingPaymentDAO;
import com.smoothstack.utopia.dao.BookingUserDAO;
import com.smoothstack.utopia.dao.FlightBookingDAO;
import com.smoothstack.utopia.dao.FlightDAO;
import com.smoothstack.utopia.dao.FlightRouteDAO;
import com.smoothstack.utopia.dao.FlightStatusDAO;
import com.smoothstack.utopia.dao.PassengerDAO;
import com.smoothstack.utopia.dao.RouteDAO;
import com.smoothstack.utopia.entities.AirplaneType;
import com.smoothstack.utopia.entities.Airport;
import com.smoothstack.utopia.entities.Booking;
import com.smoothstack.utopia.entities.BookingPayment;
import com.smoothstack.utopia.entities.BookingUser;
import com.smoothstack.utopia.entities.Flight;
import com.smoothstack.utopia.entities.FlightBooking;
import com.smoothstack.utopia.entities.FlightRoute;
import com.smoothstack.utopia.entities.FlightStatus;
import com.smoothstack.utopia.entities.Passenger;
import com.smoothstack.utopia.entities.Route;
import com.smoothstack.utopia.entities.User;
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
	
	public List<FlightRoute> getFlightList() throws SQLException{
		Connection conn = null;
		List<FlightRoute> array;
		//read operation does not change data so there is no need to roll back
		try {
			conn = util.getConnection();
			FlightRouteDAO f1 = new FlightRouteDAO(conn);
			array = f1.readAll();
			return array;
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
	
	public String printRoute(Route r, int count) {
		return count+") "+r.getSource().getIataId()+", "+r.getSource().getCity()+" -> "+r.getDestination().getIataId()+", "+r.getDestination().getCity();
	}
	
	public List<FlightRoute> getFlightListFiltered(int id) throws SQLException{
		Connection conn = null;
		List<FlightRoute> array;
		//read operation does not change data so there is no need to roll back
		try {
			conn = util.getConnection();
			FlightRouteDAO f1 = new FlightRouteDAO(conn);
			array = f1.readAllFilter(id);
			return array;
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
	
	public String getFlightInfo(FlightRoute fr, FlightStatus fs) {
		Flight f = fr.getFlight();
		Route r = fr.getRoute();
		StringBuilder b1 = new StringBuilder("You have chosen to view the Flight with Flight Id: "+f.getId());
		b1.append(" and Departure Airport: "+r.getSource().getIataId());
		b1.append(" and Arrival Airport: "+r.getDestination().getIataId()+".\n\n");
		b1.append("Departure Airport: "+r.getSource().getIataId());
		b1.append(" | Arrival Airport: "+r.getDestination().getIataId()+"\n");
		b1.append("Departure Date: "+f.getDeparture().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		b1.append(" | Departure Time: "+ f.getDeparture().format(DateTimeFormatter.ofPattern("HH:mm")));
		b1.append("Arrival Date: "+f.getArrival().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		b1.append(" | Arrival Time: "+ f.getArrival().format(DateTimeFormatter.ofPattern("HH:mm"))+"\n\n");
		b1.append("Available seats: \n");
		b1.append("First -> "+(fs.getCapacity()-f.getReservedSeats())+"\n");
		b1.append("Business -> "+(fs.getCapacity2()-f.getReservedSeats2())+"\n");
		b1.append("Economy -> "+(fs.getCapacity3()-f.getReservedSeats3())+"\n");
		return b1.toString();
	}

	public boolean changeSeats(Flight flight) {
		return false;	
	}
	
	public AirplaneType readAirplaneType(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirplaneTypeDAO a1 = new AirplaneTypeDAO(conn);
			return a1.read(id);
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
	
	public boolean createAirport(Airport airport) throws SQLException {
		//create operation in AirportDao
		Connection conn = null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
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
			conn.setAutoCommit(false);
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
			conn.setAutoCommit(false);
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
			conn.setAutoCommit(false);
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
			conn.setAutoCommit(false);
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

	public FlightStatus getFlightStatus(Flight flight) throws SQLException {
		Connection conn = null;
		FlightStatus fs = null;
		//read operation does not change data so there is no need to roll back
		try {
			conn = util.getConnection();
			FlightStatusDAO f1  = new FlightStatusDAO(conn);
			fs = f1.read(flight);
			return fs;
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

	public boolean updateFlight(Flight flight) throws SQLException {
		//update operation in Flights
		Connection conn = null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			FlightDAO f1 = new FlightDAO(conn);
			f1.update(flight);
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

	public boolean updateAirplane(AirplaneType airplaneType) throws SQLException {
		//update airplane type
		Connection conn = null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			AirplaneTypeDAO a1 = new AirplaneTypeDAO(conn);
			a1.update(airplaneType);
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

	public Route findRoute(Route route) throws SQLException {
		//returns route if found with its id
		Connection conn = null;
		Route found = null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			RouteDAO r1 = new RouteDAO(conn);
			found=r1.read(route);
			conn.commit();
			return found;
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

	public void createRoute(Route route) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			RouteDAO r1 = new RouteDAO(conn);
			r1.create(route);
			conn.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
		}
		finally {
			if (conn!=null) {
				conn.close();
			}
		}
	}

	public boolean createFlight(Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			FlightDAO f1 = new FlightDAO(conn);
			f1.create(flight);
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

	public boolean deleteFlight(Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			FlightDAO f1 = new FlightDAO(conn);
			f1.delete(flight.getId());
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

	public boolean bookSeat(User user, FlightRoute f, int classId) throws SQLException {
		Connection conn = null;
		Booking b = new Booking(false);
		BookingPayment bp;
		BookingUser bu;
		FlightBooking fb;
		Passenger p;
		int bookingId;
		switch(classId) {
		case 1:
			f.getFlight().setReservedSeats(f.getFlight().getReservedSeats()+1);
			break;
		case 2:
			f.getFlight().setReservedSeats2(f.getFlight().getReservedSeats2()+1);
			break;
		case 3:
			f.getFlight().setReservedSeats(f.getFlight().getReservedSeats()+1);
			break;
		}
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			BookingDAO b1 = new BookingDAO(conn);
			BookingPaymentDAO bp1 = new BookingPaymentDAO(conn);
			BookingUserDAO bu1 = new BookingUserDAO(conn);
			PassengerDAO p1 = new PassengerDAO(conn);
			FlightBookingDAO fb1 = new FlightBookingDAO(conn);
			FlightDAO f1 = new FlightDAO(conn);
			//insert and read booking
			bookingId = b1.create(b);
			
			//add passenger birthday/gender/address preset for now
			p = new Passenger(bookingId, user.getGivenName(), user.getFamilyName(), LocalDate.of(1996, 5, 15), "n/a", "n/a");
			
			//not sure what stripe id is so I'll insert this
			bp = new BookingPayment(bookingId,"",false);
			bp1.create(bp);
			
			//insert booking user
			bu = new BookingUser(b.getId(),user.getId());
			bu1.create(bu);
			
			//create flight_booking
			fb = new FlightBooking(f.getFlight().getId(), bookingId);
			fb1.create(fb);
			
			//update flight seating
			f1.update(f.getFlight());
			
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
}
