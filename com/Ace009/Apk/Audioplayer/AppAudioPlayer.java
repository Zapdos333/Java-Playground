package com.Ace009.Apk.Audioplayer;

import com.Ace009.library.RNG;
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
	public static void main(String[] args) {
		RNG.main(args);
	}
}
