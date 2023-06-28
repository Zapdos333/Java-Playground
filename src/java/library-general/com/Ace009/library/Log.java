package com.Ace009.library;

import java.io.PrintStream;

/**
 * a class for selective log output
 * @author Ace009
 */
public class Log {
	/** the default output PrintStream {@link System#out} */
	public static final PrintStream output = System.out;
	/** the Level of an output */
	public static enum Level {
		DEBUG((byte)0), INFO((byte)1), WARNING((byte)2), ERROR((byte)3), FATAL((byte)4);
		byte nr;
		private Level(byte nr) { this.nr=nr; }
	}
	/** the current global(static) configuration of which output gets printed */
	protected static Level outputLevel = Level.INFO;
	/** sets the global {@link #outputLevel output configuration} */
	public static void setOutputLevel(Level level) { Log.outputLevel = level; }
	/** @return the global {@link #outputLevel output configuration} */
	public static Level getOutputLevel() { return outputLevel; }
	/**
	 * prints the message into {@link #output},
	 * if {@code level} is larger or equal to {@link #outputLevel}
	 * <p> uses {@link PrintStream#println(String)}
	 */
	public static void out(Level level, String message) {
		if (level.nr >= Log.outputLevel.nr) Log.output.println(message);
	}
	/**
	 * prints the message into {@link #output},
	 * if {@code level} is larger or equal to {@link #outputLevel},
	 * <p>uses {@link PrintStream#printf(String, Object[])}
	 */
	public static void out(Level level, String format, Object...args) {
		if (level.nr >= Log.outputLevel.nr) Log.output.printf(format,args);
	}
}
