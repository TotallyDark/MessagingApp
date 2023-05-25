import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class PersonalInfo extends JPanel implements ActionListener{
    JButton NameSet, ProfileSet;
    JLabel label1, label2, label3, Profile1, newProfile;
    JTextField text, text1;
    JPanel Top, first, second, NameSection, ProfileSection, third;
    private JPanel general = new JPanel();
    private String name, URL;
    private FrontPage fp;
    private final Dimension PhotoDimension = new Dimension(50, 50);

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Set Name")) {
            this.remove(general);
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
            general = NameSection;
            this.add(general, BorderLayout.CENTER);
            this.validate();
            this.repaint();
            if(text.getText() != null) {
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
                JFrame windowAncestor = (JFrame) SwingUtilities.getWindowAncestor(this);
                windowAncestor.revalidate();
            }
        }
        if(e.getActionCommand().equals("Set Profile")) {
            this.remove(general);
            ProfileSection = new JPanel();
            ProfileSection.setLayout(new GridLayout(2, 1));
            third = new JPanel();
            newProfile = new JLabel("New Profile Picture URL: ");
            text1 = new JTextField(15);
            third.add(newProfile);
            third.add(text1);
            ProfileSection.add(third);

            if(text1.getText() != null) {
                text1.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_ENTER){
                            SwingUtilities.invokeLater(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            fp.setUrl(text1.getText());
                                            try {
                                                fp.setImage();
                                            } catch (MalformedURLException ex) {
                                                throw new RuntimeException(ex);
                                            }
                                            text1.setText("");

                                        }
                                    }
                            );

                        }
                    }
                });
            }
            general = ProfileSection;
            this.add(general, BorderLayout.CENTER);
            JFrame windowAncestor = (JFrame) SwingUtilities.getWindowAncestor(this);
            windowAncestor.revalidate();
        }
    }
    public PersonalInfo(String name, FrontPage fp, String URL) {
        this.fp = fp;
        this.name = name;
        this.URL = URL;
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
