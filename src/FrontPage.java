import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FrontPage implements ActionListener {
    JFrame frame;
    JPanel buttonPanel, pagePanel;
    JButton SocialCircle, MessagePage, AddFriend, PersonalInfo;
    private String user = "User";
    private JPanel thePanel = new JPanel();
    private final Dimension PhotoDimension = new Dimension(50, 50);
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("SocialCircle")){
            pagePanel.remove(thePanel);
            SocialCircle x = new SocialCircle(frame);
            pagePanel.add(x);
            frame.getContentPane().validate();
            frame.getContentPane().repaint();
            thePanel = x;
        }
        else if(e.getActionCommand().equals("PersonalInfo")) {
            pagePanel.remove(thePanel);
            PersonalInfo y = new PersonalInfo(user, this);
            pagePanel.add(y, BorderLayout.CENTER);
            frame.getContentPane().validate();
            frame.getContentPane().repaint();
            thePanel = y;
        }
        else if(e.getActionCommand().equals("MessagePage")) {
            pagePanel.remove(thePanel);
            Messaging z = new Messaging();
            pagePanel.add(z, BorderLayout.SOUTH);
            frame.getContentPane().validate();
            frame.getContentPane().repaint();
            thePanel = z;
        }
    }
    public FrontPage() throws IOException {
        frame = new JFrame("Mechat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonPanel = new JPanel();

        MessagePage = new JButton("MessagePage");
        MessagePage.addActionListener(this);
        buttonPanel.add(MessagePage);

        SocialCircle = new JButton("SocialCircle");
        SocialCircle.addActionListener(this);
        buttonPanel.add(SocialCircle);

        AddFriend = new JButton("AddFriend");
        SocialCircle.addActionListener(this);
        buttonPanel.add(AddFriend);

        PersonalInfo = new JButton("PersonalInfo");
        PersonalInfo.addActionListener(this);
        buttonPanel.add(PersonalInfo);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        ImageIcon IIcon = new ImageIcon("Photos/Profile_Picture_Default.png");
        JLabel image = new JLabel(IIcon);

        Image img = ImageIO.read(new File("Photos/Profile_Picture_Default.png"));
        BufferedImage mainIcon = (BufferedImage) img;
        int diameter = Math.min(mainIcon.getHeight(), mainIcon.getWidth());
        BufferedImage mask = new BufferedImage(mainIcon.getWidth(), mainIcon.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = mask.createGraphics();

        g.fillOval(0,0, diameter-1, diameter-1);
        g.dispose();

        BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        g = masked.createGraphics();
        int x = (diameter-mainIcon.getWidth()) / 2;
        int y = (diameter-mainIcon.getHeight()) / 2;
        g.drawImage(mainIcon,x,y,null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
        g.drawImage(mask,0,0,null);
        g.dispose();


        IIcon.setImage(masked.getScaledInstance((int)PhotoDimension.getWidth(), (int)PhotoDimension.getHeight(), Image.SCALE_DEFAULT));
        buttonPanel.add(image);
        image.setPreferredSize(PhotoDimension);


        pagePanel = new JPanel();
        frame.add(pagePanel, BorderLayout.NORTH);
        pagePanel.setSize(600,600);

        frame.setSize(600, 600);
        frame.setVisible(true);
    }
    public void setName(String name) {
       this.user = name;
    }
}
