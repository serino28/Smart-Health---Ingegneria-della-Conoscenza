import java.util.HashMap;
import java.util.LinkedList;

public class Ospedale {
    private Citta citta;
    private static KB kb;
    private static final Csp CSP = new Csp();
    private Incrocio LUOGO = null; //luogo dell'ospedale sulla mappa .... INUTILE (?)

    private static Integer CAPIENZA = 100; //(=POSTI_COVID+POSTI)
    private static Integer N_POSTI_COVID = 50;    //posti covid totali
    private static Integer N_RIANIMATORI_COVID = 20;  //rianimatori totali per le ambulanze
    private static Integer N_RIANIMATORI_NORM = 20;  //rianimatori totali per le ambulanze
    private static Integer N_MEDICI_NORM = 20;  //medici NONcovid totali per le ambulanze
    private static Integer N_AMBULANZE = 15;
    private static Integer N_MEDICI_COVID = 20;  //medici covid totali per le ambulanze
    private static Integer N_AUTISTI = 12;
    private static Integer N_POSTI_NORM = CAPIENZA - N_POSTI_COVID;;    //posti NONcovid totali
    //private static Integer NUMERO_POSTI_COVID = 20;
    //private static Integer NUMERO_POSTI_NORM = 20;

    private static Integer disp_postiCovid;  //posti covid disponibili
    private static Integer disp_postiNorm; //posti NONcovid disponibili
    private static Integer disp_rianimatori_covid;   //rianimatori covid disponibili
    private static Integer disp_rianimatori_norm;   //rianimatori non covid disponibili
    private static Integer disp_mediciNorm; //medici NONcovid disponibili
    private static Integer disp_mediciCovid;   //medici covid disponibili
    private static Integer disp_autisti;
    private static Integer disp_ambulanze;

    private Boolean ambulanze_disponibili;

    private static final String str_rianimatore_norm = "rianimatore norm";
    private static final String str_rianimatore_covid = "rianimatore covid";
    private static final String str_medicoCovid = "medicoCovid";
    private static final String str_medicoNorm = "medicoNorm";
    private static final String str_autista = "autista";

    private static LinkedList<Operatore> rianimatori_norm;
    private static LinkedList<Operatore> rianimatori_covid; //rianimatori attualmente a lavoro (mettere boolean al rianimatore se impegnato in emergenza)
    private static LinkedList<Operatore> mediciNorm;
    private static LinkedList<Operatore> mediciCovid;
    private static LinkedList<Operatore> autisti;
    private static LinkedList<Ambulanza> ambulanze;

    private static LinkedList<Paziente> pazientiCovid ;
    private static Integer num_pazienti_covid;
    private static LinkedList<Paziente> pazientiNorm ;
    private static Integer num_pazienti_norm;


