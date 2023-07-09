package com.Ace009.library.CClass;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
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
	 * gets all constructors of the given class
	 * <p>uses {@link Class#getDeclaredConstructors} and {@link Class#getConstructors}
	 * @param clas the class to scan
	 * @return an array of all {@code Constructor} of the given class
	 */
	public static Constructor<?>[] getConstructors(Class<?> clas) {
		List<Constructor<?>> constructorsD = CArray.asList(clas.getDeclaredConstructors());
		List<Constructor<?>> constructorsS = CArray.asList(clas.getConstructors());
		List<Constructor<?>> constructors = new ArrayList<>();
		constructors.addAll(constructorsD); constructors.addAll(constructorsS);
		constructors = CList.deduplicate(constructors);
		return constructors.toArray(Constructor<?>[]::new);
	}
	/**
	 * gets all methods of the given class
	 * <p>uses {@link Class#getDeclaredMethods} and {@link Class#getMethods}
	 * @param clas the class to scan
	 * @return an array of all {@code Method} of the given class
	 */
	public static Method[] getMethods(Class<?> clas) {
		List<Method> methodsD = CArray.asList(clas.getDeclaredMethods());
		List<Method> methodsS = CArray.asList(clas.getMethods());
		List<Method> methods = new ArrayList<>();
		methods.addAll(methodsD); methods.addAll(methodsS);
		methods = CList.deduplicate(methods);
		return methods.toArray(Method[]::new);
	}
	/**
	 * gets all fields of the given class
	 * <p>uses {@link Class#getDeclaredFields} and {@link Class#getFields}
	 * @param clas the class to scan
	 * @return an array of {@code Field} of the given class
	 */
	public static Field[] getFields(Class<?> clas) {
		List<Field> fieldsD = CArray.asList(clas.getDeclaredFields());
		List<Field> filedsS = CArray.asList(clas.getFields());
		List<Field> fields = new ArrayList<>();
		fields.addAll(fieldsD); fields.addAll(filedsS);
		fields = CList.deduplicate(fields);
		return fields.toArray(Field[]::new);
	}
	/**
	 * returns all fields keys/names of the class of the {@code Object obj}
	 * @param obj Object to scan
	 * @return {@code String[]} containing the fields names
	 * @see #getFields(Class)
	 */
	public static String[] fieldKeys(Object obj) {
		Class<?> clas = obj.getClass();
		Field[] fields = getFields(clas);
		String[] output = new String[fields.length];
		for (int i = 0; i < fields.length; i++) output[i]=fields[i].getName();
		return output;
	}
	/**
	 * returns all fields values of the {@code Object obj}
	 * @param obj Object to scan
	 * @return {@code Object[]} containing the fields values
	 * @see #getFields(Class)
	 */
	public static Object[] fieldValues(Object obj)  {
		Class<?> clas = obj.getClass();
		Field[] fields = getFields(clas);
		for (Field field : fields) field.setAccessible(true);
		Object[] output = new Object[fields.length];
		for (int i = 0; i < output.length; i++) {
			try { output[i] = fields[i].get(obj); }
			catch (IllegalAccessException e) { e.printStackTrace(); }
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
	 * depending on {@code isField} will return the result of the method or field with the provided {@code name}
	 * <p> args is only relevant for methods and object is practically irrelevant for static methods/fields
	 * @param object the object to run the method on / get the field from
	 * is ignored if the method/field is static
	 * @param name the {@code String} name of the field/method
	 * @param isField should name be applied to a field, if not a method
	 * @param args an Object array for the method, only relevant for {@code isField=false}
	 * @throws InvocationTargetException wrapped exception from {@link Method#invoke(Object, Object...)}
	 * @return the values of the field or the result of the method
	 */
	public static Object apply(Object object, String name, boolean isField, Object...args) throws InvocationTargetException {
		Class<?> clas = object.getClass();
		if (isField == false) {
			Method[] methods = getMethods(clas);
			Method method = CStreamOf.findFirst(methods, e->{ return e.getName()==name && e.getParameterCount() == (args==null?0:args.length);} );
			try { return method.invoke(object, args); } catch (IllegalAccessException e) { e.printStackTrace(); return null; }
		} else {
			Field[] fields = getFields(clas);
			Field field = CStreamOf.findFirst(fields, e->e.getName()==name);
			try { return field.get(object); } catch (IllegalAccessException e) { e.printStackTrace(); return null; }
		}
	}
}
