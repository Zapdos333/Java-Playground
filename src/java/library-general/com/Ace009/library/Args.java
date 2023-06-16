package com.Ace009.library;

import java.util.Scanner;

/**
 * class to asks console input for arguments
 * @author Ace009
 * @see com.Ace009.library.Args#Args(Args.OutputType, String...)  constructor(String type, String...args)
 */
public class Args {
	/** copy of constructors {@code type} */
	public final OutputType createdType;
	/** copy of constructors {@code args} */
	public final String[] SArgs;
	/** {@code int} output array */
	public int[] outputInt;
	/** {@code long} output array */
	public long[] outputLong;
	/** {@code double} output array */
	public double[] outputDouble;
	/** {@code float} output array */
	public float[] outputFloat;
	/** {@code String} output array, always filled */
	public final String[] output;
	/**
	 * {@code enum} declaration for Output Types,
	 * possible values are: String, Int, Long, Double, Float
	 */
	public static enum OutputType {
		/** output type string */
		String,
		/** output type integer */
		Int,
		/** output type long */
		Long,
		/** output type double */
		Double,
		/** output type float */
		Float;
	}
	/**
	 * prints out the term+":" and
	 * returns the user input up to the next enter
	 * <p> uses a {@link Scanner} of {@link System#in} 
	 * and returns {@link Scanner#nextLine()}
	 * @param term the thing to ask
	 * @return the user input
	 */
	@SuppressWarnings("resource") //never close System.in
	public static String ask(String term){
		term = term==null ? "" : term;
		Scanner scanner = new Scanner(System.in);
		System.out.printf("%s:", term);
		return scanner.nextLine();
	}
	/**
	 * suspends the current thread with a message in {@link System#out}
	 * until {@link System#in} gets a {@link Scanner#nextLine()}
	 * @param term suspension message
	 */
	@SuppressWarnings("resource") //never close System.in
	public static void suspend(String term){
		term = term==null ? "" : term;
		Scanner scanner = new Scanner(System.in);
		if (term != "") System.out.printf("%s .Suspending, \nPress any key to continue...", term);
		else System.out.print("Suspending, \nPress any key to continue...");
		scanner.nextLine();
	}
	/**
	 * constructs an {@code Args} object, which contains the in {@code @see} mentioned properties,
	 * which are filled by printing {@code args[i]} and storing the response in {@code output[i]}
	 * and if the corresponding type is given, parse it into one of the {@code number[]}
	 * @param type {@code String} stating the type of output
	 * @param args {@code String[]} with the arguments names
	 * @see #output {@code output} String[]
	 * @see #outputInt {@code outputInt} int[]
	 * @see #outputLong {@code outputLong} long[]
	 * @see #outputFloat {@code outputFloat} float[]
	 * @see #outputDouble {@code outputDouble} double[]
	 * @see #SArgs {@code SArgs} String[] (copy of {@code args})
	 * @see #createdType {@code createdType} String (copy of {@code type})
	 */
	public Args(OutputType type, String...args) {
		SArgs = args;
		createdType = type;
		output = new String[args.length];
		for (int i : Range.ListRange(args.length)) {
			output[i] = ask(args[i]);
		}
		this.parseAsType(createdType);
	}
	/**
	 * parses {@code output[]} as {@code type} number into the corresponding {@code outputnumber[]}
	 * @param type {@code String} number type
	 */
	public void parseAsType(OutputType type) {
		switch (type) {
			case Int: outputInt = new int[output.length]; break;
			case Long: outputLong = new long[output.length]; break;
			case Float: outputFloat = new float[output.length]; break;
			case Double: outputDouble = new double[output.length]; break;
			case String: break;
		}
		for (int i : Range.ListRange(output.length)) {
			switch (type) {
				case Int:
					if (output[i]=="") {outputInt[i] = 0; break;}
					outputInt[i] = Integer.parseInt(output[i]); break;
				case Long:
					if (output[i]=="") {outputLong[i] = 0; break;}
					outputLong[i] = Long.parseLong(output[i]); break;
				case Float:
					if (output[i]=="") {outputFloat[i] = 0; break;}
					outputFloat[i] = Float.parseFloat(output[i]); break;
				case Double:
					if (output[i]=="") {outputDouble[i] = 0; break;}
					outputDouble[i] = Double.parseDouble(output[i]); break;
				case String: break;
			}
		}
	}
	/**
	 * parses {@code output[]} as {@code long} number into {@code outputLong[]}
	 * filling any empty values with {@code defaults[]}
	 * <p> (assumes that output[] can be parsed as {@code long})
	 * @throws NumberFormatException if {@code output[]} is not parsable
	 * @param defaults the array to get the defaults from
	 */
	public void parseWithDefaults(long[] defaults) {
		assert defaults.length >= output.length;
		outputLong = new long[output.length];
		for (int i : Range.ListRange(output.length)) {
			if (output[i]=="") outputLong[i] = defaults[i];
			else outputLong[i] = Long.parseLong(output[i]);
		}
	}
	/**
	 * parses {@code output[]} as {@code int} number into {@code outputInt[]}
	 * filling any empty values with {@code defaults[]}
	 * <p> (assumes that output[] can be parsed as {@code int})
	 * @throws NumberFormatException if {@code output[]} is not parsable
	 * @param defaults the array to get the defaults from
	 */
	public void parseWithDefaults(int[] defaults) {
		assert defaults.length >= output.length;
		outputInt = new int[output.length];
		for (int i : Range.ListRange(output.length)) {
			if (output[i]=="") outputInt[i] = defaults[i];
			else outputInt[i] = Integer.parseInt(output[i]);
		}
	}
	/**
	 * parses {@code output[]} as {@code double} number into {@code outputDouble[]}
	 * filling any empty values with {@code defaults[]}
	 * <p> (assumes that output[] can be parsed as {@code double})
	 * @throws NumberFormatException if {@code output[]} is not parsable
	 * @param defaults the array to get the defaults from
	 */
	public void parseWithDefaults(double[] defaults) {
		assert defaults.length >= output.length;
		outputDouble = new double[output.length];
		for (int i : Range.ListRange(output.length)) {
			if (output[i]=="") outputDouble[i] = defaults[i];
			else outputDouble[i] = Double.parseDouble(output[i]);
		}
	}
	/**
	 * parses {@code output[]} as {@code float} number into {@code outputFloat[]}
	 * filling any empty values with {@code defaults[]}
	 * <p> (assumes that output[] can be parsed as {@code float})
	 * @throws NumberFormatException if {@code output[]} is not parsable
	 * @param defaults the array to get the defaults from
	 */
	public void parseWithDefaults(float[] defaults) {
		assert defaults.length >= output.length;
		outputFloat = new float[output.length];
		for (int i : Range.ListRange(output.length)) {
			if (output[i]=="") outputFloat[i] = defaults[i];
			else outputFloat[i] = Float.parseFloat(output[i]);
		}
	}
	/**
	 * fills any empty values in {@code output[]}
	 * with the corresponding value from {@code defaults[]}
	 * @param defaults the array to get the defaults from
	 */
	public void parseWithDefaults(String[] defaults) {
		assert defaults.length >= output.length;
		for (int i : Range.ListRange(output.length)) {
			if (output[i]=="") {
				output[i] = defaults[i];
			}
		}
	}
}
