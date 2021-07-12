import org.chocosolver.solver.constraints.nary.nvalue.amnv.rules.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Paziente {
    private String CF;                  //codice fiscale paziente
    private String nome;                //nome paziente
    private String cognome;             //cognome paziente
    private Date ddn;                   //data di nascita paziente
    private char sesso;                 //sesso paziente
    private int giorniRimanenti;        //numero giorni rimanenti
    private int aspettativaDegenza;     //numero giorni previsti
    private int probabilitàGuarire;     //10, 20, 30 ...
    private String status;              //"VIVO", "MORTO", o "GUARITO"


    //Costruttore paziente
    public Paziente(String nome, String cognome, Date ddn, char sesso, String CodCom)
    {
        this.nome = nome;
        this.cognome = cognome;
        this.ddn = ddn;
        this.sesso = sesso;
        this.CF = "codiceFiscale";
        this.CF = calcolaCF(CodCom);
        probabilitàGuarire = setProbGuarigione();
        this.aspettativaDegenza = calcolaAspettativaDegenza();
        this.giorniRimanenti = this.aspettativaDegenza;
        this.status = "VIVO";
    }

    public String esitoDegenza(){
        giorniRimanenti --;
        if(giorniRimanenti == 0)
            if(probabilitàGuarire >= 50)
                status = "GUARITO";
            else status = "MORTO";
        return status;
    }

    private Integer setProbGuarigione(){
        Random r = new Random();
        Integer prob;
        while(true){
            prob = r.nextInt(101);
            if(prob != 0 && prob%10 == 0)
                break;
        }
        return prob;
    }

    //Costruttore paziente
    public Paziente(String nome, String cognome, Date ddn, char sesso)
    {
        this(nome, cognome, ddn, sesso, "F284");
    }

    //Funzione che effettua il calcolo del codice fiscale dato un codice catastale
    private String calcolaCF(String CodCom)
    {
        String upNome = nome.toUpperCase();
        String upCognome = cognome.toUpperCase();
        String codFis = "";
        /*calcolo prime 3 lettere */
        int cont = 0;
        /*caso cognome minore di 3 lettere*/
        if (upCognome.length()<3){
            codFis+=upCognome;
            while (codFis.length()<3) codFis+= "X";
            cont=3;
        }
        /*caso normale*/
        for (int i=0;i<upCognome.length();i++) {
            if (cont==3) break;
            if (upCognome.charAt(i)!='A' && upCognome.charAt(i)!='E' &&
                    upCognome.charAt(i)!='I' && upCognome.charAt(i)!='O' &&
                    upCognome.charAt(i)!='U') {
                codFis+= Character.toString(upCognome.charAt(i));
                cont++;
            }
        }
        /* nel casoci siano meno di 3 consonanti*/
        while (cont<3) {
            for (int i=0;i<upCognome.length();i++) {
                if (cont==3) break;
                if (upCognome.charAt(i)=='A' || upCognome.charAt(i)=='E' ||
                        upCognome.charAt(i)=='I' || upCognome.charAt(i)=='O' ||
                        upCognome.charAt(i)=='U') {
                    codFis+= Character.toString(upCognome.charAt(i));
                    cont++;
                }
            }
        }
        /*lettere nome*/
        cont = 0;
        /*caso nome minore di 3 lettere*/
        if (upNome.length()<3){
            codFis+= upNome;
            while (codFis.length()<6) codFis+= "X";
            cont=3;
        }
        /*caso normale*/
        for (int i=0;i<upNome.length();i++) {
            if (cont==3) break;
            if (upNome.charAt(i)!='A' && upNome.charAt(i)!='E' &&
                    upNome.charAt(i)!='I' && upNome.charAt(i)!='O' &&
                    upNome.charAt(i)!='U') {
                codFis+= Character.toString(upNome.charAt(i));
                cont++;
            }
        }
        /* nel caso ci siano meno di 3 consonanti*/
        while (cont<3) {
            for (int i=0;i<upNome.length();i++) {
                if (cont==3) break;
                if (upNome.charAt(i)=='A' || upNome.charAt(i)=='E' ||
                        upNome.charAt(i)=='I' || upNome.charAt(i)=='O' ||
                        upNome.charAt(i)=='U') {
                    codFis+= Character.toString(upNome.charAt(i));
                    cont++;
                }
            }
        }
        /* anno */
        String fullYearString = Integer.toString(ddn.getYear());
        codFis+=fullYearString.substring(2);
        /*Mese*/
        int mese = ddn.getMonth();
        switch (mese) {
            case 1: {codFis+="A";break;}
            case 2: {codFis+="B";break;}
            case 3: {codFis+="C";break;}
            case 4: {codFis+="D";break;}
            case 5: {codFis+="E";break;}
            case 6: {codFis+="H";break;}
            case 7: {codFis+="L";break;}
            case 8: {codFis+="M";break;}
            case 9: {codFis+="P";break;}
            case 10: {codFis+="R";break;}
            case 11: {codFis+="S";break;}
            case 12: {codFis+="T";break;}
        }
        /*giorno*/
        int giorno = ddn.getDay();

        if (sesso == 'M'){
            if(giorno < 10)
                codFis += "0";
            codFis += giorno;
            }
        else {
            giorno+=40;
            codFis+=Integer.toString(giorno);
        }

        /*comune nascita*/
        codFis+=CodCom;
        /*Carattere di controllo*/
        int sommaPari=0;
        for (int i=1;i<=13;i+=2) {
            switch (codFis.charAt(i)) {
                case '0': {sommaPari+=0;break;}
                case '1': {sommaPari+=1;break;}
                case '2': {sommaPari+=2;break;}
                case '3': {sommaPari+=3;break;}
                case '4': {sommaPari+=4;break;}
                case '5': {sommaPari+=5;break;}
                case '6': {sommaPari+=6;break;}
                case '7': {sommaPari+=7;break;}
                case '8': {sommaPari+=8;break;}
                case '9': {sommaPari+=9;break;}
                case 'A': {sommaPari+=0;break;}
                case 'B': {sommaPari+=1;break;}
                case 'C': {sommaPari+=2;break;}
                case 'D': {sommaPari+=3;break;}
                case 'E': {sommaPari+=4;break;}
                case 'F': {sommaPari+=5;break;}
                case 'G': {sommaPari+=6;break;}
                case 'H': {sommaPari+=7;break;}
                case 'I': {sommaPari+=8;break;}
                case 'J': {sommaPari+=9;break;}
                case 'K': {sommaPari+=10;break;}
                case 'L': {sommaPari+=11;break;}
                case 'M': {sommaPari+=12;break;}
                case 'N': {sommaPari+=13;break;}
                case 'O': {sommaPari+=14;break;}
                case 'P': {sommaPari+=15;break;}
                case 'Q': {sommaPari+=16;break;}
                case 'R': {sommaPari+=17;break;}
                case 'S': {sommaPari+=18;break;}
                case 'T': {sommaPari+=19;break;}
                case 'U': {sommaPari+=20;break;}
                case 'V': {sommaPari+=21;break;}
                case 'W': {sommaPari+=22;break;}
                case 'X': {sommaPari+=23;break;}
                case 'Y': {sommaPari+=24;break;}
                case 'Z': {sommaPari+=25;break;}
            }
        }
        int sommaDispari=0;
        for (int i=0;i<=14;i+=2) {
            switch (codFis.charAt(i)) {
                case '0': {sommaDispari+=1;break;}
                case '1': {sommaDispari+=0;break;}
                case '2': {sommaDispari+=5;break;}
                case '3': {sommaDispari+=7;break;}
                case '4': {sommaDispari+=9;break;}
                case '5': {sommaDispari+=13;break;}
                case '6': {sommaDispari+=15;break;}
                case '7': {sommaDispari+=17;break;}
                case '8': {sommaDispari+=19;break;}
                case '9': {sommaDispari+=21;break;}
                case 'A': {sommaDispari+=1;break;}
                case 'B': {sommaDispari+=0;break;}
                case 'C': {sommaDispari+=5;break;}
                case 'D': {sommaDispari+=7;break;}
                case 'E': {sommaDispari+=9;break;}
                case 'F': {sommaDispari+=13;break;}
                case 'G': {sommaDispari+=15;break;}
                case 'H': {sommaDispari+=17;break;}
                case 'I': {sommaDispari+=19;break;}
                case 'J': {sommaDispari+=21;break;}
                case 'K': {sommaDispari+=2;break;}
                case 'L': {sommaDispari+=4;break;}
                case 'M': {sommaDispari+=18;break;}
                case 'N': {sommaDispari+=20;break;}
                case 'O': {sommaDispari+=11;break;}
                case 'P': {sommaDispari+=3;break;}
                case 'Q': {sommaDispari+=6;break;}
                case 'R': {sommaDispari+=8;break;}
                case 'S': {sommaDispari+=12;break;}
                case 'T': {sommaDispari+=14;break;}
                case 'U': {sommaDispari+=16;break;}
                case 'V': {sommaDispari+=10;break;}
                case 'W': {sommaDispari+=22;break;}
                case 'X': {sommaDispari+=25;break;}
                case 'Y': {sommaDispari+=24;break;}
                case 'Z': {sommaDispari+=23;break;}
            }
        }
        int interoControllo = (sommaPari+sommaDispari)%26;
        String carattereControllo="";
        switch (interoControllo) {
            case 0:{carattereControllo="A";break;}
            case 1:{carattereControllo="B";break;}
            case 2:{carattereControllo="C";break;}
            case 3:{carattereControllo="D";break;}
            case 4:{carattereControllo="E";break;}
            case 5:{carattereControllo="F";break;}
            case 6:{carattereControllo="G";break;}
            case 7:{carattereControllo="H";break;}
            case 8:{carattereControllo="I";break;}
            case 9:{carattereControllo="J";break;}
            case 10:{carattereControllo="K";break;}
            case 11:{carattereControllo="L";break;}
            case 12:{carattereControllo="M";break;}
            case 13:{carattereControllo="N";break;}
            case 14:{carattereControllo="O";break;}
            case 15:{carattereControllo="P";break;}
            case 16:{carattereControllo="Q";break;}
            case 17:{carattereControllo="R";break;}
            case 18:{carattereControllo="S";break;}
            case 19:{carattereControllo="T";break;}
            case 20:{carattereControllo="U";break;}
            case 21:{carattereControllo="V";break;}
            case 22:{carattereControllo="W";break;}
            case 23:{carattereControllo="X";break;}
            case 24:{carattereControllo="Y";break;}
            case 25:{carattereControllo="Z";break;}
        }
        codFis+=carattereControllo;
        return codFis;
    }

    //metodo get che restituisce il codice fiscale
    public String getCF()
    {
        return CF;
    }

    //metodo get che restituisce il nome
    public String getNome() {return nome;}

    //metodo get che restituisce il cognome
    public String getCognome() {return cognome;}

    //metodo get che restituisce la data (come Date)
    public Date getDdn() {return ddn;}

    //metodo get che restituisce il sesso
    public char getSesso() {return sesso;}

    //metodo set che avvalora il nome
    public void setNome(String nome) {this.nome = nome;}

    //metodo set che avvalora il cognome
    public void setCognome(String cognome) {this.cognome = cognome;}

    //metodo set che avvalora la data (Date)
    public void setDdn(Date ddn) {this.ddn = ddn;}

    //metodo set che avvalora la data (giorno, data, e ora)
    public void setDdn(int day, int month, int year) {this.ddn = new Date(year, month, day);}

    //metodo set che avvalora il sesso
    public void setSesso(char sesso) {this.sesso = sesso;}

    //metodo che calcola il numero di giorni di degenza
    private int calcolaAspettativaDegenza()
    {
        switch (probabilitàGuarire)
        {
            case 10:
            {
                return 6;
                //break;
            }
            case 20:
            {
                return 5;
                //break;
            }
            case 30:
            {
                return 5;
                //break;
            }
            case 40:
            {
                return 4;
                //break;
            }
            case 50:
            {
                return 4;
                //break;
            }
            case 60:
            {
                return 4;
                //break;
            }
            case 70:
            {
                return 3;
                //break;
            }
            case 80:
            {
                return 3;
                //break;
            }
            case 90:
            {
                return 2;
                //break;
            }
        }
        return 0;
    }

    //metodo che decrementa i giorni rimanenti di degenza
    public void decrementoGiorniRimanenti() {this.giorniRimanenti = giorniRimanenti - 1;}

    //metodo che aumenta la probabilità di guarire col passare dei giorni
    public void aumentoProbabilita() {this.probabilitàGuarire = probabilitàGuarire + 10;}

    //metodo che imposta un paziente come morto
    public void mortePaziente() {this.status = "MORTO";}

    //metodo che imposta un paziente come guarito
    public void pazienteGuarito() {this.status = "GUARITO";}

    //metodo che determina se un paziente è vivo
    public boolean isVivo() {return this.status == "VIVO" || this.status == "GUARITO";}

    //metodo che definisce la situazione medica giornaliera di un paziente
    public void situazioneMedicaDay()
    {
        int randvalue = (int)(Math.random() * 100)+1;       //valore da 1 a 100
        if(probabilitàGuarire > randvalue)
        {
            mortePaziente();
            return;
        }
        else
        {
            decrementoGiorniRimanenti();
            aumentoProbabilita();
            if(giorniRimanenti == 0)
                pazienteGuarito();
        }
    }

    public String toString()
    {
        return CF + " " + nome + " " + cognome + " " + ddn + " " + sesso;
    }
}
