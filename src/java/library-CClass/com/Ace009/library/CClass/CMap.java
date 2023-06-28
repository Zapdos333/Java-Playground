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
	public static String[] print(Map<String, Object> map, String keys, String values) {
		String[] output = new String[map.size()+1];
		int keyLength=0; int valueLength=0;
		for (Map.Entry<String,Object> e : map.entrySet()) {
			keyLength = Math.max(keyLength,e.getKey().length());
			valueLength = Math.max(valueLength,e.getValue().toString().length());
		}
		output[0] = String.format("|%s:%s|%s:%s|",
			keys,CString.formatToLength("", keyLength-keys.length()),
			values,CString.formatToLength("", valueLength-values.length()));
		int i = 1;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
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
	public static String[] print(Map<String, Object> map){ return print(map,"Keys","Values"); }
	/**
	 * Maps the input arrays as keys and values into the returned {@code Map}
	 * @param keys {@code String[]} the keys array
	 * @param values {@code Object[]} the values array
	 * @return the returned {@code Map}
	 */
	public static Map<String,Object> fromArray(String[] keys, Object[] values) {
		Map<String, Object> output = new HashMap<>();
		assert keys.length == values.length;
		for (int i = 0; i < keys.length; i++) {
			output.put(keys[i], values[i]);
		}
		return output;
	}
	/**
	 * maps the input array as keys and values into the returned {@code Map<String,Object>}
	 * <p>(uses keys {@link Object#toString()} for the String keys)
	 * @param entries an {@code Object[][]} array containing Touples of [key, value]
	 * @return the mapped input
	 */
	public static Map<String, Object> fromArray(Object[][] entries) {
		Map<String, Object> output = new HashMap<>();
		for (Object[] entry : entries) {
			output.put(entry[0].toString(),entry[1]);
		}
		return output;
	}
}
