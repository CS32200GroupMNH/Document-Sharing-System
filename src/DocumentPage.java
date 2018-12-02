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
                textArea1.setEditable(!textArea1.isEditable());
                ((AbstractDocument) textArea1.getDocument()).setDocumentFilter(new DSSMainFilter());
                if(!textArea1.isEditable()){
                    lockButton.setText("Lock");
                }
                else{
                    lockButton.setText("Unlock");
                }

            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDocument.updateDocument(textArea1.getText());
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
            super.insertString(fb, offset, text, attr);
        }

        @Override
        public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
                throws BadLocationException {
            super.remove(fb, offset, length);
        }

    }


}


