import javax.swing.*;

public class SearchUsersPage {
    private JPanel SearchUsersPanel;
    private JTextField textField1;
    private JList list1;
    private JButton openButton;
    private JButton searchButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("SearchUsersPage");
        frame.setContentPane(new SearchUsersPage().SearchUsersPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
