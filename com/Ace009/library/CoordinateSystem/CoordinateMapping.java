package com.Ace009.library.CoordinateSystem;

import java.util.ArrayList;

final class CoordinateMap {
	public class Position {
		double x;double y;Object content;
		public Position(Coordinate coords, Object cont) {
			content=cont;x=coords.x;y=coords.y;
		}
	}
	public static ArrayList<ArrayList<Position>> createMap (float scale, int sizeX, int sizeY) {
		ArrayList<ArrayList<Position>> output= new ArrayList<ArrayList<Position>>();
		for (int i1=0; i1<output.size(); i1++) {
			for (int i2=0; i2<output.get(0).size(); i2++) {
				output.get(i1).get(i2).x=scale*i2;
				output.get(i1).get(i2).y=scale*i1;
			}
		}
		return output;
	}
}
