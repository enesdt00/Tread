import java.io.FileNotFoundException;
public class LeseTrad1 implements Runnable {
 


    private String filnavn;
    private Monitor2 monitor;

    public LeseTrad1(String filnavn, Monitor2 monitor){
        this.filnavn=filnavn;
        this.monitor=monitor;

    }

    public void run(){
        try {
            monitor.settInn(Monitor1.nyHashMap(filnavn)); // Hva er feilen her ?
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
    

