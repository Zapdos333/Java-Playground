package com.Ace009.library.CClass;

/**
 * final 'static' class,
 * one of the 'C(ustom)Class' libaries,
 * this one works with simple mathematics
 * @author Ace009
 */
final public class CMath {
	/**
	 * don't
	 * @see CMath
	 */
	private CMath() {}
	/**
	 * returns a random integer between min and max
	 * @param min
	 * @param max
	 * @return {@code int}: random, between min and max
	 */
	public static int limitedRandom(int min, int max) {
		Double random=Math.random()*((max+1)-min)+min;
		return random.intValue();
	}
}
