/**
 * 
 */
package com.smoothstack.utopia.view;

import java.util.Scanner;

import com.smoothstack.utopia.jdbc.Util;
import com.smoothstack.utopia.jdbc.UtopiaUtil;
import com.smoothstack.utopia.screens.Screen;
import com.smoothstack.utopia.screens.ScreenManager;
import com.smoothstack.utopia.services.FlightService;
import com.smoothstack.utopia.services.ServiceManager;

/**
 * @author dyltr
 * initializes everything to run on console
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Set up connections utils and pass into services
		Util util = new UtopiaUtil("jdbc:mysql://localhost:3306/utopia", "Dylan", "Tran");

		ServiceManager.setFlightService(new FlightService(util));
		
		Screen curr = ScreenManager.getMain();
		Scanner scanner = new Scanner(System.in);
		while (curr!=null) {
			curr = curr.run(scanner);
		}
	}

}
