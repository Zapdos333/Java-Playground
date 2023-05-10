package com.Ace009.library.CClass;

import java.util.ArrayList;

final public class CList {
	private CList() {}
	public static <Type> Type getRandom(ArrayList<Type> list) {
		return list.get((int)(Math.random() * list.size()));
	}
}
