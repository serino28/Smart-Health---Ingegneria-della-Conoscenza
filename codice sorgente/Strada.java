/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.LinkedList;
public class Strada {
    private double costo;
    private Incrocio incrocio;

    /*
    //attributi vecchi
    private Incrocio start;
    private Incrocio end;
    private Integer id;
    private Integer lunghezza;
    private Integer traffico;
    private Integer pesototale = lunghezza + traffico;
*/
    //nuovo costruttore
    public Strada( Incrocio incrocio, double costVal){
        this.incrocio = incrocio;
        costo = costVal;
    }

    public double getCosto(){
        return costo;
    }
    //nuovo
    public Incrocio getIncrocio(){
        return incrocio;
    }

    /*
    //tutto ciò che segue è vecchio
 Strada(Incrocio start , Incrocio end, Integer id, Integer lunghezza){
        this.start = start;
        this.end = end;
        this.id = id;
        this.lunghezza = lunghezza;
    }
    
    Strada(Object start, Object end, Object id) {
       Strada a = new Strada(start, end, id);
    }

    public Incrocio getStart(){
        return(start);
    }
    public Incrocio getEnd(){
        return(end);
    }
    public Integer getId(){
        return(id);
    }
    public Integer getLunghezza(){
        return(lunghezza);
    }
    public Integer getTraffico(){
        return(traffico);
    }
    public void setTraffico(Integer traffico){
        this.traffico = traffico;
    }
    public Integer getPesoTotale(){
        return(pesototale);
    }
    */
    
}
