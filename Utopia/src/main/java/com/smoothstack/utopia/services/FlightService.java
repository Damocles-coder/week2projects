package com.smoothstack.utopia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
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
 * A lot of things have dependencies on flight
 */
public class FlightService {
	Util util;
	
	public FlightService (Util util) {
		this.util = util;
	}
	
	/**
	 * @return list of all flights
	 * @throws SQLException
	 */
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
	
	/**
	 * @param r
	 * @param count
	 * @return route information
	 */
	public String printRoute(Route r, int count) {
		return count+") "+r.getSource().getIataId()+", "+r.getSource().getCity()+" -> "+r.getDestination().getIataId()+", "+r.getDestination().getCity();
	}
	
	/**
	 * @param id
	 * @return list of FlightRoute objects
	 * @throws SQLException
	 */
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
	
	/**
	 * @param fr
	 * @param fs
	 * @return description of flight
	 */
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
		b1.append("\nArrival Date: "+f.getArrival().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		b1.append(" | Arrival Time: "+ f.getArrival().format(DateTimeFormatter.ofPattern("HH:mm"))+"\n\n");
		b1.append("Available seats: \n");
		b1.append("First -> "+(fs.getCapacity()-f.getReservedSeats())+"\n");
		b1.append("Business -> "+(fs.getCapacity2()-f.getReservedSeats2())+"\n");
		b1.append("Economy -> "+(fs.getCapacity3()-f.getReservedSeats3())+"\n");
		return b1.toString();
	}

	/**
	 * @param id
	 * @return AirplaneType from id
	 * @throws SQLException
	 */
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
	
	/**
	 * @param airport creates
	 * @return true if no sql exceptions
	 * @throws SQLException
	 */
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
	
	/**
	 * @param id read
	 * @return Airport object from id
	 * @throws SQLException
	 */
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
	
	/**
	 * @param newAirport update
	 * @return true if no exceptions
	 * @throws SQLException
	 */
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
	
	/**
	 * @param id delete airport
	 * @return true if no exceptions
	 * @throws SQLException
	 */
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
	
	/**
	 * @return list of all airports
	 * @throws SQLException
	 */
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

	/**
	 * @param flight
	 * @return FlightStatus from flight
	 * @throws SQLException
	 */
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

	/**
	 * @param flight update
	 * @return true if no exception
	 * @throws SQLException
	 */
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

	/**
	 * @param airplaneType update
	 * @return true if no exception
	 * @throws SQLException
	 */
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

	/**
	 * @param route
	 * @return route if route exists in database else null
	 * @throws SQLException
	 */
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

