package socketProgramming.chat2;

import java.io.*;
import java.net.*;

public class SocketServer2 {
    public static void main(String[] args) {
        System.out.println("Server started...");
        try {
            ServerSocket serverSocket = new ServerSocket(15120);
            System.out.println("Waiting for client connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            // Open once outside the loop
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equals("exit")) {
                    System.out.println("Client requested to end the chat.");
                    break;
                }

                System.out.println("Received: " + inputLine);

                out.println("Echo: " + inputLine); // use println instead of write+flush
                System.out.println("Sent response to client.");
            }

            // Close after the loop
            in.close();
            out.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server stopped.");
    }
}
