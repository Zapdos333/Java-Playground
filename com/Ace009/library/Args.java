package com.Ace009.library;

//import com.Ace009.library.Range;
import java.util.Scanner;

/**
 * class to aks console input for arguments
 * @author Ace009
 * @see #Args(String, String...) constuctor(String type, String...args)
 */
public class Args {
	/** copy of {@code type} */
	String createdType;
	/** copy of {@code args} */
	String[] Sargs;
	/** {@code int} output array */
	int[] outputInt;
	/** {@code long} output array */
	long[] outputLong;
	/** {@code double} output array */
	double[] outputDouble;
	/** {@code float} output array */
	float[] outputFloat;
	/** {@code String} output array, always filled */
	String[] output;
	/**
	 * constructs an {@code Args} object, which contains the in {@code @see} mentioned properties,
	 * which are filled by printing {@code args[i]} and storing the response in {@code output[i]}
	 * and if the corresponding type is given, parse it into one of the {@code number[]}
	 * @param type {@code String} stating the type of output
	 * @param args {@code String[]} with the arguments names
	 * @return {@code Args} with output arrays filled with the (if number: parsed) console input
	 * @see #output {@code output} String[]
	 * @see #outputInt {@code outputInt} int[]
	 * @see #outputLong {@code outputLong} long[]
	 * @see #outputFloat {@code outputFloat} float[]
	 * @see #outputDouble {@code outputDouble} double[]
	 * @see #Sargs {@code Sargs} String[] (copy of {@code args})
	 * @see #createdType {@code createdType} String (copy of {@code type})
	 */
	@SuppressWarnings("resource")
	public Args(String type, String...args) {
		Sargs = args;
		createdType = type;
		output = new String[args.length];
		switch (type.toLowerCase()) {
				case "int": outputInt = new int[args.length]; break;
				case "long": outputLong = new long[args.length]; break;
				case "float": outputFloat = new float[args.length]; break;
				case "double": outputDouble = new double[args.length]; break;
		}
		Scanner scanner = new Scanner(System.in);
		String input;
		for (int i : new Range(args.length)) {
			input = "";
			System.out.print(args[i] + ":");
			input = scanner.nextLine();
			output[i] = input;
			switch (type.toLowerCase()) {
				case "int": outputInt[i] = Integer.parseInt(input); break;
				case "long": outputLong[i] = Long.parseLong(input); break;
				case "float": outputFloat[i] = Float.parseFloat(input); break;
				case "double": outputDouble[i] = Double.parseDouble(input); break;
			}
		}
	}
	/**
	 * parses {@code output[]} as {@code type} number into the corresponding {@code outputnumber[]}
	 * @param type {@code String} number type
	 */
	public void parseAsType(String type) {
		if (type==createdType) {return;}
		switch (type.toLowerCase()) {
			case "int": outputInt = new int[output.length]; break;
			case "long": outputLong = new long[output.length]; break;
			case "float": outputFloat = new float[output.length]; break;
			case "double": outputDouble = new double[output.length]; break;
		}
		for (int i : new Range(output.length)) {
			switch (type.toLowerCase()) {
				case "int": outputInt[i] = Integer.parseInt(output[i]); break;
				case "long": outputLong[i] = Long.parseLong(output[i]); break;
				case "float": outputFloat[i] = Float.parseFloat(output[i]); break;
				case "double": outputDouble[i] = Double.parseDouble(output[i]); break;
			}
		}
	}
}
