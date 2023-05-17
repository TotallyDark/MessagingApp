import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PersonalInfo extends JPanel implements ActionListener{
    JButton NameSet, ProfileSet, Change;
    JLabel label1, label2;
    JTextField text;


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("NameSet")) {
            label1 = new JLabel("Current Name: ");
            label2 = new JLabel("User");
            this.add(label1);
            this.add(label2);

        }
    }
    public PersonalInfo() {
        NameSet = new JButton("Set Name");
        NameSet.addActionListener(this);
        this.add(NameSet);

        ProfileSet = new JButton("Set Profile");
        ProfileSet.addActionListener(this);
        this.add(ProfileSet);
    }

}
