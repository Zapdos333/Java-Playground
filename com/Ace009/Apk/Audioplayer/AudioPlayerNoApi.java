package com.Ace009.Apk.Audioplayer;

import java.util.ArrayList;
import java.util.Arrays;
import com.Ace009.library.CMath;

class AudioPlayer {
	//define Song Object
	private class Song {
		int id;	//id as number in (play)list
		String fName;	//file name
		String fPath; //file path
		int[] length; //array [sec,min]
		ArrayList<Integer> fitsToId; //ArrayList of ids of Songs that fit
		ArrayList<Integer> noFitId; //ArrayList of ids of Songs that don't fit
		public Song(int index, String f_name, String f_path, int lengthMin, int lengthSec) {
			id=index;
			fName=f_name;
			fPath=f_path;
			length=new int[2];
			length[0]=lengthSec;
			length[1]=lengthMin;
		}
	}
	//debug Main function
	public static void main(String[] args) {
		for (int i = 1; i <= 15; i++) {
			System.out.println("Iteration: "+i);
			System.out.println("Result: "+limitedRandom(10,20));
		}
	}
}
