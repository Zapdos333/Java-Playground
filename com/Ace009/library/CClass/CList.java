package com.Ace009.library.CClass;

import java.util.ArrayList;
/**
 * final 'static' class,
 * one of the 'C(ustom)Class' libaries,
 * this one works with <code>ArrayLists</code>
 * @author Ace009
 */
final public class CList {
	/**
	 * don't
	 * @see CList
	 */
	private CList() {}
	/**
	 * return a random entry from the list
	 * @param <T> the type of the lists entries
	 * @param list <code>ArrayList</code>
	 * @return <code>Type</code>: a random entry
	 */
	public static <T> T getRandom(ArrayList<T> list) {
		return list.get((int)(Math.random() * list.size()));
	}
}
