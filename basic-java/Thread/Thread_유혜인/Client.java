import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		Socket socket = null;
		DataInputStream in = null;
		BufferedReader in2 = null;

		DataOutputStream out = null;

		try {
			InetAddress ia = null;
			ia = InetAddress.getLocalHost();
			socket = new Socket(ia, 4444);

			in = new DataInputStream(socket.getInputStream());
			in2 = new BufferedReader(new InputStreamReader(System.in));
			out = new DataOutputStream(socket.getOutputStream());

			System.out.print("닉네임을 입력해주세요 : ");
			String data = in2.readLine();

			out.writeUTF(data);
			Thread th = new Thread(new Send(out));
			th.start();
		} catch (IOException e) {

		}

		try {
			while (true) { // 끊어지기 전까지는 계속 채팅을 입력받기 때문에 무한루프(while)안에서 동작
				String str2 = in.readUTF();
				System.out.println(str2);
			}
		} catch (IOException e) {

		}

	}

}
