package com.Ace009.library.CClass;

import java.lang.reflect.Field;
import java.util.ArrayList;

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
	 * returns all findable fields of the class {@code clas},
	 * uses {@code Class.getDeclaredFields} and {@code Class.getFields}
	 * @param clas the class to scan
	 * @return {@code Field[]} containing all fields
	 */
	private static Field[] getAllFields(Class<?> clas) {
		Field[] declared = clas.getDeclaredFields();
		Field[] set = clas.getFields();
		ArrayList<Field> outputAr = new ArrayList<>();
		outputAr.ensureCapacity(Math.max(set.length, declared.length));
		for (Field field : set) {
			if (!outputAr.contains(field)) {
				outputAr.add(field);
			}
		}
		for (Field field : declared) {
			if (!outputAr.contains(field)) {
				outputAr.add(field);
			}
		}
		Field[] output = outputAr.toArray(new Field[outputAr.size()]);
		return output;

	}
	/**
	 * returns all property keys/names of the class of the {@code Object obj}
	 * @param obj Object to scan
	 * @return {@code String[]} containing the property names
	 */
	public static String[] keys(Object obj) {
		Class<?> clas = obj.getClass();
		Field[] fields = getAllFields(clas);
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
		Field[] fields = getAllFields(clas);
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
