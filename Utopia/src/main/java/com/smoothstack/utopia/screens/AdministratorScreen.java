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
public class AdministratorScreen implements Screen {

	public Screen run(Scanner scanner) throws InputMismatchException {
		System.out.println("\n1) Add/Update/Delete/Read Flights");
		System.out.println("2) Add/Update/Delete/Read Seats");
		System.out.println("3) Add/Update/Delete/Read Tickets and Passengers");
		System.out.println("4) Add/Update/Delete/Read Airports");
		System.out.println("5) Add/Update/Delete/Read Travelers");
		System.out.println("6) Add/Update/Delete/Read Employees");
		System.out.println("7) Over-ride Trip Cancellation for a ticket.");
		
		int type = scanner.nextInt();
		if (type>=0&&type<7) {
			System.out.println("1) Add");
			System.out.println("2) Update");
			System.out.println("3) Delete");
			System.out.println("4) Read");
			int choice = scanner.nextInt();
			crud(type,choice);
		}
		else if (type==7) {
			overrideTicket();
		}
		else {
			return null;
		}
		return this;
	}
	
	private void overrideTicket() {
		
	}
	
	/**
	 * @param choice
	 */
	private void crud(int type, int choice) {
		switch(type) {
		case 1:
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
		case 5:
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
