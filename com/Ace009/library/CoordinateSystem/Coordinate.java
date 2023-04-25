package com.Ace009.library.CoordinateSystem;
class Coordinate {
	float x;float y;
	public Coordinate(float f_x, float f_y) {
		x=f_x;y=f_y;
	}
	public static Coordinate cordsInt(int f_x, int f_y) {
		return new Coordinate((float)f_x,(float)f_y);
	}
}
