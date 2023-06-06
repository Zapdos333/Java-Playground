package com.Ace009.nonLibrary.tests;

import java.util.Arrays;
import java.util.ArrayList;
import com.Ace009.library.CClass.CNumber;

/**
 * test for how many different possible values there are for every number type
 * @author Ace009
 */
public class numberTest {
	static double MIN_VALUE;
	static double MAX_VALUE;
	/** actual main method */
	public static void nain(String className) {
		switch (className.toLowerCase()) {
			case "integer":
			case "int":
				MIN_VALUE = 1;
				MAX_VALUE = Integer.MAX_VALUE;
				break;
			case "long":
				MIN_VALUE = 1;
				MAX_VALUE = Long.MAX_VALUE;
				break;
			case "double":
				MIN_VALUE = Double.MIN_VALUE;
				MAX_VALUE = Double.MAX_VALUE;
				break;
			case "float":
				MIN_VALUE = Float.MIN_VALUE;
				MAX_VALUE = Float.MAX_VALUE;
				break;
			default:
				MIN_VALUE = 0;
				MAX_VALUE = 0;
				return;
		}
		double amount=Math.ceil(MAX_VALUE/MIN_VALUE);
		System.out.printf("Class: %s has %f different possibilitys with a difference of %f%n",
			className,CNumber.format(amount),MIN_VALUE);
	}
	/**
	 * command line wrapper for {@link #nain(String)}
	 * @param args standard main-method arguments
	 */
	public static void main(String[] args) {
		ArrayList<String> argus=new ArrayList<String>(Arrays.asList(args));
		if (argus.size()<1) {argus.add("");}
		nain(argus.toString().replaceAll("\\[|\\]|,",""));
	}
}
