import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.DocumentFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;

public class DocumentPage {

    private Document currentDocument;

    private JPanel DocumentPanel;
    private JToolBar DocumentToolBar;
    private JButton saveButton;
    private JButton versionsButton;
    private JButton sharingButton;
    private JButton complaintsButton;
    private JTextArea textArea1;
    private JButton lockButton;
    private JLabel lockLabel;
    private JButton homeButton;

    public DocumentPage() {
        lockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ((AbstractDocument) textArea1.getDocument()).setDocumentFilter(new DSSMainFilter());

                if(currentDocument.changeDocumentLock(!textArea1.isEditable())){

                    textArea1.setEditable(!textArea1.isEditable());
                }


                if(textArea1.isEditable()){
                    lockButton.setText("Unlock");

                }
                else{
                    lockButton.setText("Lock");
                }

                setLockLabelText(currentDocument.getLockedBy());

            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean foundTabooWord = currentDocument.checkDocumentForTabooWords(textArea1.getText());

                if(foundTabooWord){
                    textArea1.setText(currentDocument.getDocumentContent());
                    JOptionPane.showMessageDialog(DocumentPanel,"There are taboo words in the document. Please Fix it.","Alert",JOptionPane.WARNING_MESSAGE);
                }
                else{
                    boolean saved = currentDocument.updateDocument(textArea1.getText());

                    if(saved){
                        JOptionPane.showMessageDialog(DocumentPanel,"Document Saved");
                    }
                    else{
                        JOptionPane.showMessageDialog(DocumentPanel,"Document is not saved.","Alert",JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        });
        versionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentVersionDialog dialog = new DocumentVersionDialog();

                dialog.setLocation(250,250);
                dialog.setLocationRelativeTo( textArea1);
                dialog.pack();

                dialog.setLocation(dialog.getX() -dialog.getWidth()/2 ,dialog.getY()-dialog.getHeight()/2);

                dialog.setVisible(true);
            }
        });
        sharingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // SystemManager s = SystemManager.getInstance();
                if (true){
                    DocumentSharingDialog dialog = new DocumentSharingDialog(currentDocument);
                    dialog.setLocationRelativeTo( textArea1);
                    dialog.pack();
                    dialog.setLocation(dialog.getX() -dialog.getWidth()/2 ,dialog.getY()-dialog.getHeight()/2);
                    dialog.setVisible(true);

                }
                else{
                    JOptionPane.showMessageDialog(DocumentPanel,"You are not the Owner.");
                }
            }
        });
        complaintsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentComplaintsDialog dialog = new DocumentComplaintsDialog();
                dialog.setLocationRelativeTo( textArea1);
                dialog.pack();
                dialog.setLocation(dialog.getX() -dialog.getWidth()/2 ,dialog.getY()-dialog.getHeight()/2);
                dialog.setVisible(true);
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean foundTabooWord = currentDocument.checkDocumentForTabooWords(textArea1.getText());
                SystemManager s = SystemManager.getInstance();
                if(foundTabooWord){
                    textArea1.setText(currentDocument.getDocumentContent());
                    JOptionPane.showMessageDialog(DocumentPanel,"There are taboo words in the document. Please Fix it.","Alert",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                    int a=JOptionPane.showConfirmDialog(DocumentPanel,"Unsaved changes will be discarded. Are you sure you want to go home?");
                    if(a==JOptionPane.YES_OPTION){
                        s.goHome();
                    }
            }
        });
    }

    public JPanel getDocumentPanel() {
        return DocumentPanel;
    }

    public void setDocumentData(Document d){
        SystemManager s = SystemManager.getInstance();
        currentDocument = d;


         if(this.currentDocument.getDocumentOwner().equals(s.getUserName()) || s.getUserType().equals("SU")){
            this.setReadWrite();
        }
        else if (s.getUserType().equals("GU") || this.currentDocument.getDocumentType().equals("Public")){
            this.setReadOnly();
        }
        else{
             if(d.isLocked()){
                 this.setReadOnly();
             }else {
                 this.setReadWrite();
                 sharingButton.setVisible(false);
             }
        }


        this.setLockLabelText(d.getLockedBy());
        textArea1.setText(currentDocument.getDocumentContent());
    }

    private void setReadOnly(){
        saveButton.setVisible(false);
        lockButton.setVisible(false);
        sharingButton.setVisible(false);

    }

    private void setReadWrite(){
        saveButton.setVisible(true);
        lockButton.setVisible(true);
        sharingButton.setVisible(true);

    }

    private void setLockLabelText(String name){

        if(name == null){
            this.lockLabel.setText("Unlocked");
        }
        else{
            this.lockLabel.setText("Locked By: " + name);
        }


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DocumentPage");
        frame.setContentPane(new DocumentPage().DocumentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    class DSSMainFilter extends DocumentFilter {
        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
                            String text, AttributeSet attrs) throws BadLocationException {

            System.out.println(text);
            if (text.equals(" ")) {
                super.replace(fb, offset , length, "\n", attrs);
                return;
            } else {
                super.replace(fb, offset,length, text, attrs);
            }
        }

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset,
                                 String text, AttributeSet attr) throws BadLocationException {
            System.out.println(text);
            super.insertString(fb, offset, text, attr);
        }

        @Override
        public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
                throws BadLocationException {
            System.out.println(offset);
            super.remove(fb, offset, length);
        }

    }


}


