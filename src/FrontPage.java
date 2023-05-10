import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontPage implements ActionListener {
    JFrame frame;
    JPanel buttonPanel;
    JButton SocialCircle, MessagePage;
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

        MessagePage = new JButton("MessagePage");
        MessagePage.addActionListener(this);
        buttonPanel.add(MessagePage);

        SocialCircle = new JButton("SocialCircle");
        SocialCircle.addActionListener(this);
        buttonPanel.add(SocialCircle);

        frame.pack();
        frame.setVisible(true);
    }
}
