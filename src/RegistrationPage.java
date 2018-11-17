import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationPage {


    private JPanel RegestrationPanel;
    private JTextField userNameField;
    private JButton registerButton;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JLabel statusLabel;

    public RegistrationPage() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();

                if(s.registerUser(userNameField.getText(),passwordField1.getPassword(),"SU")){
                    statusLabel.setText("Successfully Registered User");
                }
                else{
                    statusLabel.setText("Unable to Register. Try a different username.");
                }
            }
        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                s.changePage("LoginPage");
            }
        });
    }

    public JPanel getRegestrationPanel() {
        return RegestrationPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RegistrationPage");
        frame.setContentPane(new RegistrationPage().RegestrationPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
