package dev.codewizz;

import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person> {
	
	public String name = "";
	public int age = 0;
	public int total = 0;
	public ArrayList<Container> containers = new ArrayList<Container>();
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public static Person getLoser(List<Person> team) {
		int t = Integer.MAX_VALUE;
		Person person = null;
		
		for(Person p : team) {
			if(p.total < t) {
				t = p.total;
				person = p;
			}
		}
		
		return person;
	}

	@Override
	public int compareTo(Person o) {
		if(o.total > total) {
			return 0;
		} else {
			return -1;
		}
	}
}
