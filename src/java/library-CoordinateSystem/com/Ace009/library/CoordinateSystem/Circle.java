package com.Ace009.library.CoordinateSystem;

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
	 * @param polygon the polygon {@code List<NumberCoordinate>} of the circle
	 * @param radius the circles radius
	 * @return the quotient of the {@code totalDistance} of the {@code polygon} over the {@code circumference} of a {@code Circle} with the given {@code radius},
	 */
	public static double getCircularity(List<NumberCoordinate<Double>> polygon, double radius) {
		double circumferance= NumberCoordinate.totalDistance(polygon,true);
		Circle temp=new Circle(new NumberCoordinate<Double>(0.0,0.0),radius);
		double circleU=temp.circumferance();
		return circumferance/circleU;
	}
	/** center NumberCoordinate of the circle */
	public NumberCoordinate<Double> center;
	/** radius of the circle */
	public double radius;
	/**
	 * creates a circle with the given {@code center} and {@code radius},
	 * calculations are the instance methods
	 * @param f_Center the center {@code NumberCoordinate} of the circle
	 * @param r {@code double} the radius of the circle
	 * @see Circle
	 */
	public Circle(NumberCoordinate<Double> f_Center, double r) {
		center=f_Center;
		radius=r;
	}
	/**
	 * calculates the {@code Circles} circumferance by using its {@code radius}
	 * @return {@code double} the {@code Circles} circumferance
	 */
	public double circumferance() { return Math.PI*radius*2.0; }
	/**
	 * returns the {@code NumberCoordinates} of the {@code degrees} position on the {@code Circle}
	 * ,<p>while: for {@code degrees = 0} it returns the {@code NumberCoordinate(center.x,center.y+radius)}
	 * @param degrees double
	 * @return the {@code NumberCoordinates} of the {@code degrees} position on the {@code Circle}
	 */
	public NumberCoordinate<Double> positionDegrees(double degrees) {
		double deltax= 0, deltay= 0;
		degrees=degrees%360;
		deltax=radius*Math.sin(Math.toRadians(degrees));
		deltay=radius*Math.cos(Math.toRadians(degrees));
		double x=center.x+deltax, y=center.y+deltay;
		return new NumberCoordinate<Double>(Double.valueOf(x),Double.valueOf(y));
	}
	/**
	 * returns a {@code List} of {@code NumberCoordinates} using {@code Circle.positionDegrees},
	 * while every entry uses {@code 0+iterations*interval} as degrees
	 * @param interval double
	 * @return {@code List} of {@code NumberCoordinates} with all {@code NumberCoordinates} on {@code Circle}
	 */
	public List<NumberCoordinate<Double>> construct(double interval) {
		ArrayList<NumberCoordinate<Double>> output = new ArrayList<>();
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
	 * @return {@code List} of {@code NumberCoordinates}
	 * @see #construct(double)
	 */
	public List<NumberCoordinate<Double>> constructPoly(int corners) {
		final double circ=360.0;
		return this.construct(circ/(double)corners);
	}
}
