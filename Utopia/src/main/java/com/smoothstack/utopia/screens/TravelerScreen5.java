/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.smoothstack.utopia.entities.FlightRoute;
import com.smoothstack.utopia.entities.FlightStatus;
import com.smoothstack.utopia.entities.User;
import com.smoothstack.utopia.services.ServiceManager;

/**
 * @author dyltr
 *
 */
public class TravelerScreen5 implements Screen {
	private FlightRoute f;
	private User user;

	public Screen run(Scanner scanner) throws InputMismatchException {
		FlightStatus fs = null;
		try {
			fs = ServiceManager.getFlightService().getFlightStatus(f.getFlight());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("1) View Flight Details");
		System.out.println("2) Cancel Booking");
		System.out.println("3) Quit to cancel operation");
		switch(scanner.nextInt()) {
		case 1:
			System.out.println(ServiceManager.getFlightService().getFlightInfo(f,fs));
			return this;
		case 2:
			int classId=0;
			//get flight_booking to get seat class
			//decrement reserved seat depending on the class
			//update flight
			//set Booking.isActive to false
			//set BookingPayment.refund to true
			//update both
			try {
				ServiceManager.getFlightService().unbookSeat(user,f);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ScreenManager.getTRAV4();
		case 3:
			return ScreenManager.getTRAV4();
		default:
			System.out.println("Invalid input");
			return this;
		}
	}

	public FlightRoute getF() {
		return f;
	}

	public void setF(FlightRoute f) {
		this.f = f;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
