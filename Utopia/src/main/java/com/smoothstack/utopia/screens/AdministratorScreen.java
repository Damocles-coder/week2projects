/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.entities.Airport;
import com.smoothstack.utopia.services.ServiceManager;

/**
 * @author dyltr
 *
 */
public class AdministratorScreen implements Screen {
	
	public Screen run(Scanner scanner) throws InputMismatchException {
		System.out.println("\n1) Add/Update/Delete/Read Flights");
		System.out.println("2) Add/Update/Delete/Read Seats");
		System.out.println("3) Add/Update/Delete/Read Tickets and Passengers");
		System.out.println("4) Add/Update/Delete/Read Airports");
		System.out.println("5) Add/Update/Delete/Read Travelers");
		System.out.println("6) Add/Update/Delete/Read Employees");
		System.out.println("7) Over-ride Trip Cancellation for a ticket.");
		System.out.println("8) Return to previous.");
		
		int type = scanner.nextInt();
		if (type>=0&&type<7) {
			System.out.println("1) Add");
			System.out.println("2) Update");
			System.out.println("3) Delete");
			System.out.println("4) Read");
			int choice = scanner.nextInt();
			crud(type,choice,scanner);
		}
		else if (type==7) {
			overrideTicket(scanner);
		}
		return ScreenManager.getAdmin();
	}
	
	private void overrideTicket(Scanner scanner) throws InputMismatchException {
		//set booking is_active to true
		System.out.println("What ticket number would you like to override?: ");
		int id = scanner.nextInt();
		
	}
	
	/**
	 * @param choice
	 */
	private void crud(int type, int choice, Scanner scanner) throws InputMismatchException {
		switch(type) {
		case 1:
			//flights
			switch(choice) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				
			}
			break;
		case 2:
			//seats
			switch(choice) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				
			}
			break;
		case 3:
			//tickets and passengers
			switch(choice) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				
			}
			break;
		case 4:
			//airports
			String iataId;
			String city;
			switch(choice) {
			case 1:
				//create
				System.out.println("Enter airport iata_id:");
				iataId = scanner.nextLine();
				System.out.println("Enter airport city:");
				city = scanner.nextLine();
				try {
					ServiceManager.getFlightService().createAirport(new Airport(iataId,city));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				//update
				System.out.println("Enter iata_id of Airport you wish to delete:");
				iataId = scanner.nextLine();
				System.out.println("Enter what city you want it changed to:");
				city = scanner.nextLine();
				try {
					ServiceManager.getFlightService().updateAirport(new Airport(iataId,city));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				//delete
				System.out.println("Enter iata_id of Airport you wish to delete:");
				iataId = scanner.nextLine();
				try {
					ServiceManager.getFlightService().deleteAirport(iataId);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				//read
				try {
					List<Airport> array = ServiceManager.getFlightService().readAllAirport();
					for(Airport a:array) {
						System.out.println(a.getIataId() + ", " + a.getCity());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		case 5:
			//travelers
			switch(choice) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				
			}
			break;
		case 6:
			//employees
			switch(choice) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				
			}
		}
	}

}
