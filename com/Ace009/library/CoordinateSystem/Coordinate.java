package com.Ace009.library.CoordinateSystem;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * {@code final class},
 * stores {@code x} and {@code y} values as {@code doubles}
 * @author Ace009
 */
public class Coordinate {
	/**
	* {@code final static class},
	* stores {@code x} and {@code y} values as {@code ints}
	* @author Ace009
	* @see Coordinate
	*/
	public static class Int{
		int x;int y;
		public Int(int f_x,int f_y) {
			x=f_x;y=f_y;
		}
		/**
		 * Integer implementation of {@code Coordinate.distance}
		 * @param pA {@code Coordinate.Int} point A
		 * @param pB {@code Coordinate.Int} point B
		 * @return {@code double}: distance between given points
		 * @see Coordinate#distance(Coordinate, Coordinate)
		 */
		public static double distance(Coordinate.Int pA,Coordinate.Int pB) {
			double deltax = pA.x-pB.x; double deltay = pA.y-pB.y;
			return Math.hypot(deltax,deltay);
		}
		public static ArrayList<Coordinate.Int> roundCoordList(ArrayList<Coordinate> list) {
			ArrayList<Coordinate.Int> output;
			output=new ArrayList<>(list.stream().map(e->new Coordinate.Int((int)Math.round(e.x),(int)Math.round(e.y))).collect(Collectors.toList()));
			return output;
		}
		/**
		 * Integer implementation of {@code Coordinate.distanceTo},
		 * also simply calls the {@code .distance(this,target)}
		 * @param target {@code Coordinate.Int} target point
		 * @return {@code double}: distance to given points
		 * @see Coordinate#distanceTo(Coordinate)
		 * @see Coordinate.Int#distance(Coordinate.Int, Coordinate.Int)
		 */
		public double distanceTo(Coordinate.Int target) {
			return Coordinate.Int.distance(this, target);
		}
		/**
		 * converts the {@code Coordinate.Int} of {@code list} into {@code Coordinates}
		 * and returns the new {@code ArrayList}
		 * @param list {@code ArrayList} of {@code Coordinate.Int}
		 * @return new {@code ArrayList} of {@code Coordinate}
		 */
		public static ArrayList<Coordinate> toCoordinates(ArrayList<Coordinate.Int> list) {
			ArrayList<Coordinate> output;
			output=new ArrayList<>(list.stream().map(e->new Coordinate((double)e.x, (double)e.y)).collect(Collectors.toList()));
			return output;
		}
		/**
		 * returns the {@code point} as new {@code Coordinate}
		 * with {@code int}-values cooerced to {@code double}
		 * @param point
		 * @return new {@code Coordinate}
		 */
		public static Coordinate toCoordinate(Coordinate.Int point) {
			return new Coordinate((double)point.x,(double)point.y);
		}
		/**
		 * returns {@code Coordinate.Int.toCoordinate} on {@code this}
		 * @return new {@code Coordinate}
		 * @see Coordinate.Int#toCoordinate(Coordinate)
		 */
		public Coordinate toCoordinate() {
			return Coordinate.Int.toCoordinate(this);
		}
		/**
		 * Integer implementation of {@code Coordinate.toString()},
		 * @return {@code String}: string representation of the {@code Coordinate}
		 * @see Coordinate#toString()
		 */
		@Override
		public String toString() {
			return "[x:"+this.x+",y:"+this.y+"]";
		}
		@Override
		public boolean equals(Object o) {
			if (o==this) {return true;}
			if (!(o instanceof Coordinate.Int)) {return false;}
			Coordinate.Int c = (Coordinate.Int) o;
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
	public static double totalDistance(ArrayList<Coordinate> list,boolean polygon) {
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
	public static ArrayList<Coordinate> roundCoordList(ArrayList<Coordinate> list, float interval) {
		ArrayList<Coordinate> output=new ArrayList<>();
		int i=0;
		while (output.size()<list.size()) {
			final double iteration=interval*i;
			output.addAll(list.stream().filter(e->e.x<iteration).map(e->new Coordinate(Math.round(e.x/interval)*interval,Math.round(e.y/interval)*interval)).collect(Collectors.toList()));
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
