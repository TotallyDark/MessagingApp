import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SocialCircle implements ActionListener{
    JFrame frame;
    JPanel frontPage;
    JButton button;
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public SocialCircle(){
        frame = new JFrame("Social Circle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frontPage = new JPanel();
        frame.add(frontPage, BorderLayout.NORTH);

        button = new JButton("u");
        frontPage.add(button);

        frame.pack();
        frame.setVisible(true);
    }

    public void getSocialCircle(){

    }

}