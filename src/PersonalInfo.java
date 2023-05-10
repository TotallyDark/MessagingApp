import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PersonalInfo implements ActionListener{
    JFrame frame1;
    JPanel ButtonPanel;
    JButton NameSet, ProfileSet;

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public PersonalInfo() {
        frame1 = new JFrame("Personal Information");

        ButtonPanel = new JPanel();
        frame1.add(ButtonPanel, BorderLayout.SOUTH);

        NameSet = new JButton("Set Name");
        NameSet.addActionListener(this);
        ButtonPanel.add(NameSet);

        ProfileSet = new JButton("Set Profile");
        ProfileSet.addActionListener(this);
        ButtonPanel.add(ProfileSet);

        ButtonPanel = new JPanel();
        frame1.add(ButtonPanel, BorderLayout.NORTH);

    }
}
