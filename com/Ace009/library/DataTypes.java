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
	public Coordinate positionDegrees(float degrees) {
		float x;
		float y;
		if (degrees%360 > 0) {
			degrees= degrees-((degrees%360)*degrees);
		}
		if (degrees < 90) {
			
		} else if (degrees < 180) {
			
		} else if (degrees < 270) {
			
		} else if (degrees < 360) {
			
		} else {
			throw new Exception("invalid degree number");
		}
	}
	public static void main(String[] args) {

	}
}
