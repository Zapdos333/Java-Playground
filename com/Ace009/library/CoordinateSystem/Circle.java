package com.Ace009.library.CoordinateSystem;

import java.util.ArrayList;

class Circle {
	Coordinate center;
	float radius;
	public Circle(Coordinate f_Center, float r) {
		center=f_Center;
		radius=r;
	}
	public Coordinate positionDegrees(float degrees) {
		float deltax= 0;
		float deltay= 0;
		degrees= degrees%360;
		if (degrees < 90) {
			deltax=radius*(float)Math.sin(Math.toRadians(degrees));
			deltay=radius*(float)Math.cos(Math.toRadians(degrees));
		} else if (degrees < 180) {
			deltay=radius*(float)Math.sin(Math.toRadians(degrees));
			deltax=radius*(float)Math.cos(Math.toRadians(degrees));
		} else if (degrees < 270) {
			deltax=radius*(float)Math.sin(Math.toRadians(degrees));
			deltay=radius*(float)Math.cos(Math.toRadians(degrees));
		} else if (degrees < 360) {
			deltay=radius*(float)Math.sin(Math.toRadians(degrees));
			deltax=radius*(float)Math.cos(Math.toRadians(degrees));
		}
		return new Coordinate(center.x+deltax,center.y+deltay);
	}
	public ArrayList<Coordinate> construct(float interval) {
		ArrayList<Coordinate> output = new ArrayList<Coordinate>();
		for(float i=0; i<360;i=i+interval) {
			output.add(this.positionDegrees(i));
		}
		return output;
	}
	public static void main(String[] args) {
		System.out.println("Test program: ");
		System.out.println("1: CenterX; 2: CenterY; 3: Radius; 4: DegreesInterval");
		float aX= Float.parseFloat(args[0]);
		float aY= Float.parseFloat(args[1]);
		float aR= Float.parseFloat(args[2]);
		float aD= Float.parseFloat(args[3]);
		Circle test = new Circle(new Coordinate(aX,aY),aR);
		System.out.print("CenterX="+test.center.x);
		System.out.print(" CenterY="+test.center.y);
		System.out.print(" Radius="+test.radius);
		System.out.println(" Degrees="+(aD%360));
		ArrayList<Coordinate> result = test.construct(aD);
		System.out.println("");
	}
}
