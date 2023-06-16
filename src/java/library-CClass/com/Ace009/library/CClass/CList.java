package com.Ace009.library.CClass;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
/**
 * 'static' class,
 * one of the 'C(ustom)Class' libaries,
 * this one works with {@link ArrayList}
 * <p> tries it's best to work Interfaces as I/O
 * to work for more different {@link Collection}/{@link List}
 * @author Ace009
 * @see java.util.ArrayList
 * @see java.util.Collection
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
	 * @param list {@link ArrayList} of type {@code T}
	 * @return {@code Type}: a random entry from {@code list}
	 */
	public static <T> T getRandom(List<T> list) {
		return list.get((int)(Math.random() * list.size()));
	}
	/**
	 * deduplicates the {@code list} by adding the entries
	 * to a new {@code List} one by one and checking 
	 * that the new entry isn't already in the new {@code List},
	 * relies on {@code Object.equals}
	 * @param <T> the type of the lists entries
	 * @param list the list to deduplicate
	 * @return a new {@code List} with only one of each entry
	 */
	public static <T> List<T> deduplicate(Collection<T> list) {
		List<T> output = new ArrayList<>(list.size());
		for (T entry : list) {
			if (!output.contains(entry)) {
				output.add(entry);
			}
		}
		return output;
	}
}
