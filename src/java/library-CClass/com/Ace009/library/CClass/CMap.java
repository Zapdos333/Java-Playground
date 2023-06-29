package com.Ace009.library.CClass;

import java.util.Map;
import java.util.HashMap;

/**
 * 'static' class,
 * one of the 'C(ustom)Class' libaries,
 * this one works with Maps
 * @author Ace009
 */
public class CMap {
	/** don't */
	private CMap() {}
	/**
	 * Prints the map into the {@link System#out} output stream,
	 * <p> prints in a table format with {@code keys} and {@code values} as headings
	 * and seperates the keys and values with "|" on sides.
	 * @param map the map to print
	 * @param keys the keys heading
	 * @param values the values heading
	 * @return a {@code String[]} containing the printable output seperated in lines
	 * @see CString#formatToLength(String, int)
	 */
	public static String[] print(Map<String, ?> map, String keys, String values) {
		String[] output = new String[map.size()+1];
		int keyLength=0; int valueLength=0;
		for (Map.Entry<String,?> e : map.entrySet()) {
			keyLength = Math.max(keyLength,e.getKey().length());
			valueLength = Math.max(valueLength,e.getValue().toString().length());
		}
		output[0] = String.format("|%s:%s|%s:%s|",
			keys,CString.formatToLength("", keyLength-keys.length()),
			values,CString.formatToLength("", valueLength-values.length()));
		int i = 1;
		for (Map.Entry<String, ?> entry : map.entrySet()) {
			output[i] = String.format("|%s:|%s|",
				CString.formatToLength(entry.getKey(),keyLength),
				CString.formatToLength(entry.getValue().toString(),valueLength)
			);
			i++;
		};
		return output;
	}
	/**
	 * default implementation of {@link #print(Map, String, String)} method,
	 * with "Keys" and "Values" as default headings
	 * @return a {@code String[]} containing the printable output seperated in lines
	 * @param map the map to print
	 */
	public static String[] print(Map<String, ?> map){ return print(map,"Keys","Values"); }
	/**
	 * Maps the input arrays as keys and values into the returned {@code Map}
	 * @param <K> {@code <K>} the type of the keys
	 * @param <V> {@code <V>} the type of the values
	 * @param keys {@code K[]} the keys array
	 * @param values {@code V[]} the values array
	 * @return the returned {@code Map}
	 */
	public static <K,V> Map<K,V> fromArray(K[] keys, V[] values) {
		Map<K,V> output = new HashMap<>();
		assert keys.length == values.length;
		for (int i = 0; i < keys.length; i++) {
			output.put(keys[i], values[i]);
		}
		return output;
	}
	/**
	 * maps the input array as keys and values into the returned {@code Map<K, V>}
	 * <p> uses the first entry of each subarray as key and the second entry as value
	 * @param <T> {@code <T>} the type of the input array
	 * @param <K> {@code <K>} the type of the keys, must extend {@code <T>}
	 * @param <V> {@code <V>} the type of the values, must extend {@code <T>}
	 * @param entries an {@code T[][]} array containing Touples of [key, value]
	 * @return a Map of the input
	 */
	@SuppressWarnings("unchecked") // for K, V type casting
	public static <K extends T, V extends T, T> Map<K, V> fromArray(T[][] entries) {
		Map<K, V> output = new HashMap<>();
		for (T[] entry : entries) {
			output.put((K)entry[0],(V)entry[1]);
		}
		return output;
	}
}
