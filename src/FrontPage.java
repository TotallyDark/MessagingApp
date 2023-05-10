import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontPage implements ActionListener {
    JFrame frame;
    JPanel buttonPanel, pagePanel;
    JButton SocialCircle, MessagePage, AddFriend, PersonalInfo;
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("SocialCircle")){
            SocialCircle x = new SocialCircle();
            x.getSocialCircle();
        }
    }
    public FrontPage(){
        frame = new JFrame("Mechat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        MessagePage = new JButton("MessagePage");
        MessagePage.addActionListener(this);
        buttonPanel.add(MessagePage);

        SocialCircle = new JButton("SocialCircle");
        SocialCircle.addActionListener(this);
        buttonPanel.add(SocialCircle);

        AddFriend = new JButton("AddFriend");
        SocialCircle.addActionListener(this);
        buttonPanel.add(AddFriend);

        PersonalInfo = new JButton("PersonalInfo");
        PersonalInfo.addActionListener(this);
        buttonPanel.add(PersonalInfo);

        pagePanel = new JPanel();
        frame.add(pagePanel, BorderLayout.NORTH);



        frame.pack();
        frame.setVisible(true);
    }
}
