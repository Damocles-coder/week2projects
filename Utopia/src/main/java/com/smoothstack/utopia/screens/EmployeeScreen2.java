/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.entities.Flight;
import com.smoothstack.utopia.entities.FlightRoute;
import com.smoothstack.utopia.services.ServiceManager;

/**
 * @author dyltr
 *
 */
public class EmployeeScreen2 implements Screen {
	
	public Screen run(Scanner scanner) throws InputMismatchException {
		/*Get list of flights that the employee manages in
		 * the format sourceAirport -> destinationAirport
		 * Get booking where id = user where role = employee
		 * Get flight where flight id is associated with booking id
		 */
		
		//call method from flight service that returns a list of flights
		List<FlightRoute> array = ServiceManager.getFlightService().getFlightList();
		for (FlightRoute i:array) {
			
		}
		
		return null;
	}

	
}
