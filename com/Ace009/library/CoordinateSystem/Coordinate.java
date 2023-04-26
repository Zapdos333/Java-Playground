package com.Ace009.library.CoordinateSystem;

import java.util.ArrayList;
import java.util.stream.Collectors;
//import java.util.stream.Stream;

class Coordinate {
	double x;double y;
	public Coordinate(double f_x, double f_y) {
		x=f_x;y=f_y;
	}
	public static double distance(Coordinate pA, Coordinate pB) {
		double deltax = pA.x-pB.x;
		double deltay = pA.y-pB.y;
		return Math.hypot(deltax,deltay);
	}
	public static double totalDistance(ArrayList<Coordinate> list,boolean polygon) {
		double output=0;
		for (int i=0; i<list.size();i++) {
			output=+distance(list.get(i),list.get(i+1));
		}
		if (polygon) {
			output=+distance(list.get(list.size()-1),list.get(0));
		}
		return output;
	}
	public static ArrayList<Coordinate> roundCoordList(ArrayList<Coordinate> list, float interval) {
		ArrayList<Coordinate> output=new ArrayList<Coordinate>();
		int i=0;
		while (output.size()<list.size()) {
			final double iteration=interval*i;
			output.addAll(list.stream().filter(e->e.x<iteration).map(e->new Coordinate(Math.round(e.x/interval)*interval,Math.round(e.y/interval)*interval)).collect(Collectors.toList()));
			i++;
		}
		return output;
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
