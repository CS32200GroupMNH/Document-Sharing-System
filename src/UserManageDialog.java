import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserManageDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList currentUserLIst;
    private JButton deleteUserButton;
    private JButton changeMembershipButton;
    private JButton viewUserSDocumentsButton;

    private ArrayList<String> userList;

    public UserManageDialog(ArrayList<String> u) {
        userList = u;
        userList.remove("sys");
        this.updateUserList(userList);
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
        changeMembershipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentUserLIst.getSelectedIndex() > -1) {
                    int a = JOptionPane.showOptionDialog(contentPane,
                            "Update user to be?",
                            "Change Membership",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new String[]{"Ordinary User", "Super User", "Cancel"}, // this is the array
                            "default");
                    SystemManager s = SystemManager.getInstance();
                    if (a == JOptionPane.YES_OPTION) {
                        //Change membership
                        s.changeUserType("OU",(String) currentUserLIst.getSelectedValue());
                        System.out.println("OU");
                    } else if (a == JOptionPane.NO_OPTION) {
                        //Change membership
                        s.changeUserType("SU",(String) currentUserLIst.getSelectedValue());
                        System.out.println("SU");
                    }
                }

            }



        });
        viewUserSDocumentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                if(currentUserLIst.getSelectedIndex() > -1) {
                    ManageUserDocumentDialog dialog = new ManageUserDocumentDialog(s.getDocumentsOfUser((String) currentUserLIst.getSelectedValue()));
                    dialog.setLocationRelativeTo( contentPane);
                    dialog.pack();
                    dialog.setLocation(dialog.getX() -dialog.getWidth()/2 ,dialog.getY()-dialog.getHeight()/2);
                    dialog.setVisible(true);
                }
            }
        });
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                if(currentUserLIst.getSelectedIndex() > -1) {
                    if( s.deleteUser((String) currentUserLIst.getSelectedValue())){
                        //remove from list
                        userList.remove(currentUserLIst.getSelectedValue());
                        updateUserList(userList);
                    }
                }
            }
        });
    }

    public void updateUserList(ArrayList<String> wordList){
        DefaultListModel docListModel = new DefaultListModel();
        for (String s: wordList) {
            docListModel.addElement(s);
        }
        currentUserLIst.setModel(docListModel);

    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        SystemManager s = SystemManager.getInstance();
        s.goHome();
        dispose();
    }

    public static void main(String[] args) {
        //UserManageDialog dialog = new UserManageDialog();
       // dialog.pack();
        //dialog.setVisible(true);
        System.exit(0);
    }
}
