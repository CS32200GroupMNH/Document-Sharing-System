import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuestUserHomePage {


    private JPanel GUPanel;
    private JPanel SUHPPanel;
    private JButton applyToBecomeOrdinaryButton;
    private JList documentLIst;
    private JButton openDocumentButton;
    private JButton suggestTabooWordsButton;
    private JButton logOutButton;

    public GuestUserHomePage() {
        applyToBecomeOrdinaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                s.changePage("RegistrationPage");
            }
        });
        openDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(documentLIst.getSelectedIndex() > -1){
                    SystemManager s = SystemManager.getInstance();
                    if(documentLIst.getSelectedValue() != null) {
                        s.openDocumentFromObject((Document) documentLIst.getSelectedValue());
                    }
                }
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                s.logOut();
            }
        });
        suggestTabooWordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name=JOptionPane.showInputDialog(GUPanel,"Suggest a word");

                if(name != null){
                    SystemManager s = SystemManager.getInstance();
                    s.sendMessage("admin","TWSuggestion","GLOBAL",name);
                }
            }
        });
    }

    public JPanel getGUPanel() {
        return GUPanel;
    }

    public void listDocuments(ArrayList<Document> docArray){
        DefaultListModel docListModel = new DefaultListModel();
        for (Document d: docArray) {
            docListModel.addElement(d);
        }
        documentLIst.setModel(docListModel);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GuestUserHomePage");
        frame.setContentPane(new GuestUserHomePage().GUPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
