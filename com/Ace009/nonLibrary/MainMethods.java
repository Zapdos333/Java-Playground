package com.Ace009.nonLibrary;

import com.Ace009.library.*;
//import com.Ace009.library.CClass.*;
import com.Ace009.library.CoordinateSystem.*;

import java.util.ArrayList;

/**
 * A class containing all of {@code com.Ace009.library} {@code main}-methods
 * @author Ace009
 */
public class MainMethods {
	/**
	 * {@code Range}s main method:
	 * <p>
	 * prints the iterator created by 
	 * {@link com.Ace009.library.Range#arrayRange(int,int,int)}
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
	public static void main(String[] args) {
		if (args.length==0) {
			int argNum = new Args("int", "argument Amount").outputInt[0];
			String[] argNames=new String[argNum+1];
			for (int i : Range.arrayRange(argNum+1)) {
				if (i==0) {argNames[0]="type"; continue;}
				argNames[i] = Integer.toString(i);
			}
			args = new Args("String", argNames).output;
		}
		assert args.length>0;
		final String[] Nargs = new String[args.length-1];
		String type = "";
		for (int i : new Range(args.length)) {
			if (i==0) {type = args[0].toLowerCase();continue;}
			Nargs[i-1] = args[i];
		}
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
