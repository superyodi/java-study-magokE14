package cs;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.*;
public class ChatServer {
	public static final int PORT = 5000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		List<PrintWriter> listWriters = new ArrayList<PrintWriter>();
		
		try {
			//1. 서버 소켓 생성
			serverSocket = new ServerSocket();
			
			//2. 바인딩
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind( new InetSocketAddress(hostAddress, PORT));
			consoleLog("연결 기다림 - " + hostAddress + ":" + PORT);
			
			//3. 요청대기
			while(true) {
				Socket socket = serverSocket.accept();
				new ChatServerProcessThread(socket, listWriters).start();
				//listWriters 변수는 채팅 서버에 연결된 모든 클라이언트들을 저장하고 있는 List. (join시 추가됨)
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void consoleLog(String log) {
		System.out.println("[server" + Thread.currentThread().getId() + "] "+log);
	}

}
