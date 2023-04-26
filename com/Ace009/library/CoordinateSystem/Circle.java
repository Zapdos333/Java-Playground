package com.Ace009.library.CoordinateSystem;

import java.util.ArrayList;

class Circle {
	Coordinate center;
	double radius;
	public Circle(Coordinate f_Center, double r) {
		center=f_Center;
		radius=r;
	}
	public Coordinate positionDegrees(double degrees) {
		double deltax= 0;
		double deltay= 0;
		degrees= degrees%360;
		if (degrees < 90) {
			deltax=radius*Math.sin(Math.toRadians(degrees));
			deltay=radius*Math.cos(Math.toRadians(degrees));
		} else if (degrees < 180) {
			deltay=radius*Math.sin(Math.toRadians(degrees));
			deltax=radius*Math.cos(Math.toRadians(degrees));
		} else if (degrees < 270) {
			deltax=radius*Math.sin(Math.toRadians(degrees));
			deltay=radius*Math.cos(Math.toRadians(degrees));
		} else if (degrees < 360) {
			deltay=radius*Math.sin(Math.toRadians(degrees));
			deltax=radius*Math.cos(Math.toRadians(degrees));
		}
		return new Coordinate(center.x+deltax,center.y+deltay);
	}
	public ArrayList<Coordinate> construct(double interval) {
		ArrayList<Coordinate> output = new ArrayList<Coordinate>();
		for(double i=0; i<360;i=i+interval) {
			output.add(this.positionDegrees(i));
		}
		return output;
	}
	public ArrayList<Coordinate> constructPoly(int corners) {
		return this.construct(360/corners);
	}
	public static void main(String[] args) {
		System.out.println("Test program: ");
		System.out.println("1: CenterX; 2: CenterY; 3: Radius; 4: Corners");
		double aX= Double.parseDouble(args[0]);
		double aY= Double.parseDouble(args[1]);
		double aR= Double.parseDouble(args[2]);
		int aC= Integer.parseInt(args[3]);
		Circle test = new Circle(new Coordinate(aX,aY),aR);
		System.out.print("CenterX="+test.center.x);
		System.out.print(" CenterY="+test.center.y);
		System.out.print(" Radius="+test.radius);
		System.out.println(" Corners="+(aC));
		ArrayList<Coordinate> result = test.constructPoly(aC);
		System.out.println("Circle: "+result.toString());
	}
}
