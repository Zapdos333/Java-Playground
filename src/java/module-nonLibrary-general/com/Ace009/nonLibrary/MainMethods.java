package com.Ace009.nonLibrary;

import com.Ace009.library.*;
//import com.Ace009.library.CClass.*;
import com.Ace009.library.CoordinateSystem.*;

import java.util.ArrayList;

/**
 * A class containing all of {@code com.Ace009.library} {@code main}-methods
 * <p>all methods get their necessary arguments using {@link Args}
 * @author Ace009
 * @see #main(String[])
 */
public class MainMethods {
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
		Args arguments = new Args("int", "Stop","Start","Steps");
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
		Args input = new Args("int","amount","max","Seed length");
		//insert defaults
		input.parseWithDefaults(new int[]{10,10,15});
		//set parameters
		int limit = input.outputInt[0];
		int max = input.outputInt[1];
		int seedLength = input.outputInt[2];
		//ask for type of number
		String type = new Args("String", "type of numbers").output[0].toLowerCase();
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
	 * @see com.Ace009.library.Circle
	 */
	public static void CircleMain() {
		Args input = new Args("double", "X","Y","Radius","Corners");
		input.parseWithDefaults(new double[] {5,5,5,10});
		System.out.println("Test program: ");
		double[] Nargs = input.outputDouble;
		int aC = (int)Math.floor(Nargs[3]);
		double aX= Nargs[0];
		double aY= Nargs[1];
		double aR= Nargs[2];
		Circle test = new Circle(new DoubleCoordinate(aX,aY),aR);
		ArrayList<DoubleCoordinate> result = test.constructPoly(aC);
		System.out.println("Circle: "+result.toString());
		System.out.println("Circluarity: "+test.circumferance()+"/"
		+DoubleCoordinate.totalDistance(result,true)+"="+Circle.getCircularity(result,aR));
	}
	/**
	 * launches a specified main method.
	 * <p> specification is possible through {@link Args} or {@code args}
	 * @param args {@code String[]}, irrelevant, arguments gathered using {@link Args}
	 * @param args [0] optional, main method name,
	 * skips specification launch of {@link Args}
	 * @see com.Ace009.library.Args
	 * @see #rangeMain()
	 * @see #RNGMain()
	 * @see #CircleMain()
	 */
	public static void main(String[] args) {
		String type = "";
		if (args.length==0) {
			type = new Args("String", "method").output[0].toLowerCase();
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
			default: System.out.println("No implemented type given. type: "+type);
		}
	}
}
