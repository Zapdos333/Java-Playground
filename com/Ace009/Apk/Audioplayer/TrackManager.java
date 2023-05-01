package com.Ace009.Apk.Audioplayer;

import java.util.ArrayList;
import java.util.Arrays;
import com.Ace009.library.CClass.CMath;
import com.Ace009.library.CClass.CList;

public final class TrackManager {
	ArrayList<Track> history=new ArrayList<Track>();
	ArrayList<Track> playlist=new ArrayList<Track>();
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
			fitsToName=new ArrayList<String>();
			noFitName=new ArrayList<String>();
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
				element.fitsToName=new ArrayList<String>();
				element.noFitName=new ArrayList<String>();
				element.amplification=1;
			});
			playlist=new ArrayList<Track>();
		}
		history=new ArrayList<Track>();
	}
	public Track getNextTrack(Track currTrack) {
		Track output;
		if (CMath.limitedRandom(0,3)>1) {
			String outputName=CList.getRandom(currTrack.fitsToName);
			output=playlist.stream().filter(element->element.fName.equals(outputName)).findFirst().get();
		} else {
			output=CList.getRandom(playlist);
		}
		return output;
	}
	//debug Main method
	public static void main(String[] args) {
		for (int i = 1; i <= 15; i++) {
			System.out.println("Iteration: "+i);
			System.out.println("Result: "+CMath.limitedRandom(10,20));
		}
	}
}
