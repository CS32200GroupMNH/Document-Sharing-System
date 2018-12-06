public class SuperUser {
    //member variables
    private String userName;
    private String password;

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

