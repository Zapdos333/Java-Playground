package App.AudioPlayer;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class AppAudioPlayer {
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
	//function for RNG
	public static int limitedRandom(int min, int max) {
		Random rng=new Random();
		return rng.nextInt(max - min + 1) + min;
	}
	//debug Main function
	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			System.out.println(i);
			System.out.println(limitedRandom(0,10));
		}
	}
}
