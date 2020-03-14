import java.util.ArrayList;
import java.util.Scanner;

public class AutomateSimple{
    ArrayList<String> alphabet;
    Sommet S0;
    ArrayList<Sommet> etats;
    ArrayList<Sommet> finaux;
    ArrayList<Transition> transitions;
    public AutomateSimple(){}

    public AutomateSimple(Sommet init)
    {
        this.S0=init;
        this.alphabet= new ArrayList<String>();
        this.etats= new ArrayList<Sommet>();
        this.finaux= new ArrayList<Sommet>();
        this.transitions= new ArrayList<Transition>();
    }
    public Sommet getS0(){
        return this.S0;
    }
    public ArrayList<Sommet> getEtats(){
        return this.etats;
    }
    /**
     * @return the alphabet
     */
    public ArrayList<String> getAlphabet() {
        return alphabet;
    }
    /**
     * @return the finaux
     */
    public ArrayList<Sommet> getFinaux() {
        return finaux;
    }
    /**
     * @return the transitions
     */
    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public Sommet getSommetById(String id){
        int i=0;
        while ((i<this.getEtats().size())&&(!this.getEtats().get(i).getId().equals(id)) ){
            i++;
        }
        if (i<this.getEtats().size())return this.getEtats().get(i);
        else return null;
    }
    /**
     * @param alphabet the alphabet to set
     */

