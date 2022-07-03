import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver implements Runnable {

	Socket socket;
	DataInputStream in;
	String name;
	User user = new User();

	public Receiver(User user, Socket socket) throws Exception {
		this.user = user;
		this.socket = socket;
		in = new DataInputStream(socket.getInputStream());
		this.name = in.readUTF();
		user.addClient(name, socket);
	}

	@Override
	public void run() {
		try {
			while (true) {
				String msg = in.readUTF(); // send가 보낸 메시지 읽어오기
				user.sendMsg(msg, name);
			}
		} catch (IOException e) {
			user.removeClient(this.name);
		}

	}

}
