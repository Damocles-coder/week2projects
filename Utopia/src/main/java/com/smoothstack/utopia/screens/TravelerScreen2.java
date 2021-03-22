/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.entities.FlightRoute;
import com.smoothstack.utopia.entities.User;
import com.smoothstack.utopia.services.ServiceManager;

/**
 * @author dyltr
 * screen that prints out every flight
 */
public class TravelerScreen2 implements Screen {
	private User user;

	/**
	 *return trav3 or previous
	 *passes in user and flightroute object into trav3
	 */
	public Screen run(Scanner scanner) throws InputMismatchException {
		System.out.println("Pick the Flight you want to book a ticket for: /n/n");
		//call method from flight service that returns a list of flights
		List<FlightRoute> array = null;
		try {
			array = ServiceManager.getFlightService().getFlightList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 1;
		for (FlightRoute i:array) {
			System.out.println(ServiceManager.getFlightService().printRoute(i.getRoute(),count++));
		}
		System.out.println(count+") Quit to previous");
		int choice = scanner.nextInt();
		if(choice==count) {
			return ScreenManager.getTRAV1();
		}
		else if (choice>=1&&choice<count) {
			Screen temp = ScreenManager.getTRAV3();
			((TravelerScreen3)temp).setUser(user);
			((TravelerScreen3)temp).setF(array.get(choice-1));
			return temp;
		}
		else {
			System.out.println("Invalid input");
			return this;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
