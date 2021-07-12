import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Random;

public class Citta {
    //private Grafo mappa;
    private ArrayList<String> sintomi;
    private ArrayList<Integer> urgenze;
    private LinkedList<Incrocio> luoghiCittà;
    private Ospedale ospedale;

    public void setOspedale(Ospedale osped){
        ospedale = osped;
        ospedale.setLuogo(luoghiCittà.getFirst()); //il primo inserito è ospedale
    }

    public Citta( ) {
        //mappa = new Grafo();

        sintomi = new ArrayList<String>();

        //COVID
        sintomi.add("febbre");
        sintomi.add("affanno");
        sintomi.add("tosse");
        sintomi.add("mal di gola");
        sintomi.add("perdita gusto");
        sintomi.add("perdita olfatto");
        sintomi.add("bronchite");
        sintomi.add("polmonite");
        sintomi.add("difficoltà respiratorie");

        sintomi.add("stanchezza");

        //INFARTO
        sintomi.add("dolore al petto");
        sintomi.add("dolore al braccio sinistro");
        sintomi.add("tachicardia");
        sintomi.add("accelerazione del battito cardiaco");
        sintomi.add("affanno");
        sintomi.add("fitta al petto");
        sintomi.add("ipertensione");

        sintomi.add("dolore");

        //CADUTA
        sintomi.add("urtare la testa");
        sintomi.add("cadere");
        sintomi.add("ferita alla gamba");
        sintomi.add("inciampare");
        sintomi.add("scivolare");
        sintomi.add("livido");
        sintomi.add("ematoma");

        sintomi.add("vista annebbiata"); //DA CAMBIARE

        //SVENIMENTO
        sintomi.add("perdita conoscenza");
        sintomi.add("pressione bassa");
        sintomi.add("giramento di testa");
        sintomi.add("perdita dei sensi");

        sintomi.add("ferito");

        //INCIDENTE AUTOMOBILISTICO
        sintomi.add("scontro tra automobili");
        sintomi.add("incidente in macchina");
        sintomi.add("urtato con la macchina");
        sintomi.add("tamponamento");
        sintomi.add("scontro automobile");

        urgenze = new ArrayList<Integer>();
        urgenze.add(1);
        urgenze.add(2);
        urgenze.add(3);

        luoghiCittà = new LinkedList<Incrocio>();
        costruireMappa();


    }

    private void settareEuristiche(LinkedList<Incrocio> mappaDaSettare,Incrocio destinazione){
        for(int i=0; i<mappaDaSettare.size(); i++)
            mappaDaSettare.get(i).setEuristica(destinazione);
    }