    public void setAlphabet(ArrayList<String> alphabet) {
        this.alphabet = alphabet;
    }
    /**
     * @param etats the etats to set
     */
    public void setEtats(ArrayList<Sommet> etats) {
        this.etats = etats;
    }/**
     * @param finaux the finaux to set
     */
    public void setFinaux(ArrayList<Sommet> finaux) {
        this.finaux = finaux;
    }
    /**
     * @param s0 the s0 to set
     */
    public void setS0(Sommet s0) {
        this.S0 = s0;
    }
    /**
     * @param transitions the transitions to set
     */
    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }


    public AutomateSimple Reduire(){
        ArrayList<Sommet> accessible = new ArrayList<Sommet>();
        ArrayList<Sommet> coaccessible = new ArrayList<Sommet>();
        ArrayList<Transition> trans = new ArrayList<Transition>();
        ArrayList<Transition> sortants = new ArrayList<Transition>();
        ArrayList<Transition> entrants = new ArrayList<Transition>();
        AutomateSimple AReduit = new AutomateSimple(this.S0);
        AReduit.setAlphabet(alphabet);
        AReduit.setEtats(etats);
        AReduit.setFinaux(finaux);
        AReduit.setTransitions(transitions);
        Sommet s0= this.S0;
        int tete=0;
        int cpt=0;
        Sommet s=s0;
        accessible.add(s);
        sortants=s.getTransSortantes();
        trans=transitions;
        Transition t=trans.get(cpt);
        boolean stop= false;
        while(!stop)
        {
            sortants=s.getTransSortantes();
            for (Transition tr : sortants)
            {
                accessible.add(tr.getDest());
            }
            System.out.println(accessible);
            System.out.println(tete);
            tete++;
            if (tete < accessible.size()) {
                s = accessible.get(tete);
            }
            else{
                stop= true;
            }
        }
        //la procédure de coacessible
        stop=false;
        tete=0;
        for (Sommet f: finaux)
        {
            coaccessible.add(f);
            s=f;
            while(!stop)
            {
                entrants=s.getTransEntrantes();
                for (Transition tr : entrants)
                {
                    coaccessible.add(tr.getSrc());
                }
                tete++;
                if (tete < coaccessible.size()) {
                    s = coaccessible.get(tete);
                }
                else{
                    stop= true;
                }
            }

        }

        ArrayList<Transition> at= new ArrayList<Transition>();
        ArrayList<Sommet> as= new ArrayList<Sommet>();
        for(Sommet ss: AReduit.getEtats()) {
            if (accessible.contains(ss) && coaccessible.contains(ss))
            {
                as.add(ss);
            }

        }
        for(Transition tt : AReduit.getTransitions())
        {
            if((as.contains(tt.getDest()))&& (as.contains(tt.getSrc())))
            {
                at.add(tt);
            }
        }

        AReduit.setTransitions(at);
        AReduit.setEtats(as);


        //eliminer les etats non accessibles et non coacessibles
  /*      ArrayList<Transition> at= AReduit.getTransitions();
        for(Sommet ss: AReduit.getEtats())
        {
            if (!accessible.contains(ss) || !coaccessible.contains(ss))
            {
                System.out.println("before for");
                for(Transition tt : at)
                {
                    System.out.println("after ofr");
                    if((tt.getDest()==ss)|| (tt.getSrc()==ss))
                    {

                        AReduit.transitions.remove(tt);
                    }
                }
                AReduit.etats.remove(ss);
            }
        }*/
        return AReduit;


    }

    public AutomateSimple Complet()
    {
        AutomateSimple AComplet = new AutomateSimple(this.S0);
        AComplet.setAlphabet(alphabet);
        AComplet.setEtats(etats);
        AComplet.setFinaux(finaux);
        AComplet.setTransitions(transitions);
        boolean trouv=false;
        Sommet puits= new Sommet("puits", EEtat.SIMPLE);
        for (Sommet s: AComplet.getEtats()) {
            for (String a : AComplet.getAlphabet()) {
                trouv= false;
                for (Transition t : s.getTransSortantes()) {
                    if (t.getTrans().equals(a)) {
                        trouv = true;
                    }
                }

                if (!trouv) {
                    Transition ajout = new Transition(s, puits, a, false);
                    AComplet.transitions.add(ajout);
                }

            }

        }

        Transition tpuit;
        for (String a : AComplet.alphabet)
        {
            tpuit= new Transition(puits, puits, a, true);
            AComplet.transitions.add(tpuit);

        }
        AComplet.etats.add(puits);

        return AComplet;
    }

    public AutomateSimple Complement()
    {
        AutomateSimple AComplement= new AutomateSimple(this.S0);
        AComplement.setAlphabet(alphabet);
        AComplement.setEtats(etats);
        ArrayList<Sommet> efinaux = new ArrayList<Sommet>();
        AComplement.setFinaux(efinaux);
        AComplement.setTransitions(transitions);
        AComplement= AComplement.Complet();
        for ( Sommet e : AComplement.etats)
        {
            if (e.getEtat()==EEtat.FINAL)
            {
                e.setEtat(EEtat.SIMPLE);
            }
            else{
                if (e.getEtat()==EEtat.SIMPLE)
                {
                    e.setEtat(EEtat.FINAL);
                    AComplement.finaux.add(e);

                }
            }
        }
        return AComplement;
    }


    public static AutomateSimple CreerAutomateSimple()
    {
        AutomateSimple automate=new AutomateSimple();
        automate.S0=new Sommet();
        automate.alphabet= new ArrayList<String>();
        automate.etats= new ArrayList<Sommet>();
        automate.finaux= new ArrayList<Sommet>();
        automate.transitions= new ArrayList<Transition>();

        Scanner scanner=new Scanner(System.in);
        System.out.println("Introduire l'ensemble X des alphabets : ");
        String alpha=scanner.nextLine();
        String [] parts=alpha.split(" ");
        ArrayList<String> alphabet=new ArrayList<String>();
        for ( int i=0;i<parts.length;i++) alphabet.add(parts[i]);
        automate.setAlphabet(alphabet);
        System.out.println("Introduire l'ensemble des etats : ");
        System.out.print("Nombre d'etats : \n");
        int nb_etat=scanner.nextInt();
        for (int i=1;i<=nb_etat;i++){
            System.out.print("  -> id-sommet :\n");
            String noun=scanner.next();
            System.out.print("  -> etat du sommet (1:initial | 2:simple | 3:final) : choisir un numero ");
            int choice =scanner.nextInt();
            switch (choice){
                case 1 :
                    Sommet si=new Sommet(noun,EEtat.INITIAL);
                    automate.getEtats().add(si);
                    automate.setS0(si);
                    break;
                case 2 :
                    Sommet ss=new Sommet(noun,EEtat.SIMPLE);
                    automate.getEtats().add(ss);
                    break;
                case 3:
                    Sommet sf=new Sommet(noun,EEtat.FINAL);
                    automate.getEtats().add(sf);
                    automate.getFinaux().add(sf);
                    break;
            }
        }
        System.out.println("Saisir l'ensemble des instructions : \n");
        int choix=1; int num=1;
        while (choix!=0){
            System.out.print("Ensemble des sommets : ");
            for (Sommet s :automate.getEtats()) System.out.print(s.getId()+" ");
            System.out.println("\n*********Instruction "+num+"*********");
            num++;
            System.out.print("\nSommet source :");
            String src=scanner.next();
            System.out.print("Sommet destination : ");
            String dst=scanner.next();
            System.out.print("la transition (une lettre de l'alphabet) :");
            String trans=scanner.next();
            boolean boucle;
            if (src.equals(dst)) boucle=true;
            else boucle=false;
            Sommet source= automate.getSommetById(src);
            Sommet destination = automate.getSommetById(dst);
            Transition transition=new Transition(source,destination,trans,boucle);
            ArrayList<Transition> ts= source.getTransSortantes();
            ts.add(transition);
            source.setTransSortantes(ts);
            System.out.println("heeeeeeeereee" + source.getTransSortantes());
            ArrayList<Transition> te =destination.getTransEntrantes();
            te.add(transition);
            destination.setTrans_entrantes(te);
            automate.getTransitions().add(transition);
            System.out.println("0- Quitter \n1-Continuer");
            choix=scanner.nextInt();
        }
        return automate;
    }

    public void afficheAutomateSimple(){
        System.out.println("Ensemble de l'alphabet :"+this.getAlphabet().toString());
        System.out.println("Etat initial :"+this.getS0().getId());
        System.out.print("Etats finaux : ");

        for (int i=0;i<this.getFinaux().size();i++) System.out.print(this.getFinaux().get(i).getId()+", ");
        for (Transition tr:this.getTransitions()) System.out.println("\n"+tr.getSrc().getId()+" -> ["+tr.getTrans()+"] -> "+tr.getDest().getId());
       /* for (Sommet s : this.getEtats())
        {
            System.out.println("sommet"+s.getId());
            System.out.println("transitions sortantes");
            for (Transition tr:s.getTransSortantes()) System.out.println("\n"+tr.getSrc().getId()+" -> ["+tr.getTrans()+"] -> "+tr.getDest().getId());
        }*/

    }



}