    //costruttore per manipolazione delle setting
    //quando viene chiamato questo costruttore vanno effettuati i controlli che gli Integer siano >= 0
    public Ospedale(Integer N_POSTI_COVID, Integer N_POSTI_NORM,
                    Integer N_RIANIMATORI_COVID, Integer N_RIANIMATORI_NORM,
                    Integer N_MEDICI_COVID, Integer N_MEDICI_NORM,
                    Integer N_AMBULANZE, Integer N_AUTISTI,
                    Citta citta){

        this.CAPIENZA = N_POSTI_COVID + N_POSTI_NORM;
        this.N_POSTI_COVID = N_POSTI_COVID;
        this.N_POSTI_NORM = N_POSTI_NORM;
        this.N_RIANIMATORI_COVID = N_RIANIMATORI_COVID;
        this.N_RIANIMATORI_NORM = N_RIANIMATORI_NORM;
        this.N_MEDICI_COVID = N_MEDICI_COVID;
        this.N_MEDICI_NORM = N_MEDICI_NORM;
        this.N_AMBULANZE = N_AMBULANZE;
        this.N_AUTISTI = N_AUTISTI;

        kb = new KB();
        this.citta=citta;

        rianimatori_covid = creaListaOperatori(str_rianimatore_covid, this.N_RIANIMATORI_COVID);
        //conto gli operatori che hanno Stato == getStatoPossibile di 0 cioè == "turno"
        //quindi sto contando gli operatori diponibili
        disp_rianimatori_covid = contaOperatori(rianimatori_covid,Operatore.getStatoPossibile(0));
        if(disp_rianimatori_covid>0)
            kb.setFlag("DISP_RIANIMATORI_COVID",true);
        else kb.setFlag("DISP_RIANIMATORI_COVID",false);

        rianimatori_norm = creaListaOperatori(str_rianimatore_norm, this.N_RIANIMATORI_NORM);
        disp_rianimatori_norm = contaOperatori(rianimatori_norm,Operatore.getStatoPossibile(0));
        if(disp_rianimatori_norm>0)
            kb.setFlag("DISP_RIANIMATORI_NORMALI",true);
        else kb.setFlag("DISP_RIANIMATORI_NORMALI",false);

        mediciNorm = creaListaOperatori(str_medicoNorm, this.N_MEDICI_NORM);
        disp_mediciNorm = contaOperatori(mediciNorm,Operatore.getStatoPossibile(0));
        if(disp_mediciNorm>0)
            kb.setFlag("DISP_MEDICI_NORMALI",true);
        else kb.setFlag("DISP_MEDICI_NORMALI",false);

        mediciCovid = creaListaOperatori(str_medicoCovid, this.N_MEDICI_COVID);
        disp_mediciCovid = contaOperatori(mediciCovid,Operatore.getStatoPossibile(0));
        if(disp_mediciCovid>0)
            kb.setFlag("DISP_MEDICI_COVID",true);
        else kb.setFlag("DISP_MEDICI_COVID",false);

        autisti = creaListaOperatori(str_autista, this.N_AUTISTI);
        disp_autisti = contaOperatori(autisti,Operatore.getStatoPossibile(0));
        if(disp_autisti>0)
             kb.setFlag("DISP_AUTISTI",true);
        else kb.setFlag("DISP_AUTISTI",false);

        ambulanze = creaListaAmbulanze(this.N_AMBULANZE);
        disp_ambulanze = N_AMBULANZE;
        if(disp_ambulanze>0){
            kb.setFlag("DISP_AMB",true);
            //System.out.println("DISP_AMB SETTATA A TRUE NELLA KB");
        }
        else{ kb.setFlag("DISP_AMB",false);
        //System.out.println("DISP_AMB SETTATA A FALSE NELLA KB");
        }

        if(N_POSTI_COVID > 0){
            kb.setFlag("COVID_GESTIBILE",true);
            //System.out.println("covid gestibile");
        }
        else kb.setFlag("COVID_GESTIBILE",false);

        if(N_POSTI_NORM > 0)
            kb.setFlag("NORM_GESTIBILE",true);
        else kb.setFlag("NORM_GESTIBILE",false);

        pazientiCovid = new LinkedList<Paziente> ();
        num_pazienti_covid = 0;

        if(N_POSTI_COVID>num_pazienti_covid)
            kb.setFlag("DISP_POSTI_COVID",true);
        else kb.setFlag("DISP_POSTI_COVID",false);

        pazientiNorm = new LinkedList<Paziente> ();
        num_pazienti_norm = 0;

        if(N_POSTI_NORM > num_pazienti_norm ){
            kb.setFlag("DISP_POSTI_NORMALI",true);
            //System.out.println("DISP POST NORM FLAG DELLA KB SETTATO A ::::: TRUE");
        }
        else {kb.setFlag("DISP_POSTI_NORMALI",false);
            //System.out.println("DISP POST NORM FLAG DELLA KB SETTATO A ::::: FALSE");
        }

    }

