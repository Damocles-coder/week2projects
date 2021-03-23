/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.entities.AirplaneType;
import com.smoothstack.utopia.entities.Airport;
import com.smoothstack.utopia.entities.Flight;
import com.smoothstack.utopia.entities.FlightRoute;
import com.smoothstack.utopia.entities.FlightStatus;
import com.smoothstack.utopia.entities.Route;
import com.smoothstack.utopia.entities.User;
import com.smoothstack.utopia.services.ServiceManager;

/**
 * @author dyltr administrator screen
 */
public class AdministratorScreen implements Screen {

	/**
	 * returns this screen if 1-7, !=8 selected, returns main menu if 8 is selected
	 */
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
		if (type >= 0 && type < 7) {
			try {
				crud(type, scanner);
			} catch (InputMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (type == 7) {
			overrideTicket(scanner);
		} else if (type == 8) {
			return ScreenManager.getMain();
		}
		return ScreenManager.getAdmin();
	}

	/**
	 * Copied from employee screen 2 Find route id by airports
	 * 
	 * @param scanner
	 */
	private void updateFlight(Scanner scanner, FlightRoute fr) throws Exception {
		String temp;
		String[] split;
		int routeId = 0;
		Airport origin = null;
		Airport destination = null;
		boolean changed = false;
		System.out.println("You have chosen to update the Flight with Flight Id: " + fr.getFlight().getId()
				+ " and Flight Origin: " + fr.getRoute().getSource().getIataId() + " and Flight Destination:"
				+ fr.getRoute().getDestination().getIataId() + ". ");
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		// flush nextLine from scanner
		scanner.nextLine();
		System.out.println(
				"Please enter new Origin Airport and City (seperated by a comma) or enter N/A for no change: ");
		temp = scanner.nextLine();
		if ("quit".equalsIgnoreCase(temp)) {
			return;
		}
		if (!"N/A".equalsIgnoreCase(temp)) {
			split = temp.split(",");
			origin = new Airport(split[0].trim(), split[1].trim());
		}
		System.out.println(
				"Please enter new Desitination Airport and City (seperated by a comma) or enter N/A for no change: ");
		temp = scanner.nextLine();
		if ("quit".equalsIgnoreCase(temp)) {
			return;
		}
		if (!"N/A".equalsIgnoreCase(temp)) {
			split = temp.split(",");
			destination = new Airport(split[0].trim(), split[1].trim());
		}
		if (origin != null || destination != null) {
			if (origin == null) {
				origin = fr.getRoute().getSource();
			}
			if (destination == null) {
				destination = fr.getRoute().getDestination();
			}
			routeId = ServiceManager.getFlightService().findRoute(new Route(origin, destination)).getId();
			fr.getFlight().setRouteId(routeId);
			changed = true;
		}
		String[] dateTime = new String[2];
		System.out.println("Please enter new Departure Date (MM/dd/yyyy) or enter N/A for no change: ");
		temp = scanner.nextLine();
		if ("quit".equalsIgnoreCase(temp)) {
			return;
		}
		if (!"N/A".equalsIgnoreCase(temp)) {
			dateTime[0] = temp;
		}
		System.out.println("Please enter new Departure Time (HH:mm) or enter N/A for no change: ");
		temp = scanner.nextLine();
		if ("quit".equalsIgnoreCase(temp)) {
			return;
		}
		if (!"N/A".equalsIgnoreCase(temp)) {
			dateTime[1] = temp;
		}
		if (dateTime[0] != null || dateTime[1] != null) {
			if (dateTime[0] == null) {
				// gets date from current departure
				dateTime[0] = fr.getFlight().getDeparture().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
			}
			if (dateTime[1] == null) {
				// gets time from current departure
				dateTime[1] = fr.getFlight().getDeparture().format(DateTimeFormatter.ofPattern("HH:mm"));
			}
			fr.getFlight().setDeparture(LocalDateTime.parse(dateTime[0] + " " + dateTime[1],
					DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")));
			changed = true;
			System.out.println(fr.getFlight().getDeparture().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")));
		}

		System.out.println("Please enter new Arival Date (MM/dd/yyyy) or enter N/A for no change: ");
		temp = scanner.nextLine();
		if (!"N/A".equalsIgnoreCase(temp)) {
			dateTime[0] = temp;
		}
		temp = scanner.nextLine();
		if (!"N/A".equalsIgnoreCase(temp)) {
			dateTime[1] = temp;
		}
		if (dateTime[0] != null || dateTime[1] != null) {
			if (dateTime[0] == null) {
				// gets date from current arrival
				dateTime[0] = fr.getFlight().getArrival().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
			}
			if (dateTime[1] == null) {
				// gets time from current arrival
				dateTime[1] = fr.getFlight().getArrival().format(DateTimeFormatter.ofPattern("HH:mm"));
			}
			fr.getFlight().setArrival(LocalDateTime.parse(dateTime[0] + " " + dateTime[1],
					DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")));
			changed = true;
		}
		if (changed) {
			try {
				ServiceManager.getFlightService().updateFlight(fr.getFlight());
				System.out.println("Successfully updated");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Update Unsuccessful");
			}
		} else {
			System.out.println("No change Detected");
		}
	}

	private void overrideTicket(Scanner scanner) throws InputMismatchException {
		// set booking is_active to true
		List<User> users = null;
		List<FlightRoute> flights = null;
		User user = null;
		try {
			users = ServiceManager.getUserService().readAllTravelers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int count = 0;
		for (User u : users) {
			System.out.println((count++) + ") Username: " + u.getUsername());
		}
		System.out.println("What user would you like to look up?:");
		int choice = scanner.nextInt();
		user = users.get(choice);
		// list of canceled flights
		try {
			flights = ServiceManager.getFlightService().getFlightListFilteredCancel(user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		count = 0;
		for (FlightRoute f : flights) {
			System.out.println(ServiceManager.getFlightService().printRoute(f.getRoute(), count++));
		}
		if (flights.size() == 0) {
			System.out.println("There are no flights cancelled for this user");
			return;
		}
		System.out.println("What flight would you like to override?: ");
		choice = scanner.nextInt();
		try {
			ServiceManager.getFlightService().rebookSeat(user, flights.get(choice));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crud switch method. A little redundant at this point but makes the screen
	 * readable
	 * 
	 * @param choice of which item you want to operate on
	 * @throws SQLException
	 */
	private void crud(int type, Scanner scanner) throws InputMismatchException, SQLException {
		switch (type) {
		case 1:
			// flights
			flightCrud(scanner);
			break;
		case 2:
			// seats
			seatCrud(scanner);
			break;
		case 3:
			// tickets and passengers
			bookingCrud(scanner);
		case 4:
			// airports
			airportCrud(scanner);
			break;
		case 5:
			// travelers
			travelerCrud(scanner);
			break;
		case 6:
			// employees
			employeeCrud(scanner);
		}
	}

	/**
	 * A very long create flight operation deserves its own method
	 * 
	 * @param scanner
	 */
	private void flightCreate(Scanner scanner) {
		List<Airport> aArray = null;
		Airport origin;
		Airport destination;
		String iataId;
		String city;
		int routeId = 0;
		int airplaneId;
		try {
			aArray = ServiceManager.getFlightService().readAllAirport();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 0;
		for (Airport i : aArray) {
			System.out.println((count++) + ") " + i.getIataId() + ", " + i.getCity());
		}
		System.out.println(count + ") create new airport");
		// create airport
		System.out.println("Enter in origin Airport or create one");
		int choice = scanner.nextInt();
		if (choice == count) {
			System.out.println("Enter the iata_id: ");
			iataId = scanner.nextLine();
			System.out.println("Enter the city: ");
			city = scanner.nextLine();
			origin = new Airport(iataId, city);
			try {
				ServiceManager.getFlightService().createAirport(origin);
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
		} else {
			origin = aArray.get(choice);
		}
		System.out.println("Enter in destination Airport or create one");
		choice = scanner.nextInt();
		if (choice == count) {
			System.out.println("Enter the iata_id: ");
			iataId = scanner.nextLine();
			System.out.println("Enter the city: ");
			city = scanner.nextLine();
			destination = new Airport(iataId, city);
			try {
				ServiceManager.getFlightService().createAirport(destination);
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
		} else {
			destination = aArray.get(choice);
		}
		// create ignore route since it isn't guaranteed to exist
		Route route = new Route(origin, destination);
		try {
			ServiceManager.getFlightService().createRoute(route);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		try {
			routeId = ServiceManager.getFlightService().findRoute(route).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// Only create departure and arrival times as we lack a table for them
		scanner.nextLine();
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		System.out.println("Please enter an departure DateTime (MM/dd/yyyy HH:mm)");
		LocalDateTime departure = LocalDateTime.parse(scanner.nextLine(), pattern);
		System.out.println("Please enter an departure DateTime (MM/dd/yyyy HH:mm)");
		LocalDateTime arrival = LocalDateTime.parse(scanner.nextLine(), pattern);
		System.out.println("Please enter airplane_id: ");
		airplaneId = scanner.nextInt();
		try {
			ServiceManager.getFlightService()
					.createFlight(new Flight(routeId, airplaneId, departure, arrival, 100, 100, 100, 100));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * crud operations on flight objects
	 * 
	 * @param scanner
	 * @throws SQLException
	 */
	private void flightCrud(Scanner scanner) throws SQLException {
		int count;
		int choice;
		List<FlightRoute> fArray = null;
		System.out.println("1) Add");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		System.out.println("4) Read");
		switch (scanner.nextInt()) {
		case 1:
			// create, used hash for pk
			flightCreate(scanner);
			break;
		case 3:
			// delete by flight_id
			fArray = null;
			try {
				fArray = ServiceManager.getFlightService().getFlightList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count = 0;
			for (FlightRoute i : fArray) {
				System.out.println(ServiceManager.getFlightService().printRoute(i.getRoute(), count++));
			}
			System.out.println(count + ") Quit");
			System.out.println("Choose the Flight you wish to delete: ");
			choice = scanner.nextInt();
			if (choice == count) {
				return;
			}
			ServiceManager.getFlightService().deleteFlight(fArray.get(choice).getFlight());
			break;
		case 2:
			// update Flight
			fArray = null;
			try {
				fArray = ServiceManager.getFlightService().getFlightList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count = 0;
			for (FlightRoute i : fArray) {
				System.out.println(ServiceManager.getFlightService().printRoute(i.getRoute(), count++));
			}
			System.out.println("Choose the Flight you wish to delete: ");
			choice = scanner.nextInt();
			try {
				updateFlight(scanner, fArray.get(choice));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			fArray = null;
			try {
				fArray = ServiceManager.getFlightService().getFlightList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count = 0;
			for (FlightRoute i : fArray) {
				System.out.println(ServiceManager.getFlightService().printRoute(i.getRoute(), count++));
			}
			System.out.println("Choose the Flight you wish to read: ");
			choice = scanner.nextInt();
			try {
				FlightStatus fs = ServiceManager.getFlightService().getFlightStatus(fArray.get(choice).getFlight());
				System.out.println(ServiceManager.getFlightService().getFlightInfo(fArray.get(choice), fs));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * crud operation on seat in airplane_type objects decided to make the whole
	 * crud operation happen on airplane_type
	 * 
	 * @param scanner
	 */
	private void seatCrud(Scanner scanner) {
		List<AirplaneType> array = null;
		AirplaneType airplane = null;
		int cap1;
		int cap2;
		int cap3;
		int choice;
		try {
			array = ServiceManager.getFlightService().readAllAirplaneType();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (AirplaneType a : array) {
			System.out.println(a.getId() + ": Capacity1-" + a.getCapacity() + " | Capacity2-" + a.getCapacity2()
					+ " | Capacity3-" + a.getCapacity3());
		}
		System.out.println("1) Add");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		System.out.println("4) Read");
		switch (scanner.nextInt()) {
		case 1:
			// create
			System.out.println("Enter first Class capacity");
			cap1 = scanner.nextInt();
			System.out.println("Enter Business Class capacity");
			cap2 = scanner.nextInt();
			System.out.println("Enter Economy Class capacity");
			cap3 = scanner.nextInt();
			airplane = new AirplaneType(cap1, cap2, cap3);
			try {
				ServiceManager.getFlightService().createAiplane(airplane);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			// update
			System.out.println("Enter Airplane ID");
			choice = scanner.nextInt();
			System.out.println("Enter new first Class capacity");
			cap1 = scanner.nextInt();
			System.out.println("Enter new Business Class capacity");
			cap2 = scanner.nextInt();
			System.out.println("Enter new Economy Class capacity");
			cap3 = scanner.nextInt();
			airplane = new AirplaneType(choice, cap1, cap2, cap3);
			try {
				ServiceManager.getFlightService().updateAirplane(airplane);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			// delete
			System.out.println("Enter Airplane ID of plane you want to delete");
			choice = scanner.nextInt();
			try {
				ServiceManager.getFlightService().deleteAirplane(choice);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 4:
			// read
			System.out.println("Enter Airplane ID of plane you want to read");
			choice = scanner.nextInt();
			try {
				airplane = ServiceManager.getFlightService().readAirplaneType(choice);
				System.out.println(airplane.getId() + ": Capacity1-" + airplane.getCapacity() + " | Capacity2-"
						+ airplane.getCapacity2() + " | Capacity3-" + airplane.getCapacity3());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	/**
	 * booking crud not implemented
	 * 
	 * @param scanner
	 */
	private void bookingCrud(Scanner scanner) {
		System.out.println("1) Add");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		System.out.println("4) Read");
		switch (scanner.nextInt()) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:

		}
	}

	/**
	 * crud ops on airports
	 * 
	 * @param scanner
	 */
	private void airportCrud(Scanner scanner) {
		List<Airport> airports = null;
		try {
			airports = ServiceManager.getFlightService().readAllAirport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Airport a : airports) {
			System.out.println(a.getIataId() + ", " + a.getCity());
		}
		System.out.println("1) Add");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		System.out.println("4) Read");
		String iataId;
		String city;
		switch (scanner.nextInt()) {
		case 1:
			// create
			scanner.nextLine();
			System.out.println("Enter airport iata_id:");
			iataId = scanner.nextLine();
			System.out.println("Enter airport city:");
			city = scanner.nextLine();
			try {
				ServiceManager.getFlightService().createAirport(new Airport(iataId, city));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			// update
			scanner.nextLine();
			System.out.println("Enter iata_id of Airport you wish to update:");
			iataId = scanner.nextLine();
			System.out.println("Enter what city you want it changed to:");
			city = scanner.nextLine();
			try {
				ServiceManager.getFlightService().updateAirport(new Airport(iataId, city));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			// delete
			scanner.nextLine();
			System.out.println("Enter iata_id of Airport you wish to delete:");
			iataId = scanner.nextLine();
			try {
				ServiceManager.getFlightService().deleteAirport(iataId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 4:
			// read
			scanner.nextLine();
			System.out.println("Enter the iata_id of the airport you want to look up: ");
			iataId = scanner.nextLine();
			try {
				Airport a = ServiceManager.getFlightService().readAirport(iataId);
				System.out.println(a.getIataId() + ", " + a.getCity());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * crud ops on travelers
	 * 
	 * @param scanner
	 */
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
		for (User u : travelers) {
			System.out.println(u.getEmail());
		}
		System.out.println("1) Add");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		System.out.println("4) Read");
		switch (scanner.nextInt()) {
		case 1:
			// create
			// id set to auto increment, role set to 2
			scanner.nextLine();
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
			scanner.nextLine();
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
			// delete
			scanner.nextLine();
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
			scanner.nextLine();
			System.out.println("Enter email of traveler you like to read: ");
			email = scanner.nextLine();
			try {
				user = ServiceManager.getUserService().read(email);
				System.out.println("Traveler number: " + user.getId());
				System.out.println("Email: " + user.getEmail());
				System.out.println("Username: " + user.getUsername());
				System.out.println("Password: " + user.getPassword());
				System.out.println("First Name: " + user.getGivenName());
				System.out.println("Last Name: " + user.getFamilyName());
				System.out.println("Role-id: " + user.getRoleId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * crud ops on employees
	 * 
	 * @param scanner
	 */
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
		for (User u : employees) {
			System.out.println(u.getEmail());
		}
		System.out.println("1) Add");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		System.out.println("4) Read");
		switch (scanner.nextInt()) {
		case 1:
			// create
			scanner.nextLine();
			// id set to auto increment, role set to 2
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
		case 3:
			// delete
			scanner.nextLine();
			System.out.println("Enter email of employee you wish to delete:");
			email = scanner.nextLine();
			try {
				ServiceManager.getUserService().deleteEmployee(email);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			scanner.nextLine();
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
			scanner.nextLine();
			System.out.println("Enter email of employee you like to read: ");
			email = scanner.nextLine();
			try {
				user = ServiceManager.getUserService().read(email);
				System.out.println("Employee number: " + user.getId());
				System.out.println("Email: " + user.getEmail());
				System.out.println("Username: " + user.getUsername());
				System.out.println("Password: " + user.getPassword());
				System.out.println("First Name: " + user.getGivenName());
				System.out.println("Last Name: " + user.getFamilyName());
				System.out.println("Role-id: " + user.getRoleId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
