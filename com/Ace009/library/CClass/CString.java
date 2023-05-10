package com.Ace009.library.CClass;

final public class CString {
	private CString() {}
	public static String formatToLength(String input, int length) {
		StringBuilder output=new StringBuilder(input);
		output.ensureCapacity(length);
		while (output.length()<length) {
			output.insert(0," ");
		}
		return output.toString();
	}
}