    //costruttore
    public Ospedale(boolean primaEsecuzione,Citta citta) {

        if(primaEsecuzione){

            kb = new KB();

            this.citta=citta;
            rianimatori_covid = creaListaOperatori(str_rianimatore_covid, N_RIANIMATORI_COVID);
            //conto gli operatori che hanno Stato == getStatoPossibile di 0 cioè == "turno"
            //quindi sto contando gli operatori diponibili
            disp_rianimatori_covid = contaOperatori(rianimatori_covid,Operatore.getStatoPossibile(0));
            kb.setFlag("DISP_RIANIMATORI_COVID",true);

            rianimatori_norm = creaListaOperatori(str_rianimatore_norm, N_RIANIMATORI_NORM);
            disp_rianimatori_norm = contaOperatori(rianimatori_norm,Operatore.getStatoPossibile(0));
            kb.setFlag("DISP_RIANIMATORI_NORMALI",true);

            mediciNorm = creaListaOperatori(str_medicoNorm, N_MEDICI_NORM);
            disp_mediciNorm = contaOperatori(mediciNorm,Operatore.getStatoPossibile(0));
            kb.setFlag("DISP_MEDICI_NORMALI",true);

            mediciCovid = creaListaOperatori(str_medicoCovid, N_MEDICI_COVID);
            disp_mediciCovid = contaOperatori(mediciCovid,Operatore.getStatoPossibile(0));
            kb.setFlag("DISP_MEDICI_COVID",true);

            autisti = creaListaOperatori(str_autista, N_AUTISTI);
            disp_autisti = contaOperatori(autisti,Operatore.getStatoPossibile(0));
            kb.setFlag("DISP_AUTISTI",true);

            ambulanze = creaListaAmbulanze(N_AMBULANZE);
            disp_ambulanze = N_AMBULANZE;
            kb.setFlag("DISP_AMB",true);

            if(N_POSTI_COVID > 0){
                kb.setFlag("COVID_GESTIBILE",true);
                //System.out.println("covid gestibile");
            }
            else kb.setFlag("COVID_GESTIBILE",false);
            if(N_POSTI_NORM > 0)
                kb.setFlag("NORM_GESTIBILE",true);
            else kb.setFlag("NORM_GESTIBILE",false);

            pazientiCovid = new LinkedList<Paziente> ();
            num_pazienti_covid = 0;
            if(N_POSTI_COVID > 0 && num_pazienti_covid == 0)//è un'imperfezione ma in questo COSTRUTTORE PUò ANCHE ANDARE
                kb.setFlag("DISP_POSTI_COVID",true);
            else kb.setFlag("DISP_POSTI_COVID",false);

            pazientiNorm = new LinkedList<Paziente> ();
            num_pazienti_norm = 0;
            if(N_POSTI_NORM > 0 && num_pazienti_norm == 0)//è un'imperfezione ma in questo  COSTRUTTORE PUò ANCHE ANDARE
                kb.setFlag("DISP_POSTI_NORMALI",true);
            else kb.setFlag("DISP_POSTI_NORMALI",false);

        } else {
            /*se non è la prima esecuzione recuperare da file i dati dell'ultima esecuzione avvenuta*/
            //a disposizione per successive implementazioni
            rianimatori_covid = null;  /*rtti*/
            rianimatori_norm = null;  /*rtti*/
            mediciNorm = null; /*rtti*/
            mediciCovid = null; /*rtti*/
            autisti = null; /*rtti*/
            ambulanze = null; /*rtti*/
            citta = null; /*rtti*/
        }

    }

    public KB getKb(){
        return kb;
    }

    private static Boolean controllaDisponibilitàAmbulanze(){
        for (int i = 0; i < ambulanze.size(); i++ ) {
            if(ambulanze.get(i).get_disponibile()){
                kb.setFlag("DISP_AMB",true);
                return true;
            }

        }
        kb.setFlag("DISP_AMB",false);
        return false;
    }

