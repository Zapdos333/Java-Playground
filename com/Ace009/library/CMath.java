package com.Ace009.library;

class CMath {
	public static int limitedRandom(int min, int max) {
		Double random=Math.random()*((max+1)-min)+min;
		return random.intValue();
	}
}
