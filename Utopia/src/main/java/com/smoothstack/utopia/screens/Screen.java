package com.smoothstack.utopia.screens;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author dyltr
 * interface to implement modularity
 */
public interface Screen {

	public Screen run(Scanner scanner) throws InputMismatchException;
	
}
