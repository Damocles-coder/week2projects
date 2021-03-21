/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.entities.Flight;
import com.smoothstack.utopia.entities.FlightRoute;
import com.smoothstack.utopia.entities.Route;
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
		int count = 1;
		for (FlightRoute i:array) {
			readFlightRoute(i,count++);
		}
		System.out.println(count+") Quit to previous");
		int choice = scanner.nextInt();
		if(choice==count) {
			return ScreenManager.getEMP1();
		}
		else if (choice>=1&&choice<count) {
			Screen temp = ScreenManager.getEMP3();
			((EmployeeScreen3)temp).setF(array.get(count-1));
			return temp;
		}
		else {
			System.out.println("Invalid input");
			return this;
		}
	}
	
	/**
	 * print out the route source and destination city and iata_id
	 * @param f
	 */
	private void readFlightRoute(FlightRoute f, int count) {
		Route r = f.getRoute();
		System.out.println(count+") "+r.getSource()+", "+r.getSourceCity()+" -> "+r.getDestination()+", "+r.getDestinationCity());
	}

	
}
