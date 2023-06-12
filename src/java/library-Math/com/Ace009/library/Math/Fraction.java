package com.Ace009.library.Math;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import com.Ace009.library.CClass.CMath;
import com.Ace009.library.CClass.CArray;
import com.Ace009.library.CClass.CList;

/**
 * a class defining a number consisting of a multiplier and a divider
 * <p> defines behaviour and interactions of these numbers with each other and other numbers
 * @author Ace009
 */
public class Fraction {
	/**
	 * subclass of ComplexNumber, which provides various methods to perform more complex calculations,
	 * necessary for dealing with fractions
	 */
	public static class calculations {
		/** don't */
		private calculations() {}
		/**
		 * turns a number into an {@code int[]} containing the prime factors of the number
		 * @param number number to dissect
		 * @return the array of prime factors
		 * @see CMath#getPrimesUpTo(int)
		 */
		public static int[] seperateToPrimes(long number) {
			int[] check = CMath.getPrimesUpTo((int)Math.ceil(number/2));
			List<Integer> output = new ArrayList<>();
			for (int i = 1; i < check.length; i++) {
				if (number % check[i] == 0) {
					output.add(check[i]);
					number /= check[i];
					i--;
				}
			}
			return output.stream().mapToInt(i->i).toArray();
		}
		/**
		 * returns the greatest common divisor of the two numbers
		 * @param n1 number 1
		 * @param n2 number 2
		 * @return the greatest common divisor
		 */
		public static int gcd(long n1, long n2) {
			int[] p1 = seperateToPrimes(n1);
			int[] p2 = seperateToPrimes(n2);
			List<Integer> output = new ArrayList<>();
			if (p1.length >= p2.length) {
				for (int i : p1) {
					if ( CArray.indexOf(CArray.asObjectArray(p2), i)>=0 ) {
						output.add(i);
					}
				}
			} else  {
				for (int i : p2) {
					if ( CArray.indexOf(CArray.asObjectArray(p1), i)>=0 ) {
						output.add(i);
					}
				}
			}
			return output.stream().mapToInt(i->i).reduce(1,(a,b)->a*b);
		}
		/**
		 * returns the least common multiple of the two numbers
		 * @param n1 number 1
		 * @param n2 number 2
		 * @return the least common multiple
		 */
		public static int lcm(long n1, long n2) {
			List<Integer> p1 = CArray.asList(CArray.asObjectArray(seperateToPrimes(n1)));
			List<Integer> p2 = CArray.asList(CArray.asObjectArray(seperateToPrimes(n2)));
			List<Collection<Integer>> t_ = new ArrayList<Collection<Integer>>();
			t_.add(p1);t_.add(p2);
			List<Integer> output = CList.merge(t_);
			return output.stream().mapToInt(i->i).reduce(1,(a,b)->a*b);
		}
	}
	/** numerator of the fraction */
	private long numerator;
	/** denominator of the fraction */
	private long denominator;
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
	/**
	 * multiplies the fraction by the given number, by multiplying the numerator by {@code number}
	 * @param number number to multiply with
	 */
	public void multipyBy(int number) { numerator *= number; reduceAndCheck(); }
	/**
	 * divide the fraction by the given number, by multiplying the denominator by {@code number}
	 * @param number number to divide with
	 */
	public void divideBy(int number) { denominator *= number; reduceAndCheck(); }
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
	public long get(Type part) {
		switch (part) {
			case numerator: return numerator;
			case denominator: return denominator;
			default: return 0;
		}
	}
	/**
	 * adds a number to the fraction, by {@code numerator += number*denominator}
	 * @param number the number to add
	 */
	public void add(int number) { numerator += number*denominator; reduceAndCheck(); }
	/**
	 * subtracts a number from the fraction, by {@code numerator -= number*denominator}
	 * @param number the number to subtract
	 */
	public void subtract(int number) { numerator -= number*denominator; reduceAndCheck(); }
	/**
	 * multiplies the fraction by the number, by multiplying the numerator by it
	 * @param number the number to multiply by
	 */
	public void multiplyBy(Fraction number) { numerator *= number.get(Type.numerator); denominator *= number.get(Type.denominator); reduceAndCheck(); }
	/**
	 * divides the fraction by the number, by multiplying the denominator by it
	 * @param number the number to divide by
	 */
	public void divideBy(Fraction number) { numerator *= number.get(Type.denominator); denominator *= number.get(Type.numerator); reduceAndCheck(); }
	/**
	 * reduces the fraction and checks if its values are valid
	 * <p> reduces the fraction by getting the greatest common divisor of the numerator and denominator
	 * and then dividing them both by it
	 * <p> this is run at the end of every method that modifies the fraction (except for extension methods)
	 * @see Fraction.calculations#gcd(long, long)
	 */
	public void reduceAndCheck() {
		int Ldiv = calculations.gcd(numerator, denominator);
		assert (numerator % Ldiv == 0) && (denominator % Ldiv == 0) : "wrong greatest common divisor";
		numerator /= Ldiv; denominator /= Ldiv;
		check();
	}
	/**
	 * extends the fraction by the given multiplier
	 * @param multiplier extension multiplier
	 */
	public void extendBy(int multiplier) {
		Fraction mult = new Fraction(multiplier,multiplier);
		mult.numerator=multiplier;mult.denominator=multiplier;
		multiplyBy(mult);
		check();
	}
	/**
	 * extends the fraction to the given number in the given part
	 * @param part part of the fraction to extend
	 * @param number number to extend to
	 */
	public void extendTo(Type part, long number) {
		int Tmultiplier=1;
		switch (part) {
			case numerator: Tmultiplier = (int)Math.floor(number/numerator); break;
			case denominator: Tmultiplier = (int)Math.floor(number/denominator); break;
		}
		extendBy(Tmultiplier);
		check();
	}
	/**
	 * default implementation of {@link #extendTo(Type, long)},
	 * with {@link Type} being {@code Type#denominator}
	 * @param number number to extend to
	 */
	public void extendTo(long number) { extendTo(Type.denominator, number); check(); }
	/**
	 * extends the fractions to their denominators {@link calculations#lcm(long, long)}
	 * and then adds their numerators
	 * @param number Fraction to add
	 */
	public void add(Fraction number) {
		int t_ = calculations.lcm(number.get(Type.denominator),this.get(Type.denominator));
		this.extendTo(t_); number.extendTo(t_);
		assert (this.get(Type.denominator) == number.get(Type.denominator)) : "noncommon dividers";
		this.numerator += number.get(Type.numerator);
		reduceAndCheck();
	}
	/**
	 * extends the fractions to their denominators {@link calculations#lcm(long, long)}
	 * and then subtracts their numerators
	 * @param number Fraction to subtract
	 */
	public void subtract(Fraction number) {
		int t_ = calculations.lcm(number.get(Type.denominator),this.get(Type.denominator));
		this.extendTo(t_); number.extendTo(t_);
		assert (this.get(Type.denominator) == number.get(Type.denominator)) : "noncommon dividers";
		this.numerator -= number.get(Type.numerator);
		reduceAndCheck();
	}
	/**
	 * takes the fraction to the power of the given number,
	 * by taking the numerator and denominator to the power of the number
	 * @param number the exponent
	 * @throws IllegalArgumentException if {@code number} is negative, because roots aren't displayable with fractions
	 */
	public void toPowerOf(int number) throws IllegalArgumentException {
		if (number > 0) { throw new IllegalArgumentException("Roots are not implemented");}
		numerator = (long)Math.pow(numerator, number);
		denominator = (long)Math.pow(denominator, number);
		reduceAndCheck();
	}
	/**
	 * checks the values of the fraction
	 * <p> checks if the values of the fraction are outside the range of a {@code long},
	 * and if the denominator is {@code 0} (division by zero)
	 * <p> this is run at the end of every method that extends the fraction
	 * @throws ArithmeticException if the fraction is invalid
	 */
	private void check() throws ArithmeticException {
		if (numerator > Long.MAX_VALUE) throw new ArithmeticException("numerator out of Long range");
		if (denominator > Long.MAX_VALUE) throw new ArithmeticException("denominator out Long range");
		if (denominator < Long.MIN_VALUE) throw new ArithmeticException("denominator out Long range");
		if (numerator < Long.MIN_VALUE) throw new ArithmeticException("numerator out Long range");
		if (denominator == 0) throw new ArithmeticException("attempt to divide by zero");
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