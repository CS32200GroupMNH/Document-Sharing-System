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
import java.io.File;

public class SystemManager {
    private static SystemManager ourInstance = new SystemManager();
    public static SystemManager getInstance() {
        return ourInstance;
    }
    private JPanel cards;

    public String getUserType() {
        return userType;
    }

    private String userType = "NA";

    public String getUserName() {
        return userName;
    }

    private String userName;
    private Connection dataBaseConnection;

    private ArrayList<Document> documentList;

    private LoginPage logInPanel = new LoginPage();
    private OUHomePage ordinaryUserPanel =  new OUHomePage();
    private SUHomePage superUserPanel = new SUHomePage();
    private RegistrationPage registrationPanel = new RegistrationPage();
    private GuestUserHomePage guestUserPanel = new GuestUserHomePage();
    private NewDocumentPage newDocumentPanel = new NewDocumentPage();
    private DocumentPage documentPanel = new DocumentPage();

    private static HashSet<String> dictionary; //to store words from words.txt

    private SystemManager() {

        try{
            dataBaseConnection = getConnection();
        }catch (Exception e){System.out.println(e);}

        cards = new JPanel(new CardLayout());


        cards.add(logInPanel.getMainPanel(),"LoginPage");
        cards.add(ordinaryUserPanel.getOUPanel(),"OUHomePage");
        cards.add(registrationPanel.getRegestrationPanel(),"RegistrationPage");
        cards.add(guestUserPanel.getGUPanel(),"GuestUserPage");
        cards.add(newDocumentPanel.getNewDocumentPanel(),"NewDocumentPage");
        cards.add(documentPanel.getDocumentPanel(),"DocumentPage");
        cards.add(superUserPanel.getSUHPPanel(),"SUHomePage");

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
            String password = "Boost346";
            Class.forName(driver);
            initializeDictionary();

            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connected");

            return  conn;
        } catch(Exception e){System.out.println(e);}

