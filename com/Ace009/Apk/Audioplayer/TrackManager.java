package com.Ace009.Apk.Audioplayer;

import java.util.ArrayList;
import java.util.Arrays;
import com.Ace009.library.CClass.CMath;
import com.Ace009.library.CClass.CList;

public final class TrackManager {
	ArrayList<Integer> history=new ArrayList<Integer>();	//list of ids in allTracks
	ArrayList<Integer> playlist=new ArrayList<Integer>();	//""
	ArrayList<Track> allTracks=new ArrayList<Track>();
	//define Music Track Object
	public class Track {
		String fName;	//file name
		String fPath; //file path
		int[] length; //array [sec,min]
		ArrayList<Integer> fitsToId=new ArrayList<Integer>(); //ArrayList of allTrackIds of Tracks that fit
		ArrayList<Integer> noFitId=new ArrayList<Integer>(); //ArrayList of allTrackIds of Tracks that don't fit
		float amplification;
		public Track(String f_name, String f_path, int lengthMin, int lengthSec) {
			//id is index in _allTracks_
			amplification=1;
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
			allTracks.forEach(element->{
				element.fitsToId=new ArrayList<String>();
				element.noFitId=new ArrayList<String>();
				element.amplification=1;
			});
			playlist=new ArrayList<Integer>();
		}
		history=new ArrayList<Integer>();
	}
	//method to get a random next Track based on fits
	public Track getNextTrack(Track currTrack) {
		int output;
		if (CMath.limitedRandom(0,3)>1) {
			output=CList.getRandom(currTrack.fitsToId);
		} else {
			output=CList.getRandom(playlist);
		}
		return allTracks.get(output);
	}
	//method to set the values int 
	public void setFitValue(int fitValue, int currTrack, int nextTrack) {
		switch(fitValue) {
			case 1:
				allTracks.get(currTrack).fitsToId.add(playlist.get(nextTrack));
				break;
			case -1:
				allTracks.get(currTrack).noFitId.add(playlist.get(nextTrack));
				break;
		}
	}
	//debug Main method
	public static void main(String[] args) {
		for (int i = 1; i <= 15; i++) {
			System.out.println("Iteration: "+i);
			System.out.println("Result: "+CMath.limitedRandom(10,20));
		}
	}
}
