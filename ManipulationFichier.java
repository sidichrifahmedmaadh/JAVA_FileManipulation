import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ManipulationFichier {

    public static void main(String[] args) {
        // Chemin du fichier à lire
        String cheminDuFichier = "fichier.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminDuFichier))) {
            String line;
            // Lecture de chaque ligne du fichier
            while ((line = reader.readLine()) != null) {
                // Division de la ligne en valeurs en utilisant la virgule comme séparateur
                String[] values = line.split(",");
                // Affichage des valeurs de chaque ligne
                for (String value : values) {
                    System.out.print(value.trim() + " ");
                }
                System.out.println(); // Nouvelle ligne pour la prochaine entrée
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
