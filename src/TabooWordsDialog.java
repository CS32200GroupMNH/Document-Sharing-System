import javax.swing.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;


public class TabooWordsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList tabooJList;
    private JTextField wordField;
    private JButton addWordButton;
    private JButton removeWordButton;

    private ArrayList<String> tabooWordList;

    public TabooWordsDialog(Collection<String> contents) {
        tabooWordList = new ArrayList<String>();
        tabooWordList.addAll(contents);
        tabooWordList.remove("UNK");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.setVisible(false);
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

        this.updateWordList(this.tabooWordList);
        addWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!wordField.getText().equals("")){
                    SystemManager s = SystemManager.getInstance();
                    if(s.addTabooWord(wordField.getText())){
                        tabooWordList.add(0,wordField.getText().toLowerCase());
                        updateWordList(tabooWordList);
                        wordField.setText("");
                    }
                }
            }
        });
        removeWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabooJList.getSelectedIndex() != -1){
                    SystemManager s = SystemManager.getInstance();
                    if(s.removeTabooWord((String)tabooJList.getSelectedValue())){
                        tabooWordList.remove(tabooJList.getSelectedIndex());
                        updateWordList(tabooWordList);
                    }
                }
            }
        });
    }

    public void updateWordList(ArrayList<String> wordList){
        DefaultListModel docListModel = new DefaultListModel();
        for (String s: wordList) {
            docListModel.addElement(s);
        }
        tabooJList.setModel(docListModel);

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

        ArrayList<String> arr = new ArrayList<String>();
        arr.add("pasta");
        arr.add("pasdta");
        arr.add("pdasdta");
        arr.add("pasda");
        arr.add("pasta");
        arr.add("pasdta");
        arr.add("pdasdta");
        arr.add("pasda");
        arr.add("pasta");
        arr.add("pasdta");
        arr.add("pdasdta");
        arr.add("pasda");
        arr.add("pasta");
        arr.add("pasdta");
        arr.add("pdasdta");
        arr.add("pasda");arr.add("pasta");
        arr.add("pasdta");
        arr.add("pdasdta");
        arr.add("pasda");arr.add("pasta");
        arr.add("pasdta");
        arr.add("pdasdta");
        arr.add("pasda");arr.add("pasta");
        arr.add("pasdta");
        arr.add("pdasdta");
        arr.add("pasda");







        TabooWordsDialog dialog = new TabooWordsDialog(arr);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
