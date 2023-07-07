package com.Ace009.library.CoordinateSystem;

/**
 * an abstract class that gives a rough skeleton of a coordinate
 * <p>
 * implements overrides for {@code toString(), equals(), hashCode()},
 * all based on the corresponding methods of {@code T}
 * @author Ace009
 */
public abstract class Coordinate<T> {
	/** the x coordinate */
	public T x;
	/** the y coordinate */
	public T y;
	/**
	 * returns a {@code String} representing the {@code Coordinate},
	 * for example: "3|6"
	 * @return {@code String}: string representation of the {@code Coordinate}
	 */
	@Override
	public String toString() { return String.format("%s|%s",x,y); }
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coordinate<?> that = (Coordinate<?>) o;
		return (x == that.x) && (y == that.y);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() { return x.hashCode()+y.hashCode(); }
}