        return null;

    }

    //fills the HashSet dictionary with words
    public static void initializeDictionary(){
        try {
            if (dictionary == null) {
                Scanner file = new Scanner(new File("/../Assets/words.txt"));
                HashSet<String> tempDictionary = new HashSet<String>();

                while (file.hasNext()) {
                    String line = file.nextLine();
                    tempDictionary.add(line.toLowerCase());
                }

                dictionary = tempDictionary;
            }
        } catch(Exception e) {System.out.println(e);}
    }

    public void changePage(String pageName){
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards,pageName);
    }

    public boolean logIn(String userName, char[] password){ ///Queries the Database and checks if the password is correct for the user in the database. Returns true if correct false otherwise
        System.out.println(userName);
        try {
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("SELECT * FROM users WHERE userName = '" + userName + "';");
            ResultSet result = statement1.executeQuery();

            while (result.next()) {
                String pass = result.getString("password");
                this.userType = result.getString("userType");
                if(Arrays.equals(result.getString("password").toCharArray(),password)) {
                    this.userName = userName;




                    if(this.userType.equals("OU")){
                        this.changePage("OUHomePage");
                        ordinaryUserPanel.setUser(this.userName);
                        ordinaryUserPanel.listDocuments(getAllDocuments());
                    }
                    else if(this.userType.equals("SU")){
                        this.changePage("SUHomePage");
                        superUserPanel.setUser(this.userName);
                        superUserPanel.listDocuments(getAllDocuments());
                    }




                    return true;
                }
            }
        }catch (Exception e){System.out.println(e);}

        return false;
    }

    public void logInAsGuest(){
        this.userType = "GU";
        guestUserPanel.listDocuments(this.getAllDocuments());
        this.changePage("GuestUserPage");
    }

    public void goHome(){
        if(this.userType.equals("GU")){
            this.changePage("GuestUserPage");
        }
        else if (this.userType.equals("OU")){
            ordinaryUserPanel.listDocuments(getAllDocuments());
            this.changePage("OUHomePage");
        }
        else if(this.userType.equals("SU")){
            superUserPanel.listDocuments(getAllDocuments());
            this.changePage("SUHomePage");
        }

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
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("INSERT INTO Documents VALUES ('"+ uniqueID +"', '"+ docName+"', '"+this.userName+"', '"+docType+"',NULL,'',0);");
            statement1.executeUpdate();
             documentPanel.setDocumentData(new Document(uniqueID,docName,this.userName,docType,null,"",0));
            return true;
        }catch (Exception e){System.out.println(e);}

        return false;
    }

    public boolean saveDocument(Document d,String docContent){
        try{
            String uniqueID = UUID.randomUUID().toString();
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("UPDATE Documents SET contents = '" + docContent + "', versionCount = "+ d.getCurrentDocVersion() + " WHERE documentID = '" + d.getDocumentID() + "';");
            statement1.executeUpdate();
            return true;
        }catch (Exception e){System.out.println(e);}


        return false;
    }

    public boolean saveOldDocuments(DocumentCommands d){
        try{
            String uniqueID = UUID.randomUUID().toString();
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("INSERT INTO OldDocuments VALUES ("+d.getVersion()+",'"+d.getDocumentID()+"','"+d.getDocCommands()+"','"+d.getUpdatedBy()+"','"+d.getUpdateDate()+"')");
            statement1.executeUpdate();
            return true;
        }catch (Exception e){System.out.println(e);}
        return false;
    }


    public ArrayList<Document> getAllDocuments(){
        ArrayList<Document> docArray = new ArrayList<Document>(3);

        try {

            String getSharedDocIds = "(SELECT documentID FROM SharedDocuments WHERE sharedWith = '" + this.userName + "') AS B";
            String getDocumentsSharedWith = "(SELECT * FROM Documents NATURAL JOIN " + getSharedDocIds + ")";
            String getDocumentsOwnerBy = "(SELECT * FROM Documents WHERE owner = '" + this.userName + "') AS D";
            String getPublicAndRDocs = "SELECT * FROM documents WHERE documentType = 'Public' OR documentType = 'Restricted'";
            String sqlQuery = "SELECT * FROM " + getDocumentsOwnerBy + " UNION " + getDocumentsSharedWith + " UNION " + getPublicAndRDocs + ";";
            //System.out.println(sqlQuery);
            if (this.userType.equals("GU")){
                sqlQuery = "SELECT * FROM documents WHERE documentType = 'Public' OR documentType = 'Restricted';";
            }

            PreparedStatement statement1 = dataBaseConnection.prepareStatement(sqlQuery);

            ResultSet result = statement1.executeQuery();

            while (result.next()) {

                //System.out.println(result.getString("lockedBy"));
                docArray.add(new Document(result.getString("documentID"),result.getString("documentName"),result.getString("owner"),result.getString("documentType"),result.getString("lockedBy"),result.getString("contents"),result.getInt("versionCount")));
            }
        }catch (Exception e){System.out.println(e + "147");}
        return docArray;

    }

    public ArrayList<DocumentCommands> getOldDocuments(String documentID) {
        ArrayList<DocumentCommands> docArray = new ArrayList<DocumentCommands>(3);

        try {

            PreparedStatement statement1 = dataBaseConnection.prepareStatement("SELECT * FROM OldDocuments WHERE documentID = '"+documentID+"' ORDER BY versionNumber ASC;");

            ResultSet result = statement1.executeQuery();

            while (result.next()) {
                docArray.add(new DocumentCommands(result.getInt("versionNumber"),documentID,result.getString("updatedBY"),result.getString("updateDate"),result.getString("commands")));
            }
        }catch (Exception e){System.out.println(e + "215");}
        return docArray;
    }

    public boolean openDocumentByID(String documentID){
        try {
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("SELECT * FROM documents WHERE documentID = '"+ documentID +"';");
            ResultSet result = statement1.executeQuery();

            while (result.next()) {
                documentPanel.setDocumentData( new Document(result.getString("documentID"),result.getString("documentName"),result.getString("owner"),result.getString("documentType"),result.getString("lockedBy"),result.getString("contents"),result.getInt("versionCount")));
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
               // System.out.println(result.getString("lockedBy"));
                docArray.add(new DocumentCommands(result.getInt("versionNumber"),documentID,result.getString("updatedBY"),"December 5 2018",result.getString("commands")));
            }
        }catch (Exception e){System.out.println(e + "147");}
        return docArray;

    }

    public static HashSet<String> getDictionary(){
        return dictionary;
    }

    public HashSet<String> getTabooWords(String documentID){
        HashSet<String> wordSet = new HashSet<String>();

        try {
            String sql = "SELECT * FROM TabooWords WHERE location = '"+ documentID +"' OR location = 'GLOBAL';";
           // System.out.println(sql);
            PreparedStatement statement1 = dataBaseConnection.prepareStatement(sql);

            ResultSet result = statement1.executeQuery();

            while (result.next()) {
              //  System.out.println(result.getString("word"));
                wordSet.add(result.getString("word"));
            }
        }catch (Exception e){System.out.println(e + "222");}

        return wordSet;
    }

    public boolean setDocumentType(String documentID,String documentType){
        try{
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("UPDATE Documents SET documentType = '" + documentType + "' WHERE documentID = '" + documentID + "';");
            statement1.executeUpdate();

            if(documentType.equals("Private")){
                this.removeAllSharedUsers(documentID);
            }

            return true;
        }catch (Exception e){System.out.println(e);}
        return false;
    }

    public boolean inviteUserToDocument(String documentID, String userName){

        ///SEND INVIATION FIRST USER ACCEPTS THE INVITAIOTN
        return this.addSharedUser(documentID,userName);


      //  return false;
    }

    public ArrayList<String> getUsersSharedWith(String documentID){
        ArrayList<String> userList = new ArrayList<String>();

        try {
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("SELECT * FROM SharedDocuments WHERE documentID = '" + documentID + "';");
            ResultSet result = statement1.executeQuery();
            while (result.next()) {
                userList.add(result.getString("sharedWith"));
            }
        }catch (Exception e){System.out.println(e + "279");}



        return userList;
    }

    public boolean addSharedUser(String documentID, String userName){
        try{
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("INSERT INTO SharedDocuments VALUES ('" + userName + "','" + documentID + "')");
            statement1.executeUpdate();

            return true;
        }catch (Exception e){System.out.println(e);}
        return false;
    }

    public boolean removeSharedUser(String documentID, String userName){
        try{
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("DELETE FROM SharedDocuments WHERE sharedWith = '" + userName + "' AND documentID = '" + documentID + "'");
            statement1.executeUpdate();

            return true;
        }catch (Exception e){System.out.println(e);}

        return false;
    }

    public boolean removeAllSharedUsers(String documentID){
        try{
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("DELETE FROM SharedDocuments WHERE documentID = '" + documentID + "'");
            statement1.executeUpdate();

            return true;
        }catch (Exception e){System.out.println(e);}

        return false;
    }

    public boolean sendMessage(String userName, String messageType, String subject, String message){
       try{
           PreparedStatement statement1=dataBaseConnection.prepareStatement("INSERT INTO Messages Values ('"+ userName +"', '"+messageType+"', '"+subject+"', '"+message+"');");
           statement1.executeUpdate();
           return true;
       }catch (Exception e){System.out.println(e);}

       return false;
    }
    public ArrayList<String> getallMessages(String messageType){

        ArrayList<String> allmessages = new ArrayList<String>();

        try{
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("SELECT * FROM Messages WHERE messageType = '" + messageType + "';");
            ResultSet result = statement1.executeQuery();
            while (result.next()){
                allmessages.add(result.getString("messageType"));
            }
        }catch (Exception e){System.out.println(e);}

        return allmessages;

    }

}

    public ArrayList<User> searchUsers(String name, String interests) {
        ArrayList<User> userArray = new ArrayList<User>();

        try {
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("SELECT userName, userInterests FROM userInformation WHERE userName LIKE '" + name + "%' AND userInterests LIKE '" + interests + "%';");
            ResultSet result = statement1.executeQuery();

            while (result.next()) {
                userArray.add(new User(result.getString("userName"), result.getString("userInterests")));
            }

        } catch (Exception e) {System.out.println(e);}
        return userArray;
    }

    public ArrayList<Document> searchDocument(String docName, String docOwner) {
        ArrayList<Document> userArray = new ArrayList<Document>();

        try {
            PreparedStatement statement1 = dataBaseConnection.prepareStatement("SELECT documentName, owner FROM Documents WHERE documentName LIKE '" + docName +"%' AND owner LIKE '" + docOwner + "%';");
            ResultSet result = statement1.executeQuery();

            while (result.next()) {
                userArray.add(new User(result.getString("documentName"), result.getString("owner")));
            }

        } catch (Exception e) {System.out.println(e);}
        return userArray;
    }
