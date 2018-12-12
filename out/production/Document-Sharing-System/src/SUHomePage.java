import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class SUHomePage {


    private JPanel SUHPPanel;
    private JLabel UserNameLabel;
    private JButton messagesButton;
    private JButton tabooWordsButton;
    private JButton newDocumentButton1;
    private JButton searchDocumentsButton1;
    private JButton searchUsersButton1;
    private JList docList;
    private JButton openDocumentButton;
    private JLabel ImageLabel;
    private JButton manageUsersButton;

    public SUHomePage() {
        newDocumentButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                s.changePage("NewDocumentPage");
            }
        });
        openDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(docList.getSelectedIndex() > -1){
                    SystemManager s = SystemManager.getInstance();
                    if(docList.getSelectedValue() != null) {
                        s.openDocumentFromObject((Document) docList.getSelectedValue());
                    }
                }
            }
        });
        tabooWordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                TabooWordsDialog dialog = new TabooWordsDialog(s.getTabooWords("GLOBAL"));
                dialog.setLocationRelativeTo( SUHPPanel);
                dialog.pack();
                dialog.setLocation(dialog.getX() -dialog.getWidth()/2 ,dialog.getY()-dialog.getHeight()/2);
                dialog.setVisible(true);
            }
        });
        manageUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                UserManageDialog dialog = new UserManageDialog(s.getAllUsers());
                dialog.setLocationRelativeTo( SUHPPanel);
                dialog.pack();
                dialog.setLocation(dialog.getX() -dialog.getWidth()/2 ,dialog.getY()-dialog.getHeight()/2);
                dialog.setVisible(true);
            }
        });
    }

    public JPanel getSUHPPanel() {
        return SUHPPanel;
    }

    public void listDocuments(ArrayList<Document> docArray){
        DefaultListModel docListModel = new DefaultListModel();
        for (Document d: docArray) {
            docListModel.addElement(d);
        }
        docList.setModel(docListModel);

    }

    public void setUser(String userName){
        UserNameLabel.setText("Username: " + userName);
    }


    public void setImage(URL image){
        ImageIcon  icon = new ImageIcon(image);
        ImageLabel.setIcon(icon);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SUHomePage");
        frame.setContentPane(new SUHomePage().SUHPPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
