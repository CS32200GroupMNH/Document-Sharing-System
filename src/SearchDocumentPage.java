import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchDocumentPage {


    private JPanel SearchDocumentPanel;
    private JTextField textField1;
    private JButton searchButton;
    private JList docList;
    private JButton openDocumentButton;
    private JButton homeButton;

    public JPanel getSearchDocumentPanel() {
        return SearchDocumentPanel;
    }

    public SearchDocumentPage() {
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
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                ArrayList<Document> emptyAr = new ArrayList<Document>();
                listDocuments(emptyAr);
                textField1.setText("");
                s.goHome();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                listDocuments(s.searchDocument(textField1.getText(),""));
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("SearchDocumentPage");
        frame.setContentPane(new SearchDocumentPage().SearchDocumentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
