import java.io.*;

public class Send implements Runnable {
	DataOutputStream out;
	BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));

	public Send(DataOutputStream out) {
		this.out = out;
	}

	public void run() {
		while (true) {
			try {
				String msg = in2.readLine();
				out.writeUTF(msg); // 받은 msg 출력
			} catch (Exception e) {

			}
		}
	}

}
