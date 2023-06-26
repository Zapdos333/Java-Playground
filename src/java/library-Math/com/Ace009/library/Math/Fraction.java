package com.Ace009.library.Math;

import java.util.Map;

import com.Ace009.library.CClass.CMath;

/**
 * a class defining a number consisting of a multiplier and a divider
 * <p> defines behaviour and interactions of these numbers with each other and other numbers
 * @author Ace009
 */
public class Fraction {
	/** numerator of the fraction */
	protected int numerator;
	/** denominator of the fraction */
	protected int denominator;
	/** Type enum for Fractions */
	public static enum Type {
		/** is type numerator */
		numerator,
		/** is type denominator */
		denominator
	}
	/**
	 * direct constructor,
	 * <p> simply puts the values into their respective fields
	 * <p> note that the constructor uses {@code int} while the fields use {@code long} for more space for calculations
	 * @param numerator the numerator
	 * @param denominator the denominator
	 */
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
		reduceAndCheck();
	}
	/**
	 * default implementation of the direct constructor
	 * <p> denominator defaults to {@code 1}
	 * @param number the numerator
	 */
	public Fraction(int number) {
		this(number,1);
	}
	/** creates a Fraction with the value {@code 1} */
	public Fraction() {this(1);}
	/** */
	public Fraction(double number) {
		Map<String,Integer> t_ = CMath.seperate(number);
		this.numerator = t_.get("number");
		this.denominator = t_.get("exponent");
		reduceAndCheck();
	}
	/** @return a copy of this Fraction */
	public Fraction get() {return new Fraction().multiplyBy(this);}
	/**
	 * multiplies the fraction by the given number, by multiplying the numerator by {@code number}
	 * @param number number to multiply with
	 * @return {@code this}
	 */
	public Fraction multiplyBy(int number) { numerator *= number; return reduceAndCheck(); }
	/**
	 * multiplies the fraction by the given number,
	 * by multiplying with a new {@link #Fraction} based on {@code number}
	 * @param number number to multiply with
	 * @return {@code this}
	 */
	public Fraction multiplyBy(double number) { return this.multiplyBy(new Fraction(number)); }
	/**
	 * divide the fraction by the given number, by multiplying the denominator by {@code number}
	 * @param number number to divide with
	 * @return {@code this}
	 */
	public Fraction divideBy(int number) { denominator *= number; return reduceAndCheck(); }
	/**
	 * divide the fraction by the given number,
	 * by dividing by a new {@link #Fraction} based on {@code number}
	 * @param number number to divide with
	 * @return {@code this}
	 */
	public Fraction divideBy(double number) { return this.divideBy(new Fraction(number)); }
	/**
	 * calculates the fraction, by dividing the numerator by the denominator
	 * @return the {@code double} value of the fraction
	 */
	public double calculate() { return (double)numerator/denominator; }
	/**
	 * returns the requested part of the fraction
	 * @param part {@code Type} part of the fraction
	 * @return {@code long} value of the requested part
	 */
	public int get(Type part) {
		switch (part) {
			case numerator: return numerator;
			case denominator: return denominator;
			default: assert false; return 0;
		}
	}
	/**
	 * adds a number to the fraction, by {@code numerator += number*denominator}
	 * @param number the number to add
	 * @return {@code this}
	 */
	public Fraction add(int number)
	{ numerator += number*denominator; return reduceAndCheck(); }
	/**
	 * adds a number to the fraction, by adding a new {@link #Fraction} based on {@code number}
	 * @param number the number to add
	 * @return {@code this}
	 */
	public Fraction add(double number) { return this.add(new Fraction(number)); }
	/**
	 * subtracts a number from the fraction, by {@code numerator -= number*denominator}
	 * @param number the number to subtract
	 * @return {@code this}
	 */
	public Fraction subtract(int number)
	{ numerator -= number*denominator; return reduceAndCheck(); }
	/**
	 * subtracts a number from the fraction, by adding a new {@link #Fraction} based on {@code number}
	 * @param number the number to subtract
	 * @return {@code this}
	 */
	public Fraction subtract(double number) { return this.subtract(new Fraction(number)); }
	/**
	 * multiplies the fraction by the number, by multiplying the numerator by it
	 * @param number the number to multiply by
	 * @return {@code this}
	 */
	public Fraction multiplyBy(Fraction number) {
		numerator *= number.get(Type.numerator);
		denominator *= number.get(Type.denominator);
		return reduceAndCheck();
	}
	/**
	 * divides the fraction by the number, by multiplying the denominator by it
	 * @param number the number to divide by
	 * @return {@code this}
	 */
	public Fraction divideBy(Fraction number) {
		numerator *= number.get(Type.denominator);
		denominator *= number.get(Type.numerator);
		return reduceAndCheck();
	}
	/**
	 * reduces the fraction and checks if its values are valid
	 * <p> reduces the fraction by getting the {@link Calculations#gcd(int, int) greatest common divisor} of the numerator and denominator
	 * and then dividing them both by it
	 * <p> this is run at the end of every method that modifies the fraction (except for extension methods)
	 * @return {@code this}
	 */
	protected Fraction reduceAndCheck() {
		int Ldiv = Calculations.gcd(numerator, denominator);
		numerator /= Ldiv; denominator /= Ldiv;
		if (numerator<0 && denominator<0) { numerator*=-1; denominator*=-1; }
		return check();
	}
	/**
	 * extends the fraction by the given multiplier
	 * @param multiplier extension multiplier
	 * @return {@code this}
	 */
	protected Fraction extendBy(int multiplier) {
		numerator *= multiplier; denominator *= multiplier;
		return check();
	}
	/**
	 * extends the fraction to the given number in the given part
	 * @param part part of the fraction to extend
	 * @param number number to extend to
	 * @return {@code this}
	 */
	protected Fraction extendTo(Type part, int number) {
		int multiplier=1;
		switch (part) {
			case numerator: multiplier = Calculations.lcm(number,numerator); break;
			case denominator: multiplier = Calculations.lcm(number,denominator); break;
		}
		extendBy(multiplier);
		switch (part) {
			case numerator: assert numerator%number==0; break;
			case denominator: assert denominator%number==0; break;
			default: assert false;
		}
		return this;
	}
	/**
	 * default implementation of {@link #extendTo(Type, int)},
	 * with {@link Type} being {@code Type#denominator}
	 * @param number number to extend to
	 * @return {@code this}
	 */
	protected Fraction extendTo(int number) { return extendTo(Type.denominator, number); }
	/**
	 * extends the fractions to their denominators {@link Calculations#lcm(int, int) least common multiple}
	 * and then adds their numerators
	 * @param number Fraction to add
	 * @return {@code this}
	 */
	public Fraction add(Fraction number) {
		int t_ = Calculations.lcm(number.get(Type.denominator),this.get(Type.denominator));
		this.extendTo(t_); number.extendTo(t_);
		assert (this.get(Type.denominator) == number.get(Type.denominator)) : "noncommon dividers";
		this.numerator += number.get(Type.numerator);
		return reduceAndCheck();
	}
	/**
	 * extends the fractions to their denominators {@link Calculations#lcm(int, int) least common multiple}
	 * and then subtracts their numerators
	 * @param number Fraction to subtract
	 * @return {@code this}
	 */
	public Fraction subtract(Fraction number) {
		int t_ = Calculations.lcm(number.get(Type.denominator),this.get(Type.denominator));
		this.extendTo(t_); number.extendTo(t_);
		assert (this.get(Type.denominator) == number.get(Type.denominator)) : "noncommon dividers";
		this.numerator -= number.get(Type.numerator);
		return reduceAndCheck();
	}
	/**
	 * takes the fraction to the power of the given number,
	 * by taking the numerator and denominator to the power of the number
	 * @param number the exponent (can be a non-whole number for roots)
	 * @throws ArithmeticException if the result isn't a whole number, only possible with non-whole exponents
	 * @return {@code this}
	 */
	public Fraction toPowerOf(double number) throws ArithmeticException {
		if (number != Math.round(number)) {
			double tN_ = Math.pow(numerator,number);
			double tD_ = Math.pow(denominator,number);
			if (tN_ != Math.round(tN_)||tD_ != Math.round(tD_))
				throw new ArithmeticException("non-whole root result");
			else { numerator=(int)Math.round(tN_);denominator=(int)Math.round(tD_); }
		}
		if (number < 0) {
			denominator = (int)Math.pow(numerator,number);
			numerator =(int)Math.pow(denominator,number);
		} else {
			numerator = (int)Math.pow(numerator,number);
			denominator =(int)Math.pow(denominator,number);
		}
		return reduceAndCheck();
	}
	/**
	 * checks the values of the fraction
	 * <p> checks if the values of the fraction are outside the range of an {@code integer},
	 * and if the denominator is {@code 0} (division by zero)
	 * <p> this is run at the end of every method that modifies the fraction
	 * @throws ArithmeticException if the fraction is invalid
	 * @return {@code this}
	 */
	protected Fraction check() throws ArithmeticException {
		if (numerator > Integer.MAX_VALUE) throw new ArithmeticException("numerator out of Integer range");
		if (denominator > Integer.MAX_VALUE) throw new ArithmeticException("denominator out Integer range");
		if (denominator < Integer.MIN_VALUE) throw new ArithmeticException("denominator out Integer range");
		if (numerator < Integer.MIN_VALUE) throw new ArithmeticException("numerator out Integer range");
		if (denominator == 0) throw new ArithmeticException("attempt to divide by zero");
		return this;
	}
	/**
	 * method to always calculate the power of a Fraction
	 * <p> is less accurate, because if {@link #toPowerOf(double)} throws an {@link ArithmeticException},
	 * it resorts to {@link #Fraction(double)} of {@link Math#pow(double, double)} using {@link #calculate()}
	 * @return {@code this} 
	 */
	public Fraction inAccPow(double power) {
		try { return this.toPowerOf(power); } //if the root is a whole number, is more accurate
		catch (IllegalArgumentException e) {}
		Fraction out = new Fraction(Math.pow(this.calculate(),power)); //otherwise resort to Math.sqrt(double)
		this.numerator = out.numerator; this.denominator = out.denominator; //override this
		return reduceAndCheck();
	}
	@Override
	public String toString() {
		return String.format("%d/%d",numerator,denominator);
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || this.getClass()!= o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return (numerator == fraction.numerator) && (denominator == fraction.denominator);
	}
	@Override
	public int hashCode() {
		return (int)Math.ceil(calculate());
	}
}