import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestUserHomePage {


    private JPanel GUPanel;
    private JPanel SUHPPanel;
    private JButton applyToBecomeOrdinaryButton;
    private JList list1;
    private JButton openDocumentButton;
    private JButton suggestTabooWordsButton;

    public GuestUserHomePage() {
        applyToBecomeOrdinaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                s.changePage("RegistrationPage");
            }
        });
    }

    public JPanel getGUPanel() {
        return GUPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GuestUserHomePage");
        frame.setContentPane(new GuestUserHomePage().GUPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
