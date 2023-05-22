import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFriend extends JPanel implements ActionListener {
    JLabel ip, port, friendName;
    JButton ipButton, portButton, friendButton;
    JTextField ipText, portText, friendNameText;
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public AddFriend(){
        setLayout(new BorderLayout());

        ip = new JLabel("ip address");
        port = new JLabel("portal number");
        friendName = new JLabel("friend name");

        ipText = new JTextField(20);
        portText = new JTextField(20);
        friendNameText = new JTextField(20);

        ipButton = new JButton("send");
        portButton = new JButton("send");
        friendButton = new JButton("send");

    }
}
