/**
 * 
 */
package com.smoothstack.utopia.view;

import java.util.Scanner;

import com.smoothstack.utopia.screens.Screen;
import com.smoothstack.utopia.screens.ScreenManager;

/**
 * @author dyltr
 * initializes everything to run on console
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Screen curr = ScreenManager.getMain();
		Scanner scanner = new Scanner(System.in);
		while (curr!=null) {
			curr = curr.run(scanner);
		}
	}

}
