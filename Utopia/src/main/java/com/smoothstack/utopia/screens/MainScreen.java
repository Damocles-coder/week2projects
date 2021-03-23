/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.sql.SQLException;
import java.util.Scanner;
import com.smoothstack.utopia.entities.User;
import com.smoothstack.utopia.services.ServiceManager;

/**
 * @author dyltr main menu screen
 */
public class MainScreen implements Screen {

	/**
	 * return emp1, trav1, or admin screen cannot proceed to trav1 without valid
	 * user id
	 */
	public Screen run(Scanner scanner) {
		Screen temp;

		System.out
				.println("\nWelcome to the Utopia Airlines Management System. " + "Which category of a user are you\n");
		System.out.println("1) Employee");
		System.out.println("2) Administrator");
		System.out.println("3) Traveler");
		switch (scanner.nextInt()) {
		case 1:
			temp = ScreenManager.getEMP1();
			// below is for finding flights related to employee
			// System.out.println("\nEnter the your Employee Number:");
			// ((EmployeeScreen)temp).setId(scanner.nextInt());
			return temp;
		case 2:
			temp = ScreenManager.getAdmin();
			return temp;
		case 3:
			temp = ScreenManager.getTRAV1();
			System.out.println("\nEnter the your Membership Number:");
			// check if valid
			int key = scanner.nextInt();
			User user = null;
			try {
				user = ServiceManager.getUserService().checkKey(key);
				if (user == null) {
					return this;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			((TravelerScreen) temp).setUser(user);
			return temp;
		default:
			System.out.println("Invalid input");
			return this;
		}
	}

}
