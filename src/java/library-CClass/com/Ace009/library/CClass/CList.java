package com.Ace009.library.CClass;

import java.util.List;
import java.util.stream.Stream;

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
	 * deduplicates the {@code list} by filtering out all entries that
	 * are more than once in the {@code List},
	 * <p> uses {@link Stream#distinct()}
	 * @param <T> the type of the lists entries
	 * @param list the list to deduplicate
	 * @return a new {@code List} with only one of each entry
	 */
	public static <T> List<T> deduplicate(List<T> list) {
		return list.stream().distinct().toList();
	}
}
