import javax.print.Doc;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    public OUHomePage() {
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("OUHomePage");
        OUHomePage o = new OUHomePage();
        frame.setContentPane(o.OUPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
       // final DefaultListModel fruitsName = new DefaultListModel();

       // fruitsName.addElement("Apple");
       // fruitsName.addElement("Grapes");
       // fruitsName.addElement("Mango");
      //  fruitsName.addElement("Peer");

    }
}
