import java.util.ArrayList;
import java.util.List;
public class GuestUser {

    private List<String> messages;

    public GuestUser(){
        messages = new ArrayList<String>();
    }

    public void sendComplaint(String message){
        messages.add(message);
    }

    public void askPermission(String message){
        messages.add(message);
    }

    public void sendOtherMessage(String message){
        messages.add(message);
    }

    public List<String> getMessages(){
        return messages;
    }
}
