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
	 * "triangle":runs the test method for the triangle class <br>
	 * "cobject":runs a test for the CObject class, takes a custom Object and analyses it <br>
	 * "wrapper":runs an internal instance of this in a try-catch block <br>
	 * </code>
	 */
	private static final String[] help = new String[]{
		"\nAvailable commands(all commands are internally converted to lower case):",
		"\"stop\"/\"break\"/\"end\"/\"exit\": stops/ends the main method",
		"\"help\": prints this message",
		"\"circle\":runs a test main method for the CoordinateSystems Circle class",
		"\"rng\":runs a test for the custom RNG class, arguments are taken via Args",
		"\"range\":runs a test for the Range Class, printing all the steps",
		"\"cipher\":runs a command-line interface for the CaesarCipher class, can encode/decode/crack",
		"\"fraction\":uses Args to create and calculate with a user given fraction",
		"\"debug\":a debug method only used for debugging, current contetn in source-code",
		"\"triangle\":runs the test method for the triangle class",
		"\"cobject\":runs a test for the CObject class, takes a custom Object and analyses it",
		"\"wrapper\":runs an internal instance of this in a try-catch block"
	};
	private static Log.Level LogInfo = Log.Level.INFO;
	private static Log.Level LogDebug = Log.Level.DEBUG;
	private static Log.Level LogWarning = Log.Level.WARNING;
	private static Log.Level LogError = Log.Level.ERROR;
	/**
	 * system default main method, uses {@link Args#ask(String)} to run specifed commands
	 * @param args default system arguments, will de used when calling a method that supports {@code String[] args}
	 */
	public static void main(String[] args) {
		if (args.length>0) switch (args[0].toLowerCase()) {
			case "debug": Log.setOutputLevel(LogDebug); break;
			case "info": Log.setOutputLevel(LogInfo); break;
			case "warning": Log.setOutputLevel(LogWarning); break;
			case "error": Log.setOutputLevel(Log.Level.ERROR); break;
		}
		Log.out(LogDebug,String.format("Log level set to: %s",Log.getOutputLevel()));
		String command;
		Log.out(Log.Level.INFO,"\nRunning CommandLineWrapper:");
		while (true) {
			command = Args.ask("\nCommand").toLowerCase();
			switch (command) {
				case"stop":	case"break": case"end": case "exit":
					Log.out(LogInfo,"stopping...");
					return;
				case"help":
					for (String line : help) {
						Log.out(LogInfo,line);
					}
					break;
				case "circle":
					Log.out(LogInfo,"Running MainMethod Circle...");
					MainMethods.CircleMain(); break;
				case "rng":
					Log.out(LogInfo,"Running MainMethod RNG...");
					MainMethods.RNGMain(); break;
				case "range":
					Log.out(LogInfo,"Running MainMethod Range...");
					MainMethods.rangeMain(); break;
				case "cipher":
					Log.out(LogInfo,"Running MainMethod Cipher...");
					MainMethods.CipherTest(); break;
				case "fraction":
					Log.out(LogInfo,"Running MainMethod Fraction...");
					MainMethods.FractionTest(); break;
				case "triangle":
                    Log.out(LogInfo,"Running MainMethod Triangle...");
					MainMethods.TriangleTest();
					break;
				case "debug":
					Log.out(LogInfo,"Running Debug...");
					debug(); break;
				case"cobject":
					Log.out(LogInfo,"Running CObject Test...");
					CobjectTest(); break;
				case"wrapper":
					Log.out(LogInfo,"Starting wrapper...");
					try {
						main(args);
						Log.out(LogInfo,"\ninternal wrapper ended normally");
					} catch (Throwable e) {
						Log.out(LogError,"\ninternal wrapper ended with exception:\n%s\n\n", e.toString());
						for (StackTraceElement line : e.getStackTrace()) Log.out(LogError,line.toString());
					}
					break;
				default:
					Log.out(LogInfo,"No implemented command given, command: \"%s\"\n",command);
					Log.out(LogInfo,"use the command \"help\" to see available commands");
			}
			Log.out(LogInfo,"\nreturning to Wrapper...");
		}
	}
	/** debug Method, generally empty in releases */
	public static void debug() {
		// empty in release
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
		Log.out(LogInfo,"Getting test object...");
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
				Log.out(LogWarning,"No implemented Object Type given,"); return;
		}
		Map<String, Object> output = new HashMap<>();
		output = CObject.fieldEntriesMap(test);
		Log.out(LogInfo,"Debug: %s; %s\n", test, output);
		for (String E : CMap.print(output)) {
			Log.out(LogInfo,E);
		}
	}
}
