import java.util.*;

public class KB {

    //STRINGHE DEGLI ATOMI ASKABILI ALLA KB
    public String emergenze_normali_erogabili_str = "emergenze_normali_erogabili";
    public String emergenze_covid_erogabili_str = "emergenze_covid_erogabili";
    public String ospedale_2_mode_str = "ospedale_2_mode";
    public String ospedale_normale_str = "ospedale_normale";
    public String ospedale_covid_str = "ospedale_covid";
    public String ospedale_funzionante_str = "ospedale_funzionante";
    public String personale_normale_disponibile_str = "personale_normale_disponibile";
    public String personale_covid_disponibile_str = "personale_covid_disponibile";
    public String ambulanze_partibili_str = "ambulanze_partibili";
    public String medici_disponibili_str = "medici_disponibili";
    public String rianimatori_disponibili_str = "rianimatori_disponibili";
    public String posti_normali_disponibili_str = "posti_normali_disponibili";
    public String posti_covid_disponibili_str = "posti_covid_disponibili";
    public String medici_normali_disponibili_str = "medici_normali_disponibili";
    public String medici_covid_disponibili_str = "medici_covid_disponibili";
    public String rianimatori_nomali_disponibili_str = "rianimatori_nomali_disponibili";
    public String rianimatori_covid_disponibili_str = "rianimatori_covid_disponibili";
    public String normali_gestibile_str = "normali_gestibile";
    public String covid_gestibile_str = "covid_gestibile";
    public String ambulanze_disponibili_str = "ambulanze_disponibili";
    public String autisti_disponibili_str = "autisti_disponibili";

    //ATOMI
    private Boolean autisti_disponibili ; // attributi di base
    private Boolean ambulanze_disponibili ;
    private Boolean covid_gestibile ;   //l'ospedale può gestire casi covid
    private Boolean normali_gestibile ;   //l'ospedale può gestire casi normali
    private Boolean rianimatori_covid_disponibili ;
    private Boolean rianimatori_nomali_disponibili ;
    private Boolean medici_covid_disponibili ;
    private Boolean medici_normali_disponibili ;
    private Boolean posti_covid_disponibili ;
    private Boolean posti_normali_disponibili ;

    private LinkedList<Sensor> flags;

    private Boolean rianimatori_disponibili ;
    private Boolean medici_disponibili ;
    private Boolean ambulanze_partibili ;
    private Boolean personale_covid_disponibile ;
    private Boolean personale_normale_disponibile ;
    private Boolean ospedale_funzionante ;
    private Boolean ospedale_covid ;
    private Boolean ospedale_normale ;
    private Boolean ospedale_2_mode ;
    private Boolean emergenze_covid_erogabili ;
    private Boolean emergenze_normali_erogabili ;

    //private final LinkedList<Boolean> askable = new LinkedList<Boolean>();
    private LinkedList<String> clausoleHowWhynot = new LinkedList<>();
    private LinkedList<String> atomiCorpoFalsi = new LinkedList<>();

    private LinkedList<String> clausola_emergenze_normali_erogabili_list = new LinkedList<>();
    private LinkedList<String> clausola_emergenze_covid_erogabili_list = new LinkedList<>();
    private LinkedList<String> clausola_ospedale_2_mode_list = new LinkedList<>();
    private LinkedList<String> clausola_ospedale_normale_list = new LinkedList<>();
    private LinkedList<String> clausola_ospedale_covid_list = new LinkedList<>();
    private LinkedList<String> clausola_ospedale_funzionante_list = new LinkedList<>();
    private LinkedList<String> clausola_personale_normale_disponibile_list = new LinkedList<>();
    private LinkedList<String> clausola_personale_covid_disponibile_list = new LinkedList<>();
    private LinkedList<String> clausola_ambulanze_partibili_list = new LinkedList<>();
    private LinkedList<String> clausola_medici_disponibili_list = new LinkedList<>();
    private LinkedList<String> clausola_rianimatori_disponibili_list = new LinkedList<>();
    private LinkedList<String> clausola_posti_normali_disponibili_list = new LinkedList<>();
    private LinkedList<String> clausola_posti_covid_disponibili_list = new LinkedList<>();
    private LinkedList<String> clausola_medici_normali_disponibili_list = new LinkedList<>();
    private LinkedList<String> clausola_medici_covid_disponibili_list = new LinkedList<>();
    private LinkedList<String> clausola_rianimatori_nomali_disponibili_list = new LinkedList<>();
    private LinkedList<String> clausola_rianimatori_covid_disponibili_list = new LinkedList<>();
    private LinkedList<String> clausola_normali_gestibile_list = new LinkedList<>();
    private LinkedList<String> clausola_covid_gestibile_list = new LinkedList<>();
    private LinkedList<String> clausola_ambulanze_disponibili_list = new LinkedList<>();
    private LinkedList<String> clausola_autisti_disponibili_list = new LinkedList<>();

    public static void main(String args[]){ //MAIN TEST KB
        KB kb = new KB();

        kb.setFlag("DISP_MEDICI_NORMALI",false);
        kb.setFlag("DISP_MEDICI_COVID",false);

        System.out.println("ASK : " + kb.askEsterno("ospedale_funzionante"));
        System.out.println("HOW : " + kb.how("ospedale_funzionante"));
        System.out.println("WHYNOT : " + kb.whyNot("ospedale_funzionante"));

    }


