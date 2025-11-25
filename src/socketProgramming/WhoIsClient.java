package socketProgramming;

import java.net.*;
import java.io.*;

public class WhoIsClient {

    public static void main(String[] args) {

        String domainName = (args.length > 0) ? args[0] : "google.com";

        String hostname = "whois.internic.net";
        int port = 43;
        System.out.println("Connecting to " + hostname + " on port " + port + " for domain: " + domainName);
        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("Connected to " + hostname + " on port " + port);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            System.out.println("Sending domain name: " + domainName);
            writer.println(domainName);
            System.out.println("Waiting for response...");
            InputStream input = socket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;
            System.out.println("Response from server:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}