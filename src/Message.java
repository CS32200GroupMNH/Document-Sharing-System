import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
public class Message implements ActionListener{

    private String currentUser;

    private JButton btn1, btn2, btn3, btn4;
    private JTextField field;
    private JLabel label;

    private JFrame frame,frame2;

    private SuperUser superUser;
    private OrdinaryUser ordinary;
    private GuestUser guest;

    public Message(String currentUser){
        this.currentUser = currentUser;
        superUser = new SuperUser();
        ordinary = new OrdinaryUser();
        guest = new GuestUser();
        btn1 = new JButton("Complaint");
        btn1.setBounds(50, 150, 100, 40);
        btn2 = new JButton("Ask Permission");
        btn2.setBounds(160, 150, 100, 40);
        btn3 = new JButton("Other Message");
        btn3.setBounds(270, 150, 100, 40);
        btn4 = new JButton("Show All Messages");
        btn4.setBounds(100, 250, 200, 40);
        field = new JTextField();
        field.setBounds(150, 70, 100, 40);
        label = new JLabel("You are logged as +++"+currentUser);
        label.setBounds(10, 10, 300, 40);

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);

        createGUI();
        checkUser();
    }

    public void createGUI(){
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(label);
        frame.add(field);
        frame.add(btn1);
        frame.add(btn2);
        frame.add(btn3);
        frame.add(btn4);
    }

    public void checkUser(){
        if(currentUser.equals("Super User")){
            btn1.setEnabled(false);
            btn2.setEnabled(false);
        }else if(currentUser.equals("Ordinary User")){
            btn2.setEnabled(false);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(currentUser.equals("Super User")){
            if(e.getActionCommand().equals("Complaint")){
                superUser.sendOtherMessage(field.getText());
            }
        }else if(currentUser.equals("Ordinary User")){
            if(e.getActionCommand().equals("Complaint")){
                ordinary.sendComplaint(field.getText());
            }else if(e.getActionCommand().equals("Other Message")){
                ordinary.sendOtherMessage(field.getText());
            }
        }else if(currentUser.equals("Guest User")){
            if(e.getActionCommand().equals("Complaint")){
                guest.sendComplaint(field.getText());
            }else if(e.getActionCommand().equals("Ask Permission")){
                guest.askPermission(field.getText());
            }else if(e.getActionCommand().equals("Other Message")){
                guest.sendOtherMessage(field.getText());
            }
        }
        field.setText("");
        if(e.getActionCommand().equals("Show All Messages")){
            frame.setVisible(false);
            frame2 = new JFrame();
            frame2.setSize(400, 400);
            frame2.setVisible(true);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setLayout(new GridLayout(0,1));

            frame2.add(new JLabel("Super User Messages:"));
            for(String msgs:superUser.getMessages()){
                frame2.add(new JLabel(msgs));
            }

            frame2.add(new JLabel("Ordinary User Messages:"));
            for(String msgs:ordinary.getMessages()){
                frame2.add(new JLabel(msgs));
            }

            frame2.add(new JLabel("Guest User Messages:"));
            for(String msgs:guest.getMessages()){
                frame2.add(new JLabel(msgs));
            }
        }
    }

    public static void main(String[]args){
        Message message1 = new Message("Guest User");
    }
}