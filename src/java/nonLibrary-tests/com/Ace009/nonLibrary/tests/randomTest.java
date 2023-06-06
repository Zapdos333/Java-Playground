package com.Ace009.nonLibrary.tests;

import java.util.ArrayList;
import java.util.stream.Collectors;
import com.Ace009.library.CClass.CString;

/**
 * an over-the-top experimen for randomness
 * @author Ace009
 */
final class randomTest {
	private static class Value {
		int i1; int i2; int nr;
		public Value(int c_i1, int c_i2, int c_nr) {
			i1=c_i1;i2=c_i2;nr=c_nr;
		}
		@Override
		public String toString() {
			StringBuilder output=new StringBuilder();
			output.append(CString.formatToLength(String.valueOf(this.i1),3));
			output.append(":");
			output.append(CString.formatToLength(String.valueOf(this.i2),3));
			output.append(":");
			output.append(CString.formatToLength(String.valueOf(this.nr),3));
			return output.toString();
		}
	}
	static ArrayList<Value> list = new ArrayList<>();
	public static ArrayList<Value> newValues(int maxTarget) {
		int nr=0;
		ArrayList<Value> output=new ArrayList<>();
		for (int i2=1; i2<=maxTarget; i2++) {
			int i1=0;
			while (nr!=i2) {
				i1++;
				nr=(int)Math.ceil(Math.random()*maxTarget);
			}
			output.add(new Value(0,nr,i1));
		}
		return output;
	}
	public static String listToString(ArrayList<Value> list) {
		int line=0;
		StringBuilder output=new StringBuilder();
		for (Value entry : list) {
			if (entry.i1 != line) {
				output.deleteCharAt(output.length()-1);
				output.append("\n");
				line=entry.i1;
			}
			output.append(entry.toString());
			output.append(",");
		}
		output.deleteCharAt(output.length()-1);
		return output.toString();
	}
	public static void main(String[] args) throws AssertionError {
		if (args.length<2) {
			throw new AssertionError("not sufficient arguments given");
		}
		int target=Integer.parseInt(args[0]);
		int iterations=Integer.parseInt(args[1]);
		int i3=0;
		while (i3<iterations) {
			final int iter=i3;
			list.addAll(newValues(target).stream().map(e -> new Value(iter,e.i2,e.nr)).collect(Collectors.toList()));
			i3++;
		}
		System.out.println(listToString(list));
	}
}