    public KB()
    {
        flags = new LinkedList<Sensor>();
        // System.out.println("sensore messo:" + flags.get(0).getType());
        flags.add(new Sensor("DISP_AMB", true));
        flags.add(new Sensor("COVID_GESTIBILE", true));
        flags.add(new Sensor("NORM_GESTIBILE" , true));
        flags.add(new Sensor("DISP_POSTI_COVID", true));
        flags.add(new Sensor("DISP_POSTI_NORMALI", true));
        flags.add(new Sensor( "DISP_AUTISTI", true));
        flags.add(new Sensor( "DISP_RIANIMATORI_COVID", true));
        flags.add(new Sensor( "DISP_RIANIMATORI_NORMALI", true));
        flags.add(new Sensor( "DISP_MEDICI_NORMALI", true));
        flags.add(new Sensor( "DISP_MEDICI_COVID", true)) ;
        flags.add(new Sensor("DISP_POSTI_COVID", true));
        flags.add(new Sensor("DISP_POSTI_NORMALI", true));

        //System.out.println(flags.toString());

        autisti_disponibili = getFlag( "DISP_AUTISTI"); // attributi di base
        ambulanze_disponibili = getFlag("DISP_AMB");
        covid_gestibile = getFlag("COVID_GESTIBILE");   //l'ospedale può gestire casi covid
        normali_gestibile = getFlag("NORM_GESTIBILE");   //l'ospedale può gestire casi normali
        rianimatori_covid_disponibili = getFlag("DISP_RIANIMATORI_COVID");
        rianimatori_nomali_disponibili = getFlag("DISP_RIANIMATORI_NORMALI");
        medici_covid_disponibili = getFlag("DISP_MEDICI_COVID");
        medici_normali_disponibili = getFlag("DISP_MEDICI_NORMALI");
        posti_covid_disponibili = getFlag("DISP_POSTI_COVID");
        posti_normali_disponibili = getFlag("DISP_POSTI_NORMALI");

        rianimatori_disponibili = clausola_rianimatori_disponibili();
        medici_disponibili = clausola_medici_disponibili();
        ambulanze_partibili = clausola_ambulanze_partibili();
        personale_covid_disponibile = clausola_personale_covid_disponibile();
        personale_normale_disponibile = clausola_personale_normale_disponibile();
        ospedale_funzionante = clausola_ospedale_funzionante();
        ospedale_covid = clausola_ospedale_covid();
        ospedale_normale = clausola_ospedale_normale();
        ospedale_2_mode = clausola_ospedale_2_mode();
        emergenze_covid_erogabili = clausola_emergenze_covid_erogabili();
        emergenze_normali_erogabili = clausola_emergenze_normali_erogabili();

        clausola_ospedale_2_mode_list.add(covid_gestibile_str);
        clausola_ospedale_2_mode_list.add(normali_gestibile_str);

        clausola_ospedale_normale_list.add("not_"+covid_gestibile_str);
        clausola_ospedale_normale_list.add(normali_gestibile_str);

        clausola_ospedale_covid_list.add(covid_gestibile_str);
        clausola_ospedale_covid_list.add("not_"+normali_gestibile_str);

        clausola_personale_normale_disponibile_list.add(rianimatori_nomali_disponibili_str);
        clausola_personale_normale_disponibile_list.add(medici_normali_disponibili_str);

        clausola_personale_covid_disponibile_list.add(rianimatori_covid_disponibili_str);
        clausola_personale_covid_disponibile_list.add(medici_covid_disponibili_str);

        clausola_ambulanze_partibili_list.add(ambulanze_disponibili_str);
        clausola_ambulanze_partibili_list.add(autisti_disponibili_str);

        clausola_medici_disponibili_list.add(medici_covid_disponibili_str);
        clausola_medici_disponibili_list.add(medici_normali_disponibili_str);

        clausola_rianimatori_disponibili_list.add(rianimatori_nomali_disponibili_str);
        clausola_rianimatori_disponibili_list.add(rianimatori_covid_disponibili_str);

        clausola_posti_normali_disponibili_list.add(posti_normali_disponibili_str);

        clausola_posti_covid_disponibili_list.add(posti_covid_disponibili_str);

        clausola_medici_normali_disponibili_list.add(medici_normali_disponibili_str);

        clausola_medici_covid_disponibili_list.add(medici_covid_disponibili_str);

        clausola_rianimatori_nomali_disponibili_list.add(rianimatori_nomali_disponibili_str);

        clausola_rianimatori_covid_disponibili_list.add(rianimatori_covid_disponibili_str);

        clausola_normali_gestibile_list.add(normali_gestibile_str);

        clausola_covid_gestibile_list.add(covid_gestibile_str);

        clausola_ambulanze_disponibili_list.add(ambulanze_disponibili_str);

        clausola_autisti_disponibili_list.add(autisti_disponibili_str);

    }

    //metodi attributi derivati - "CLAUSOLE"

    private Boolean clausola_emergenze_covid_erogabili() {
        ospedale_covid = clausola_ospedale_covid();
        ospedale_2_mode = clausola_ospedale_2_mode();
        ambulanze_partibili = clausola_ambulanze_partibili();
        personale_covid_disponibile = clausola_personale_covid_disponibile();

        if(ospedale_2_mode && ambulanze_partibili && personale_covid_disponibile)
            return true;
        if(ospedale_covid && ambulanze_partibili && personale_covid_disponibile)
            return true;
        return false;
    }

