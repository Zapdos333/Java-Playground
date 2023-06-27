package com.Ace009.library.CoordinateSystem;

import java.util.Collection;
import java.util.List;

import com.Ace009.library.CClass.CString;
import com.Ace009.library.Math.Fraction;

/**
 * stores {@code x} and {@code y} values as {@link Fraction}
 * @author Ace009
 * @see com.Ace009.library.CoordinateSystem.Coordinate
 */
public class FractionCoordinate {
	/**
	 * parses a Coordinate with Fraction values,
	 * by seperating it at a {@code "|"} and {@link Fraction#parse(String)}
	 * @param in the String representing a {@code Coordinate}
	 * @return the parsed {@code FractionCoordinate}
	 */
	public static FractionCoordinate parse(String in) {
		String x,y;
		x= in.split("\\|")[0]; y= in.split("\\|")[1];
		return new FractionCoordinate(Fraction.parse(x),Fraction.parse(y));
	}
	/**
	 * Fraction implementation of {@link Coordinate#distance(Coordinate,Coordinate)}
	 * <p> potentially slightly inaccurate, because of the non-gurantee of an accurate square root,
	 * (uses {@link Fraction#inAccPow(double)} with 0.5 for square root)
	 * @param pA {@code FractionCoordinate} point A
	 * @param pB {@code FractionCoordinate} point B
	 * @return {@code Fraction}: distance between given points
	 */
	public static Fraction distance(final FractionCoordinate pA, final FractionCoordinate pB) {
		Fraction squarex = pA.x.subtract(pB.x).toPowerOf(2);
		Fraction squarey = pA.y.subtract(pB.y).toPowerOf(2);
		Fraction sum = squarex.add(squarey);
		return sum.inAccPow(0.5);
	}
	/**
	 * Returns the distance between all the coordinates in the list in the order they are in.
	 * @param list {@code ArrayList} of {@code FractionCoordinate}
	 * @param polygon {@code boolean} value indicating if the list is a polygon or not
	 * @return {@code Fraction}: distance
	 * @see Circle
	 */
	public static Fraction totalDistance(List<FractionCoordinate> list,boolean polygon) {
		Fraction output=new Fraction(0);
		for (int i=1; i<list.size();i++) output.add(distance(list.get(i-1),list.get(i)));
		if (polygon) output.add(distance(list.get(list.size()-1),list.get(0)));
		return output;
	}
	/**
	 * converts a list of {@code Coordinate} into a list of {@code FractionCoordinate},
	 * using {@link Fraction#Fraction()}
	 * @param list the list of {@code Coordinate}
	 * @return the list of {@code FractionCoordinate}
	 */
	public static Collection<FractionCoordinate> convertCoordList(Collection<Coordinate> list)
		{ return list.stream().map(e->new FractionCoordinate(new Fraction(e.x),new Fraction(e.y))).toList(); }
	/**
	 * converts the FractionCoordinate into a {@code Coordinate},
	 * by running {@link Fraction#calculate()} on both {@code x} and {@code y}
	 * @param c the {@code FractionCoordinate} to convert
	 * @return the {@code (Double)Coordinate}
	 */
	public static Coordinate toCoordinate(FractionCoordinate c)
		{ return new Coordinate(c.x.calculate(),c.y.calculate()); }
	/**
	 * converts the list of {@code FractionCoordinate} to a list of {@code Coordinate}
	 * using {@link #toCoordinate()}
	 * @param list the list of {@code FractionCoordinate} to convert
	 * @return the converted list of {@code Coordinate}
	 */
	public static Collection<Coordinate> toCoordinates(Collection<FractionCoordinate> list)
		{ return list.stream().map(e->e.toCoordinate()).toList(); }
	/** x coordinate */
	public Fraction x;
	/** y coordinate */
	public Fraction y;
	/**
	 * Simply stores {@code x} and {@code y} in the classes {@code doubles}
	 * @param x {@code double} x input
	 * @param y {@code double} y input
	 * @see Coordinate
	 */
	public FractionCoordinate(Fraction x, Fraction y) { this.x=x;this.y=y; }
	/**
	 * returns the distance to the target {@code Coordinate}
	 * @param target {@code Coordinate}
	 * @return {@code double}: distance
	 * @see #distance(FractionCoordinate, FractionCoordinate)
	 */
	public Fraction distanceTo(FractionCoordinate target) { return distance(this,target); }
	/**
	 * converts the FractionCoordinate into a {@code Coordinate},
	 * uses {@link #toCoordinate(FractionCoordinate)} with {@code this}
	 * @return the {@code (Double)Coordinate}
	 */
	public Coordinate toCoordinate() { return toCoordinate(this); }
	/**
	 * returns a {@code String} representing the {@code FractionCoordinate},
	 * for example: "[x:4/5,y:3/8]"
	 * @return {@code String}: string representation of the {@code Coordinate}
	 */
	@Override
	public String toString() { return String.format("[x:%s,y:%s]", x, y); }
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
		output.append(this.x); output.append(this.y);
		return CString.numericalSum(output.toString());
	}
}
