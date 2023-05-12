package com.Ace009.non_library.school.math;

import java.util.ArrayList;

abstract public class AbstractNumberSequence extends ArrayList<Double> {
	//constructor
	static protected double a1=0;
	protected AbstractNumberSequence() {
		super();
		this.add(a1);
	}
	//instance methods
	protected void calculateNextTo(int to, boolean next) {
		this.ensureCapacity(to);
		int i=this.size();
		while (this.size()<to) {
			if (next) {this.add(calculateNext(this.get(i)));}
			else {this.add(calculateAt(i+1));}
			i++;
		}
	}
	protected void calculateNextAmount(int amount, boolean next) {
		this.calculateNextTo(this.size()+amount,next);
	}
	//	should be overridden
	protected double calculateNext(double prev) {
		return prev+1;
	}
	protected double calculateAt(int pos){
		return pos;
	}
}
