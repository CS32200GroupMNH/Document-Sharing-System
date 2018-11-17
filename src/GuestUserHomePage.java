import javax.swing.*;

public class GuestUserHomePage {
    private JPanel GUPanel;
    private JPanel SUHPPanel;
    private JLabel UserNameLabel;
    private JButton applyToBecomeOrdinaryButton;
    private JList list1;
    private JButton openDocumentButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GuestUserHomePage");
        frame.setContentPane(new GuestUserHomePage().GUPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
