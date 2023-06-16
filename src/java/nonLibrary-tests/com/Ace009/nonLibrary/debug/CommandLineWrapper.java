package com.Ace009.nonLibrary.debug;

import java.util.Map;
import java.util.stream.Stream;
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
	 * {@code String[]} containing the help message
	 * <p>help-command<code>
	 * Available commands(all commands are internally converted to lower case): <br>
	 * "stop"/"break": stops/ends the main method <br>
	 * "help": prints this message <br>
	 * "circle":runs a test main method for the CoordinateSystems Circle class <br>
	 * "rng":runs a test for the custom RNG class, arguments are taken via Args <br>
	 * "range":runs a test for the Range Class, printing all the steps <br>
	 * "cipher":runs a command-line interface for the CaesarCipher class, can encode/decode/crack <br>
	 * "fraction":uses Args to create and calculate with a user given fraction <br>
	 * "debug":a debug method only used for debugging, current contetn in source-code <br>
	 * "cobject":runs a test for the CObject class, takes a custom Object and analyses it <br>
	 * "wrapper":runs an internal instance of this in a try-catch block <br>
	 * </code>
	 */
	private static final String[] help = new String[]{
		"\nAvailable commands(all commands are internally converted to lower case):",
		"\"stop\"/\"break\": stops/ends the main method",
		"\"help\": prints this message",
		"\"circle\":runs a test main method for the CoordinateSystems Circle class",
		"\"rng\":runs a test for the custom RNG class, arguments are taken via Args",
		"\"range\":runs a test for the Range Class, printing all the steps",
		"\"cipher\":runs a command-line interface for the CaesarCipher class, can encode/decode/crack",
		"\"fraction\":uses Args to create and calculate with a user given fraction",
		"\"debug\":a debug method only used for debugging, current contetn in source-code",
		"\"cobject\":runs a test for the CObject class, takes a custom Object and analyses it",
		"\"wrapper\":runs an internal instance of this in a try-catch block"
	};
	/**
	 * system default main method, uses {@link Args#ask(String)} to run specifed commands
	 * @param args default system arguments, will de used when calling a method that supports {@code String[] args}
	 */
	public static void main(String[] args) {
		String command;
		System.out.println("\nRunning CommandLineWrapper:");
		while (true) {
			command = Args.ask("\nCommand").toLowerCase();
			switch (command) {
				case"stop":	case"break": case"end":
					System.out.println("stopping...");
					return;
				case"help":
					for (String line : help) {
						System.out.println(line);
					}
					break;
				case "circle":
					System.out.println("Running MainMethod Circle...");
					MainMethods.CircleMain(); break;
				case "rng":
					System.out.println("Running MainMethod RNG...");
					MainMethods.RNGMain(); break;
				case "range":
					System.out.println("Running MainMethod Range...");
					MainMethods.rangeMain(); break;
				case "cipher":
					System.out.println("Running MainMethod Cipher...");
					MainMethods.CipherTest(); break;
				case "fraction":
					System.out.println("Running MainMethod Fraction...");
					MainMethods.FractionTest(); break;
				case "debug":
					System.out.println("Running Debug...");
					debug(); break;
				case"cobject":
					System.out.println("Running CObject Test...");
					CobjectTest(); break;
				case"wrapper":
					System.out.println("Starting wrapper...");
					try {
						main(args);
						System.out.println("\ninternal wrapper ended normally");
					} catch (Throwable e) {
						System.out.printf("\ninternal wrapper ended with exception:\n%s\n\n", e.toString());
						Stream.of(e.getStackTrace()).map(line->line.toString()).forEach(System.out::println);
					}
					break;
				default:
					System.out.printf("No implemented command given, command: \"%s\"\n",command);
					System.out.println("use the command \"help\" to see available commands");
			}
			System.out.println("\nreturning to Wrapper...");
		}
	}
	/** debug Method, generally empty in releases */
	public static void debug() {
		//empty
	}
	/**
	 * {@code CObject} Test method
	 * <p>
	 * creates a custom test {@code Object} with given parameters,
	 * (current options are {@link Args} and {@link Fraction} )
	 * 
	 * @see com.Ace009.library.CClass.CObject
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
		try { output = CObject.fieldEntriesMap(test); }
		catch (Exception e) { e.printStackTrace(); }
		System.out.printf("Debug: %s; %s\n", test, output);
		for (String E : CMap.print(output)) {
			System.out.print(E);
		}
	}
}