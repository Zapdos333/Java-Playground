package com.Ace009.nonLibrary.school;

import java.util.ArrayList;

import com.Ace009.library.Math.Fraction;

/**
 * abstract skeleton for creating a number sequence
 * <p>
 * (extends {@link java.util.ArrayList ArrayList(Double)})
 * @author Ace009
 */
abstract public class AbstractNumberSequence {
	/** the list containing the values */
	protected final ArrayList<Fraction> list=new ArrayList<>(1);
	/** use the calculateNext when calculating */
	protected final boolean defNext = false;
	/**
	 * calculates the next values of the sequence until the size is {@code to}
	 * @param to {@code int} limit of calculation
	 */
	protected void calculateNextTo(int to) {
		list.ensureCapacity(to);
		int i=list.size();
		while (list.size()<to) {
			if (defNext) {list.add(calculateNext(list.get(i)));}
			else {list.add(calculateAt(i+1));}
			i++;
		}
	}
	/**
	 * calculates the next {@code amount} values of the sequence
	 * @param amount {@code int} amount of values to calculate
	 * @see AbstractNumberSequence#calculateNextTo(int)
	 */
	protected void calculateNextAmount(int amount) {
		this.calculateNextTo(list.size()+amount);
	}
	/**
	 * method that calculates the next value of the sequence,
	 * based on the provided value being the previous one
	 * @param prev {@code double} the previous value
	 * @return {@code double} the next value
	 */
	abstract Fraction calculateNext(Fraction prev);
	/**
	 * method that calculates the value at the provided index of the sequence
	 * @param pos {@code int} the position of the value to calculate
	 * @return {@code double} the value calculated
	 */
	abstract Fraction calculateAt(int pos);
}
