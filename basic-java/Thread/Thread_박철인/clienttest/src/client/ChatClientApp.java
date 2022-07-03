package client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Scanner;

public class ChatClientApp {

	private static final String SERVER_IP = "";
	private static final int SERVER_PORT = 5000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name = null;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("대화명을 입력하세요.");
			System.out.print(">>>");
			name = scanner.nextLine();

			if (name.isEmpty() == false) {
				break;
			}

			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}

		scanner.close();

		final String nick = name;
		SocketService ss = (Socket s) -> {
			try {
				s.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
				consoleLog("채팅방에 입장하였습니다.");
				new ChatWindow(nick, s).show();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8),
						true);
				String request = "join:" + nick + "\r\n";
				pw.println(request);
			} catch (IOException e) {
				e.printStackTrace();
			}

		};
		ss.connect(new Socket());
		// socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
		// consoleLog("채팅방에 입장하였습니다.");
		// new ChatWindow(name, socket).show();

		// PrintWriter pw = new PrintWriter(new
		// OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
		// String request = "join:" + name + "\r\n";
		// pw.println(request);

	}

	private static void consoleLog(String log) {
		System.out.println(log);
	}

}