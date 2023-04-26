package com.Ace009.Apk.Audioplayer;

import java.util.ArrayList;
import java.util.Arrays;
import com.Ace009.library.CMath;

class TrackManager {
	ArrayList<Track> history=new ArrayList<Track>;
	ArrayList<Track> playlist=new ArrayList<Track>;
	//define Music Track Object
	public class Track {
		int id;	//id as number in (play)list
		String fName;	//file name
		String fPath; //file path
		int[] length; //array [sec,min]
		ArrayList<String> fitsToName; //ArrayList of fNames of Tracks that fit
		ArrayList<String> noFitName; //ArrayList of fNames of Tracks that don't fit
		float amplification;
		public Track(int index, String f_name, String f_path, int lengthMin, int lengthSec) {
			amplification=1;
			id=index;
			fName=f_name;
			fPath=f_path;
			length=new int[2];
			length[0]=lengthSec;
			length[1]=lengthMin;
		}
	}
	//reset playlist method
	public void reset(boolean full) {
		if (full) {
			playlist.forEach(element->{
				element.fitsToName=ArrayList<String>;
				element.noFitName=ArrayList<String>;
				element.amplification=1;
			});
		}
		history=new ArrayList<Track>();
	}
	//debug Main method
	public static void main(String[] args) {
		for (int i = 1; i <= 15; i++) {
			System.out.println("Iteration: "+i);
			System.out.println("Result: "+limitedRandom(10,20));
		}
	}
}
