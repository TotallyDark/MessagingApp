import javax.swing.*;

public class Server_Test {
    private int port;
    private String IP;
    Server server;
    Client client;
    public Server_Test(int port) {
        this.port = port;
    }
    public Server_Test(int port, String IP){
        this.port = port;
        this.IP = IP;
    }
    public void start() {
        server = new Server(port);
        server.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        server.getContentPane().setName("Message Page");
    }
    public void clientStart() {
        //client = new Client(IP,port,"JON");
    }
    public void endServer() {
        server.dispose();
    }
    public Server getServer() {
        return this.server;
    }
    public Client getClient() {return this.client;}
}
