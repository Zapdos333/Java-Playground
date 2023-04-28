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
		degrees=degrees%360;
		deltax=radius*Math.sin(Math.toRadians(degrees));
		deltay=radius*Math.cos(Math.toRadians(degrees));
		return new Coordinate(center.x+deltax,center.y+deltay);
	}
	public ArrayList<Coordinate> construct(double interval) {
		ArrayList<Coordinate> output = new ArrayList<Coordinate>();
		System.out.println("Debug, interval: "+interval);
		if (interval<0) {interval=Math.abs(interval);}
		if (interval==0) {interval=360;}
		if (interval>360) {interval=interval%360;}
		while (true) {
			try {
				output.ensureCapacity((int)Math.ceil(360/interval));
			} catch (java.lang.OutOfMemoryError e) {
				interval=360/((360/interval)-1);
				System.out.println("retry, interval: "+interval);
				continue;
			} finally {
				for(int i=0; i*interval<360;i++) {
					output.add(this.positionDegrees(i*interval));
				}
				return output;			
			}
		}
	}
	public ArrayList<Coordinate> constructPoly(int corners) {
		final double circ=360.0;
		return this.construct(circ/(double)corners);
	}
	public static String getCircularity(ArrayList<Coordinate> polygon, double radius) {
		double circumferance= Coordinate.totalDistance(polygon,true);
		final double two=2;
		double circleU=Math.PI*radius*two;
		return circumferance+"/"+circleU;
	}
	public static void main(String[] args) {
		System.out.println("Test program: ");
		System.out.println("1: Corners");
		int aC= Integer.parseInt(args[0]);
		double aX= 5;
		double aY= 5;
		double aR= 5;
		Circle test = new Circle(new Coordinate(aX,aY),aR);
		System.out.print("CenterX="+test.center.x);
		System.out.print(" CenterY="+test.center.y);
		System.out.println(" Radius="+test.radius);
		ArrayList<Coordinate> result = test.constructPoly(aC);
		//result= Coordinate.roundCoordList(result,(float)1);
		System.out.println("Circle: "+result.toString());
		System.out.println("Circluarity: "+getCircularity(result,test.radius));
		System.out.println("Corners: "+result.size());
		/*CoordinateMap map= new CoordinateMap((int)aR*2,(int)aR*2);
		map.inputList(result,"Circle");
		System.out.println(map.toString()); */
	}
}
