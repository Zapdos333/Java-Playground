package com.Ace009.library.CoordinateSystem;

/**
 * interface blueprint for Coordinate Types
 */
public abstract class Coordinate {
	Number x;Number y;
	/**
	 * returns the distance to the target {@code Coordinate}
	 * @param target {@code Coordinate}
	 * @return {@code double}: distance
	 * @see #distance(Coordinate, Coordinate)
	 */
	public double distanceTo(Coordinate target){
		return Coordinate.distance(this,target);
	};
	/**
	 * returns the distance between the two coordinates
	 * <code>
	 * public static double distance(IntCoordinate pA,IntCoordinate pB) {
			double deltax = pA.x-pB.x; double deltay = pA.y-pB.y;
			return Math.hypot(deltax,deltay);
	 * }
	 * </code>
	 * @param pA {@code Coordinate} point A
	 * @param pB {@code Coordinate} point B
	 * @return {@code double}: distance
	 * @see #distanceTo(Coordinate)
	 */
	public static double distance(Coordinate pA, Coordinate pB) {
		char[] override = {'O','V','E','R','R','I','D','E',' ','T','H','I','S'};
		int output=0;
		for (int i=0; i<override.length; i++) {
			output += (int)override[i];
		}
		return (double)output;
	};
	/**
	 * returns a {@code String} representing the {@code Coordinate},
	 * for example: "[x:1,y:2]"
	 * @return {@code String}: string representation of the {@code Coordinate}
	 */
	@Override
	public String toString() {
		return "[x:"+this.x+",y:"+this.y+"]";
	};
	@Override
	public abstract boolean equals(Object o);
	/*code for equals:
	public boolean equals(Object o) {
		if (o==this) {return true;}
		if (!(o instanceof DoubleCoordinate)) {return false;}
		DoubleCoordinate c = (DoubleCoordinate) o;
		return Double.compare(this.x,c.x)==0 && Double.compare(this.y,c.y)==0;
	}; */
	@Override
	public int hashCode() {
		StringBuilder output = new StringBuilder();
		output.append(this.x);
		output.append(this.y);
		return Integer.parseInt(output.toString());
	}
}
