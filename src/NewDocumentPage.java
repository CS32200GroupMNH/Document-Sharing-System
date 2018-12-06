import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewDocumentPage {
    private JTextField docNameField;
    private JComboBox comboBox1;
    private JPanel NewDocumentPanel;
    private JButton createDocumentButton;
    private JButton homeButton;
    private JLabel statusLabel;

    public NewDocumentPage() {
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                s.changePage("HomePage");
            }
        });
        createDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemManager s = SystemManager.getInstance();
                //(String)cb.getSelectedItem()
                if(s.createNewDocument(docNameField.getText(),(String)comboBox1.getSelectedItem())){
                    s.changePage("DocumentPage");
                }

            }
        });
    }

    public JPanel getNewDocumentPanel() {
        return NewDocumentPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("NewDocumentPage");
        frame.setContentPane(new NewDocumentPage().NewDocumentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);




    }
}
