package com.Ace009.library;

import java.util.Random;
import java.util.Arrays;

public class RNG {
	Random rng;
	//function for RNG
	public int limitedRandom(int min, int max) {
		return rng.nextInt(max - min + 1) + min;
	}
	public long rerollRandom(int length) {
		StringBuilder seedS=new StringBuilder();
		Long seed;
		if (length>18) {length=18;}
		for (int i = 0; i<length; i++) {
			seedS.append(limitedRandom(0,9));
		}
		seed=Long.parseLong(seedS.toString());
		rng.setSeed(seed);
		return seed;
	}
	public RNG () {
		rng=new Random();
	}
	//debug Main function
	public static void main(String[] args) {
		RNG testRng= new RNG();
		System.out.println("Testing Function for Random Number generator");
		System.out.println("first argument is for the amount of iterations");
		System.out.println("second argument is the highest maximum number");
		System.out.println("third argument is the seed length");
		System.out.println("default values are: limit=10, max=10, seed length=15");
		int limit;
		int max;
		int seedLength;
		if (args.length < 1) {
			limit=10;
		} else {
			limit=Integer.parseInt(args[0]);
			System.out.println("Set limit to custom: "+limit);
		} if (args.length < 2) {
			max=10;
		} else {
			max=Integer.parseInt(args[1]);
			System.out.println("Set max to custom: "+limit);
		} if (args.length < 3) {
			seedLength=15;
		} else {
			seedLength=Integer.parseInt(args[2]);
			System.out.println("Set seedLength to custom: "+limit);
		}
		System.out.println("new Seed is: "+testRng.rerollRandom(seedLength));
		for (int i = 1; i <= limit; i++) {
			System.out.println("Iteration: "+i);
			System.out.println("Result: "+testRng.limitedRandom(0,max));
		}
		System.out.println("new Seed is: "+testRng.rerollRandom(seedLength));
	}
}
