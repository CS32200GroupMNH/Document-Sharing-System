import javax.swing.*;
import java.awt.event.*;

public class DocumentSharingDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox documentTypeBox;
    private JTextField userNameField;
    private JLabel satusLabel;
    private JButton manageButton;

    public DocumentSharingDialog(String documentType) {
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
        documentTypeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String boxValue = (String)documentTypeBox.getSelectedItem();
                if(boxValue.equals("Shared")){
                    userNameField.setEditable(true);
                }
                else{
                    userNameField.setText("");
                    userNameField.setEditable(false);
                }
            }
        });
        manageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SharingManageDialog dialog = new SharingManageDialog();
                dialog.setLocationRelativeTo(contentPane);
                dialog.setLocationRelativeTo( contentPane);
                dialog.pack();
                dialog.setLocation(dialog.getX() -dialog.getWidth()/2 ,dialog.getY()-dialog.getHeight()/2);

                dialog.setVisible(true);
            }
        });

        documentTypeBox.setSelectedItem(documentType);
        if(documentType.equals("Shared")){
            userNameField.setEditable(true);
        }
        else{
            userNameField.setText("");
            userNameField.setEditable(false);
        }

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
        DocumentSharingDialog dialog = new DocumentSharingDialog("Shared");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
