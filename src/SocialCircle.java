import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SocialCircle extends JPanel implements ActionListener{
    JTextField textField = new JTextField(50);
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public SocialCircle(){
        this.add(textField);
        this.add(new JButton("Image"));
        textField.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                String text = textField.getText();
                JLabel text1 = new JLabel(text);
                add(text1);
            }});
    }
}