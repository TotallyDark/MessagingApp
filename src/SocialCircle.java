import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SocialCircle extends JPanel implements ActionListener{
    JTextField textField = new JTextField(20);
    JPanel buttonPanel = new JPanel();
    private JList<String> textMessage;
    private DefaultListModel<String> listModel;
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public SocialCircle(JFrame frame) {
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        buttonPanel.add(textField);
        buttonPanel.add(new JButton("Image"));

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
                    listModel.addElement(text);
                    repaint();
                }
            }
        });

    }
}