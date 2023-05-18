import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PersonalInfo extends JPanel implements ActionListener{
    JButton NameSet, ProfileSet, Change;
    JLabel label1, label2;
    JTextField text;
    JPanel Top, Center;


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Set Name")) {
            Center = new JPanel();
            Center.setSize(10, 10);
            label1 = new JLabel("Current Name:");
            label2 = new JLabel("User");
            Center.add(label1);
            Center.add(label2);
            this.add(Center, BorderLayout.CENTER);
            this.validate();
            this.repaint();
        }
    }
    public PersonalInfo() {
        setLayout(new BorderLayout());
        Top = new JPanel();

        NameSet = new JButton("Set Name");
        NameSet.addActionListener(this);
        Top.add(NameSet);

        ProfileSet = new JButton("Set Profile");
        ProfileSet.addActionListener(this);
        Top.add(ProfileSet);

        this.add(Top, BorderLayout.NORTH);
        this.validate();
        this.repaint();
    }

}
