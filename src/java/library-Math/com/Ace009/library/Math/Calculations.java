package com.Ace009.library.Math;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;

import com.Ace009.library.CClass.CMath;
import com.Ace009.library.CClass.CArray;

/**
 * a library for more complex calculations,
 * used mainly in the {@link com.Ace009.library.Math local package}
 * @author Ace009
 */
public class Calculations {
	/** don't */
	private Calculations() {}
	/**
	 * turns a number into an {@code int[]} containing the prime factors of the number
	 * @param number number to dissect
	 * @return the array of prime factors
	 * @see CMath#getPrimesUpTo(int)
	 */
	public static int[] seperateToPrimes(int number) {
		number=Math.abs(number);
		int[] check = CMath.getPrimesUpTo(number);
		List<Integer> output = new ArrayList<>();
		for (int i = 1; i < check.length; i++) {
			if (number % check[i] == 0) {
				output.add(check[i]);
				number /= check[i];
				i--;
			}
		}
		return output.stream().mapToInt(i->i).toArray();
	}
	/**
	 * returns the greatest common divisor of the two numbers
	 * @param n1 number 1
	 * @param n2 number 2
	 * @return the greatest common divisor
	 */
	public static int gcd(int n1, int n2) {
		int[] p1 = seperateToPrimes(n1);
		Integer[] p2 = CArray.asObjectArray(seperateToPrimes(n2));
		return IntStream.of(p1)
			.filter(i->CArray.indexOf(p2, i)>-1)
			.reduce(1,(a,b)->a*b);
	}
	/**
	 * returns the least common multiple of the two numbers
	 * @param n1 number 1
	 * @param n2 number 2
	 * @return the least common multiple
	 */
	public static int lcm(int n1, int n2) {
		int[] p1 = seperateToPrimes(n1);
		int[] p2 = seperateToPrimes(n2);
		Map<Integer, Integer> fMap1 = new HashMap<>(IntStream.of(p1).distinct().toArray().length);
		Map<Integer, Integer> fMap2 = new HashMap<>(IntStream.of(p2).distinct().toArray().length);
		for (int e : p1) {
			fMap1.put(e,fMap1.getOrDefault(e,0)+ 1);
		}
		for (int e : p2) {
			fMap2.put(e,fMap2.getOrDefault(e,0)+ 1);
		}
		Map<Integer, Integer> frequencyMap = new HashMap<>(
			Stream.concat(
				fMap1.entrySet().stream().map(Map.Entry::getKey),
				fMap2.entrySet().stream().map(Map.Entry::getKey)
			).distinct().toArray().length
		);
		for (Map.Entry<Integer,Integer> e : fMap1.entrySet()) {
			frequencyMap.put(e.getKey(),Math.max(e.getValue(),fMap2.getOrDefault(e.getKey(),0)));
		}
		for (Map.Entry<Integer,Integer> e : fMap2.entrySet()) {
			frequencyMap.put(e.getKey(),Math.max(e.getValue(),fMap1.getOrDefault(e.getKey(),0)));
		}
		return frequencyMap.entrySet().stream()
			.mapToInt(e->(int)Math.pow(e.getKey(),e.getValue()))
			.reduce(1,(a,b)->a*b);
	}
}
