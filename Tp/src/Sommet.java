import java.util.ArrayList;


public class Sommet {
    private String id;
    private EEtat etat;
    private ArrayList<Transition> trans_entrantes;
    private ArrayList<Transition> trans_sortantes;

    public Sommet()
    {

    }

    public Sommet(String id, EEtat etat )
    {
        this.trans_entrantes= new ArrayList<Transition>();
        this.trans_sortantes= new ArrayList<Transition>();
        this.id=id;
        this.etat=etat;
    }

    public ArrayList<Transition> getTransEntrantes() {
        return this.trans_entrantes;
    }

    public ArrayList<Transition> getTransSortantes() {
        return this.trans_sortantes;
    }

    public void setTransSortantes(ArrayList<Transition> TransSortantes ) {
        this.trans_sortantes = TransSortantes;
    }
    /**
     * @return the etat
     */
    public EEtat getEtat() {
        return etat;
    }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param etat the etat to set
     */
    public void setEtat(EEtat etat) {
        this.etat = etat;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    public void setTrans_entrantes(ArrayList<Transition> trans_entrantes) {
        this.trans_entrantes = trans_entrantes;
    }
}