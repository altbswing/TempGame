package util;

import java.util.ArrayList;

public class Queue {

	private String[] mainArray;
	
	private final int width;
	private int size = 0;
	private int headPointer = 0;
	
	public Queue() {
		this(10);
	}
	
	public Queue(int maxSize) {
		this(maxSize, 20);
	}
	
	public Queue(int maxSize, int width) {
		this.width = width;
		this.mainArray = new String[maxSize];
	}
	
	public String get(int i) {
		if(i > this.size || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		return this.mainArray[getIndex(i)];
	}
	
	public void set(int i, String str) {
		if(i > this.size) {
			throw new IndexOutOfBoundsException();
		}
		int idx = getIndex(i);
		this.mainArray[idx] = str;
	}
	
	public int size() {
		return this.size;
	}
	
	public void add(String str) {
		int idx = getIndex(size);
		this.mainArray[idx] = str;
		if(size < mainArray.length) {
			size++;
		} else {
			headPointer++;
		}
	}
	
	@Override
	public String toString() {
		ArrayList<String> al = new ArrayList<String>();
		for (int i = 0; i < mainArray.length; i++) {
			al.add(mainArray[i] + "\t");
		}
		return al.toString();
	}
	
	private int getIndex(int idx) {
		int pt = headPointer + idx;
		while(pt >= mainArray.length || headPointer < 0 ) {
			if (pt >= mainArray.length) {
				pt -= mainArray.length;
			} else if(pt < 0) {
				pt += mainArray.length;
			}
		}
		return pt;
	}
	
	public static void main(String[] args) {
		Queue sqTest = new Queue(14);
		for (int i = 0; i < sqTest.size(); i++) {
			System.out.println(sqTest.get(i));
		}
	}
}
