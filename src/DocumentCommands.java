import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;
import java.util.Collections;

public class DocumentCommands {
    private int version;

    private String documentID;
    private String updatedBy;
    private String updateDate;

    public String getDocCommands() {
        return docCommands;
    }

    private String docCommands;


    public DocumentCommands(int versionNum, String documentID, String updatedBy, String updateD)  {
        this.version = versionNum;
        this.documentID = documentID;
        this.updatedBy = updatedBy;
        this.updateDate = updateD;
    }

    public DocumentCommands(int versionNum, String documentID, String updatedBy,String updateD, String commands) {
        this.version = versionNum;
        this.documentID = documentID;
        this.updatedBy = updatedBy;
        this.updateDate = updateD;
        this.docCommands = commands;
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

    public void generateCommands(String oldDoc, String newDoc){
        String[] oldLines = oldDoc.split("\n");
        String[] newLines = newDoc.split("\n");

        StringBuilder commands = new StringBuilder("");

        int maxSize = Math.max(oldLines.length,newLines.length);
        for (int i = 0; i < maxSize; i++){
            if(i < oldLines.length && i < newLines.length){
                commands.append( compareLines(oldLines[i],newLines[i],i+1) );
            }
            else if(i < oldLines.length){
                commands.append( compareLines(oldLines[i],null,i+1) );
            }
            else{
                commands.append( compareLines(null,newLines[i],i+1)  );
            }
        }


        this.docCommands = commands.toString();

    }

    public int getVersion() {
        return version;
    }

    public String getDocumentID() {
        return documentID;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }


    public String getUpdateDate() {
        return updateDate;
    }



    public static String createOldFile(String commands, String doc){
        String[] docLines = doc.split("\n");

        String[] commandLines = commands.split("\n");
        StringBuilder oldDoc = new StringBuilder("");
        int removalCount = 1;
        ArrayList<String> wordList = new ArrayList( Arrays.asList( docLines) );
        String newUpdate;
        for(String command: commandLines){
            String[] commandWords = command.split(" ");

            switch(commandWords[0])
            {
                case "Update":
                    if(commandWords.length == 2){
                        newUpdate = "";
                    }
                    else{
                        newUpdate = commandWords[2];
                    }
                    wordList.set(Integer.parseInt(commandWords[1]) - removalCount, newUpdate);
                    break;
                case "Delete":
                   // System.out.println(wordList);
                    wordList.remove(Integer.parseInt(commandWords[1]) - removalCount);
                    removalCount++;
                    break;
                case "Add":

                    wordList.add(Integer.parseInt(commandWords[1]) - removalCount ,commandWords[2]);
                    break;
                default:
                    System.out.println("no match" + commandWords[0]);
            }
        }

        return String.join("\n",wordList);

    }


    public static void main(String[] args) {
        String o = "the";
        String doc = "welcome          the\nworld\n";

       doc =  doc.replaceAll(" ","\n");
        String[] docLines = doc.split("\n");
        System.out.println(doc);
      //  System.out.println(co);
       // System.out.println(createOldFile(co,n));

    }
}
