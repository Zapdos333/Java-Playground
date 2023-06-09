package com.Ace009.library;

import java.util.ArrayList;
import java.util.Collection;



/**
 * class to create Lists or Arrays that work like pythons for(i in range(x)),
 * uses {@link java.util.Collection} as {@link Iterable},
 * also has static methods to create {@code int[]} with the same contents
 * @author Ace009
 */
public class Range {
	/**
	 * don't
	 */
	private Range() {}
	/**
	 * creates a {@code Collection<Integer>} with the first element being {@code 0} adding {@code 1}
	 * and adding it to the list each iteration, until it reaches {@code stop}
	 * <p>
	 * uses {@link #ListRange(int, int, int)} with start defaulting to {@code 0}
	 * and steps defaulting to {@code 1}
	 * @param stop end integer
	 * @return a {@code Collection<Integer>}
	 * @see Range#ListRange(int,int,int)
	 */
	public static Collection<Integer> ListRange(int stop) {
		return ListRange(0, stop, 1);
	}
	/**
	 * create a {@code Collection<Integer>} with the first element being {@code start}
	 * adding 1 and adding it to the list each iteration, until it reaches {@code stop}
	 * <p>
	 * uses {@link #ListRange(int, int, int)} with steps defaulting to {@code 1}
	 * @param start starting integer
	 * @param stop end integer
	 * @return a {@code Collection<Integer>}
	 * @see Range#ListRange(int,int,int)
	 */
	public static Collection<Integer> ListRange(int start, int stop) {
		return ListRange(start, stop, 1);
	}
	/**
	 * create a {@code Collection<Integer>} with the first element being {@code start}
	 * adding {@code steps} and adding it to the list each iteration,
	 * before(exclusive) it reaches {@code stop}
	 * @param start starting integer
	 * @param stop end integer
	 * @param steps integer step in each iteration
	 * @return a {@code Collection<Integer>}
	 */
	public static Collection<Integer> ListRange(int start, int stop, int steps) {
		ArrayList<Integer> output = new ArrayList<>();
		output.ensureCapacity(((stop-start)/steps)+1);
		for (int i = start; i < stop; i+=steps) {
			output.add(i);
		}
		return output;
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
		int[] output = new int[((stop-start)/steps)];
		for (int i = start; i < stop; i+=steps) {
			output[(i-start)/steps] = i;
		}
		return output;
	}
}
