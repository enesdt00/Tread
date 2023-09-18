import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Oblig5Del1 {
    public static void main(String[] args) throws FileNotFoundException {
        
        String mappeNavn=args[0];
       // String[] mapper  = {"testdataliten"};
        SubsekvensRegister subsekvensRegister=new SubsekvensRegister(); // subsekvenRegister olarak tanimladigimda hata aliyoru,!!!
       // subsekvensRegister.nyHashMap("fil1.csv");
        
            ArrayList<String> filnavnene=new ArrayList<>();
            File metaFil=new File(mappeNavn,"/metadata.csv");
            try(Scanner scanner =new Scanner(metaFil)){
                while(scanner.hasNext()){
                    String linje=scanner.nextLine();
                    String[] kolonner=linje.split(",");
                    String fileNavn=kolonner[0].trim();
                    filnavnene.add(fileNavn);

                    

                  
                }
            } catch (IOException e) {
                System.out.println("Kunne ikke lese metadata-filen.");
                e.printStackTrace();
                return;
            }
            for(String fil: filnavnene){
              //  System.out.println(fil);
             // File nyfil=new File("OBLIGEN5/testdatalike/"+fil);
              subsekvensRegister.settInn(subsekvensRegister.nyHashMap(mappeNavn+"/"+fil));
             

              
            
           }
           while(subsekvensRegister.hentAntSubOrdbok()>1){
            HashMap<String, Subsekvens> subsek1=subsekvensRegister.taUt(0);
            HashMap<String, Subsekvens> subsek2=subsekvensRegister.taUt(0);
            HashMap<String, Subsekvens> sumSubsek = SubsekvensRegister.kombinasjonsHashMap(subsek1, subsek2);
            subsekvensRegister.settInn(sumSubsek);
           }
        subsekvensRegister.hentVerdi();
        

      
        
        
        
        
        
        
        
        
        
       
    }}