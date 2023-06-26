package com.Ace009.library.CClass;

import java.util.stream.Stream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * 'static' class,
 * one of the 'C(ustom)Class' libaries,
 * this one works with {@code Strings}
 * @author Ace009
 */
public class CString {
	/**
	 * don't
	 * @see CString
	 */
	private CString() {}
	/**
	 * returns a {@code String} with (at least) the given {@code length},
	 * and th start filled up with {@code space}, if {@code input} is shorter than {@code length}
	 * @param input {@code String} to fit to {@code length}
	 * @param length {@code int} minimum length for output
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
	 * returns the {@code sum} of the {@code int} values of all {@code char} in {@code input}
	 * @param input {@code String}, converted {@code .toCharArray}
	 * @return {@code int}: sum of all {@code char} in {@code input}
	 */
	public static int numericalSum(String input) {
		return Stream.of(CArray.asObjectArray(input.toCharArray())).mapToInt(e->(int)e.charValue()).sum();
	}
	/**
	 * returns the first accurance of {@code regex} in {@code input}
	 * or {@code ""}, if no match is found
	 */
	public static String match(String input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.find() ? matcher.group() : "";
	}
}
