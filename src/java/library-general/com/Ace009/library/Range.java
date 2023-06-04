package com.Ace009.library;

import java.util.ArrayList;

/**
 * class to create Lists or Arrays that work like pythons for(i in range(x)),
 * extends {@link java.util.ArrayList} to create range using the constructor,
 * also has static methods to create {@code int[]} with the same contents
 * @author Ace009
 */
public class Range extends ArrayList<Integer> {
	/**
	 * create an {@code ArrayList<Integer>} with the first element being 0 adding 1
	 * and adding it to the list each iteration, until it reaches {@code stop}
	 * @param stop end integer
	 * @return {@code ArrayList<Integer>} containing the specified {@code Integers}
	 * @see #Range(int,int,int)
	 */
	public Range(int stop) {
		this.ensureCapacity(stop+1);
		for (int i = 0; i < stop; i++) {
			this.add(i);
		}
	}
	/**
	 * create an {@code ArrayList<Integer>} with the first element being {@code start}
	 * adding 1 and adding it to the list each iteration, until it reaches {@code stop}
	 * @param start starting integer
	 * @param stop end integer
	 * @return {@code ArrayList<Integer>} containing the specified {@code Integers}
	 * @see #Range(int,int,int)
	 */
	public Range(int start, int stop) {
		this.ensureCapacity(1+stop-start);
		for (int i = start; i < stop; i++) {
			this.add(i);
		}
	}
	/**
	 * create an {@code ArrayList<Integer>} with the first element being {@code start}
	 * adding {@code steps} and adding it to the list each iteration,
	 * before(exclusive) it reaches {@code stop}
	 * @param start starting integer
	 * @param stop end integer
	 * @param steps integer step in each iteration
	 * @return {@code ArrayList<Integer>} containing the specified {@code Integers}
	 */
	public Range(int start, int stop, int steps) {
		this.ensureCapacity(((stop-start)/steps)+1);
		for (int i = start; i < stop; i+=steps) {
			this.add(i);
		}
	}
	/**
	 * default implementation of {@link #arrayRange(int, int, int)}
	 * with {@code start} defaulting to 0
	 * and {@code steps} defaulting to 1
	 * @param stop end integer
	 * @return an {@code int array} containing the specified {@code int}
	 * @see #arrayRange(int, int, int)
	 */
	public static int[] arrayRange(int stop) {
		return arrayRange(0, stop, 1);
	}
	/**
	 * default implementation of {@link #arrayRange(int, int, int)}
	 * with {@code steps} defaulting to 1
	 * @param start starting integer
	 * @param stop end integer
	 * @return an {@code int array} containing the specified {@code int}
	 * @see #arrayRange(int, int, int)
	 */
	public static int[] arrayRange(int start, int stop) {
		return arrayRange(start, stop, 1);
	}
	/**
	 * creates an array of {@code int} with the first {@code int} being {@code start},
	 * the last {@code int} being {@code stop}
	 * and the next {@code int} always being the previous {@code int} plus {@code steps}
	 * @param start starting integer
	 * @param stop end integer
	 * @param steps integer step in each iteration
	 * @return an {@code int array} containing the specified {@code int}
	 */
	public static int[] arrayRange(int start, int stop, int steps) {
		int[] output = new int[((stop-start)/steps)+1];
		for (int i = start; i < stop; i+=steps) {
			output[(i-start)/steps] = i;
		}
		return output;
	}
}