    private Integer contaOperatori(LinkedList<Operatore> operatori, String stato){
        //conta gli operatori con lo stato = a stato
        int disp = 0;
        for(int i = 0; i < operatori.size(); i++)
            if(operatori.get(i).getStato() == stato) {
                disp++;
            }
        return disp;
    }

    /*creaListaOperatori:
    istanzia tanti operatori quanto è lunghezza
    ogni operatore istanziato del tipo tipo
    per ogni operatore, con una certa casualità dice lo stato (es. turno, ferie, malattia)
     */
    private LinkedList<Operatore> creaListaOperatori(String tipo, Integer lunghezza){
        LinkedList<Operatore> operatori = new LinkedList<Operatore>();
        String stato;
        for( int i = 0; i<lunghezza; i++){
            stato = Operatore.getStatoPossibile(0); /* assegnare con probabilità(bassa) un elemento di getStatoPossibile*/
            operatori.add(new Operatore(tipo,stato));
        }
        return operatori;
    }

    /*
     istanzia tante ambulanze quanto è lunghezza
    */
    private LinkedList<Ambulanza> creaListaAmbulanze(Integer lunghezza){
        LinkedList<Ambulanza> ambulanze = new LinkedList<Ambulanza>();
        for( int i = 0; i<lunghezza; i++){
            Ambulanza ambulanza = new Ambulanza(citta);
            ambulanze.add(ambulanza);

        }
        return ambulanze;
    }

    public void setLuogo(Incrocio LUOGO)   { this.LUOGO = LUOGO; }

    public Incrocio getLuogo() {  return LUOGO; }

    public void setCitta(Citta citta)   { this.citta = citta; }

    public Citta getCitta() {  return citta; }

    public Boolean getDisponibilitàAmbulanze()  { return ambulanze_disponibili; }

    //metodi get e set vari
    Integer getDisp_postiCovid()    { return N_POSTI_COVID - num_pazienti_covid; };

    void setDisp_postiCovid(Integer disp_postiCovid)    { this.disp_postiCovid = disp_postiCovid; };

    Integer getDisp_postiNorm() { return N_POSTI_NORM - num_pazienti_norm; };

    void setDisp_postiNorm(Integer disp_postiNorm)  { this.disp_postiNorm = disp_postiNorm; };

    Integer getDisp_rianimatori_norm()   { return disp_rianimatori_norm; };

    Integer getDisp_rianimatori_covid()   { return disp_rianimatori_covid; };

   // void setDisp_rianimatori(Integer disp_rianimatori)  { this.disp_rianimatori = disp_rianimatori; };

    Integer getDisp_mediciNorm()    { return disp_mediciNorm; };

    void setDisp_mediciNorm(Integer disp_mediciNorm)    { this.disp_mediciNorm = disp_mediciNorm; };

    Integer getDisp_mediciCovid()   { return disp_mediciCovid; };

    void setDisp_mediciCovid(Integer disp_mediciCovid)  { this.disp_mediciCovid = disp_mediciCovid; };

    Integer getDisp_autisti()   { return disp_autisti; };

    void setDisp_autisti(Integer disp_autisti)  { this.disp_autisti = disp_autisti; };

    Integer getDisp_ambulanze() { return disp_ambulanze; };

    void setDisp_ambulanze(Integer disp_ambulanze)  { this.disp_ambulanze = disp_ambulanze; };

