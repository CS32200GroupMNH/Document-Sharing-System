import java.util.ArrayList;
import java.util.List;

public class SuperUser {
    //member variables
    private String userName;
    private String password;

    private List<String> messages;

    public SuperUser(){
        messages=new ArrayList<String>();
    }

    public void sendComplaint(String message){
        messages.add(message);
    }

    public void sendOtherMessage(String message){
        messages.add(message);
    }
    public List<String> getMessages(){
        return messages;
    }

    //constructor
    public SuperUser(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }

    //getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

