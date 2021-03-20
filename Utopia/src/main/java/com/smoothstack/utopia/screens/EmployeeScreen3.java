/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.smoothstack.utopia.entities.Flight;

/**
 * @author dyltr
 * View more details about selected flight
 */
public class EmployeeScreen3 implements Screen {
	Flight flight;

	public Screen run(Scanner scanner) throws InputMismatchException {
		System.out.println("\n1) View more details about the flight");
		System.out.println("2)	Update the details of the Flight");
		System.out.println("3)	Add Seats to Flight");
		System.out.println("4)	Quit to previous");
		switch(scanner.nextInt()) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			return new EmployeeScreen2();
		default:
			System.out.println("/nIncorrect input for flight menu.");
			return null;
		}
		return this;
	}

}
