import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class User {
	HashMap<String, DataOutputStream> clientmap = new HashMap<>();

	public synchronized void addClient(String name, Socket socket) {
		try {
			sendMsg(name + "님이 입장하셨습니다.", "Server"); // 서버에 입장 메세지 전달
			clientmap.put(name, new DataOutputStream(socket.getOutputStream()));
			System.out.println("채팅 참여 인원 : " + clientmap.size());
		} catch (Exception e) {
			System.out.println("채팅 입장 실패");
		}
	}

	public synchronized void removeClient(String name) {
		try {
			clientmap.remove(name);
			sendMsg(name + "님이 퇴장하였습니다.", "Server");
			System.out.println("채팅 참여 인원 : " + clientmap.size());
		} catch (IOException e) {
			System.out.println("채팅 퇴장 실패");
		}
	}

	public synchronized void sendMsg(String msg, String name) throws IOException {
		Iterator iterator = clientmap.keySet().iterator();
		while (iterator.hasNext()) {
			String clientname = (String) iterator.next();
			clientmap.get(clientname).writeUTF(name + " : " + msg);
		}
	}
}
