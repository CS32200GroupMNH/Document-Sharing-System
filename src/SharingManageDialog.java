import javax.swing.*;
import java.awt.event.*;

public class SharingManageDialog extends JDialog {
    private JPanel contentPane;
    private JButton removeButton;
    private JButton buttonCancel;
    private JList userNameList;

    public SharingManageDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(removeButton);

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPress();
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
    }

    private void onPress() {
        // add your code here
        DefaultListModel model = (DefaultListModel) userNameList.getModel();
        int selectedIndex = userNameList.getSelectedIndex();
        if (selectedIndex != -1) {
            model.remove(selectedIndex);
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        SharingManageDialog dialog = new SharingManageDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
