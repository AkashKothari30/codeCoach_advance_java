package socketProgramming;

import java.io.*;
import java.net.*;

public class SocketServer {
    public static void main(String[] args) {
        System.out.println("Server started...");
        try {
            ServerSocket serverSocket = new ServerSocket(12346);
            System.out.println("Waiting for client connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);

                if (inputLine.equalsIgnoreCase("exit")) {
                    System.out.println("close");
                    out.println("Goodbye");
                    break;
                } 
               // inputLine = in.readLine();
               // System.out.println("Received: " + inputLine);
                out.write("Echo: " + inputLine);
                out.flush();
                System.out.println("Message Sent: " +"Echo: " + inputLine);
            } 
            out.close();
            in.close();            
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server stopped.");
    }

}
