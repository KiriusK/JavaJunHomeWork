package edu.gb.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clients.add(this);
            broadcastMessage("Server: "+name+" подключился к чату.");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()){
            try {
                messageFromClient = bufferedReader.readLine();
                System.out.println(messageFromClient);

                if (messageFromClient == null) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                } else {
                    broadcastMessage(messageFromClient);
                }
            } catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    private void broadcastMessage(String messageToSend) {
        if (messageToSend.startsWith("@")) {
            String recieverName = getNameFromString(messageToSend);
            System.out.println(recieverName);
            if (checkName(recieverName)) {
                messageToOne(messageToSend, recieverName);
            }
        } else {
            for (ClientManager client : clients) {
                try {
                    if (!client.name.equals(name)) {
                        client.bufferedWriter.write(this.name + ": " + messageToSend);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    }
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }
    }

    private boolean checkName(String recieverName) {
            for (ClientManager cl: clients) {
                if (recieverName.equalsIgnoreCase(cl.name)) {
                    return true;
                }
            }
        return false;
    }

    private String getNameFromString(String stringToCheck) {
            String name = stringToCheck.split(" ")[0];
            int length = name.length();
            return name.substring(1, length);
    }

    private void messageToOne(String messageToSend, String recieverName) {
        int spaceIndex = messageToSend.indexOf(" ");
        messageToSend = messageToSend.substring(spaceIndex+1);
        for (ClientManager client : clients) {
            if (recieverName.equalsIgnoreCase(client.name)) {
                try {
                    if (!client.name.equals(name)) {
                        client.bufferedWriter.write(this.name + ": " + messageToSend);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    }
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }
    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
            removeClient();
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    public void removeClient(){
            clients.remove(this);
            broadcastMessage("SERVER: "+name+" покинул чат.");
        }
    }


