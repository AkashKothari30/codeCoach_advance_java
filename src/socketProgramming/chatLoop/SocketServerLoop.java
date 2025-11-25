package socketProgramming.chatLoop;

import java.io.*;
import java.net.*;

public class SocketServerLoop {
    public static void main(String[] args) {
        System.out.println("Server started...");
        try (ServerSocket serverSocket = new ServerSocket(12346)) {
            System.out.println("Waiting for client connection...");

            try (Socket socket = serverSocket.accept()) {
                System.out.println("Client connected: " + socket.getInetAddress());

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received: " + inputLine);

                    if ("exit".equalsIgnoreCase(inputLine)) {
                        System.out.println("Client requested to exit.");
                        break;
                    }

                    out.println("Echo: " + inputLine);
                    System.out.println("Sent response to client.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server stopped.");
    }
}