package main;

import city.Seoul;

public class Main {
	public static void main(String[] args) {
		int civilianCount = 10;
		int villainCount = 10;
		Seoul seoul = new Seoul(civilianCount, villainCount);
		seoul.run();
	}
}
