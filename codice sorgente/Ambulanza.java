import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

public class Ambulanza {
    //ATTRIBUTI
    private Citta citta;
    private Paziente paziente;

    //private boolean puoiPartire; //l'ambulanza ha l'ok dall'ospedale
    private boolean disponibile; //ambulanza disponibile per essere inviata
    //private Incrocio destinazione; //nodo della città in cui deve andare l'ambulanza
    private int n_medici_covid; //numero medici covid
    private int n_medici_norm; //numero medici non covid
    private int n_rianimatori_covid;
    private int n_rianimatori_norm;
    private int n_autista; //se c'è è 1 (un unico autista)

    private Operatore autista = new Operatore("non presente","non presente"); //autista dell'ambulanza
    //private boolean rianimatore_presente;
    private Operatore rianimatori_norm = new Operatore("non presente","non presente");
    private Operatore rianimatori_covid = new Operatore("non presente","non presente");
    private LinkedList<Operatore> medici_norm = new LinkedList<Operatore>();
    private LinkedList<Operatore> medici_covid = new LinkedList<Operatore>();
    //private static final int MAX_MEDICI_NORM = 3;
    //private static final int MAX_MEDICI_COVID = 3;

    //COSTRUTTORE
    public Ambulanza(Citta citta) {
        this.citta = citta;
        //puoiPartire = false;
        disponibile = true;
       //rianimatore_presente = false;
        //n_medici_covid = 0;
        //n_medici_norm = 0;
        //n_rianimatori_covid = 0;
        //n_rianimatori_norm = 0;
        //n_autista = 0;
    }

    public void addMedicoNorm(Operatore op) {
        medici_norm.add(op);
    }
    public void addMedicoCovid(Operatore op) {
        medici_covid.add(op);
    }
    public void addRianNorm(Operatore op) {
        rianimatori_norm = op;
    }
    public void addRianCovid(Operatore op) {
        rianimatori_covid = op;
    }
    public void addAutista(Operatore op) {
        autista = op;
    }

    //GET & SET
    /*public void set_puoiPartire(boolean stato) {
        puoiPartire = stato;
    }

    public boolean get_puoiPartire() {
        return puoiPartire;
    }*/

    public void set_disponibile(boolean stato) {
        disponibile = stato;
    }

    public boolean get_disponibile() {
        return disponibile;
    }


    public void set_n_medici_covid(int num) {
        n_medici_covid = num;
    }

    public void set_n_rianimatori_covid(int num) {
        n_rianimatori_covid = num;
    }

    public void set_n_rianimatori_norm(int num) {
        n_rianimatori_norm = num;
    }

    public int get_n_medici_covid() {
        return n_medici_covid;
    }

    public void set_n_medici_non_covid(int num) {
        n_medici_norm = num;
    }

    public void set_n_autista(int num) {
        n_autista = num;
    }

    public int get_n_medici_non_covid() {
        return n_medici_norm;
    }

   /* public void set_destinazione(Incrocio destinazione) {
        this.destinazione = destinazione;
    }*/

   /* public Incrocio get_destinazione() {
        return destinazione;
    } */

  /*  public boolean get_rianimatore_presente() {
        return rianimatore_presente;
    }*/

    /*
    public void aggiungi_medico_norm(Operatore op) {
        if (medici_norm.size() < MAX_MEDICI_NORM && op.getTipo() == "medicoNorm") {
            medici_norm.add(op);
            n_medici_norm++;
        }
    }

    public void aggiungi_medico_covid(Operatore op) {
        if (medici_covid.size() < MAX_MEDICI_COVID && op.getTipo() == "medicoCovid") {
            medici_covid.add(op);
            n_medici_covid++;
        }
    }*/

   /* public void set_rianimatore(Operatore op) {
        if (op.getTipo() == "rianimatore") {
            rianimatore_presente = true;
            rianimatore = op;
        }
    }*/