    /*segnalazione
    riceve da Città segnalazione di nuova emergenza (arriveranno sintomi, urgenza e luogo)
    crea oggetto emergenza in base a sintomi, urgenza e luogo (emergenza si occuperà della categorizzazione)
    l'oggetto emergenza, categorizzato, viene parrato a mandaAmbulanza */
    public void segnalazione( LinkedList<String> sintomi, Integer urgenza, Incrocio luogo){
        Emergenza emergenza = new Emergenza( sintomi, urgenza, luogo);
        //in Emergenza avviene la categorizzazione
        //interrogare base di conoscenza per capire se ci sono posti disponibili, personale disponibile ecc per la categoria appropriata

        //System.out.println("covid erogabile: " + kb.askInterno(kb.emergenze_covid_erogabili_str).toString());
       //System.out.println("norm erogabile: " + kb.askInterno(kb.emergenze_normali_erogabili_str).toString());
        if(kb.askEsterno(kb.emergenze_covid_erogabili_str) || kb.askEsterno(kb.emergenze_normali_erogabili_str)){
            if(!mandaAmbulanza(emergenza)) { //se non è stato possibile mandare un'ambulanza
                inoltraEmergenza();
                System.out.println("!!! Non è stato possibile mandare l'ambulanza !!!\n" +
                                    "emergenza inoltrata ad un altro ospedale.");
            }
        }
        else { inoltraEmergenza();
               System.out.println("!!! Dalla base di conoscenza del sistema\n" +
                                  "risulta che non sono erogabili le emergenze\n" +
                                  "covid e normali !!!\n" +
                                  "emergenza inoltrata ad un altro ospedale.");
        }
    }

    private static void inoltraEmergenza() { /*a disposizione per successive implementazioni*/ }


    /*rivece in input oggetto emergenza
    in base ad esso, chiama il metodo di risoluzione del csp (risolviCSP)
    se possibile (se esiste ambulanza disponibile), prende oggetto ambulanza (dalla lista) i cui campi riguardanti il personale
    vengono avvalorati in base alla risoluzione del CSP
    far partire l'ambulanza*/
    private Boolean mandaAmbulanza(Emergenza emergenza){
        ambulanze_disponibili = controllaDisponibilitàAmbulanze();

        if(ambulanze_disponibili) {
            Boolean selezionata = false;
            Ambulanza amb_selezionata = null;
            for (int i = 0; i < ambulanze.size(); i++ ) { //trova un'ambulanza disponibile
                if (ambulanze.get(i).get_disponibile()) { //l'attributo stato di ambulanza è un boolean, true se disponibile else false
                    amb_selezionata = ambulanze.get(i); //aliasing .. da capire se scritto correttamente
                    amb_selezionata.set_disponibile(false); //pone l'attributo disponibile dell'ogg Ambulanza a false
                    disp_ambulanze--;
                    controllaDisponibilitàAmbulanze(); //per aggiornare la kb
                    selezionata = true;
                    break;
                }
            }

            boolean csp_risolto = risolviCSP(amb_selezionata, emergenza);
            if ( selezionata && csp_risolto) {
                System.out.println("Il sistema ha risolto il csp per determinare il personale nell'ambulanza");
                controllaPersonale(); // aggiornare kb
                System.out.println("in base alla categoria dell'emergenza e all'urgenza : ");
                System.out.println("Numero medici non covid : " + amb_selezionata.get_n_medici_non_covid());
                System.out.println("Numero medici covid : " + amb_selezionata.get_n_medici_covid());
                System.out.println("Rianimatore covid presente : " + amb_selezionata.getRianCovidPresente());
                System.out.println("Rianimatore non covid presente : " + amb_selezionata.getRianNormPresente());
                System.out.println("Autista presente : " + amb_selezionata.getAutistaPresente());
                amb_selezionata.raggiungiDestinazione(LUOGO,emergenza.get_luogo(),emergenza.get_categoria());
                return true;
            }
            else if ( !csp_risolto ){
                System.out.println("Non è stato possibile risolvere il csp per determinare il personale nell'ambulanza.");
            }

        }
        return false;
    }

