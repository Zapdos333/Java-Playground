package com.Ace009.library.Math;

import java.util.List;
import java.util.ArrayList;

import com.Ace009.library.CClass.CList;
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
	public static int[] seperateToPrimes(long number) {
		//if (number <= 2) return new int[]{(int)number};
		int[] check = CMath.getPrimesUpTo((int)number);
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
	public static int gcd(long n1, long n2) {
		int[] p1 = seperateToPrimes(n1);
		int[] p2 = seperateToPrimes(n2);
		List<Integer> output = new ArrayList<>();
		if (p1.length >= p2.length) {
			for (int i : p1) {
					if ( CArray.indexOf(CArray.asObjectArray(p2), i)>=0 ) {
				output.add(i);
				}
			}
		} else  {
			for (int i : p2) {
				if ( CArray.indexOf(CArray.asObjectArray(p1), i)>=0 ) {
					output.add(i);
				}
			}
		}
		return output.stream().mapToInt(i->i).reduce(1,(a,b)->a*b);
	}
	/**
	 * returns the least common multiple of the two numbers
	 * @param n1 number 1
	 * @param n2 number 2
	 * @return the least common multiple
	 */
	public static int lcm(long n1, long n2) {
	List<Integer> p1 = CArray.asList(CArray.asObjectArray(seperateToPrimes(n1)));
		List<Integer> p2 = CArray.asList(CArray.asObjectArray(seperateToPrimes(n2)));
		List<Integer> t_ = new ArrayList<Integer>();
		t_.addAll(p1);t_.addAll(p2);
		List<Integer> output = CList.deduplicate(t_);
		return output.stream().mapToInt(i->i).reduce(1,(a,b)->a*b);
	}
}