    public void raggiungiDestinazione(Incrocio partenza, Incrocio destinazione, String categoria) {
        /*per stabilire la destinazione occorre settare l'euristica a tutti i nodi*/
        LinkedList<Incrocio> luoghiSettati = citta.getMappaToDestinazione(destinazione);
        Incrocio nodo_partenza_settato = new Incrocio();
        for(int i = 0; i<luoghiSettati.size(); i++)
            if(luoghiSettati.get(i).getDescrizione() == partenza.getDescrizione()){
                nodo_partenza_settato=luoghiSettati.get(i);
                break;
            }
        IDAStar.IDAStar(nodo_partenza_settato);
        System.out.println("il sistema ha utilizzato un algoritmo per determinare\n"
                            + "il percorso dell'ambulanza sulla mappa\n" +
                            "considerando le distanze e il traffico");
        System.out.println("percorso trovato : " + IDAStar.getPercorsoTrovato());
        IDAStar.svuotaPercorsoTrovato();

            System.out.println("AMBULANZA: ''sto andando verso la destinazione . . .'' ");
            System.out.println("...");
            System.out.println("AMBULANZA: ''c'è traffico . . .''");
            System.out.println("AMBULANZA: ''accendo le sirene . . .''");
            System.out.println("...");
            System.out.println("l'ambulanza è arrivata a destinazione, prelevazione del paziente in corso...");
            Paziente paziente = prelevarePaziente();
            System.out.println("il paziente è stato prelevato, calcolo del percorso per tornare all'ospedale . . .");
            nodo_partenza_settato = destinazione;
            destinazione = luoghiSettati.get(0);
            luoghiSettati = citta.getMappaToDestinazione(destinazione);
            IDAStar.IDAStar(nodo_partenza_settato);
         //   System.out.println("calcolo del percorso effettuato!");
            System.out.println("percorso trovato : " + IDAStar.getPercorsoTrovato());
            IDAStar.svuotaPercorsoTrovato();
            System.out.println("AMBULANZA: ''sto tornando all'ospedale . . .''");

        /*bisogna salvare il percorso calcolato ??*/
        Ospedale.arrivoAmbulanza(paziente, categoria, medici_norm, medici_covid, rianimatori_norm, rianimatori_covid, autista);
        svuotaAmbulanza();
    }

    public String getRianCovidPresente(){
        if(rianimatori_covid.getStato() == "non presente") {
            //System.out.println(rianimatori_covid.getStato());
            return "No";
        }
        else {
            System.out.println(rianimatori_covid.getStato());
            return "Sì";
        }
    }

    public String getRianNormPresente(){
        if(rianimatori_norm.getStato() == "non presente")
            return "No";
        else return "Sì";
    }

    public String getAutistaPresente(){
        if(autista.getStato() == "non presente")
            return "No";
        else return "Sì";
    }

    private void svuotaAmbulanza(){
        medici_norm.clear();
        medici_covid.clear();
        /*
        for(int i=0; i<medici_norm.size(); i++){
            medici_norm.remove();
            //i--;
        }
        for(int i=0; i<medici_covid.size(); i++){
            medici_covid.remove();
            //i--;
        }*/
        rianimatori_covid.setStato("non presente");
        rianimatori_covid.setTipo("non presente");
        rianimatori_norm.setStato("non presente");
        rianimatori_norm.setTipo("non presente");
        autista.setStato("non presente");
        autista.setTipo("non presente");
    }

    private Paziente prelevarePaziente(){
        Date ddn = new Date(1970, 1, 1);
        char sesso = 'M';
        paziente = new Paziente("John","Doe",ddn,sesso); //LANCIA ECCEZIONE ??

        return paziente;
    }

    public String toString()
    {
        return "Ambulanza:\nMedici (Covid / Non): "+n_medici_covid+" "+n_medici_norm
                +"\nRianimatori (Covid / Non):"+n_rianimatori_covid+" "+n_rianimatori_norm+
                "\nAutista: "+n_autista+"\nDisponibile: " +disponibile;
    }

}