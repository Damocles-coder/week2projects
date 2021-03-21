/**
 * 
 */
package com.smoothstack.utopia.screens;

/**
 * @author dyltr
 * Class to create Pseudo Singletons of screens
 */
public class ScreenManager {
	private static Screen main;
	private static Screen emp1;
	private static Screen emp2;
	private static Screen emp3;
	private static Screen trav1;
	private static Screen trav2;
	private static Screen trav3;
	private static Screen admin;
	
	public static Screen getMain() {
		if (main==null) {
			main = new MainScreen();
		}
		return main;
	}
	static Screen getAdmin() {
		if (admin==null) {
			admin = new AdministratorScreen();
		}
		return admin;
	}
	static Screen getEMP1() {
		if (emp1==null) {
			emp1 = new EmployeeScreen();
		}
		return emp1;
	}
	static Screen getEMP2() {
		if (emp2==null) {
			emp2 = new EmployeeScreen2();
		}
		return emp2;
	}
	static Screen getEMP3() {
		if (emp3==null) {
			emp3 = new EmployeeScreen3();
		}
		return emp3;
	}
	static Screen getTRAV1() {
		if (trav1==null) {
			trav1 = new TravelerScreen();
		}
		return trav1;
	}
	static Screen getTRAV2() {
		if (trav2==null) {
			trav2 = new TravelerScreen2();
		}
		return trav2;
	}
	static Screen getTRAV3() {
		if (trav3==null) {
			trav3 = new TravelerScreen3();
		}
		return trav3;
	}
}
