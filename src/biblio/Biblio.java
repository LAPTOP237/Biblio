/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package biblio;

import biblio.services.AdherentManager;
import java.util.*;
import java.sql.*;

/**
 *
 * @author LINJOUOM ALAIN P
 */
public class Biblio {
/**
     * 
     */
    public List<Adherent> adherents;

    /**
     * 
     */
    public List<Document> documents;

    /**
     * 
     */
    public List<Emprunt> emprunts;

    /**
     * 
     */
    public List<Utilisateur> utilisateurs;
    
     /**
     * Debut Mes Menus
     */
    
     /**
     * MENU PRINCIPAL
     */
    public void menuPrincipal(){
        int choix;
         do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("*********************");
            System.out.println("***MENU PRINCIPAL****");
            System.out.println("*********************");
            System.out.println("");
            System.out.println("Faites un choix");
            System.out.println("1. Gestion Adherents");
            System.out.println("2. Gestion Documents");
            System.out.println("3. Gestion Emprunts");
            System.out.println("4. Gestion Utilisateurs");
            System.out.println("5. Statistiques");
            System.out.println("0. Quitter");
            choix = scanner.nextInt();
                switch (choix) {
                    case 1 -> {
                        // Fonction menu Gestion Adherents
                        this.menuGestAdherents();
                    }
                    case 2 -> {
                        // Fonction menu Gestion Documents
                        this.menuGestDocuments();
                    }
                    case 3 -> {
                        // Fonction menu Gestion Emprunts
                        this.menuGestEmprunts();
                    }
                    case 4 -> {
                        // Fonction menu Gestion Utilisateurs
                        this.menuGestUtilisateurs();
                    }
                    case 5 -> {
                        // Fonction pour afficher les stats
                        this.stats();
                    }
                    case 0 -> System.out.println("Au revoir !");
                    default -> System.out.println("Choix invalide, veuillez réessayer.");
                }
            } while (choix != 0);
    }
     /**
     * MENU Gestion Adherents
     */
    public void menuGestAdherents(){
        String choix;
         do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("************************");
            System.out.println("***Gestion Adherents****");
            System.out.println("************************");
            System.out.println("");
            System.out.println("Faites un choix");
            System.out.println("1. Ajouter un Adherent");
            System.out.println("2. Modifier un Adherent");
            System.out.println("3. Supprimer un Adherent");
            System.out.println("4. Lister les Adherents");
            System.out.println("5. Effectuer une recherche");
            System.out.println("00. MENU PRINCIPAL");
            System.out.println("0. Quitter");
            choix = scanner.next();
                switch (choix) {
                    case "1" -> {
                        // Fonction Ajouter un Adherent
                    }
                    case "2" -> {
                        // Fonction Modifier un Adherent
                    }
                    case "3" -> {
                        // Fonction Supprimer un Adherent
                    }
                    case "4" -> {
                        // Fonction Lister les Adherents
                    }
                    case "5" -> {
                        // Fonction Effectuer une recherche
                        this.menuRechercheAdherent();
                    }
                    case "00" -> {
                        this.menuPrincipal();
                    }
                    case "0" -> System.out.println("Au revoir !");
                    default -> System.out.println("Choix invalide, veuillez réessayer.");
                }
            } while (!"0".equals(choix));
    }
     /**
     * MENU Gestion Documents
     */
    public void menuGestDocuments(){
        String choix;
         do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("************************");
            System.out.println("***Gestion Documents****");
            System.out.println("************************");
            System.out.println("");
            System.out.println("Faites un choix");
            System.out.println("1. Ajouter un Document");
            System.out.println("2. Modifier un Document");
            System.out.println("3. Supprimer un Document");
            System.out.println("4. Lister les Documents");
            System.out.println("5. Effectuer une recherche");
            System.out.println("00. MENU PRINCIPAL");
            System.out.println("0. Quitter");
            choix = scanner.next();
                switch (choix) {
                    case "1" -> {
                        // Fonction Ajouter un Document
                    }
                    case "2" -> {
                        // Fonction Modifier un Document
                    }
                    case "3" -> {
                        // Fonction Supprimer un Document
                    }
                    case "4" -> {
                        // Fonction Lister les Document
                    }
                    case "5" -> {
                        // Fonction Effectuer une recherche
                        this.menuRechercheDocument();
                    }
                    case "00" -> {
                        this.menuPrincipal();
                    }
                    case "0" -> System.out.println("Au revoir !");
                    default -> System.out.println("Choix invalide, veuillez réessayer.");
                }
            } while (!"0".equals(choix));
    }
     /**
     * MENU Gestion Emprunts
     */
    public void menuGestEmprunts(){
        String choix;
         do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("************************");
            System.out.println("***Gestion Emprunts****");
            System.out.println("************************");
            System.out.println("");
            System.out.println("Faites un choix");
            System.out.println("1. Ajouter un Emprunt");
            System.out.println("2. Modifier un Emprunt");
            System.out.println("3. Supprimer un Emprunt");
            System.out.println("4. Lister les Emprunts");
            System.out.println("5. Effectuer une recherche");
            System.out.println("6. Enregistrer un retour");
            System.out.println("7. Lister les retardataires");
            System.out.println("00. MENU PRINCIPAL");
            System.out.println("0. Quitter");
            choix = scanner.next();
                switch (choix) {
                    case "1" -> {
                        // Fonction Ajouter un Emprunt
                    }
                    case "2" -> {
                        // Fonction Modifier un Emprunt
                    }
                    case "3" -> {
                        // Fonction Supprimer un Emprunt
                    }
                    case "4" -> {
                        // Fonction Lister les Emprunts
                    }
                    case "5" -> {
                        // Fonction Effectuer une recherche
                        this.menuRechercheEmprunt();
                    }
                    case "6" -> {
                        // Fonction Enregistrer un retour
                    }
                    case "7" -> {
                        // Fonction Lister les retardataires
                    }
                    case "00" -> {
                        this.menuPrincipal();
                    }
                    case "0" -> System.out.println("Au revoir !");
                    default -> System.out.println("Choix invalide, veuillez réessayer.");
                }
            } while (!"0".equals(choix));
    }
      /**
     * MENU Gestion Utilisateurs
     */
    public void menuGestUtilisateurs(){
        String choix;
         do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("************************");
            System.out.println("***Gestion Utilisateurs****");
            System.out.println("************************");
            System.out.println("");
            System.out.println("Faites un choix");
            System.out.println("1. Ajouter un Utilisateur");
            System.out.println("2. Modifier un Utilisateur");
            System.out.println("3. Supprimer un Utilisateur");
            System.out.println("4. Lister les Utilisateurs");
            System.out.println("5. Effectuer une recherche");
            System.out.println("6. Modifier Etat d\'un utilisateur");
            System.out.println("00. MENU PRINCIPAL");
            System.out.println("0. Quitter");
            choix = scanner.next();
                switch (choix) {
                    case "1" -> {
                        // Fonction Ajouter un Utilisateur
                    }
                    case "2" -> {
                        // Fonction Modifier un Utilisateur
                    }
                    case "3" -> {
                        // Fonction Supprimer un Utilisateur
                    }
                    case "4" -> {
                        // Fonction Lister les Utilisateurs
                    }
                    case "5" -> {
                        // Fonction Effectuer une recherche
                        this.menuRechercheUtilisateur();
                    }
                    case "6" -> {
                        // Fonction Modifier Etat d\'un utilisateur
                    }
                    case "00" -> {
                        this.menuPrincipal();
                    }
                    case "0" -> System.out.println("Au revoir !");
                    default -> System.out.println("Choix invalide, veuillez réessayer.");
                }
            } while (!"0".equals(choix));
    }
     /**
     * MENU Recherche Adherents
     */
    public void menuRechercheAdherent(){
        String choix;
         do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("***Recherche Adherent****");
            System.out.println("1. Par identifiant");
            System.out.println("2. Par Type");
            System.out.println("3. Par Nom");
            System.out.println("#. Revenir");
            System.out.println("00. MENU PRINCIPAL");
            System.out.println("0. Quitter");
            choix = scanner.next();
                switch (choix) {
                    case "1" -> {
                        // Fonction de recherche par id
                    }
                    case "2" -> {
                        // Fonction de recherche par type
                    }
                    case "3" -> {
                        // Fonction de recherche par Nom
                    }
                    case "#" -> {
                        // Revenir au menu Gestion Adherents
                        this.menuGestAdherents();
                    }
                    case "00" -> {
                        this.menuPrincipal();
                    }
                    case "0" -> System.out.println("Au revoir !");
                    default -> System.out.println("Choix invalide, veuillez réessayer.");
                }
            } while (!"0".equals(choix));
    }
     /**
     * MENU Recherche Documents
     */
    public void menuRechercheDocument(){
        String choix;
         do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("***Recherche Document****");
            System.out.println("1. Par identifiant");
            System.out.println("2. Par Type");
            System.out.println("3. Par Nom");
            System.out.println("#. Revenir");
            System.out.println("00. MENU PRINCIPAL");
            System.out.println("0. Quitter");
            choix = scanner.next();
                switch (choix) {
                    case "1" -> {
                        // Fonction de recherche par id
                    }
                    case "2" -> {
                        // Fonction de recherche par type
                    }
                    case "3" -> {
                        // Fonction de recherche par Nom
                    }
                    case "#" -> {
                        // Revenir au menu Gestion Documents
                        this.menuGestDocuments();
                    }
                    case "00" -> {
                        this.menuPrincipal();
                    }
                    case "0" -> System.out.println("Au revoir !");
                    default -> System.out.println("Choix invalide, veuillez réessayer.");
                }
            } while (!"0".equals(choix));
    }
    /**
     * MENU Recherche Emprunts
     */
    public void menuRechercheEmprunt(){
        String choix;
         do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("***Recherche Document****");
            System.out.println("1. Par identifiant");
            System.out.println("2. Par Date Limite");
            System.out.println("3. Par Date Debut");
            System.out.println("4. Par Date Retour");
            System.out.println("#. Revenir");
            System.out.println("00. MENU PRINCIPAL");
            System.out.println("0. Quitter");
            choix = scanner.next();
                switch (choix) {
                    case "1" -> {
                        // Fonction de recherche par id
                    }
                    case "2" -> {
                        // Fonction de recherche par intervale Date limite
                    }
                    case "3" -> {
                        // Fonction de recherche par intervale date Debut
                    }
                    case "4" -> {
                        // Fonction de recherche par intervale date Retour
                    }
                    case "#" -> {
                        // Revenir au menu Gestion Emprunts
                        this.menuGestEmprunts();
                    }
                    case "00" -> {
                        this.menuPrincipal();
                    }
                    case "0" -> System.out.println("Au revoir !");
                    default -> System.out.println("Choix invalide, veuillez réessayer.");
                }
            } while (!"0".equals(choix));
    }
    /**
     * MENU Recherche Utilisateurs
     */
    public void menuRechercheUtilisateur(){
        String choix;
         do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("***Recherche Utilisateur****");
            System.out.println("1. Par nom");
            System.out.println("2. Par identifiant");
            System.out.println("#. Revenir");
            System.out.println("00. MENU PRINCIPAL");
            System.out.println("0. Quitter");
            choix = scanner.next();
                switch (choix) {
                    case "1" -> {
                        // Fonction de recherche par nom
                    }
                    case "2" -> {
                        // Fonction de recherche par identifiant
                    }
                    case "#" -> {
                        // Revenir au menu Gestion User
                        this.menuGestDocuments();
                    }
                    case "00" -> {
                        this.menuPrincipal();
                    }
                    case "0" -> System.out.println("Au revoir !");
                    default -> System.out.println("Choix invalide, veuillez réessayer.");
                }
            } while (!"0".equals(choix));
    }
     /**
     * Statistiques
     */
    public void stats(){
        //code pour afficher les stats
       System.out.println("**********************"); 
       System.out.println("****STATISTIQUES******"); 
       System.out.println("**********************");
       System.out.println("-> Adherents : 30");
       System.out.println("  10 Etudiants");
       System.out.println("  10 Enseignants");
       System.out.println("  10 Visiteurs");
       System.out.println("-> Emprunts : 30");
       System.out.println("  10 En cours");
       System.out.println("  10 Depassés");
       System.out.println("  10 Restitués");
       System.out.println("-> Documents : 40");
       System.out.println("  10 Livres");
       System.out.println("  10 Magazines");
       System.out.println("  10 Articles");
       System.out.println("  10 Memoires");
       System.out.println("");
       System.out.println("Appuyez sur # pour revenir ou sur une autre touche pour Quitter");
       Scanner scanner = new Scanner(System.in);
       String choix = scanner.next();
                switch (choix) {
                    case "#" -> {
                        // Revenir au menu principal
                        this.menuPrincipal();
                    }
                    default -> System.out.println("Au revoir !");
                }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Bon retour sur BiblioSur");
       // Biblio bibliotheque = new Biblio();
       // bibliotheque.menuPrincipal();
        try {
            // Ajouter un adhérent
            Adherent nouvelAdherent = new Adherent("Doe", "John", "123 rue de Paris", 1);
            AdherentManager.ajouterAdherent(nouvelAdherent);

            // Afficher la liste des adhérents
            List<Adherent> listeAdherents = AdherentManager.listerAdherents();
            System.out.println("Liste des adhérents : ");
            for (Adherent adherent : listeAdherents) {
                System.out.println(adherent.nom);
            }

            // Rechercher un adhérent par son identifiant
            int idAdherent = 1;
            Adherent adherentParId = AdherentManager.rechercherAdherentParId(idAdherent);
            System.out.println("Adhérent trouvé par identifiant : " + adherentParId);

            // Rechercher des adhérents par leur type
            int typeAdherent = 1; // Exemple : 1 pour Etudiant
            List<Adherent> adherentsParType = AdherentManager.rechercherAdherentParType(typeAdherent);
            System.out.println("Adhérents trouvés par type : ");
            for (Adherent adherent : adherentsParType) {
                System.out.println(adherent.nom);
            }

            // Rechercher des adhérents par leur nom
            String nomAdherent = "Doe"; // Exemple : recherche les adhérents dont le nom contient "Doe"
            List<Adherent> adherentsParNom = AdherentManager.rechercherAdherentParNom(nomAdherent);
            System.out.println("Adhérents trouvés par nom : ");
            for (Adherent adherent : adherentsParNom) {
                System.out.println(adherent);
            }

            // Modifier un adhérent
            nouvelAdherent.setAdresse("456 avenue des Champs-Élysées");
            AdherentManager.modifierAdherent(nouvelAdherent);

            // Supprimer un adhérent
            int idAdherentASupprimer = 1; // Exemple : supprime l'adhérent avec l'identifiant 1
            AdherentManager.supprimerAdherent(idAdherentASupprimer);

        } catch (SQLException e) {
         e.printStackTrace();   
        }
    }
        
}
    

