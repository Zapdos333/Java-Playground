package com.Ace009.library.CClass;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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
	 * private method to check if a number is prime,
	 * <p> by checking if the part of {@link CMath#Numbers()} is at max {@code 2} long (1 and itself)
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
	 * <p> (uses {@code Sieve of Eratosthenes})
	 * @param max highest possible prime in {@code return}
	 * @return an {@code int[]}
	 */
	public static int[] getPrimesUpTo(int max) {
		max=Math.absExact(max);
		boolean[] isComposite = new boolean[max+1];
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
	 * returns an {@code int[]} containing all prime numbers up to {@code max}
	 * <p> (uses {@code Sieve of Sundaram})
	 * @param max highest possible prime in {@code return}
	 * @return an {@code int[]}
	 */
	public static int[] getPrimesUpTo_(int max) {
		max=Math.absExact(max);
		max = Math.floorDiv(max, 2);
		boolean[] isComposite = new boolean[max + 1];
		List<Integer> primes = new ArrayList<>();
		for (int i=1; i<Math.sqrt(max); i++) {
			for (int j=i; i+j+(2*i*j)<=max; j++) {
				isComposite[i+j+(2*i*j)]=true;
			}
		}
		for (int i=0; i<isComposite.length; i++) if (!isComposite[i]) primes.add(i);
		return primes.stream().mapToInt(e->(e*2)+1).toArray();
	}
	/**
	 * returns an {@code int[]} containing all prime numbers up to {@code max}
	 * <p> (uses {@code Sieve of Atkin})
	 * @param max highest possible prime in {@code return}
	 * @return an {@code int[]}
	 */
	public static int[] getPrimesUpTo__(int max) {
		max=Math.absExact(max);
		final int[] sieve = new int[]{1,7,11,13,17,19,23,29,31,37,41,43,47,49,53,59};
		boolean[] prime = new boolean[max];
		for (int i=0; i<Math.sqrt(max); i++) {
			List<Integer> m = new ArrayList<>();
			for (int j=0; j<Math.sqrt(max); j+=2) {
				m.add(4*(int)Math.pow(i,2)+(int)Math.pow(j,2));
				int[] t_ = m.stream().mapToInt(e->e%60).distinct().toArray();
				if (Arrays.equals(t_,new int[]{1,13,17,29,37,41,49,53}) && m.size()<=max) {
					for (int ix : m) {
						prime[ix] = !prime[ix];
					}
				}
			}
		}
		for (int i=0; i<Math.sqrt(max);	i+=2) {
			List<Integer> m = new ArrayList<>();
			for (int j=1; j<Math.sqrt(max); j+=2) {
				m.add(3*(int)Math.pow(i,2)+(int)Math.pow(j,2));
				int[] t_ = m.stream().mapToInt(e->e%60).distinct().toArray();
				if (Arrays.equals(t_,new int[]{7,19,31,43}) && m.size()<=max) {
					for (int ix : m) {
						prime[ix] = !prime[ix];
					}
				}
			}
		}
		final int[] w = new int[(int)Math.ceil(max/(double)60)];
		for (int i = 0; i < w.length; i++) { w[i] = i; }
		List<Integer> M = new ArrayList<>();
		for (int i : sieve) for (int j : w) M.add(60*j+i);
		M.remove(Integer.valueOf(1));
		List<Integer> mm = new ArrayList<>();
		for (int m : M)	{
			if (Math.pow(m, 2)>max) break;
			else {
				mm.add((int)Math.pow(m, 2));
				List<Integer> c = new ArrayList<>();
				if (prime[m]) for (int m2 : M) {
					c.addAll(mm.stream().map(e->e*m2).toList());
					if (c.size()>max) break;
					else for (int e : c) prime[e] = false;
				}
			}
		}
		List<Integer> output = new ArrayList<>(){ {add(2);add(3);add(5);} };
		for (int i=0; i<prime.length; i++) if (prime[i]) output.add(i);
		return output.stream().mapToInt(Integer::intValue).toArray();
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
