package com.Ace009.library.CClass;

import java.util.ArrayList;

import com.Ace009.library.Range;
/**
 * final 'static' class,
 * one of the 'C(ustom)Class' libaries,
 * this one works with Arrays {@code T[]}
 * @author Ace009
 */
public class CArray {
	/**
	 * don't
	 * @see CList
	 */
	private CArray() {}
	public static <T> int indexOf(T[] array, T element) {
		for (int i : Range.arrayRange(array.length)) {
			if (array[i] == element) {return i;}
		}
		return -1;
	}
	public static <T> ArrayList<T> asList(T[] array) {
		ArrayList<T> output = new ArrayList<>(array.length);
		for (T element : array) {
			output.add(element);
		}
		return output;
	}
	//region asObjectArray
		public static Character[] asObjectArray(char[] array) {
			Character[] output = new Character[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
		public static Integer[] asObjectArray(int[] array) {
			Integer[] output = new Integer[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
		public static Boolean[] asObjectArray(boolean[] array) {
			Boolean[] output = new Boolean[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
		public static Double[] asObjectArray(double[] array) {
			Double[] output = new Double[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
		public static Float[] asObjectArray(float[] array) {
			Float[] output = new Float[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
		public static Long[] asObjectArray(long[] array) {
			Long[] output = new Long[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
		public static Short[] asObjectArray(short[] array) {
			Short[] output = new Short[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
		public static Byte[] asObjectArray(byte[] array) {
			Byte[] output = new Byte[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
	//endregion
}
