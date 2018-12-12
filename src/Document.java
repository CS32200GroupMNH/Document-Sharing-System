import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class Document {

    private String documentID;
    private String documentName;
    private String documentOwner;
    private String documentType;
    private boolean locked = false;

    public int getCurrentDocVersion() {
        return currentDocVersion;
    }

    private int currentDocVersion;
    private String lockedBy;

    private String documentContent = "";
    private boolean flaggedDocument = false;


    public void setDocFlag(boolean val){
        flaggedDocument = val;
    }

    public boolean isFlagged(){
        return flaggedDocument;
    }


    public Document(String id, String name, String owner, String type, String lockedName, String contents, int docVersion) {
        documentID = id;
        documentName = name;
        documentType = type;
        documentOwner = owner;
        documentContent = contents;
        currentDocVersion = docVersion;

        if(lockedName == null){
            locked = false;
        }
        else{
            locked = true;
            lockedBy = lockedName;

        }

    }



    //This function checks if a particular String is in the HashSet
    public boolean checkForWord(String word, HashSet<String> list)
    {
        if (list.contains(word.toLowerCase())){

            return true;
        }

        return false;
    }

    //checks document for taboo word and replaces with UNK, returns a string with what the document should contain
    public String checkDocumentForTabooWords(String preSaveDocument)
    {
        SystemManager s = SystemManager.getInstance();

        boolean isThereUNK = false;

        StringBuilder newDocumentContent = new StringBuilder("");

        String[] documentWords = preSaveDocument.split("\n");

        for(int i = 0; i<documentWords.length; i++ )
        {
            if (checkForWord(documentWords[i], s.getTabooWords(documentID))){
                newDocumentContent.append("UNK"+"\n");
                isThereUNK = true;
            }
            else{
                newDocumentContent.append(documentWords[i]+"\n");
            }
        }

        if(isThereUNK){
            return newDocumentContent.toString();
        }
        else{
            return null;
        }
    }
    //if the last word is spelled incorrectly returns false
    public boolean spellChecker(){
        SystemManager s = SystemManager.getInstance();

        String[] parts = documentContent.split("\n");
        String lastWord = parts[parts.length - 1];
        if (checkForWord(lastWord, s.getDictionary())){
            return true;
        }
        return false;
    }

    public int getStartIndex(){
        return documentContent.lastIndexOf("\n");
    }

    public int getEndIndex(){
        return documentContent.length() -1;
    }

    public boolean updateDocument(String s){
        SystemManager s1 = SystemManager.getInstance();
        String oldDocumentText = this.documentContent;


        this.currentDocVersion++;
        boolean savedDoc = false;
        boolean savedHistoryCommands = false;

        if(this.locked){
            savedDoc = s1.saveDocument(this,s);
        }

        if(savedDoc){
            if(this.currentDocVersion > 1) {
                DocumentCommands oldDoc = new DocumentCommands(this.currentDocVersion - 1, this.documentID, s1.getUserName(), "DATE");
                oldDoc.generateCommands(oldDocumentText, s);
                s1.saveOldDocuments(oldDoc);
            }
            this.documentContent = s;
            return true;
        }
        else{
            this.documentContent = oldDocumentText;

            if(this.currentDocVersion > 0){
                this.currentDocVersion--;
            }

            return false;
        }
    }

    public void flagThisDocument(){
        SystemManager s = SystemManager.getInstance();
        s.flagDocument(this.documentID);
    }

    public void unFlagThisDocument(){
        SystemManager s = SystemManager.getInstance();
        s.unFlagDocument(this.documentID);
    }

    private String generateHistoryCommands(String oldDoc,String newDoc){

        return oldDoc;
    }

    private boolean hasPermissionToUnlock(String userName){
        if (userName != null){
                return (userName.equals(this.lockedBy) || userName.equals(this.documentOwner));
        }
        return false;
    }

    public boolean changeDocumentLock(boolean lock){ //lock and unlock the document

        SystemManager s1 = SystemManager.getInstance();
        if (!lock){
            if(s1.unLockDocument(this.documentID) && hasPermissionToUnlock(s1.getUserName())){
                locked = false;
                lockedBy = null;
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(s1.lockDocument(this.documentID)){
                locked = true;
                lockedBy = s1.getUserName();
                return true;
            }
            else{
                return false;
            }
        }


    }

    public boolean changeDocumentType(String docType){
        if(docType.equals(this.documentType)){
            return true;
        }

        SystemManager s = SystemManager.getInstance();
        if (s.setDocumentType(this.documentID,docType)){
            this.documentType = docType;
            return true;
        }
        return false;
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

    public ArrayList<String> getSharedUsers(){
        SystemManager s = SystemManager.getInstance();
      ArrayList<String> userList =  s.getUsersSharedWith(this.documentID);

          return userList;
    }

    public boolean removeUserFromDocument(String userName){
        SystemManager s = SystemManager.getInstance();
        return s.removeSharedUser(this.documentID, userName);
    }

    @Override
    public String toString() {
        return this.getDocumentName();
    }

    public String getDocumentID() {
        return documentID;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getDocumentOwner() {
        return documentOwner;
    }

    public String getDocumentType() {
        return documentType;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getLockedBy() {
        return lockedBy;
    }

    public String getDocumentContent() {
        return documentContent;
    }

}
