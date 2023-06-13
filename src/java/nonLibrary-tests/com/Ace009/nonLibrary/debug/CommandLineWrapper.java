package com.Ace009.nonLibrary.debug;

import com.Ace009.library.Args;

public class CommandLineWrapper {
	public static void main(String[] args) {
		boolean running = true;
		String command;
		while (running) {
			command = Args.ask("Command").toLowerCase();
			switch (command) {
				case"stop": running=false; break;
				case"run":
				case"main":
					System.out.println("running MainMethods...");
					MainMethods.main(args); break;
			}
		}
		System.out.println("stopping...");
	}
}