import java.util.Date;

public class DocumentCommands {
    private int version;
    private String documentID;
    private String updatedBy;
    private String content;
    private String updateDate;


    public DocumentCommands(int versionNum, String documentID, String updatedBy, String updateD)  {

    }

    public DocumentCommands(int versionNum, String documentID, String updatedBy,String updateD, String commands) {

    }

    private static String compareLines(String oldLine, String newLine, int lineNumber){
        if(newLine == null){
            return "Add " + lineNumber + " " + oldLine + "\n";
        }
        else if (oldLine == null){
            return "Delete " + lineNumber + "\n";
        }
        else if (oldLine.equals(newLine)){
            return "";
        }
        else{
            return "Update " + lineNumber + " " + oldLine + "\n";
        }
    }

    public static String generateCommands(String oldDoc, String newDoc){
        String[] oldLines = oldDoc.split("\n");
        String[] newLines = newDoc.split("\n");

        StringBuilder commands = new StringBuilder("");

        int maxSize = Math.max(oldLines.length,newLines.length);
        for (int i = 0; i < maxSize; i++){
            if(i < oldLines.length && i < newLines.length){
                commands.append( compareLines(oldLines[i],newLines[i],i) );
            }
            else if(i < oldLines.length){
                commands.append( compareLines(oldLines[i],null,i) );
            }
            else{
                commands.append( compareLines(null,newLines[i],i)  );
            }
        }


        return commands.toString();


    }

    public static String createOldFile(String commands, String doc){
        String[] docLines = doc.split("\n");
        String[] commandLines = commands.split("\n");
        StringBuilder oldDoc = new StringBuilder("");


        return "";
    }


    public static void main(String[] args) {
        String o = "the";
        String n = "welcome\nthe\nworld\n";

        System.out.println(generateCommands(o,n));
    }
}
