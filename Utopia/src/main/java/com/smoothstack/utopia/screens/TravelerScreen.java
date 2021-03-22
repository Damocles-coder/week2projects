/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.smoothstack.utopia.entities.User;

/**
 * @author dyltr
 *
 */
public class TravelerScreen implements Screen {
	private User user;

	public Screen run(Scanner scanner) throws InputMismatchException {
		System.out.println("\n1) Book a Ticket");
		System.out.println("2) Cancel an Upcoming Trip");
		System.out.println("3) Quit to Previous");
		Screen temp;
		switch(scanner.nextInt()) {
		case 1:
			temp = ScreenManager.getTRAV2();
			((TravelerScreen2)temp).setUser(user);
			return ScreenManager.getTRAV2();
		case 2:
			temp = ScreenManager.getTRAV4();
			((TravelerScreen4)temp).setUser(user);
			return temp;
		case 3:
			return ScreenManager.getMain();
		default:
			System.out.println("Invalid choice.");
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
