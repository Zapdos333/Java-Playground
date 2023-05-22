package com.Ace009.library.CoordinateSystem;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * {@code final class},
 * stores {@code x} and {@code y} values as {@code doubles}
 * @author Ace009
 */
public class DoubleCoordinate extends Coordinate {
	/**
	 * Returns the distance between all the coordinates in the list in the order they are in.
	 * @param list {@code ArrayList} of {@code Coordinates}
	 * @param polygon {@code boolean} value indicating if the list is a polygon or not
	 * @return {@code double}: distance
	 * @see Circle
	 */
	public static double totalDistance(ArrayList<DoubleCoordinate> list,boolean polygon) {
		double output=0;
		for (int i=1; i<list.size();i++) {
			output=+distance(list.get(i-1),list.get(i));
		}
		if (polygon) {
			output=+distance(list.get(list.size()-1),list.get(0));
		}
		return output;
	}
	/**
	 * returns a new {@code ArrayList} of {@code Coordinates}, with the given {@code Coodinates} rounded to the given {@code interval}
	 * @param list {@code ArrayList} of {@code Coordinates}
	 * @param interval {@code float} value for rounding interval
	 * @return {@code ArrayList} of {@code Coordinates} rounded to {@code interval}
	 */
	public static ArrayList<DoubleCoordinate> roundCoordList(ArrayList<DoubleCoordinate> list, float interval) {
		ArrayList<DoubleCoordinate> output=new ArrayList<>();
		int i=0;
		while (output.size()<list.size()) {
			final double iteration=interval*i;
			output.addAll(list.stream().filter(e->e.x<iteration).map(e->new DoubleCoordinate(Math.round(e.x/interval)*interval,Math.round(e.y/interval)*interval)).collect(Collectors.toList()));
			i++;
		}
		return output;
	}
	double x;
	double y;
	/**
	 * Simply stores {@code x} and {@code y} in the classes {@code doubles}
	 * @param f_x
	 * @param f_y
	 * @see Coordinate
	 */
	public DoubleCoordinate(double f_x, double f_y) {
		super();
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
	/**
	 * returns the distance to the target {@code Coordinate}
	 * @param target {@code Coordinate}
	 * @return {@code double}: distance
	 * @see #distance(Coordinate, Coordinate)
	 */
	public double distanceTo(DoubleCoordinate target) {
		return distance(this,target);
	}
	@Override
	public boolean equals(Object o) {
		if (o==this) {return true;}
		if (!(o instanceof DoubleCoordinate)) {return false;}
		DoubleCoordinate c = (DoubleCoordinate) o;
		return Double.compare(this.x,c.x)==0 && Double.compare(this.y,c.y)==0;
	};
}
