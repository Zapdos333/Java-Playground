package com.Ace009.library.CoordinateSystem;

import java.util.Collection;
import java.util.List;

import com.Ace009.library.Math.Fraction;

/**
 * {@code final class},
 * stores {@code x} and {@code y} values as {@code doubles}
 * @author Ace009
 */
public class FractionCoordinate {
	/**
	 * returns the distance between the two coordinates
	 * @param pA {@code Coordinate} point A
	 * @param pB {@code Coordinate} point B
	 * @return {@code double}: distance
	 * @see #distanceTo(Coordinate)
	 */
	public static double distance(final FractionCoordinate pA, final FractionCoordinate pB) {
		Fraction squarex = pA.x.subtract(pB.x).toPowerOf(2);
		Fraction squarey = pA.y.subtract(pB.y).toPowerOf(2);
		Fraction sum = squarex.add(squarey);
		//return sum.toPowerOf(0,5);
		return Math.sqrt(sum.calculate());
	}
	/**
	 * Returns the distance between all the coordinates in the list in the order they are in.
	 * @param list {@code ArrayList} of {@code Coordinates}
	 * @param polygon {@code boolean} value indicating if the list is a polygon or not
	 * @return {@code double}: distance
	 * @see Circle
	 */
	public static double totalDistance(List<FractionCoordinate> list,boolean polygon) {
		double output=0;
		for (int i=1; i<list.size();i++) output=+distance(list.get(i-1),list.get(i));
		if (polygon) output=+distance(list.get(list.size()-1),list.get(0));
		return output;
	}
	/**
	 * 
	 */
	public static Collection<FractionCoordinate> convertCoordList(Collection<Coordinate> list) {
		return list.stream().map(e->new FractionCoordinate(new Fraction(e.x),new Fraction(e.y))).toList();
	}
	/** x coordinate */
	public Fraction x;
	/** y coordinate */
	public Fraction y;
	/**
	 * Simply stores {@code x} and {@code y} in the classes {@code doubles}
	 * @param f_x {@code double} x input
	 * @param f_y {@code double} y input
	 * @see Coordinate
	 */
	public FractionCoordinate(Fraction x, Fraction y) {this.x=x;this.y=y;}
	/**
	 * returns the distance to the target {@code Coordinate}
	 * @param target {@code Coordinate}
	 * @return {@code double}: distance
	 * @see #distance(Coordinate, Coordinate)
	 */
	public double distanceTo(FractionCoordinate target) {
		return distance(this,target);
	}
	/**
	 * returns a {@code String} representing the {@code Coordinate},
	 * for example: "[x:1,y:2]"
	 * @return {@code String}: string representation of the {@code Coordinate}
	 */
	@Override
	public String toString() {
		return String.format("[x:%s,y:%s]", x, y);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (o==this) {return true;}
		if (!(o instanceof FractionCoordinate)) {return false;}
		FractionCoordinate c = (FractionCoordinate) o;
		return this.x.equals(c.x) && this.y.equals(c.y);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		StringBuilder output = new StringBuilder();
		output.append(this.x);
		output.append(this.y);
		return Integer.parseInt(output.toString());
	}
}
