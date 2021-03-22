/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.entities.Airport;
import com.smoothstack.utopia.entities.FlightRoute;
import com.smoothstack.utopia.entities.User;
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
			crud(type,scanner);
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
	private void crud(int type, Scanner scanner) throws InputMismatchException {
		switch(type) {
		case 1:
		//flights
			flightCrud(scanner);
			break;
		case 2:
		//seats
			seatCrud(scanner);
			break;
		case 3:
			//tickets and passengers
			bookingCrud(scanner);
		case 4:
			//airports
			airportCrud(scanner);
			break;
		case 5:
		//travelers
			travelerCrud(scanner);
			break;
		case 6:
		//employees
			employeeCrud(scanner);
		}
	}
	
	private void flightCrud(Scanner scanner) {
		List<FlightRoute> fArray = null;
		try {
			fArray = ServiceManager.getFlightService().getFlightList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 0;
		if(fArray!=null) {
			for (FlightRoute i:fArray) {
				System.out.println(ServiceManager.getFlightService().printRoute(i.getRoute(),count++));
			}
		}
		System.out.println("1) Add");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		System.out.println("4) Read");
		switch(scanner.nextInt()) {
		case 1:
			//create
			
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			
		}
	}
	
	private void seatCrud(Scanner scanner) {
		System.out.println("1) Add");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		System.out.println("4) Read");
		switch(scanner.nextInt()) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			
		}
	}
	
	private void bookingCrud(Scanner scanner) {
		System.out.println("1) Add");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		System.out.println("4) Read");
		switch(scanner.nextInt()) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				
		}
	}
	
 	private void airportCrud(Scanner scanner) {
		List <Airport> airports = null;
		try {
			airports = ServiceManager.getFlightService().readAllAirport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Airport a:airports) {
			System.out.println(a.getIataId()+", "+a.getCity());
		}
		System.out.println("1) Add");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		System.out.println("4) Read");
		String iataId;
		String city;
		switch(scanner.nextInt()) {
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
				System.out.println("Enter the iata_id of the airport you want to look up: ");
				iataId = scanner.nextLine();
				try {
					Airport a = ServiceManager.getFlightService().readAirport(iataId);
					System.out.println(a.getIataId()+", "+a.getCity());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
	
	private void travelerCrud(Scanner scanner) {
		String givenName;
		String familyName;
		String username;
		String email;
		String password;
		String phone;
		User user = null;
		List<User> travelers = null;
		try {
			travelers = ServiceManager.getUserService().readAllTravelers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(User u:travelers) {
			System.out.println(u.getEmail());
		}
		System.out.println("1) Add");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		System.out.println("4) Read");
		switch(scanner.nextInt()) {
			case 1:
				//create
				//id set to auto increment, role set to 2
				System.out.println("Enter given name of new traveler:");
				givenName = scanner.nextLine();
				System.out.println("Enter family name of new traveler:");
				familyName = scanner.nextLine();
				System.out.println("Enter username of new traveler:");
				username = scanner.nextLine();
				System.out.println("Enter email of new traveler:");
				email = scanner.nextLine();
				System.out.println("Enter password of new traveler:");
				password = scanner.nextLine();
				System.out.println("Enter phone of new traveler:");
				phone = scanner.nextLine();
				user = new User(givenName, familyName, username, email, password, phone);
				try {
					ServiceManager.getUserService().createTraveler(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Enter email of traveler you want to update:");
				email = scanner.nextLine();
				try {
					user = ServiceManager.getUserService().read(email);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				System.out.println("Enter new roleId or N/A:");
				givenName = scanner.nextLine();
				if (!"N/A".equalsIgnoreCase(givenName)) {
					user.setId(Integer.parseInt(givenName));
				}
				System.out.println("Enter new given name or N/A:");
				givenName = scanner.nextLine();
				if (!"N/A".equalsIgnoreCase(givenName)) {
					user.setGivenName(givenName);
				}
				System.out.println("Enter new family name or N/A:");
				givenName = scanner.nextLine();
				if (!"N/A".equalsIgnoreCase(givenName)) {
					user.setFamilyName(givenName);
				}
				System.out.println("Enter new username or N/A:");
				givenName = scanner.nextLine();
				if (!"N/A".equalsIgnoreCase(givenName)) {
					user.setUsername(givenName);
				}
				System.out.println("Enter new email or N/A:");
				givenName = scanner.nextLine();
				if (!"N/A".equalsIgnoreCase(givenName)) {
					user.setEmail(givenName);
				}
				System.out.println("Enter new password or N/A:");
				givenName = scanner.nextLine();
				if (!"N/A".equalsIgnoreCase(givenName)) {
					user.setPassword(givenName);
				}
				System.out.println("Enter new phone or N/A:");
				givenName = scanner.nextLine();
				if (!"N/A".equalsIgnoreCase(givenName)) {
					user.setPhone(givenName);
				}
				try {
					ServiceManager.getUserService().updateUser(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				//delete
				System.out.println("Enter email of traveler you wish to delete:");
				email = scanner.nextLine();
				try {
					ServiceManager.getUserService().deleteTraveler(email);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.println("Enter email of traveler you like to read: ");
				email = scanner.nextLine();
				try {
					user = ServiceManager.getUserService().read(email);
					System.out.println("Email: "+user.getEmail());
					System.out.println("Username: "+user.getUsername());
					System.out.println("Password: "+user.getPassword());
					System.out.println("First Name: "+user.getGivenName());
					System.out.println("Last Name: "+user.getFamilyName());
					System.out.println("Role-id: "+user.getRoleId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
 	private void employeeCrud(Scanner scanner) {
		String givenName;
		String familyName;
		String username;
		String email;
		String password;
		String phone;
		User user = null;
		List<User> employees = null;
		try {
			employees = ServiceManager.getUserService().readAllEmployees();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(User u:employees) {
			System.out.println(u.getEmail());
		}
		System.out.println("1) Add");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		System.out.println("4) Read");
		switch(scanner.nextInt()) {
			case 1:
				//create
				//id set to auto increment, role set to 2
				System.out.println("Enter given name of new employee:");
				givenName = scanner.nextLine();
				System.out.println("Enter family name of new employee:");
				familyName = scanner.nextLine();
				System.out.println("Enter username of new employee:");
				username = scanner.nextLine();
				System.out.println("Enter email of new employee:");
				email = scanner.nextLine();
				System.out.println("Enter password of new employee:");
				password = scanner.nextLine();
				System.out.println("Enter phone of new employee:");
				phone = scanner.nextLine();
				user = new User(givenName, familyName, username, email, password, phone);
				try {
					ServiceManager.getUserService().createEmployee(user);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				//delete
				System.out.println("Enter email of employee you wish to delete:");
				email = scanner.nextLine();
				try {
					ServiceManager.getUserService().deleteEmployee(email);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				System.out.println("Enter email of employee you want to update:");
				email = scanner.nextLine();
				try {
					user = ServiceManager.getUserService().read(email);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				System.out.println("Enter new roleId or N/A:");
				givenName = scanner.nextLine();
				if (!"N/A".equalsIgnoreCase(givenName)) {
					user.setId(Integer.parseInt(givenName));
				}
				System.out.println("Enter new given name or N/A:");
				givenName = scanner.nextLine();
				if (!"N/A".equalsIgnoreCase(givenName)) {
					user.setGivenName(givenName);
				}
				System.out.println("Enter new family name or N/A:");
				givenName = scanner.nextLine();
				if (!"N/A".equalsIgnoreCase(givenName)) {
					user.setFamilyName(givenName);
				}
				System.out.println("Enter new username or N/A:");
				givenName = scanner.nextLine();
				if (!"N/A".equalsIgnoreCase(givenName)) {
					user.setUsername(givenName);
				}
				System.out.println("Enter new email or N/A:");
				givenName = scanner.nextLine();
				if (!"N/A".equalsIgnoreCase(givenName)) {
					user.setEmail(givenName);
				}
				System.out.println("Enter new password or N/A:");
				givenName = scanner.nextLine();
				if (!"N/A".equalsIgnoreCase(givenName)) {
					user.setPassword(givenName);
				}
				System.out.println("Enter new phone or N/A:");
				givenName = scanner.nextLine();
				if (!"N/A".equalsIgnoreCase(givenName)) {
					user.setPhone(givenName);
				}
				try {
					ServiceManager.getUserService().updateUser(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.println("Enter email of employee you like to read: ");
				email = scanner.nextLine();
				try {
					user = ServiceManager.getUserService().read(email);
					System.out.println("Email: "+user.getEmail());
					System.out.println("Username: "+user.getUsername());
					System.out.println("Password: "+user.getPassword());
					System.out.println("First Name: "+user.getGivenName());
					System.out.println("Last Name: "+user.getFamilyName());
					System.out.println("Role-id: "+user.getRoleId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

}
