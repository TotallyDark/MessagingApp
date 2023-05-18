import javax.swing.*;

public class Server_Test {
    public static void main(String[] args) {
        Server server = new Server(6789);
        server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        server.startRun();
    }
}
