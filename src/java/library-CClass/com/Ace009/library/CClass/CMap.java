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
	public static void print(Map<String, Object> map, String keys, String values) {
		int keyLength=0; int valueLength=0;
		for (String key : map.keySet()) {
			if (key.length()>keyLength) keyLength = key.length();
		}
		for (Object value : map.values()) {
			if (value == null) value = "null";
			if (value.toString().length()>valueLength) valueLength=value.toString().length();
		}
		Map<String, String> output = new HashMap<>();
		final int finalKeyLength = keyLength; final int finalValueLength = valueLength;
		map.forEach((String key, Object value)->{
			if (value == null) value= "null";
			output.put(CString.formatToLength(key, finalKeyLength), CString.formatToLength(value.toString(), finalValueLength));
		});
		System.out.printf("|%s:%s|%s:%s|\n",keys,CString.formatToLength("", finalKeyLength-keys.length()),values,CString.formatToLength("", finalValueLength-values.length()));
		output.forEach((String key, String value)->{
			System.out.printf("|%s:|%s|\n", key, value);
		});
	}
	public static void print(Map<String, Object> map){ print(map,"Keys","Values"); }
	public static Map<String,Object> fromArray(String[] keys, Object[] values) {
		Map<String, Object> output = new HashMap<>();
		assert keys.length == values.length;
		for (int i = 0; i < keys.length; i++) {
			output.put(keys[i], values[i]);
		}
		return output;
	}
	public static Map<String, Object> fromArray(Object[][] entries) {
		Map<String, Object> output = new HashMap<>();
		for (Object[] entry : entries) {
			output.put(entry[0].toString(),entry[1]);
		}
		return output;
	}
}
