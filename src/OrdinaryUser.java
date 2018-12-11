import java.lang.String;
import java.util.HashSet;

public class OrdinaryUser {
    //member variables
    private String userName;
    private String fullName;
    private HashSet<String> technicalInterests = new HashSet<String>(); //Used a HashSet so we can enter multiple technical interests as keys and search them
    private String password;

    //constructor
    public OrdinaryUser(String userName, String fullName, HashSet<String> technicalInterests) {
        setUserName(userName);
        setFullName(fullName);
        setTechnicalInterests(technicalInterests); //iterate through HashSet and add each string to the HashSet

    }

    //member functions
    //Takes in a string and adds it to the HashSet technicalInterests
    public boolean addToTechnicalInterests(String technicalInterest)
    {
        technicalInterests.add(technicalInterest);
        return true;
    }

    //getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public HashSet getTechnicalInterests() {
        return technicalInterests;
    }

    public void setTechnicalInterests(HashSet<String> technicalInterests) { //Adds a string to the HashSet
        this.technicalInterests = technicalInterests;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
