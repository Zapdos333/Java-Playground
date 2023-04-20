package com.Ace009.Apk.Audioplayer;

import java.util.ArrayList;
import java.util.Arrays;

class AudioPlayer {
	//define Song Object
	private class Song {
		int nId;
		String name;
		int[] length;
		ArrayList<Integer> fitsToId;
		ArrayList<Integer> noFitId;
		public Song(int index, String s_name, int lengthMin, int lengthSec) {
			nId=index;
			name=s_name;
			length=new int[2];
			length[0]=lengthSec;
			length[1]=lengthMin;
		}
	}
	//internal limited Int RNG
	private static int limitedRandom(int min, int max) {
		Double random=Math.random()*((max+1)-min)+min;
		return random.intValue();
	}
	//debug Main function
	public static void main(String[] args) {
		for (int i = 1; i <= 15; i++) {
			System.out.println("Iteration: "+i);
			System.out.println("Result: "+limitedRandom(10,20));
		}
	}
}
