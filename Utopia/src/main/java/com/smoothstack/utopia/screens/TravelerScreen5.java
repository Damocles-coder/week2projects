/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.smoothstack.utopia.entities.FlightRoute;
import com.smoothstack.utopia.entities.FlightStatus;
import com.smoothstack.utopia.services.ServiceManager;

/**
 * @author dyltr
 *
 */
public class TravelerScreen5 implements Screen {
	private FlightRoute f;

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
			break;
		case 3:
			//create new booking with flight_booking class=2
			break;
		case 4:
			//create new booking with flight_booking class=1
			break;
		case 5:
			return ScreenManager.getTRAV1();
		default:
			System.out.println("Invalid input");
			return this;
		}
		return this;
	}

	public FlightRoute getF() {
		return f;
	}

	public void setF(FlightRoute f) {
		this.f = f;
	}

}
