package com.Ace009.nonLibrary.school;

import java.util.ArrayList;

import com.Ace009.library.Math.Fraction;

/**
 * abstract skeleton for creating a number sequence
 * <p>
 * (extends {@link java.util.ArrayList ArrayList(Double)})
 * @author Ace009
 */
public class NumberSequence {
	/** the list containing the values */
	protected ArrayList<Fraction> list=new ArrayList<>(1);
	/** use the calculateNext when calculating */
	protected boolean defNext = false;
	/** Type defenition of calculation */
	public enum Type {
		/** adds the fraction in every step */
		ADDITION,
		/** multiplies with the fraction in every step */
		MULTIPLICATION
	}
	/** type used in calculations */
	public final Type type;
	/** base calculation step */
	public final Fraction step;
	public NumberSequence() {this(new Fraction(1),Type.ADDITION);}
	public NumberSequence(Fraction step, Type type) { this(new Fraction(1), step, type); }
	public NumberSequence(Fraction first, Fraction step, Type type) {
		list.add(first);
		this.type=type;
		this.step=step;
	}
	/**
	 * calculates the next values of the sequence until the size is {@code to}
	 * @param to {@code int} limit of calculation
	 * @param next {@code boolean} if true: use {@link NumberSequence#calculateNext(Fraction)}, else use {@link NumberSequence#calculateAt(int)}
	 */
	public void calculateNextTo(int to) {
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
	 * @param next {@code boolean} if true: use {@link NumberSequence#calculateNext(Fraction)}, else use {@link NumberSequence#calculateAt(int)}
	 * @see NumberSequence#calculateNextTo(int)
	 */
	public void calculateNextAmount(int amount) {
		this.calculateNextTo(list.size()+amount);
	}
	public Fraction getAt(int pos) {
		if (list.size()>=pos && defNext) return list.get(pos-1);
		if (defNext) { calculateNextTo(pos); return getAt(pos); }
		return calculateAt(pos);
	}
	/**
	 * method that calculates the next value of the sequence,
	 * based on the provided value being the previous one
	 * @param prev {@code double} the previous value
	 * @return {@code double} the next value
	 */
	protected Fraction calculateNext(Fraction prev) {
		Fraction t_ = prev.get();
		switch (type) {
			case ADDITION: t_.add(step); break;
			case MULTIPLICATION: t_.multiplyBy(step); break;
		}
		return t_;
	}
	/**
	 * method that calculates the value at the provided index of the sequence
	 * @param pos {@code int} the position of the value to calculate
	 * @return {@code double} the value calculated
	 */
	protected Fraction calculateAt(int pos){
		Fraction t_ = step.get();
		switch (type) {
			case ADDITION: t_.multiplyBy(pos); t_.add(list.get(0)); break;
			case MULTIPLICATION: t_.toPowerOf(pos); t_.multiplyBy(list.get(0)); break;
		}
		return t_;
	}
}