    /*(categoria,grado di urgenza,oggetto ambulanza) //preparare l'ambulanza
    deve, in base ai vincoli* del csp, avvalorare i campi del personale dell'oggetto ambulanza risolvendo il csp adeguatamente
            (soluzione ottimale, cioè ottimizzare i vincoli flessibili e rispettare i vincoli rigidi)
            *capire dove stanno questi vincoli
	**rispettare anche capienza dell'ospedale (numero posti covid totali & numero posti non covid totali)
            (se non c'è disponibilità mandare ad altro ospedale => temporizzare quanto tempo l'ambulanza perde per andare e venire da altro ospedale)*/
    private Boolean risolviCSP(Ambulanza ambulanza, Emergenza emergenza) {
        String categoria = emergenza.get_categoria();
        Integer urgenza = emergenza.get_urgenza();

        HashMap<String,Integer> soluzioni;
        soluzioni = Csp.getPersonaleAmbulanza(categoria,urgenza);
        if(soluzioni != null){
            Integer numeroMediciNorm = soluzioni.get("mediciNorm");
            Integer numeroMediciCovid = soluzioni.get("mediciCovid");
            Integer numeroRianimatoriNorm = soluzioni.get("rianNorm");
            Integer numeroRianimatoriCovid = soluzioni.get("rianCovid");
            Integer numeroAutista = soluzioni.get("autisti");

            if( numeroMediciNorm <= disp_mediciNorm &&
                numeroMediciCovid <= disp_mediciCovid &&
                numeroRianimatoriCovid <= disp_rianimatori_covid &&
                numeroRianimatoriNorm <= disp_rianimatori_norm &&
                numeroAutista <= disp_autisti) {
                //System.out.println("arrivato al settaggio");
                    ambulanza.set_n_medici_covid(numeroMediciCovid);
                    setPersonaleAmbulanza(ambulanza,str_medicoCovid,numeroMediciCovid);

                    ambulanza.set_n_medici_non_covid(numeroMediciNorm);
                    setPersonaleAmbulanza(ambulanza,str_medicoNorm,numeroMediciNorm);

                    ambulanza.set_n_rianimatori_covid(numeroRianimatoriCovid);
                    setPersonaleAmbulanza(ambulanza,str_rianimatore_covid,numeroRianimatoriCovid);

                    ambulanza.set_n_rianimatori_norm(numeroRianimatoriNorm);
                    setPersonaleAmbulanza(ambulanza,str_rianimatore_norm,numeroRianimatoriNorm);

                    ambulanza.set_n_autista(numeroAutista);
                    setPersonaleAmbulanza(ambulanza,str_autista,numeroAutista);

                    disp_rianimatori_covid -= numeroRianimatoriCovid;
                    disp_rianimatori_norm -= numeroRianimatoriNorm;
                    disp_mediciCovid -= numeroMediciCovid;
                    disp_mediciNorm -= numeroMediciNorm;
                    disp_autisti -= numeroAutista;

                //System.out.println("Personale settato");
                    return true;

            }
            else {
                System.out.println(" !!! personale non sufficiente !!!");
            }
        }
        return false; //return true se csp risolto else return false
    }

    private static void controllaPersonale(){
        if(disp_autisti>0)
            kb.setFlag("DISP_AUTISTI",true);
        else kb.setFlag("DISP_AUTISTI",false);
        if(disp_rianimatori_norm>0)
            kb.setFlag("DISP_RIANIMATORI_NORMALI",true);
        else kb.setFlag("DISP_RIANIMATORI_NORMALI",false);
        if(disp_rianimatori_covid>0)
            kb.setFlag("DISP_RIANIMATORI_COVID",true);
        else kb.setFlag("DISP_RIANIMATORI_COVID",false);
        if(disp_mediciNorm>0)
            kb.setFlag("DISP_MEDICI_NORMALI",true);
        else kb.setFlag("DISP_MEDICI_NORMALI",false);
        if(disp_mediciCovid>0)
            kb.setFlag("DISP_MEDICI_COVID",true);
        else kb.setFlag("DISP_MEDICI_COVID",false);
    }

