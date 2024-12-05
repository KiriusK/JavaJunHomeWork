package edu.gb.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Program {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1250);
        Server server = new Server(serverSocket);
        server.runServer();
    }
}
