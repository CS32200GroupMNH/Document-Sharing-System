import javax.swing.*;

public class SUHomePage {
    private JPanel SUHPPanel;
    private JLabel UserNameLabel;
    private JButton messagesButton;
    private JButton tabooWordsButton;
    private JButton newDocumentButton1;
    private JButton searchDocumentsButton1;
    private JButton searchUsersButton1;
    private JList list1;
    private JButton openDocumentButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("SUHomePage");
        frame.setContentPane(new SUHomePage().SUHPPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
