import java.util.HashMap;
import java.util.LinkedList;

public class Incrocio {
    private  String descrizione; //deve essere univoca
    private  double euristica;
    private HashMap<String, Double> euristiche = new HashMap< String, Double>();
    /* per ogni Incrocio destinazione possibile, identificato da descrizione,
    è indicata l'euristica a partire dal nodo in questione
     */
    private  LinkedList<Strada> adiacenti;
    private  Incrocio genitore;
  /*  private LinkedList <Strada> strade_entranti; // vecchio
    private LinkedList <Strada> strade_uscenti; // vecchio
    private Integer numentranti; // vecchio
    private Integer numuscenti; // vecchio */

    public Incrocio(){ }

    public Incrocio(String descrizione, HashMap<String, Double> euristiche){
        this.descrizione = descrizione;
        this.euristiche=euristiche;
        euristica = 0;
        adiacenti = new LinkedList<Strada>();
    }
    public void setEuristica(Incrocio destinazione){
        euristica=euristiche.get(destinazione.getDescrizione());
    }

    public double getEuristica(){
        return euristica;
    }

    public String getDescrizione(){
        return descrizione;
    }

    public Incrocio getGenitore(){
        return genitore;
    }

    public LinkedList<Strada> getListaAdiacenti(){
        return adiacenti;
    }

    public Incrocio getAdiacente(int i){
        return adiacenti.get(i).getIncrocio();
    }

    //aggiungere arco adiacente
    public void addStradaAdiacente ( Incrocio incrocio, double cost) {
        adiacenti.add(new Strada(incrocio,cost));
    }

    public String toString() {
        return descrizione;
    }

    // vecchio
    /*public Incrocio(LinkedList <Strada> strade_entranti , LinkedList <Strada> strade_uscenti){
        this.strade_entranti= strade_entranti;
        this.strade_uscenti= strade_uscenti;
        numentranti = this.strade_entranti.size();
        numuscenti = this.strade_uscenti.size();
    }

    // vecchio
    public Integer getNumentranti() {
    return(numentranti);
}

    // vecchio
    public Integer getNumuscenti() {
    return(numuscenti);
}

    // vecchio
    public Strada getStradaentrante(Integer index){
        if(index >= 0&& index < strade_entranti.size())
            return(strade_entranti.get(index));
        return(strade_entranti.get(0));
    }

    // vecchio
    public Strada getStradauscente(Integer index){
        if(index >= 0&& index < strade_uscenti.size())
            return(strade_uscenti.get(index));
        return(strade_uscenti.get(0));
    }*/
    
}


