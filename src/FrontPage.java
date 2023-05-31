import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FrontPage implements ActionListener {
    private String url = "https://as2.ftcdn.net/v2/jpg/00/64/67/63/1000_F_64676383_LdbmhiNM6Ypzb3FM4PPuFP9rHe7ri8Ju.jpg";
    JFrame frame;
    JPanel buttonPanel, pagePanel, portPanel;
    JButton SocialCircle, AddFriend, PersonalInfo, msgAFriend;
    JLabel profile, portLabel;
    JTextField MessagePage;
    Server server;
    private int portNum = (int)(Math.random()*10000 +1);
    private ArrayList<Friend> friendList = new ArrayList<>();
    private String user = "User", URL;
    private JPanel thePanel = new JPanel();
    private JLabel general = new JLabel();
    private final Dimension PhotoDimension = new Dimension(50, 50);

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SocialCircle")) {
            pagePanel.remove(thePanel);
            SocialCircle x = new SocialCircle(this);
            pagePanel.add(x);
            frame.getContentPane().validate();
            frame.getContentPane().repaint();
            thePanel = x;
        } else if (e.getActionCommand().equals("PersonalInfo")) {
            pagePanel.remove(thePanel);
            PersonalInfo y = new PersonalInfo(user, this, URL);
            pagePanel.add(y, BorderLayout.CENTER);
            frame.getContentPane().validate();
            frame.getContentPane().repaint();
            thePanel = y;
        } else if (e.getActionCommand().equals("Message A Friend")) {
            messageAFriend m = new messageAFriend(this);
            m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            m.setVisible(true);
        } else if(e.getActionCommand().equals("AddFriend")) {
            pagePanel.remove(thePanel);
            AddFriend a = new AddFriend(friendList);
            pagePanel.add(a, BorderLayout.CENTER);
            frame.getContentPane().validate();
            frame.getContentPane().repaint();
            thePanel = a;
        }
    }

    public FrontPage() throws IOException {
        startServer();
        frame = new JFrame("Never gonna give you up, never gonna let you down...");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonPanel = new JPanel();

        MessagePage = new JTextField(String.valueOf(portNum),10);
        MessagePage.addActionListener(this);
        portPanel = new JPanel();
        portLabel = new JLabel("My Port Num:");
        portPanel.setLayout(new GridLayout(2,1));
        portPanel.add(portLabel);
        portPanel.add(MessagePage);
        buttonPanel.add(portPanel);
        MessagePage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    portNum = Integer.parseInt(MessagePage.getText());
                    try {
                        server.changePort(portNum);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    SwingWorker worker1 = new SwingWorker<ImageIcon[], Void>() {
                        @Override
                        public ImageIcon[] doInBackground() {
                            server.startRun();
                            return new ImageIcon[0];
                        }
                    };
                    worker1.execute();
                }
            }
        });

        SocialCircle = new JButton("SocialCircle");
        SocialCircle.addActionListener(this);
        buttonPanel.add(SocialCircle);

        msgAFriend = new JButton("Message A Friend");
        msgAFriend.addActionListener(this);
        buttonPanel.add(msgAFriend);

        AddFriend = new JButton("AddFriend");
        AddFriend.addActionListener(this);
        buttonPanel.add(AddFriend);

        PersonalInfo = new JButton("PersonalInfo");
        PersonalInfo.addActionListener(this);
        buttonPanel.add(PersonalInfo);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        profile = new JLabel();
        setImage();

        pagePanel = new JPanel();
        frame.add(pagePanel, BorderLayout.NORTH);
        pagePanel.setSize(600, 600);

        frame.setSize(700, 300);
        frame.setVisible(true);
        server.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        server.getContentPane().setName("Message Page");

        myWorker().execute();
    }

    public void setImage() throws MalformedURLException {
        buttonPanel.remove(general);
        ImageIcon IIcon = new ImageIcon(url);
        Image img = null;
        URL url1 = new URL(url);
        try {
            img = ImageIO.read(url1);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        BufferedImage mainIcon = (BufferedImage) img;
        int diameter = Math.min(mainIcon.getHeight(), mainIcon.getWidth());
        BufferedImage mask = new BufferedImage(mainIcon.getWidth(), mainIcon.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = mask.createGraphics();

        g.fillOval(0, 0, diameter - 1, diameter - 1);
        g.dispose();

        BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        g = masked.createGraphics();
        int x = (diameter - mainIcon.getWidth()) / 2;
        int y = (diameter - mainIcon.getHeight()) / 2;
        g.drawImage(mainIcon, x, y, null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
        g.drawImage(mask, 0, 0, null);
        g.dispose();


        IIcon.setImage(masked.getScaledInstance((int) PhotoDimension.getWidth(), (int) PhotoDimension.getHeight(), Image.SCALE_DEFAULT));
        profile = new JLabel(IIcon);
        general = profile;
        buttonPanel.add(general);
        frame.repaint();
        frame.revalidate();
    }
    public SwingWorker myWorker() {
        SwingWorker worker = new SwingWorker<ImageIcon[], Void>() {
            @Override
            public ImageIcon[] doInBackground() {
                server.startRun();
                return new ImageIcon[0];
            }
        };
        return worker;
    }
    public void startServer() {
        server = new Server(portNum);
        server.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }

    public void setName(String name) {
       this.user = name;
    }
    public void setUrl(String url) {this.url = url;}

    public void setFriend(ArrayList<Friend> x) {this.friendList = x;}
    public String getName(){
        return user;
    }
    public Server getServer() {
        return this.server;
    }
}
