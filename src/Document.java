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
        System.out.println(lockedBy);
    }

    public boolean updateDocument(String s){
        SystemManager s1 = SystemManager.getInstance();
        String oldDocumentText = this.documentContent;
        this.documentContent = s;

        boolean worked = s1.saveDocument(this);

        if(worked){
            return true;
        }
        else{
            this.documentContent = oldDocumentText;
            return false;
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
