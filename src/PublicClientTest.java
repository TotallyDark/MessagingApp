import java.io.*;
import java.net.*;

public class PublicClientTest {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public PublicClientTest(String serverAddress, int serverPort) {
        try {
            clientSocket = new Socket(serverAddress, serverPort);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Thread receiveThread = new Thread(new ReceiveThread());
            receiveThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    private class ReceiveThread implements Runnable {
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received message: " + message);
                }

                // Server has closed the connection
                System.out.println("Disconnected from the server");
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String serverAddress = "localhost"; // Replace with the server IP address
        int serverPort = 12345; // Replace with the server port number

        PublicClientTest client = new PublicClientTest(serverAddress, serverPort);

        // Example usage: send messages from command line
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            while ((input = consoleReader.readLine()) != null) {
                client.sendMessage(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
