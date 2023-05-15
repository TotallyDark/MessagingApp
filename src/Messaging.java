import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Messaging extends JPanel implements ActionListener {
    JButton send;
    JTextField text;
    public Messaging() {
        this.
        send = new JButton("Send");
        text = new JTextField(10);
        this.add(text);
        this.add(send);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
