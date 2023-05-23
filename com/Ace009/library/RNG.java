package com.Ace009.library;

import java.util.Random;
import com.Ace009.library.Range;

/**
 * a random number generator,
 * that <code>extends Random</code>,
 * has some additional instance methods
 * @see Random
 * @author Ace009
 */
public class RNG extends Random{
	/**
	 * uses <code>args</code> as values to run specified iterations of random number generation.
	 * @param args String Array:
	 * @param args [0] iteration count (default:10)
	 * @param args [1] highest number (default:10)
	 * @param args [2] custom start seed length (default:15)
	 */
	public static void main(String[] args) {
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
		for (int i : new Range(1,limit+1)) {
			System.out.println("Iteration: "+i);
			System.out.println("Result: "+testRng.limitedFloatRandom(0,max));
		}
	}
	/**
	 * returns a random integer, based on <code>Random</code>
	 * @param min
	 * @param max
	 * @return random integer
	 * @see Random#nextInt(int)
	 */
	public int limitedIntRandom(int min, int max) {
		return this.nextInt(max - min + 1) + min;
	}
	/**
	 * returns a random double, based on <code>Random</code>
	 * @param min
	 * @param max
	 * @return random double
	 * @see Random#nextDouble()
	 */
	public double limitedDoubleRandom(double min, double max) {
		return this.nextDouble()*((max)-min)+min;
	}
	/**
	 * returns a random float, based on <code>Random</code>
	 * @param min
	 * @param max
	 * @return random float
	 * @see Random#nextFloat()
	 */
	public float limitedFloatRandom(float min, float max) {
		return this.nextFloat()*((max)-min)+min;
	}
	/**
	 * creates a random long by calling <code>nextDouble()</code>,
	 * and the doing the old {@code (random)*(max-min)+min},
	 * unreliable due to this attempting
	 * @param min
	 * @param max
	 * @return 'random' long
	 */
	public long limitedLongRandom(long min, long max) {
		//yes were cheesing this with a double 0-1, because Random.nextLong() is not limitable
		double preRN=this.nextDouble();
		long mult = (max-min)+min;
		long rn = (long)(preRN*mult);
		return rn;
	}
	/**
	 * creates a random long by appending random integers to a {@code StringBuilder},
	 * setting the sign with a {@code Random.nextBoolean()},
	 * and then parsing it as {@code long}
	 * @param length integer length
	 * @return 'random' {@code long}
	 */
	public long rollLong (int length) { //Long max=9.223.372.036.854.775.808
		StringBuilder outputS=new StringBuilder();
		Long output;
		if (length>18) {length=18;}
		for (int i : Range.arrayRange(length)) {
			outputS.append(limitedIntRandom(0,9));
		}
		if (this.nextBoolean()) {
			outputS.insert(0,"+");
		} else {
			outputS.insert(0,"-");
		}
		output=Long.parseLong(outputS.toString());
		return output;
	}
	/**
	 * sets {@code Randoms} seed to the result of {@code rollLong(length)}
	 * @param length integer length
	 * @return the new seed
	 * @see #rollLong(int)
	 */
	public long rerollRandom(int length) {
		long seed=this.rollLong(length);
		this.setSeed(seed);
		return seed;
	}
}
