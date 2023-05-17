import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SocialCircle extends JPanel implements ActionListener{
    JTextField textField = new JTextField(20);
    JPanel buttonPanel = new JPanel();
    JPanel textPage = new JPanel();
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public SocialCircle() {
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        buttonPanel.add(textField);
        buttonPanel.add(new JButton("Image"));
        add(textPage, BorderLayout.SOUTH);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    String text =textField.getText();
                    textField.setText("");
                    JLabel text1 = new JLabel(text);
                    textPage.add(text1);
                    repaint();
                    System.out.println(getSize());
                }
            }
        });

    }
}