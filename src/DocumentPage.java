import javax.swing.*;

public class DocumentPage {
    private JPanel DocumentPanel;
    private JToolBar DocumentToolBar;
    private JButton documentButton;
    private JButton versionsButton;
    private JButton sharingButton;
    private JButton complaintsButton;
    private JTextArea textArea1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("DocumentPage");
        frame.setContentPane(new DocumentPage().DocumentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
