package com.Ace009.library.CClass;

import java.util.ArrayList;

public class CList {
	public static <Type> Type getRandom(ArrayList<Type> list) {
		return list.get((int)(Math.random() * list.size()));
	}
}
