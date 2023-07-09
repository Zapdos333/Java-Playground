package com.Ace009.nonLibrary.debug;

import com.Ace009.library.*;
import com.Ace009.library.CClass.*;
import com.Ace009.library.CoordinateSystem.*;
import com.Ace009.library.Math.*;
import com.Ace009.nonLibrary.school.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class containing all {@code main}- or debug-methods
 * <p>all methods get their necessary arguments using {@link Args}
 * @author Ace009
 */
public class MainMethods {
	/** don't */
	private MainMethods() {};
	private static Log.Level LogInfo = Log.Level.INFO;
	private static Log.Level LogDebug = Log.Level.DEBUG;
	private static Log.Level LogWarning = Log.Level.WARNING;
	@SuppressWarnings("unused") //until used
	private static Log.Level LogError = Log.Level.ERROR;
	/**
	 * {@code Range}s main method:
	 * <p>
	 * prints the iterator created by 
	 * {@link Range#arrayRange(int,int,int)}
	 * in new lines
	 * @see com.Ace009.library.Range
	 */
	public static void rangeMain() {
		Args arguments = new Args(Args.OutputType.Int, "Stop","Start","Steps");
		arguments.parseWithDefaults(new int[]{100,0,1});
		int stop = arguments.outputInt[0];
		int start = arguments.outputInt[1];
		int steps = arguments.outputInt[2];
		int columnWidth = arguments.output[0].length();
		int[] array = Range.arrayRange(start, stop, steps);
		List<Integer> list = Range.ListRange(start, stop, steps);
		for (int i : Range.arrayRange( Math.max(array.length,list.size()) )) {
			Log.out(LogInfo,"%s|%s\n",
				CString.formatToLength(Integer.toString(array[i]),columnWidth),
				CString.formatToLength(list.get(i).toString(),columnWidth)
			);
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
		RNG<java.util.Random> testRng= RNG.newInstance();
		//ask for Args
		Args input = new Args(Args.OutputType.Int,"amount","min","max","Seed length");
		//insert defaults
		input.parseWithDefaults(new int[]{10,0,10,15});
		//set parameters
		int limit = input.outputInt[0];
		int min = input.outputInt[1];
		int max = input.outputInt[2];
		int seedLength = input.outputInt[3];
		//ask for type of number
		String type = new Args(Args.OutputType.String, "type of numbers").output[0].toLowerCase();
		//reroll for seed
		Log.out(LogInfo,"new Seed is: "+testRng.rerollRandom(seedLength));
		//output requested random numbers
		for (int i : Range.arrayRange(1,limit+1)) {
			switch (type) {
				case "long":
					Log.out(LogInfo,"Iteration: %d; RNG: %d\n",i,testRng.limitedLongRandom(min,max));
					break;
				case "double":
					Log.out(LogInfo,"Iteration: %d; RNG: %f\n",i,testRng.limitedDoubleRandom(min,max));
					break;
				case "float":
					Log.out(LogInfo,"Iteration: %d; RNG: %f\n",i,testRng.limitedFloatRandom(min,max));
					break;
				case "int":
				case "integer":
				default:
					Log.out(LogInfo,"Iteration: %d; RNG: %d\n",i,testRng.limitedIntRandom(min,max));
					break;
			}
		}
	}
	/**
	 * {@code Circle}s main method:
	 * <p>
	 * creates a test circle with the given parameters,
	 * prints out the list of coordinates, and the 'Circularity'
	 * @see com.Ace009.library.CoordinateSystem.Circle
	 */
	public static void CircleMain() {
		Args input = new Args(Args.OutputType.Double, "X","Y","Radius","Corners");
		input.parseWithDefaults(new double[] {5,5,5,10});
		Log.out(LogInfo,"Starting...");
		double[] Nargs = input.outputDouble;
		int aC = (int)Math.floor(Nargs[3]);
		double aX= Nargs[0];
		double aY= Nargs[1];
		double aR= Nargs[2];
		Circle test = new Circle(new NumberCoordinate<Double>(aX,aY),aR);
		//ArrayList, because AbstractCollection implements the readable .toString()
		// so we can use neither Collection nor List
		ArrayList<NumberCoordinate<Double>> result = new ArrayList<>(test.constructPoly(aC));
		Log.out(LogDebug,"Circle: %s",result.toString());
		Log.out(LogInfo,"Circularity: %f/%f=%f\n",test.circumferance(),NumberCoordinate.totalDistance(result,true),+Circle.getCircularity(result,aR));
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
	 * {@code CaesarCipher}s main method:
	 * <p>
	 * creates a test {@code CaesarCipher} with the given parameters,
	 * encodes or decodes the given string using {@code CaesarCipher#encode} or {@code CaesarCipher#decode},
	 * or runs {@code CaesarCipher#crack} on the String, completely ignoring the given key
	 * 
	 * @see com.Ace009.nonLibrary.school.CaesarCipher
	 */
	public static void CipherTest() {
		Args input = new Args(Args.OutputType.String,"cipher Key","mode (encode/decode/crack)","String");
		input.parseWithDefaults(new String[]{"a","encode","Test"});
		CaesarCipher cipher = new CaesarCipher(input.output[0].toCharArray()[0]);
		switch (input.output[1].toLowerCase()) {
			case "decode":
				Log.out(LogInfo,cipher.decode(input.output[2]));
				break;
			case "crack":
				String[] output = CaesarCipher.crack(input.output[2]);
				for (int i : Range.arrayRange(1,output.length)) {
					Log.out(LogInfo,"(%d/%c): %s\n",i,CaesarCipher.ALPHABET[i],output[i]);
				}
				break;
			case "encode":
			default:
				Log.out(LogInfo,cipher.encode(input.output[2]));
				break;
		}
	}
	/**
	 * {@code Fraction}s main method:
	 * <p>
	 * creates two test {@code Fraction} with the given parameters,
	 * adds, subtracts, multiplies, or divides the two, depending on the parameters
	 * and prints out the result
	 */
	public static void FractionTest(){
		int[] input = new Args(Args.OutputType.Int, "numerator","demoninator").outputInt;
		Fraction test = new Fraction(input[0],input[1]);
		Log.out(LogInfo,"Fraction is: %s\n",test.toString());
		Args operations = new Args(Args.OutputType.String, "operation","numerator","denominator");
		operations.parseWithDefaults(new String[]{"multiply","33","7"});
		String operation = operations.output[0].toLowerCase();
		Fraction T2 = new Fraction(Integer.parseInt(operations.output[1]),Integer.parseInt(operations.output[2]));
		Log.out(LogInfo,"Second fraction is: %s\n",T2.toString());
		switch (operation) {
			case "multiply":
				test=test.multiplyBy(T2);
				break;
			case "divide":
				test=test.divideBy(T2);
				break;
			case "add":
			case "addition":
				test=test.add(T2);
				break;
			case "subtract":
				test=test.subtract(T2);
				break;
			default: Log.out(LogWarning,"No implemented operation given. operation: "+operation);
		}
		Log.out(LogInfo,"Fraction is: %s\n",test.toString());
	}
	/**
     * {@link com.Ace009.library.CoordinateSystem.Triangle}s main method:
     * <p>
     * creates a test {@code Triangle} with the given parameters,
     * prints out the result
	 */
	public static void TriangleTest() {
		Map<String,String> t1_ = Args.createMap(9);
		Map<Triangle.ArgsType,Object> t2_ = new HashMap<>(Math.min(9,t1_.size()));
		for (Map.Entry<String,String> entry : t1_.entrySet()) {
			switch (entry.getKey().toLowerCase()) {
				case "pointa": t2_.put(Triangle.ArgsType.PointA, FractionCoordinate.parse(entry.getValue())); break;
				case "pointb": t2_.put(Triangle.ArgsType.PointB, FractionCoordinate.parse(entry.getValue())); break;
				case "pointc": t2_.put(Triangle.ArgsType.PointC, FractionCoordinate.parse(entry.getValue())); break;
				case "sidea": t2_.put(Triangle.ArgsType.SideA, Fraction.parse(entry.getValue())); break;
				case "sideb": t2_.put(Triangle.ArgsType.SideB, Fraction.parse(entry.getValue())); break;
				case "sidec": t2_.put(Triangle.ArgsType.SideC, Fraction.parse(entry.getValue())); break;
				case "alpha": t2_.put(Triangle.ArgsType.Alpha, Double.valueOf(entry.getValue())); break;
				case "beta": t2_.put(Triangle.ArgsType.Beta, Double.valueOf(entry.getValue())); break;
				case "gamma": t2_.put(Triangle.ArgsType.Gamma, Double.valueOf(entry.getValue())); break;
			}
		}
		for (String E : CMap.print(t1_)) {
			Log.out(LogDebug,E);
		}
		Triangle test = new Triangle(t2_);
		Log.out(LogInfo,test.toString());
	}
}
