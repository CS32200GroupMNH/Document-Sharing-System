import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class DocumentVersionDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JScrollPane gridScrollPane;
    private JTable versionTable;

    private Document currentDocument;

    private ArrayList<DocumentCommands> docCommandList;

    public DocumentVersionDialog(Document d) {
        this.currentDocument = d;
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

        String[] columns = new String[] {
                "Version Number", "Saved By", "Date"
        };

        //actual data for the table in a 2d array



        Object[][] data = new Object[4][3];

        for(int i = 0; i < 4; i++){
          //  DocumentCommands d = docCommandArray.get(i);
            data[i][0] = "1";
            data[i][1] = "name1";
            data[i][2] = "December 5 2018";
        }
        versionTable.setModel(new DefaultTableModel(data, columns));
    }

    public void setContent(ArrayList<DocumentCommands> docCommandArray){
        System.out.println(docCommandArray.size());
        String[] columns = new String[] {
                "Version Number", "Saved By", "Date"
        };

        this.docCommandList = docCommandArray;
        Object[][] data = new Object[docCommandArray.size()][3];

        for(int i = 0; i < docCommandArray.size(); i++){
            DocumentCommands d = docCommandArray.get(i);
            data[i][0] = d.getVersion();
            data[i][1] = d.getUpdatedBy();
            data[i][2] = d.getUpdateDate();
        }

        versionTable.setModel(new DefaultTableModel(data, columns));
    }

    private String getDocumentContent(int versionNum){
        int amountOfVersions = docCommandList.size();
        String currentDocContents = currentDocument.getDocumentContent();
        if(amountOfVersions == versionNum){
            return DocumentCommands.createOldFile(docCommandList.get(versionNum - 1).getDocCommands(),currentDocContents);
        }
        else{

            for(int i = amountOfVersions; i >= versionNum; i-- ){
                currentDocContents =  DocumentCommands.createOldFile(docCommandList.get(i - 1).getDocCommands(),currentDocContents);
            }

            return  currentDocContents;
        }
    }

    private void openOldDocFromVersion(int versionNum){
        String oldDocumentContents = "";
        OldDocumentDialog dialog = new OldDocumentDialog(versionNum,getDocumentContent(versionNum));
        dialog.pack();
        dialog.setLocationRelativeTo( contentPane);

        dialog.setVisible(true);
    }

    private void onOK() {
        if(versionTable.getSelectedRow() >= 0) {
            openOldDocFromVersion((Integer) versionTable.getValueAt(versionTable.getSelectedRow(), 0));
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {

        System.exit(0);
    }
}
