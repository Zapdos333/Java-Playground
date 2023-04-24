package com.Ace009.library;

class Coordinate  {
	float x;
	float y;
	public Coordinate(float f_x, float f_y) {
		x=f_x;
		y=f_y;
	}
	public Coordinate cordsInt(int f_x, int f_y) {
		return new Coordinate((float)f_x,(float)f_y);
	}
}
class Circle {
	Coordinate center;
	float radius;
	public Circle(Coordinate f_Center, float r) {
		center=f_Center;
		radius=r;
	}
	public Coordinate positionDegrees(float degrees) throws Exception {
		float deltax= 0;
		float deltay= 0;
		if (degrees%360 > 0) {
			degrees= degrees-((degrees%360)*degrees);
		}
		if (degrees < 90) {
			deltax=radius*(float)Math.sin(degrees);
			deltay=radius*(float)Math.cos(degrees);
		} else if (degrees < 180) {
			deltay=radius*(float)Math.sin(degrees);
			deltax=radius*(float)Math.cos(degrees);
		} else if (degrees < 270) {
			deltax=radius*(float)Math.sin(degrees);
			deltay=radius*(float)Math.cos(degrees);
		} else if (degrees < 360) {
			deltay=radius*(float)Math.sin(degrees);
			deltax=radius*(float)Math.cos(degrees);
		} else {
			throw new Exception("invalid degree number");
		}
		return new Coordinate(center.x+deltax,center.y+deltay);
	}
}
