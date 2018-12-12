import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FileComplaintDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList sendingList;
    private JButton sendComplaintButton;
    private JTextArea complaintArea;

    private ArrayList<String> userList;

    public FileComplaintDialog(Document d) {
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
        sendComplaintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sendingList.getSelectedIndex() > -1){
                    SystemManager s = SystemManager.getInstance();
                    if(s.sendMessage((String) sendingList.getSelectedValue(),"C",d.getDocumentID(),complaintArea.getText())){
                        JOptionPane.showMessageDialog(contentPane,"Complaint Sent");
                    }
                }
            }
        });

        SystemManager s = SystemManager.getInstance();
        ArrayList<String> superUsers = s.getAllSuperUsers();
        superUsers.add(0,"owner");
        updateUserList(superUsers);

    }

    public void updateUserList(ArrayList<String> wordList){
        DefaultListModel docListModel = new DefaultListModel();
        for (String s: wordList) {
            docListModel.addElement(s);
        }
        sendingList.setModel(docListModel);

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

        System.exit(0);
    }
}
