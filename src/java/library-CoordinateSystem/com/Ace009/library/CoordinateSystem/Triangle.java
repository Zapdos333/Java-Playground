package com.Ace009.library.CoordinateSystem;

import java.util.Map;
import java.util.stream.Stream;

import com.Ace009.library.Math.Fraction;

/**
 * a class for creating and calculating Triangles
 * @author Ace009
 */
public class Triangle {
	/** the FractionCoordinate[] triplet for the corner points */
	protected final FractionCoordinate[] points = new FractionCoordinate[3];
	/** the Fraction[] triplet for the angles in degrees */
	protected final Double[] angles = new Double[3];
	/** the Fraction[] triplet for the side-lenghts */
	protected final Fraction[] sides = new Fraction[3];
	/** index of the angle with 90° angle (if assigned) */
	protected byte rightAngle = -1;
	/** enum for the different properties of a Triangle */
	public enum ArgsType {
		PointA, PointB, PointC, SideA, SideB, SideC, Alpha, Beta, Gamma
	}
	/**
	 * uses a {@code Map<ArgsType,Object>} to create a Triangle
	 * <p> fills in the properties given
	 * and then calculates all possible missing properties
	 * @param properties
	 */
	public Triangle(Map<ArgsType,Object> properties) {
		for (Map.Entry<ArgsType, Object> entry : properties.entrySet()) {
			switch (entry.getKey()) {
				case PointA: points[0] = (FractionCoordinate)entry.getValue(); break;
				case PointB: points[1] = (FractionCoordinate)entry.getValue(); break;
				case PointC: points[2] = (FractionCoordinate)entry.getValue(); break;
				case SideA: sides[0] = (Fraction)entry.getValue(); break;
				case SideB: sides[1] = (Fraction)entry.getValue(); break;
				case SideC: sides[2] = (Fraction)entry.getValue(); break;
				case Alpha: angles[0] = (Double)entry.getValue(); break;
				case Beta: angles[1] = (Double)entry.getValue(); break;
				case Gamma: angles[2] = (Double)entry.getValue(); break;
			}
		}
		calculate();
	}
	/** calculates all (possible) remaining values */
	protected void calculate() {
		//count null references
		byte Pnull=0, Anull=0, Snull=0;
		for (int i = 0; i < 3; i++) {
			if (points[i] == null) Pnull++;
			if (sides[i] == null) Snull++;
			if (angles[i] == null) Anull++;
		}
		if ((Pnull==3) && (Anull==3) && (Snull==3)) throw new NullPointerException("No data given");
		//start calculations
		// check angles
		if (Stream.of(angles).filter(e->(e==null?0:e)==90).toArray().length>1) throw new IllegalArgumentException("no more than one right angle allowed");
		if ((Anull==0)&&(Math.abs(Stream.of(angles).mapToDouble(Double::valueOf).sum()-180)<1)) throw new IllegalArgumentException("angle sum outside (of tolerance of 1°) from 180°");
		// find right angle
		if (angles[0]==null?false:angles[0]==90) rightAngle = 0;
		else if (angles[1]==null?false:angles[1]==90) rightAngle = 1;
		else if (angles[2]==null?false:angles[2]==90) rightAngle = 2;
		// calculations in rectangular triangle
		if (rightAngle > -1) {
			if ((sides[rightAngle]==null)&&(sides[(rightAngle+1)%3]!=null)&&(sides[(rightAngle+2)%3]!=null))
				sides[rightAngle]=sides[(rightAngle+1)%3]
					.toPowerOf(2)
					.add(sides[(rightAngle+2)%3].toPowerOf(2))
					.inAccPow(0.5);
			if ((sides[rightAngle]!=null)&&(sides[(rightAngle+1)%3]==null)&&(sides[(rightAngle+2)%3]!=null))
				sides[(rightAngle+1)%3]=sides[rightAngle]
					.toPowerOf(2)
					.subtract(sides[(rightAngle+2)%3].toPowerOf(2))
					.inAccPow(0.5);
			if ((sides[rightAngle]!=null)&&(sides[(rightAngle+1)%3]!=null)&&(sides[(rightAngle+2)%3]==null))
				sides[(rightAngle+2)%3]=sides[rightAngle]
					.toPowerOf(2)
					.subtract(sides[(rightAngle+2)%3].toPowerOf(2))
					.inAccPow(0.5);
			if (angles[(rightAngle+1)%3]==null) {
				if ((sides[rightAngle]==null)&&(sides[(rightAngle+1)%3]!=null)&&(sides[(rightAngle+2)%3]!=null))
					angles[(rightAngle+1)%3]= Math.tan(sides[(rightAngle+1)%3]
						.divideBy(sides[(rightAngle+2)%3])
						.calculate() );
				if ((sides[rightAngle]!=null)&&(sides[(rightAngle+1)%3]==null)&&(sides[(rightAngle+2)%3]!=null))
					angles[(rightAngle+1)%3]= Math.cos(sides[(rightAngle+2)%3]
						.divideBy(sides[rightAngle])
						.calculate() );
				if ((sides[rightAngle]!=null)&&(sides[(rightAngle+1)%3]!=null)&&(sides[(rightAngle+2)%3]==null))
					angles[(rightAngle+1)%3]= Math.sin(sides[(rightAngle+1)%3]
						.divideBy(sides[(rightAngle+0)%3])
						.calculate() );
			}
			if (angles[(rightAngle+2)%3]==null) {
				if ((sides[rightAngle]==null)&&(sides[(rightAngle+1)%3]!=null)&&(sides[(rightAngle+2)%3]!=null))
					angles[(rightAngle+2)%3]= Math.tan(sides[(rightAngle+2)%3]
						.divideBy(sides[(rightAngle+1)%3])
						.calculate() );
				if ((sides[rightAngle]!=null)&&(sides[(rightAngle+1)%3]==null)&&(sides[(rightAngle+2)%3]!=null))
					angles[(rightAngle+2)%3]= Math.cos(sides[(rightAngle+1)%3]
						.divideBy(sides[rightAngle])
						.calculate() );
				if ((sides[rightAngle]!=null)&&(sides[(rightAngle+1)%3]!=null)&&(sides[(rightAngle+2)%3]==null))
					angles[(rightAngle+2)%3]= Math.sin(sides[(rightAngle+2)%3]
						.divideBy(sides[rightAngle])
						.calculate() );
			}
		}
		// calculate side from sides+angles
		if ((Anull<=1) && (Snull<=2)) {
			if (sides[0]==null) sides[0] = sides[1]
				.toPowerOf(2)
				.add(sides[2].toPowerOf(2))
				.add(new Fraction(2)
					.multiplyBy(sides[1])
					.multiplyBy(sides[2])
					.multiplyBy(Math.cos(angles[0]))
				).inAccPow(0.5);
			else if (sides[1]==null) sides[1] = sides[0]
				.toPowerOf(2)
				.add(sides[2].toPowerOf(2))
				.add(new Fraction(2)
					.multiplyBy(sides[0])
					.multiplyBy(sides[2])
					.multiplyBy(Math.cos(angles[1]))
				).inAccPow(0.5);
			else if (sides[2]==null) sides[2] = sides[0]
				.toPowerOf(2)
				.add(sides[1].toPowerOf(2))
				.add(new Fraction(2)
					.multiplyBy(sides[0])
					.multiplyBy(sides[1])
					.multiplyBy(Math.cos(angles[2]))
				).inAccPow(0.5);
		}
		// calculate angles from sides and angles and vice-versa (as relations)
		if ((Snull+Anull<=3) && ((Snull!=3) || (Anull!=3)) ) {
			//angles from sides and angles
			if (angles[0]==null && sides[0]!=null) {
				if ((sides[1]!=null)&&(angles[1]!=null)) angles[0] = Math.asin(sides[0]
					.divideBy(sides[1])
					.multiplyBy(Math.sin(angles[1]))
					.calculate() );
				else if ((sides[2]!=null)&&(angles[2]!=null)) angles[0] = Math.asin(sides[0]
					.divideBy(sides[2])
					.multiplyBy(Math.sin(angles[2]))
					.calculate() );
			}
			if ((angles[1]==null)&&(sides[1]!=null)) {
				if ((sides[0]!=null)&&(angles[0]!=null)) angles[1] = Math.asin(sides[1]
					.divideBy(sides[0])
					.multiplyBy(Math.sin(angles[0]))
					.calculate() );
				else if ((sides[2]!=null)&&(angles[2]!=null)) angles[1] = Math.asin(sides[1]
					.divideBy(sides[2])
					.multiplyBy(Math.sin(angles[2]))
					.calculate() );
			}
			if ((angles[2]==null) && (sides[2]!=null)) {
				if ((sides[0]!=null)&&(angles[0]!=null)) angles[2] = Math.asin(sides[2]
					.divideBy(sides[0])
					.multiplyBy(Math.sin(angles[0]))
					.calculate() );
				else if ((sides[1]!=null)&&(angles[1]!=null)) angles[2] = Math.asin(sides[2]
					.divideBy(sides[1])
					.multiplyBy(Math.sin(angles[1]))
					.calculate() );
			}
			//sides from sides and angles
			if ((sides[0]==null) && (angles[0]!=null)) {
				if ((sides[1]!=null)&&(angles[1]!=null)) sides[0] = sides[1]
					.multiplyBy(Math.sin(angles[0]))
					.divideBy(Math.sin(angles[1]));
				else if ((sides[2]!=null)&&(angles[2]!=null)) sides[0] = sides[2]
					.multiplyBy(Math.sin(angles[0]))
					.divideBy(Math.sin(angles[2]));
			}
			if ((sides[1]==null) && (angles[1]!=null)) {
				if ((sides[0]!=null)&&(angles[0]!=null)) sides[1] = sides[0]
					.multiplyBy(Math.sin(angles[1]))
					.divideBy(Math.sin(angles[0]));
				else if ((sides[2]!=null)&&(angles[2]!=null)) sides[1] = sides[2]
					.multiplyBy(Math.sin(angles[1]))
					.divideBy(Math.sin(angles[2]));
			}
			if ((sides[2]==null) && (angles[2]!=null)) {
				if ((sides[0]!=null)&&(angles[0]!=null)) sides[2] = sides[0]
					.multiplyBy(Math.sin(angles[2]))
					.divideBy(Math.sin(angles[0]));
				else if ((sides[1]!=null)&&(angles[1]!=null)) sides[2] = sides[1]
					.multiplyBy(Math.sin(angles[2]))
					.divideBy(Math.sin(angles[1]));
			}
		}
		// calculate sides from points
		if (Pnull == 0) {
			if (sides[0]==null) {sides[0] = FractionCoordinate.distance(points[1], points[2]); Snull--;}
			if (sides[1]==null) {sides[1] = FractionCoordinate.distance(points[0], points[2]); Snull--;}
			if (sides[2]==null) {sides[2] = FractionCoordinate.distance(points[0], points[1]); Snull--;}
		}
		//final check
		Pnull=0; Anull=0; Snull=0;
		for (int i = 0; i < 3; i++) {
			if (points[i] == null) Pnull++;
			if (sides[i] == null) Snull++;
			if (angles[i] == null) Anull++;
		}
		if (Pnull!=0||Anull!=0||Snull!=0) System.out.println("Warning: No full calculation possible");
	}
	/** smol method for custom {@link #toString()} */
	private String fromAB(String a, String b, String key) {
		if (a=="") return b;
		return String.format("%s; %s:%s",a,key,b);
	}
	/** {@inheritDoc} */
	@Override
	public String toString() {
		String points=Stream.of(this.points).map(e->e.toString())
			.reduce(new String(),(a,b)->fromAB(a,b,"P"));
		String sides=Stream.of(this.sides).map(e->e.toString())
			.reduce(new String(),(a,b)->fromAB(a,b,"S"));
		String angles=Stream.of(this.angles).map(e->e.toString())
			.reduce(new String(),(a,b)->fromAB(a,b,"A"));
		return String.format("Triangle:\n%s\n%s\n%s\n",points,sides,angles);
	}
}
