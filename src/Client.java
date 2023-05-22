import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.ViewFactory;

public class Client extends JFrame{
    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message = "";
    private String serverIP;
    private Socket connection;
    private int port;
    public Client(String host, int port) {
        super("Client");
        this.port = port;
        serverIP = host;
        userText = new JTextField();
        userText.setEditable(false);
        userText.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        sendMessage(event.getActionCommand(), "Client");
                        userText.setText("");
                    }
                }
        );
        add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow),BorderLayout.CENTER);
        setSize(600, 600);
        setVisible(true);
    }

    public void startRun() {
        try{
            connectToServer();
            setupStreams();
            whileChatting();
        }catch (EOFException eofException) {
            showMessage("\nClient ended connection");
        }catch (IOException ioException) {
            ioException.printStackTrace();
        }finally {
            close();
        }
    }
    private void connectToServer() throws IOException{
        showMessage("\nConnecting...");
        connection = new Socket(InetAddress.getByName(serverIP), port);
        showMessage("\nConnected");
    }
    private void setupStreams() throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\nStreams are connected");
    }
    private void whileChatting() throws IOException{
        ableToType(true);
        do {
            try{
                message = (String) input.readObject();
                showMessage(message);
            }catch (ClassNotFoundException classNotFoundException) {
                showMessage("\nUnable to read message");
            }
        } while(!message.equals("SERVER - END"));
    }
    private void close(){
        showMessage("\nShutting down connection");
        ableToType(false);
        try{
            output.close();
            input.close();
            connection.close();
        }catch(IOException ioException) {
            ioException.printStackTrace();
        }
    }
    private void sendMessage(String message, String userName) {
        try{
            output.writeObject(userName +" - " +message+"\n");
            output.flush();
            showMessage(userName + " - " +message+"\n");
        }catch(IOException ioException){
            chatWindow.append("\nMessage cannot send");
        }
    }
    private void showMessage(final String msg) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        chatWindow.append(msg);
                    }
                }
        );
    }
    private void ableToType(final boolean tf) {
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

// A Java program for a Client
/*
import java.io.*;
import java.net.*;

public class Client {
    // initialize socket and input output streams
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    // constructor to put ip address and port
    public Client(String address, int port)
    {
        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            input = new DataInputStream(System.in);

            // sends output to the socket
            out = new DataOutputStream(
                    socket.getOutputStream());
        }
        catch (UnknownHostException u) {
            System.out.println(u);
            return;
        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }

        // string to read message from input
        String line = "";

        // keep reading until "Over" is input
        while (!line.equals("Over")) {
            try {
                line = input.readLine();
                out.writeUTF(line);
            }
            catch (IOException i) {
                System.out.println(i);
            }
        }

        // close the connection
        try {
            input.close();
            out.close();
            socket.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Client client = new Client("172.59.128.244", 4000);
    }
}


// Java program to illustrate Client side
// Implementation using DatagramSocket

/*
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) throws IOException
    {
        Scanner sc = new Scanner(System.in);

        // Step 1:Create the socket object for
        // carrying the data.
        DatagramSocket ds = new DatagramSocket();

        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;

        // loop while user not enters "bye"
        while (true)
        {
            String inp = sc.nextLine();

            // convert the String input into the byte array.
            buf = inp.getBytes();

            // Step 2 : Create the datagramPacket for sending
            // the data.
            DatagramPacket DpSend =
                    new DatagramPacket(buf, buf.length, ip, 1234);

            // Step 3 : invoke the send call to actually send
            // the data.
            ds.send(DpSend);

            // break the loop if user enters "bye"
            if (inp.equals("bye"))
                break;
        }
    }
}
*/