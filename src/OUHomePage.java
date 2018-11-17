import javax.swing.*;

public class OUHomePage {
    private JPanel OUPanel;
    private JPanel SUHPPanel;
    private JLabel UserNameLabel;
    private JButton messagesButton1;
    private JButton newDocumentButton1;
    private JButton searchDocumentsButton1;
    private JButton searchUsersButton1;
    private JList list1;
    private JButton openDocumentButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("OUHomePage");
        frame.setContentPane(new OUHomePage().OUPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
