package com.Ace009.library.CClass;

/**
 * final 'static' class,
 * one of the 'C(ustom)Class' libaries,
 * this one works with <code>Strings</code>
 * @author Ace009
 */
final public class CString {
	/**
	 * don't
	 * @see CString
	 */
	private CString() {}
	/**
	 * returns a <code>String</code> with (at least) the given <code>length</code>,
	 * and th start filled up with " ", if <code>input</code> is shorter than <code>length</code>
	 * @param input
	 * @param length
	 * @return <code>String</code>: formatted to <code>length</code>
	 */
	public static String formatToLength(String input, int length) {
		StringBuilder output=new StringBuilder(input);
		output.ensureCapacity(length);
		while (output.length()<length) {
			output.insert(0," ");
		}
		return output.toString();
	}
}
