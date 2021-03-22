/**
 * 
 */
package com.smoothstack.utopia.screens;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author dyltr
 * main employee screen
 */
public class EmployeeScreen implements Screen {
	private int id;
	
	/**
	 * return employee flights screen or main based on input
	 */
	public Screen run(Scanner scanner) throws InputMismatchException {
		System.out.println("\n1) Enter Flights You Manage");
		System.out.println("2) Quit to previous");
		switch(scanner.nextInt()) {
		case 1:
			return ScreenManager.getEMP2();
		case 2:
			return ScreenManager.getMain();
		default:
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
