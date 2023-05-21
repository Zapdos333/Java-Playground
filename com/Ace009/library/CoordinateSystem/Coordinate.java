package com.Ace009.library.CoordinateSystem;

/**
 * interface blueprint for Coordinate Types
 */
public interface Coordinate {
	/**
	 * returns the distance to the target {@code Coordinate}
	 * @param target {@code Coordinate}
	 * @return {@code double}: distance
	 * @see #distance(Coordinate, Coordinate)
	 */
	///double distanceTo(this target);
	/**
	 * returns a {@code String} representing the {@code Coordinate},
	 * for example: "[x:1,y:2]"
	 * @return {@code String}: string representation of the {@code Coordinate}
	 */
	String toString();
	boolean equals(Object o);
	int hashCode();
}
