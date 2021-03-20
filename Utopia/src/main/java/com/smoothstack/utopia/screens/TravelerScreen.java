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
public class TravelerScreen implements Screen {
	private int id;

	public Screen run(Scanner scanner) throws InputMismatchException {
		System.out.println("\n1) Book a Ticket");
		System.out.println("2) Cancel an Upcoming Trip");
		System.out.println("3) Quit to Previous");
		switch(scanner.nextInt()) {
		case 1:
			return ScreenManager.getTRAV2();
		case 2:
			return ScreenManager.getTRAV3();
		case 3:
			return ScreenManager.getMain();
		default:
			System.out.println("Invalid choice.");
			return this;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
