

import javax.print.Doc;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class OUHomePage {


    private JPanel OUPanel;
    private JPanel SUHPPanel;
    private JLabel UserNameLabel;
    private JButton messagesButton1;
    private JButton newDocumentButton1;
    private JButton searchDocumentsButton1;
    private JButton searchUsersButton1;
    private JList docList;
    private JButton openDocumentButton;
    private JLabel ImageLabel;
    private JButton logOutButton;
    private JButton tabooWordsButton;


    public OUHomePage() {
        newDocumentButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                s.changePage("NewDocumentPage");
            }
        });
        
/*
        searchDocumentsButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                s.changePage("SearchDocumentPage");
            }
        });
        searchUsersButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                s.changePage("SearchUsersPage");
            }
        });
*/
        
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
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                s.logOut();
            }
        });
        searchDocumentsButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                s.changePage("SDPage");
            }
        });
        searchUsersButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                s.changePage("SUPage");
            }
        });
        tabooWordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=JOptionPane.showInputDialog(OUPanel,"Suggest a word");

                if(name != null){
                    SystemManager s = SystemManager.getInstance();
                    s.sendMessage("admin","TWSuggestion","GLOBAL",name);
                }
            }
        });
    }

    public JPanel getOUPanel() {
        return OUPanel;
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
        JFrame frame = new JFrame("OUHomePage");
        OUHomePage o = new OUHomePage();
        frame.setContentPane(o.OUPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        try {
            URL myURL = new URL("https://news.nationalgeographic.com/content/dam/news/2018/05/17/you-can-train-your-cat/02-cat-training-NationalGeographic_1484324.ngsversion.1526587209178.adapt.1900.1.jpg");
            o.setImage(myURL);
        }
        catch (Exception e){System.out.println(e);}



    }
}
