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
                SystemManager s = SystemManager.getInstance();
                s.openDocumentFromObject((Document) docList.getSelectedValue());
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
