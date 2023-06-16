package com.Ace009.library.CClass;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

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
		List<Field> fields = new ArrayList<>();
		fields.addAll(fieldsD); fields.addAll(filedsS);
		fields = CList.deduplicate(fields);
		List<Method> methodsD = CArray.asList(clas.getDeclaredMethods());
		List<Method> methodsS = CArray.asList(clas.getMethods());
		List<Method> methods = new ArrayList<>();
		methods.addAll(methodsD); methods.addAll(methodsS);
		methods = CList.deduplicate(methods);
		List<Constructor<?>> constructorsD = CArray.asList(clas.getDeclaredConstructors());
		List<Constructor<?>> constructorsS = CArray.asList(clas.getConstructors());
		List<Constructor<?>> constructors = new ArrayList<>();
		constructors.addAll(constructorsD); constructors.addAll(constructorsS);
		constructors = CList.deduplicate(constructors);
		Map<String, Member[]> output = new HashMap<String, Member[]>();
		output.put("fields",fields.toArray(new Member[0]));
		output.put("methods",methods.toArray(new Member[0]));
		output.put("constructors",constructors.toArray(new Member[0]));
		return output;
	}
	/**
	 * returns all fields keys/names of the class of the {@code Object obj}
	 * @param obj Object to scan
	 * @return {@code String[]} containing the fields names
	 * @see #getAll(Class)
	 */
	public static String[] fieldKeys(Object obj) {
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
	 * returns all fields values of the {@code Object obj}
	 * @param obj Object to scan
	 * @return {@code Object[]} containing the fields values
	 * @see #getAll(Class)
	 */
	public static Object[] fieldValues(Object obj)  {
		Class<?> clas = obj.getClass();
		Field[] fields = (Field[]) getAll(clas).get("fields");
		Object[] output = new Object[fields.length];
		for (int i=0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try { output[i]=fields[i].get(obj); } catch (IllegalAccessException e) { e.printStackTrace(); }
		}
		return output;
	}
	/**
	 * returns all fields of the {@code Object obj}
	 * ,<p> returns an {@code Object[][]} in the format of
	 * {@code { {key1, value1}, {key2, value2}, ... } }}
	 * @param obj Object to scan
	 * @return {@code Object[][]} containing the fields
	 * @see CObject#fieldValues(Object)
	 * @see CObject#fieldKeys(Object)
	 */
	public static Object[][] fieldEntries(Object obj)  {
		String[] keys = fieldKeys(obj);
		Object[] values = fieldValues(obj);
		assert keys.length == values.length;
		Object[][] output = new Object[keys.length][2];
		for (int i=0; i < keys.length; i++) {
			output[i][0] = keys[i];
			output[i][1] = values[i];
		}
		return output;
	}
	/**
	 * returns the output of {@link #fieldEntries(Object)} as a {@code Map}
	 * @param obj Object to scan
	 * @return a {@code Map} containing the entries of the {@code Object}
	 */
	public static Map<String, Object> fieldEntriesMap(Object obj)  {
		return CMap.fromArray(fieldEntries(obj));
	}
	/**
	 * compares the objects by {@code Class} and {@code entries} by properties
	 * <p> if any property is different between the objects,
	 * it returns {@code false}
	 * @param a {@code Object} a
	 * @param b {@code Object} b
	 * @return {@code true} if all properties are equal
	 * @see CObject#fieldEntries(Object)
	 */
	public static boolean equals(Object a, Object b)  {
		Object[][] entriesA = fieldEntries(a);
		Object[][] entriesB = fieldEntries(b);
		if (entriesA.length != entriesB.length) {return false;}
		for (int i=0; i<entriesA.length; i++) {
			if (entriesA[i][0] != entriesB[i][0]) {return false;}
			if (entriesA[i][1] != entriesB[i][1]) {return false;}
		}
		return true;
	}
	/**
	 * returns an array containing the result of the with {@code key} specified field of method,
	 * returns an array containing {@code null} if no such field or method exists
	 * <p>
	 * does run the specified method on all the objects, priortieses fields over methods
	 * <p>
	 * replacable with {@code Stream.of(entries).map(e->e.key(args)).toArray()}
	 * @param entries the Objects to use
	 * @param key the {@code string} name of the field of method 
	 * @param args the arguments for a method-call, can be {@code null}
	 * @return an {@code Object[]} containing the results
	 * @throws InvocationTargetException because of {@link Method#invoke(Object, Object[])}
	 */
	public static Object[] results (Object[] entries, String key, Object[] args) throws InvocationTargetException {
		Class<?> clas = entries.getClass().getComponentType();
		assert clas != null;
		Field[] fields = (Field[]) getAll(clas).get("fields");
		Method[] methods = (Method[]) getAll(clas).get("methods");
		fields = (Field[]) Stream.of(fields).filter(e->e.getName()==key).toArray();
		methods = (Method[]) Stream.of(methods).filter(e->e.getName()==key).toArray();
		Object[] output = new Object[entries.length];
		if (fields.length != 0) {
			fields[0].setAccessible(true);
			for (int i=0; i<entries.length; i++) {
				try { output[i] = fields[0].get(entries[i]); } catch (Exception e) { e.printStackTrace(); }
			}
		} else if (methods.length != 0) {
			methods[0].setAccessible(true);
			assert methods[0].getParameterCount() == 0;
			for (int i = 0; i < entries.length; i++) {
				try { output[i] = methods[i].invoke(entries[i], args); } catch (IllegalAccessException e) { e.printStackTrace(); }
			}
		}
		return output;
	}
}
