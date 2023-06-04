package com.Ace009.library.CClass;

/**
 * final 'static' class,
 * one of the 'C(ustom)Class' libaries,
 * this one works with {@code Strings}
 * @author Ace009
 */
final public class CString {
	/**
	 * don't
	 * @see CString
	 */
	private CString() {}
	/**
	 * returns a {@code String} with (at least) the given {@code length},
	 * and th start filled up with " ", if {@code input} is shorter than {@code length}
	 * @param input
	 * @param length
	 * @return {@code String}: formatted to {@code length}
	 */
	public static String formatToLength(String input, int length) {
		StringBuilder output=new StringBuilder(input);
		output.ensureCapacity(length);
		while (output.length()<length) {
			output.insert(0," ");
		}
		return output.toString();
	}
	/**
	 * returns the {@code sum} of the {@code int} value of all {@code char} in {@code input}
	 * @param input {@code String}
	 * @return {@code int}: sum of all {@code char} in {@code input}
	 */
	public static int numericalSum(String input) {
		int output = 0;
		for (char c : input.toCharArray()) {
			output += (int)c;
		}
		return output;
	}
}