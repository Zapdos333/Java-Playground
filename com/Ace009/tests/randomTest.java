package com.Ace009.tests;

import java.util.ArrayList;
import com.Ace009.library.CClass.CString;

final class randomTest {
	private class Value {
		int i1; int i2; int nr;
		public value(int c_i1, int c_i2, int c_nr) {
			i1=c_i1;i2=c_i2;nr=c_nr;
		}
		@Override
		public String toString() {
			StringBuilder output=new StringBuilder();
			output.append(CString.formatToLength(String.valueOf(this.i2),3));
			output.append(":");
			output.append(CString.formatToLength(String.valueOf(this.i1),3));
			output.append(":");
			output.append(CString.formatToLength(String.valueOf(this.nr),3));
			return output.toString();
		}
	}
	ArrayList<Value> list = new ArrayList<>();
	public void addNewValue(int target) { //WIP here
		int nr=0;
		for (int i2=1; i2<=target; i2++) {
			int i1=0;
			while (nr!=i2) {
				i1++;
				nr=(int)Math.ceil(Math.random()*target);
			}
			list.add(new Value(i1,nr,))
		}
	}

	public static void main(String[] args) throws AssertionError {
		if (args.length<2) {
			throw new AssertionError("not enough arguments given");
		}
		int target=Integer.parseInt(args[0]);
		int iterations=Integer.parseInt(args[1]);
		//


	}
}
