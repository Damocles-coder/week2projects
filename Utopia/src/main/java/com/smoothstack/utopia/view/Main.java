/**
 * 
 */
package com.smoothstack.utopia.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		Main m1 = new Main();
		Util util = m1.utilSetup();

		ServiceManager.setFlightService(new FlightService(util));
		
		Screen curr = ScreenManager.getMain();
		Scanner scanner = new Scanner(System.in);
		while (curr!=null) {
			curr = curr.run(scanner);
		}
	}
	
	private Util utilSetup() {
		BufferedReader r1;
		String url = null;
		String username = null;
		String password = null;
		try {
			r1 = new BufferedReader(new FileReader("../utopia/src/main/resources/sql_resources.txt"));
			url = r1.readLine();
			username = r1.readLine();
			password = r1.readLine();
			r1.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new UtopiaUtil(url, username, password);
	}

}
