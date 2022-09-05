package dev.codewizz;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Planny {

	static List<Container> containers = new CopyOnWriteArrayList<>();
	static List<Person> team = new CopyOnWriteArrayList<>();
	static List<Person> teamYoung = new CopyOnWriteArrayList<>();

	static int teamSize, loadSize, totalTime, averageTime;
	static int teamSizeYoung;

	static boolean CONSOLE = false;

	static File file;
	static File containerFile;
	static File teamFile;

	public static void main(String[] args) {
		if (!CONSOLE) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH;mm;ss");
			LocalDateTime now = LocalDateTime.now();

			file = new File("C:\\Users\\tomhe\\Desktop\\vulplanningen\\vulplanning_" + dtf.format(now) + ".txt");
			containerFile = new File("C:\\Users\\tomhe\\Desktop\\vulplanningen\\containers.txt");
			teamFile = new File("C:\\Users\\tomhe\\Desktop\\vulplanningen\\team.txt");

			try {
				boolean young = false;
				Scanner scannerTeam = new Scanner(teamFile);
				while (scannerTeam.hasNextLine()) {
					String line = scannerTeam.nextLine();
					if (line.equalsIgnoreCase("")) {
						young = !young;
					} else {
						if (young) {
							teamYoung.add(new Person(line, 15));
						} else {
							team.add(new Person(line, 16));
						}
					}
				}
				scannerTeam.close();

				teamSize = team.size();
				teamSizeYoung = teamYoung.size();

				Scanner scannerContainer = new Scanner(containerFile);
				while (scannerContainer.hasNextLine()) {
					String line = scannerContainer.nextLine();
					int index = line.indexOf(',');
					if (index == -1) {
						int minutes = Integer.parseInt(line);
						containers.add(new Container(minutes));
					} else {
						int minutes = Integer.parseInt(line.substring(index + 1, line.length()));
						String code = line.substring(0, index);
						containers.add(new Container(code, minutes));
					}

				}
				scannerContainer.close();

				loadSize = containers.size();

				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter size of team (16 or older)");
			teamSize = scanner.nextInt();

			for (int i = 0; i < teamSize; i++) {
				System.out.println("Enter name of member [" + (i + 1) + "]");
				String name = scanner.next();
				team.add(new Person(name, 16));
			}

			System.out.println("Enter size of team (15)");
			teamSizeYoung = scanner.nextInt();

			for (int i = 0; i < teamSizeYoung; i++) {
				System.out.println("Enter name of member [" + (i + 1) + "]");
				String name = scanner.next();
				teamYoung.add(new Person(name, 15));
			}

			System.out.println("Enter size of load");
			loadSize = scanner.nextInt();
			totalTime = 0;
			for (int i = 0; i < loadSize; i++) {
				System.out.println("Enter code of container [" + (i + 1) + "]");
				String code = scanner.next();
				System.out.println("Enter time of container [" + (i + 1) + "]");
				int minutes = scanner.nextInt();
				containers.add(new Container(code, minutes));
			}

			scanner.close();
		}

		// preset();

		for (Container c : containers) {
			totalTime += c.minutes;
		}

		Collections.sort(containers);

		for (Container c : containers) {
			Collections.sort(teamYoung);
			for (Person p : teamYoung) {
				if (p.total + c.minutes <= 110 && !c.used) {
					c.used = true;
					c.p = p;
					totalTime -= c.minutes;
					p.total += c.minutes;
					p.containers.add(c);
					break;
				}
			}
		}

		averageTime = totalTime / teamSize;

		Collections.sort(containers);

		for (Container c : containers) {
			if (!c.used) {
				Person p = Person.getLoser(team);
				c.p = p;
				p.containers.add(c);
				p.total += c.minutes;
			}
		}

		if (CONSOLE) {
			System.out.println("-----------------------------");
			for (Person p : teamYoung) {
				int total = 0;
				System.out.println(p.name + " (" + p.age + "): ");
				System.out.println("Containers [" + (p.containers.size()) + "]: ");
				System.out.println();
				for (Container c : p.containers) {
					System.out.println("Container [" + c.code + "]: " + c.minutes + " minutes");
					total += c.minutes;
				}
				System.out.println();
				System.out.println("Total time: " + total);
				System.out.println("-----------------------------");
			}

			System.out.println("Total time: " + totalTime + " Average time: " + averageTime);

			for (Person p : team) {
				int total = 0;
				System.out.println(p.name + " (" + p.age + "): ");
				System.out.println("Containers [" + (p.containers.size()) + "]: ");
				System.out.println();
				for (Container c : p.containers) {
					System.out.println("Container [" + c.code + "]: " + c.minutes + " minutes");
					total += c.minutes;
				}
				System.out.println();
				System.out.println("Total time: " + total);
				System.out.println("-----------------------------");
			}
		} else {
			try {
				PrintWriter bw = new PrintWriter(new FileWriter(file));

				bw.write("---CONTAINERS SORTED BY CODE---\n");
				for (Container c : Container.getOrderedList()) {
					bw.write("Container[" + c.code + "] " + c.minutes + " Minutes | " + c.p.name + "\n");
				}
				bw.write("---CONTAINERS SORTED BY PERSON---\n");
				for (Person p : team) {

					bw.write(p.name + "\n");
					for (Container c : p.containers) {
						bw.write("Container[" + c.code + "] " + c.minutes + " Minutes\n");
					}

					bw.write("Total time: " + p.total + " Minutes\n");
					bw.write("------\n");
				}
				for (Person p : teamYoung) {

					bw.write(p.name + "\n");
					for (Container c : p.containers) {
						bw.write("Container[" + c.code + "] " + c.minutes + " Minutes\n");
					}

					bw.write("Total time: " + p.total + " Minutes\n");
					bw.write("------\n");
				}

				bw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void preset() {
		teamSize = 6;

		team.add(new Person("Daniel", 16));
		team.add(new Person("Wendly", 16));
		team.add(new Person("DaanR", 16));
		team.add(new Person("DaanG", 16));
		team.add(new Person("Melissa", 16));
		team.add(new Person("Iliaas", 16));

		teamSizeYoung = 3;

		teamYoung.add(new Person("Surafiel", 15));
		teamYoung.add(new Person("Madina", 15));
		teamYoung.add(new Person("Arsema", 15));

		loadSize = 18;

		containers.add(new Container("1", 31));
		containers.add(new Container("2", 20));
		containers.add(new Container("3", 48));
		containers.add(new Container("4", 35));
		containers.add(new Container("5", 56));
		containers.add(new Container("6", 50));
		containers.add(new Container("7", 57));
		containers.add(new Container("8", 21));
		containers.add(new Container("9", 34));
		containers.add(new Container("10", 52));
		containers.add(new Container("11", 33));
		// containers.add(new Container("12", 25));
		containers.add(new Container("13", 48));
		// containers.add(new Container("14", 67));
		containers.add(new Container("15", 47));
		containers.add(new Container("16", 38));
		containers.add(new Container("17", 38));
		containers.add(new Container("18", 32));
		containers.add(new Container("19", 42));
		containers.add(new Container("20", 7));
	}
}
