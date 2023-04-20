package com.Ace009.Apk.Audioplayer;

import java.util.Random;
import java.util.Arrays;

public class RNG {
	//function for RNG
	public static int limitedRandom(int min, int max) {
		Random rng=new Random();
		return rng.nextInt(max - min + 1) + min;
	}
	//debug Main function
	public static void main(String[] args) {
		int limit;
		int max;
		if (args.length < 1) {
			limit=10;
		} else {
			limit=Integer.parseInt(args[0]);
		} if (args.length < 2) {
			max=10;
		} else {
			max=Integer.parseInt(args[1]);
		}
		for (int i = 1; i <= limit; i++) {
			System.out.println("Iteration: "+i);
			System.out.println("Result: "+limitedRandom(0,max));
		}
	}
}
