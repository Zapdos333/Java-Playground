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
	 * @param args is irrelevant,
	 * uses {@link com.Ace009.library.Args}
	 * 
	 * @see com.Ace009.library.Range
	 */
	public static void rangeMain(String[] args) {
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
	 * uses <code>args</code> as values to run specified iterations of random number generation.
	 * @param args String Array:
	 * @param args [0] iteration count (default:10)
	 * @param args [1] highest number (default:10)
	 * @param args [2] custom start seed length (default:15)
	 * 
	 * @see com.Ace009.library.RNG
	 */
	public static void RNGMain(String[] args) {
		RNG testRng= new RNG();
		int limit;
		int max;
		int seedLength;
		//checking for custom values, else use defaults
		if (args.length < 1) {
			limit=10;
		} else {
			limit=Integer.parseInt(args[0]);
			System.out.println("Set limit to custom: "+limit);
		} if (args.length < 2) {
			max=10;
		} else {
			max=Integer.parseInt(args[1]);
			System.out.println("Set max to custom: "+max);
		} if (args.length < 3) {
			seedLength=15;
		} else {
			seedLength=Integer.parseInt(args[2]);
			System.out.println("Set seedLength to custom: "+seedLength);
		}
		//reroll for seed
		System.out.println("new Seed is: "+testRng.rerollRandom(seedLength));
		//output requested random numbers
		for (int i = 1; i <= limit; i++) {
			System.out.println("Iteration: "+i);
			System.out.println("Result: "+testRng.limitedFloatRandom(0,max));
		}
	}
	/**
	 * {@code RNG}s main method:
	 * <p>
	 * 
	 * @param args String Array:
	 * @param args [0] Corners
	 */
	public static void CircleMain(String[] args) {
		System.out.println("Test program: ");
		System.out.println("1: Corners");
		int aC= Integer.parseInt(args[0]);
		double aX= 5;
		double aY= 5;
		double aR= 5;
		Circle test = new Circle(new DoubleCoordinate(aX,aY),aR);
		System.out.print("CenterX="+test.center.x);
		System.out.print(" CenterY="+test.center.y);
		System.out.println(" Radius="+test.radius);
		ArrayList<DoubleCoordinate> result = test.constructPoly(aC);
		System.out.println("Circle: "+result.toString());
		System.out.println("Circluarity: "+test.circumferance()+"/"+DoubleCoordinate.totalDistance(result,true)+"="+Circle.getCircularity(result,aR));
		System.out.println("Corners: "+result.size());
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
				CircleMain(Nargs);
				break;
			case "rng":
			case "rngmain":
				RNGMain(Nargs);
				break;
			case "range":
			case "rangemain":
				rangeMain(Nargs);
				break;
			default: System.out.println("No implemented type given. type: "+type);
		}
	}
}
