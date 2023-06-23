package com.Ace009.library.CClass;

import java.util.stream.IntStream;

/**
 * 'static' class,
 * one of the 'C(ustom)Class' libaries,
 * this one works with simple mathematics
 * @author Ace009
 */
public class CMath {
	/**
	 * don't
	 * @see CMath
	 */
	private CMath() {}
	/**
	 * returns a random integer between min and max
	 * @param min {@code int} minimum value (inclusive)
	 * @param max {@code int} maximum value (inclusive)
	 * @return {@code int}: random, between min and max
	 */
	public static int limitedRandom(int min, int max) {
		Double random=Math.random()*((max+1)-min)+min;
		return random.intValue();
	}
	/**
	 * method to create an {@code IntStream} containing {@code {1,2,3,...)}}
	 * @return {@code {1,2,3,...)}}
	 */
	public static IntStream Numbers() { return IntStream.iterate(1, i->i+1); }
	/**
	 * private method to check if a number is prime,
	 * <p> by checking if the part of {@link CMath#Numbers()} is at max {@code 2} long (1 and itself)
	 * @param n number to check
	 * @return true if prime, false otherwise
	 */
	public static boolean isPrime(int n) {
		return Numbers().limit(n).parallel().filter(i->n%i==0).toArray().length <= 2;
	}
	/**
	 * returns an {@code int[]} containing all prime numbers up to {@code max}
	 * @param max highest possible prime in {@code return}
	 * @return an {@code int[]}
	 * @see CMath#isPrime(int)
	 */
	public static int[] getPrimesUpTo(final int max) {
		return Numbers().limit(max).parallel().filter(e->isPrime(e)).sequential().toArray();
	}
	/**
	 * returns an {@code int[]} containing the first {@code length} prime numbers
	 * @param length length of {@code return}
	 * @return an {@code int[]}
	 * @see CMath#isPrime(int)
	 */
	public static int[] getPrimes(final int length) {
		return Numbers().parallel().filter(e->isPrime(e)).sequential().limit(length).toArray();
	}
}
