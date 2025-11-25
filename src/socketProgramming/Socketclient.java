package socketProgramming;

import java.io.*;
import java.net.*;

public class Socketclient {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java SocketClient <server-ip>");
            return;
        }

        String serverIp = args[0];
        int port = 12346;

        System.out.println("Connecting to server at " + serverIp + ":" + port);
        try (Socket socket = new Socket(serverIp, port)) {
            System.out.println("Connected to server at " + serverIp + ":" + port);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String userMessage;
            while (true) {
                System.out.println("Type your messages:");
                
                userMessage = userInput.readLine();
                if (userMessage == null || userMessage.equalsIgnoreCase("quit")) {
                    System.out.println("Exiting chat");
                    break;
                }
            
                out.println(userMessage);// send to server
                System.out.println("Message sent to server.");
                System.out.println("Waiting for server response...");
                String response = in.readLine();
                System.out.println("Server response: " + response);
            
                if (response == null) {
                    System.out.println("closed.");
                    break;
                }
            }
            in.close();
            out.close();
            userInput.close();
            System.out.println("Connection closed.");


        } catch (IOException e) {
            e.printStackTrace();

        }
        System.out.println("Tata bye bye.");

    }

}
