import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PersonalInfo extends JPanel implements ActionListener{
    JButton NameSet, ProfileSet;


    @Override
    public void actionPerformed(ActionEvent e) {

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
