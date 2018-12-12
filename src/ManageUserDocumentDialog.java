import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ManageUserDocumentDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList docList;
    private JButton unlockButton;
    private JButton deleteButton;

    private ArrayList<Document> documentArrList;

    public ManageUserDocumentDialog(ArrayList<Document> d) {
        documentArrList = d;
        this.listDocuments(d);
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
        unlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(docList.getSelectedIndex() > -1){
                    SystemManager s = SystemManager.getInstance();
                    if(s.unLockDocument(((Document) docList.getSelectedValue()).getDocumentID())){
                        JOptionPane.showMessageDialog(contentPane,"Unlocked Document.");
                    }
                    else{
                        JOptionPane.showMessageDialog(contentPane,"Couldn't Document.");
                    }
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(docList.getSelectedIndex() > -1){
                    SystemManager s = SystemManager.getInstance();
                    if(s.deleteDocument(((Document) docList.getSelectedValue()).getDocumentID())){
                        documentArrList.remove((Document) docList.getSelectedValue());
                        listDocuments(documentArrList);
                    }
                    else{
                        JOptionPane.showMessageDialog(contentPane,"Couldn't Delete Document.");
                    }
                }
            }
        });
    }

    public void listDocuments(ArrayList<Document> docArray){
        DefaultListModel docListModel = new DefaultListModel();
        for (Document d: docArray) {
            docListModel.addElement(d);
        }
        docList.setModel(docListModel);

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
