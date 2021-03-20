package com.smoothstack.utopia.screens;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface Screen {

	public Screen run(Scanner scanner) throws InputMismatchException;
	
}