    private void set_clausola_emergenze_covid_erogabili_list(){
        ospedale_covid = clausola_ospedale_covid();
        ospedale_2_mode = clausola_ospedale_2_mode();
        ambulanze_partibili = clausola_ambulanze_partibili();
        personale_covid_disponibile = clausola_personale_covid_disponibile();

        if(ospedale_2_mode && ambulanze_partibili && personale_covid_disponibile){
            clausola_emergenze_covid_erogabili_list.add(ospedale_2_mode_str);
            clausola_emergenze_covid_erogabili_list.add(ambulanze_partibili_str);
            clausola_emergenze_covid_erogabili_list.add(personale_covid_disponibile_str);
            return;
        }
        if(ospedale_covid && ambulanze_partibili && personale_covid_disponibile){
            clausola_emergenze_covid_erogabili_list.add(ospedale_covid_str);
            clausola_emergenze_covid_erogabili_list.add(ambulanze_partibili_str);
            clausola_emergenze_covid_erogabili_list.add(personale_covid_disponibile_str);
            return;
        }
        if(clausola_emergenze_covid_erogabili_list.isEmpty())
        {
            clausola_emergenze_covid_erogabili_list.add(ospedale_covid_str);
            clausola_emergenze_covid_erogabili_list.add(ambulanze_partibili_str);
            clausola_emergenze_covid_erogabili_list.add(personale_covid_disponibile_str);
        }
    }

    private Boolean clausola_emergenze_normali_erogabili() {

        ospedale_normale = clausola_ospedale_normale();
        ambulanze_partibili = clausola_ambulanze_partibili();
        personale_normale_disponibile = clausola_personale_normale_disponibile();
        ospedale_2_mode = clausola_ospedale_2_mode();
       /* System.out.println("osp normale: " + ospedale_normale.toString());
        System.out.println("amb partibili: " + ambulanze_partibili.toString());
        System.out.println("pers norm disp: " + personale_normale_disponibile.toString());*/

        if(ospedale_2_mode && ambulanze_partibili && personale_normale_disponibile)
            return true;
        if(ospedale_normale && ambulanze_partibili && personale_normale_disponibile)
            return true;
        return false;
    }

    private void set_clausola_emergenze_normali_erogabili_list(){
        ospedale_normale = clausola_ospedale_normale();
        ambulanze_partibili = clausola_ambulanze_partibili();
        personale_normale_disponibile = clausola_personale_normale_disponibile();
        ospedale_2_mode = clausola_ospedale_2_mode();
       /* System.out.println("osp normale: " + ospedale_normale.toString());
        System.out.println("amb partibili: " + ambulanze_partibili.toString());
        System.out.println("pers norm disp: " + personale_normale_disponibile.toString());*/

            if (ospedale_2_mode && ambulanze_partibili && personale_normale_disponibile) {
                clausola_emergenze_normali_erogabili_list.add(ospedale_2_mode_str);
                clausola_emergenze_normali_erogabili_list.add(ambulanze_partibili_str);
                clausola_emergenze_normali_erogabili_list.add(personale_normale_disponibile_str);
                return;
            }

            if (ospedale_normale && ambulanze_partibili && personale_normale_disponibile) {
                clausola_emergenze_normali_erogabili_list.add(ospedale_normale_str);
                clausola_emergenze_normali_erogabili_list.add(ambulanze_partibili_str);
                clausola_emergenze_normali_erogabili_list.add(personale_normale_disponibile_str);
                return;
            }
            if(clausola_emergenze_normali_erogabili_list.isEmpty()) {
                clausola_emergenze_normali_erogabili_list.add(ospedale_2_mode_str);
                clausola_emergenze_normali_erogabili_list.add(ambulanze_partibili_str);
                clausola_emergenze_normali_erogabili_list.add(personale_normale_disponibile_str);
            }
    }

    private Boolean clausola_ospedale_covid() {
        covid_gestibile = getFlag("COVID_GESTIBILE");
        normali_gestibile = getFlag("NORM_GESTIBILE");

        return covid_gestibile && !normali_gestibile;
    }

    private Boolean clausola_ospedale_normale() {
        covid_gestibile = getFlag("COVID_GESTIBILE");
        normali_gestibile = getFlag("NORM_GESTIBILE");

        return !covid_gestibile && normali_gestibile;
    }

    private Boolean clausola_ospedale_2_mode(){
        covid_gestibile = getFlag("COVID_GESTIBILE");
        normali_gestibile = getFlag("NORM_GESTIBILE");

        return covid_gestibile && normali_gestibile;
    }

    private Boolean clausola_rianimatori_disponibili() {
        rianimatori_covid_disponibili = getFlag("DISP_RIANIMATORI_COVID");
        rianimatori_nomali_disponibili = getFlag("DISP_RIANIMATORI_NORMALI");

        return rianimatori_nomali_disponibili && rianimatori_covid_disponibili;
    }

    private Boolean clausola_medici_disponibili(){
        medici_covid_disponibili = getFlag("DISP_MEDICI_COVID");
        medici_normali_disponibili = getFlag("DISP_MEDICI_NORMALI");

        return medici_covid_disponibili && medici_normali_disponibili;
    }

