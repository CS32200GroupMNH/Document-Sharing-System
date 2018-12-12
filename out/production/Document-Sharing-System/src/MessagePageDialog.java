import javax.swing.*;
import java.awt.event.*;

public class MessagePageDialog extends JDialog {
    private JPanel contentPane;
    private JTextArea ComplaintTextArea;
    private JLabel ComplaintLabel;
    private JButton cancelButton;
    private JButton OKButton;

    public MessagePageDialog(int complaintNumber, String content) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(OKButton);

        OKButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
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

        this.setContent(complaintNumber,content);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public void setContent(int complaintNumber, String content){
        ComplaintLabel.setText("complain " + complaintNumber );
        ComplaintTextArea.setText(content);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        MessagePageDialog dialog = new MessagePageDialog(1,"Hi\nthis\nDocument\nis\nnot opening please help");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}