    public LinkedList<Incrocio> getMappaToDestinazione(Incrocio destinazione){
        /* crea una copia della lista dei luoghi della città, imposta le euristiche per ogni nodo
        in base a destinazione
        ritorna la mappa settata per il raggiungimento della destinazione
         */
        LinkedList<Incrocio> mappaSettata = new LinkedList<Incrocio> ();
        mappaSettata = (LinkedList<Incrocio>)luoghiCittà.clone(); //da scrivere il clone
        settareEuristiche(mappaSettata, destinazione);
        return mappaSettata;
    }
    private void costruireMappa(){
//Setting Euristiche

        HashMap<String, Double> euristiche_ospedale = new HashMap<String, Double>();
        euristiche_ospedale.put("Ospedale",0.0);
        euristiche_ospedale.put("Villa Comunale",1.0);
        euristiche_ospedale.put("Palestra",5.0);
        euristiche_ospedale.put("Stadio",4.0);
        euristiche_ospedale.put("Scuola",6.0);
        euristiche_ospedale.put("Supermercato",2.0);
        euristiche_ospedale.put("Chiesa",13.0);
        euristiche_ospedale.put("Comune",7.0);
        euristiche_ospedale.put("Universita'",14.0);
        euristiche_ospedale.put("MediaWord",8.0);
        euristiche_ospedale.put("Hotel",7.0);
        euristiche_ospedale.put("Unitalsi",8.0);
        euristiche_ospedale.put("Aereoporto",4.0);
        euristiche_ospedale.put("Concessionario",8.0);
        euristiche_ospedale.put("Benzinaio",14.0);
        euristiche_ospedale.put("Pescheria",11.0);
        euristiche_ospedale.put("Teatro",11.0);
        euristiche_ospedale.put("Ristorante",4.0);
        euristiche_ospedale.put("Corso Umberto",3.0);
        euristiche_ospedale.put("Lungomare",5.0);
        euristiche_ospedale.put("Via Sparano",15.0);
        euristiche_ospedale.put("Parco Due Giugno",17.0);
        euristiche_ospedale.put("Cinema",12.0);
        euristiche_ospedale.put("Bar",18.0);
        euristiche_ospedale.put("Pizzeria",19.0);
        euristiche_ospedale.put("Piazza Trilussa",5.0);
        Incrocio ospedale = new Incrocio("Ospedale",euristiche_ospedale);
        luoghiCittà.add(ospedale);

        HashMap<String, Double> euristiche_villaComunale = new HashMap<String, Double>();
        euristiche_villaComunale.put("Ospedale",8.0);
        euristiche_villaComunale.put("Villa Comunale",0.0);
        euristiche_villaComunale.put("Palestra",4.0);
        euristiche_villaComunale.put("Stadio",3.0);
        euristiche_villaComunale.put("Scuola",5.0);
        euristiche_villaComunale.put("Supermercato",1.0);
        euristiche_villaComunale.put("Chiesa",12.0);
        euristiche_villaComunale.put("Comune",6.0);
        euristiche_villaComunale.put("Universita'",13.0);
        euristiche_villaComunale.put("MediaWord",7.0);
        euristiche_villaComunale.put("Hotel",6.0);
        euristiche_villaComunale.put("Unitalsi",7.0);
        euristiche_villaComunale.put("Aereoporto",3.0);
        euristiche_villaComunale.put("Concessionario",7.0);
        euristiche_villaComunale.put("Benzinaio",13.0);
        euristiche_villaComunale.put("Pescheria",10.0);
        euristiche_villaComunale.put("Teatro",10.0);
        euristiche_villaComunale.put("Ristorante",3.0);
        euristiche_villaComunale.put("Corso Umberto",2.0);
        euristiche_villaComunale.put("Lungomare",4.0);
        euristiche_villaComunale.put("Via Sparano",14.0);
        euristiche_villaComunale.put("Parco Due Giugno",16.0);
        euristiche_villaComunale.put("Cinema",11.0);
        euristiche_villaComunale.put("Bar",17.0);
        euristiche_villaComunale.put("Pizzeria",18.0);
        euristiche_villaComunale.put("Piazza Trilussa",4.0);
        Incrocio villaComunale = new Incrocio("Villa Comunale",euristiche_villaComunale);
        luoghiCittà.add(villaComunale);

        HashMap<String, Double> euristiche_stadio = new HashMap<String, Double>();
        euristiche_stadio.put("Ospedale",5.0);
        euristiche_stadio.put("Villa Comunale",4.0);
        euristiche_stadio.put("Palestra",1.0);
        euristiche_stadio.put("Stadio",0.0);
        euristiche_stadio.put("Scuola",9.0);
        euristiche_stadio.put("Supermercato",5.0);
        euristiche_stadio.put("Chiesa",9.0);
        euristiche_stadio.put("Comune",8.0);
        euristiche_stadio.put("Universita'",10.0);
        euristiche_stadio.put("MediaWord",4.0);
        euristiche_stadio.put("Hotel",3.0);
        euristiche_stadio.put("Unitalsi",4.0);
        euristiche_stadio.put("Aereoporto",5.0);
        euristiche_stadio.put("Concessionario",7.0);
        euristiche_stadio.put("Benzinaio",10.0);
        euristiche_stadio.put("Pescheria",7.0);
        euristiche_stadio.put("Teatro",10.0);
        euristiche_stadio.put("Ristorante",7.0);
        euristiche_stadio.put("Corso Umberto",6.0);
        euristiche_stadio.put("Lungomare",8.0);
        euristiche_stadio.put("Via Sparano",11.0);
        euristiche_stadio.put("Parco Due Giugno",13.0);
        euristiche_stadio.put("Cinema",8.0);
        euristiche_stadio.put("Bar",14.0);
        euristiche_stadio.put("Pizzeria",15.0);
        euristiche_stadio.put("Piazza Trilussa",8.0);
        Incrocio stadio = new Incrocio("Stadio",euristiche_stadio);
        luoghiCittà.add(stadio);

        HashMap<String, Double> euristiche_palestra= new HashMap<String, Double>();
        euristiche_palestra.put("Ospedale",11.0);
        euristiche_palestra.put("Villa Comunale",3.0);
        euristiche_palestra.put("Palestra",0.0);
        euristiche_palestra.put("Stadio",6.0);
        euristiche_palestra.put("Scuola",8.0);
        euristiche_palestra.put("Supermercato",4.0);
        euristiche_palestra.put("Chiesa",8.0);
        euristiche_palestra.put("Comune",9.0);
        euristiche_palestra.put("Universita'",9.0);
        euristiche_palestra.put("MediaWord",3.0);
        euristiche_palestra.put("Hotel",9.0);
        euristiche_palestra.put("Unitalsi",10.0);
        euristiche_palestra.put("Aereoporto",6.0);
        euristiche_palestra.put("Concessionario",10.0);
        euristiche_palestra.put("Benzinaio",16.0);
        euristiche_palestra.put("Pescheria",13.0);
        euristiche_palestra.put("Teatro",13.0);
        euristiche_palestra.put("Ristorante",6.0);
        euristiche_palestra.put("Corso Umberto",5.0);
        euristiche_palestra.put("Lungomare",7.0);
        euristiche_palestra.put("Via Sparano",17.0);
        euristiche_palestra.put("Parco Due Giugno",19.0);
        euristiche_palestra.put("Cinema",7.0);
        euristiche_palestra.put("Bar",13.0);
        euristiche_palestra.put("Pizzeria",14.0);
        euristiche_palestra.put("Piazza Trilussa",7.0);
        Incrocio palestra = new Incrocio("Palestra",euristiche_palestra);
        luoghiCittà.add(palestra);

        HashMap<String, Double> euristiche_scuola= new HashMap<String, Double>();
        euristiche_scuola.put("Ospedale",5.0);
        euristiche_scuola.put("Villa Comunale",6.0);
        euristiche_scuola.put("Palestra",3.0);
        euristiche_scuola.put("Stadio",9.0);
        euristiche_scuola.put("Scuola",0.0);
        euristiche_scuola.put("Supermercato",7.0);
        euristiche_scuola.put("Chiesa",11.0);
        euristiche_scuola.put("Comune",12.0);
        euristiche_scuola.put("Universita'",12.0);
        euristiche_scuola.put("MediaWord",6.0);
        euristiche_scuola.put("Hotel",12.0);
        euristiche_scuola.put("Unitalsi",13.0);
        euristiche_scuola.put("Aereoporto",9.0);
        euristiche_scuola.put("Concessionario",13.0);
        euristiche_scuola.put("Benzinaio",19.0);
        euristiche_scuola.put("Pescheria",16.0);
        euristiche_scuola.put("Teatro",16.0);
        euristiche_scuola.put("Ristorante",9.0);
        euristiche_scuola.put("Corso Umberto",8.0);
        euristiche_scuola.put("Lungomare",10.0);
        euristiche_scuola.put("Via Sparano",20.0);
        euristiche_scuola.put("Parco Due Giugno",22.0);
        euristiche_scuola.put("Cinema",10.0);
        euristiche_scuola.put("Bar",16.0);
        euristiche_scuola.put("Pizzeria",17.0);
        euristiche_scuola.put("Piazza Trilussa",10.0);
        Incrocio scuola = new Incrocio("Scuola",euristiche_scuola);
        luoghiCittà.add(scuola);


        HashMap<String, Double> euristiche_supermercato= new HashMap<String, Double>();
        euristiche_supermercato.put("Ospedale", 8.0);
        euristiche_supermercato.put("Villa Comunale", 7.0);
        euristiche_supermercato.put("Palestra",4.0);
        euristiche_supermercato.put("Stadio", 3.0);
        euristiche_supermercato.put("Scuola", 4.0);
        euristiche_supermercato.put("Supermercato",0.0);
        euristiche_supermercato.put("Chiesa", 12.0);
        euristiche_supermercato.put("Comune", 5.0);
        euristiche_supermercato.put("Universita'", 13.0);
        euristiche_supermercato.put("MediaWord", 7.0);
        euristiche_supermercato.put("Hotel", 6.0);
        euristiche_supermercato.put("Unitalsi", 7.0);
        euristiche_supermercato.put("Aereoporto", 2.0);
        euristiche_supermercato.put("Concessionario", 6.0);
        euristiche_supermercato.put("Benzinaio",  13.0);
        euristiche_supermercato.put("Pescheria", 10.0);
        euristiche_supermercato.put("Teatro",  9.0);
        euristiche_supermercato.put("Ristorante", 2.0);
        euristiche_supermercato.put("Corso Umberto", 1.0);
        euristiche_supermercato.put("Lungomare", 3.0);
        euristiche_supermercato.put("Via Sparano", 14.0);
        euristiche_supermercato.put("Parco Due Giugno", 16.0);
        euristiche_supermercato.put("Cinema", 11.0);
        euristiche_supermercato.put("Bar", 17.0);
        euristiche_supermercato.put("Pizzeria", 18.0);
        euristiche_supermercato.put("Piazza Trilussa", 3.0);
        Incrocio supermercato = new Incrocio("Supermercato",euristiche_supermercato);
        luoghiCittà.add(supermercato);


        HashMap<String, Double> euristiche_chiesa= new HashMap<String, Double>();
        euristiche_chiesa.put("Ospedale", 10.0);
        euristiche_chiesa.put("Villa Comunale", 11.0);
        euristiche_chiesa.put("Palestra", 8.0);
        euristiche_chiesa.put("Stadio", 7.0);
        euristiche_chiesa.put("Scuola",  5.0);
        euristiche_chiesa.put("Supermercato", 8.0);
        euristiche_chiesa.put("Chiesa",0.0);
        euristiche_chiesa.put("Comune",2.0);
        euristiche_chiesa.put("Universita'", 1.0);
        euristiche_chiesa.put("MediaWord", 11.0);
        euristiche_chiesa.put("Hotel", 10.0);
        euristiche_chiesa.put("Unitalsi", 11.0);
        euristiche_chiesa.put("Aereoporto", 6.0);
        euristiche_chiesa.put("Concessionario", 7.0);
        euristiche_chiesa.put("Benzinaio", 17.0);
        euristiche_chiesa.put("Pescheria", 14.0);
        euristiche_chiesa.put("Teatro", 10.0);
        euristiche_chiesa.put("Ristorante", 6.0);
        euristiche_chiesa.put("Corso Umberto", 5.0);
        euristiche_chiesa.put("Lungomare", 4.0);
        euristiche_chiesa.put("Via Sparano", 18.0);
        euristiche_chiesa.put("Parco Due Giugno", 20.0);
        euristiche_chiesa.put("Cinema", 15.0);
        euristiche_chiesa.put("Bar", 5.0);
        euristiche_chiesa.put("Pizzeria", 22.0);
        euristiche_chiesa.put("Piazza Trilussa", 7.0);
        Incrocio chiesa = new Incrocio("Chiesa",euristiche_chiesa);
        luoghiCittà.add(chiesa);



        HashMap<String, Double> euristiche_comune= new HashMap<String, Double>();
        euristiche_comune.put("Ospedale", 8.0);
        euristiche_comune.put("Villa Comunale",  9.0);
        euristiche_comune.put("Palestra", 6.0);
        euristiche_comune.put("Stadio", 12.0);
        euristiche_comune.put("Scuola", 3.0);
        euristiche_comune.put("Supermercato", 10.0);
        euristiche_comune.put("Chiesa", 14.0);
        euristiche_comune.put("Comune",0.0);
        euristiche_comune.put("Universita'", 15.0);
        euristiche_comune.put("MediaWord", 9.0);
        euristiche_comune.put("Hotel", 15.0);
        euristiche_comune.put("Unitalsi", 16.0);
        euristiche_comune.put("Aereoporto", 12.0);
        euristiche_comune.put("Concessionario", 16.0);
        euristiche_comune.put("Benzinaio", 22.0);
        euristiche_comune.put("Pescheria", 19.0);
        euristiche_comune.put("Teatro", 19.0);
        euristiche_comune.put("Ristorante", 12.0);
        euristiche_comune.put("Corso Umberto", 11.0);
        euristiche_comune.put("Lungomare", 13.0);
        euristiche_comune.put("Via Sparano", 23.0);
        euristiche_comune.put("Parco Due Giugno", 25.0);
        euristiche_comune.put("Cinema", 13.0);
        euristiche_comune.put("Bar", 19.0);
        euristiche_comune.put("Pizzeria", 20.0);
        euristiche_comune.put("Piazza Trilussa", 13.0);
        Incrocio comune = new Incrocio("Comune",euristiche_comune);
        luoghiCittà.add(comune);


        HashMap<String, Double> euristiche_universita= new HashMap<String, Double>();
        euristiche_universita.put("Ospedale", 9.0);
        euristiche_universita.put("Villa Comunale",  10.0);
        euristiche_universita.put("Palestra", 7.0);
        euristiche_universita.put("Stadio", 6.0);
        euristiche_universita.put("Scuola", 4.0);
        euristiche_universita.put("Supermercato",  11.0);
        euristiche_universita.put("Chiesa", 15.0);
        euristiche_universita.put("Comune", 1.0);
        euristiche_universita.put("Universita'",0.0);
        euristiche_universita.put("MediaWord", 10.0);
        euristiche_universita.put("Hotel", 9.0);
        euristiche_universita.put("Unitalsi", 10.0);
        euristiche_universita.put("Aereoporto", 5.0);
        euristiche_universita.put("Concessionario", 6.0);
        euristiche_universita.put("Benzinaio", 16.0);
        euristiche_universita.put("Pescheria", 13.0);
        euristiche_universita.put("Teatro", 9.0);
        euristiche_universita.put("Ristorante",  5.0);
        euristiche_universita.put("Corso Umberto", 4.0);
        euristiche_universita.put("Lungomare", 3.0);
        euristiche_universita.put("Via Sparano", 17.0);
        euristiche_universita.put("Parco Due Giugno",  19.0);
        euristiche_universita.put("Cinema", 14.0);
        euristiche_universita.put("Bar", 20.0);
        euristiche_universita.put("Pizzeria", 21.0);
        euristiche_universita.put("Piazza Trilussa", 6.0);
        Incrocio universita = new Incrocio("Universita'",euristiche_universita);
        luoghiCittà.add(universita);

        HashMap<String, Double> euristiche_mediaword= new HashMap<String, Double>();
        euristiche_mediaword.put("Ospedale", 15.0);
        euristiche_mediaword.put("Villa Comunale", 16.0);
        euristiche_mediaword.put("Palestra", 13.0);
        euristiche_mediaword.put("Stadio", 12.0);
        euristiche_mediaword.put("Scuola",  10.0);
        euristiche_mediaword.put("Supermercato", 13.0);
        euristiche_mediaword.put("Chiesa", 5.0);
        euristiche_mediaword.put("Comune",7.0);
        euristiche_mediaword.put("Universita'", 6.0);
        euristiche_mediaword.put("MediaWord", 0.0);
        euristiche_mediaword.put("Hotel", 15.0);
        euristiche_mediaword.put("Unitalsi", 16.0);
        euristiche_mediaword.put("Aereoporto", 11.0);
        euristiche_mediaword.put("Concessionario", 12.0);
        euristiche_mediaword.put("Benzinaio", 22.0);
        euristiche_mediaword.put("Pescheria", 19.0);
        euristiche_mediaword.put("Teatro", 15.0);
        euristiche_mediaword.put("Ristorante", 11.0);
        euristiche_mediaword.put("Corso Umberto", 10.0);
        euristiche_mediaword.put("Lungomare", 9.0);
        euristiche_mediaword.put("Via Sparano", 23.0);
        euristiche_mediaword.put("Parco Due Giugno", 25.0);
        euristiche_mediaword.put("Cinema",20.0);
        euristiche_mediaword.put("Bar", 10.0);
        euristiche_mediaword.put("Pizzeria", 27.0);
        euristiche_mediaword.put("Piazza Trilussa", 12.0);
        Incrocio mediaword = new Incrocio("MediaWord",euristiche_mediaword);
        luoghiCittà.add(mediaword);

        HashMap<String, Double> euristiche_hotel = new HashMap<String, Double>();
        euristiche_hotel.put("Ospedale", 8.0);
        euristiche_hotel.put("Villa Comunale", 7.0);
        euristiche_hotel.put("Palestra", 4.0);
        euristiche_hotel.put("Stadio", 3.0);
        euristiche_hotel.put("Scuola", 8.0);
        euristiche_hotel.put("Supermercato",8.0);
        euristiche_hotel.put("Chiesa", 12.0);
        euristiche_hotel.put("Comune", 5.0);
        euristiche_hotel.put("Universita'", 13.0);
        euristiche_hotel.put("MediaWord", 7.0);
        euristiche_hotel.put("Hotel",0.0);
        euristiche_hotel.put("Unitalsi", 1.0);
        euristiche_hotel.put("Aereoporto", 2.0);
        euristiche_hotel.put("Concessionario", 4.0);
        euristiche_hotel.put("Benzinaio", 7.0);
        euristiche_hotel.put("Pescheria", 4.0);
        euristiche_hotel.put("Teatro", 7.0);
        euristiche_hotel.put("Ristorante", 10.0);
        euristiche_hotel.put("Corso Umberto", 9.0);
        euristiche_hotel.put("Lungomare", 11.0);
        euristiche_hotel.put("Via Sparano", 8.0);
        euristiche_hotel.put("Parco Due Giugno", 10.0);
        euristiche_hotel.put("Cinema", 11.0);
        euristiche_hotel.put("Bar", 17.0);
        euristiche_hotel.put("Pizzeria", 18.0);
        euristiche_hotel.put("Piazza Trilussa", 11.0);
        Incrocio hotel = new Incrocio("Hotel",euristiche_hotel);
        luoghiCittà.add(hotel);

        HashMap<String, Double> euristiche_unitalsi= new HashMap<String, Double>();
        euristiche_unitalsi.put("Ospedale",7.0);
        euristiche_unitalsi.put("Villa Comunale", 6.0);
        euristiche_unitalsi.put("Palestra", 3.0);
        euristiche_unitalsi.put("Stadio", 2.0);
        euristiche_unitalsi.put("Scuola", 7.0);
        euristiche_unitalsi.put("Supermercato", 7.0);
        euristiche_unitalsi.put("Chiesa", 11.0);
        euristiche_unitalsi.put("Comune", 4.0);
        euristiche_unitalsi.put("Universita'", 12.0);
        euristiche_unitalsi.put("MediaWord", 6.0);
        euristiche_unitalsi.put("Hotel", 5.0);
        euristiche_unitalsi.put("Unitalsi",0.0);
        euristiche_unitalsi.put("Aereoporto", 1.0);
        euristiche_unitalsi.put("Concessionario",3.0);
        euristiche_unitalsi.put("Benzinaio", 6.0);
        euristiche_unitalsi.put("Pescheria", 3.0);
        euristiche_unitalsi.put("Teatro", 6.0);
        euristiche_unitalsi.put("Ristorante",9.0);
        euristiche_unitalsi.put("Corso Umberto",8.0);
        euristiche_unitalsi.put("Lungomare", 10.0);
        euristiche_unitalsi.put("Via Sparano", 7.0);
        euristiche_unitalsi.put("Parco Due Giugno",  9.0);
        euristiche_unitalsi.put("Cinema", 10.0);
        euristiche_unitalsi.put("Bar", 16.0);
        euristiche_unitalsi.put("Pizzeria", 17.0);
        euristiche_unitalsi.put("Piazza Trilussa", 10.0);
        Incrocio unitalsi = new Incrocio("Unitalsi",euristiche_unitalsi);
        luoghiCittà.add(unitalsi);

        HashMap<String, Double> euristiche_aereoporto= new HashMap<String, Double>();
        euristiche_aereoporto.put("Ospedale", 6.0);
        euristiche_aereoporto.put("Villa Comunale",5.0);
        euristiche_aereoporto.put("Palestra", 2.0);
        euristiche_aereoporto.put("Stadio", 1.0);
        euristiche_aereoporto.put("Scuola",  10.0);
        euristiche_aereoporto.put("Supermercato", 6.0);
        euristiche_aereoporto.put("Chiesa", 10.0);
        euristiche_aereoporto.put("Comune", 9.0);
        euristiche_aereoporto.put("Universita'", 11.0);
        euristiche_aereoporto.put("MediaWord", 5.0);
        euristiche_aereoporto.put("Hotel", 4.0);
        euristiche_aereoporto.put("Unitalsi", 5.0);
        euristiche_aereoporto.put("Aereoporto",0.0);
        euristiche_aereoporto.put("Concessionario", 8.0);
        euristiche_aereoporto.put("Benzinaio", 11.0);
        euristiche_aereoporto.put("Pescheria", 8.0);
        euristiche_aereoporto.put("Teatro", 11.0);
        euristiche_aereoporto.put("Ristorante", 8.0);
        euristiche_aereoporto.put("Corso Umberto", 7.0);
        euristiche_aereoporto.put("Lungomare", 9.0);
        euristiche_aereoporto.put("Via Sparano", 12.0);
        euristiche_aereoporto.put("Parco Due Giugno", 14.0);
        euristiche_aereoporto.put("Cinema", 9.0);
        euristiche_aereoporto.put("Bar", 15.0);
        euristiche_aereoporto.put("Pizzeria", 16.0);
        euristiche_aereoporto.put("Piazza Trilussa", 9.0);
        Incrocio aereoporto = new Incrocio("Aereoporto",euristiche_aereoporto);
        luoghiCittà.add(aereoporto);

        HashMap<String, Double> euristiche_concessionario= new HashMap<String, Double>();
        euristiche_concessionario.put("Ospedale",9.0);
        euristiche_concessionario.put("Villa Comunale", 10.0);
        euristiche_concessionario.put("Palestra", 7.0);
        euristiche_concessionario.put("Stadio", 13.0);
        euristiche_concessionario.put("Scuola", 4.0);
        euristiche_concessionario.put("Supermercato", 11.0);
        euristiche_concessionario.put("Chiesa", 15.0);
        euristiche_concessionario.put("Comune", 1.0);
        euristiche_concessionario.put("Universita'", 16.0);
        euristiche_concessionario.put("MediaWord",10.0);
        euristiche_concessionario.put("Hotel", 16.0);
        euristiche_concessionario.put("Unitalsi", 17.0);
        euristiche_concessionario.put("Aereoporto", 13.0);
        euristiche_concessionario.put("Concessionario",0.0);
        euristiche_concessionario.put("Benzinaio",23.0);
        euristiche_concessionario.put("Pescheria", 20.0);
        euristiche_concessionario.put("Teatro", 3.0);
        euristiche_concessionario.put("Ristorante", 13.0);
        euristiche_concessionario.put("Corso Umberto", 12.0);
        euristiche_concessionario.put("Lungomare", 14.0);
        euristiche_concessionario.put("Via Sparano", 24.0);
        euristiche_concessionario.put("Parco Due Giugno", 26.0);
        euristiche_concessionario.put("Cinema", 14.0);
        euristiche_concessionario.put("Bar", 20.0);
        euristiche_concessionario.put("Pizzeria", 21.0);
        euristiche_concessionario.put("Piazza Trilussa", 14.0);
        Incrocio concessionario = new Incrocio("Concessionario",euristiche_concessionario);
        luoghiCittà.add(concessionario);

        HashMap<String, Double> euristiche_benzinaio= new HashMap<String, Double>();
        euristiche_benzinaio.put("Ospedale", 10.0);
        euristiche_benzinaio.put("Villa Comunale", 9.0);
        euristiche_benzinaio.put("Palestra", 6.0);
        euristiche_benzinaio.put("Stadio", 5.0);
        euristiche_benzinaio.put("Scuola", 10.0);
        euristiche_benzinaio.put("Supermercato", 10.0);
        euristiche_benzinaio.put("Chiesa", 14.0);
        euristiche_benzinaio.put("Comune", 7.0);
        euristiche_benzinaio.put("Universita'", 15.0);
        euristiche_benzinaio.put("MediaWord", 9.0);
        euristiche_benzinaio.put("Hotel", 8.0);
        euristiche_benzinaio.put("Unitalsi", 3.0);
        euristiche_benzinaio.put("Aereoporto", 4.0);
        euristiche_benzinaio.put("Concessionario", 6.0);
        euristiche_benzinaio.put("Benzinaio",0.0);
        euristiche_benzinaio.put("Pescheria", 2.0);
        euristiche_benzinaio.put("Teatro", 9.0);
        euristiche_benzinaio.put("Ristorante", 12.0);
        euristiche_benzinaio.put("Corso Umberto",11.0);
        euristiche_benzinaio.put("Lungomare", 13.0);
        euristiche_benzinaio.put("Via Sparano", 1.0);
        euristiche_benzinaio.put("Parco Due Giugno", 3.0);
        euristiche_benzinaio.put("Cinema", 10.0);
        euristiche_benzinaio.put("Bar", 19.0);
        euristiche_benzinaio.put("Pizzeria", 17.0);
        euristiche_benzinaio.put("Piazza Trilussa", 13.0);
        Incrocio benzinaio = new Incrocio("Benzinaio",euristiche_benzinaio);
        luoghiCittà.add(benzinaio);

        HashMap<String, Double> euristiche_pescheria= new HashMap<String, Double>();
        euristiche_pescheria.put("Ospedale", 13.0);
        euristiche_pescheria.put("Villa Comunale", 12.0);
        euristiche_pescheria.put("Palestra", 9.0);
        euristiche_pescheria.put("Stadio", 8.0);
        euristiche_pescheria.put("Scuola", 13.0);
        euristiche_pescheria.put("Supermercato", 13.0);
        euristiche_pescheria.put("Chiesa", 17.0);
        euristiche_pescheria.put("Comune", 10.0);
        euristiche_pescheria.put("Universita'", 18.0);
        euristiche_pescheria.put("MediaWord", 12.0);
        euristiche_pescheria.put("Hotel", 11.0);
        euristiche_pescheria.put("Unitalsi", 6.0);
        euristiche_pescheria.put("Aereoporto", 7.0);
        euristiche_pescheria.put("Concessionario", 9.0);
        euristiche_pescheria.put("Benzinaio", 3.0);
        euristiche_pescheria.put("Pescheria",0.0);
        euristiche_pescheria.put("Teatro", 12.0);
        euristiche_pescheria.put("Ristorante", 15.0);
        euristiche_pescheria.put("Corso Umberto", 14.0);
        euristiche_pescheria.put("Lungomare", 16.0);
        euristiche_pescheria.put("Via Sparano", 4.0);
        euristiche_pescheria.put("Parco Due Giugno", 6.0);
        euristiche_pescheria.put("Cinema", 13.0);
        euristiche_pescheria.put("Bar", 22.0);
        euristiche_pescheria.put("Pizzeria", 20.0);
        euristiche_pescheria.put("Piazza Trilussa", 16.0);
        Incrocio pescheria = new Incrocio("Pescheria",euristiche_pescheria);
        luoghiCittà.add(pescheria);

        HashMap<String, Double> euristiche_teatro= new HashMap<String, Double>();
        euristiche_teatro.put("Ospedale", 11.0);
        euristiche_teatro.put("Villa Comunale",12.0);
        euristiche_teatro.put("Palestra", 9.0);
        euristiche_teatro.put("Stadio", 15.0);
        euristiche_teatro.put("Scuola", 6.0);
        euristiche_teatro.put("Supermercato", 13.0);
        euristiche_teatro.put("Chiesa", 17.0);
        euristiche_teatro.put("Comune", 3.0);
        euristiche_teatro.put("Universita'", 18.0);
        euristiche_teatro.put("MediaWord", 12.0);
        euristiche_teatro.put("Hotel", 18.0);
        euristiche_teatro.put("Unitalsi", 19.0);
        euristiche_teatro.put("Aereoporto", 15.0);
        euristiche_teatro.put("Concessionario", 19.0);
        euristiche_teatro.put("Benzinaio", 25.0);
        euristiche_teatro.put("Pescheria", 22.0);
        euristiche_teatro.put("Teatro",0.0);
        euristiche_teatro.put("Ristorante", 15.0);
        euristiche_teatro.put("Corso Umberto", 14.0);
        euristiche_teatro.put("Lungomare", 16.0);
        euristiche_teatro.put("Via Sparano", 26.0);
        euristiche_teatro.put("Parco Due Giugno", 28.0);
        euristiche_teatro.put("Cinema", 16.0);
        euristiche_teatro.put("Bar", 22.0);
        euristiche_teatro.put("Pizzeria", 23.0);
        euristiche_teatro.put("Piazza Trilussa", 16.0);
        Incrocio teatro = new Incrocio("Teatro",euristiche_teatro);
        luoghiCittà.add(teatro);

        HashMap<String, Double> euristiche_ristorante= new HashMap<String, Double>();
        euristiche_ristorante.put("Ospedale", 7.0);
        euristiche_ristorante.put("Villa Comunale",8.0);
        euristiche_ristorante.put("Palestra", 5.0);
        euristiche_ristorante.put("Stadio", 4.0);
        euristiche_ristorante.put("Scuola", 2.0);
        euristiche_ristorante.put("Supermercato", 9.0);
        euristiche_ristorante.put("Chiesa", 13.0);
        euristiche_ristorante.put("Comune", 5.0);
        euristiche_ristorante.put("Universita'", 14.0);
        euristiche_ristorante.put("MediaWord", 8.0);
        euristiche_ristorante.put("Hotel", 7.0);
        euristiche_ristorante.put("Unitalsi", 8.0);
        euristiche_ristorante.put("Aereoporto", 3.0);
        euristiche_ristorante.put("Concessionario", 4.0);
        euristiche_ristorante.put("Benzinaio", 14.0);
        euristiche_ristorante.put("Pescheria", 11.0);
        euristiche_ristorante.put("Teatro", 7.0);
        euristiche_ristorante.put("Ristorante",0.0);
        euristiche_ristorante.put("Corso Umberto", 2.0);
        euristiche_ristorante.put("Lungomare", 1.0);
        euristiche_ristorante.put("Via Sparano", 15.0);
        euristiche_ristorante.put("Parco Due Giugno", 17.0);
        euristiche_ristorante.put("Cinema", 12.0);
        euristiche_ristorante.put("Bar", 18.0);
        euristiche_ristorante.put("Pizzeria", 19.0);
        euristiche_ristorante.put("Piazza Trilussa", 1.0);
        Incrocio ristorante = new Incrocio("Ristorante",euristiche_ristorante);
        luoghiCittà.add(ristorante);

        HashMap<String, Double> euristiche_corsoUmberto= new HashMap<String, Double>();
        euristiche_corsoUmberto.put("Ospedale", 7.0);
        euristiche_corsoUmberto.put("Villa Comunale",6.0);
        euristiche_corsoUmberto.put("Palestra", 3.0);
        euristiche_corsoUmberto.put("Stadio", 2.0);
        euristiche_corsoUmberto.put("Scuola", 3.0);
        euristiche_corsoUmberto.put("Supermercato", 7.0);
        euristiche_corsoUmberto.put("Chiesa", 11.0);
        euristiche_corsoUmberto.put("Comune", 6.0);
        euristiche_corsoUmberto.put("Universita'", 12.0);
        euristiche_corsoUmberto.put("MediaWord", 6.0);
        euristiche_corsoUmberto.put("Hotel", 5.0);
        euristiche_corsoUmberto.put("Unitalsi", 6.0);
        euristiche_corsoUmberto.put("Aereoporto", 1.0);
        euristiche_corsoUmberto.put("Concessionario", 5.0);
        euristiche_corsoUmberto.put("Benzinaio", 12.0);
        euristiche_corsoUmberto.put("Pescheria", 9.0);
        euristiche_corsoUmberto.put("Teatro", 8.0);
        euristiche_corsoUmberto.put("Ristorante", 1.0);
        euristiche_corsoUmberto.put("Corso Umberto",0.0);
        euristiche_corsoUmberto.put("Lungomare",  2.0);
        euristiche_corsoUmberto.put("Via Sparano", 13.0);
        euristiche_corsoUmberto.put("Parco Due Giugno", 15.0);
        euristiche_corsoUmberto.put("Cinema", 10.0);
        euristiche_corsoUmberto.put("Bar", 16.0);
        euristiche_corsoUmberto.put("Pizzeria", 17.0);
        euristiche_corsoUmberto.put("Piazza Trilussa", 2.0);
        Incrocio corsoUmberto = new Incrocio("Corso Umberto",euristiche_corsoUmberto);
        luoghiCittà.add(corsoUmberto);

        HashMap<String, Double> euristiche_lungomare= new HashMap<String, Double>();
        euristiche_lungomare.put("Ospedale", 8.0);
        euristiche_lungomare.put("Villa Comunale", 7.0);
        euristiche_lungomare.put("Palestra", 4.0);
        euristiche_lungomare.put("Stadio", 3.0);
        euristiche_lungomare.put("Scuola", 4.0);
        euristiche_lungomare.put("Supermercato", 8.0);
        euristiche_lungomare.put("Chiesa", 12.0);
        euristiche_lungomare.put("Comune", 4.0);
        euristiche_lungomare.put("Universita'", 13.0);
        euristiche_lungomare.put("MediaWord", 7.0);
        euristiche_lungomare.put("Hotel", 6.0);
        euristiche_lungomare.put("Unitalsi", 7.0);
        euristiche_lungomare.put("Aereoporto", 2.0);
        euristiche_lungomare.put("Concessionario", 3.0);
        euristiche_lungomare.put("Benzinaio", 13.0);
        euristiche_lungomare.put("Pescheria", 10.0);
        euristiche_lungomare.put("Teatro", 6.0);
        euristiche_lungomare.put("Ristorante", 2.0);
        euristiche_lungomare.put("Corso Umberto", 1.0);
        euristiche_lungomare.put("Lungomare",0.0);
        euristiche_lungomare.put("Via Sparano", 14.0);
        euristiche_lungomare.put("Parco Due Giugno", 16.0);
        euristiche_lungomare.put("Cinema", 11.0);
        euristiche_lungomare.put("Bar", 17.0);
        euristiche_lungomare.put("Pizzeria", 18.0);
        euristiche_lungomare.put("Piazza Trilussa", 3.0);
        Incrocio lungomare = new Incrocio("Lungomare",euristiche_lungomare);
        luoghiCittà.add(lungomare);

        HashMap<String, Double> euristiche_viaSparano= new HashMap<String, Double>();
        euristiche_viaSparano.put("Ospedale", 14.0);
        euristiche_viaSparano.put("Villa Comunale", 13.0);
        euristiche_viaSparano.put("Palestra", 10.0);
        euristiche_viaSparano.put("Stadio", 9.0);
        euristiche_viaSparano.put("Scuola", 14.0);
        euristiche_viaSparano.put("Supermercato", 14.0);
        euristiche_viaSparano.put("Chiesa", 18.0);
        euristiche_viaSparano.put("Comune", 11.0);
        euristiche_viaSparano.put("Universita'", 19.0);
        euristiche_viaSparano.put("MediaWord", 13.0);
        euristiche_viaSparano.put("Hotel", 12.0);
        euristiche_viaSparano.put("Unitalsi", 7.0);
        euristiche_viaSparano.put("Aereoporto", 8.0);
        euristiche_viaSparano.put("Concessionario", 10.0);
        euristiche_viaSparano.put("Benzinaio", 4.0);
        euristiche_viaSparano.put("Pescheria", 1.0);
        euristiche_viaSparano.put("Teatro", 13.0);
        euristiche_viaSparano.put("Ristorante", 16.0);
        euristiche_viaSparano.put("Corso Umberto", 15.0);
        euristiche_viaSparano.put("Lungomare", 17.0);
        euristiche_viaSparano.put("Via Sparano",0.0);
        euristiche_viaSparano.put("Parco Due Giugno", 7.0);
        euristiche_viaSparano.put("Cinema", 14.0);
        euristiche_viaSparano.put("Bar", 23.0);
        euristiche_viaSparano.put("Pizzeria", 21.0);
        euristiche_viaSparano.put("Piazza Trilussa", 17.0);
        Incrocio viaSparano= new Incrocio("Via Sparano",euristiche_viaSparano);
        luoghiCittà.add(viaSparano);

        HashMap<String, Double> euristiche_parcoDueGiugno= new HashMap<String, Double>();
        euristiche_parcoDueGiugno.put("Ospedale",22.0);
        euristiche_parcoDueGiugno.put("Villa Comunale",23.0);
        euristiche_parcoDueGiugno.put("Palestra",20.0);
        euristiche_parcoDueGiugno.put("Stadio",19.0);
        euristiche_parcoDueGiugno.put("Scuola",17.0);
        euristiche_parcoDueGiugno.put("Supermercato",20.0);
        euristiche_parcoDueGiugno.put("Chiesa",12.0);
        euristiche_parcoDueGiugno.put("Comune",14.0);
        euristiche_parcoDueGiugno.put("Universita'",13.0);
        euristiche_parcoDueGiugno.put("MediaWord", 7.0);
        euristiche_parcoDueGiugno.put("Hotel",22.0);
        euristiche_parcoDueGiugno.put("Unitalsi",23.0);
        euristiche_parcoDueGiugno.put("Aereoporto",18.0);
        euristiche_parcoDueGiugno.put("Concessionario",19.0);
        euristiche_parcoDueGiugno.put("Benzinaio",29.0);
        euristiche_parcoDueGiugno.put("Pescheria",26.0);
        euristiche_parcoDueGiugno.put("Teatro",22.0);
        euristiche_parcoDueGiugno.put("Ristorante",18.0);
        euristiche_parcoDueGiugno.put("Corso Umberto",17.0);
        euristiche_parcoDueGiugno.put("Lungomare",16.0);
        euristiche_parcoDueGiugno.put("Via Sparano",30.0);
        euristiche_parcoDueGiugno.put("Parco Due Giugno",0.0);
        euristiche_parcoDueGiugno.put("Cinema",7.0);
        euristiche_parcoDueGiugno.put("Bar",17.0);
        euristiche_parcoDueGiugno.put("Pizzeria",14.0);
        euristiche_parcoDueGiugno.put("Piazza Trilussa",19.0);
        Incrocio parcoDueGiugno = new Incrocio("Parco Due Giugno",euristiche_parcoDueGiugno);
        luoghiCittà.add(parcoDueGiugno);

        HashMap<String, Double> euristiche_cinema= new HashMap<String, Double>();
        euristiche_cinema.put("Ospedale",20.0);
        euristiche_cinema.put("Villa Comunale",21.0);
        euristiche_cinema.put("Palestra",18.0);
        euristiche_cinema.put("Stadio",24.0);
        euristiche_cinema.put("Scuola",15.0);
        euristiche_cinema.put("Supermercato",22.0);
        euristiche_cinema.put("Chiesa",26.0);
        euristiche_cinema.put("Comune",26.0);
        euristiche_cinema.put("Universita'",27.0);
        euristiche_cinema.put("MediaWord",21.0);
        euristiche_cinema.put("Hotel",21.0);
        euristiche_cinema.put("Unitalsi",22.0);
        euristiche_cinema.put("Aereoporto",23.0);
        euristiche_cinema.put("Concessionario",25.0);
        euristiche_cinema.put("Benzinaio",28.0);
        euristiche_cinema.put("Pescheria",25.0);
        euristiche_cinema.put("Teatro",28.0);
        euristiche_cinema.put("Ristorante",24.0);
        euristiche_cinema.put("Corso Umberto",23.0);
        euristiche_cinema.put("Lungomare",25.0);
        euristiche_cinema.put("Via Sparano",29.0);
        euristiche_cinema.put("Parco Due Giugno",31.0);
        euristiche_cinema.put("Cinema",0.0);
        euristiche_cinema.put("Bar",31.0);
        euristiche_cinema.put("Pizzeria",7.0);
        euristiche_cinema.put("Piazza Trilussa",14.0);
        Incrocio cinema = new Incrocio("Cinema",euristiche_cinema);
        luoghiCittà.add(cinema);

        HashMap<String, Double> euristiche_bar= new HashMap<String, Double>();
        euristiche_bar.put("Ospedale",11.0);
        euristiche_bar.put("Villa Comunale",10.0);
        euristiche_bar.put("Palestra",7.0);
        euristiche_bar.put("Stadio",6.0);
        euristiche_bar.put("Scuola",7.0);
        euristiche_bar.put("Supermercato",3.0);
        euristiche_bar.put("Chiesa",15.0);
        euristiche_bar.put("Comune",8.0);
        euristiche_bar.put("Universita'",16.0);
        euristiche_bar.put("MediaWord",10.0);
        euristiche_bar.put("Hotel",9.0);
        euristiche_bar.put("Unitalsi",10.0);
        euristiche_bar.put("Aereoporto",5.0);
        euristiche_bar.put("Concessionario",9.0);
        euristiche_bar.put("Benzinaio",16.0);
        euristiche_bar.put("Pescheria",13.0);
        euristiche_bar.put("Teatro",12.0);
        euristiche_bar.put("Ristorante",5.0);
        euristiche_bar.put("Corso Umberto",4.0);
        euristiche_bar.put("Lungomare",6.0);
        euristiche_bar.put("Via Sparano",17.0);
        euristiche_bar.put("Parco Due Giugno",19.0);
        euristiche_bar.put("Cinema",14.0);
        euristiche_bar.put("Bar",0.0);
        euristiche_bar.put("Pizzeria",21.0);
        euristiche_bar.put("Piazza Trilussa",6.0);
        Incrocio bar = new Incrocio("Bar",euristiche_bar);
        luoghiCittà.add(bar);

        HashMap<String, Double> euristiche_pizzeria= new HashMap<String, Double>();
        euristiche_pizzeria.put("Ospedale",13.0);
        euristiche_pizzeria.put("Villa Comunale",14.0);
        euristiche_pizzeria.put("Palestra",11.0);
        euristiche_pizzeria.put("Stadio",17.0);
        euristiche_pizzeria.put("Scuola",8.0);
        euristiche_pizzeria.put("Supermercato",15.0);
        euristiche_pizzeria.put("Chiesa",19.0);
        euristiche_pizzeria.put("Comune",19.0);
        euristiche_pizzeria.put("Universita'",20.0);
        euristiche_pizzeria.put("MediaWord",14.0);
        euristiche_pizzeria.put("Hotel",14.0);
        euristiche_pizzeria.put("Unitalsi",15.0);
        euristiche_pizzeria.put("Aereoporto",16.0);
        euristiche_pizzeria.put("Concessionario",18.0);
        euristiche_pizzeria.put("Benzinaio",21.0);
        euristiche_pizzeria.put("Pescheria",18.0);
        euristiche_pizzeria.put("Teatro",21.0);
        euristiche_pizzeria.put("Ristorante",17.0);
        euristiche_pizzeria.put("Corso Umberto",16.0);
        euristiche_pizzeria.put("Lungomare",18.0);
        euristiche_pizzeria.put("Via Sparano",22.0);
        euristiche_pizzeria.put("Parco Due Giugno",24.0);
        euristiche_pizzeria.put("Cinema",18.0);
        euristiche_pizzeria.put("Bar",24.0);
        euristiche_pizzeria.put("Pizzeria",0.0);
        euristiche_pizzeria.put("Piazza Trilussa",7.0);
        Incrocio pizzeria = new Incrocio("Pizzeria",euristiche_pizzeria);
        luoghiCittà.add(pizzeria);

        HashMap<String, Double> euristiche_piazza_trilussa= new HashMap<String, Double>();
        euristiche_piazza_trilussa.put("Ospedale",6.0);
        euristiche_piazza_trilussa.put("Villa Comunale",7.0);
        euristiche_piazza_trilussa.put("Palestra",4.0);
        euristiche_piazza_trilussa.put("Stadio",10.0);
        euristiche_piazza_trilussa.put("Scuola",1.0);
        euristiche_piazza_trilussa.put("Supermercato",8.0);
        euristiche_piazza_trilussa.put("Chiesa",12.0);
        euristiche_piazza_trilussa.put("Comune",12.0);
        euristiche_piazza_trilussa.put("Universita'",13.0);
        euristiche_piazza_trilussa.put("MediaWord",7.0);
        euristiche_piazza_trilussa.put("Hotel",7.0);
        euristiche_piazza_trilussa.put("Unitalsi",8.0);
        euristiche_piazza_trilussa.put("Aereoporto",9.0);
        euristiche_piazza_trilussa.put("Concessionario",11.0);
        euristiche_piazza_trilussa.put("Benzinaio",14.0);
        euristiche_piazza_trilussa.put("Pescheria",11.0);
        euristiche_piazza_trilussa.put("Teatro",14.0);
        euristiche_piazza_trilussa.put("Ristorante",10.0);
        euristiche_piazza_trilussa.put("Corso Umberto",9.0);
        euristiche_piazza_trilussa.put("Lungomare",11.0);
        euristiche_piazza_trilussa.put("Via Sparano",15.0);
        euristiche_piazza_trilussa.put("Parco Due Giugno",17.0);
        euristiche_piazza_trilussa.put("Cinema",11.0);
        euristiche_piazza_trilussa.put("Bar",17.0);
        euristiche_piazza_trilussa.put("Pizzeria",18.0);
        euristiche_piazza_trilussa.put("Piazza Trilussa",0.0);
        Incrocio piazza_trilussa = new Incrocio("Piazza Trilussa",euristiche_piazza_trilussa);
        luoghiCittà.add(piazza_trilussa);

// Congiungere i nodi(incroci) del grafo(città) con gli archi(strade) pesando tali archi con numeri interi casuali
        ospedale.addStradaAdiacente(villaComunale,randomTraffic());

        villaComunale.addStradaAdiacente(stadio,randomTraffic());
        villaComunale.addStradaAdiacente(supermercato,randomTraffic());

        stadio.addStradaAdiacente(ospedale,randomTraffic());
        stadio.addStradaAdiacente(palestra,randomTraffic());
        stadio.addStradaAdiacente(hotel,randomTraffic());

        palestra.addStradaAdiacente(villaComunale,randomTraffic());
        palestra.addStradaAdiacente(cinema,randomTraffic());
        palestra.addStradaAdiacente(mediaword,randomTraffic());

        scuola.addStradaAdiacente(ospedale,randomTraffic());
        scuola.addStradaAdiacente(palestra,randomTraffic());

        supermercato.addStradaAdiacente(comune,randomTraffic());
        supermercato.addStradaAdiacente(corsoUmberto,randomTraffic());

        chiesa.addStradaAdiacente(bar,randomTraffic());
        chiesa.addStradaAdiacente(universita,randomTraffic());

        comune.addStradaAdiacente(scuola,randomTraffic());

        universita.addStradaAdiacente(comune,randomTraffic());
        universita.addStradaAdiacente(lungomare,randomTraffic());

        mediaword.addStradaAdiacente(chiesa,randomTraffic());

        hotel.addStradaAdiacente(unitalsi,randomTraffic());

        unitalsi.addStradaAdiacente(aereoporto,randomTraffic());
        unitalsi.addStradaAdiacente(pescheria,randomTraffic());

        aereoporto.addStradaAdiacente(stadio,randomTraffic());

        concessionario.addStradaAdiacente(comune,randomTraffic());
        concessionario.addStradaAdiacente(teatro,randomTraffic());

        benzinaio.addStradaAdiacente(unitalsi,randomTraffic());
        benzinaio.addStradaAdiacente(viaSparano,randomTraffic());
        benzinaio.addStradaAdiacente(parcoDueGiugno,randomTraffic());

        pescheria.addStradaAdiacente(benzinaio,randomTraffic());

        teatro.addStradaAdiacente(comune,randomTraffic());

        ristorante.addStradaAdiacente(lungomare,randomTraffic());
        ristorante.addStradaAdiacente(piazza_trilussa,randomTraffic());

        corsoUmberto.addStradaAdiacente(aereoporto,randomTraffic());
        corsoUmberto.addStradaAdiacente(ristorante,randomTraffic());

        lungomare.addStradaAdiacente(concessionario,randomTraffic());
        lungomare.addStradaAdiacente(corsoUmberto,randomTraffic());

        viaSparano.addStradaAdiacente(pescheria,randomTraffic());

        parcoDueGiugno.addStradaAdiacente(mediaword,randomTraffic());
        parcoDueGiugno.addStradaAdiacente(cinema,randomTraffic());

        cinema.addStradaAdiacente(pizzeria,randomTraffic());

        bar.addStradaAdiacente(supermercato,randomTraffic());

        pizzeria.addStradaAdiacente(piazza_trilussa,randomTraffic());

        piazza_trilussa.addStradaAdiacente(scuola,randomTraffic());
        piazza_trilussa.addStradaAdiacente(hotel,randomTraffic());

    }

