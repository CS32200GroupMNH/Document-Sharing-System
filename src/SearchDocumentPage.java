import javax.swing.*;

public class SearchDocumentPage {
    private JPanel SearchDocumentPanel;
    private JTextField textField1;
    private JButton searchButton;
    private JList list1;
    private JButton openDocumentButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("SearchDocumentPage");
        frame.setContentPane(new SearchDocumentPage().SearchDocumentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