    private void setPersonaleAmbulanza(Ambulanza ambulanza, String tipo, Integer numero){

        switch(tipo){
            case(str_rianimatore_norm):{
                for(int n=0; n<numero; n++)
                    for(int i=0; i<rianimatori_norm.size(); i++)
                        if(rianimatori_norm.get(i).getStato() == Operatore.getStatoPossibile(0)){
                            ambulanza.addRianNorm(rianimatori_norm.get(i));
                            rianimatori_norm.get(i).setStato(Operatore.getStatoPossibile(1));
                            break;
                        }
                break;
            }
            case(str_rianimatore_covid):{
                for(int n=0; n<numero; n++)
                    for (int i = 0; i < rianimatori_covid.size(); i++)
                        if (rianimatori_covid.get(i).getStato() == Operatore.getStatoPossibile(0)) {
                            ambulanza.addRianCovid(rianimatori_covid.get(i));
                            rianimatori_covid.get(i).setStato(Operatore.getStatoPossibile(1));
                            break;
                        }
                    break;
            }
            case(str_medicoCovid):{
                for(int n=0; n<numero; n++)
                    for(int i=0; i<mediciCovid.size(); i++)
                        if(mediciCovid.get(i).getStato() == Operatore.getStatoPossibile(0)){
                            ambulanza.addMedicoCovid(mediciCovid.get(i));
                            mediciCovid.get(i).setStato(Operatore.getStatoPossibile(1));
                            break;
                        }
                break;
            }
            case(str_medicoNorm):{
                for(int n=0; n<numero; n++)
                    for(int i=0; i<mediciNorm.size(); i++)
                        if(mediciNorm.get(i).getStato() == Operatore.getStatoPossibile(0)){
                            ambulanza.addMedicoNorm(mediciNorm.get(i));
                            mediciNorm.get(i).setStato(Operatore.getStatoPossibile(1));
                            break;
                        }
                break;
            }
            case(str_autista):{
                for(int i=0; i<autisti.size(); i++)
                    if(autisti.get(i).getStato() == Operatore.getStatoPossibile(0)){
                        ambulanza.addAutista(autisti.get(i));
                        autisti.get(i).setStato(Operatore.getStatoPossibile(1));
                        break;
                    }
                break;
            }
        }
    }

    public static void arrivoAmbulanza(Paziente paziente, String categoria,
                                       LinkedList<Operatore> medNormArrivati,
                                       LinkedList<Operatore> medCovidArrivati,
                                       Operatore rianNormArrivato,
                                       Operatore rianCovidArrivato,
                                       Operatore autistaArrivato){
        System.out.println("L'ambulanza è tornata all'ospedale.");

        if(categoria == "covid"){
            if(num_pazienti_covid < N_POSTI_COVID){
                pazientiCovid.add(paziente);
                num_pazienti_covid ++ ;
            }
            else {System.out.println("Posti covid non sufficienti,\n" +
                    "paziente inoltrato ad un altro ospedale");
                    inoltraEmergenza();
            }
        }
        else if(num_pazienti_norm < N_POSTI_NORM){
            pazientiNorm.add(paziente);
            num_pazienti_norm ++ ;
            }
        else {System.out.println("Posti nonCovid non sufficienti,\n" +
                "paziente inoltrato ad un altro ospedale");
                 inoltraEmergenza();
             }
/*
        disp_rianimatori_covid;   //rianimatori covid disponibili
        disp_rianimatori_norm;   //rianimatori non covid disponibili
        disp_mediciNorm; //medici NONcovid disponibili
        disp_mediciCovid;   //medici covid disponibili
        disp_autisti;
        disp_ambulanze;*/

        for(int i = 0; i<medCovidArrivati.size(); i++){
            for(int k = 0; k<mediciCovid.size(); k++)
                if(mediciCovid.get(k).getStato() == Operatore.getStatoPossibile(1)){
                    mediciCovid.get(k).setStato(Operatore.getStatoPossibile(0));
                    disp_mediciCovid++;
                    break;
                }
        }
        for(int i = 0; i<medNormArrivati.size(); i++){
            for (int k = 0; k < mediciNorm.size(); k++)
                if (mediciNorm.get(k).getStato() == Operatore.getStatoPossibile(1)){
                    mediciNorm.get(k).setStato(Operatore.getStatoPossibile(0));
                    disp_mediciNorm++;
                    break;
                }
        }

        if(rianNormArrivato.getStato() != "non presente"){
            for(int k = 0; k<rianimatori_norm.size(); k++)
                if(rianimatori_norm.get(k).getStato() == Operatore.getStatoPossibile(1)){
                    rianimatori_norm.get(k).setStato(Operatore.getStatoPossibile(0));
                    disp_rianimatori_norm++;
                    break;
                }
        }

        if(rianCovidArrivato.getStato() != "non presente"){
            for(int k = 0; k<rianimatori_covid.size(); k++)
                if(rianimatori_covid.get(k).getStato() == Operatore.getStatoPossibile(1)){
                    rianimatori_covid.get(k).setStato(Operatore.getStatoPossibile(0));
                    disp_rianimatori_covid++;
                    break;
                }
        }

        if(autistaArrivato.getStato() != "non presente"){
            for(int k = 0; k<autisti.size(); k++)
                if(autisti.get(k).getStato() == Operatore.getStatoPossibile(1)){
                    autisti.get(k).setStato(Operatore.getStatoPossibile(0));
                    disp_autisti++;
                    break;
                }
        }

        for(int i = 0; i<ambulanze.size(); i++){
            if(ambulanze.get(i).get_disponibile() == false){
                ambulanze.get(i).set_disponibile(true);
                disp_ambulanze++;
                break;
            }
        }


        //AGGIORNAMENTO KB
        controllarePazienti();
        controllaPersonale();
        controllaDisponibilitàAmbulanze();

        /*
        System.out.println(ambulanze);
        System.out.println(pazientiCovid);
        System.out.println(pazientiNorm);
        System.out.println(mediciCovid);
        System.out.println(mediciNorm);
        System.out.println(rianimatori_covid);
        System.out.println(rianimatori_norm);
        System.out.println(autisti);*/
        //aggiornare il personale
    }

