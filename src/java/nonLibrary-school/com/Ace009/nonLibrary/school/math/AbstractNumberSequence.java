package com.Ace009.nonLibrary.school.math;

import java.util.ArrayList;

/**
 * abstract skeleton for creating a number sequence
 * <p>
 * (extends {@link java.util.ArrayList ArrayList(Double)})
 * @author Ace009
 */
abstract public class AbstractNumberSequence extends ArrayList<Double> {
	/** first value of the sequence */
	static protected double a1=0;
	/** constructor, creates the ArrayList and adds the first element */
	protected AbstractNumberSequence() {
		super();
		this.add(a1);
	}
	/**
	 * calculates the next values of the sequence up to value number {@code to}
	 * @param to {@code int} limit of calculation
	 * @param next {@code boolean} if true: use {@link #calculateNext(double)}, else use {@link #calculateAt(double)}
	 */
	protected void calculateNextTo(int to, boolean next) {
		this.ensureCapacity(to);
		int i=this.size();
		while (this.size()<to) {
			if (next) {this.add(calculateNext(this.get(i)));}
			else {this.add(calculateAt(i+1));}
			i++;
		}
	}
	/**
	 * calculates the next {@code amount} values of the sequence
	 * @param amount {@code int} amount of values to calculate
	 * @param next {@code boolean} if true: use {@link #calculateNext(double)}, else use {@link #calculateAt(double)}
	 * @see #calculateNextTo(int, boolean)
	 */
	protected void calculateNextAmount(int amount, boolean next) {
		this.calculateNextTo(this.size()+amount,next);
	}
	/**
	 * method that calculates the next value of the sequence,
	 * based on the provided value being the previous one
	 * @param prev {@code double} the previous value
	 * @return {@code double} the next value
	 */
	protected double calculateNext(double prev) {
		return prev+1;
	}
	/**
	 * method that calculates the value at the provided position of the sequence
	 * @param pos {@code int} the position of the value to calculate
	 * @return {@code double} the value calculated
	 */
	protected double calculateAt(int pos){
		return pos;
	}
}
