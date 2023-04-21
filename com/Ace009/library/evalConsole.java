//requires Overhaul and external ScriptEngines, currently only checks for all available ScriptEngines

package com.Ace009.library;

import java.util.Scanner;
import java.util.Arrays;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngineFactory;

import java.util.ArrayList;
import java.util.List;

public class evalConsole {
	public static void main(String[] args) {
		if (args.length>0) {
			System.out.println("Arguments: "+Arrays.toString(args));
		}
		Scanner scanner = new Scanner(System.in);
		String input = new String();
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		/*
		while (true) {
			System.out.print("Console input: ");
			input=scanner.nextLine();
			try {
				Object result = engine.eval(input);
				System.out.println("Returns: "+result.toString());
			} catch (Exception e) {
				System.out.println("");
				e.printStackTrace();
			}
			System.out.println("");
			scanner.close();
		} */
		List<ScriptEngineFactory> factoriesList = manager.getEngineFactories();
		ArrayList<List<String>> factorieNames = new ArrayList<List<String>>();
		for (int i=0; i<factoriesList.size(); i++) {
			factorieNames.set(i,factoriesList.get(i).getNames());
		}
		System.out.println(factorieNames.toString());
		System.out.println(engine != null);
	}
}
