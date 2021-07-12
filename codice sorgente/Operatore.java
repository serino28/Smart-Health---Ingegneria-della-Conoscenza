import java.util.LinkedList;

public class Operatore {
    private String tipo;
    private String stato;

    private static LinkedList<String> statiPossibili = creaStatiPossibili();
    //stati possibili sono "turno" (index 0) ; "malattia" (index 1) ; "ferie" (index 2)

    Operatore(String tipo,String stato) {
        this.tipo = tipo;
        this.stato = stato;
    }

    public String toString(){
        return "Operatore: "+tipo+", stato: "+stato;
    }

    public static String getStatoPossibile(Integer index) {
        if(index < statiPossibili.size() && index >= 0 )
            return statiPossibili.get(index);
        else return statiPossibili.get(0); //di default restituisco "turno"
    }

    private static LinkedList<String> creaStatiPossibili() {
        LinkedList<String> stati = new LinkedList<>();
        stati.add("turno"); // index 0
        stati.add("ambulanza");
        stati.add("malattia"); // index 1
        stati.add("ferie"); // index 2
        return stati;
    }

    public String getTipo()    { return tipo;}
    public String getStato()    { return stato;}
    public void setTipo(String tipo)    { this.tipo = tipo; }
    public void setStato(String stato)    { this.stato = stato;}
}
