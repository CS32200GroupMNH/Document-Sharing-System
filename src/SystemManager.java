import javax.swing.*;
import java.sql.Driver;
import java.util.Arrays;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.awt.CardLayout;

public class SystemManager {
    private static SystemManager ourInstance = new SystemManager();
    public static SystemManager getInstance() {
        return ourInstance;
    }
    private JPanel cards;
    private String userType;
    private String userName;
    private Connection dataBaseConnection;

    private SystemManager() {

        try{
            dataBaseConnection = getConnection();
        }catch (Exception e){System.out.println(e);}

        cards = new JPanel(new CardLayout());
        JPanel logInPanel = new LoginPage().getMainPanel();
        JPanel homePanel =  new OUHomePage().getOUPanel();
        JPanel registrationPanel = new RegistrationPage().getRegestrationPanel();
        JPanel guestUserPanel = new GuestUserHomePage().getGUPanel();

        cards.add(logInPanel,"LoginPage");
        cards.add(homePanel,"HomePage");
        cards.add(registrationPanel,"RegistrationPage");
        cards.add(guestUserPanel,"GuestUserPage");

        JFrame frame = new JFrame("LoginPage");
        frame.setContentPane(cards);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    private static Connection getConnection() throws Exception{ //Creates the connection with the sql server and returns it
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/DSSDatabase";
            String username = "root";
            String password = "password";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connected");
            return  conn;
        } catch(Exception e){System.out.println(e);}

        return null;

    }

    public void changePage(String pageName){
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards,pageName);

    }

    public boolean logIn(String userName, char[] password){ ///Queries the Database and checks if the password is correct for the user in the database. Returns true if correct false otherwise
        System.out.println(userName);
        try {
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("SELECT password FROM users WHERE userName = '" + userName + "';");
            ResultSet result = statement1.executeQuery();

            while (result.next()) {
                String pass = result.getString("password");
                if(Arrays.equals(result.getString("password").toCharArray(),password)) {
                    userName = userName;
                    return true;
                }
            }
        }catch (Exception e){System.out.println(e);}

        return false;
    }

    public boolean registerUser(String userName, char[] password, String userType){
        try {
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("INSERT INTO users VALUES ('"+ userName +"', '"+ String.valueOf(password) +"', '" + userType + "');");
            statement1.executeUpdate();
            return true;
          }catch (Exception e){System.out.println(e);}


        return false;
    }

    public boolean applyForMembership(String userName, char[] password, String name, String interests) {
        try{
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("INSERT INTO userapplications VALUES ('"+ userName +"', '"+ String.valueOf(password)+"', '"+name+"', '"+interests+"');");
            statement1.executeUpdate();
            return true;
        }catch (Exception e){System.out.println(e);}

        return false;
    }
}
