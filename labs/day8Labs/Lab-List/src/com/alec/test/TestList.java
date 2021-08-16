package com.alec.test;

import com.alec.datastructures.List;

public class TestList {
	public static void main(String[] args) {
		List list = new List();
		list.set(0,  "Apple");
		System.out.println(list.get(0));
		
		int index = list.add("Banana");
		System.out.println(index);
		System.out.println(list.get(index));
		
		String value = list.remove();
		System.out.println(value);
		
		value = list.remove();
		System.out.println(value);
	}
}
