import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Server extends JFrame {
    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    private int port;
    private boolean tfWhile;

    public Server(int port) {
        super("Messaging Page");
        this.port =port;
        userText = new JTextField(60);
        userText.setEditable(false);
        userText.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        sendMessage(event.getActionCommand(), "Server");
                        userText.setText("");
                    }
                }
            );
        add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea(30,10);
        add(new JScrollPane(chatWindow), BorderLayout.SOUTH);
        this.pack();
        setVisible(true);
    }
    public void startRun() {
        tfWhile = true;
        try {
            server = new ServerSocket(port, 100);
            while (tfWhile) {
                try {
                    showMessage("Port: " + port);
                    waitForConnection();
                    setupStreams();
                    whileChatting();
                } catch (EOFException eofException) {
                    showMessage("\nClient closed the chat! ");
                } finally {
                    close();
                }
            }
        }
        catch(IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public void changePort(int port) throws IOException {
        tfWhile = false;
        server.close();
        this.port =port;
    }
    private void waitForConnection() throws IOException {
        showMessage("\nWaiting for connection");
        connection = server.accept();
        showMessage("\nConnected to " + connection.getInetAddress().getHostName());
    }

    private void setupStreams() throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\nYou are now chatting");
    }
    private void sendMessage(String message, String userName) {
        try{
            output.writeObject(userName+ " - " +message+"\n");
            output.flush();
            showMessage(userName + " - " +message+"\n");
        }
        catch (IOException ioException) {
            chatWindow.append("\nMessage was not sent");
        }
    }
    private void whileChatting() throws IOException {
        String message = "\nYou can now begin chatting";
        sendMessage(message, "Server");
        ableToType(true);
        do {
            try {
                message = (String) input.readObject();
                showMessage(message);
            }catch (ClassNotFoundException classNotFoundException){
                showMessage("\nMessage not readable");
            }
        } while (!message.equals("CLIENT - END"));
    }
    private void showMessage(final String text) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        chatWindow.append(text);
                    }
                }
        );
    }
    public void close() {
        showMessage("\nEnding chat...\n\n");
        ableToType(false);
        tfWhile =false;
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    private void ableToType(final boolean tf){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        userText.setEditable(tf);
                    }
                }
        );
    }
}