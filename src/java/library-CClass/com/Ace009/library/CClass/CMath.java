package com.Ace009.library.CClass;

import java.util.stream.IntStream;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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
	 * <p> (uses {@code Sieve of Eratosthenes})
	 * @param max highest possible prime in {@code return}
	 * @return an {@code int[]}
	 */
	public static int[] getPrimesUpTo(final int max) {
		boolean[] isComposite = new boolean[max + 1];
		List<Integer> primes = new ArrayList<>();
		for (int i = 2; i <= max; i++) {
			if (!isComposite[i]) {
				primes.add(i);
				for (int j = i * 2; j <= max; j += i) {
					isComposite[j] = true;
				}
			}
		}
		return primes.stream().mapToInt(Integer::intValue).toArray();
	}
	/**
	 * seperates a double into a map containing its value and exponent
	 * @param number the double to analyze
	 * @return the map with the keys {@code "number"} and {@code "exponent"}
	 * @implNote this method will remove any last digits until the number-value can fit into an integer ({@link Integer#MAX_VALUE})
	 */
	public static Map<String,Integer> seperate(final double number) {
		String nr = Double.valueOf(number).toString();
		while (Double.parseDouble(nr.replace(".",""))>Integer.MAX_VALUE) {
			char[] t_ = new char[nr.length()-1];
			for (int i=0; i<t_.length; i++) {t_[i] = nr.charAt(i);}
			nr=String.valueOf(t_);
		}
		int sub = nr.indexOf("."); nr=nr.replace(".","");
		final String Fnr = nr;
		return new HashMap<>(2){
			{
				put("number", Integer.parseInt(Fnr) );
				put("exponent",Fnr.replace("-","").length()-sub);
			}
		};
	}
}
