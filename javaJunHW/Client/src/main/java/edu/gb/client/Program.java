package edu.gb.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите своё имя: ");
        String name = scanner.nextLine();
        Socket socket = new Socket("localhost", 1250);
        Client client = new Client(socket, name);
        client.listenForMessage();
        client.sendMessage();
    }
}