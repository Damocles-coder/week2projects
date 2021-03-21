/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.smoothstack.utopia.entities.Airport;
import com.smoothstack.utopia.entities.Flight;
import com.smoothstack.utopia.entities.FlightRoute;
import com.smoothstack.utopia.entities.FlightStatus;
import com.smoothstack.utopia.services.ServiceManager;

/**
 * @author dyltr
 * View more details about selected flight
 */
public class EmployeeScreen3 implements Screen {
	private FlightRoute f;

	public Screen run(Scanner scanner) throws InputMismatchException {
		System.out.println("\n1) View more details about the flight");
		System.out.println("2) Update the details of the Flight");
		System.out.println("3) Add Seats to Flight");
		System.out.println("4) Quit to previous");
		//get FlightStatus from FlightRoute
		FlightStatus fs = null;
		try {
			fs = ServiceManager.getFlightService().getFlightStatus(f.getFlight());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch(scanner.nextInt()) {
		case 1:
			System.out.println(ServiceManager.getFlightService().getFlightInfo(f));
			return this;
		case 2:
			ServiceManager.getFlightService().addSeat(null, 0);
			return this;
		case 3:
			try {
				updatePrompts(scanner, f);
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Invalid inputs");
			}
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
	
	/**
	 * Assuming employee cannot add new airports or routes
	 * Find route id by airports
	 * 
	 * @param scanner
	 */
	private void updatePrompts(Scanner scanner, FlightRoute fr) throws Exception {
		String temp;
		String[] split;
		int routeId=0;
		Airport origin = null;
		Airport destination = null;
		boolean changed = false;
		System.out.println("Please enter new Origin Airport and City (seperated by a comma) or enter N/A for no change: ");
		temp = scanner.nextLine();
		if ("N/A".equalsIgnoreCase(temp)) {
			split = temp.split(",");
			origin = new Airport(split[0].trim(),split[1].trim());
		}
		System.out.println("Please enter new Desitination Airport and City (seperated by a comma) or enter N/A for no change: ");
		temp = scanner.nextLine();
		if ("N/A".equalsIgnoreCase(temp)) {
			split = temp.split(",");
			destination = new Airport(split[0].trim(),split[1].trim());
		}
		if (origin!=null||destination!=null) {
			if (origin==null) {
				origin = fr.getRoute().getSource();
			}
			if (destination==null) {
				destination=fr.getRoute().getDestination();
			}
			//getRouteId by airport
			fr.getFlight().setRouteId(routeId);
			changed=true;
		}
		String[] dateTime=new String[2];
		System.out.println("Please enter new Departure Date (MM/dd/yyyy) or enter N/A for no change: ");
		temp = scanner.nextLine();
		if("N/A".equalsIgnoreCase(temp)) {
			dateTime[0]=temp;
		}
		System.out.println("Please enter new Departure Time (HH:mm) or enter N/A for no change: ");
		temp = scanner.nextLine();
		if("N/A".equalsIgnoreCase(temp)) {
			dateTime[1]=temp;
		}
		if(dateTime[0]!=null||dateTime[1]!=null) {
			if(dateTime[0]==null) {
				//gets date from current departure
				dateTime[0]=fr.getFlight().getDeparture().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
			}
			if(dateTime[1]==null) {
				//gets time from current departure
				dateTime[1]=fr.getFlight().getDeparture().format(DateTimeFormatter.ofPattern("HH:mm"));
			}
			fr.getFlight().setDeparture(LocalDateTime.parse(dateTime[0]+" "+dateTime[1],
					DateTimeFormatter.ofPattern("HH:mm")));
			changed=true;
		}
		
		System.out.println("Please enter new Arival Date (MM/dd/yyyy) or enter N/A for no change: ");
		temp = scanner.nextLine();
		if("N/A".equalsIgnoreCase(temp)) {
			dateTime[0]=temp;
		}
		System.out.println("Please enter new Arrival Time (HH:mm) or enter N/A for no change: ");
		temp = scanner.nextLine();
		if("N/A".equalsIgnoreCase(temp)) {
			dateTime[1]=temp;
		}
		if(dateTime[0]!=null||dateTime[1]!=null) {
			if(dateTime[0]==null) {
				//gets date from current arrival
				dateTime[0]=fr.getFlight().getDeparture().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
			}
			if(dateTime[1]==null) {
				//gets time from current arrival
				dateTime[1]=fr.getFlight().getDeparture().format(DateTimeFormatter.ofPattern("HH:mm"));
			}
			fr.getFlight().setDeparture(LocalDateTime.parse(dateTime[0]+" "+dateTime[1],
					DateTimeFormatter.ofPattern("HH:mm")));
			changed=true;
		}
		if(changed) {
			try {
				ServiceManager.getFlightService().updateFlight(fr.getFlight());
				System.out.println("Successfully updated");
			}
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Update Unsuccessful");
			}
		}
		else {
			System.out.println("No change Detected");
		}
	}

}