    private Boolean clausola_ambulanze_partibili(){
        ambulanze_disponibili = getFlag("DISP_AMB");
        autisti_disponibili = getFlag("DISP_AUTISTI");

        return ambulanze_disponibili && autisti_disponibili;
    }

    private Boolean clausola_personale_covid_disponibile(){
        medici_covid_disponibili = getFlag("DISP_MEDICI_COVID");
        rianimatori_covid_disponibili = getFlag("DISP_RIANIMATORI_COVID");

        return rianimatori_covid_disponibili && medici_covid_disponibili;
    }
    private Boolean clausola_personale_normale_disponibile(){
        medici_normali_disponibili = getFlag("DISP_MEDICI_NORMALI");
        rianimatori_nomali_disponibili = getFlag("DISP_RIANIMATORI_NORMALI");

        return rianimatori_nomali_disponibili && medici_normali_disponibili;
    }

    private void set_clausola_ospedale_funzionante_list(){
        rianimatori_disponibili = clausola_rianimatori_disponibili();
        ambulanze_partibili = clausola_ambulanze_partibili();
        medici_disponibili = clausola_medici_disponibili();
        ospedale_covid = clausola_ospedale_covid();
        ospedale_normale = clausola_ospedale_normale();
        ospedale_2_mode = clausola_ospedale_2_mode();
        rianimatori_covid_disponibili = getFlag("DISP_RIANIMATORI_COVID");
        rianimatori_nomali_disponibili = getFlag("DISP_RIANIMATORI_NORMALI");
        medici_covid_disponibili = getFlag("DISP_MEDICI_COVID");
        medici_normali_disponibili = getFlag("DISP_MEDICI_NORMALI");

        if(ospedale_2_mode && rianimatori_disponibili && ambulanze_partibili && medici_disponibili){
            clausola_ospedale_funzionante_list.add(ospedale_2_mode_str);
            clausola_ospedale_funzionante_list.add(rianimatori_disponibili_str);
            clausola_ospedale_funzionante_list.add(ambulanze_partibili_str);
            clausola_ospedale_funzionante_list.add(medici_disponibili_str);
            return;
        }

        if(ospedale_covid && rianimatori_covid_disponibili && ambulanze_partibili && medici_covid_disponibili){
            clausola_ospedale_funzionante_list.add(ospedale_covid_str);
            clausola_ospedale_funzionante_list.add(rianimatori_covid_disponibili_str);
            clausola_ospedale_funzionante_list.add(ambulanze_partibili_str);
            clausola_ospedale_funzionante_list.add(medici_covid_disponibili_str);
            return;
        }
        if(ospedale_normale && rianimatori_nomali_disponibili && ambulanze_partibili && medici_normali_disponibili){
            clausola_ospedale_funzionante_list.add(ospedale_normale_str);
            clausola_ospedale_funzionante_list.add(rianimatori_nomali_disponibili_str);
            clausola_ospedale_funzionante_list.add(ambulanze_partibili_str);
            clausola_ospedale_funzionante_list.add(medici_normali_disponibili_str);
            return;
        }
        if(clausola_ospedale_funzionante_list.isEmpty())
        {
            clausola_ospedale_funzionante_list.add(ospedale_2_mode_str);
            clausola_ospedale_funzionante_list.add(rianimatori_disponibili_str);
            clausola_ospedale_funzionante_list.add(ambulanze_partibili_str);
            clausola_ospedale_funzionante_list.add(medici_disponibili_str);
        }
    }

    private Boolean clausola_ospedale_funzionante(){
        rianimatori_disponibili = clausola_rianimatori_disponibili();
        ambulanze_partibili = clausola_ambulanze_partibili();
        medici_disponibili = clausola_medici_disponibili();
        ospedale_covid = clausola_ospedale_covid();
        ospedale_normale = clausola_ospedale_normale();
        ospedale_2_mode = clausola_ospedale_2_mode();
        rianimatori_covid_disponibili = getFlag("DISP_RIANIMATORI_COVID");
        rianimatori_nomali_disponibili = getFlag("DISP_RIANIMATORI_NORMALI");
        medici_covid_disponibili = getFlag("DISP_MEDICI_COVID");
        medici_normali_disponibili = getFlag("DISP_MEDICI_NORMALI");

        if(ospedale_2_mode && rianimatori_disponibili && ambulanze_partibili && medici_disponibili)
            return true;
        if(ospedale_covid && rianimatori_covid_disponibili && ambulanze_partibili && medici_covid_disponibili)
            return true;
        if(ospedale_normale && rianimatori_nomali_disponibili && ambulanze_partibili && medici_normali_disponibili)
            return true;
        return false;
    }

    public void setFlag(String type, boolean state)
    {
        try {
            int i;
            for(i = 0; i < flags.size(); i++)
            {
                if(flags.get(i).isEqual(type))
                    break;
            }
            if(i == flags.size())
                throw new Exception("Invalid Flag Type");
            flags.set(i, new Sensor(flags.get(i).getType(), state));
        }
        catch(Exception e){}
    }

    private boolean getFlag(String type)
    {
        return getSensor(type).getState();
    }

