import java.lang.String;

public class User {
    private String userName;
    private String userInterests;

    public User(String name, String interests){
        userName = name;
        userInterests = interests;

    }

    public String getUserName() { return userName; }
    public String getUserInterests() { return userInterests; }
}
