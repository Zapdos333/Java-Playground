package com.Ace009.library.CoordinateSystem;

import java.util.List;

/**
 * an implementation of {@link Coordinate},
 * which uses any {@link Number} as {@code T}
 * <p> calculates in doubles
 * @author Ace009
 */
public class NumberCoordinate<T extends Number> extends Coordinate<T> {
	NumberCoordinate(T x, T y) { this.x = x; this.y = y; }
	public double distanceTo(NumberCoordinate<?> p2) {
		double deltaX = Math.abs(p2.x.doubleValue() - x.doubleValue());
		double deltaY = Math.abs(p2.y.doubleValue() - y.doubleValue());
		return Math.hypot(deltaX, deltaY);
	}
	public static double distance(NumberCoordinate<?>p1, NumberCoordinate<?>p2) { return p1.distanceTo(p2); }
	public static double totalDistance(List<? extends NumberCoordinate<?>> polygon2, boolean polygon) {
		double output = 0;
		for (int i=1; i<polygon2.size();i++) output+=polygon2.get(i-1).distanceTo(polygon2.get(i));
		if (polygon) output+=polygon2.get(polygon2.size()-1).distanceTo(polygon2.get(0));
		return output;
	}
}
