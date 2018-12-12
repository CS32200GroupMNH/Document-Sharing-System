import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accept_Deny_Invitation extends JFrame{
    private JLabel AcceptDeny;
    private JButton acceptInvitationButton;
    private JButton denyInvitationButton;
    private JPanel rootPanel;

    public Accept_Deny_Invitation(){

        //This uses the form designer form
        add(rootPanel);

        setTitle("This accept or Deny Invitation");
        setSize(400,500);
        acceptInvitationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane, "Accepting the user Invitation");
            }
        });
        denyInvitationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane,"Declining the inivation for a user");
            }
        });
    }



public static void main(String[]args){

    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (UnsupportedLookAndFeelException e) {
        e.printStackTrace();
    }

    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {

            Accept_Deny_Invitation ad = new Accept_Deny_Invitation();
            ad.setVisible(true);
        }
    });
}
}
