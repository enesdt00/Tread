import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Oblig5Del2A {
    public static void main(String[] args) {
        String mappeNavn=args[0];
        SubsekvensRegister subsekvenslist=new SubsekvensRegister();
        Monitor1 monitor = new Monitor1(subsekvenslist);
       
        ArrayList<Thread> traader = new ArrayList<>();

        
            ArrayList<String> filnavnene = new ArrayList<>();
            File metaFil = new File(mappeNavn, "/metadata.csv");
            try (Scanner scanner = new Scanner(metaFil)) {
                while (scanner.hasNext()) {
                    String linje = scanner.nextLine();
                    String[] kolonner = linje.split(",");
                    String fileNavn = kolonner[0].trim();
                    filnavnene.add(fileNavn);
                }
            } catch (IOException e) {
                System.out.println("Kunne ikke lese metadata-filen.");
                e.printStackTrace();
                return;
            }

            for (String filnavn : filnavnene) {
                filnavn=mappeNavn+"/"+filnavn;
                Thread traad = new Thread(new LeseTrad(filnavn, monitor));
                traader.add(traad);
                traad.start();
            }
        

        
        for (Thread traad : traader) {
            try {
                traad.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //eneste forskjell er at nå ligger HashMap-ene som skal flettes inne i Monitor1-objektet.   Hva er egentlig ønsket her?
        while(monitor.hentAntSubOrdbok()>1){ 
            HashMap<String, Subsekvens> subsek1=monitor.taUt(0);
            HashMap<String, Subsekvens> subsek2=monitor.taUt(0);
            HashMap<String, Subsekvens> sumSubsek = monitor.kombinasjonsHashMap(subsek1, subsek2);
            monitor.settInn(sumSubsek);
           }
        monitor.hentVerdi();
        
    
 
    
    }}
