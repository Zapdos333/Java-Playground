package com.Ace009.library.CoordinateSystem;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * {@code final static class},
 * stores {@code x} and {@code y} values as {@code ints}
 * @author Ace009
 * @see Coordinate
 */

class IntCoordinate extends Coordinate{
	int x;int y;
	/**
	 * Simply stores {@code x} and {@code y} in the classes {@code ints}
	 * @param f_x
	 * @param f_y
	 */
	public IntCoordinate(int f_x,int f_y) {
		x=f_x;y=f_y;
	}
	/**
	 * converts the input {@code DoubleCoordinate} to a new {@code IntCoordinate}
	 * @param coord
	 */
	public IntCoordinate(DoubleCoordinate coord){
		int f_x=(int)Math.round(coord.x);
		int f_y=(int)Math.round(coord.y);
		x=f_x;y=f_y;
	}
	/**
	 * returns the distance between the two coordinates
	 * @param pA {@code Coordinate} point A
	 * @param pB {@code Coordinate} point B
	 * @return {@code double}: distance
	 * @see #distanceTo(Coordinate)
	 */
	public static double distance(IntCoordinate pA,IntCoordinate pB) {
		double deltax = pA.x-pB.x; double deltay = pA.y-pB.y;
		return Math.hypot(deltax,deltay);
	}
	public static ArrayList<IntCoordinate> roundCoordList(ArrayList<DoubleCoordinate> list) {
		ArrayList<IntCoordinate> output;
		output=new ArrayList<>(list.stream().map(e->new IntCoordinate(e)).collect(Collectors.toList()));
		return output;
	}
	/**
	 * Integer implementation of {@code Coordinate.distanceTo},
	 * also simply calls the {@code .distance(this,target)}
	 * @param target {@code CoordinateInt} target point
	 * @return {@code double}: distance to given points
	 * @see Coordinate#distanceTo(Coordinate)
	 * @see CoordinateInt#distance(CoordinateInt, CoordinateInt)
	 */
	public double distanceTo(IntCoordinate target) {
		return IntCoordinate.distance(this, target);
	}
	/**
	 * converts the {@code CoordinateInt} of {@code list} into {@code Coordinates}
	 * and returns the new {@code ArrayList}
	 * @param list {@code ArrayList} of {@code CoordinateInt}
	 * @return new {@code ArrayList} of {@code Coordinate}
	 */
	public static ArrayList<DoubleCoordinate> toCoordinates(ArrayList<IntCoordinate> list) {
		ArrayList<DoubleCoordinate> output;
		output=new ArrayList<>(list.stream().map(e->new DoubleCoordinate(e.x,e.y)).collect(Collectors.toList()));
		return output;
	}
	/**
	 * returns the {@code point} as new {@code Coordinate}
	 * with {@code int}-values cooerced to {@code double}
	 * @param point
	 * @return new {@code Coordinate}
	 */
	public static DoubleCoordinate toCoordinate(IntCoordinate point) {
		return new DoubleCoordinate(point.x,point.y);
	}
	/**
	 * returns {@code CoordinateInt.toCoordinate} on {@code this}
	 * @return new {@code Coordinate}
	 * @see CoordinateInt#toCoordinate(Coordinate)
	 */
	public DoubleCoordinate toCoordinate() {
		return IntCoordinate.toCoordinate(this);
	}
	@Override
	public boolean equals(Object o) {
		if (o==this) {return true;}
		if (!(o instanceof IntCoordinate)) {return false;}
		IntCoordinate c = (IntCoordinate) o;
		return Integer.compare(this.x,c.x)==0 && Integer.compare(this.y,c.y)==0;
	}
}
