package socketProgramming.chat2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketClientGUI extends JFrame {

    private JTextField serverIpField;
    private JTextField portField;
    private JButton connectButton;
    private JButton disconnectButton;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private volatile boolean connected = false;
    private static final String DEFAULT_PORT = "15120";
    private static final String DEFAULT_HOST = "localhost";

    public SocketClientGUI() {
        setTitle("Simple Chat Client");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Handle close manually

        initializeGUI();
        addListeners();

        updateUIForDisconnectedState(); // Set initial UI state

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleWindowClose();
            }
        });

        pack();
        setMinimumSize(new Dimension(400, 300)); // Set a reasonable minimum size
        setLocationRelativeTo(null); // Center on screen
    }

    private void initializeGUI() {
        // Top panel for connection details
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Server IP:"));
        serverIpField = new JTextField(DEFAULT_HOST, 15);
        topPanel.add(serverIpField);

        topPanel.add(new JLabel("Port:"));
        portField = new JTextField(DEFAULT_PORT, 5);
        topPanel.add(portField);

        connectButton = new JButton("Connect");
        topPanel.add(connectButton);

        disconnectButton = new JButton("Disconnect");
        topPanel.add(disconnectButton);

        // Center panel for chat messages
        chatArea = new JTextArea(15, 50);
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Bottom panel for sending messages
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        messageField = new JTextField();
        bottomPanel.add(messageField, BorderLayout.CENTER);

        sendButton = new JButton("Send");
        bottomPanel.add(sendButton, BorderLayout.EAST);

        // Main layout
        setLayout(new BorderLayout(5, 5));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addListeners() {
        connectButton.addActionListener(e -> connectToServer());
        disconnectButton.addActionListener(e -> disconnect(true, "Disconnected by client."));
        sendButton.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage()); // Allow sending with Enter key
    }

    private void connectToServer() {
        String serverIp = serverIpField.getText().trim();
        String portStr = portField.getText().trim();

        if (serverIp.isEmpty() || portStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Server IP and Port cannot be empty.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int port;
        try {
            port = Integer.parseInt(portStr);
            if (port <= 0 || port > 65535) {
                throw new NumberFormatException("Port out of range. Must be between 1 and 65535.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Port Number: " + e.getMessage(), "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update UI for connection attempt
        connectButton.setEnabled(false);
        disconnectButton.setEnabled(false);
        serverIpField.setEnabled(false);
        portField.setEnabled(false);
        appendToChatArea("Connecting to " + serverIp + ":" + port + "...");

        new Thread(() -> {
            try {
                socket = new Socket(serverIp, port);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                connected = true;

                SwingUtilities.invokeLater(() -> {
                    appendToChatArea("Connected to server: " + socket.getRemoteSocketAddress());
                    disconnectButton.setEnabled(true);
                    messageField.setEnabled(true);
                    sendButton.setEnabled(true);
                });

                startReceivingMessages(); // Start the reader thread

            } catch (UnknownHostException e) {
                handleConnectionError("Connection failed: Unknown host " + serverIp);
            } catch (IOException e) {
                handleConnectionError("Connection failed: " + e.getMessage());
            }
        }).start();
    }

    private void handleConnectionError(String errorMessage) {
        connected = false; // Ensure connected is false
        SwingUtilities.invokeLater(() -> {
            appendToChatArea(errorMessage);
            updateUIForDisconnectedState(); // Reset UI
        });
    }

    private void startReceivingMessages() {
        Thread readerThread = new Thread(() -> {
            try {
                String serverMessage;
                // Loop while connected and the input stream is valid
                while (connected && in != null && (serverMessage = in.readLine()) != null) {
                    final String msgToAppend = serverMessage;
                    SwingUtilities.invokeLater(() -> appendToChatArea("Server: " + msgToAppend));
                }
            } catch (SocketException se) {
                // This can happen if the socket is closed by disconnect() while readLine() is
                // blocking
                if (connected) { // Only an issue if we thought we were still connected
                    disconnect(false, "Connection closed: " + se.getMessage());
                }
            } catch (IOException e) {
                if (connected) { // Only an issue if we thought we were still connected
                    disconnect(false, "Error receiving message: " + e.getMessage());
                }
            } finally {
                // If the loop terminated due to an issue (e.g., server closed connection)
                // and we are still marked as 'connected', initiate client-side disconnect.
                if (connected) {
                    disconnect(false, "Connection to server was lost.");
                }
            }
        });
        readerThread.setDaemon(true); // Allow JVM to exit if this is the only non-daemon thread
        readerThread.start();
    }

    private void sendMessage() {
        if (!connected || out == null) {
            appendToChatArea("Not connected to server. Cannot send message.");
            return;
        }
        String message = messageField.getText().trim();
        if (message.isEmpty()) {
            return;
        }

        out.println(message);
        if (out.checkError()) {
            appendToChatArea("Error sending message. Please check connection.");
            disconnect(false, "Error sending message. Disconnecting.");
            return;
        }

        appendToChatArea("You: " + message);
        messageField.setText("");

        if (message.equalsIgnoreCase("exit")) {
            // Disconnect will be handled by the server closing the connection,
            // or we can explicitly call disconnect here.
            // The server will close its end upon receiving "exit".
            // Our reader thread will then detect the closure.
            // For immediate UI feedback and resource cleanup:
            disconnect(true, "Disconnected by client (sent 'exit').");
        }
    }

    private synchronized void disconnect(boolean initiatedByClient, String disconnectMessage) {
        if (!connected && socket == null) { // Already disconnected or never connected.
            SwingUtilities.invokeLater(this::updateUIForDisconnectedState); // Ensure UI is correct
            return;
        }

        final boolean wasConnected = this.connected;
        this.connected = false; // Signal reader thread and others to stop

        if (initiatedByClient && out != null && wasConnected && socket != null && !socket.isOutputShutdown()) {
            try {
                out.println("exit"); // Politely inform server
            } catch (Exception e) {
                // PrintWriter methods like println don't throw checked IOExceptions
                // but can set an error flag.
                if (out.checkError()) {
                    System.err.println("Error flag set on PrintWriter after attempting to send exit.");
                }
            }
        }

        // Close resources
        try {
            if (out != null)
                out.close();
        } catch (Exception e) {
            System.err.println("Error closing output stream: " + e.getMessage());
        }
        try {
            if (in != null)
                in.close();
        } catch (IOException e) {
            System.err.println("Error closing input stream: " + e.getMessage());
        }
        try {
            if (socket != null && !socket.isClosed())
                socket.close();
        } catch (IOException e) {
            System.err.println("Error closing socket: " + e.getMessage());
        }

        out = null;
        in = null;
        socket = null;

        SwingUtilities.invokeLater(() -> {
            if (wasConnected) {
                appendToChatArea(disconnectMessage);
            }
            updateUIForDisconnectedState();
        });
    }

    private void updateUIForDisconnectedState() {
        serverIpField.setEnabled(true);
        portField.setEnabled(true);
        connectButton.setEnabled(true);
        disconnectButton.setEnabled(false);
        messageField.setEnabled(false);
        sendButton.setEnabled(false);
    }

    private void appendToChatArea(String message) {
        SwingUtilities.invokeLater(() -> {
            chatArea.append(message + "\n");
            chatArea.setCaretPosition(chatArea.getDocument().getLength()); // Auto-scroll
        });
    }

    private void handleWindowClose() {
        if (connected) {
            disconnect(true, "Client window closed. Disconnecting.");
        }
        dispose(); // Close the window
        System.exit(0); // Terminate application
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SocketClientGUI clientGUI = new SocketClientGUI();
            clientGUI.setVisible(true);
        });
    }
}
