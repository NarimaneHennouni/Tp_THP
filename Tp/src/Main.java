import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Veuillez entrer votre Automate");
        System.out.println("");
        AutomateSimple A =AutomateSimple.CreerAutomateSimple();
        A.afficheAutomateSimple();
        System.out.println("Choisir ce que vous voulez faire sur votre automate:");
        System.out.println(" 1.Réduire l'automate");
        System.out.println(" 2.Faire le complement");
        System.out.println(" 3.Faire le miroir");
        System.out.println(" 4.Vérifier la reconnaissance de mots");
        System.out.println(" 5.Déterminiser l'automate");

        Scanner sc= new Scanner(System.in);
        int i=sc.nextInt();
        switch(i)
        {
            case 1:
                AutomateSimple AR= A.Reduire();
                AR.afficheAutomateSimple();
                break;

            case 2:
                AutomateSimple AC = A.Complement();
                AC.afficheAutomateSimple();
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

        }

    }





}
