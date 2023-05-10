import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontPage implements ActionListener {
    JFrame frame;
    JPanel buttonPanel, pagePanel;
    JButton SocialCircle, MessagePage, AddFriend;
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("SocialCircle")){

        }
    }
    public FrontPage(){
        frame = new JFrame("Mechat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        SocialCircle = new JButton("SocialCircle");
        SocialCircle.addActionListener(this);
        buttonPanel.add(SocialCircle);



        AddFriend = new JButton("AddFriend");
        SocialCircle.addActionListener(this);
        buttonPanel.add(AddFriend);

        pagePanel = new JPanel();
        frame.add(pagePanel, BorderLayout.NORTH);



        frame.pack();
        frame.setVisible(true);
    }
}
