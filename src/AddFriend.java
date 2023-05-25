import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddFriend extends JPanel implements ActionListener {

    JPanel ipPanel, portPanel, friendPanel, infoPanel, buttonPanel;
    JLabel ip, port, friendName;
    JButton addFriend, getFriendInfo, enterInfo;
    JTextField ipText, portText, friendNameText;

    private JPanel general = new JPanel();
    private JPanel general2 = new JPanel();

    private ArrayList<Friend> friendList = new ArrayList<>();
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add Friend")){
            remove(general);
            remove(general2);
            infoPanel = new JPanel(new BorderLayout());
            enterInfo = new JButton("Enter info");
            enterInfo.addActionListener(this);

            ipPanel = new JPanel();
            ip = new JLabel("ip address");
            ipText = new JTextField(20);
            ipPanel.add(ip);
            ipPanel.add(ipText);

            portPanel = new JPanel();
            port = new JLabel("port number");
            portText = new JTextField(20);
            portPanel.add(port);
            portPanel.add(portText);

            friendPanel = new JPanel();
            friendName = new JLabel("Friend Name");
            friendNameText = new JTextField(20);
            friendPanel.add(friendName);
            friendPanel.add(friendNameText);

            infoPanel.add(ipPanel, BorderLayout.NORTH);
            infoPanel.add(portPanel, BorderLayout.CENTER);
            infoPanel.add(friendPanel, BorderLayout.SOUTH);
            infoPanel.add(enterInfo, BorderLayout.WEST);
            general = infoPanel;
            add(general, BorderLayout.SOUTH);
            this.validate();
            this.repaint();
            JFrame windowAncestor = (JFrame) SwingUtilities.getWindowAncestor(this);
            windowAncestor.revalidate();
        }
        if(e.getActionCommand().equals("Enter info")){
            Friend x = new Friend(friendNameText.getText(),ipText.getText(),portText.getText());
            friendList.add(x);
            friendNameText.setText("");
            ipText.setText("");
            portText.setText("");
        }
        if(e.getActionCommand().equals("Friend Info")){
            this.remove(general);
            friendPanel = new JPanel();
            friendName = new JLabel("Friend Name");
            friendNameText = new JTextField(20);
            enterInfo = new JButton("enter");
            enterInfo.addActionListener(this);

            friendPanel.add(friendName);
            friendPanel.add(friendNameText);
            friendPanel.add(enterInfo);
            general = friendPanel;
            add(general, BorderLayout.CENTER);
            JFrame windowAncestor = (JFrame) SwingUtilities.getWindowAncestor(this);
            windowAncestor.revalidate();
        }
        if(e.getActionCommand().equals("enter")){

            for (int i = 0; i < friendList.size(); i++) {
                System.out.println("234t6235634264");
                if(friendList.get(i).getFriendName().equals(friendNameText.getText())){
                    infoPanel = new JPanel();
                    ip = new JLabel(friendList.get(i).getIpAddress());
                    port = new JLabel(friendList.get(i).getPortalNumber());
                    infoPanel.add(ip);
                    infoPanel.add(port);
                    general2 = infoPanel;
                    add(general2, BorderLayout.SOUTH);
                    JFrame windowAncestor = (JFrame) SwingUtilities.getWindowAncestor(this);
                    windowAncestor.revalidate();
                    break;
                }
            }
        }
    }

    public AddFriend(ArrayList<Friend> x){
        this.friendList = x;
        setLayout(new BorderLayout());
        buttonPanel = new JPanel();
        addFriend = new JButton("Add Friend");
        addFriend.addActionListener(this);
        getFriendInfo = new JButton("Friend Info");
        getFriendInfo.addActionListener(this);
        buttonPanel.add(addFriend);
        buttonPanel.add(getFriendInfo);
        this.validate();
        this.repaint();
        add(buttonPanel, BorderLayout.NORTH);
    }
}
