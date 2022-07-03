import java.net.ServerSocket;
import java.net.Socket;

// https://freloha.tistory.com/44
public class Server {
	public static void main(String[] args) {
		Socket socket = null;
		User user = new User();
		ServerSocket server_socket = null;

		int count = 0;
		Thread thread[] = new Thread[10]; // 채팅방에 10명이 접속 가능

		try {
			server_socket = new ServerSocket(4444);
			System.out.println("I am Server!");
			while (true) {
				socket = server_socket.accept(); // 통신이 종료되기 전까지 연결

				thread[count] = new Thread(new Receiver(user, socket)); // Receiver 클래스(implements Runnable)를 Thread에서
																		// 돌림
				thread[count].start();
				count++;
			}
		} catch (Exception e) {

		}
		;
	}
}
