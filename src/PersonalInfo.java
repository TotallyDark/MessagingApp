import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class PersonalInfo extends JPanel implements ActionListener{
    JButton NameSet, ProfileSet;
    JLabel label1, label2, label3, label4, Profile1, newProfile;
    JTextField text;
    JPanel Top, first, second, NameSection, ProfileSection, third, fourth;
    private String name;
    private FrontPage fp;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Set Name")) {
            NameSection = new JPanel();
            NameSection.setLayout(new GridLayout(2, 1));
            first = new JPanel();
            second = new JPanel();

            label1 = new JLabel("Current Name:");
            label2 = new JLabel(name);
            first.add(label1);
            first.add(label2);

            label3 = new JLabel("New Name: ");
            text = new JTextField(15);
            second.add(label3);
            second.add(text);

            NameSection.add(first);
            NameSection.add(second);
            this.add(NameSection, BorderLayout.CENTER);
            this.validate();
            this.repaint();
        }
        if(e.getActionCommand().equals("Set Profile")) {
            ProfileSection = new JPanel();
            ProfileSection.setLayout(new GridLayout(2, 1));
            third = new JPanel();
            fourth = new JPanel();

            label4 = new JLabel("Current Profile");

            third.add(label4);

            newProfile = new JLabel("New Profile: ");
        }
        if(!text.getText().equals("")) {
            text.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        SwingUtilities.invokeLater(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        label2.setText(text.getText());
                                        fp.setName(text.getText());
                                        text.setText("");
                                    }
                                }
                        );

                    }
                }
            });
            this.validate();
            this.repaint();
        }
    }
    public PersonalInfo(String name, FrontPage fp) {
        this.fp = fp;
        this.name = name;
        setLayout(new BorderLayout());
        Top = new JPanel();

        NameSet = new JButton("Set Name");
        NameSet.addActionListener(this);
        Top.add(NameSet);

        ProfileSet = new JButton("Set Profile");
        ProfileSet.addActionListener(this);
        Top.add(ProfileSet);


        this.validate();
        this.repaint();
        this.add(Top, BorderLayout.NORTH);
    }

}
