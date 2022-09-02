package dev.codewizz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Planny {
	
	public static void main(String[] args) {
		ArrayList<Container> containers = new ArrayList<>();
		ArrayList<Person> team = new ArrayList<>();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter size of team");
		int teamSize = scanner.nextInt();
		
		for(int i = 0; i < teamSize; i++) {
			System.out.println("Enter name of member [" + (i+1) + "]");
			String name = scanner.next();
			team.add(new Person(name, 16));
		}
		
		
		System.out.println("Enter size of load");
		int loadSize = scanner.nextInt();
		int totalTime = 0;
		for(int i = 0; i < loadSize; i++) {
			System.out.println("Enter code of container [" + (i+1) + "]");
			String code = scanner.next();
			System.out.println("Enter time of container [" + (i+1) + "]");
			int minutes = scanner.nextInt();
			totalTime += minutes;
			containers.add(new Container(code, minutes));
		}
		
		int averageTime = totalTime / teamSize;
		
		System.out.println("Total time: " + totalTime + " Average time: " + averageTime);
		
		Collections.sort(containers);
		
		if(teamSize <= loadSize) {
			
		}
		
		
		
		
		
		
		
		
		
		
		scanner.close();
	}
}
