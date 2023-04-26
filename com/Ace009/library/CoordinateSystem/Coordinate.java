package com.Ace009.library.CoordinateSystem;
class Coordinate {
	double x;double y;
	public Coordinate(double f_x, double f_y) {
		x=f_x;y=f_y;
	}
	public static double Distance(Coordinate pA, Coordinate pB) {
		double deltax = pA.x-pB.x;
		double deltay = pA.y-pB.y;
		return Math.hypot(deltax,deltay);
	}
	@Override
	public String toString() {
		return "[x:"+this.x+", y:"+this.y+"]";
	}
	public boolean equals(Object o) {
		if (o==this) {return true;}
		if (!(o instanceof Coordinate)) {return false;}
		Coordinate c = (Coordinate) o;
		return Double.compare(this.x,c.x)==0 && Double.compare(this.y,c.y)==0;
	}
	public int hashCode() {
		return Double.hashCode(x)+Double.hashCode(y);
	}
}
