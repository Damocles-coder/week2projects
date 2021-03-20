/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author dyltr
 *
 */
public class MainScreen implements Screen {
	
	public Screen run(Scanner scanner) {
		Screen temp;
		
		System.out.println("\nWelcome to the Utopia Airlines Management System. "
				+ "Which category of a user are you\n");
		System.out.println("1) Employee");
		System.out.println("2) Administrator");
		System.out.println("3) Traveler");
		switch(scanner.nextInt()) {
		case 1:
			temp = ScreenManager.getEMP1();
			System.out.println("\nEnter the your Employee Number:");
			((EmployeeScreen)temp).setId(scanner.nextInt());
			return temp;
		case 2:
			temp = ScreenManager.getAdmin();
			return temp;
		case 3:
			temp = ScreenManager.getTRAV1();
			System.out.println("\nEnter the your Membership Number:");
			((TravelerScreen)temp).setId(scanner.nextInt());
			return temp;
		default:
			System.out.println("Invalid input");
			return this;
		}
	}

}
