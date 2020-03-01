import java.util.ArrayList;

public class Transition {
    private String src;
    private String dest;
    private String trans;
    private boolean boucle;

    public Transition(String src, String dest, String trans, boolean boucle)
    {
        this.dest=dest;
        this.src=src;
        this.trans=trans;
        this.boucle=boucle;
    }
    /**
     * @return the dest
     */
    public String getDest() {
        return dest;
    }
    /**
     * @return the src
     */
    public String getSrc() {
        return src;
    }
    /**
     * @return the trans
     */
    public String getTrans() {
        return trans;
    }
    /**
     * @param dest the dest to set
     */
    public void setDest(String dest) {
        this.dest = dest;
    }
    /**
     * @param src the src to set
     */
    public void setSrc(String src) {
        this.src = src;
    }
    
    /**
     * @param trans the trans to set
     */
    public void setTrans(String trans) {
        this.trans = trans;
    }
    /**
     * @param boucle the boucle to set
     */
    public void setBoucle(boolean boucle) {
        this.boucle = boucle;
    }
    /**
     * @return the boucle
     */
    public boolean getBoucle() {
        return boucle;
    }
}


public enum EEtat{ initial,final};

public class Sommet {
    private String id;
    private EEtat etat;
    private ArrayList<Transition> trans_entrantes;
    private ArrayList<Transition> trans_sortantes;

}