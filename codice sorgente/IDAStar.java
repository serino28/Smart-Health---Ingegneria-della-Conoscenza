import java.util.*;

public class IDAStar {
    private static LinkedList<Incrocio> percorsoTrovato = new LinkedList<Incrocio>();

    public static String getPercorsoTrovato() {
        return percorsoTrovato.toString();
    }

    public static void svuotaPercorsoTrovato() {
        for(int i= 0; i<percorsoTrovato.size(); i++){
            percorsoTrovato.remove();
            i--;
        }

    }

    public static void IDAStar(Incrocio node) {
        double max = node.getEuristica();
        while(true) {
            //System.out.println("==========Iterazione==========");
            Risultato result = Search(node, 0, max, 0);
            if (result.type == 1) {
                //System.out.println(node);
                return;
            }
            else if (result.type == 2) {
                double min = (Double) result.result1;
                if (min == Double.MAX_VALUE) {
                    System.out.println("Percorso non trovato");
                    return;
                }
            }
            max = (Double) result.result1;
            //System.out.println("=============================");
        }
    }

    public static Risultato Search(Incrocio node, double g, double max, int sysout_level) {
        Risultato result;
        double f = node.getEuristica() + g;

        for (int i=0; i<sysout_level; i++) {
            //System.out.print("-");
        }
        //System.out.println(node);

        if (f>max) {
            result = new Risultato();
            result.type = 2;
            result.result1 = new Double(f);
            return result;
        }

        if (node.getEuristica() == 0) {
            result = new Risultato();
            result.type = 1;
            //result.result1 = new Double(f);
            //System.out.println("FOUND - use stack to reverse the order :)");
            //System.out.println(String.format("Total time: %.0f minutes", g));
            //System.out.println("Percorso:");
            //System.out.println(node); //qui
            percorsoTrovato.addFirst(node);
            return result;
        }

        double min = Double.MAX_VALUE;
        for (Strada e : node.getListaAdiacenti()) {
            Risultato result1 = Search(e.getIncrocio(), g + e.getCosto(), max, sysout_level+1);
            if (result1.type == 1) {
               //System.out.println(node); //qui
                percorsoTrovato.addFirst(node);
                return result1;
            }
            else if (result1.type == 2) {
                double newMin = (Double) result1.result1;
                if (newMin < min) {
                    min = newMin;
                }
            }
        }

        result = new Risultato();
        result.type = 2;
        result.result1 = new Double(min);
        return result;
    }
}
