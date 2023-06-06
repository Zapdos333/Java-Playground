package com.Ace009.nonLibrary;

import com.Ace009.library.*;
import com.Ace009.library.Args.OutputType;
import com.Ace009.library.CClass.*;
import com.Ace009.library.CoordinateSystem.*;
import com.Ace009.nonLibrary.school.*;

import java.util.ArrayList;

/**
 * A class containing all {@code main}- or debug-methods
 * <p>all methods get their necessary arguments using {@link Args}
 * @author Ace009
 * @see #main(String[])
 */
public class MainMethods {
	/** don't */
	private MainMethods() {}
	/**
	 * {@code Range}s main method:
	 * <p>
	 * prints the iterator created by 
	 * {@link Range#arrayRange(int,int,int)}
	 * in new lines
	 * 
	 * @see com.Ace009.library.Range
	 */
	public static void rangeMain() {
		Args arguments = new Args(OutputType.Int, "Stop","Start","Steps");
		int stop = arguments.outputInt[0];
		int start = arguments.outputInt[1];
		int steps = arguments.outputInt[2];
		for (int i : Range.arrayRange(start, stop, steps)) {
			System.out.println(i);
		}
	}
	/**
	 * {@code RNG}s main method:
	 * <p>
	 * runs specified iterations of random number generation.
	 * 
	 * @see com.Ace009.library.RNG
	 */
	public static void RNGMain() {
		//create new RNG
		RNG testRng= new RNG();
		//ask for Args
		Args input = new Args(OutputType.Int,"amount","max","Seed length");
		//insert defaults
		input.parseWithDefaults(new int[]{10,10,15});
		//set parameters
		int limit = input.outputInt[0];
		int max = input.outputInt[1];
		int seedLength = input.outputInt[2];
		//ask for type of number
		String type = new Args(OutputType.String, "type of numbers").output[0].toLowerCase();
		//reroll for seed
		System.out.println("new Seed is: "+testRng.rerollRandom(seedLength));
		//output requested random numbers
		for (int i = 1; i <= limit; i++) {
			switch (type) {
				case "long":
					System.out.printf("Iteration: %d; RNG: %d",i,testRng.limitedLongRandom(0,max));
					break;
				case "double":
					System.out.printf("Iteration: %d; RNG: %f",i,testRng.limitedDoubleRandom(0,max));
					break;
				case "float":
					System.out.printf("Iteration: %d; RNG: %f",i,testRng.limitedFloatRandom(0,max));
					break;
				case "int":
				case "integer":
				default:
					System.out.printf("Iteration: %d; RNG: %d",i,testRng.limitedIntRandom(0,max));
					break;
			}
		}
	}
	/**
	 * {@code Circle}s main method:
	 * <p>
	 * creates a test circle with the given parameters,
	 * prints out the list of coordinates, and the 'Circularity'
	 * 
	 * @see com.Ace009.library.CoordinateSystem.Circle
	 */
	public static void CircleMain() {
		Args input = new Args(OutputType.Double, "X","Y","Radius","Corners");
		input.parseWithDefaults(new double[] {5,5,5,10});
		System.out.println("Test program: ");
		double[] Nargs = input.outputDouble;
		int aC = (int)Math.floor(Nargs[3]);
		double aX= Nargs[0];
		double aY= Nargs[1];
		double aR= Nargs[2];
		Circle test = new Circle(new Coordinate(aX,aY),aR);
		ArrayList<Coordinate> result = test.constructPoly(aC);
		System.out.println("Circle: "+result.toString());
		System.out.println("Circluarity: "+test.circumferance()+"/"
		+Coordinate.totalDistance(result,true)+"="+Circle.getCircularity(result,aR));
	}
	/* ** object parse test **
	 * <code>
	 * int amount = new Args("int", "Amount of Properties").outputInt[0];
		String[] askKey = new String[amount];
		for (int i : Range.arrayRange(amount)) {
			askKey[i] = i+". Key";
		}
		String[] askVal = new String[amount];
		for (int i : Range.arrayRange(amount)) {
			askVal[i] = i+". Value";
		}
		String[] keys = new Args("String", askKey).output;
		String[] values = new Args("String", askVal).output;
	 * </code>
	 */
	/**
	 * {@code CObject}s main method:
	 * <p>
	 * creates a test {@code Args} with given parameters,
	 * prints out its properties using {@link CObject#entries(Args)}
	 * 
	 * @see com.Ace009.library.CClass.CObject
	 */
	public static void CobjectTest() {
		int amount = new Args(OutputType.Int, "Amount of Entries").outputInt[0];
		String[] askKey = new String[amount];
		for (int i : Range.arrayRange(amount)) {
			askKey[i] = i+". Entry";
		}
		Args test = new Args(OutputType.String, askKey);
		Object[][] result = new Object[amount][2];
		try {
			result = CObject.entries(test);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		for (int i : Range.arrayRange(result.length)) {
            System.out.printf("Entry: %d, Key: %s, Value: %s\n",i,result[i][0],result[i][1]);
		}
	}
	/**
	 * {@code CaesarCipher}s main method:
	 * <p>
	 * creates a test {@code CaesarCipher} with the given parameters,
	 * encodes or decodes the given string using {@code CaesarCipher#encode} or {@code CaesarCipher#decode},
	 * or runs {@code CaesarCipher#crack} on the String, completely ignoring the given key
	 * 
	 * @see com.Ace009.nonLibrary.school.CaesarCipher
	 */
	public static void CipherTest() {
		Args input = new Args(OutputType.String,"cipher Key","mode (encode/decode/crack)","String");
		input.parseWithDefaults(new String[]{"a","encode","Test"});
		CaesarCipher cipher = new CaesarCipher(input.output[0].toCharArray()[0]);
		switch (input.output[1].toLowerCase()) {
			case "decode":
				System.out.println(cipher.decode(input.output[2]));
				break;
			case "crack":
				String[] output = CaesarCipher.crack(input.output[2]);
				for (int i : Range.arrayRange(output.length)) {
					System.out.printf("%d: %s\n",i,output[i]);
				}
				break;
			case "encode":
			default:
				System.out.println(cipher.encode(input.output[2]));
				break;
		}
	}
	/**
	 * launches a specified main method.
	 * <p> specification is possible through {@link Args} or {@code args}
	 * @param args {@code String[]}, irrelevant, arguments gathered using {@link Args}
	 * <p>if provided: [0] optional, main method name,
	 * skips specification launch of {@link Args}
	 * @see com.Ace009.library.Args
	 * @see #rangeMain()
	 * @see #RNGMain()
	 * @see #CircleMain()
	 * @see #CobjectTest()
	 */
	public static void main(String[] args) {
		String type = "";
		if (args.length==0) {
			type = new Args(OutputType.String, "method").output[0].toLowerCase();
		} else { type = args[0].toLowerCase(); }
		switch (type) {
			case "circle":
			case "circlemain":
				CircleMain();
				break;
			case "rng":
			case "rngmain":
				RNGMain();
				break;
			case "range":
			case "rangemain":
				rangeMain();
				break;
			case "cobject":
			case "cobjecttest":
				CobjectTest();
				break;
			case "cipher":
			case "ciphertest":
				CipherTest();
				break;
			default: System.out.println("No implemented type given. type: "+type);
		}
	}
}
