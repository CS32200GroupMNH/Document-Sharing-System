public class Document {

    private String documentID;
    private String documentName;
    private String documentOwner;
    private String documentType;
    private boolean locked = false;
    private String lockedBy;

    private String documentContent = "";

    public Document(String id, String name, String owner, String type, String lockedBy, String contents) {
        documentID = id;
        documentName = name;
        documentType = type;
        documentOwner = owner;
        documentContent = contents;
        if(lockedBy == null){
            locked = false;
        }
        else{
            locked = true;
        }
        System.out.println(lockedBy);
    }

    public boolean updateDocument(String s){
        SystemManager s1 = SystemManager.getInstance();
        String oldDocumentText = this.documentContent;
        this.documentContent = s;

        boolean savedDoc = false;
        boolean savedHistoryCommands = false;

        if(this.locked){
            savedDoc = s1.saveDocument(this);
        }

        if(savedDoc){
            return true;
        }
        else{
            this.documentContent = oldDocumentText;
            return false;
        }
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
