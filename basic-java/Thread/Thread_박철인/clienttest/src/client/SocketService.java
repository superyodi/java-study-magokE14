package client;

import java.net.Socket;

@FunctionalInterface
public interface SocketService {
    void connect(Socket s);
}
