import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class messageAFriend extends JFrame {
    private JLabel label;
    private JTextField text;
    private String IPAddress;
    private int portNumber;
    private FrontPage F;
    private ArrayList<Friend> friendList = AddFriend.getFriendList();
    private String name;

    public messageAFriend(FrontPage F) {
        super("Enter Friend Info");
        name = "Client";
        this.F = F;
        label = new JLabel("Enter Friend Name");
        text = new JTextField(10);
        this.setLayout(new GridLayout(2, 1));
        this.add(label);
        this.add(text);
        this.pack();
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println("ran");
                    for (Friend friend : friendList) {
                        if (friend.getFriendName().equals(text.getText())) {
                            name = text.getText();
                            try {
                                F.getServer().closeServerSocket();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            F.getServer().dispose();
                            IPAddress = friend.getIpAddress();
                            portNumber = Integer.parseInt(friend.getPortalNumber());
                            Client client = new Client(IPAddress,portNumber,name, F);
                            SwingWorker worker = new SwingWorker<ImageIcon[], Void>() {
                                @Override
                                public ImageIcon[] doInBackground() {
                                    client.startRun();
                                    return new ImageIcon[0];
                                }
                            };
                            worker.execute();
                        }
                    }
                }
            }
        });
    }
}