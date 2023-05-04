package com.Ace009.school.math;

import java.util.ArrayList;

abstract public class AbstractNumberSequence extends ArrayList<Double> {
	public void calculateNextTo(int to, boolean next) {
		this.ensureCapacity(to);
		int i=this.size();
		while (this.size()<to) {
			if (next) {this.add(calculateNext(this.get(i)));}
			else {this.add(calculateAt(i+1));}
			i++;
		}
	}
	//should be overridden
	private double calculateNext(double prev) {
		return prev+1;
	}
	private double calculateAt(int pos){
		return pos;
	}
}