    private Incrocio getLuogoCasuale(){
        Random r = new Random();
        Integer i = r.nextInt(luoghiCittà.size());
        if(i==0) i++;   //affinchè non esca mai ospedale
        return luoghiCittà.get(i);
    }

    public void mandaSegnalazione()
    {
        Random r = new Random();
        LinkedList<String> sintomiEmergenza = new LinkedList<String> ();
        int n = r.nextInt(sintomi.size()-2);
        sintomiEmergenza.add(sintomi.get(n));
        sintomiEmergenza.add(sintomi.get(n+1));
        sintomiEmergenza.add(sintomi.get(n+2));
        int urgenza = urgenze.get(r.nextInt(urgenze.size()));
        Incrocio luogo = getLuogoCasuale();
       // System.out.println("Segnalazione rilevata presso " + luogo + " I sintomi sono i seguenti " + sintomiEmergenza.toString() +" , urgenza : " + urgenza);
        System.out.println(" Segnalazione rilevata presso : " + luogo);
        System.out.println(" Grado di urgenza : " + urgenza);
        ospedale.segnalazione(sintomiEmergenza,urgenza,luogo);
    }

    private double randomDouble(){
        Random rd = new Random();
        return rd.nextDouble() * rd.nextInt(7);
    }

    private double randomTraffic(){
        int a = 80; // numero minimo
        int b = 500; // numero massimo
        int c = ((b-a) + 1);
        Random rd = new Random();
        double result = rd.nextInt(c) + a;
        return result;
    }

}