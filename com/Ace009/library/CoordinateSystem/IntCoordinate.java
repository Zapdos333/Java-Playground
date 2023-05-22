package com.Ace009.library.CoordinateSystem;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * {@code final static class},
 * stores {@code x} and {@code y} values as {@code ints}
 * @author Ace009
 * @see Coordinate
 */

class IntCoordinate implements Coordinate{
	int x;int y;
	public IntCoordinate(int f_x,int f_y) {
		x=f_x;y=f_y;
	}
	public IntCoordinate(DoubleCoordinate coord){
		x=(int)Math.round(coord.x);
		y=(int)Math.round(coord.y);
	}
	/**
	 * Integer implementation of {@code Coordinate.distance}
	 * @param pA {@code CoordinateInt} point A
	 * @param pB {@code CoordinateInt} point B
	 * @return {@code double}: distance between given points
	 * @see Coordinate#distance(Coordinate, Coordinate)
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
	public String toString() {
		return "[x:"+this.x+",y:"+this.y+"]";
	}
	@Override
	public boolean equals(Object o) {
		if (o==this) {return true;}
		if (!(o instanceof IntCoordinate)) {return false;}
		IntCoordinate c = (IntCoordinate) o;
		return Integer.compare(this.x,c.x)==0 && Integer.compare(this.y,c.y)==0;
	}
	@Override
	public int hashCode() {
		StringBuilder output = new StringBuilder();
		output.append(this.x);
		output.append(this.y);
		return Integer.parseInt(output.toString());
	}
}
