package com.Ace009.library.Math;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.Ace009.library.CClass.CMath;
import com.Ace009.library.CClass.CArray;
import com.Ace009.library.CClass.CList;
import com.Ace009.library.CClass.CStreamOf;

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
		number=Math.absExact(number);
		if (number==0||number==1) return new int[]{number};
		int[] check = CMath.getPrimesUpTo(number);
		for (int i : check) { if (i==number) return new int[]{number}; }
		List<Integer> output = new ArrayList<>();
		for (int i = 0; (i<check.length)&&(number>1); i++) {
			if (number % check[i] == 0) {
				output.add(check[i]);
				number /= check[i];
				i--;
			}
		}
		int[] t_ = new int[output.size()];
		for (int i = 0; i<t_.length; i++) t_[i] = output.get(i);
		return t_;
	}
	/**
	 * returns the greatest common divisor of the two numbers
	 * @param n1 number 1
	 * @param n2 number 2
	 * @return the greatest common divisor
	 */
	public static int gcd(int n1, int n2) {
		int[] p1 = seperateToPrimes(n1);
		int[] p2 = seperateToPrimes(n2);
		Map<Integer, Integer> fMap1 = CArray.frequencyMap(CArray.asObjAr(p1));
		Map<Integer, Integer> fMap2 = CArray.frequencyMap(CArray.asObjAr(p2));
			List<Integer> Length = new ArrayList<>();
			Length.addAll(fMap1.keySet()); Length.addAll(fMap2.keySet());
		Map<Integer, Integer> frequencyMap = new HashMap<>(
			CList.deduplicate(Length).size()
		);
		for (Map.Entry<Integer,Integer> e : fMap1.entrySet()) {
			frequencyMap.put(e.getKey(),Math.min(e.getValue(),fMap2.getOrDefault(e.getKey(),0)));
		}
		for (Map.Entry<Integer,Integer> e : fMap2.entrySet()) {
			frequencyMap.put(e.getKey(),Math.min(e.getValue(),fMap1.getOrDefault(e.getKey(),0)));
		}
		Integer[] t_1 = CStreamOf.map(
			frequencyMap.entrySet().toArray(Map.Entry[]::new),
			e-> Integer.valueOf((int)Math.pow((Integer)e.getKey(),(Integer)e.getValue()))
		);
		int t_2 = 1;
		for (Integer e : t_1) t_2*=e;
		return t_2;
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
		Map<Integer, Integer> fMap1 = CArray.frequencyMap(CArray.asObjAr(p1));
		Map<Integer, Integer> fMap2 = CArray.frequencyMap(CArray.asObjAr(p2));
			List<Integer> Length = new ArrayList<>();
			Length.addAll(fMap1.keySet()); Length.addAll(fMap2.keySet());
		Map<Integer, Integer> frequencyMap = new HashMap<>(
			CList.deduplicate(Length).size()
		);
		for (Map.Entry<Integer,Integer> e : fMap1.entrySet()) {
			frequencyMap.put(e.getKey(),Math.max(e.getValue(),fMap2.getOrDefault(e.getKey(),0)));
		}
		for (Map.Entry<Integer,Integer> e : fMap2.entrySet()) {
			frequencyMap.put(e.getKey(),Math.max(e.getValue(),fMap1.getOrDefault(e.getKey(),0)));
		}
		Integer[] t_1 = CStreamOf.map(
			frequencyMap.entrySet().toArray(Map.Entry[]::new),
			e-> Integer.valueOf((int)Math.pow((Integer)e.getKey(),(Integer)e.getValue()))
		);
		int t_2 = 1;
		for (Integer e : t_1) t_2*=e;
		return t_2;
	}
}
