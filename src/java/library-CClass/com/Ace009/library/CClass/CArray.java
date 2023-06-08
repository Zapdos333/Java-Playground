package com.Ace009.library.CClass;

import java.util.ArrayList;

import com.Ace009.library.Range;
/**
 * 'static' class,
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
	/**
	 * returns the first index where the element is found in the array,
	 * <p>returns -1 if the element is not found
	 * @param <T> the type of the element/array
	 * @param array the array to search
	 * @param element the element to search for
	 * @return {@code int} the index where {@code element} was found, else -1
	 */
	public static <T> int indexOf(T[] array, T element) {
		for (int i : Range.arrayRange(array.length)) {
			if (array[i] == element) {return i;}
		}
		return -1;
	}
	/**
	 * turns the array into an {@code ArrayList}
	 * by adding all the elements one by one to a new {@code ArrayList}
	 * @param <T> the type of the elements
	 * @param array the original array
	 * @return {@code ArrayList} containing all the elements from the array
	 */
	public static <T> ArrayList<T> asList(T[] array) {
		ArrayList<T> output = new ArrayList<>(array.length);
		for (T element : array) {
			output.add(element);
		}
		return output;
	}
	//region asObjectArray
		/**
		 * turns the given array of primitives (here: {@code char})
		 * into an array of objects (here: {@code Character})
		 * @param array the original array
		 * @return an Object array
		 */
		public static Character[] asObjectArray(char[] array) {
			Character[] output = new Character[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
		/**
		 * turns the given array of primitives (here: {@code int})
		 * into an array of objects (here: {@code Integer})
		 * @param array the original array
		 * @return an Object array
		 */
		public static Integer[] asObjectArray(int[] array) {
			Integer[] output = new Integer[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
		/**
		 * turns the given array of primitives (here: {@code boolean})
		 * into an array of objects (here: {@code Boolean})
		 * @param array the original array
		 * @return an Object array
		 */
		public static Boolean[] asObjectArray(boolean[] array) {
			Boolean[] output = new Boolean[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
		/**
		 * turns the given array of primitives (here: {@code double})
		 * into an array of objects (here: {@code Double})
		 * @param array the original array
		 * @return an Object array
		 */
		public static Double[] asObjectArray(double[] array) {
			Double[] output = new Double[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
		/**
		 * turns the given array of primitives (here: {@code float})
		 * into an array of objects (here: {@code Float})
		 * @param array the original array
		 * @return an Object array
		 */
		public static Float[] asObjectArray(float[] array) {
			Float[] output = new Float[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
		/**
		 * turns the given array of primitives (here: {@code long})
		 * into an array of objects (here: {@code Long})
		 * @param array the original array
		 * @return an Object array
		 */
		public static Long[] asObjectArray(long[] array) {
			Long[] output = new Long[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
		/**
		 * turns the given array of primitives (here: {@code short})
		 * into an array of objects (here: {@code Short})
		 * @param array the original array
		 * @return an Object array
		 */
		public static Short[] asObjectArray(short[] array) {
			Short[] output = new Short[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
		/**
		 * turns the given array of primitives (here: {@code byte})
		 * into an array of objects (here: {@code Byte})
		 * @param array the original array
		 * @return an Object array
		 */
		public static Byte[] asObjectArray(byte[] array) {
			Byte[] output = new Byte[array.length];
			for (int i : Range.arrayRange(array.length)) {
				output[i] = array[i];
			}
			return output;
		}
	//endregion
}
