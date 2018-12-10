public class HomePage {


    public HomePage() {
    }

    public static void clickedNewDocument(){
        SystemManager s = SystemManager.getInstance();
        s.changePage("NewDocumentPage");
    }

    public static void clickedOpenDocument(Document d){
        SystemManager s = SystemManager.getInstance();
        s.openDocumentFromObject(d);
    }



}
