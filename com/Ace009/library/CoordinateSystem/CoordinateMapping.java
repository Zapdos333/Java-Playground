package com.Ace009.library.CoordinateSystem;

import java.util.ArrayList;

/**
 * a final class with one property,
 * CoordinateMap.map: it is a 2d Matrix of <code>Position</code>
 * @author Ace_009
 * @see Position
 */
final class CoordinateMap {
	ArrayList<ArrayList<Position>> map=new ArrayList<>();
	/**
	 * a static class containing only a <code>Coordinate</code> and content <code>Object</code>,
	 * should not be used outside of testing
	 * @author Ace_009
	 * @see Coordinate
	 */
	public static class Position {
		DoubleCoordinate pos; Object content;
		/**
		 * creates an instance of <code>Position</code>
		 * @param coords the <code>Coordinate</code>
		 * @param cont the content <code>Object</code>
		 * @see Position
		 */
		public Position(DoubleCoordinate coords, Object cont) {
			content=cont;pos=coords;
		}
		/**
		 * returns the <code>Coordinate</code> and the content <code>Object</code> seperated by a colon,
		 * for example: <code>[x:1,y:2]:String-from-Object</code>
		 * @return <code>String</code>
		 */
		@Override
		public String toString() {
			return pos.toString()+":"+content.toString();
		}
	}
	/**
	 * sets the <code>map</code> of this instance to <code>sizeY</code> rows and <code>sizeX</code> columns,
	 * also fills them with <code>new Position(the-position-in the-matrix-as-Cooridnate,<br>" ")</code>
	 * @param sizeX
	 * @param sizeY
	 * @see CoordinateMap
	 */
	public CoordinateMap (int sizeX, int sizeY) {
		this.map.ensureCapacity(sizeY);
		for (int i1=0; i1<sizeY; i1++) {
			this.map.add(new ArrayList<>());
			this.map.get(i1).ensureCapacity(sizeX);
			for (int i2=0; i2<sizeX; i2++) {
				this.map.get(i1).add(new Position(new DoubleCoordinate(i2,i1)," "));
			}
		}
	}
	/**
	 * sets the <code>content</code> of the <code>Positions</code> in <code>map</code> to <code>f_content</code>,
	 * for the <code>Coordinates</code> in the matrix specified by <code>list</code>
	 * @param list an <code>ArrayList</code> of <code>Coordinate</code>
	 * @param f_content an <code>Object</code> used as content
	 */
	public void inputList(ArrayList<DoubleCoordinate> list,Object f_content) {
		for (DoubleCoordinate pos: list) {
			this.map.get((int)pos.y).get((int)pos.x).content=f_content;
			DoubleCoordinate coord=map.get((int)pos.y).get((int)pos.x).pos;
			if (!(pos.x==coord.x)) {
				System.out.println("unequal x");
			} if (!(pos.y==coord.y)) {
				System.out.println("unequal y");
			}
		}
	}
	/**
	 * adds all the <code>.toString</code> of the <code>maps</code> content together,
	 * inserts a line-break<code>"\n"</code> at the end of each row
	 * @return the <code>String</code> representation of the <code>map</code>
	 */
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