	/**
	 * @param route create
	 * @return true if no exception
	 * @throws SQLException
	 */
	public boolean createRoute(Route route) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			RouteDAO r1 = new RouteDAO(conn);
			r1.create(route);
			conn.commit();
			return true;
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
	}

	/**
	 * @param flight create
	 * @return true if no exceptions
	 * @throws SQLException
	 */
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

	/**
	 * @param flight delete
	 * @return true if no exception
	 * @throws SQLException
	 */
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

	/**
	 * books a seat and updates/insert influenced records
	 * @param user
	 * @param f
	 * @param classId
	 * @return true if no exception
	 * @throws SQLException
	 */
	public boolean bookSeat(User user, FlightRoute f, int classId) throws SQLException {
		Connection conn = null;
		Booking b = new Booking(true);
		BookingPayment bp;
		BookingUser bu;
		FlightBooking fb;
		Passenger p;
		int bookingId = 0;
		switch(classId) {
		case 1:
			f.getFlight().setReservedSeats(f.getFlight().getReservedSeats()+1);
			break;
		case 2:
			f.getFlight().setReservedSeats2(f.getFlight().getReservedSeats2()+1);
			break;
		case 3:
			f.getFlight().setReservedSeats3(f.getFlight().getReservedSeats3()+1);
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
			p1.create(p);
			
			//not sure what stripe id is so I'll insert this
			bp = new BookingPayment(bookingId,"",false);
			bp1.create(bp);
			
			//insert booking user
			bu = new BookingUser(bookingId,user.getId());
			bu1.create(bu);
			
			//create flight_booking
			fb = new FlightBooking(f.getFlight().getId(), bookingId, classId);
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

	/**
	 * books a seat and updates/delete influenced records
	 * @param user
	 * @param f
	 * @return true if no exception
	 * @throws SQLException
	 */
	public boolean unbookSeat(User user, FlightRoute f) throws SQLException {
		Connection conn = null;
		Booking b;
		BookingPayment bp;
		FlightBooking fb;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			FlightBookingDAO fb1 = new FlightBookingDAO(conn);
			BookingDAO b1 = new BookingDAO(conn);
			FlightDAO f1 = new FlightDAO(conn);
			BookingPaymentDAO bp1 = new BookingPaymentDAO(conn);
			
			b=b1.read(user.getId(),f.getFlight().getId());
			if(!b.isActive()) {
				return false;
			}
			
			fb=fb1.read(user.getId(),f.getFlight().getId());
			//read from inner joining flight and user
			bp=bp1.read(user.getId(),f.getFlight().getId());
			
			switch(fb.getClassId()) {
			case 1:
				f.getFlight().setReservedSeats(f.getFlight().getReservedSeats()-1);
				break;
			case 2:
				f.getFlight().setReservedSeats2(f.getFlight().getReservedSeats2()-1);
				break;
			case 3:
				f.getFlight().setReservedSeats3(f.getFlight().getReservedSeats3()-1);
				break;
			}
			b.setActive(false);
			bp.setRefunded(true);
			b1.update(b);
			f1.update(f.getFlight());
			bp1.update(bp);
			
			//delete passenger may be needed
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

	/**
	 * @param id
	 * @return get list of cancelled flightroute objects
	 * @throws SQLException
	 */
	public List<FlightRoute> getFlightListFilteredCancel(int id) throws SQLException {
		Connection conn = null;
		List<FlightRoute> array;
		//read operation does not change data so there is no need to roll back
		try {
			conn = util.getConnection();
			FlightRouteDAO f1 = new FlightRouteDAO(conn);
			array = f1.readAllFilterCancel(id);
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

	/**
	 * sets booking.active to true and booking_payment.refunded to false
	 * updates flight seats
	 * @param user
	 * @param f
	 * @return true if no exceptions 
	 * @throws SQLException
	 */
	public boolean rebookSeat(User user, FlightRoute f) throws SQLException {
		Connection conn = null;
		Booking b;
		BookingPayment bp;
		FlightBooking fb;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			FlightBookingDAO fb1 = new FlightBookingDAO(conn);
			BookingDAO b1 = new BookingDAO(conn);
			FlightDAO f1 = new FlightDAO(conn);
			BookingPaymentDAO bp1 = new BookingPaymentDAO(conn);
			
			b=b1.read(user.getId(),f.getFlight().getId());
			if(b.isActive()) {
				return false;
			}
			
			fb=fb1.read(user.getId(),f.getFlight().getId());
			//read from inner joining flight and user
			bp=bp1.read(user.getId(),f.getFlight().getId());
			
			//increments seat belonging to its class
			switch(fb.getClassId()) {
			case 1:
				f.getFlight().setReservedSeats(f.getFlight().getReservedSeats()+1);
				break;
			case 2:
				f.getFlight().setReservedSeats2(f.getFlight().getReservedSeats2()+1);
				break;
			case 3:
				f.getFlight().setReservedSeats3(f.getFlight().getReservedSeats3()+1);
				break;
			}
			
			b.setActive(true);
			bp.setRefunded(false);
			b1.update(b);
			f1.update(f.getFlight());
			bp1.update(bp);
			
			//delete passenger may be needed
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

	/**
	 * @return list of all airplane types
	 * @throws SQLException
	 */
	public List<AirplaneType> readAllAirplaneType() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirplaneTypeDAO a1 = new AirplaneTypeDAO(conn);
			return a1.readAll();
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

	/**
	 * @param airplane create (airplane_type)
	 * @return true if no exception
	 * @throws SQLException
	 */
	public boolean createAiplane(AirplaneType airplane) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			AirplaneTypeDAO a1 = new AirplaneTypeDAO(conn);
			a1.create(airplane);
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

	/**
	 * @param choice delete (airplane type)
	 * @return true if no exception
	 * @throws SQLException
	 */
	public boolean deleteAirplane(int choice) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			conn.setAutoCommit(false);
			AirplaneTypeDAO a1 = new AirplaneTypeDAO(conn);
			a1.delete(choice);
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
