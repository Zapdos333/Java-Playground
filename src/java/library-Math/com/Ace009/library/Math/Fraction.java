package com.Ace009.library.Math;

/**
 * a class defining a number consisting of a multiplier and a divider
 * <p> defines behaviour and interactions of these numbers with each other and other numbers
 * @author Ace009
 */
public class Fraction {
	/** numerator of the fraction */
	private int numerator;
	/** denominator of the fraction */
	private int denominator;
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
	/** @return a copy of this Fraction */
	public Fraction get() {return new Fraction().multiplyBy(this);}
	/**
	 * multiplies the fraction by the given number, by multiplying the numerator by {@code number}
	 * @param number number to multiply with
	 * @return {@code this}
	 */
	public Fraction multiplyBy(int number) { numerator *= number; return reduceAndCheck(); }
	/**
	 * divide the fraction by the given number, by multiplying the denominator by {@code number}
	 * @param number number to divide with
	 * @return {@code this}
	 */
	public Fraction divideBy(int number) { denominator *= number; return reduceAndCheck(); }
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
			default: return 0;
		}
	}
	/**
	 * adds a number to the fraction, by {@code numerator += number*denominator}
	 * @param number the number to add
	 * @return {@code this}
	 */
	public Fraction add(int number) {
		numerator += number*denominator;
		return reduceAndCheck();
	}
	/**
	 * subtracts a number from the fraction, by {@code numerator -= number*denominator}
	 * @param number the number to subtract
	 * @return {@code this}
	 */
	public Fraction subtract(int number) {
		numerator -= number*denominator;
		return reduceAndCheck();
	}
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
	 * <p> reduces the fraction by getting the greatest common divisor of the numerator and denominator
	 * and then dividing them both by it
	 * <p> this is run at the end of every method that modifies the fraction (except for extension methods)
	 * @see Calculations#gcd(int, int)
	 * @return {@code this}
	 */
	protected Fraction reduceAndCheck() {
		int Ldiv = Calculations.gcd(numerator, denominator);
		numerator /= Ldiv; denominator /= Ldiv;
		return check();
	}
	/**
	 * extends the fraction by the given multiplier
	 * @param multiplier extension multiplier
	 * @return {@code this}
	 */
	public Fraction extendBy(int multiplier) {
		numerator *= multiplier; denominator *= multiplier;
		check(); return this;
	}
	/**
	 * extends the fraction to the given number in the given part
	 * @param part part of the fraction to extend
	 * @param number number to extend to
	 * @return {@code this}
	 */
	public Fraction extendTo(Type part, long number) {
		int Tmultiplier=1;
		switch (part) {
			case numerator: Tmultiplier = (int)Math.floor(number/numerator); break;
			case denominator: Tmultiplier = (int)Math.floor(number/denominator); break;
		}
		return extendBy(Tmultiplier);
	}
	/**
	 * default implementation of {@link #extendTo(Type, long)},
	 * with {@link Type} being {@code Type#denominator}
	 * @param number number to extend to
	 * @return {@code this}
	 */
	public Fraction extendTo(long number) { return extendTo(Type.denominator, number); }
	/**
	 * extends the fractions to their denominators {@link Calculations#lcm(int, int)}
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
	 * extends the fractions to their denominators {@link Calculations#lcm(int, int)}
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
	 * @param number the exponent
	 * @throws IllegalArgumentException if {@code number} is negative, because roots aren't displayable with fractions
	 * @return {@code this}
	 */
	public Fraction toPowerOf(int number) throws IllegalArgumentException {
		if (number > 0) { throw new IllegalArgumentException("Roots are not implemented");}
		numerator = (int)Math.pow(numerator, number);
		denominator =(int) Math.pow(denominator, number);
		return reduceAndCheck();
	}
	/**
	 * checks the values of the fraction
	 * <p> checks if the values of the fraction are outside the range of a {@code long},
	 * and if the denominator is {@code 0} (division by zero)
	 * <p> this is run at the end of every method that extends the fraction
	 * @throws ArithmeticException if the fraction is invalid
	 * @return {@code this}
	 */
	protected Fraction check() throws ArithmeticException {
		if (numerator > Long.MAX_VALUE) throw new ArithmeticException("numerator out of Long range");
		if (denominator > Long.MAX_VALUE) throw new ArithmeticException("denominator out Long range");
		if (denominator < Long.MIN_VALUE) throw new ArithmeticException("denominator out Long range");
		if (numerator < Long.MIN_VALUE) throw new ArithmeticException("numerator out Long range");
		if (denominator == 0) throw new ArithmeticException("attempt to divide by zero");
		return this;
	}
	@Override
	public String toString() {
		return String.format("%d/%d=%f",numerator,denominator,calculate());
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
		return (int)Math.ceil(numerator/denominator);
	}
}