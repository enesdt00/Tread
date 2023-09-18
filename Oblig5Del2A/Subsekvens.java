public class Subsekvens{
    public final String subsekvens;
    private int antMenneske;
    public Subsekvens(String subssekvens, int antMenneske){
        this.subsekvens=subssekvens;
        this.antMenneske=antMenneske;
    }
    public int hentAntall(){
        return antMenneske;
    }
    public void endreTall(int nyTall){
        antMenneske=nyTall;
    }
    @Override
    public String toString(){
        return "("+subsekvens+","+antMenneske+")";

    }
}