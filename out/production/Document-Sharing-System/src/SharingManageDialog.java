import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SharingManageDialog extends JDialog {
    private JPanel contentPane;
    private JButton removeButton;
    private JButton buttonCancel;
    private JList userNameList;

    private Document currentDocument;

    public SharingManageDialog(Document d) {
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

        this.listUsers(d.getSharedUsers());
        this.currentDocument = d;
    }

    public void listUsers(ArrayList<String> userList){
        DefaultListModel docListModel = new DefaultListModel();
        for (String s: userList) {
            docListModel.addElement(s);
        }
        userNameList.setModel(docListModel);



    }

    private void onPress() {

        DefaultListModel model = (DefaultListModel) userNameList.getModel();
        String user = (String) userNameList.getSelectedValue();
        int selectedIndex = userNameList.getSelectedIndex();



        if (currentDocument.removeUserFromDocument(user)) {
            if (selectedIndex != -1) {
                model.remove(selectedIndex);
            }
        }
        else{
            JOptionPane.showMessageDialog(contentPane,
                    "User was unable to be removed.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }



    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        SharingManageDialog dialog = new SharingManageDialog(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
