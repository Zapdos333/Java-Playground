package com.Ace009.library.CoordinateSystem;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * <code>final class</code>,
 * stores <code>x</code> and <code>y</code> values as <code>doubles</code>
 * @author Ace009
 */
final class Coordinate {
	/**
	* <code>final static class</code>, 
	* stores <code>x</code> and <code>y</code> values as <code>ints</code> 
	* @author Ace009
	* @see Coordinate
	*/
	final public static class Int{
		int x;int y;
		public Int(int f_x,int f_y) {
			x=f_x;y=f_y;
		}
	}
	/**
	 * returns the distance between the two coordinates
	 * @param pA <code>Coordinate</code> point A
	 * @param pB <code>Coordinate</code> point B
	 * @return <code>double</code>: distance
	 * @see #distanceTo(Coordinate)
	 */
	public static double distance(Coordinate pA, Coordinate pB) {
		double deltax = pA.x-pB.x; double deltay = pA.y-pB.y;
		return Math.hypot(deltax,deltay);
	}/**
	 * Returns the distance between all the coordinates in the list in the order they are in.
	 * @param list <code>ArrayList</code> of <code>Coordinates</code>
	 * @param polygon <code>boolean</code> value indicating if the list is a polygon or not
	 * @return <code>double</code>: distance
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
	 * returns a new <code>ArrayList</code> of <code>Coordinates</code>, with the given <code>Coodinates</code> rounded to the given <code>interval</code>
	 * @param list <code>ArrayList</code> of <code>Coordinates</code>
	 * @param interval <code>float</code> value for rounding interval
	 * @return <code>ArrayList</code> of <code>Coordinates</code> rounded to <code>interval</code>
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
	 * Simply stores <code>x</code> and <code>y</code> in the classes <code>doubles</code>
	 * @param f_x
	 * @param f_y
	 * @see Coordinate
	 */
	public Coordinate(double f_x, double f_y) {
		x=f_x;y=f_y;
	}
	/**
	 * returns the distance to the target <code>Coordinate</code>
	 * @param target <code>Coordinate</code>
	 * @return <code>double</code>: distance
	 * @see #distance(Coordinate, Coordinate)
	 */
	public double distanceTo(Coordinate target) {
		return distance(this,target);
	}
	/**
	 * returns a <code>String</code> representing the <code>Coordinate</code>,
	 * for example: "[x:1,y:2]"
	 * @return <code>String</code>: string representation of the <code>Coordinate</code>
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
