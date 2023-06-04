package com.Ace009.library.CClass;

import java.util.ArrayList;
import java.util.Collection;
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
	/**
	 * returns an {@code ArrayList} with all entries
	 * from the given {@code Collection},
	 * contains no duplicates
	 * @param <T> the type of the lists entries
	 * @param allLists the array of all {@code Collection}
	 * @return an {@code ArrayList} with all entries
	 */
	@SuppressWarnings("unchecked")//to allow {@code Collection<T>[]}
	public static <T> ArrayList<T> merge(Collection<T> ... allLists) {
		ArrayList<T> output = new ArrayList<>();
		for (Collection<T> list : allLists) {
			output.ensureCapacity(output.size()+list.size());
			for (T entry : list) {
				if (!output.contains(entry)) {
					output.add(entry);
				}
			}
			output.trimToSize();
		}
		return output;
	}
	/**
	 * deduplicates the {@code list} by adding the entries
	 * to a new {@code ArrayList} one by one and checking 
	 * that the new entrie isn't already in the new {@code ArrayList},
	 * relies on {@code Object.equals}
	 * @param <T> the type of the lists entries
	 * @param list the list to deduplicate
	 * @return a new {@code ArrayList} with only one of each entry
	 */
	public static <T> ArrayList<T> deduplicate(ArrayList<T> list) {
		ArrayList<T> output = new ArrayList<>(list.size());
		for (T entry : list) {
			if (!output.contains(entry)) {
				output.add(entry);
			}
		}
		return output;
	}
}
