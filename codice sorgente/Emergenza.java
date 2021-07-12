import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;

import java.util.Arrays;
import java.util.LinkedList;

public class Emergenza {

    int urgenza; //-> SETTATO DA OSPEDALE grado di urgenza dell'emergenza
    Incrocio luogo; // -> SETTATO DA OSPEDALE luogo in cui avviene l'emergenza

    LinkedList<String> sintomi; //->SETTATO DA OSPEDALE Sintomi del paziente
    String categoria; // categoria dell'emergenza

    //COSTRUTTORE
    public Emergenza(LinkedList<String>sintomi,int urgenza,Incrocio luogo){
        this.sintomi=sintomi;
        this.urgenza=urgenza;
        this.luogo=luogo;
        categoria=categorizza(sintomi,urgenza);
        System.out.println("Il sistema ha utilizzato l'apprendimento per categorizzare la situazione del paziente");
        System.out.println("la situazione del paziente è la seguente : " + sintomi.toString() +"\n"+
                            "la categoria è : " + categoria);
    }

    /*
     public static void main(String args[]){
        LinkedList<String>sintomi = new LinkedList<>();
         sintomi.add("dolore");
         sintomi.add("urtare la testa");
         sintomi.add("cadere");


         Incrocio luogo = new Incrocio();
        Emergenza emergenza = new Emergenza(sintomi,1,luogo);
        System.out.println("cat : " + emergenza.get_categoria());
    }
*/
    private String categorizza(LinkedList<String>sintomi,int urgenza){

// Create a new bayes classifier with string categories and string features.
        Classifier<String, String> bayes = new BayesClassifier<String, String>();

        String[] covidText = "starnuto tosse febbre mal  gola stanchezza affanno covid perdita gusto olfatto bronchite polmonite polmoni difficoltà respiratorie".split("\\s");
        String[] infartoText = "dolore braccio affanno sinistro petto cuore infarto tachicardia accelerazione battito fitta ipertensione".split("\\s");
        String[] cadutaText = "vista annebbiata urtare dolore testa cadere gamba inciampare scivolare caduta  livido ematoma".split("\\s");
        String[] svenimentoText = "perdita  conoscenza pressione bassa svenire sensi ferito giramento  testa svenimento ".split("\\s");
        String[] incidenteText = "automobile incidente scontro urtare tamponamento ferito macchina".split("\\s");

/* Two examples to learn from.
        String[] positiveText = "I love sunny days".split("\\s");
        String[] negativeText = "I hate rain".split("\\s");*/

        bayes.learn("covid", Arrays.asList(covidText));
        bayes.learn("infarto", Arrays.asList(infartoText));
        bayes.learn("caduta", Arrays.asList(cadutaText));
        bayes.learn("svenimento", Arrays.asList(svenimentoText));
        bayes.learn("incidente", Arrays.asList(incidenteText));

// Learn by classifying examples.
// New categories can be added on the fly, when they are first used.
// A classification consists of a category and a list of features
// that resulted in the classification in that category.
       /* bayes.learn("positive", Arrays.asList(positiveText));
        bayes.learn("negative", Arrays.asList(negativeText));*/

        //sintomi ricevuto in input
        String[] unknownText1 = "".split("\\s");
        for(int i = 0; i<sintomi.size(); i++){
            //System.out.println(sintomi.get(i));
            unknownText1 = sintomi.get(i).split("\\s");
        }

// Here are two unknown sentences to classify.
       /* String[] unknownText1 = "today is a sunny day".split("\\s");
        String[] unknownText2 = "there will be rain".split("\\s");*/

        /*
        System.out.println( // will output "positive"
                bayes.classify(Arrays.asList(unknownText1)).getCategory());
        System.out.println( // will output "negative"
                bayes.classify(Arrays.asList(unknownText2)).getCategory());
*/

// Get more detailed classification result.
        ((BayesClassifier<String, String>) bayes).classifyDetailed(
                Arrays.asList(unknownText1));

// Change the memory capacity. New learned classifications (using
// the learn method) are stored in a queue with the size given
// here and used to classify unknown sentences.
        bayes.setMemoryCapacity(500);

        return bayes.classify(Arrays.asList(unknownText1)).getCategory().toString();
    }

    public int get_urgenza(){return urgenza;}
    public String get_categoria(){return categoria;}
    public Incrocio get_luogo(){return luogo;}

}

/*
import java.util.Arrays;
import java.util.LinkedList;
public class Emergenza {
    LinkedList<String> sintomi; //->SETTATO DA OSPEDALE Sintomi del paziente
    int urgenza; //-> SETTATO DA OSPEDALE grado di urgenza dell'emergenza
    Incrocio luogo; // -> SETTATO DA OSPEDALE luogo in cui avviene l'emergenza
    String categoria; // categoria dell'emergenza

    //COSTRUTTORE
    public Emergenza(LinkedList<String>sintomi,int urgenza,Incrocio luogo){
        this.sintomi=sintomi;
        this.urgenza=urgenza;
        this.luogo=luogo;
        categoria=categorizza(sintomi,urgenza);
    }
    public String categorizza(LinkedList<String> sintomi, Integer urgenza){
        return "covid";
    }
    public int get_urgenza(){return urgenza;}
    public String get_categoria(){return categoria;}
    public Incrocio get_luogo(){return luogo;}
}
*/