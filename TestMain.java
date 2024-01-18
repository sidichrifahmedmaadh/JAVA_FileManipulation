
public class TestMain {

    public static void main(String[] args) {

        //4. --> Objet:
        TraitementNotes traitement = new TraitementNotes("ElementsModule.txt", "Etudiants.txt", "Notes.txt");

        System.out.println("\n$$$$$$$$$$$$$$$$$$$$$ LES RESULTAT $$$$$$$$$$$$$$$$$$$$$");

        System.out.println("\nC'est l'etudiant(e) : " + traitement.nomEtudiant(1701));

        System.out.println("\nCoefficient : " + traitement.coefficient("TC111"));

        System.out.println("\nNomber d'etudiant ayant une moyenne >= 10 : " + traitement.nbrElevesR("TC111")+" etudiants.");

        System.out.println("\nLe moyenne generale : " + traitement.moyenne(1701));

        traitement.sauvegarderResultats();

    }
}
