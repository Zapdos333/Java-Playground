package com.Ace009.nonLibrary;

import com.Ace009.library.*;
import com.Ace009.library.CClass.*;
import com.Ace009.library.CoordinateSystem.*;

import java.util.ArrayList;

public class MainMethods {
	public static void rangeMain(String[] args) {
		Args arguments = new Args("int", "Stop","Start","Steps");
		int start = arguments.outputInt[1];
		int stop = arguments.outputInt[0];
		int steps = arguments.outputInt[2];
		for (int i : Range.arrayRange(start, stop, steps)) {
			System.out.println(i);
		}
	}
	/**
	* uses <code>args</code> as values to run specified iterations of random number generation.
	* @param args String Array:
	* @param args [0] iteration count (default:10)
	* @param args [1] highest number (default:10)
	* @param args [2] custom start seed length (default:15)
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
}
