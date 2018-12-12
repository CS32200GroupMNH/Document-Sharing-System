import java.util.ArrayList;

public class Message{

    private String userName;

    private String messagetype;

    private String Subject;

    private String message;

    private String documentID;

    private String documentOwner;

    public Message(String userName, String messageType, String Subject, String message, String id, String owner){
        this.userName=userName;

        this.messagetype=messageType;

        this.Subject=Subject;

        this.message=message;

        this.documentID=id;

        this.documentOwner=owner;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentID() {
        return documentID;
    }

    public String getDocumentOwner() {
        return documentOwner;
    }

    public boolean inviteToDocument(String userNameInvite){
        SystemManager s  = SystemManager.getInstance();

        if(!userNameInvite.equals("") && !(this.documentOwner.equals(userNameInvite))){
            return s.inviteUserToDocument(this.documentID,userNameInvite);
        }
        else{
            return true;
        }

    }
    public boolean sendMessage(String name, String messageType, String Subject, String message)
    {
        SystemManager s = SystemManager.getInstance();
        if(!name.equals("")&&!(this.documentOwner.equals(name))){
            return s.sendMessage(name, messageType, Subject, message);
        }
        else{
            return true;
        }


    }
    public ArrayList<String> getallMessages(){
        SystemManager s = SystemManager.getInstance();
        ArrayList<String> allmessages=s.getallMessages(messagetype);

        return allmessages;
    }
}
