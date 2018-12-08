import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {


    private JPanel MainPanel;
    private JTextField userNameField;
    private JButton logInButton;
    private JPasswordField passwordField1;
    private JButton newUserButton;
    private JLabel incorrectPasswordLabel;

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public LoginPage() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();

                if(s.logIn(userNameField.getText(),passwordField1.getPassword())){///User Found and password is correct

                    //s.changePage("HomePage");


                }
                else{
                    System.out.println("INCORRECT PASSWORD");
                    incorrectPasswordLabel.setText("Incorrect Username or Password");
                }

            }
        });


        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                s.logInAsGuest();;
                incorrectPasswordLabel.setText("");
                passwordField1.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginPage");
        frame.setContentPane(new LoginPage().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
