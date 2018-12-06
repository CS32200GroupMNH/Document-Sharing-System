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

            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDocument.updateDocument(textArea1.getText());
            }
        });
        versionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentVersionDialog dialog = new DocumentVersionDialog();

                dialog.setLocation(250,250);
                dialog.setLocationRelativeTo( textArea1);
                dialog.pack();

                dialog.setLocation(dialog.getX() -dialog.getWidth()/2 ,dialog.getY());

                dialog.setVisible(true);
            }
        });
    }

    public JPanel getDocumentPanel() {
        return DocumentPanel;
    }

    public void setDocumentData(Document d){
        currentDocument = d;
        textArea1.setText(currentDocument.getDocumentContent());
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


