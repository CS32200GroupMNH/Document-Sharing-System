
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
    public class MessengerGu {
        // Component and container object declared for user 1 and user 2
        JFrame user1, user2;
        JTextField user1T, user2T;
        JLabel user1L, user1Message, user2L, user2Message;
        JPanel jp1, user1MessP, user1MainP, jp2, user2MessP, user2MainP;
        JButton user1B, user2B;

        // Default constructor
        MessengerGu() {
// Initializes components and containers for user 1 and user 2
            user1 = new JFrame("User One Message Box");
            user2 = new JFrame("User Two Message Box");
            user1T = new JTextField(10);
            user2T = new JTextField(10);
            user1L = new JLabel("Enter your message: ");
            user1Message = new JLabel();
            user2L = new JLabel("Enter your message: ");
            user2Message = new JLabel();
            jp1 = new JPanel();
            jp2 = new JPanel();
            user1MessP = new JPanel();
            user1MainP = new JPanel();
            user2MessP = new JPanel();
            user2MainP = new JPanel();
// Set the layout to two rows and one column for user 1 and user 2
            user1MainP.setLayout(new GridLayout(2, 1));
            user2MainP.setLayout(new GridLayout(2, 1));
            user1B = new JButton("Send");
            user2B = new JButton("Send");
// Adds the components to panel 1 for user 1
            jp1.add(user1L);
            jp1.add(user1T);
            user1MessP.add(user1Message);
            jp1.add(user1B);
// Adds the components to panel 2 for user 2
            jp2.add(user2L);
            jp2.add(user2T);
            user2MessP.add(user2Message);
            jp2.add(user2B);
// Adds the panels to main panel1 for user 1
            user1MainP.add(jp1);
            user1MainP.add(user1MessP);
// Adds the panels to main panel2 for user 2
            user2MainP.add(jp2);
            user2MainP.add(user2MessP);
// Adds the main panel of user 1 to frame 1 for user 1
            user1.add(user1MainP);
// Adds the main panel of user 1 to frame 1 for user 1
            user2.add(user2MainP);
// Set the visible property of both the frames to true

            user1.setVisible(true);
            user2.setVisible(true);
// Set the size property of both the frames to 400 width and 200 height
            user1.setSize(400, 200);
            user2.setSize(400, 200);
// Registers action listener for user 1 SEND button using anonymous class
            user1B.addActionListener(new ActionListener()
            {
                // Overrides actionPerformed() method of ActionListener interface
                public void actionPerformed(ActionEvent ae)
                {
// Set the label text of user 2 with the text of user 1 text field data
                    user2Message.setText(user1T.getText());
                }// End of method
            });// End of anonymous class
// Registers action listener for user 2 SEND button using anonymous class
            user2B.addActionListener(new ActionListener()
            {
                // Overrides actionPerformed() method of ActionListener interface
                public void actionPerformed(ActionEvent ae)
                {
// Set the label text of user 1 with the text of user 2 text field data
                    user1Message.setText(user2T.getText());
                }// End of method
            });// End of anonymous class
        }// End of constructor
        // main method definition
        public static void main(String[] args)
        {
// Calls the constructor of MessagePassing class
            new MessengerGu();
        }// End of main method
    }// End of class


