package com.Ace009.library;

import java.io.PrintStream;
import java.util.Calendar;

/**
 * a class for selective log output
 * @author Ace009
 */
public class Log {
	/** the default output PrintStream {@link System#out} */
	public static final PrintStream OUT = System.out;
	/** the default output PrintStream {@link System#err} */
	public static final PrintStream ERROR = System.err;
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
	 * prints the message into {@link #OUT} or {@link #ERROR},
	 * if {@code level} is larger or equal to {@link #outputLevel}
	 * <p> uses {@link PrintStream#println(String)}
	 * <p> uses the format {@code [hour:minute:second]:[level]:message}
	 * @param level the output level of the message
	 * @param message the message
	 */
	public static void out(Level level, String message) {
		Calendar time = Calendar.getInstance();
		String t_ = String.format("[%d:%d:%d]:[%s]:%s",
			time.get(Calendar.HOUR_OF_DAY),time.get(Calendar.MINUTE),time.get(Calendar.SECOND),
			level.name(),message
		);
		if (level.nr >= Log.outputLevel.nr) {
			if (level.nr < Log.Level.ERROR.nr) Log.OUT.println(t_);
			else Log.ERROR.println(t_);
		}
	}
	/**
	 * prints the message into {@link #OUT} or {@link #ERROR},
	 * if {@code level} is larger or equal to {@link #outputLevel},
	 * <p> relays the format and args into {@link String#format(String, Object[]) String.format(String, Object...)},
	 * and then into {@link #out(Level,String)}
	 * @param level the output level of the message
	 * @param format the message according to {@code String.format(String, Object[])}
	 * @param args for the formatting
	 */
	public static void out(Level level, String format, Object...args) {
		Log.out(level,String.format(format, args));
	}
}
