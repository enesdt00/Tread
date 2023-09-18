import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
public class Monitor2 {
    private static SubsekvensRegister subsekvensRegister;
    private Lock laas=new ReentrantLock();
    private Condition ikkeNokMap=laas.newCondition();
    private  int antall;
    private boolean ikkeFerdig=false;
    private int underbehandlig=0;

    public Monitor2(SubsekvensRegister subsekvensRegister){
        this.subsekvensRegister=subsekvensRegister;
        antall=0;
        
    }
    public void settInnFlett(HashMap<String,Subsekvens> nyOrdbok){
        laas.lock();
        try{
            
          subsekvensRegister.settInn(nyOrdbok);
         if(antall ==1 && !ikkeFerdig){

          ikkeNokMap.signalAll();}
        }
        finally{
            laas.unlock();
        }

    }
    public void settInn(HashMap<String,Subsekvens> nyOrdbok){
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
    public ArrayList<HashMap<String,Subsekvens>> taUt2() throws InterruptedException{
        laas.lock();
        try{
            while(subsekvensRegister.hentAntSubOrdbok()<2 ){
                ikkeNokMap.await();
            }
            
            HashMap<String, Subsekvens> subMap1 = subsekvensRegister.taUt(0);
            HashMap<String, Subsekvens> subMap2 = subsekvensRegister.taUt(0);
            ArrayList<HashMap<String, Subsekvens>> subMapsummen =new ArrayList<>();
            subsekvensRegister.kombinasjonsHashMap(subMap1, subMap2);
            subMapsummen.add(subMap2);
            subMapsummen.add(subMap1);
            
            if(subMapsummen.size()==1 && underbehandlig==0){
                ikkeFerdig=true;
            }
            
            return subMapsummen;
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
   public HashMap<String,Subsekvens> nyHashMap(String filenavn) throws FileNotFoundException {
        laas.lock();
        try{
            ikkeNokMap.signal();
           
           return SubsekvensRegister.nyHashMap(filenavn);}
           finally{
            laas.unlock();
           }
        
       }
 

    public  HashMap<String,Subsekvens> kombinasjonsHashMap( HashMap<String,Subsekvens> subsekvenser1, HashMap<String,Subsekvens> subsekvenser2) throws InterruptedException{
      laas.lock();
      try{
      
     return  SubsekvensRegister.kombinasjonsHashMap(subsekvenser1, subsekvenser2);}
     finally{
        laas.unlock();
     }


    }

}


    

