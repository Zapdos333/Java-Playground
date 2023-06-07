package com.Ace009.nonLibrary.HelloWorld;

import java.util.Arrays;

/**
 * main method prints Hello You, or the first argument instead of 'You'
 * @author Ace009
 */
public class HelloYou {
	/** don't */
	private HelloYou() {}
	/**
	 * prints out 'Hello You', supliments 'You' with {@code args[0]}
	 * @param args the standard main-method arguments
	 */
	public static void main(String[] args) {
		if (args.length<0) {
			args=new String[1];
			args[0] = "You";
		}
		System.out.println("Hello, "+Arrays.toString(args).replaceAll("\\[|\\]|,",""));
	}
}
