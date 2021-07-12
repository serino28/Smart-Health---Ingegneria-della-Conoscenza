import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

import java.util.HashMap;
import java.util.List;

public class Csp
{
    public static HashMap<String,Integer> getPersonaleAmbulanza(String categoria,Integer urgenza)  {
        int numCategoria;
        switch(categoria){
            case("covid"):{
                numCategoria = 1;
                break;
            }
            case("infarto"):{
                numCategoria = 2;
                break;
            }
            case("caduta"):{
                numCategoria = 3;
                break;
            }
            case("svenimento"):{
                numCategoria = 4;
                break;
            }
            case("incidente"):{
                numCategoria = 5;
                break;
            }
            default:{
                numCategoria = 0;
                break;
            }

        }
       // System.out.println("numcategoria che passo è " + numCategoria);
        List<IntVar> listaSoluzione = getSoluzione(numCategoria,urgenza);
        if(listaSoluzione != null) {
            HashMap<String,Integer> soluzione = new HashMap<String,Integer>();
            for(int i = 0; i<7; i++)
                soluzione.put(listaSoluzione.get(i).getName(),listaSoluzione.get(i).getValue());
            return soluzione;
            }
        return null;
    }

    private static List<IntVar>  getSoluzione(Integer cat, Integer urgenza)
    {
    // 1. Create a Model
    Model model = new Model("my first problem");
    // 2. Create variables
    IntVar categoria = model.intVar("categoria", 0, 5);
    IntVar grado = model.intVar("grado", 1, 3);
    IntVar mediciCovid = model.intVar("mediciCovid", 0, 3);
    IntVar mediciNorm = model.intVar("mediciNorm", 0, 3);
    IntVar rianNorm = model.intVar("rianNorm", 0, 1);
    IntVar rianCovid = model.intVar("rianCovid", 0, 1);
    IntVar autisti = model.intVar("autisti", 1, 1);


        //****VINCOLO ZERO****

        model.ifThen(
                model.arithm(categoria,"=",0),
                model.and(model.arithm(mediciCovid,"=",0),
                          model.arithm(mediciNorm,"=",2),
                          model.arithm(rianNorm,"=",1),
                          model.arithm(rianCovid,"=",0))
        );


        //****PRIMO VINCOLO****
        model.ifThenElse(
                model.arithm(categoria,"=",1),
                model.arithm(rianCovid,"=",1),
                model.arithm(rianCovid,"=",0)
        );

        //****SECONDO VINCOLO****
        model.ifThen(
                model.and(model.arithm(categoria,"=",1),model.arithm(grado,"=",3)),
                model.or(model.arithm(mediciCovid,"=",2),model.arithm(mediciCovid,"=",3))
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",1),model.arithm(grado,"=",2)),
                model.or(model.arithm(mediciCovid,"=",2),model.arithm(mediciCovid,"=",3))
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",1),model.arithm(grado,"=",1)),
                model.arithm(mediciCovid,"=",2)
        );
        model.ifThen(model.arithm(categoria,"!=",1),
                model.arithm(mediciCovid,"=",0)
        );

        //****TERZO VINCOLO****
        model.ifThen(model.arithm(categoria,"=",1),
                model.arithm(mediciNorm,"=",0)
        );

        //****QUARTO VINCOLO****
        model.ifThen(
                model.and(model.arithm(categoria,"=",5),model.arithm(grado,"=",1)),
                model.arithm(mediciNorm,"=",2)
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",5),model.arithm(grado,"=",2)),
                model.arithm(mediciNorm,"=",3)
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",5),model.arithm(grado,"=",3)),
                model.arithm(mediciNorm,"=",3)
        );

        //****QUINTO VINCOLO****
        model.ifThen(
                model.and(model.arithm(categoria,"=",2),model.arithm(grado,"=",3)),
                model.or(model.arithm(mediciNorm,"=",3),model.arithm(mediciNorm,"=",2))
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",3),model.arithm(grado,"=",3)),
                model.or(model.arithm(mediciNorm,"=",3),model.arithm(mediciNorm,"=",2))
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",4),model.arithm(grado,"=",3)),
                model.or(model.arithm(mediciNorm,"=",3),model.arithm(mediciNorm,"=",2))
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",2),model.arithm(grado,"=",2)),
                model.arithm(mediciNorm,"=",2)
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",3),model.arithm(grado,"=",2)),
                model.arithm(mediciNorm,"=",2)
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",4),model.arithm(grado,"=",2)),
                model.arithm(mediciNorm,"=",2)
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",2),model.arithm(grado,"=",1)),
                model.arithm(mediciNorm,"=",2)
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",3),model.arithm(grado,"=",1)),
                model.arithm(mediciNorm,"=",2)
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",4),model.arithm(grado,"=",1)),
                model.arithm(mediciNorm,"=",2)
        );


        //****SESTO VINCOLO****
        model.ifThen(
                model.arithm(categoria,"=",1),
                model.arithm(rianNorm,"=",0)
        );

        //****SETTIMO VINCOLO****
        model.ifThen(
                model.or(model.arithm(categoria,"=",2),model.arithm(categoria,"=",4)),
                model.arithm(rianNorm,"=",1)
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",3),model.arithm(grado,"=",1)),
                model.arithm(rianNorm,"=",0)
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",3),model.arithm(grado,"=",3)),
                model.arithm(rianNorm,"=",1)
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",5),model.arithm(grado,"=",2)),
                model.arithm(rianNorm,"=",1)
        );
        model.ifThen(
                model.and(model.arithm(categoria,"=",5),model.arithm(grado,"=",3)),
                model.arithm(rianNorm,"=",1)
        );

    /*
    ATTENZIONE
    vincoli : cambiati in corso d'opera
    non più precisamente i seguenti:
    1) (Categoria = 1 and RianCovid = 1) or (Categoria != 1 and RianCovid = 0) ok
2) (Categoria = 1 and (Grado = 3 or Grado = 2)) and MediciCovid>= Grado-1)
 or (Categoria = 1 and Grado = 1 and MediciCovid = 2)
 or (Categoria != 1 and MediciCovid = 0) ok
3) Categoria = 1 and MediciNorm = 0 ok
4) Categoria = 5 and ((( grado= 1 or grado = 2) and MediciNorm = grado+1) or (grado = 3 and mediciNorm = grado)) ok
5) (Categoria = 2 or Categoria = 3 or Categoria = 4)
and ((grado = 3 and MediciNorm >= Grado-1)
or ( grado = 2 and mediciNorm = grado)
or (grado= 1 and mediciNorm = G+1)) ok
6) Categoria = 1 and RianNorm=0 ok
7) ((categoria = 2 or caegoria = 4 ) and RianNorm = 1)
or ((categoria = 3 or caegoria = 5 ) and grado=1 and RianNorm = 0)
or ((categoria = 3 or caegoria = 5 )and grado!=1 and RianNorm = 1) ok
     */

    // 4. Get the solver
    Solver solver = model.getSolver();
    // 6. Launch the resolution process
    solver.solve();
    //solver.printStatistics();
    Solution s = new Solution(model);
    //System.out.println( "devo trovare categoria valore : " + cat + "urgenza valore : " + urgenza);
        while (solver.solve()) {
            List<IntVar> al;
            al = s.record().retrieveIntVars(true);
            //System.out.println(al.get(0).getName() + "valore: " + al.get(0).getValue() + al.get(1).getName() + "valore: " + al.get(1).getValue());
            if(al.get(0).getValue() == cat && al.get(1).getValue() == urgenza){
                //System.out.println("sto ritornando :" + al.toString());
                return al;
            }
           // System.out.println(al.get(0).getValue());
           // System.out.println(al.get(0).toString());
        }
        System.out.println("ritorno nullo");
    return null;
    }
}