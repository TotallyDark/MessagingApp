import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFriend extends JPanel implements ActionListener {
    JPanel ipPanel, portPanel, friendPanel, friendListPanel, buttonPanel;
    JLabel ip, port, friendName;
    JButton addFriend;
    JTextField ipText, portText, friendNameText;
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("enter")){
            Friend x = new Friend(friendNameText.getText(), ipText.getText(), Integer.parseInt(portText.getText()));
            add(x, BorderLayout.WEST);
            repaint();
        }
    }

    public AddFriend(){
        setLayout(new BorderLayout());

        buttonPanel = new JPanel(new BorderLayout());
        ipPanel = new JPanel();
        portPanel = new JPanel();
        friendPanel = new JPanel();

        ip = new JLabel("ip address");
        port = new JLabel("portal number");
        friendName = new JLabel("friend name");

        ipText = new JTextField(20);
        portText = new JTextField(20);
        friendNameText = new JTextField(20);

        addFriend = new JButton("enter");

        ipPanel.add(ip);
        ipPanel.add(ipText);
        portPanel.add(port);
        portPanel.add(portText);
        friendPanel.add(friendName);
        friendPanel.add(friendNameText);

        buttonPanel.add(ipPanel, BorderLayout.NORTH);
        buttonPanel.add(portPanel, BorderLayout.CENTER);
        buttonPanel.add(friendPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.NORTH);
        add(addFriend,BorderLayout.CENTER);
        repaint();
    }
}