    private Sensor getSensor(String type)
    {
        try{
            //System.out.println("devo cercare il sensore" + type);
            //System.out.println(flags.toString());
            for(int i = 0; i < flags.size(); i++)
            {
                //System.out.println("found : " + flags.get(i).getType());
                if(flags.get(i).getType() == type)
                    return flags.get(i);
            }
            throw new Exception("Invalid Flag Type, return NULL");
        }catch(Exception e){ }
        return null;
    }

    private void setClausoleHowWhynot(String primoVal, LinkedList<String> lista){
        clausoleHowWhynot.add(primoVal);
        clausoleHowWhynot.add("<---");
        clausoleHowWhynot.add(lista.get(0));
        for(int i=1; i<lista.size();i++){
            clausoleHowWhynot.add("AND");
            clausoleHowWhynot.add(lista.get(i));
        }
    }

    private void setAtomiCorpoFalsi(LinkedList<String> lista){
        String str_aux = "";
        for(int i = 0; i<lista.size(); i++){
            if(lista.get(i).charAt(0)=='n' && lista.get(i).charAt(1)=='o' && lista.get(i).charAt(2)=='t'){
                for(int k=4; k< lista.get(i).length(); k++){
                    str_aux += lista.get(i).charAt(k);
                }
                //System.out.println("tolto il not, ho chiesto :" + str_aux);
                if(askEsterno(str_aux))
                    atomiCorpoFalsi.add(lista.get(i));
            }
            else if(!askEsterno(lista.get(i))){
                //System.out.println("ho chiesto :" + lista.get(i));
                atomiCorpoFalsi.add(lista.get(i));
            }

        }
    }

