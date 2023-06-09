package com.Ace009.library.CoordinateSystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code final class},
 * stores {@code x} and {@code y} values as {@code doubles}
 * @author Ace009
 */
public class Coordinate {
	/**
	 * returns the distance between the two coordinates
	 * @param pA {@code Coordinate} point A
	 * @param pB {@code Coordinate} point B
	 * @return {@code double}: distance
	 * @see #distanceTo(Coordinate)
	 */
	public static double distance(Coordinate pA, Coordinate pB) {
		double deltax = pA.x-pB.x; double deltay = pA.y-pB.y;
		return Math.hypot(deltax,deltay);
	}
	/**
	 * Returns the distance between all the coordinates in the list in the order they are in.
	 * @param list {@code ArrayList} of {@code Coordinates}
	 * @param polygon {@code boolean} value indicating if the list is a polygon or not
	 * @return {@code double}: distance
	 * @see Circle
	 */
	public static double totalDistance(List<Coordinate> list,boolean polygon) {
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
	public static Collection<Coordinate> roundCoordList(Collection<Coordinate> list, float interval) {
		ArrayList<Coordinate> output=new ArrayList<>();
		int i=0;
		while (output.size()<list.size()) {
			final double iteration=interval*i;
			output.addAll(list.stream().filter(e->e.x<iteration).map(e->new Coordinate(Math.round(e.x/interval)*interval,Math.round(e.y/interval)*interval)).collect(Collectors.toList()));
			i++;
		}
		return output;
	}
	/** x coordinate */
	public double x;
	/** y coordinate */
	public double y;
	/**
	 * Simply stores {@code x} and {@code y} in the classes {@code doubles}
	 * @param f_x {@code double} x input
	 * @param f_y {@code double} y input
	 * @see Coordinate
	 */
	public Coordinate(double f_x, double f_y) {
		x=f_x;y=f_y;
	}
	/**
	 * returns the distance to the target {@code Coordinate}
	 * @param target {@code Coordinate}
	 * @return {@code double}: distance
	 * @see #distance(Coordinate, Coordinate)
	 */
	public double distanceTo(Coordinate target) {
		return distance(this,target);
	}
	/**
	 * returns a {@code String} representing the {@code Coordinate},
	 * for example: "[x:1,y:2]"
	 * @return {@code String}: string representation of the {@code Coordinate}
	 */
	@Override
	public String toString() {
		return "[x:"+this.x+",y:"+this.y+"]";
	}
	//TODO: javadoc comment from super implementation ?
	@Override
	public boolean equals(Object o) {
		if (o==this) {return true;}
		if (!(o instanceof Coordinate)) {return false;}
		Coordinate c = (Coordinate) o;
		return Double.compare(this.x,c.x)==0 && Double.compare(this.y,c.y)==0;
	}
	@Override
	public int hashCode() {
		StringBuilder output = new StringBuilder();
		output.append(this.x);
		output.append(this.y);
		return Integer.parseInt(output.toString());
	}
}
