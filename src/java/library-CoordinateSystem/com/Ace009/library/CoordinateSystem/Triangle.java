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
	protected final Fraction[] angles = new Fraction[3];
	/** the Fraction[] triplet for the side-lenghts */
	protected final Fraction[] sides = new Fraction[3];
	/** index of the angle with 90° angle (if assigned) */
	protected byte rightAngle = -1;
	// [a,b,c]
	public enum ArgsType {
		PointA, PointB, PointC, SideA, SideB, SideC, Alpha, Beta, Gamma
	}
	protected Triangle(Map<ArgsType,Object> properties) {
		for (Map.Entry<ArgsType, Object> entry : properties.entrySet()) {
			switch (entry.getKey()) {
				case PointA: points[0] = (FractionCoordinate)entry.getValue(); break;
				case PointB: points[1] = (FractionCoordinate)entry.getValue(); break;
				case PointC: points[2] = (FractionCoordinate)entry.getValue(); break;
				case SideA: sides[0] = (Fraction)entry.getValue(); break;
				case SideB: sides[1] = (Fraction)entry.getValue(); break;
				case SideC: sides[2] = (Fraction)entry.getValue(); break;
				case Alpha: angles[0] = (Fraction)entry.getValue(); break;
				case Beta: angles[1] = (Fraction)entry.getValue(); break;
				case Gamma: angles[2] = (Fraction)entry.getValue(); break;
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
		if (Pnull == 3 && Anull == 3 && Snull == 3) throw new NullPointerException("No data given");
		//start calculations
		// check angles
		if (Stream.of(angles).filter(e->e.calculate()==90).toArray().length>1) throw new IllegalArgumentException("no more than one right angle allowed");
		if (Anull==0&&Math.abs(Stream.of(angles).mapToDouble(e->e.calculate()).sum()-180)<1) throw new IllegalArgumentException("angle sum outside (of tolerance of 1°) from 180°");
		// find right angle
		if (angles[0].calculate()==90) rightAngle = 0;
		else if (angles[1].calculate()==90) rightAngle = 1;
		else if (angles[2].calculate()==90) rightAngle = 2;
		// calculations in rectangular triangle
		if (rightAngle > -1) {
			if (sides[rightAngle]==null&&sides[(rightAngle+1)%3]!=null&&sides[(rightAngle+2)%3]!=null)
				sides[rightAngle]=sides[(rightAngle+1)%3]
					.toPowerOf(2)
					.add(sides[(rightAngle+2)%3].toPowerOf(2))
					.tempSquareRoot();
			if (sides[rightAngle]!=null&&sides[(rightAngle+1)%3]==null&&sides[(rightAngle+2)%3]!=null)
				sides[(rightAngle+1)%3]=sides[rightAngle]
					.toPowerOf(2)
					.subtract(sides[(rightAngle+2)%3].toPowerOf(2))
					.tempSquareRoot();
			if (sides[rightAngle]!=null&&sides[(rightAngle+1)%3]!=null&&sides[(rightAngle+2)%3]==null)
				sides[(rightAngle+2)%3]=sides[rightAngle]
					.toPowerOf(2)
					.subtract(sides[(rightAngle+2)%3].toPowerOf(2))
					.tempSquareRoot();
			if (angles[(rightAngle+1)%3]==null) {
				if (sides[rightAngle]==null&&sides[(rightAngle+1)%3]!=null&&sides[(rightAngle+2)%3]!=null)
					angles[(rightAngle+1)%3]= new Fraction(Math.tan(sides[(rightAngle+1)%3]
						.divideBy(sides[(rightAngle+2)%3])
						.calculate() ));
				if (sides[rightAngle]!=null&&sides[(rightAngle+1)%3]==null&&sides[(rightAngle+2)%3]!=null)
					angles[(rightAngle+1)%3]= new Fraction(Math.cos(sides[(rightAngle+2)%3]
						.divideBy(sides[rightAngle])
						.calculate() ));
				if (sides[rightAngle]!=null&&sides[(rightAngle+1)%3]!=null&&sides[(rightAngle+2)%3]==null)
					angles[(rightAngle+1)%3]= new Fraction(Math.sin(sides[(rightAngle+1)%3]
						.divideBy(sides[(rightAngle+0)%3])
						.calculate() ));
			}
			if (angles[(rightAngle+2)%3]==null) {
				if (sides[rightAngle]==null&&sides[(rightAngle+1)%3]!=null&&sides[(rightAngle+2)%3]!=null)
					angles[(rightAngle+2)%3]= new Fraction(Math.tan(sides[(rightAngle+2)%3]
						.divideBy(sides[(rightAngle+1)%3])
						.calculate() ));
				if (sides[rightAngle]!=null&&sides[(rightAngle+1)%3]==null&&sides[(rightAngle+2)%3]!=null)
					angles[(rightAngle+2)%3]= new Fraction(Math.cos(sides[(rightAngle+1)%3]
						.divideBy(sides[rightAngle])
						.calculate() ));
				if (sides[rightAngle]!=null&&sides[(rightAngle+1)%3]!=null&&sides[(rightAngle+2)%3]==null)
					angles[(rightAngle+2)%3]= new Fraction(Math.sin(sides[(rightAngle+2)%3]
						.divideBy(sides[rightAngle])
						.calculate() ));
			}
		}
		// calculate side from sides+angles
		if (Anull <= 1 && Snull <= 2) {
			if (sides[0]==null) sides[0] = sides[1]
				.toPowerOf(2)
				.add(sides[2].toPowerOf(2))
				.add(new Fraction(2)
					.multiplyBy(sides[1])
					.multiplyBy(sides[2])
					.multiplyBy(new Fraction(Math.cos(angles[0].calculate())))
				).tempSquareRoot();
			else if (sides[1]==null) sides[1] = sides[0]
				.toPowerOf(2)
				.add(sides[2].toPowerOf(2))
				.add(new Fraction(2)
					.multiplyBy(sides[0])
					.multiplyBy(sides[2])
					.multiplyBy(new Fraction(Math.cos(angles[1].calculate())))
				).tempSquareRoot();
			else if (sides[2]==null) sides[2] = sides[0]
				.toPowerOf(2)
				.add(sides[1].toPowerOf(2))
				.add(new Fraction(2)
					.multiplyBy(sides[0])
					.multiplyBy(sides[1])
					.multiplyBy(new Fraction(Math.cos(angles[2].calculate())))
				).tempSquareRoot();
		}
		// calculate angles from sides and angles and vice-versa (as relations)
		if (Snull+Anull<=3 && Snull!=3 && Anull!=3) {
			//angles from sides and angles
			if (angles[0]==null && sides[0]!=null) {
				if (sides[1]!=null&&angles[1]!=null) angles[0] = new Fraction(Math.asin(sides[0]
					.divideBy(sides[1])
					.multiplyBy(new Fraction(Math.sin(angles[1].calculate() )))
					.calculate() ));
				else if (sides[2]!=null&&angles[2]!=null) angles[0] = new Fraction(Math.asin(sides[0]
					.divideBy(sides[2])
					.multiplyBy(new Fraction(Math.sin(angles[2].calculate() )))
					.calculate() ));
			}
			if (angles[1]==null && sides[1]!=null) {
				if (sides[0]!=null&&angles[0]!=null) angles[1] = new Fraction(Math.asin(sides[1]
					.divideBy(sides[0])
					.multiplyBy(new Fraction(Math.sin(angles[0].calculate() )))
					.calculate() ));
				else if (sides[2]!=null&&angles[2]!=null) angles[1] = new Fraction(Math.asin(sides[1]
					.divideBy(sides[2])
					.multiplyBy(new Fraction(Math.sin(angles[2].calculate() )))
					.calculate() ));
			}
			if (angles[2]==null && sides[2]!=null) {
				if (sides[0]!=null&&angles[0]!=null) angles[2] = new Fraction(Math.asin(sides[2]
					.divideBy(sides[0])
					.multiplyBy(new Fraction(Math.sin(angles[0].calculate() )))
					.calculate() ));
				else if (sides[1]!=null&&angles[1]!=null) angles[2] = new Fraction(Math.asin(sides[2]
					.divideBy(sides[1])
					.multiplyBy(new Fraction(Math.sin(angles[1].calculate() )))
					.calculate() ));
			}
			//sides from sides and angles
			if (sides[0]==null && angles[0]!=null) {
				if (sides[1]!=null&&angles[1]!=null) sides[0] = sides[1]
					.multiplyBy(new Fraction(Math.sin(angles[0].calculate())))
					.divideBy(new Fraction(Math.sin(angles[1].calculate())));
				else if (sides[2]!=null&&angles[2]!=null) sides[0] = sides[2]
					.multiplyBy(new Fraction(Math.sin(angles[0].calculate())))
					.divideBy(new Fraction(Math.sin(angles[2].calculate())));
			}
			if (sides[1]==null && angles[1]!=null) {
				if (sides[0]!=null&&angles[0]!=null) sides[1] = sides[0]
					.multiplyBy(new Fraction(Math.sin(angles[1].calculate())))
					.divideBy(new Fraction(Math.sin(angles[0].calculate())));
				else if (sides[2]!=null&&angles[2]!=null) sides[1] = sides[2]
					.multiplyBy(new Fraction(Math.sin(angles[1].calculate())))
					.divideBy(new Fraction(Math.sin(angles[2].calculate())));
			}
			if (sides[2]==null && angles[2]!=null) {
				if (sides[0]!=null&&angles[0]!=null) sides[2] = sides[0]
					.multiplyBy(new Fraction(Math.sin(angles[2].calculate())))
					.divideBy(new Fraction(Math.sin(angles[0].calculate())));
				else if (sides[1]!=null&&angles[1]!=null) sides[2] = sides[1]
					.multiplyBy(new Fraction(Math.sin(angles[2].calculate())))
					.divideBy(new Fraction(Math.sin(angles[1].calculate())));
			}
		}
		// calculate sides from points
		if (Pnull == 0) {
			if (sides[0]==null) sides[0] = FractionCoordinate.distance(points[1], points[2]);
			if (sides[1]==null) sides[1] = FractionCoordinate.distance(points[0], points[2]);
			if (sides[2]==null) sides[2] = FractionCoordinate.distance(points[0], points[1]);
		}
		Pnull=0; Anull=0; Snull=0;
		for (int i = 0; i < 3; i++) {
			if (points[i] == null) Pnull++;
			if (sides[i] == null) Snull++;
			if (angles[i] == null) Anull++;
		}
		if (Pnull!=0||Anull!=0||Snull!=0) System.out.println("Warning: No full calculation possible");
	}
	@Override
	public String toString() {
		String points=Stream.of(this.points).map(e->e.toString())
			.reduce(new String(),(a,b)->a+"; P:"+b);
		String sides=Stream.of(this.sides).map(e->e.toString())
			.reduce(new String(),(a,b)->a+"; S:"+b);
		String angles=Stream.of(this.angles).map(e->e.toString())
			.reduce(new String(),(a,b)->a+"; A:"+b);
		return String.format("Triangel\n%s\n%s\n%s\n",points,sides,angles);
	}
}