    private Boolean askInterno(String atomo){
        switch(atomo){
            case("emergenze_normali_erogabili"):{
                emergenze_normali_erogabili = clausola_emergenze_normali_erogabili();
                set_clausola_emergenze_normali_erogabili_list(); //da fare solo per i primi due atomi, perché bisogna capire quale if andare a mettere
                setClausoleHowWhynot(emergenze_normali_erogabili_str,clausola_emergenze_normali_erogabili_list);
                if(!emergenze_normali_erogabili)
                    setAtomiCorpoFalsi(clausola_emergenze_normali_erogabili_list);
                return emergenze_normali_erogabili;
            }
            case("emergenze_covid_erogabili"):{
                emergenze_covid_erogabili = clausola_emergenze_covid_erogabili();
                set_clausola_emergenze_covid_erogabili_list();
                setClausoleHowWhynot(emergenze_covid_erogabili_str,clausola_emergenze_covid_erogabili_list);
                if(!emergenze_covid_erogabili)
                    setAtomiCorpoFalsi(clausola_emergenze_covid_erogabili_list);
                return emergenze_covid_erogabili;
            }
            case("ospedale_2_mode"):{
                ospedale_2_mode = clausola_ospedale_2_mode();
                setClausoleHowWhynot(ospedale_2_mode_str,clausola_ospedale_2_mode_list);
                if(!ospedale_2_mode)
                    setAtomiCorpoFalsi(clausola_ospedale_2_mode_list);
                return ospedale_2_mode;
            }
            case("ospedale_normale"):{
                ospedale_normale = clausola_ospedale_normale();
                setClausoleHowWhynot(ospedale_normale_str,clausola_ospedale_normale_list);
                if(!ospedale_normale)
                    setAtomiCorpoFalsi(clausola_ospedale_normale_list);
                return ospedale_normale;
            }
            case("ospedale_covid"):{
                ospedale_covid = clausola_ospedale_covid();
                setClausoleHowWhynot(ospedale_covid_str,clausola_ospedale_covid_list);
                if(!ospedale_covid)
                    setAtomiCorpoFalsi(clausola_ospedale_covid_list);
                return ospedale_covid;
            }
            case("ospedale_funzionante"):{
                ospedale_funzionante = clausola_ospedale_funzionante();
                set_clausola_ospedale_funzionante_list();
                setClausoleHowWhynot(ospedale_funzionante_str,clausola_ospedale_funzionante_list);
                if(!ospedale_funzionante)
                    setAtomiCorpoFalsi(clausola_ospedale_funzionante_list);
                return ospedale_funzionante;
            }
            case("personale_normale_disponibile"):{
                personale_normale_disponibile = clausola_personale_normale_disponibile();
                setClausoleHowWhynot(personale_normale_disponibile_str,clausola_personale_normale_disponibile_list);
                if(!personale_normale_disponibile)
                    setAtomiCorpoFalsi(clausola_personale_normale_disponibile_list);
                return personale_normale_disponibile;
            }
            case("personale_covid_disponibile"):{
                personale_covid_disponibile = clausola_personale_covid_disponibile();
                setClausoleHowWhynot(personale_covid_disponibile_str,clausola_personale_covid_disponibile_list);
                if(!personale_covid_disponibile)
                    setAtomiCorpoFalsi(clausola_personale_covid_disponibile_list);
                return personale_covid_disponibile;
            }
            case("ambulanze_partibili"):{
                ambulanze_partibili = clausola_ambulanze_partibili();
                setClausoleHowWhynot(ambulanze_partibili_str,clausola_ambulanze_partibili_list);
                if(!ambulanze_partibili)
                    setAtomiCorpoFalsi(clausola_ambulanze_partibili_list);
                return ambulanze_partibili;
            }
            case("medici_disponibili"):{
                medici_disponibili = clausola_medici_disponibili();
                setClausoleHowWhynot(medici_disponibili_str,clausola_medici_disponibili_list);
                if(!medici_disponibili)
                    setAtomiCorpoFalsi(clausola_medici_disponibili_list);
                return medici_disponibili;
            }
            case("rianimatori_disponibili"):{
                rianimatori_disponibili = clausola_rianimatori_disponibili();
                setClausoleHowWhynot(rianimatori_disponibili_str,clausola_rianimatori_disponibili_list);
                if(!rianimatori_disponibili)
                    setAtomiCorpoFalsi(clausola_rianimatori_disponibili_list);
                return rianimatori_disponibili;
            }
            case("posti_normali_disponibili"):{
                posti_normali_disponibili = getFlag("DISP_POSTI_NORMALI");
                setClausoleHowWhynot(posti_normali_disponibili_str,clausola_posti_normali_disponibili_list);
                if(!posti_normali_disponibili)
                    setAtomiCorpoFalsi(clausola_posti_normali_disponibili_list);
                return posti_normali_disponibili;
            }
            case("posti_covid_disponibili"):{
                posti_covid_disponibili = getFlag("DISP_POSTI_COVID");
                setClausoleHowWhynot( posti_covid_disponibili_str,clausola_posti_covid_disponibili_list);
                if(!posti_covid_disponibili)
                    setAtomiCorpoFalsi(clausola_posti_covid_disponibili_list);
                return posti_covid_disponibili;
            }
            case("medici_normali_disponibili"):{
                medici_normali_disponibili = getFlag("DISP_MEDICI_NORMALI");
                setClausoleHowWhynot(medici_normali_disponibili_str,clausola_medici_normali_disponibili_list);
                if(!medici_normali_disponibili)
                    setAtomiCorpoFalsi(clausola_medici_normali_disponibili_list);
                return medici_normali_disponibili;
            }
            case("medici_covid_disponibili"):{
                medici_covid_disponibili = getFlag("DISP_MEDICI_COVID");
                setClausoleHowWhynot(medici_covid_disponibili_str,clausola_medici_covid_disponibili_list);
                if(!medici_covid_disponibili)
                    setAtomiCorpoFalsi(clausola_medici_covid_disponibili_list);
                return medici_covid_disponibili;
            }
            case("rianimatori_nomali_disponibili"):{
                rianimatori_nomali_disponibili = getFlag("DISP_RIANIMATORI_NORMALI");
                setClausoleHowWhynot(rianimatori_nomali_disponibili_str,clausola_rianimatori_nomali_disponibili_list);
                if(!rianimatori_nomali_disponibili)
                    setAtomiCorpoFalsi(clausola_rianimatori_nomali_disponibili_list);
                return rianimatori_nomali_disponibili;
            }
            case("rianimatori_covid_disponibili"):{
                rianimatori_covid_disponibili = getFlag("DISP_RIANIMATORI_COVID");
                setClausoleHowWhynot(rianimatori_covid_disponibili_str,clausola_rianimatori_covid_disponibili_list);
                if(!rianimatori_covid_disponibili)
                    setAtomiCorpoFalsi(clausola_rianimatori_covid_disponibili_list);
                return rianimatori_covid_disponibili;
            }
            case("normali_gestibile"):{
                normali_gestibile = getFlag("NORM_GESTIBILE");
                setClausoleHowWhynot(normali_gestibile_str,clausola_normali_gestibile_list);
                if(!normali_gestibile)
                    setAtomiCorpoFalsi(clausola_normali_gestibile_list);
                return normali_gestibile;
            }
            case("covid_gestibile"):{
                covid_gestibile = getFlag("COVID_GESTIBILE");
                setClausoleHowWhynot(covid_gestibile_str,clausola_covid_gestibile_list);
                if(!covid_gestibile)
                    setAtomiCorpoFalsi(clausola_covid_gestibile_list);
                return covid_gestibile;
            }
            case("ambulanze_disponibili"):{
                ambulanze_disponibili = getFlag("DISP_AMB");
                setClausoleHowWhynot(ambulanze_disponibili_str,clausola_ambulanze_disponibili_list);
                if(!ambulanze_disponibili)
                    setAtomiCorpoFalsi(clausola_ambulanze_disponibili_list);
                return ambulanze_disponibili;
            }
            case("autisti_disponibili"):{
                autisti_disponibili = getFlag("DISP_AUTISTI");
                setClausoleHowWhynot(autisti_disponibili_str,clausola_autisti_disponibili_list);
                if(!autisti_disponibili)
                    setAtomiCorpoFalsi(clausola_autisti_disponibili_list);
                return autisti_disponibili;
            }
            default:{
                System.out.println("ECCEZIONE ASK INTERNO");
                return false;
            }
        }
    }



    public String whyNot(String atomo){ //cliccabile solo se la risposta è false
        //whyNot atomo? : Perché l'atomo è falso?

        //pulisco la lista degli atomi corpo false
        for(int i=0; i<atomiCorpoFalsi.size(); i++){
            atomiCorpoFalsi.remove();
            i--;
        }

        //pulisco la lista delle clausole HowWhynot
        for(int i=0; i<clausoleHowWhynot.size(); i++){
            clausoleHowWhynot.remove();
            i--;
        }

        if(!askInterno(atomo)){ //se è false
            String clausola = parseClausola(clausoleHowWhynot.toString());
            String falsi = parseFalsi(atomiCorpoFalsi.toString());
            return parseWhynot(clausola,falsi);
        }
        return "Non valido per risposte true";
    }

