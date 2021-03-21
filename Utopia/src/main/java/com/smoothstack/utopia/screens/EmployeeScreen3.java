/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.smoothstack.utopia.entities.Flight;
import com.smoothstack.utopia.entities.FlightRoute;
import com.smoothstack.utopia.services.ServiceManager;

/**
 * @author dyltr
 * View more details about selected flight
 */
public class EmployeeScreen3 implements Screen {
	private FlightRoute f;

	public Screen run(Scanner scanner) throws InputMismatchException {
		System.out.println("\n1) View more details about the flight");
		System.out.println("2)	Update the details of the Flight");
		System.out.println("3)	Add Seats to Flight");
		System.out.println("4)	Quit to previous");
		//get FlightStatus from FlightRoute
		switch(scanner.nextInt()) {
		case 1:
			System.out.println(ServiceManager.getFlightService().getFlightInfo(f));
			return this;
		case 2:
			ServiceManager.getFlightService().addSeat(null, 0);
			return this;
		case 3:
			return this;
		case 4:
			return ScreenManager.getEMP2();
		default:
			System.out.println("/nIncorrect input for flight menu.");
			return this;
		}
	}

	public void setF(FlightRoute f) {
		this.f = f;
	}

}
