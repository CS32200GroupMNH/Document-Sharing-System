import javax.swing.*;

public class RegistrationPage {
    private JPanel RegestrationPanel;
    private JTextField userNameField;
    private JButton registerButton;
    private JPasswordField passwordField1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("RegistrationPage");
        frame.setContentPane(new RegistrationPage().RegestrationPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