    private String parseWhynot(String clausola,String falsi){
        String output = "";
        output = "Nella clausola : " + clausola + " : Risulta FALSE : " +falsi;
        return output;
    }

    private String parseFalsi(String str1){
        String output = "";
        for(int i=0; i<str1.length(); i++){
            if( str1.charAt(i) == ']' || str1.charAt(i) == '[' ){
                continue;
            }
            else{
                output += str1.charAt(i);
                //System.out.println(listStr.charAt(i));
            }

        }
        return output;
    }

    public String how(String atomo){ //cliccabile solo se la risposta è true
        //how atomo? : Perché l'atomo è vero?

        //pulisco la lista delle clausole HowWhynot
        for(int i=0; i<clausoleHowWhynot.size(); i++){
            clausoleHowWhynot.remove();
            i--;
        }

        if(askInterno(atomo)){ //se è vero
            return parseClausola(clausoleHowWhynot.toString());
        }
        return "Non valido per risposte false";
    }

    private String parseClausola(String listStr){
        String output = "";
        for(int i=0; i<listStr.length(); i++){
            if( listStr.charAt(i) == ',' || listStr.charAt(i) == ']' || listStr.charAt(i) == '[' ){
                continue;
            }
            else{
                output += listStr.charAt(i);
                //System.out.println(listStr.charAt(i));
            }
        }
        return output;
    }

    public Boolean askEsterno(String atomo){
        switch(atomo){
            case("emergenze_normali_erogabili"):{
                emergenze_normali_erogabili = clausola_emergenze_normali_erogabili();
                return emergenze_normali_erogabili;
            }
            case("emergenze_covid_erogabili"):{
                emergenze_covid_erogabili = clausola_emergenze_covid_erogabili();
                return emergenze_covid_erogabili;
            }
            case("ospedale_2_mode"):{
                ospedale_2_mode = clausola_ospedale_2_mode();
                return ospedale_2_mode;
            }
            case("ospedale_normale"):{
                ospedale_normale = clausola_ospedale_normale();
                return ospedale_normale;
            }
            case("ospedale_covid"):{
                ospedale_covid = clausola_ospedale_covid();
                return ospedale_covid;
            }
            case("ospedale_funzionante"):{
                ospedale_funzionante = clausola_ospedale_funzionante();
                return ospedale_funzionante;
            }
            case("personale_normale_disponibile"):{
                personale_normale_disponibile = clausola_personale_normale_disponibile();
                return personale_normale_disponibile;
            }
            case("personale_covid_disponibile"):{
                personale_covid_disponibile = clausola_personale_covid_disponibile();
                return personale_covid_disponibile;
            }
            case("ambulanze_partibili"):{
                ambulanze_partibili = clausola_ambulanze_partibili();
                return ambulanze_partibili;
            }
            case("medici_disponibili"):{
                medici_disponibili = clausola_medici_disponibili();
                return medici_disponibili;
            }
            case("rianimatori_disponibili"):{
                rianimatori_disponibili = clausola_rianimatori_disponibili();
                return rianimatori_disponibili;
            }
            case("posti_normali_disponibili"):{
                posti_normali_disponibili = getFlag("DISP_POSTI_NORMALI");
                return posti_normali_disponibili;
            }
            case("posti_covid_disponibili"):{
                posti_covid_disponibili = getFlag("DISP_POSTI_COVID");
                return posti_covid_disponibili;
            }
            case("medici_normali_disponibili"):{
                medici_normali_disponibili = getFlag("DISP_MEDICI_NORMALI");
                return medici_normali_disponibili;
            }
            case("medici_covid_disponibili"):{
                medici_covid_disponibili = getFlag("DISP_MEDICI_COVID");
                return medici_covid_disponibili;
            }
            case("rianimatori_nomali_disponibili"):{
                rianimatori_nomali_disponibili = getFlag("DISP_RIANIMATORI_NORMALI");
                return rianimatori_nomali_disponibili;
            }
            case("rianimatori_covid_disponibili"):{
                rianimatori_covid_disponibili = getFlag("DISP_RIANIMATORI_COVID");
                return rianimatori_covid_disponibili;
            }
            case("normali_gestibile"):{
                normali_gestibile = getFlag("NORM_GESTIBILE");
                return normali_gestibile;
            }
            case("covid_gestibile"):{
                covid_gestibile = getFlag("COVID_GESTIBILE");
                return covid_gestibile;
            }
            case("ambulanze_disponibili"):{
                ambulanze_disponibili = getFlag("DISP_AMB");
                return ambulanze_disponibili;
            }
            case("autisti_disponibili"):{
                autisti_disponibili = getFlag("DISP_AUTISTI");
                return autisti_disponibili;
            }
            default:{
                System.out.println("ECCEZIONE ASK ESTERNO");
                return false;
            }
        }
    }



