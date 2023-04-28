package com.Ace009.library.CoordinateSystem;

import java.util.ArrayList;

final class CoordinateMap {
	ArrayList<ArrayList<Position>> map=new ArrayList<ArrayList<Position>>();
	public class Position {
		Coordinate pos ;Object content;
		public Position(Coordinate coords, Object cont) {
			content=cont;pos=coords;
		}
		@Override
		public String toString() {
			return pos.toString()+":"+content.toString();
		}
	}
	public CoordinateMap (int sizeX, int sizeY) {
		for (int i1=0; i1<sizeY; i1++) {
			this.map.add(new ArrayList<Position>());
			for (int i2=0; i2<sizeX; i2++) {
				this.map.get(i1).add(new Position(new Coordinate(i2,i1)," "));
			}
		}
	}
	public void inputList(ArrayList<Coordinate> list,Object f_content) {
		for (Coordinate pos: list) {
			this.map.get((int)pos.y).get((int)pos.x).content=f_content;
			Coordinate coord=map.get((int)pos.y).get((int)pos.x).pos;
			if (!(pos.x==coord.x)) {
				System.out.println("unequal x");
			} if (!(pos.y==coord.y)) {
				System.out.println("unequal y");
			}
		}
	}
	@Override
	public String toString() {
		StringBuilder outputB= new StringBuilder();
		outputB.append("[");
		for (ArrayList<Position> row: map) {
			outputB.append("[");
			for (Position location: row) {
				outputB.append(location.toString()+",");
			}
			outputB.append("],\n");
		}
		outputB.deleteCharAt(outputB.length()-1);
		outputB.append("]");
		return outputB.toString();
	}
}
