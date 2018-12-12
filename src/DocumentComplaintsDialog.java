import javax.swing.*;
import java.awt.event.*;

public class DocumentComplaintsDialog extends JDialog {
    private JPanel contentPane;
    private JButton openComplaint;
    private JButton buttonCancel;
    private JTable complaintTable;
    private JButton sendComplaintButton;
    private Document currentDocument;
    public DocumentComplaintsDialog(Document d) {
        currentDocument = d;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(openComplaint);

        openComplaint.addActionListener(new ActionListener() {
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
        sendComplaintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileComplaintDialog dialog = new FileComplaintDialog(currentDocument);
                dialog.setLocationRelativeTo( contentPane);
                dialog.pack();
                dialog.setLocation(dialog.getX() -dialog.getWidth()/2 ,dialog.getY()-dialog.getHeight()/2);
                dialog.setVisible(true);
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {

        System.exit(0);
    }
}