    //attributi derivabili
    /*private Boolean paziente_ricoverato = clausola_paziente_ricoverato();
    private Boolean prob_guarire = clausola_probabilità_guarigione();
    private Boolean paz_morto = clausola_paziente_morto();
    private Boolean paz_dimesso = clausola_paziente_dimesso();
    private Boolean medici_malattia = clausola_medici_in_malattia();
    private Boolean medici_ferie = clausola_medici_in_ferie();
    private Boolean autisti_ferie = clausola_autisti_ferie();
    private Boolean autisti_malattia = clausola_autisti _in_malattia();
    private Boolean rianimatori_malattia = clausola_rianimatori_in_malattia();
    private Boolean rianimatori_ferie = clausola_rianimatori_in_ferie();
    private Boolean operatore_ricoverato = clausola_operatore_ricoverato();
    private Boolean data = clausola_giorno_oggi();
    private Boolean mese = clausola_mese_odierno();
    private Boolean natale = clausola_natale();
    private Boolean incidenti = clausola_ci_sono_incidenti();*/

    /*
   public boolean ask(String type) throws Exception
    {
        switch (type)
        {
            case "ricoverati pazienti normali":
                return pazienti_normali();
            case "ambulanza disponibile":
                return AMB_DISP();
            case "autisti disponibili":
                return autisti_disponibili();
            case "pazienti covid":
                return pazienti_covid();
            case "rianimatori covid":
                return rianimatori_covid();
            case "rianimatori normali":
                return rianimatori_normali();
            case "medici covid":
                return medici_covid();
            case "medici normali":
                return medici_normali();
            //...
            default:
                throw new Exception("Invalid Question");
        }
    }*/

/*
    private boolean AMB_DISP() throws Exception
    {
        return !this.getSensor("AMB_DISP").getState();
    }

private boolean pazienti_normali(){
    return !this.getSensor("PAZIENTI_NORMALI").getState();
}
private boolean autisti_disponibili(){
    return !this.getSensor("DISP_AUTISTI").getState();
}
private boolean pazienti_covid(){
    return !this.getSensor("PAZIENTI_COVID").getState();
}
private boolean rianimatori_covid(){
    return !this.getSensor("DISP_RIANIMATORI_COVID").getState();
}
private boolean rianimatori_normali(){
    return !this.getSensor("DISP_RIANIMATORI_NORMALI").getState();
}
private boolean medici_covid(){
    return !this.getSensor("DISP_MEDICICOVID").getState();
}
private boolean medici_normali(){
    return !this.getSensor("DISP_MEDICINORMALI").getState();
}*/
    
  /*  public static boolean main(){
        Integer d=0;
        boolean risposta1= true;
        boolean risposta2= false;
        System.out.println("E' POSSIBILE INTERROGARE LA BASE DI CONOSCENZA CON LE SEGUENTI DOMANDE:");
        System.out.println("1 Ci sono pazienti normali ricoverati?");
        System.out.println("2) Ci sono ambulanze disponibili? ");
        System.out.println("3) Ci sono pazienti COVID ricoverati?");
        System.out.println("4) Sono disponibili dei posti covid?");
        System.out.println("5) Ci sono posti disponibili (no COVID) in ospedale?");
        System.out.println("6) Ci sono autisti disponibili?");
        System.out.println("7) Ci sono rianimatori disponibili?");
        System.out.println("8) Ci sono medici normali disponibili?");
        System.out.println("9) Ci sono medici COVID disponibili?");
        System.out.println("10) Ci sono rianimatori normali disponibili?");
        System.out.println("11) Ci sono rianimatori COVID disponibili?");
        System.out.println("12) E' in funzione l'ospedale?");
        System.out.println("13) Ci sono ambulanze partibili?");
        System.out.println("14) Ci sono medici disponibili?");
        System.out.println("15) C'è del personale COVID disponibile?");
        System.out.println("16) C'è del personale normale disponibile?");
        System.out.println("17) ci sono medici COVID disponibili?");
        
        Scanner input = new Scanner(System.in);
        System.out.println( d);
        String dString;
        
        switch (d){
            case 1: {
              return pazienti_normali();
              }
        
            
        
            case 2: {
                if(osp.getDisp_ambulanze() != 0)
                {
                    return risposta1;
                }
                else {
                    return risposta2;
                }
                        }
            case 3: {
                if(osp.getDisp_postiCovid()<50){
                    return risposta1;
                }
                else {
                    return risposta2;
                }
            }
            case 4: {
                if(osp.getDisp_postiCovid()!=0){
                    return risposta1;
                }
                else{
                    return risposta2;
                }
                }
            case 5:{
                if(osp.getDisp_postiNorm()!=0){
                    return risposta1;
                }
                else {
                    return risposta2;
                }
            }
            case 6:{
                if(osp.getDisp_autisti()!=0){
                    return risposta1;
                }
                else{
                    return risposta2;
                }
                
                
            }
            case 7: {
                if(osp.getDisp_rianimatori()!=0){
                    return risposta1;
                }
                else{
                    return risposta2;
                }
            }
            case 8: {
                if(osp.getDisp_mediciNorm()!=0){
                    return risposta1;
                }
                else{
                    return risposta2;
                }
            }
            case 9: {
                if(osp.getDisp_mediciCovid()!=0){
                    return risposta1;
                }
                else{
                    return risposta2;
                }
            }
        }
        return true;
    }*/



}
