package socketProgramming.chatLoop;


import java.io.*;
import java.net.*;

public class SocketClientLoop {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java SocketClientLoop <server-ip>");
            return;
        }

        String serverIp = args[0];
        int port = 12347;

        System.out.println("Connecting to server at " + serverIp + ":" + port);
        try (Socket socket = new Socket(serverIp, port)) {
            System.out.println("Connected to server at " + serverIp + ":" + port);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String userMessage;

            while (true) {
                System.out.println("Type your messages (type 'exit' to quit):");
                userMessage = userInput.readLine();
                out.println(userMessage);// send to server

                if ("exit".equalsIgnoreCase(userMessage)) {
                    break;
                }

                System.out.println("Waiting for server response...");
                String response = in.readLine();
                System.out.println("Server: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connection closed.");
    }
}

