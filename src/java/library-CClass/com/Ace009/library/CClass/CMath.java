package com.Ace009.library.CClass;

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
	 * method to check if a number is prime,
	 * <p> checks division by all integers from {@code 2} to {@code Math.ceil(n/(double)2)}
	 * @param n number to check
	 * @return true if prime, false otherwise
	 */
	public static boolean isPrime(int n) {
		for (int i = 2; i < Math.ceil(n/(double)2); i++) {
			if (n % i == 0) return false;
		}
		return true;
	}
	/**
	 * returns an {@code int[]} containing all prime numbers up to {@code max}
	 * <p> (uses {@code Sieve of Sundaram})
	 * @param max highest possible prime in {@code return}
	 * @return an {@code int[]}
	 */
	public static int[] getPrimesUpTo(int max) {
		max=Math.absExact(max);
		max=Math.floorDiv(max, 2);
		boolean[] isComposite = new boolean[max + 1];
		for (int i=1; i<Math.sqrt(max); i++) {
			for (int j=i; i+j+(2*i*j)<=max; j++) {
				isComposite[i+j+(2*i*j)]=true;
			}
		}
		List<Integer> primes = new ArrayList<>();
		for (int i=0; i<isComposite.length; i++) if (!isComposite[i]) primes.add(i);
		primes.remove(Integer.valueOf(0));
		int[] t_ = new int[primes.size()+1]; t_[0]=2;
		for (int i=1; i<t_.length; i++) t_[i] = (primes.get(i-1).intValue()*2)+1;
		return t_;
	}
	/**
	 * seperates a double into a map containing its value and exponent
	 * <p> implementationNotice: this method will remove any last digits until the number-value can fit into an integer ({@link Integer#MAX_VALUE})
	 * @param number the double to analyze
	 * @return the map with the keys {@code "number"} and {@code "exponent"}
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
