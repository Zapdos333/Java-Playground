package com.Ace009.library.CClass;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.lang.reflect.Array;

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
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {return i;}
		}
		return -1;
	}
	/**
	 * wrapper for {@link Arrays#asList(Object...)}
	 * @param <T> the type of the elements
	 * @param array the original array
	 * @return {@code List} containing all the elements from the array
	 */
	public static <T> List<T> asList(T[] array) {
		return Arrays.asList(array);
	}
	//region asObjectArray
		/**
		 * turns the given array of primitives (here: {@code char})
		 * into an array of objects (here: {@link Character})
		 * @param array the original array
		 * @return a {@code Character[]} array
		 */
		public static Character[] asObjAr(char[] array) {
			Character[] output = new Character[array.length];
			for (int i = 0; i < array.length; i++) {
				output[i] = array[i];
			}
			return output;
		}
		/**
		 * turns the given array of primitives (here: {@code int})
		 * into an array of objects (here: {@link Integer})
		 * @param array the original array
		 * @return an {@code Integer[]} array
		 */
		public static Integer[] asObjAr(int[] array) {
			Integer[] output = new Integer[array.length];
			for (int i = 0; i < array.length; i++) {
				output[i] = array[i];
			}
			return output;
		}
		/**
		 * turns the given array of primitives (here: {@code boolean})
		 * into an array of objects (here: {@link Boolean})
		 * @param array the original array
		 * @return a {@code Boolean[]} array
		 */
		public static Boolean[] asObjAr(boolean[] array) {
			Boolean[] output = new Boolean[array.length];
			for (int i = 0; i < array.length; i++) {
				output[i] = array[i];
			}
			return output;
		}
		/**
		 * turns the given array of primitives (here: {@code double})
		 * into an array of objects (here: {@link Double})
		 * @param array the original array
		 * @return a {@code Double[]} array
		 */
		public static Double[] asObjAr(double[] array) {
			Double[] output = new Double[array.length];
			for (int i = 0; i < array.length; i++) {
				output[i] = array[i];
			}
			return output;
		}
		/**
		 * turns the given array of primitives (here: {@code float})
		 * into an array of objects (here: {@link Float})
		 * @param array the original array
		 * @return a {@code Float[]} array
		 */
		public static Float[] asObjAr(float[] array) {
			Float[] output = new Float[array.length];
			for (int i = 0; i < array.length; i++) {
				output[i] = array[i];
			}
			return output;
		}
		/**
		 * turns the given array of primitives (here: {@code long})
		 * into an array of objects (here: {@link Long})
		 * @param array the original array
		 * @return a {@code Long[]} array
		 */
		public static Long[] asObjAr(long[] array) {
			Long[] output = new Long[array.length];
			for (int i = 0; i < array.length; i++) {
				output[i] = array[i];
			}
			return output;
		}
		/**
		 * turns the given array of primitives (here: {@code short})
		 * into an array of objects (here: {@link Short})
		 * @param array the original array
		 * @return a {@code Short[]} array
		 */
		public static Short[] asObjAr(short[] array) {
			Short[] output = new Short[array.length];
			for (int i = 0; i < array.length; i++) {
				output[i] = array[i];
			}
			return output;
		}
		/**
		 * turns the given array of primitives (here: {@code byte})
		 * into an array of objects (here: {@link Byte})
		 * @param array the original array
		 * @return a {@code Byte[]} array
		 */
		public static Byte[] asObjAr(byte[] array) {
			Byte[] output = new Byte[array.length];
			for (int i = 0; i < array.length; i++) {
				output[i] = array[i];
			}
			return output;
		}
	//endregion
	/**
	 * creates a frequency map from a given array
	 * @param <T> the type of the elements
	 * @param array the array to map
	 * @return the frequency map
	 */
	public static <T> Map<T,Integer> frequencyMap(T[] array) {
		Map<T, Integer> output = new HashMap<>(CList.deduplicate(asList(array)).size());
		for (T e : array) {
			output.put(e,output.getOrDefault(e,0)+ 1);
		}
		return output;
	}
	/**
	 * returns a new instance of the given array with the given size
	 * @param <T> the type of the elements
	 * @param original the array to take the type from
	 * @param size the size of the new array
	 * @return a new array with the given size and type from original
	 */
	@SuppressWarnings("unchecked")
	static <T> T[] newArrayOf(T[] original, int size) { return (T[]) Array.newInstance(original.getClass().componentType(), size); }
}
