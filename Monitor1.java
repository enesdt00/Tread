import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor1{
    private SubsekvensRegister subsekvensRegister;
    private Lock laas=new ReentrantLock();
    private Condition fulfortOppdrag;
    private int ferdigeTraader = 0;

    public Monitor1(SubsekvensRegister subsekvensRegister){
        this.subsekvensRegister=subsekvensRegister;
    } public void settInn(HashMap<String,Subsekvens> nyOrdbok){
        laas.lock();
        try{
          subsekvensRegister.settInn(nyOrdbok);
        }
        finally{
            laas.unlock();
        }
 }
    public HashMap<String,Subsekvens> taUt(int indeksNummer){
        laas.lock();
        try{
           return subsekvensRegister.taUt(indeksNummer); 
        }
        finally{
            laas.unlock();
        }
 }
    public int hentAntSubOrdbok(){
        laas.lock();
        try{
           return subsekvensRegister.hentAntSubOrdbok(); 
        }
        finally{
            laas.unlock();
        }


    }

    public void hentVerdi(){
        laas.lock();
        try{
           subsekvensRegister.hentVerdi(); 
        }
        finally{
            laas.unlock();
        }
}

  //Begge to er at statisk metoder hvordan kan vi kalle objekt som ikke er statisk i en statisk metod?
   public static  HashMap<String,Subsekvens> nyHashMap(String filenavn) throws FileNotFoundException {
        
           return SubsekvensRegister.nyHashMap(filenavn);
        
       }
 

    public static  HashMap<String,Subsekvens> kombinasjonsHashMap( HashMap<String,Subsekvens> subsekvenser1, HashMap<String,Subsekvens> subsekvenser2){
       
     return  SubsekvensRegister.kombinasjonsHashMap(subsekvenser1, subsekvenser2);


    }
    public HashMap<String, Subsekvens> flett() throws InterruptedException {
        laas.lock();
        try {
            while (hentAntSubOrdbok() > 1 || ferdigeTraader < hentAntSubOrdbok() - 1) {
                if (hentAntSubOrdbok() < 2) {
                    fulfortOppdrag.await();
                } else {
                    HashMap<String, Subsekvens> ordbok1 = taUt(0);
                    HashMap<String, Subsekvens> ordbok2 = taUt(0);
                    HashMap<String, Subsekvens> kombinertOrdbok = kombinasjonsHashMap(ordbok1, ordbok2);
                    settInn(kombinertOrdbok);
                    ferdigeTraader++;
                }
            }
            return taUt(0);
        } finally {
            laas.unlock();
        }
    }

}

