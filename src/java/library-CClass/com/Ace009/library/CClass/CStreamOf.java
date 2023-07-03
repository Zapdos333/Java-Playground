package com.Ace009.library.CClass;

import java.util.List;
import java.util.concurrent.CompletionException;

/**
 * 'static' class,
 * one of the 'C(ustom)Class' libaries,
 * this one replecates stream functionality for arrays
 * @author Ace009
 */
public class CStreamOf {
	/**
	 * maps the input array into a new array using the provided mapper
	 * @param <T> the type of input elements
	 * @param <R> the type of output elements
	 * @param array the initial array
	 * @param mapper the mapper
	 * @return the new mapped array
	 * @see java.util.stream.Stream#map(java.util.function.Function)
	 */
		@SuppressWarnings("unchecked")
	public static <T,R> R[] map(T[] array, java.util.function.Function<? super T,? extends R> mapper) {
		R[] output = (R[]) new Object[array.length];
		for (int i = 0; i < array.length; i++) { output[i] = mapper.apply(array[i]); }
		return output;
	}
	/**
	 * returns the first element which satisfies the given predicate,
	 * or null if none are found
	 * @param <T> the type of the input elements
	 * @param array the array to search in
	 * @param predicate the predicate to fulfill
	 * @return the first found element
	 */
	public static <T> T findFirst(T[] array, java.util.function.Predicate<T> predicate) {
		for (T e : array) {
			if (predicate.test(e)) { return e; }
		} return null;
	}
	/**
	 * maps the given array using {@link CObject#apply(Object, String, boolean, Object...)}
	 * and {@link CStreamOf#map(Object[], java.util.function.Function)}
	 * @param array the initial array to map
	 * @param key {@code name} for {@code CObject.apply()}
	 * @param isField {@code isField} for {@code CObject.apply()}
	 * @param args arguments for {@code CObject.apply()}
	 * @throws CompletionException if CObject the method run by {@code CObject.apply()} throws
	 * @return the result of the mapping
	 */
	public static Object[] map(Object[] array, String key, boolean isField, Object...args) {
		return map(array, e->{
			try {
				return CObject.apply(e,key,isField,args);
			}catch (Exception e1) {
				throw new CompletionException(e1);
			}
		});
	}
	/**
	 * deduplicates the given array using {@link CList#deduplicate(List)}
	 * and {@link CArray#asList(Object[])}
	 * @param <T> the type of the elements in the array
	 * @param array the array to deduplicate
	 * @return the deduplicated array
	 */
		@SuppressWarnings("unchecked")
	public static <T> T[] deduplicate(T[] array) {
		List<T> t_ = CList.deduplicate(CArray.asList(array));
		return (T[])t_.toArray();
	}
}
