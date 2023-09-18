import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch


;

public class FletteTrad implements Runnable  {
    private Monitor2 monitor;
    private Thread traad;
    

    public FletteTrad(Monitor2 monitor){
        this.monitor=monitor;
        traad=new Thread(this);
         }
         

    public void run(){

        try{
            for(int teller=8; teller>0; teller--){
               ArrayList nArrayList=monitor.taUt2();
               //while(nArrayList.size()>2){
                if (nArrayList.get(0) == null && nArrayList.get(1) == null) {
                    break;
                }
                monitor.settInn(monitor.kombinasjonsHashMap(monitor.taUt2().get(0),monitor.taUt2().get(1)));
                traad=new Thread();
                traad.start();
                
            }
        } catch(InterruptedException e){
            System.out.println(e+ " er Interrupted");
        }
       /*  try{
        do{

            HashMap<String, Subsekvens> subMap1 = monitor.taUt(0);
            HashMap<String, Subsekvens> subMap2 = monitor.taUt(0);

            if (subMap1 == null && subMap2 == null) {
                break;
            }
            monitor.settInn(monitor.kombinasjonsHashMap(subMap1,subMap2));

        }while(monitor.hentAntSubOrdbok()==1);
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }finally{
        tilstand.countDown();

          

        }*/
    
    }
    
}
