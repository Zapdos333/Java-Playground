package com.Ace009.library;

import java.util.Random;

/**
 * a random number generator,
 * that {@code extends Random},
 * has some additional instance methods
 * @see Random
 * @author Ace009
 */
public class RNG extends Random{
	/**
	 * creates a new instance of {@code RNG}
	 * based on {@code Random}
	 * but with the additional instance methods
	 * @see Random
	 */
	public RNG() {
		super();
	}
	/**
	 * returns a random integer, based on {@code Random}
	 * @param min {@code int} minimum value (inclusive)
	 * @param max {@code int} maximum value (inclusive)
	 * @return random integer
	 * @see Random#nextInt(int)
	 */
	public int limitedIntRandom(int min, int max) {
		return this.nextInt(max - min + 1) + min;
	}
	/**
	 * returns a random double, based on {@code Random}
	 * @param min {@code double} minimum value (inclusive)
	 * @param max {@code double} minimum value (exclusive)
	 * @return random double
	 * @see Random#nextDouble()
	 */
	public double limitedDoubleRandom(double min, double max) {
		return this.nextDouble()*((max)-min)+min;
	}
	/**
	 * returns a random float, based on {@code Random}
	 * @param min {@code float} minimum value (inclusive)
	 * @param max {@code float} minimum value (exclusive)
	 * @return random float
	 * @see Random#nextFloat()
	 */
	public float limitedFloatRandom(float min, float max) {
		return this.nextFloat()*((max)-min)+min;
	}
	/**
	 * creates a random long by calling {@link Random#nextDouble()},
	 * and the doing the old {@code (random)*(max-min)+min},
	 * unknown reliablity due to this cheesing/attempting
	 * @param min {@code long} minimum value (inclusive)
	 * @param max {@code long} maximum value (exclusive)
	 * @return 'random' long
	 */
	public long limitedLongRandom(long min, long max) {
		//yes were cheesing this with a double {@code 0-1}, because Random.nextLong() is not limitable
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
		for (int i = 0; i<length; i++) {
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
