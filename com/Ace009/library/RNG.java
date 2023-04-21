package com.Ace009.library;

import java.util.Random;
import java.util.Arrays;

public class RNG extends Random{
	public int limitedIntRandom(int min, int max) {
		return this.nextInt(max - min + 1) + min;
	}
	public double limitedDoubleRandom(double min, double max) {
		return this.nextDouble()*((max+1)-min)+min;
	}
	public float limitedFloatRandom(float min, float max) {
		return this.nextFloat()*((max+1)-min)+min;
	}
	public long limitedLongRandom(long min, long max) {
		//yes were cheesing this with a double 0-1, because Random.nextLong() is not limitable and doesn't give all the same chance
		double preRN = 0.0;
		preRN=this.nextDouble()*((max+1)-min)+min;
		long rn = Long.parseLong(Double.toString(preRN).replaceAll("\\.|E",""));
		return rn;
	}
	public long rerollRandom(int length) { //Long max=9.223.372.036.854.775.808
		StringBuilder seedS=new StringBuilder();
		Long seed;
		if (length>18) {length=18;}
		for (int i = 0; i<length; i++) {
			seedS.append(limitedIntRandom(0,9));
		}
		if (this.nextBoolean()) {
			seedS.insert(0,"+");
		} else {
			seedS.insert(0,"-");
		}
		seed=Long.parseLong(seedS.toString());
		this.setSeed(seed);
		return seed;
	}
	public static void help() {
		System.out.println("The RNG class extends java.util.Random");
		System.out.println("It adds multiple Functions: limitedIntRandom, limitedFloatRandom, limitedDoubleRandom and rerollRandom");
		System.out.println("limited[Type]Random takes a minimum and maximum of [Type] and returns a random [Type] between the two(inclusive)");
		System.out.println("rerollRandom takes an integer and creates a randomized long with the given length (caps at 18 digits)");
		System.out.println("that long is then set as seed for the rng");
		//System.out.println("");
	}
	public static void helpMain() {
		//documentation output
		System.out.println("Testing Function for Random Number generator");
		System.out.println("first argument is for the amount of iterations");
		System.out.println("second argument is the highest maximum number");
		System.out.println("third argument is the seed length");
		System.out.println("default values are: limit=10, max=10, seed length=18");
	}
	//debug Main function
	public static void main(String[] args) {
		if (args.length > 0 && args[0].matches("help|\\?")) {
			help();
			System.out.println("");
			helpMain();
			return;
		}
		RNG testRng= new RNG();
		helpMain();
		int limit;
		int max;
		int seedLength;
		//checking for custom values, else use defaults
		if (args.length < 1) {
			limit=10;
		} else {
			limit=Integer.parseInt(args[0]);
			System.out.println("Set limit to custom: "+limit);
		} if (args.length < 2) {
			max=10;
		} else {
			max=Integer.parseInt(args[1]);
			System.out.println("Set max to custom: "+max);
		} if (args.length < 3) {
			seedLength=15;
		} else {
			seedLength=Integer.parseInt(args[2]);
			System.out.println("Set seedLength to custom: "+seedLength);
		}
		//reroll for seed
		System.out.println("new Seed is: "+testRng.rerollRandom(seedLength));
		//output requested random numbers
		for (int i = 1; i <= limit; i++) {
			System.out.println("Iteration: "+i);
			System.out.println("Result: "+testRng.limitedFloatRandom(0,max));
		}
	}
}
