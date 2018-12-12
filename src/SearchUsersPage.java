import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchUsersPage {
    public JPanel getSearchUsersPanel() {
        return SearchUsersPanel;
    }

    private JPanel SearchUsersPanel;
    private JTextField nameFeild;
    private JList resultLIst;
    private JButton openButton;
    private JButton searchButton;
    private JTextField otherField;
    private JButton homeButton;

    public SearchUsersPage() {

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                listUsers(s.searchUsers(nameFeild.getText(),otherField.getText()));
            }
        });
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(resultLIst.getSelectedIndex() > -1){
                    SystemManager s = SystemManager.getInstance();
                    if(resultLIst.getSelectedValue() != null) {
                        openUserInformation((User) resultLIst.getSelectedValue());
                    }
                }
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                ArrayList<User> emptyAr = new ArrayList<User>();
                listUsers(emptyAr);
                nameFeild.setText("");
                otherField.setText("");
                s.goHome();
            }
        });
    }

    public void openUserInformation(User u){
        String information = "Username is: " + u.getUserName() + "\nUser Interests Are: " + u.getUserInterests();

        JOptionPane.showMessageDialog(SearchUsersPanel,information);

    }

    public void listUsers(ArrayList<User> docArray){
        DefaultListModel userListModel = new DefaultListModel();
        for (User u: docArray) {
            userListModel.addElement(u);
        }
        resultLIst.setModel(userListModel);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SearchUsersPage");
        frame.setContentPane(new SearchUsersPage().SearchUsersPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
