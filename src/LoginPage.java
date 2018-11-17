import javax.swing.*;

public class LoginPage {
    private JPanel MainPanel;
    private JTextField userNameField;
    private JButton logInButton;
    private JPasswordField passwordField1;
    private JButton newUserButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginPage");
        frame.setContentPane(new LoginPage().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
