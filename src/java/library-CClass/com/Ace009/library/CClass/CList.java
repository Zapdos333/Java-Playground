package com.Ace009.library.CClass;

import java.util.ArrayList;
import java.util.List;

/**
 * 'static' class,
 * one of the 'C(ustom)Class' libaries,
 * this one works with {@link List}
 * @author Ace009
 * @see java.util.List
 */
public class CList {
	/**
	 * don't
	 * @see CList
	 */
	private CList() {}
	/**
	 * returns a random entry from the list
	 * @param <T> the type of the lists entries
	 * @param list {@link List} of type {@code T}
	 * @return {@code Type}: a random entry from {@code list}
	 */
	public static <T> T getRandom(List<T> list) {
		return list.get((int)(Math.random() * list.size()));
	}
	/**
	 * deduplicates the {@code list} by adding all entries to a new {@code List}
	 * @param <T> the type of the entries
	 * @param list the list to deduplicate
	 * @return the deduplicated list
	 */
	public static <T> List<T> deduplicate(List<T> list) {
		ArrayList<T> output = new ArrayList<>(list.size());
		for (T e : list) { if (!output.contains(e)) output.add(e); }
		output.trimToSize();
		return output;
	}
	/**
	 * fills the {@code list} with {@code null} values,
	 * up to {@code size}
	 * @param <T> the type of the elements
	 * @param in the list to fill
	 * @param size the size of the returned list
	 * @return the filled list
	 */
	static <T> List<T> fillToLength(List<T> in, int size) {
		List<T> output = new ArrayList<T>(in);
		for (int i = output.size(); i < size; i++) {
			output.add(null);
		} assert(in.size() == size);
		return output;
	}
}
