package com.Ace009.nonLibrary.debug;

import com.Ace009.library.Args;

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
		System.out.println("\nRunning MainMethod CommandLineWrapper:");
		while (running) {
			command = Args.ask("\nCommand").toLowerCase();
			switch (command) {
				case"stop":
				case"break":
					running=false; break;
				case"run":
				case"main":
					System.out.println("running MainMethods...");
					MainMethods.main(args); break;
			}
			System.out.println("\nreturning to Wrapper...");
		}
		System.out.println("stopping...");
	}
}