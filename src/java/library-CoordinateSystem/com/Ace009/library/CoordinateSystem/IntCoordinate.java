package com.Ace009.library.CoordinateSystem;

import java.util.Collection;

/**
 * stores {@code x} and {@code y} values as {@code ints}
 * @author Ace009
 * @see com.Ace009.library.CoordinateSystem.Coordinate
 */
public class IntCoordinate {
	/**
	 * Integer implementation of {@link Coordinate#distance(Coordinate,Coordinate)}
	 * @param pA {@code IntCoordinate} point A
	 * @param pB {@code IntCoordinate} point B
	 * @return {@code double}: distance between given points
	 */
	public static double distance(IntCoordinate pA,IntCoordinate pB) {
		int deltax = pA.x-pB.x; int deltay = pA.y-pB.y;
		return Math.hypot(deltax,deltay);
	}
	/**
	 * rounds the input {@code Collection<Coordinate>} to an {@code Collection<IntCoordinate>}
	 * @param list {@code Collection<Coordinate>} input list to round
	 * @return {@code Collection<IntCoordinate>} rounded list from input
	 */
	public static Collection<IntCoordinate> roundCoordList(Collection<Coordinate> list)
		{ return list.stream().map(e->new IntCoordinate(e)).toList(); }
	/**
	 * converts the {@code IntCoordinate} of {@code list} into {@code Coordinates}
	 * and returns the new {@code Collection}
	 * @param list {@code Collection} of {@code IntCoordinate}
	 * @return new {@code Collection} of {@code Coordinate}
	 */
	public static Collection<Coordinate> toCoordinates(Collection<IntCoordinate> list)
		{ return list.stream().map(e->e.toCoordinate()).toList(); }
	/**
	 * returns the {@code point} as new {@code Coordinate}
	 * with {@code int}-values cooerced to {@code double}
	 * @param point {@code IntCoordinate} to convert
	 * @return new {@code Coordinate}
	 */
	public static Coordinate toCoordinate(IntCoordinate point) { return new Coordinate(point.x,point.y); }
	/** x coordinate */
	public int x;
	/** y coordinate */
	public int y;
	/**
	 * Simply stores {@code x} and {@code y} in the classes {@code int}s
	 * @param x {@code int} x input
	 * @param y {@code int} y input
	 * @see IntCoordinate
	 */
	public IntCoordinate(int x,int y) { this.x=x;this.y=y; }
	/**
	 * creates a new {@code IntCoordinate} by
	 * rounding the {@code x} and {@code y} of a {@code Coordinate}
	 * @param coord {@code Coordinate} to round
	 */
	public IntCoordinate(Coordinate coord) { x=(int)Math.round(coord.x); y=(int)Math.round(coord.y); }
	/**
	 * Integer implementation of {@link com.Ace009.library.CoordinateSystem.Coordinate#distanceTo(Coordinate)},
	 * also simply calls the {@link IntCoordinate#distance(IntCoordinate,IntCoordinate)}
	 * @param target {@code IntCoordinate} target point
	 * @return {@code double}: distance to given points
	 * @see com.Ace009.library.CoordinateSystem.Coordinate#distanceTo(Coordinate)
	 */
	public double distanceTo(IntCoordinate target) { return IntCoordinate.distance(this, target); }
	/**
	 * returns {@link IntCoordinate#toCoordinate} on {@code this}
	 * @return new {@code Coordinate}
	 * @see IntCoordinate#toCoordinate(IntCoordinate)
	 */
	public Coordinate toCoordinate() { return IntCoordinate.toCoordinate(this); }
	/**
	 * returns a {@code String} representing the {@code IntCoordinate},
	 * for example: "[x:1,y:2]"
	 * @return {@code String}: string representation of the {@code IntCoordinate}
	 * @see com.Ace009.library.CoordinateSystem.Coordinate#toString()
	 */
	@Override
	public String toString() { return String.format("[x:%d, y:%d]", x, y); }
	@Override
	public boolean equals(Object o) {
		if (o==this) return true;
		if (!(o instanceof IntCoordinate)) return false;
		IntCoordinate c = (IntCoordinate) o;
		return Integer.compare(this.x,c.x)==0 && Integer.compare(this.y,c.y)==0;
	}
	@Override
	public int hashCode() {
		StringBuilder output = new StringBuilder();
		output.append(this.x); output.append(this.y);
		return Integer.parseInt(output.toString());
	}
}
