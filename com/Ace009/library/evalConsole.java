package com.Ace009.library;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.script.ScriptEngine;
//import org.mozilla.javascript.engine.RhinoScriptEngine;
import org.mozilla.javascript.engine.RhinoScriptEngineFactory;

/**
 * a class defining a console 
 * @author Ace009
 */
public class evalConsole {
	/**
	 * prints out all available script engines,
	 * currently usning RhinoScriptEngine,
	 * script copied from
	 * <a href="https://www.oracle.com/technical-resources/articles/javase/scripting.html">here</a>
	 */
	public static void check() {
		RhinoScriptEngineFactory factory=new RhinoScriptEngineFactory();
		//for (RhinoScriptEngineFactory factory: factories) {
			System.out.println("ScriptEngineFactory Info");
			String engName = factory.getEngineName();
			String engVersion = factory.getEngineVersion();
			String langName = factory.getLanguageName();
			String langVersion = factory.getLanguageVersion();
			System.out.printf("\tScript Engine: %s (%s)\n", 
					engName, engVersion);
			List<String> engNames = factory.getNames();
			for(String name: engNames) {
				System.out.printf("\tEngine Alias: %s\n", name);
			}
			System.out.printf("\tLanguage: %s (%s)\n", 
					langName, langVersion);
		//}
	}
	public static void main(String[] args) {
		if (args.length>0 && (Arrays.toString(args).contains("info"))) {
			check();
			System.out.println("");
		}
		Scanner scanner = new Scanner(System.in);
		String input = new String();
		RhinoScriptEngineFactory factory = new RhinoScriptEngineFactory();
		ScriptEngine engine = factory.getScriptEngine();
		while (true) {
			System.out.print("Console input: ");
			input=scanner.nextLine();
			try {
				Object result = engine.eval(input);
				System.out.println("Returns: "+result.toString()+"\n");
			} catch (Exception e) {
				System.out.println("");
				e.printStackTrace();
			}
		}
		//scanner.close();
	}
}
