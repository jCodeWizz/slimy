package dev.codewizz;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Planny {
	
	public static void main(String[] args) {
		List<Container> containers = new CopyOnWriteArrayList<>();
		List<Person> team = new CopyOnWriteArrayList<>();
		List<Person> teamYoung = new CopyOnWriteArrayList<>();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter size of team (16 or older)");
		int teamSize = scanner.nextInt();
		
		for(int i = 0; i < teamSize; i++) {
			System.out.println("Enter name of member [" + (i+1) + "]");
			String name = scanner.next();
			team.add(new Person(name, 16));
		}
		
		teamSize = 6;
		team.add(new Person("Daniel", 16));
		team.add(new Person("Wendly", 16));
		team.add(new Person("DaanG", 16));
		team.add(new Person("DaanR", 16));
		team.add(new Person("Melissa", 16));
		team.add(new Person("Iliaas", 16));

		System.out.println("Enter size of team (15)");
		int teamSizeYoung = scanner.nextInt();
		teamSizeYoung = 3;
		
		for(int i = 0; i < teamSizeYoung; i++) {
			System.out.println("Enter name of member [" + (i+1) + "]");
			String name = scanner.next();
			teamYoung.add(new Person(name, 15));
		}
		
		teamYoung.add(new Person("Surafiel", 15));
		teamYoung.add(new Person("Madina", 15));
		teamYoung.add(new Person("Arsema", 15));
		
		System.out.println("Enter size of load");
		int loadSize = scanner.nextInt();
		int totalTime = 0;
		for(int i = 0; i < loadSize; i++) {
			System.out.println("Enter code of container [" + (i+1) + "]");
			String code = scanner.next();
			System.out.println("Enter time of container [" + (i+1) + "]");
			int minutes = scanner.nextInt();
			containers.add(new Container(code, minutes));
		}

		containers.add(new Container("1", 10));
		containers.add(new Container("2", 10));
		containers.add(new Container("3", 10));
		containers.add(new Container("4", 10));
		containers.add(new Container("5", 10));
		containers.add(new Container("6", 10));
		containers.add(new Container("7", 10));
		containers.add(new Container("8", 10));
		containers.add(new Container("9", 10));
		containers.add(new Container("10", 10));
		containers.add(new Container("11", 10));
		containers.add(new Container("12", 10));
		containers.add(new Container("13", 10));
		containers.add(new Container("14", 10));
		containers.add(new Container("15", 10));
		containers.add(new Container("16", 10));
		containers.add(new Container("17", 10));
		containers.add(new Container("18", 10));
		containers.add(new Container("19", 10));
		containers.add(new Container("20", 10));
		
		for(Container c : containers) {
			totalTime += c.minutes;
		}
		
		/*
		containe7rs.add(new Container("111", 70));
		containers.add(new Container("222", 60));
		containers.add(new Container("333", 70));
		containers.add(new Container("444", 20));
		containers.add(new Container("555", 10));
		containers.add(new Container("666", 10));
		containers.add(new Container("777", 10));
		*/

		Collections.sort(containers);
		
		for(Container c : containers) {
			Collections.sort(teamYoung);
			for(Person p : teamYoung) {
				if(p.total + c.minutes <= 110) {
					totalTime -= c.minutes;
					p.total += c.minutes;
					p.containers.add(c);
					containers.remove(c);
				}
			}
		}
		
		int averageTime = totalTime / teamSize;
		
		Collections.sort(containers);
		
		for(Container c : containers) {
			Person p = Person.getLoser(team);
			p.containers.add(c);
			p.total += c.minutes;
		}
		System.out.println("-----------------------------");
		for(Person p : teamYoung) {
			int total = 0;
			System.out.println(p.name + " (" + p.age + "): ");
			System.out.println("Containers [" + (p.containers.size()) + "]: ");
			System.out.println();
			for(Container c : p.containers) {
				System.out.println("Container [" + c.code + "]: " + c.minutes + " minutes");
				total += c.minutes;
			}
			System.out.println();
			System.out.println("Total time: " + total);
			System.out.println("-----------------------------");
		}
		
		System.out.println("Total time: " + totalTime + " Average time: " + averageTime);

		for(Person p : team) {
			int total = 0;
			System.out.println(p.name + " (" + p.age + "): ");
			System.out.println("Containers [" + (p.containers.size()) + "]: ");
			System.out.println();
			for(Container c : p.containers) {
				System.out.println("Container [" + c.code + "]: " + c.minutes + " minutes");
				total += c.minutes;
			}
			System.out.println();
			System.out.println("Total time: " + total);
			System.out.println("-----------------------------");
		}
		
		scanner.close();
	}
}
