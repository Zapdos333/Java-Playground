package com.Ace009.library.CClass;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * 'static' class,
 * one of the 'C(ustom)Class' libaries,
 * this one works with Objects
 * <p>(uses/imports the java.lang.reflect.Field )
 * @author Ace009
 */
public class CObject {
	/**
	 * don't
	 * @see CObject
	 */
	private CObject() {}
	/**
	 * returns all {@code Member}s of the {@code Class} {@code clas},
	 * <p>
	 * uses {@link Class#getDeclaredFields} and {@link Class#getFields} for {@link Map#get(Object) Map.get("fields")}, 
	 * uses {@link Class#getDeclaredMethods} and {@link Class#getMethods} for {@link Map#get(Object) Map.get("methods")}, 
	 * uses {@link Class#getDeclaredConstructors} and {@link Class#getConstructors} for {@link Map#get(Object) Map.get("constructors")}, 
	 * @param clas the class to scan
	 * @return {@code Map<String, Member[]>} containing all members with an associated {@code String} key
	 */
	public static Map<String, Member[]> getAll(Class<?> clas) {
		List<Field> fieldsD = CArray.asList(clas.getDeclaredFields());
		List<Field> filedsS = CArray.asList(clas.getFields());
		List<Field> fields = new ArrayList<Field>();
		fields.addAll(fieldsD); fields.addAll(filedsS);
		fields = CList.deduplicate(fields);
		List<Executable> methodsD = CArray.asList(clas.getDeclaredMethods());
		List<Executable> methodsS = CArray.asList(clas.getMethods());
		List<Executable> methods = new ArrayList<Executable>();
		methods.addAll(methodsD); methods.addAll(methodsS);
		methods = CList.deduplicate(methods);
		List<Executable> constructorsD = CArray.asList(clas.getDeclaredConstructors());
		List<Executable> constructorsS = CArray.asList(clas.getConstructors());
		List<Executable> constructors = new ArrayList<Executable>();
		constructors.addAll(constructorsD); constructors.addAll(constructorsS);
		constructors = CList.deduplicate(constructors);
		Map<String, Member[]> output = new HashMap<String, Member[]>();
		output.put("fields",fields.toArray(new Member[0]));
		output.put("methods",methods.toArray(new Member[0]));
		output.put("constructors",constructors.toArray(new Member[0]));
		return output;
	}
	/**
	 * returns all property keys/names of the class of the {@code Object obj}
	 * @param obj Object to scan
	 * @return {@code String[]} containing the property names
	 */
	public static String[] keys(Object obj) {
		Class<?> clas = obj.getClass();
		Field[] fields = (Field[]) getAll(clas).get("fields");
		String[] output = new String[fields.length];
		for (int i=0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			output[i]=fields[i].getName();
		}
		return output;
	}
	/**
	 * returns all property values of the {@code Object obj}
	 * @param obj Object to scan
	 * @return {@code Object[]} containing the properties values
	 * @throws IllegalAccessException because of {@link Field#get(Object)}
	 */
	public static Object[] values(Object obj) throws IllegalAccessException {
		Class<?> clas = obj.getClass();
		Field[] fields = (Field[]) getAll(clas).get("fields");
		Object[] output = new Object[fields.length];
		for (int i=0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			output[i]=fields[i].get(obj);
		}
		return output;
	}
	/**
	 * returns all properties of the {@code Object obj}
	 * ,<p> returns an {@code Object[][]} in the format of
	 * {@code { {key1, value1}, {key2, value2}, ... } }}
	 * @param obj Object to scan
	 * @return {@code Object[][]} containing the properties
	 * @throws IllegalAccessException because of {@link CObject#values(Object)}
	 * @see CObject#values(Object)
	 * @see CObject#keys(Object)
	 */
	public static Object[][] entries(Object obj) throws IllegalAccessException {
		String[] keys = keys(obj);
		Object[] values = values(obj);
		assert keys.length == values.length;
		Object[][] output = new Object[keys.length][2];
		for (int i=0; i < keys.length; i++) {
			output[i][0] = keys[i];
			output[i][1] = values[i];
		}
		return output;
	}
	/**
	 * returns the output of {@link #entries(Object)} as a {@code Map}
	 * @param obj Object to scan
	 * @return a {@code Map} containing the entries of the {@code Object}
	 * @throws IllegalAccessException because of {@link CObject#entries(Object)}
	 */
	public static Map<String, Object> entriesMap(Object obj) throws IllegalAccessException {
		return CMap.fromArray(entries(obj));
	}
	/**
	 * compares the objects by {@code Class} and {@code entries} by properties
	 * <p> if any property is different between the objects,
	 * it returns {@code false}
	 * @param a {@code Object} a
	 * @param b {@code Object} b
	 * @return {@code true} if all properties are equal
	 * @throws IllegalAccessException because of {@link CObject#entries(Object)}
	 * @see CObject#entries(Object)
	 */
	public static boolean equals(Object a, Object b) throws IllegalAccessException {
		Object[][] entriesA = entries(a);
		Object[][] entriesB = entries(b);
		if (entriesA.length != entriesB.length) {return false;}
		for (int i=0; i<entriesA.length; i++) {
			if (entriesA[i][0] != entriesB[i][0]) {return false;}
			if (entriesA[i][1] != entriesB[i][1]) {return false;}
		}
		return true;
	}
}
