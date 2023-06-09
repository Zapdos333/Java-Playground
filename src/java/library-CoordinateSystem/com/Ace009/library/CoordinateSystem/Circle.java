package com.Ace009.library.CoordinateSystem;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * class to represent/calculate a Circle
 * @author Ace009
 */
public class Circle {
	/**
	 * returns the quotient of the totalDistance of the {@code polygon} over the {@code circumferance} of a {@code Circle} with the given {@code radius},
	 * only works if {@code polygon} is created with {@link #construct(double)} or {@link #constructPoly(int)}
	 * @param polygon the polygon {@code ArrayList<Coordinate>} of the circle
	 * @param radius the circles radius
	 * @return the quotient of the {@code totalDistance} of the {@code polygon} over the {@code circumference} of a {@code Circle} with the given {@code radius},
	 */
	public static double getCircularity(List<Coordinate> polygon, double radius) {
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
	 * creates a circle with the given {@code center} and {@code radius},
	 * calculations are the instance methods
	 * @param f_Center the center {@code Coordinate} of the circle
	 * @param r {@code double} the radius of the circle
	 * @see Circle
	 */
	public Circle(Coordinate f_Center, double r) {
		center=f_Center;
		radius=r;
	}
	/**
	 * calculates the {@code Circles} circumferance by using its {@code radius}
	 * @return {@code double} the {@code Circles} circumferance
	 */
	public double circumferance() {
		final double two=2;
		return Math.PI*radius*two;
	}
	/**
	 * returns the {@code Coordinates} of the {@code degrees} position on the {@code Circle}
	 * ,<p>while: for {@code degrees = 0} it returns the {@code Coordinate(center.x,center.y+radius)}
	 * @param degrees double
	 * @return the {@code Coordinates} of the {@code degrees} position on the {@code Circle}
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
	 * returns a {@code ArrayList} of {@code Coordinates} using {@code Circle.positionDegrees},
	 * while every entry uses {@code 0+iterations*interval} as degrees
	 * @param interval double
	 * @return {@code ArrayList} of {@code Coordinates} with all {@code Coordinates} on {@code Circle}
	 */
	public AbstractList<Coordinate> construct(double interval) {
		ArrayList<Coordinate> output = new ArrayList<>();
		System.out.println("Debug, interval: "+interval);
		if (interval<0) {interval=Math.abs(interval);}
		if (interval>360) {interval=interval%360;}
		if (interval==0) {interval=360;}
		for(int i=0; i*interval<360;i++) {
			output.add(this.positionDegrees(i*interval));
		}
		return output;
	}
	/**
	 * simply runs {@code Circle.construct(double interval)} with {@code 360/corners} as {@code interval}
	 * @param corners int
	 * @return {@code ArrayList} of {@code Coordinates}
	 * @see #construct(double)
	 */
	public AbstractList<Coordinate> constructPoly(int corners) {
		final double circ=360.0;
		return this.construct(circ/(double)corners);
	}
}
