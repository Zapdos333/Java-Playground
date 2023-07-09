package com.Ace009.library;

import java.security.SecureRandom;
import java.util.Random;

/**
 * a random number generator,
 * that wraps a subclass of {@code Random} (or Random itself),
 * provides additional instance methods for generating random numbers
 * @author Ace009
 */
public class RNG<T extends Random> {
	/**
	 * creates a new instance of {@code RNG} from a {@link Random#Random() new Random()}
	 * @return the new instance of {@code RNG}
	 */
	public static RNG<Random> newInstance() { return new RNG<Random>(new Random()); }
	/**
	 * creates a new instance of {@code RNG} from a {@link Random#Random(long) new Random(long)}
	 * @param seed the seed to start the instance of {@code Random} with
	 * @return the new instance of {@code RNG}
	 */
	public static RNG<Random> newInstance(long seed) { return new RNG<Random>(new Random(seed)); }
	/**
	 * creates a new instance of {@code RNG} from {@link RNG#newInstance(long)},
	 * with the seed being {@link RNG#rollLong(int) newInstance().rollLong(seedLength)}
	 * @param seedLength the length of the generated seed
	 * @return the new instance of {@code RNG}
	 */
	public static RNG<Random> newInstance(int seedLength) {
		long seed = newInstance().rollLong(seedLength);
		return newInstance(seed);
	}
	/**
	 * creates a new instance of {@code RNG} from a {@link SecureRandom#SecureRandom() new SecureRandom()}
	 * @return the new instance of {@code RNG}
	 */
	public static RNG<SecureRandom> newSecureInstance() { return new RNG<SecureRandom>(new SecureRandom()); }
	/**
	 * creates a new instance of {@code RNG} from a {@link SecureRandom#SecureRandom(byte[]) new SecureRandom(byte[])},
	 * gets the {@code byte[]} from {@link Random#nextBytes(byte[])}
	 * @param seedLength the length of {@code byte[]}
	 * @return the new instance of {@code RNG}
	 */
	public static RNG<SecureRandom> newSecureInstance(int seedLength) {
		byte[] seed = new byte[seedLength];
		new Random().nextBytes(seed);
		return new RNG<SecureRandom>(new SecureRandom(seed));
	}
	/**
	 * public reference to protected constructor
	 * @param <T> the type of the {@code Random} subclass
	 * @param random the {@code ? extends Random} to wrap
	 * @return a new instance of {@code RNG} with {@code random} as {@link #gen}
	 */
	public static <T extends Random> RNG<T> wrap(T random) { return new RNG<T>(random); }
	/** the random numbergenerator contained in this instance */
	protected final T gen;
	/**
	 * creates a new instance of {@code RNG}
	 * wrapping this random number generator
	 * @param random the random number generator to wrap
	 */
	protected RNG(T random) {
		gen=random;
	}
	/**
	 * returns the {@link #gen} of this {@code RNG}
	 * @return {@code this.gen}
	 */
	public T getRandom() { return gen; }
	/**
	 * returns a random integer, based on {@code Random}
	 * @param min {@code int} minimum value (inclusive)
	 * @param max {@code int} maximum value (inclusive)
	 * @return random integer
	 * @see Random#nextInt(int)
	 */
	public int limitedIntRandom(int min, int max) {
		return gen.nextInt(max - min + 1) + min;
	}
	/**
	 * returns a random double, based on {@code Random}
	 * @param min {@code double} minimum value (inclusive)
	 * @param max {@code double} minimum value (exclusive)
	 * @return random double
	 * @see Random#nextDouble()
	 */
	public double limitedDoubleRandom(double min, double max) {
		return gen.nextDouble()*((max)-min)+min;
	}
	/**
	 * returns a random float, based on {@code Random}
	 * @param min {@code float} minimum value (inclusive)
	 * @param max {@code float} minimum value (exclusive)
	 * @return random float
	 * @see Random#nextFloat()
	 */
	public float limitedFloatRandom(float min, float max) {
		return gen.nextFloat()*((max)-min)+min;
	}
	/**
	 * creates a 'random' long<p>by calling {@link Random#nextDouble()},
	 * and the doing a {@code (random)*(max-min)+min},
	 * unknown reliablity due to this cheesing/attempting
	 * @param min {@code long} minimum value (inclusive)
	 * @param max {@code long} maximum value (exclusive)
	 * @return 'random' {@code long}
	 */
	public long limitedLongRandom(long min, long max) {
		//were cheesing this with a double {@code 0-1}, because Random.nextLong() is not limitable
		double preRN=gen.nextDouble();
		long mult = (max-min)+min;
		long rn = (long)(preRN*mult);
		return rn;
	}
	/**
	 * creates a 'random' long by appending random digits to a {@link StringBuilder},
	 * setting the sign with a {@link Random#nextBoolean()},
	 * and then parsing it as a {@code long}
	 * @param length integer length
	 * @return 'random' {@code long}
	 */
	public long rollLong (int length) { //Long max=9.223.372.036.854.775.808 ^= 19 digits
		StringBuilder outputS=new StringBuilder();
		long output;
		length=length>18?18:length; // limit lenght to a save length of digits for longs
		for (int i = 0; i<length; i++) {
			outputS.append(limitedIntRandom(0,9));
		}
		if (gen.nextBoolean()) {
			outputS.insert(0,"+");
		} else {
			outputS.insert(0,"-");
		}
		output=Long.parseLong(outputS.toString());
		return output;
	}
	/**
	 * sets {@link #gen} seed to the result of {@link RNG#rollLong(int)}
	 * @param length integer length of the new seed
	 * @return the new seed
	 */
	public long rerollRandom(int length) {
		long seed=this.rollLong(length);
		gen.setSeed(seed);
		return seed;
	}
}
