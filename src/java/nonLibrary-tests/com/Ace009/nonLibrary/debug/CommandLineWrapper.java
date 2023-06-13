package com.Ace009.nonLibrary.debug;

import java.util.Map;
import java.util.HashMap;

import com.Ace009.library.*;
import com.Ace009.library.CClass.*;
import com.Ace009.library.Math.*;
//import com.Ace009.library.CoordinateSystem.*;

/**
 * Command line wrapper for {@link MainMethods}
 * @author Ace009
 */
 // and potentially other debug methods
public class CommandLineWrapper {
	/** don't */
	private CommandLineWrapper() {}
	/**
	 * system default main method, uses {@link Args#ask(String)} to run specifed commands
	 * @param args default system arguments, will de used when calling a main method
	 */
	public static void main(String[] args) {
		boolean running = true;
		String command;
		System.out.println("\nRunning CommandLineWrapper:");
		while (running) {
			command = Args.ask("\nCommand").toLowerCase();
			switch (command) {
				case"stop":
				case"break":
					running=false;
					System.out.println("stopping...");
					return;
				case"run":
				case"main":
					System.out.println("running MainMethods...");
					MainMethods.main(args); break;
				case"object":
				case"cobject":
					System.out.println("running CObject Test...");
					CobjectTest(); break;
			}
			System.out.println("\nreturning to Wrapper...");
		}
	}
	/**
	 * 
	 */
	public static void CobjectTest() {
		String type = Args.ask("type").toLowerCase();
		Object test;
		System.out.println("Getting test object...");
		switch (type) {
			case "args":
				int number = Integer.parseInt(Args.ask("Number of Args(numerical)"));
				String[] askKey = new String[number];
				for (int i = 0; i < number; i++) {
					askKey[i] = i+". Entry";
				}
				test = new Args(Args.OutputType.String, askKey);
				break;
			case"fraction":
				int[] nr = new int[2];
				nr[0] = Integer.parseInt(Args.ask("numerator"));
				nr[1] = Integer.parseInt(Args.ask("denominator"));
				test = new Fraction(nr[0], nr[1]);
				break;
			default:
				System.out.println("No implemented Object Type given,"); return;
		}
		Map<String, Object> output = new HashMap<>();
		try { output = CObject.entriesMap(test); }
		catch (Exception e) { e.printStackTrace(); }
		System.out.printf("Debug: %s; %s\n", test, output);
		for (String E : CMap.print(output)) {
			System.out.print(E);
		}
	}
}