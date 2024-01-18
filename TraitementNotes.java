import java.io.*;

public class TraitementNotes {


    //1. --> Les Donnees:
    private String nomFichierEM;
    private String nomFichierEtudiants;
    private String nomFichierNotes;


    //2. --> Constructeur:
    public TraitementNotes( String nomFichierEM, String nomFichierEtudiants, String nomFichierNotes ) {
        this.nomFichierEM = nomFichierEM;
        this.nomFichierEtudiants = nomFichierEtudiants;
        this.nomFichierNotes = nomFichierNotes;
    }


    //3. --> Les Methodes & Les Fonctions:
    /************************* -nomEtudiant- ****************************/
    //---> Q3 (fonction) nomEtudiant:
    public String nomEtudiant ( int matricule ) {

        String nomComplet = null;

        try ( BufferedReader reader = new BufferedReader(new FileReader( nomFichierEtudiants ) ) )
        {
            String line;
            while ( ( line = reader.readLine() ) != null) {

                String[] elements = line.split(":");
                int matriculeEtudiant = Integer.parseInt ( elements[0] );

                if ( matriculeEtudiant == matricule ) {
                    nomComplet = elements[1] + " " + elements[2];
                    break;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return nomComplet;
    }

    /************************* -Coefficient- ****************************/
    //---> Q4 (méthode) coefficient:
    public int coefficient( String codeElementModule ) {

        int coefficient = 0;

       try ( BufferedReader reader = new BufferedReader(new FileReader(nomFichierEM) ) )
       {
           String line;

           while ( ( line = reader.readLine() ) != null ) {

               String[] elements = line.split(":" );

               if ( elements[0].equals( codeElementModule ) ) {
                   coefficient = Integer.parseInt(elements[3].trim());
                   break;
               }
           }
       }
       catch (IOException e)
       {
           e.printStackTrace();
       }
       return coefficient;
   }

    /************************* -nbrElevesR- ****************************/
    //---> Q5 (méthode) nbrElevesR:
    public int nbrElevesR( String codeElementModule ) {

        int nomber = 0;

        try ( BufferedReader reader = new BufferedReader(new FileReader( nomFichierNotes ) ) ) {
            String line;
            while ( ( line = reader.readLine() ) != null ) {
                String[] elements = line.split(":");
                if ( elements[0].equals( codeElementModule ) ) {
                    Double note = Double.parseDouble( elements[2] );
                    if ( note >= 10.0 ) {
                        ++nomber;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nomber;
    }

    /************************* -Moyenne- ****************************/
    //---> Q6 (méthode) moyenne:
    public double moyenne( int matricule ) {

        double somme_Notes = 0;
        int nbr_Notes = 0;

        try (BufferedReader Reader = new BufferedReader(new FileReader( nomFichierNotes ) ))
        {
            String line;
            while ( ( line = Reader.readLine() ) != null )
            {
                String[] elements = line.split(":");
                int matriculeEtudiant = Integer.parseInt(elements[1]);

                if ( matriculeEtudiant == matricule )
                {
                    Double note = Double.parseDouble(elements[2]);
                    somme_Notes += note;
                    ++nbr_Notes;
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return ( nbr_Notes > 0 ) ? somme_Notes /= nbr_Notes : 0;
    }


    /************************* -SauvegarderResultats- ****************************/
    //---> Q7 (méthode) sauvegarderResultats:
    public void sauvegarderResultats() {

        try (BufferedReader notesReader = new BufferedReader(new FileReader( nomFichierNotes ));
             BufferedReader etudiantsReader = new BufferedReader(new FileReader( nomFichierEtudiants ));
             BufferedWriter writer = new BufferedWriter(new FileWriter("Resultats.txt" ))) {

            java.util.Map<String, String> nomsEtudiants = new java.util.HashMap<>();

            String etudiantLine;

            while ( ( etudiantLine = etudiantsReader.readLine() ) != null ) {
                String[] etudiantElements = etudiantLine.split(":");

                if (etudiantElements.length >= 3) {
                    String matricule = etudiantElements[0];
                    String nomComplet = etudiantElements[1] + " " + etudiantElements[2];
                    nomsEtudiants.put(matricule, nomComplet);
                }
            }

            String notesLine;

            while ( ( notesLine = notesReader.readLine() ) != null ) {

                String[] notesElements = notesLine.split(":");

                if (notesElements.length >= 3) {

                    String matricule = notesElements[1];
                    String nomComplet = nomsEtudiants.get(matricule);
                    String codeElementModule = notesElements[0];
                    double note = Double.parseDouble(notesElements[2]);
                    int coefficient = coefficient(codeElementModule);

                    String resultatLine = matricule + ", " + nomComplet + ", " + codeElementModule + ", " + note * coefficient;
                    writer.write(resultatLine);
                    writer.newLine();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}





