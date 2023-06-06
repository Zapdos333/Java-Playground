package com.Ace009.library.CoordinateSystem;

import java.util.ArrayList;

/**
 * class to represent/calculate a Circle
 * @author Ace009
 */
public class Circle {
	/**
	 * returns the quotient of the totalDistance of the <code>polygon</code> over the circumferance of a circle with the given <code>radius</code>,
	 * only works if <code>polygon</code> is created with <code>Circles.construct</code> or <code>Circle.conssturcPoly</code>
	 * @param polygon
	 * @param radius
	 * @return 
	 */
	public static double getCircularity(ArrayList<Coordinate> polygon, double radius) {
		double circumferance= Coordinate.totalDistance(polygon,true);
		Circle temp=new Circle(new Coordinate(0,0),radius);
		double circleU=temp.circumferance();
		return circumferance/circleU;
	}
	/** center Coordinate of the circle */
	public Coordinate center;
	/** radius of the circle */
	public double radius;
	/**
	 * creates a circle with the given <code>center</code> and <code>radius</code>,
	 * calculations are the instances methods
	 * @param f_Center
	 * @param r
	 * @see Circle
	 */
	public Circle(Coordinate f_Center, double r) {
		center=f_Center;
		radius=r;
	}
	/**
	 * calculates the <code>Circles</code> circumferance by using its <code>radius</code
	 * @return <code>double</code> the <code>Circles</code> circumferance
	 */
	public double circumferance() {
		final double two=2;
		return Math.PI*radius*two;
	}
	/**
	 * returns the <code>Coordinates</code> of the <code>degrees</code> position on the <code>Circle</code>,
	 * while: for <code>degrees</code> = 0 it returns the <code>new Coordinate(center.x,center.y+radius)</code>
	 * @param degrees double
	 * @return the <code>Coordinates</code> of the <code>degrees</code> position on the <code>Circle</code>
	 */
	public Coordinate positionDegrees(double degrees) {
		double deltax= 0;
		double deltay= 0;
		degrees=degrees%360;
		deltax=radius*Math.sin(Math.toRadians(degrees));
		deltay=radius*Math.cos(Math.toRadians(degrees));
		return new Coordinate(center.x+deltax,center.y+deltay);
	}
	/**
	 * returns a <code>ArrayList</code> of <code>Coordinates</code> using <code>Circle.positionDegrees</code>,
	 * while every entry uses 0+iterations*interval as <code>degrees</code>
	 * @param interval double
	 * @return <code>ArrayList</code> of <code>Coordinates</code> with all <code>Coordinates</code> on <code>Circle</code>
	 */
	public ArrayList<Coordinate> construct(double interval) {
		ArrayList<Coordinate> output = new ArrayList<>();
		System.out.println("Debug, interval: "+interval);
		if (interval<0) {interval=Math.abs(interval);}
		if (interval>360) {interval=interval%360;}
		if (interval==0) {interval=360;}
		// reduces the number of iterations by one every time it encounters an OutOfMemoryError.
		// shouldn't be necessary anymore, but I'll leave it here just in case.
		/* while (true) {
			try {
				output.ensureCapacity((int)Math.ceil(360/interval));
			} catch (java.lang.OutOfMemoryError e) {
				interval=360/((360/interval)-1);
				System.out.println("retry, interval: "+interval);
				continue;
			} */
			for(int i=0; i*interval<360;i++) {
				output.add(this.positionDegrees(i*interval));
			}
			return output;
		// }
	}
	/**
	 * simply runs <code>Circle.construct(double interval)</code> with <code>360/corners</code> as <code>interval</code>
	 * @param corners int
	 * @return <code>ArrayList</code> of <code>Coordinates</code>
	 * @see #construct(double)
	 */
	public ArrayList<Coordinate> constructPoly(int corners) {
		final double circ=360.0;
		return this.construct(circ/(double)corners);
	}
}
