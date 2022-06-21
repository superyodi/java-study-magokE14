package main;

import java.net.ServerSocket;

import server.Server;

public class Main {

	public static void main(String[] args) {
		Server typingFightServer = new Server("타자대결 서버", 2337);
	}

}
