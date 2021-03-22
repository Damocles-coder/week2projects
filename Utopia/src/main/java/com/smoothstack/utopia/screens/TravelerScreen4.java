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
 *
 */
public class TravelerScreen4 implements Screen {
	private User user;

	public Screen run(Scanner scanner) throws InputMismatchException {
		System.out.println("\nPick the Flight you want to cancel:");
		//call method from flight service that returns a list of flights
		List<FlightRoute> array = null;
		try {
			array = ServiceManager.getFlightService().getFlightListFiltered(user.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 1;
		if(array!=null) {
			for (FlightRoute i:array) {
				System.out.println(ServiceManager.getFlightService().printRoute(i.getRoute(),count++));
			}
		}
		System.out.println(count+") Quit to previous");
		int choice = scanner.nextInt();
		if(choice==count) {
			return ScreenManager.getTRAV1();
		}
		else if (choice>=1&&choice<count) {
			Screen temp = ScreenManager.getTRAV5();
			((TravelerScreen5)temp).setF(array.get(choice-1));
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
