import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchUsersPage {
    private JPanel SearchUsersPanel;
    private JTextField textField1;
    private JList list1;
    private JButton openButton;
    private JButton searchButton;

    public SearchUsersPage() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Hello");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SearchUsersPage");
        frame.setContentPane(new SearchUsersPage().SearchUsersPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
