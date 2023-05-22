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
    JTextField text;
    JPanel Top, first, second, NameSection, ProfileSection, third, fourth;
    private String name;
    private FrontPage fp;
    private final Dimension PhotoDimension = new Dimension(50, 50);

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
                this.validate();
                this.repaint();
            }
        }
        if(e.getActionCommand().equals("Set Profile")) {
            ProfileSection = new JPanel();
            ProfileSection.setLayout(new GridLayout(1, 1));
            third = new JPanel();
            URL url = null;
            try {
                url = new URL("https://hips.hearstapps.com/hmg-prod/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=0.752xw:1.00xh;0.175xw,0&resize=1200:*");
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
            Image image;
            try {
                image = ImageIO.read(url);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Image i = image.getScaledInstance((int)PhotoDimension.getWidth(), (int)PhotoDimension.getHeight(), Image.SCALE_DEFAULT);
            Profile1 = new JLabel(new ImageIcon(i));
            ProfileSection.add(Profile1);
            this.add(ProfileSection, BorderLayout.CENTER);
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
