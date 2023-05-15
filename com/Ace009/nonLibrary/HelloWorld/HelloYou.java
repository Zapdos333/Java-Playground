package com.Ace009.nonLibrary.HelloWorld;

import java.util.Arrays;

public class HelloYou {
	public static void main(String[] args) {
		if (Arrays.asList(args).size()<0) {
			args=new String[1];
			args[0] = "You";
		}
		System.out.println("Hello, "+Arrays.toString(args).replaceAll("\\[|\\]|,",""));
	}
}
