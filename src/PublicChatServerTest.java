import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class PublicChatServerTest extends JFrame {
    private JTextArea chatArea;
    private List<Socket> clientSockets;
    private ServerSocket serverSocket;

    public PublicChatServerTest() {
        setTitle("Public Chat Server");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);

        int serverPort = Integer.parseInt(JOptionPane.showInputDialog("Enter Port Number to Start the Server:"));

        try {
            serverSocket = new ServerSocket(serverPort);
            clientSockets = new ArrayList<>();

            appendToChatArea("Server started on port " + serverPort);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientSockets.add(clientSocket);

                appendToChatArea("New client connected: " + clientSocket.getInetAddress());

                Thread clientThread = new Thread(new ClientThread(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendToChatArea(String message) {
        chatArea.append(message + "\n");
    }

    private class ClientThread implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientThread(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    broadcastMessage(message);
                    appendToChatArea("Client message: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                    clientSockets.remove(clientSocket);
                    appendToChatArea("Client disconnected: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcastMessage(String message) {
            for (Socket socket : clientSockets) {
                try {
                    PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
                    socketOut.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PublicChatServerTest();
            }
        });
    }
}