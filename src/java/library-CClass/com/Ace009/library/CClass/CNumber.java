package com.Ace009.library.CClass;

import java.util.Arrays;

/**
 * 'static' class,
 * one of the 'C(ustom)Class' libaries,
 * this one adds number formatting
 * @author Ace009
 */
// TODO: understand properly and maybe clean up
public class CNumber {
	/**
	 * don't
	 * @see CNumber
	 */
	private CNumber() {}
	/**
	 * formats the input {@code int} to a {@code String} with dots seperating blocks of 3 digits
	 * @param nr {@code int} to format
	 * @return a {@code String} representation, formatted to have '.' every 3 digits
	 */
	public static String format(int nr) {
		String negative = "";
		StringBuilder output = new StringBuilder();
		char[] digits = Integer.toString(nr).toCharArray();
		int digitsLen =Arrays.asList(digits).size();
		for (int i=0;i<digitsLen;i++) {
			output.append(digits[i]);
			if (i==0&&digits[i]=='-') {
				negative = "-";
				continue;
			}
			if ((digitsLen-i)%3==0) {
				output.append('.');
			}
		}
		return negative+output.toString();
	}
	/**
	 * formats the input {@code long} to a {@code String} with dots seperating blocks of 3 digits
	 * @param nr {@code long} to format
	 * @return a {@code String} representation, formatted to have '.' every 3 digits
	 */
	public static String format(long nr) {
		String negative = "";
		StringBuilder output = new StringBuilder();
		String digitS = Long.toString(nr);
		final char[] digits = digitS.toCharArray();
		int digitsLen = digitS.length();
		for (int i=0;i<digitsLen;i++) {
			output.append(digits[i]);
			if (i==0&&digits[i]=='-') {
				negative = "-";
				continue;
			}
			if ((digitsLen-i)%3==0) {
				output.append('.');
			}
		}
		return negative+output.toString();
	}
	/**
	 * formats the input {@code double} to a {@code String} with dots seperating blocks of 3 digits
	 * before the decimal point
	 * @param nr {@code double} to format
	 * @return a {@code String} representation, formatted to have '.' every 3 digits
	 */
	public static String format(double nr) {
		String negative = "";
		StringBuilder output = new StringBuilder();
		String digitS = Double.toString(nr);
		String digitRest = "";
		if (digitS.contains(".")) {
			final int pos = digitS.indexOf(".");
			digitS = digitS.substring(0,pos-1);
			digitRest = digitS.substring(pos);
		}
		final char[] digits = digitS.toCharArray();
		int digitsLen = digitS.length();
		for (int i=0;i<digitsLen;i++) {
			output.append(digits[i]);
			if (digits[i]=='-') {
				negative = "-";
				continue;
			}
			if ((digitsLen-i)%3==0) {
				output.append('.');
			}
		}
		return negative+output.toString()+digitRest;
	}
	/**
	 * formats the input {@code float} to a {@code String} with dots seperating blocks of 3 digits
	 * before the decimal point
	 * @param nr {@code float} to format
	 * @return a {@code String} representation, formatted to have '.' every 3 digits
	 */
	public static String format(float nr) {
		String negative = "";
		StringBuilder output = new StringBuilder();
		String digitS=Float.toString(nr);
		String digitRest = "";
		if (digitS.contains(".")) {
			final int pos = digitS.indexOf(".");
			digitS = digitS.substring(0,pos-1);
			digitRest = digitS.substring(pos);
		}
		final char[] digits = digitS.toCharArray();
		int digitsLen = digitS.length();
		for (int i=0;i<digitsLen;i++) {
			output.append(digits[i]);
			if (digits[i]=='-') {
				negative = "-";
				continue;
			}
			if ((digitsLen-i)%3==0) {
				output.append('.');
				i++;
			}
		}
		return negative+output.toString()+digitRest;
	}
	/**
	 * default implementation of {@link #protoForm(String,boolean)}
	 * with {@code boolean(useComma)=false}
	 * @param nrS the {@code String} input
	 * @return result of {@link #protoForm(String,boolean)}
	 * @see #protoForm(String,boolean)
	 */
	protected static String protoForm(String nrS) {
		return protoForm(nrS, false);
	}
	/**
	 * formats the input {@code String} only contain one(the first) dot and no commas
	 * @param nrS the {@code String} input
	 * @param useComma use commas or dots as decimal point
	 * @return a {@code String} to parse to a number
	 * @see #protoForm(String)
	 * default implementation, with {@code useComma=false}
	 */
	protected static String protoForm(String nrS, boolean useComma) {
		boolean dot= false;
		char[] digits = nrS.toCharArray();
		StringBuilder output = new StringBuilder();
		for (char digit : digits) {
			switch (digit) {
				case '.':
					if (!dot && !useComma) {
						output.append('.');
						dot = true;
					} break;
				case ',':
					if (!dot && useComma) {
						output.append('.');
						dot = true;
					} break;
				default:
					output.append(digit);
			}
		}
		return output.toString();
	}
	/**
	 * parses the input {@code String} as {@code long} using Long.parseLong and CNumber.protoForm
	 * @param nrS {@code String} to parse
	 * @return {@code long} parsed
	 * @see Long#parseLong(String)
	 * @see #protoForm(String,boolean)
	 */
	public static long formLong(String nrS) {
		return Long.parseLong(protoForm(nrS));
	}
	/**
	 * parses the input {@code String} as {@code int} using Integer.parseInt and CNumber.protoForm
	 * @param nrS {@code String} to parse
	 * @return {@code int} parsed
	 * @see Integer#parseInt(String)
	 * @see #protoForm(String,boolean)
	 */
	public static int formInt(String nrS) {
		return Integer.parseInt(protoForm(nrS));
	}
	/**
	 * parses the input {@code String} as {@code double} using Double.parseDouble and CNumber.protoForm
	 * @param nrS {@code String} to parse
	 * @return {@code double} parsed
	 * @see Double#parseDouble(String)
	 * @see #protoForm(String,boolean)
	 */
	public static double formDouble(String nrS) {
		return Double.parseDouble(protoForm(nrS));
	}
	/**
	 * parses the input {@code String} as {@code float} using Float.parseFloat(String) and CNumber.protoForm(String)
	 * @param nrS {@code String} to parse
	 * @return {@code float} parsed
	 * @see Float#parseFloat(String)
	 * @see #protoForm(String,boolean)
	 */
	public static float formFloat(String nrS) {
		return Float.parseFloat(protoForm(nrS));
	}
}
