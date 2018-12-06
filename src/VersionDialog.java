import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class VersionDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;
    private JScrollPane versionTable;

    public VersionDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        String[] columns = new String[] {
                "Version", "Edited By", "Date"
        };

        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
                {"1","user1","December 5th 2018"},
                {"2","user2","December 4th 2018"},
                {"3","user3","December 3rd 2018"},
        };
        this.table1.setModel(new DefaultTableModel(data, columns));

    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        VersionDialog dialog = new VersionDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
