package dev.codewizz;

public class Container implements Comparable<Container> {

	public int minutes = 0;
	public String code = "";
	
	public Container(String code, int minutes) {
		this.minutes = minutes;
		this.code = code;
	}

	@Override
	public int compareTo(Container o) {
		if(o.minutes > minutes)
			return 0;
		else {
			return -1;
		}
	}
}
