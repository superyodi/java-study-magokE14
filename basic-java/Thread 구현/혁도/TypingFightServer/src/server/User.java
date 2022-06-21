package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class User {
	private Socket socket;
	private InetAddress inetAddress;
	private String name;
	private String ip;
	private String state;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private int answerCount;

	public User(Socket socket, InetAddress inetAddress, String name) {
		this.socket = socket;
		this.inetAddress = inetAddress;
		this.name = name;
		this.ip = new String(inetAddress.getAddress());
		this.state = "WAIT";
		this.answerCount = 0;
	}
	
	public InetAddress getInetAddress() {
		return inetAddress;
	}
	
	public void setInetAddress(InetAddress inetAddress) {
		this.inetAddress = inetAddress;
	}
	

	public String getIp() {
		return ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}
}
