import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

public class MessagesPage extends JDialog {
    private JPanel contentPane;
    private JButton cancelButton;
    private JButton OKButton;
    private JPanel MessagesPanel;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JButton viewComplaintButton;
    private JTable table2;
    private JButton SendButton;
    private JButton viewMessagesButton;



     public static void main (String[]args){
            JFrame frame = new JFrame("MessagesPage");
            MessagesPage m = new MessagesPage();
            frame.setContentPane(m.MessagesPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            m.pack();
            m.setVisible(true);


            // TableColumnModel  t = m.table1.getColumnModel();

            String[] columns = new String[]{
                    "Document", "User", "Subject"
            };

            //actual data for the table in a 2d array
            Object[][] data = new Object[][]{
                    {"document1", "name1", "My Complaint"},
                    {"document2", "name3", "Owner of document is bad"},
                    {"document4", "name5", "Complaint"},
            };

            String[] columns2 = new String[]{
                    "User", "Subject"
            };

            //actual data for the table in a 2d array
            Object[][] data2 = new Object[][]{
                    {"name1", "Upgrade User Status"},
                    {"name3", "Can I be a Ordinary User?"},
                    {"name5", "OU request"},
            };

            m.table1.setModel(new DefaultTableModel(data, columns));
            m.table2.setModel(new DefaultTableModel(data2, columns2));

        }

    }




