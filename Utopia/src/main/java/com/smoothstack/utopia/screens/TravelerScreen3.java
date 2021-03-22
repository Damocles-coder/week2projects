/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.smoothstack.utopia.entities.Booking;
import com.smoothstack.utopia.entities.FlightBooking;
import com.smoothstack.utopia.entities.FlightRoute;
import com.smoothstack.utopia.entities.FlightStatus;
import com.smoothstack.utopia.entities.Passenger;
import com.smoothstack.utopia.entities.User;
import com.smoothstack.utopia.services.ServiceManager;

/**
 * @author dyltr
 * Class allows booking of tickets
 */
public class TravelerScreen3 implements Screen {
	private FlightRoute f;
	private User user;

	public Screen run(Scanner scanner) throws InputMismatchException {
		FlightStatus fs = null;
		try {
			fs = ServiceManager.getFlightService().getFlightStatus(f.getFlight());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("1) View Flight Details");
		System.out.println("2) First");
		System.out.println("3) Business");
		System.out.println("4) Economy");
		System.out.println("5) Quit to cancel operation");
		switch(scanner.nextInt()) {
		case 1:
			System.out.println(ServiceManager.getFlightService().getFlightInfo(f,fs));
			return this;
		case 2:
			//create new booking with flight_booking class=1
			if (fs.getCapacity()-f.getFlight().getReservedSeats()==0) {
				System.out.println("No seats available");
				return this;
			}
			//increment reserved_seat
			//update flight
			//booking id auto increments
			//create booking
			//create flight booking
			//create booking payment with flight id
			//create user booking with user id
			//create passenger auto increments with user information for simplicity sake
			try {
				ServiceManager.getFlightService().bookSeat(user,f,1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			//create new booking with flight_booking class=2
			if (fs.getCapacity2()-f.getFlight().getReservedSeats2()==0) {
				System.out.println("No seats available");
				return this;
			}
			break;
		case 4:
			//create new booking with flight_booking class=1
			if (fs.getCapacity3()-f.getFlight().getReservedSeats3()==0) {
				System.out.println("No seats available");
				return this;
			}
			break;
		case 5:
			return ScreenManager.getTRAV1();
		default:
			System.out.println("Invalid input");
			return this;
		}
		return this;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FlightRoute getF() {
		return f;
	}

	public void setF(FlightRoute f) {
		this.f = f;
	}

}
