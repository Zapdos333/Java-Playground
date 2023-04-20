package com.Ace009.HelloWorld;

import java.util.ArrayList;

class HelloWorldApp {
	public static void main(String[] args) {
		ArrayList<String> input=new ArrayList<String>();
		if (args.length<1) {input.add("You");}
		else {input.add(args[0]);}
		System.out.println("Hello "+input.get(0) +"!");
	}
}
