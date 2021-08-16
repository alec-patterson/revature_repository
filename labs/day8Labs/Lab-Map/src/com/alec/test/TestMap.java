package com.alec.test;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class TestMap {
	
	public static void main(String[] args) {
		HashMap<String, String> dictionary = new HashMap<>();
		dictionary.put("apple", "A forbidden fruit");
		dictionary.put("banana", "A yellow fruit that van be peeled");
		dictionary.put("cantaloupe", "A melon fruit that has an orange flesh");
		System.out.println(dictionary);
		
		//get value from map using the key
		String key = "apple";
		String value = dictionary.get(key);
		System.out.println("The value for the key: " + key + " is " + value);
		
		//define a new TreeMap
		TreeMap<String, String> sortedDictionary = new TreeMap<>();
		sortedDictionary.putAll(dictionary);
		System.out.println(sortedDictionary);
		
		//add another element
		sortedDictionary.put("beets",  "An actual vegetable");
		System.out.println(sortedDictionary);
		
		//re-add an element
		sortedDictionary.put("beets",  "A better explanation");
		System.out.println(sortedDictionary);
	
		//create a set of keys
		Set<String> keys = sortedDictionary.keySet();
		
		for(String s: keys) {
			System.out.println("Key: " + s);
		}
	}

}
