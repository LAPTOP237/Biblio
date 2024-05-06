/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio;
/**
 *
 * @author LINJOUOM ALAIN P
 */
public class Document {
    
    // Default constructor
    public Document() {
    }
    /**
     
     * @param titre
     * @param localisation
     * @param nbExemplaires
     */
    public Document(String titre, String localisation, int nbExemplaires) {
        this.titre = titre;
        this.localisation = localisation;
        this.nbExemplaires = nbExemplaires;
    }

    /**
     * 
     */
    public String titre;

    /**
     * 
     */
    public String localisation;

    /**
     * 
     */
    private int code;

    /**
     * 
     */
    public int nbExemplaires;

    /**
     * Debut Getters et Setters
     * @return 
     */

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getNbExemplaires() {
        return nbExemplaires;
    }

    public void setNbExemplaires(int nbExemplaires) {
        this.nbExemplaires = nbExemplaires;
    }

    /**
     * FIN Getters et Setters
     */
    
    /**
     * Affiche les caractéristiques du document
     * @param document
     */
   public static void afficherCaracteristiques(Document document) {
        if (document instanceof Livre livre) {
            System.out.println("***Livre***");
            System.out.println("Titre: " + livre.getTitre());
            System.out.println("Auteur: " + livre.getAuteur());
            System.out.println("Editeur: " + livre.getEditeur());
            System.out.println("Date d'édition: " + livre.getDateEdition());
            System.out.println("Localisation: " + livre.getLocalisation());
            System.out.println("Nombre d'exemplaires: " + livre.getNbExemplaires());
        } else if (document instanceof Magazine magazine) {
            System.out.println("***Magazine***");
            System.out.println("Titre: " + magazine.getTitre());
            System.out.println("Fréquence de parution: " + magazine.getFrequenceParution());
            System.out.println("Localisation: " + magazine.getLocalisation());
            System.out.println("Nombre d'exemplaires: " + magazine.getNbExemplaires());
        } else if (document instanceof Memoire memoire) {
            System.out.println("***Memoire***");
            System.out.println("Titre: " + memoire.getTitre());
            System.out.println("Nom du candidat: " + memoire.getNomCandidat());
            System.out.println("Titre du mémoire: " + memoire.getTitreMemoire());
            System.out.println("Date de soutenance: " + memoire.getDateSoutenance());
            System.out.println("Localisation: " + memoire.getLocalisation());
            System.out.println("Nombre d'exemplaires: " + memoire.getNbExemplaires());
        } else if (document instanceof Article article) {
            System.out.println("***Article***");
            System.out.println("Titre: " + article.getTitre());
            System.out.println("Auteur: " + article.getAuteur());
            System.out.println("Date de publication: " + article.getDatePublication());
            System.out.println("Localisation: " + article.getLocalisation());
            System.out.println("Nombre d'exemplaires: " + article.getNbExemplaires());
        }
}


    
}