    private static void controllarePazienti(){
        if(num_pazienti_covid < N_POSTI_COVID)
            kb.setFlag("DISP_POSTI_COVID",true);
        else kb.setFlag("DISP_POSTI_COVID",false);
        if(num_pazienti_norm < N_POSTI_NORM)
            kb.setFlag("DISP_POSTI_NORMALI",true);
        else kb.setFlag("DISP_POSTI_NORMALI",false);
    }


    /*
	#appena l'ambulanza arriva all'ospedale, se ci sono posti disponibili, viene creato l'oggetto paziente
            => riempite le liste del pazienti
	#per ogni posto occupato,
    in base a categorizzazione e urgenza,
    appena viene creato l'oggetto paziente si fa una stima dei giorni in cui può occupare il posto (prevedere probabilità di vita vs morte)
            => in base all' "orologio" generale del simulatore, quando arriva il day x => si libera il posto
            (vedere capito sull'incertezza... vedere come si può adattare)*/

    public void simulaDegenzaPazienti(){ //da chiamare ogni giorno passato nella temporizzazione (col tempo accelerato)
        simulaDegenzaPazientiNorm();
        simulaDegenzaPazientiCovid();
    }

    private void simulaDegenzaPazientiCovid(){
        String esitoDegenza;
        for(int i=0; i<pazientiCovid.size(); i++){
            esitoDegenza = pazientiCovid.get(i).esitoDegenza();
            if( esitoDegenza == "GUARITO" ||   esitoDegenza == "MORTO")
            {
                pazientiCovid.remove();
                num_pazienti_covid--;
                i--;
            }
        }
    }
    private void simulaDegenzaPazientiNorm(){
        String esitoDegenza;
        for(int i=0; i<pazientiNorm.size(); i++){
            esitoDegenza = pazientiNorm.get(i).esitoDegenza();
            if( esitoDegenza == "GUARITO" ||   esitoDegenza == "MORTO")
            {
                pazientiNorm.remove();
                num_pazienti_norm--;
                i--;
            }
        }
    }

}
