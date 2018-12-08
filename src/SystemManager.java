import javax.swing.*;
import java.sql.Driver;
import java.util.Arrays;
import java.util.UUID;
import java.util.*;
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

    public String getUserName() {
        return userName;
    }

    private String userName;
    private Connection dataBaseConnection;

    private ArrayList<Document> documentList;

    private LoginPage logInPanel = new LoginPage();
    private OUHomePage homePanel =  new OUHomePage();
    private RegistrationPage registrationPanel = new RegistrationPage();
    private GuestUserHomePage guestUserPanel = new GuestUserHomePage();
    private NewDocumentPage newDocumentPanel = new NewDocumentPage();
    private DocumentPage documentPanel = new DocumentPage();

    private SystemManager() {

        try{
            dataBaseConnection = getConnection();
        }catch (Exception e){System.out.println(e);}

        cards = new JPanel(new CardLayout());


        cards.add(logInPanel.getMainPanel(),"LoginPage");
        cards.add(homePanel.getOUPanel(),"HomePage");
        cards.add(registrationPanel.getRegestrationPanel(),"RegistrationPage");
        cards.add(guestUserPanel.getGUPanel(),"GuestUserPage");
        cards.add(newDocumentPanel.getNewDocumentPanel(),"NewDocumentPage");
        cards.add(documentPanel.getDocumentPanel(),"DocumentPage");

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
                    this.userName = userName;
                    homePanel.listDocuments(getAllDocuments());
                    return true;
                }
            }
        }catch (Exception e){System.out.println(e);}

        return false;
    }

    public void logInAsGuest(){
        this.userType = "GU";
    }

    public boolean applyForMembership(String userName, char[] password, String name, String interests) {
        try{
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("INSERT INTO userapplications VALUES ('"+ userName +"', '"+ String.valueOf(password)+"', '"+name+"', '"+interests+"');");
            statement1.executeUpdate();
            return true;
        }catch (Exception e){System.out.println(e);}

        return false;
    }

    public boolean createNewDocument(String docName, String docType) {
        try{
            String uniqueID = UUID.randomUUID().toString();
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("INSERT INTO Documents VALUES ('"+ uniqueID +"', '"+ docName+"', '"+this.userName+"', '"+docType+"',NULL,'');");
            statement1.executeUpdate();
             documentPanel.setDocumentData(new Document(uniqueID,docName,this.userName,docType,null,""));
            return true;
        }catch (Exception e){System.out.println(e);}

        return false;
    }

    public boolean saveDocument(Document d){
        try{
            String uniqueID = UUID.randomUUID().toString();
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("UPDATE Documents SET contents = '" + d.getDocumentContent() + "' WHERE documentID = '" + d.getDocumentID() + "';");
            statement1.executeUpdate();
            return true;
        }catch (Exception e){System.out.println(e);}


        return false;
    }

    public boolean saveHistoryCommands(){
        return false;
    }


    public ArrayList<Document> getAllDocuments(){
        ArrayList<Document> docArray = new ArrayList<Document>(3);

        try {
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("SELECT * FROM documents WHERE owner = '"+ this.userName +"';");



            ResultSet result = statement1.executeQuery();

            while (result.next()) {
                System.out.println(result.getString("lockedBy"));
                docArray.add(new Document(result.getString("documentID"),result.getString("documentName"),result.getString("owner"),result.getString("documentType"),result.getString("lockedBy"),result.getString("contents")));
            }
        }catch (Exception e){System.out.println(e + "147");}
        return docArray;

    }

    public boolean openDocumentByID(String documentID){
        try {
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("SELECT * FROM documents WHERE documentID = '"+ documentID +"';");
            ResultSet result = statement1.executeQuery();

            while (result.next()) {
                documentPanel.setDocumentData( new Document(result.getString("documentID"),result.getString("documentName"),result.getString("owner"),result.getString("documentType"),result.getString("lockedBy"),result.getString("contents")));
                changePage("DocumentPage");
                return true;
            }
        }catch (Exception e){System.out.println(e);}


        return false;
    }

    public void openDocumentFromObject(Document d){
        documentPanel.setDocumentData(d);
        changePage("DocumentPage");
    }

    public boolean lockDocument(String documentID){
        try{
            String uniqueID = UUID.randomUUID().toString();
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("UPDATE Documents SET lockedBy = '" + this.userName + "' WHERE documentID = '" + documentID + "';");
            statement1.executeUpdate();
            return true;
        }catch (Exception e){System.out.println(e);}

        return false;
    }

    public boolean unLockDocument(String documentID){
        try{
            String uniqueID = UUID.randomUUID().toString();
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("UPDATE Documents SET lockedBy = NULL WHERE documentID = '" + documentID + "';");
            statement1.executeUpdate();
            return true;
        }catch (Exception e){System.out.println(e);}

        return false;
    }

    public ArrayList<DocumentCommands> getDocumentVersions(String documentID){
        ArrayList<DocumentCommands> docArray = new ArrayList<DocumentCommands>(3);

        try {
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("SELECT * FROM DocumentUpdates WHERE documentID = '"+ documentID +"';");
            ResultSet result = statement1.executeQuery();

            while (result.next()) {
                System.out.println(result.getString("lockedBy"));
                docArray.add(new DocumentCommands(result.getInt("versionNumber"),documentID,result.getString("updatedBY"),"December 5 2018",result.getString("commands")));
            }
        }catch (Exception e){System.out.println(e + "147");}
        return docArray;

    }

    public HashSet<String> getTabooWords(String documentID){
        HashSet<String> wordSet = new HashSet<String>();

        try {
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("SELECT * FROM TabooWords WHERE location = '"+ documentID +" OR location = 'GLOBAL';");
            ResultSet result = statement1.executeQuery();

            while (result.next()) {
                wordSet.add(result.getString("word"));
            }
        }catch (Exception e){System.out.println(e + "222");}

        return wordSet;
    }

    public boolean setDocumentType(String documentID,String documentType){
        try{
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("UPDATE Documents SET documentType = '" + documentType + "' WHERE documentID = '" + documentID + "';");
            statement1.executeUpdate();
            return true;
        }catch (Exception e){System.out.println(e);}
        return false;
    }

    public boolean inviteUserToDocument(String documentID, String userName){
        //SENDS USER THE INVITATION TO JOIN THE DOCUMENT
        return false;
    }





}
