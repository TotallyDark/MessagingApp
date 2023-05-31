import javax.swing.*;

public class Server_Test {
    private int port;
    Server server;
    public Server_Test(int port) {
        this.port = port;
    }
    public void start() {
        server = new Server(port);
        server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        server.getContentPane().setName("Message Page");

    }
    public Server getServer() {
        return this.server;
    }
}
