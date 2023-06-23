package com.Ace009.nonLibrary.school;

import java.util.ArrayList;

/**
 * a mathamatical number sequence
 * <p> allows creation of {@link ArrayList} based number sequences,
 * with values of type {@code T}
 * @see NumberSequence implementation with {@link com.Ace009.library.Math.Fraction Fraction}
 */
abstract public class AbstractNumberSequence<T> {
	/** the list containing the values */
	protected final ArrayList<T> list=new ArrayList<>(1);
	/** use the calculateNext when calculating */
	public final boolean defNext = false;
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
	abstract T calculateNext(T prev);
	/**
	 * method that calculates the value at the provided index of the sequence
	 * @param pos {@code int} the position of the value to calculate
	 * @return {@code double} the value calculated
	 */
	abstract T calculateAt(int pos);
	/**
	 * returns the Value of the {@code pos} element
	 * <p> checks first if the element exists in the {@link #list}
	 * @param pos index of the returned element
	 * @return element returned
	 */
	public T getAt(int pos) {
		if (list.size()>=pos) return list.get(pos-1);
		else if (defNext) { calculateNextTo(pos); return getAt(pos); }
		else return calculateAt(pos);
	}
}
