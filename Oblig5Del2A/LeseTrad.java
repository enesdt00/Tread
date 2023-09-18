import java.io.FileNotFoundException;

public class LeseTrad implements Runnable{
    private String filnavn;
    private Monitor1 monitor;

    public LeseTrad(String filnavn, Monitor1 monitor){
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