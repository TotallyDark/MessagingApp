import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SocialCircle extends JPanel implements ActionListener{
    JTextField textField = new JTextField(30);
    JPanel buttonPanel = new JPanel();
    private JList<String> textMessage;
    private DefaultListModel<String> listModel;
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public SocialCircle(JFrame frame) {
        setLayout(new BorderLayout());
        JButton text = new JButton("Text Message");
        add(text, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        buttonPanel.add(textField);

        listModel = new DefaultListModel<>();
        textMessage = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(textMessage);
        add(scrollPane, BorderLayout.SOUTH);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    String text =textField.getText();
                    textField.setText("");
                    listModel.addElement(getName() + ":" + text);
                    repaint();
                }
            }
        });

    }
}