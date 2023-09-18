import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class SubsekvensRegister{
   // private HashMap<String,Subsekvens> subsekvensOrdbok;
    private ArrayList<HashMap<String,Subsekvens>> hashMapListe; //liste1 tar vare på alle hashMapene

    public SubsekvensRegister(){
     //  subsekvensOrdbok=new HashMap<>();
        hashMapListe=new ArrayList<>();
    }

    public void settInn(HashMap<String,Subsekvens> nyOrdbok){
        hashMapListe.add(nyOrdbok);

    }
    public HashMap<String,Subsekvens> taUt(int indeksNummer){
        return hashMapListe.remove(indeksNummer);


    }
    public int hentAntSubOrdbok(){
        return hashMapListe.size();
    }

    public void hentVerdi(){
        HashMap<String, Subsekvens> sistesub = hashMapListe.get(0);
        Subsekvens mestForekommendeSubsekvens = null;
        int maxAntall = 0;
        for (Subsekvens subsekvens : sistesub.values()) {
            if (subsekvens.hentAntall() > maxAntall) {
                maxAntall = subsekvens.hentAntall();
                mestForekommendeSubsekvens = subsekvens;
            }
        }

        System.out.println("Mest forekommende subsekvens: " + mestForekommendeSubsekvens.toString());
        System.out.println("Antall forekomster: " + mestForekommendeSubsekvens.hentAntall());
    
    }

    public static  HashMap<String,Subsekvens> nyHashMap(String filenavn) throws FileNotFoundException{
        HashMap<String,Subsekvens> subsekvensene=new HashMap<>();// det er en Ordbok
        FileInputStream fileInputStream = new FileInputStream(filenavn);
        Scanner myleser=new Scanner(fileInputStream);
        String linje="";
        while(myleser.hasNext() || myleser!=null){
            linje=myleser.nextLine().strip();
            if(linje.length()<3) continue;
            for(int teller=0; teller<linje.length()-2; teller++){ // alltid er to færre subsekvenser
                String subsekvensen =linje.substring(teller,teller+3);
              if(  !subsekvensene.containsKey(subsekvensen)){
                Subsekvens nySubsekvens=new Subsekvens(subsekvensen, 1);
                subsekvensene.put(subsekvensen,nySubsekvens);
              }

            }
            if((!myleser.hasNext())&& myleser!=null) break;
           
        }
    
       // System.out.println(subsekvensene.toString());
        myleser.close();
       
        return subsekvensene;
    }
    /* !!!! Kva menæ de å bruke toString her, fordi vi allerede har skrivet toString metoden i Subsekvens-klassen?!!!! 
     Nedenfor ser du to eksempler (disse finner du i fil1 og fil2 i mappen TestOppgaveEks). Hvis du
vil kan du teste metoden din på disse dataene og se om du får samme svar. Du kan også teste ved
å bruke datafilene i de andre mappene (se avsnitt om datafiler og formater i starten av denne
oppgaven). Skriv for eksempel ut innholdet av den returnerte HashMap-en med en for-each-løkke
og toString()-metoden i Subsekvens.
    */

    public static HashMap<String,Subsekvens> kombinasjonsHashMap( HashMap<String,Subsekvens> subsekvenser1, HashMap<String,Subsekvens> subsekvenser2){
        HashMap<String,Subsekvens> kombinasjonsHashMap=new HashMap<>();



        for(String input:subsekvenser1.keySet()){
            kombinasjonsHashMap.put(input,subsekvenser1.get(input));
        }
        for(String input:subsekvenser2.keySet()){
            if(kombinasjonsHashMap.containsKey(input)){
                int sumTall=kombinasjonsHashMap.get(input).hentAntall()+subsekvenser2.get(input).hentAntall();
                kombinasjonsHashMap.put(input,new Subsekvens(input, sumTall));
            }else{
                kombinasjonsHashMap.put(input,new Subsekvens(input, 1));
            }
        }
       
  /* 
        for(Map.Entry<String,Subsekvens> input:subsekvenser1.entrySet()){
            String subsekvens=input.getKey();
            Subsekvens subs= input.getValue();
            kombinasjonsHashMap.put(subsekvens, subs);
        }
        for(Map.Entry<String,Subsekvens> input:subsekvenser2.entrySet()){
            String subsekvens=input.getKey();
            Subsekvens subs= input.getValue();
            if(kombinasjonsHashMap.containsKey(subsekvens)){
                kombinasjonsHashMap.get(subsekvens).endreTall(kombinasjonsHashMap.get(subsekvens).hentAntall()+subs.hentAntall());
            }else{
            kombinasjonsHashMap.put(subsekvens, subs);}
        }
       
    
        for(int teller=1; teller<subsekvenser1.size(); teller++){
            int antTilfellet=1;
            String subsekvensen= subsekvenser1.get(teller).subsekvens;
           
            for(int innTeller=1; innTeller<subsekvenser2.size(); innTeller++){
                if(subsekvenser2.get(innTeller).subsekvens.equals(subsekvensen)){
                    antTilfellet++;
                    subsekvenser2.remove(subsekvenser2.get(innTeller));
                }
               
            } Subsekvens nySubsekvens=new Subsekvens(subsekvensen,antTilfellet);

            
            kombinasjonsHashMap.put(subsekvensen, nySubsekvens);

          

        }if(subsekvenser2.size()>0){
            for(int teller=0; teller<subsekvenser2.size(); teller++){
                
                String subsekvensen= subsekvenser1.get(teller).subsekvens;
                Subsekvens nySubsekvens=new Subsekvens(subsekvensen, 1);
                kombinasjonsHashMap.put(subsekvensen, nySubsekvens);
                
            }
        }*/
        
        return kombinasjonsHashMap;
    }


}