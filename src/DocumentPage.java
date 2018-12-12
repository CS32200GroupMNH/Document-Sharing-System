import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;


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
        ((AbstractDocument) textArea1.getDocument()).setDocumentFilter(new DSSMainFilter());
        textArea1.setTransferHandler(null);
        textArea1.setTabSize(0);
        lockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



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
                String newDocWithOutTWords = currentDocument.checkDocumentForTabooWords(textArea1.getText());

                if(newDocWithOutTWords != null){

                    if(currentDocument.isFlagged()){
                        JOptionPane.showMessageDialog(DocumentPanel,"There are taboo words in the document. Fix it now.","Alert",JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    textArea1.setText(newDocWithOutTWords);
                    int a = JOptionPane.showOptionDialog(DocumentPanel,
                            "Taboo word found. Replaced with 'UNK'",
                            "Word Alerts",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            null,
                            new String[]{"Fix Now", "Save and fix next time you log on"}, // this is the array
                            "default");


                     if (a== JOptionPane.NO_OPTION){
                         boolean saved = currentDocument.updateDocument(textArea1.getText());
                         if(saved){
                             JOptionPane.showMessageDialog(DocumentPanel,"Document Saved. Next Login will ");
                             homeButton.setVisible(true);
                             currentDocument.flagThisDocument();
                         }
                         else{
                             JOptionPane.showMessageDialog(DocumentPanel,"Document is not saved.","Alert",JOptionPane.WARNING_MESSAGE);
                         }
                    }

                }
                else{
                    boolean saved = currentDocument.updateDocument(textArea1.getText());

                    if(saved){
                        JOptionPane.showMessageDialog(DocumentPanel,"Document Saved");
                        homeButton.setVisible(true);
                        currentDocument.unFlagThisDocument();

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
                SystemManager s = SystemManager.getInstance();
                DocumentVersionDialog dialog = new DocumentVersionDialog(currentDocument);
                dialog.setContent(s.getOldDocuments(currentDocument.getDocumentID()));
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
                SystemManager s = SystemManager.getInstance();

                if(currentDocument.isFlagged()){
                    currentDocument.setDocFlag(false);
                  if(s.openFlaggedDocument()) {
                      return;
                  }
                }

                    int a=JOptionPane.showConfirmDialog(DocumentPanel,"Unsaved changes will be discarded. Are you sure you want to go home?");
                    if(a==JOptionPane.YES_OPTION){
                        if(currentDocument.isLocked()){
                            currentDocument.changeDocumentLock(!textArea1.isEditable());
                            textArea1.setEditable(false);
                        }
                        s.goHome();
                    }
            }
        });
        textArea1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                
                if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (currentDocument.spellChecker()) {
                        Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);

                        try {
                            textArea1.getHighlighter().addHighlight(currentDocument.getStartIndex(), currentDocument.getEndIndex(), painter);
                        }catch(Exception error){System.out.println(error);}
                    }
                    }
                }
            }
        );
    }

    public JPanel getDocumentPanel() {
        return DocumentPanel;
    }

    public void setDocumentData(Document d){
        SystemManager s = SystemManager.getInstance();
        s.setTitle(d.getDocumentName() + " (" + d.getDocumentType() + ")");
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

        if(d.isLocked() && d.getLockedBy().equals(s.getUserName())) {
            textArea1.setEditable(true);
            lockButton.setText("Unlock");
        }
        else{
            textArea1.setEditable(false);
        }

        if(!this.currentDocument.getDocumentOwner().equals(s.getUserName())){
            sharingButton.setVisible(false);
        }

        this.setLockLabelText(d.getLockedBy());
        textArea1.setText(currentDocument.getDocumentContent());



        if(d.isFlagged()){

            homeButton.setVisible(false);
            JOptionPane.showMessageDialog(DocumentPanel,"This document contains Taboo words replaced with 'UNK'.\nPlease Fix","Alert",JOptionPane.WARNING_MESSAGE);
        }


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

           // System.out.println(textArea1.getLineOfOffset(offset));
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
           // System.out.println(textArea1.getLineOfOffset(offset));
            super.insertString(fb, offset, text, attr);
        }

        @Override
        public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
                throws BadLocationException {
           // System.out.println(offset);
           // System.out.println(textArea1.getLineEndOffset(textArea1.getLineOfOffset(offset)));
            if(textArea1.getLineEndOffset(textArea1.getLineOfOffset(offset)) - 2 == offset){
                super.remove(fb, offset, length+1);
            }
            else{
                super.remove(fb, offset, length);
            }


        }

    }


}


