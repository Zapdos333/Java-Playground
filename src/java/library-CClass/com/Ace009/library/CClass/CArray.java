package com.Ace009.library.CClass;

import java.util.List;
import java.util.stream.*;

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
	 * turns the array into an {@code List}
	 * <p> uses {@link Stream} magic
	 * @param <T> the type of the elements
	 * @param array the original array
	 * @return {@code List} containing all the elements from the array
	 */
	public static <T> List<T> asList(T[] array) {
		return Stream.of(array).toList();
	}
	//region asObjectArray
		/**
		 * turns the given array of primitives (here: {@code char})
		 * into an array of objects (here: {@link Character})
		 * @param array the original array
		 * @return a {@code Character[]} array
		 */
		public static Character[] asObjectArray(char[] array) {
			Character[] output = new Character[array.length];
			for (int i = 0; i < output.length; i++) {
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
		public static Integer[] asObjectArray(int[] array) {
			return IntStream.of(array).mapToObj(Integer::valueOf).toArray(Integer[]::new);
		}
		/**
		 * turns the given array of primitives (here: {@code boolean})
		 * into an array of objects (here: {@link Boolean})
		 * @param array the original array
		 * @return a {@code Boolean[]} array
		 */
		public static Boolean[] asObjectArray(boolean[] array) {
			Boolean[] output = new Boolean[array.length];
			for (int i = 0; i < output.length; i++) {
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
		public static Double[] asObjectArray(double[] array) {
			return DoubleStream.of(array).mapToObj(Double::valueOf).toArray(Double[]::new);
		}
		/**
		 * turns the given array of primitives (here: {@code float})
		 * into an array of objects (here: {@link Float})
		 * @param array the original array
		 * @return a {@code Float[]} array
		 */
		public static Float[] asObjectArray(float[] array) {
			Float[] output = new Float[array.length];
			for (int i = 0; i < output.length; i++) {
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
		public static Long[] asObjectArray(long[] array) {
			return LongStream.of(array).mapToObj(Long::valueOf).toArray(Long[]::new);
		}
		/**
		 * turns the given array of primitives (here: {@code short})
		 * into an array of objects (here: {@link Short})
		 * @param array the original array
		 * @return a {@code Short[]} array
		 */
		public static Short[] asObjectArray(short[] array) {
			Short[] output = new Short[array.length];
			for (int i = 0; i < output.length; i++) {
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
		public static Byte[] asObjectArray(byte[] array) {
			Byte[] output = new Byte[array.length];
			for (int i = 0; i < output.length; i++) {
				output[i] = array[i];
			}
			return output;
		}
	//endregion
}
