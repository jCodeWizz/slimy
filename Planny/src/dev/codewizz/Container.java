package dev.codewizz;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Container implements Comparable<Container> {

	static int counter = 1;
	
	public int originalIndex;
	public int minutes = 0;
	public String code = "";
	public boolean used = false;
	public Person p;
	
	public Container(String code, int minutes) {
		this.minutes = minutes;
		this.code = code;
		this.originalIndex = counter;
		counter++;
	}
	
	public Container(int minutes) {
		this.minutes = minutes;
		this.code = counter + "";
		this.originalIndex = counter;
		counter++;
	}

	@Override
	public int compareTo(Container o) {
		if(o.minutes > minutes)
			return 0;
		else {
			return -1;
		}
	}
	
	public static List<Container> getOrderedList() {
		List<Container> list = new CopyOnWriteArrayList<>();
		for(int i = 0; i < Planny.containers.size(); i++) {
			Container c = getByIndex(i+1);
			list.add(c);
		}
		
		
		return list;
	}
	
	public static Container getByIndex(int i) {
		
		for(Container c : Planny.containers) {
			if(c.originalIndex == i) {
				return c;
			}
		}
		
		System.out.println("COULNDT FIND " + i);
		
		return null;
	}
}
