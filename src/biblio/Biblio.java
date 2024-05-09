/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package biblio;

import biblio.services.StatistiquesManager;
import biblio.services.AdherentManager;
import static biblio.services.AdherentManager.estEligibleEmprunt;
import biblio.services.DocumentManager;
import biblio.services.LivreManager;
import biblio.services.MemoireManager;
import biblio.services.MagazineManager;
import biblio.services.ArticleManager;
import static biblio.services.DocumentManager.estDisponiblePourEmprunt;
import biblio.services.EmpruntManager;
import biblio.services.UtilisateurManager;
import static biblio.utils.DateUtils.convertDateToString;
import java.util.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                        // Actions pour Ajouter un Adherent
                        System.out.println("=== Ajouter un adhérent ===");
                        System.out.print("Nom : ");
                        scanner.nextLine(); // Consommer le retour à la ligne
                        String nom = scanner.nextLine();
                        System.out.print("Prénom : ");
                        scanner.nextLine(); // Consommer le retour à la ligne
                        String prenom = scanner.nextLine();
                        System.out.print("Adresse : ");
                        scanner.nextLine(); // Consommer le retour à la ligne
                        String adresse = scanner.nextLine();
                        System.out.print("Type d'adhérent (1: Etudiant, 2: Enseignant, 3: Visiteur) : ");
                        int typeAdherent = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer le retour à la ligne
                        
                        Adherent adherent = new Adherent(nom,prenom,adresse,typeAdherent);
                        try {
                            // Appel de la méthode pour ajouter l'adhérent à la base de données
                            AdherentManager.ajouterAdherent(adherent);
                            System.out.println("Adhérent ajouté avec succès ! \n Appuyer sur Entrée pour continuer");
        
                        } catch (SQLException e) {
                                System.out.println("Erreur lors de l'ajout de l'adhérent : " + e.getMessage() + " \n Appuyer sur Entrée pour continuer");
                            }
                        }
                    case "2" -> {
                        // Actions pour Modifier un Adherent
                        System.out.println("=== Modifier un adhérent ===");
                        System.out.print("Identifiant de l'adhérent à modifier : ");
                        int identifiant = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer le retour à la ligne

                        try {
                            Adherent adherent = AdherentManager.rechercherAdherentParId(identifiant);
                            if (adherent != null) {
                                System.out.print("Nouveau nom (laisser vide pour ne pas modifier) : ");
                                String nom = scanner.nextLine();
                                if (!nom.isEmpty()) {
                                    adherent.setNom(nom);
                                }
                                System.out.print("Nouveau prénom (laisser vide pour ne pas modifier) : ");
                                String prenom = scanner.nextLine();
                                if (!prenom.isEmpty()) {
                                    adherent.setPrenom(prenom);
                                }
                                System.out.print("Nouvelle adresse (laisser vide pour ne pas modifier) : ");
                                String adresse = scanner.nextLine();
                                if (!adresse.isEmpty()) {
                                    adherent.setAdresse(adresse);
                                }
                                System.out.print("Nouveau type d'adhérent (1: Etudiant, 2: Enseignant, 3: Visiteur, laisser vide pour ne pas modifier) : ");
                                String typeAdherentStr = scanner.nextLine();
                                if (!typeAdherentStr.isEmpty()) {
                                    int typeAdherent = Integer.parseInt(typeAdherentStr);
                                    adherent.setTypeAdherent(typeAdherent);
                                }

                                AdherentManager.modifierAdherent(adherent);
                                System.out.println("Adhérent modifié avec succès !");
                            } else {
                                System.out.println("Aucun adhérent trouvé avec l'identifiant " + identifiant);
                            }
                        } catch (SQLException e) {
                            System.out.println("Erreur lors de la modification de l'adhérent : " + e.getMessage());
                        }
                    }
                    case "3" -> {
                        // Actions pour Supprimer un Adherent
                        System.out.println("=== Supprimer un adhérent ===");
                        System.out.print("Identifiant de l'adhérent à supprimer : ");
                        int identifiant = scanner.nextInt();
                        scanner.nextLine(); // Consommer le retour à la ligne

                        try {
                            Adherent adherent = AdherentManager.rechercherAdherentParId(identifiant);
                            if (adherent != null) {
                                AdherentManager.supprimerAdherent(identifiant);
                                System.out.println("Adhérent supprimé avec succès !");
                            } else {
                                System.out.println("Aucun adhérent trouvé avec l'identifiant " + identifiant);
                            }
                        } catch (SQLException e) {
                            System.out.println("Erreur lors de la suppression de l'adhérent : " + e.getMessage());
                        }
                    }
                    case "4" -> {
                        // Actions pour Lister les Adherents
                        try {
                            List<Adherent> adherents = AdherentManager.listerAdherents();
                            System.out.println("=== Liste des adhérents ===");
                            System.out.printf("%-10s | %-15s | %-15s | %-40s | %-15s | %-20s | %-15s%n",
                                    "ID", "Nom", "Prénom", "Adresse", "Type", "Nombre Max Emprunts", "Durée Prêt");
                            System.out.println("-".repeat(120));
                            for (Adherent adherent : adherents) {
                                System.out.printf("%-10d | %-15s | %-15s | %-40s | %-15d | %-20d | %-15d%n",
                                        adherent.getIdentifiant(), adherent.getNom(), adherent.getPrenom(),
                                        adherent.getAdresse(), adherent.getTypeAdherent(),
                                        adherent.getNombreEmpruntsMax(), adherent.getDureePret());
                            }
                        } catch (SQLException e) {
                            System.out.println("Erreur lors de la récupération des adhérents : " + e.getMessage());
                        }
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
    public void menuGestDocuments() {
        String choix;
         do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("************************");
            System.out.println("***Gestion Documents****");
            System.out.println("************************");
            System.out.println("Faites un choix");
            System.out.println("1. Ajouter un Document");
            System.out.println("2. Modifier un Document");
            System.out.println("3. Supprimer un Document");
            System.out.println("4. Lister les Documents");
            System.out.println("5. Recherche par type");
            System.out.println("00. MENU PRINCIPAL");
            System.out.println("0. Quitter");
            choix = scanner.next();
                switch (choix) {
                    case "1" -> {
                        // Fonction Ajouter un Document
                        System.out.println("=== Ajouter un document ===");
                        System.out.print("Titre du document : ");
                        String titre = scanner.nextLine();
                        System.out.print("Localisation du document : ");
                        String localisation = scanner.nextLine();
                        System.out.print("Nombre d'exemplaires : ");
                        int nb = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer le retour à la ligne

                        // Création d'un nouvel objet Document
                        Document document = new Document();
                        document.setTitre(titre);
                        document.setLocalisation(localisation);
                        document.setNbExemplaires(nb);

                        try {
                            int code_doc = DocumentManager.ajouterDocument(document);
                            System.out.print("Type du document (1.Livre, 2.Mémoire, 3.Article, 4.Magazine) : ");
                            int type = scanner.nextInt();
                            switch (type){
                                    case 1 ->{
                                            System.out.println("=== Ajouter un livre ===");
                                            System.out.print("Auteur du livre : ");
                                            String auteur = scanner.nextLine();
                                            System.out.print("Editeur du livre : ");
                                            String editeur = scanner.nextLine();
                                            System.out.print("Date d'édition du livre (YYYY-MM-DD) : ");
                                            String dateEditionStr = scanner.nextLine();
                                            java.util.Date dateEdition;
                                            try {
                                                dateEdition = new SimpleDateFormat("yyyy-MM-dd").parse(dateEditionStr);
                                            } catch (ParseException e) {
                                                System.out.println("Format de date invalide. Utilisez le format YYYY-MM-DD.");
                                                return;
                                            }
                                             // Création d'un nouvel objet Livre
                                            Livre livre = new Livre(titre,localisation,nb,auteur,editeur,dateEdition);
                                            livre.setCodeDoc(code_doc);
                                            LivreManager.ajouterLivre(livre);
                                            System.out.println("Livre ajoutée avec succés");
                                    }


                                    case 2 ->{
                                            System.out.println("=== Ajouter un Memoire ===");
                                            System.out.println("Nom Candidat : ");
                                            String candidat = scanner.nextLine();
                                            System.out.println("Titre : ");
                                            String titreM = scanner.nextLine();
                                            System.out.println("Date de soutenance (YYYY-MM-DD) : ");
                                            String dateSoutenanceStr = scanner.nextLine();
                                            java.util.Date dateSoutenance;
                                            try {
                                                dateSoutenance = new SimpleDateFormat("yyyy-MM-dd").parse(dateSoutenanceStr);
                                            } catch (ParseException e) {
                                                System.out.println("Format de date invalide. Utilisez le format YYYY-MM-DD.");
                                                return;
                                            }
                                             // Création d'un nouvel objet Memoire
                                            Memoire memoire = new Memoire(titre,localisation,nb,candidat,titreM,dateSoutenance);
                                            memoire.setCodeDoc(code_doc);
                                            MemoireManager.ajouterMemoire(memoire);
                                            System.out.println("memoire ajoutée avec succés");
                                    }
                                    case 3 ->{
                                            System.out.println("=== Ajouter un Article ===");
                                            System.out.println("Auteur de l'article : ");
                                            String auteur = scanner.nextLine();
                                            System.out.println("Date de publication (YYYY-MM-DD) : ");
                                            String datePublicationStr = scanner.nextLine();
                                            java.util.Date datePublication;
                                            try {
                                                datePublication = new SimpleDateFormat("yyyy-MM-dd").parse(datePublicationStr);
                                            } catch (ParseException e) {
                                                System.out.println("Format de date invalide. Utilisez le format YYYY-MM-DD.");
                                                return;
                                            }
                                             // Création d'un nouvel objet Article
                                            Article article = new Article(titre,localisation,nb,auteur,datePublication);
                                            article.setCodeDoc(code_doc);
                                            ArticleManager.ajouterArticle(article);
                                            System.out.println("Article ajoutée avec succés");
                                    }
                                    case 4 ->{
                                            System.out.println("=== Ajouter un Magazine ===");
                                            System.out.println("Frequence de Parution : ");
                                            String frequenceParution = scanner.nextLine();
                                            // Création d'un nouvel objet Magazine
                                            Magazine magazine = new Magazine(titre,localisation,nb,frequenceParution);
                                            magazine.setCodeDoc(code_doc);
                                            MagazineManager.ajouterMagazine(magazine);
                                            System.out.println("Magazine ajoutée avec succés");
                                    }

                                    default -> System.out.println("Choix invalide, veuillez réessayer.");
                                }

                        } 
                        catch (SQLException e) {
                            System.out.println("Une erreur s'est produite lors de l'ajout du document : " + e.getMessage());
                        }
                    }
                    case "2" -> {
                    try {
                        // Fonction Modifier un Document
                        System.out.println("=== Modifier un document ===");
                        System.out.print("Identifiant du document à modifier : ");
                        int idDocument = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer le retour à la ligne

                        // Recherche du document à modifier
                        Document documentAModifier;
                        documentAModifier = DocumentManager.rechercherDocumentParId2(idDocument);
                        
                        if (documentAModifier == null) {
                            System.out.println("Document non trouvé.");
                            return;
                        }
                        // Demande de saisie des nouvelles informations
                        System.out.print("Nouveau titre (laisser vide pour ne pas modifier) : ");
                        String nouveauTitre = scanner.nextLine();
                        if (!nouveauTitre.isEmpty()) {
                            documentAModifier.setTitre(nouveauTitre);
                        }

                        System.out.print("Nouvelle localisation (laisser vide pour ne pas modifier) : ");
                        String nouvelleLocalisation = scanner.nextLine();
                        if (!nouvelleLocalisation.isEmpty()) {
                            documentAModifier.setLocalisation(nouvelleLocalisation);
                        }

                        System.out.print("Nouveau nombre d'exemplaires (0 pour ne pas modifier) : ");
                        int nouveauNbExemplaires = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer le retour à la ligne
                        if (nouveauNbExemplaires != 0) {
                            documentAModifier.setNbExemplaires(nouveauNbExemplaires);
                        }
                        
                        
                        // Appel à la méthode de modification du document
                        int id = DocumentManager.modifierDocument(documentAModifier);
                        
                        if (documentAModifier instanceof Livre) {
                            System.out.println("=== Modifier un livre ===");
                            
                            // Recherche du livre à modifier
                            Livre livreAModifier = LivreManager.rechercherLivreParId(id);
                            if (livreAModifier == null) {
                                System.out.println("Livre non trouvé.");
                                return;
                            }
                            
                            // Affichage des informations actuelles du livre
                            System.out.println("Informations actuelles du livre :");
                            System.out.println("Auteur : " + livreAModifier.getAuteur());
                            System.out.println("Editeur : " + livreAModifier.getEditeur());
                            System.out.println("Date d'édition : " + livreAModifier.getDateEdition());
                            
                            // Demande de saisie des nouvelles informations
                            System.out.print("Nouvel auteur (laisser vide pour ne pas modifier) : ");
                            String nouveauAuteur = scanner.nextLine();
                            if (!nouveauAuteur.isEmpty()) {
                                livreAModifier.setAuteur(nouveauAuteur);
                            }
                            
                            System.out.print("Nouvel editeur (laisser vide pour ne pas modifier) : ");
                            String nouvelEditeur = scanner.nextLine();
                            if (!nouvelEditeur.isEmpty()) {
                                livreAModifier.setEditeur(nouvelEditeur);
                            }
                            
                            System.out.print("Nouvelle date d'édition (YYYY-MM-DD, laisser vide pour ne pas modifier) : ");
                            String nouvelleDateEditionStr = scanner.nextLine();
                            if (!nouvelleDateEditionStr.isEmpty()) {
                                try {
                                    java.util.Date nouvelleDateEdition = new SimpleDateFormat("yyyy-MM-dd").parse(nouvelleDateEditionStr);
                                    livreAModifier.setDateEdition(nouvelleDateEdition);
                                } catch (ParseException e) {
                                    System.out.println("Format de date invalide. Utilisez le format YYYY-MM-DD.");
                                    return;
                                }
                            }
                            
                            // Appel à la méthode de modification du livre
                            LivreManager.modifierLivre(livreAModifier);
                            System.out.println("Livre modifié avec succès.");
                            
                        } else if (documentAModifier instanceof Memoire) {
                            System.out.println("=== Modifier un mémoire ===");
                            

                            // Recherche du mémoire à modifier
                            Memoire memoireAModifier = MemoireManager.rechercherMemoireParId(id);
                            if (memoireAModifier == null) {
                                System.out.println("Mémoire non trouvé.");
                                return;
                            }

                            // Affichage des informations actuelles du mémoire
                            System.out.println("Informations actuelles du mémoire :");
                            System.out.println("Candidat : " + memoireAModifier.getNomCandidat());
                            System.out.println("Titre : " + memoireAModifier.getTitre());
                            System.out.println("Date de soutenance : " + memoireAModifier.getDateSoutenance());

                            // Demande de saisie des nouvelles informations
                            System.out.print("Nouveau candidat (laisser vide pour ne pas modifier) : ");
                            String nouveauCandidat = scanner.nextLine();
                            if (!nouveauCandidat.isEmpty()) {
                                memoireAModifier.setNomCandidat(nouveauCandidat);
                            }

                            System.out.print("Nouveau titre (laisser vide pour ne pas modifier) : ");
                            String nouveauTitre1 = scanner.nextLine();
                            if (!nouveauTitre.isEmpty()) {
                                memoireAModifier.setTitreMemoire(nouveauTitre1);
                            }

                            System.out.print("Nouvelle date de soutenance (YYYY-MM-DD, laisser vide pour ne pas modifier) : ");
                            String nouvelleDateSoutenanceStr = scanner.nextLine();
                            if (!nouvelleDateSoutenanceStr.isEmpty()) {
                                try {
                                    java.util.Date nouvelleDateSoutenance = new SimpleDateFormat("yyyy-MM-dd").parse(nouvelleDateSoutenanceStr);
                                    memoireAModifier.setDateSoutenance(nouvelleDateSoutenance);
                                } catch (ParseException e) {
                                    System.out.println("Format de date invalide. Utilisez le format YYYY-MM-DD.");
                                    return;
                                }
                            }

                            // Appel à la méthode de modification du mémoire
                            MemoireManager.modifierMemoire(memoireAModifier);
                            System.out.println("Mémoire modifié avec succès.");
                        } else if (documentAModifier instanceof Article) {
                            System.out.println("=== Modifier un article ===");
                            System.out.print("Identifiant de l'article à modifier : ");

                            // Recherche de l'article à modifier
                            Article articleAModifier = ArticleManager.rechercherArticleParId(id);
                            if (articleAModifier == null) {
                                System.out.println("Article non trouvé.");
                                return;
                            }

                            // Affichage des informations actuelles de l'article
                            System.out.println("Informations actuelles de l'article :");
                            System.out.println("Auteur : " + articleAModifier.getAuteur());
                            System.out.println("Date de publication : " + articleAModifier.getDatePublication());

                            // Demande de saisie des nouvelles informations
                            System.out.print("Nouvel auteur (laisser vide pour ne pas modifier) : ");
                            String nouvelAuteur = scanner.nextLine();
                            if (!nouvelAuteur.isEmpty()) {
                                articleAModifier.setAuteur(nouvelAuteur);
                            }

                            System.out.print("Nouvelle date de publication (YYYY-MM-DD, laisser vide pour ne pas modifier) : ");
                            String nouvelleDatePublicationStr = scanner.nextLine();
                            if (!nouvelleDatePublicationStr.isEmpty()) {
                                try {
                                    java.util.Date nouvelleDatePublication = new SimpleDateFormat("yyyy-MM-dd").parse(nouvelleDatePublicationStr);
                                    articleAModifier.setDatePublication(nouvelleDatePublication);
                                } catch (ParseException e) {
                                    System.out.println("Format de date invalide. Utilisez le format YYYY-MM-DD.");
                                    return;
                                }
                            }

                            // Appel à la méthode de modification de l'article
                            ArticleManager.modifierArticle(articleAModifier);
                            System.out.println("Article modifié avec succès.");
                        } else if (documentAModifier instanceof Magazine) {
                            System.out.println("=== Modifier un magazine ===");

                            // Recherche du magazine à modifier
                            Magazine magazineAModifier = MagazineManager.rechercherMagazineParId(id);
                            if (magazineAModifier == null) {
                                System.out.println("Magazine non trouvé.");
                                return;
                            }

                            // Affichage des informations actuelles du magazine
                            System.out.println("Informations actuelles du magazine :");
                            System.out.println("Fréquence de parution : " + magazineAModifier.getFrequenceParution());

                            // Demande de saisie des nouvelles informations
                            System.out.print("Nouvelle fréquence de parution (laisser vide pour ne pas modifier) : ");
                            String nouvelleFrequenceParution = scanner.nextLine();
                            if (!nouvelleFrequenceParution.isEmpty()) {
                                magazineAModifier.setFrequenceParution(nouvelleFrequenceParution);
                            }

                            // Appel à la méthode de modification du magazine
                            MagazineManager.modifierMagazine(magazineAModifier);
                            System.out.println("Magazine modifié avec succès.");
                        }
                        System.out.println("Document modifié avec succès.");
                    } catch (SQLException ex) {
                        Logger.getLogger(Biblio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }



                    case "3" -> {
                    try {
                        // Fonction Supprimer un Document
                        System.out.println("=== Supprimer un document ===");
                        System.out.print("Identifiant du document à supprimer : ");
                        int idDocumentASupprimer = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer le retour à la ligne

                        // Recherche du document à supprimer
                        Document documentASupprimer = DocumentManager.rechercherDocumentParId(idDocumentASupprimer);
                        if (documentASupprimer == null) {
                            System.out.println("Document non trouvé.");
                            return;
                        }

                        // Confirmation de la suppression
                        System.out.println("Êtes-vous sûr de vouloir supprimer le document suivant ?");
                        System.out.println("Titre : " + documentASupprimer.getTitre());
                        System.out.println("Localisation : " + documentASupprimer.getLocalisation());
                        System.out.println("Nombre d'exemplaires : " + documentASupprimer.getNbExemplaires());
                        System.out.print("Confirmez la suppression (o/n) : ");
                        String confirmation = scanner.nextLine();

                        if (confirmation.equalsIgnoreCase("o")) {
                            // Appel à la méthode de suppression du document
                            try {
                                DocumentManager.supprimerDocument(idDocumentASupprimer);
                                System.out.println("Document supprimé avec succès.");
                            } catch (SQLException e) {
                                System.out.println("Une erreur s'est produite lors de la suppression du document : " + e.getMessage());
                            }
                        } else {
                            System.out.println("Suppression annulée.");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Biblio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }

                    case "4" -> {
                        // Fonction Lister les Document
                        // List<Document> documents = DocumentManager.listerDocuments();
                        List<Livre> documents1 = LivreManager.afficherListeLivres() ;
                        List<Magazine> documents2 = MagazineManager.afficherListeMagazines();
                        List<Memoire> documents3 = MemoireManager.afficherListeMemoires();
                        List<Article> documents4 = ArticleManager.afficherListeArticles();
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("| %-10s | %-30s | %-20s | %-20s | %-20s | %-20s |\n", "ID", "Titre", "Localisation", "Nombre d'exemplaires", "Type", "Auteur/Editeur/Candidat");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                        String type;
                        String auteurEditeurCandidat;
                        for (Livre document : documents1) {
                            type = "Livre";
                            auteurEditeurCandidat = ((Livre) document).getAuteur() + "/" + ((Livre) document).getEditeur();
                            System.out.printf("| %-10d | %-30s | %-20s | %-20d | %-20s | %-20s |\n", document.getCode(), document.getTitre(), document.getLocalisation(), document.getNbExemplaires(), type, auteurEditeurCandidat);
                        }
                        for (Memoire document : documents3) {
                            type = "Mémoire";
                            auteurEditeurCandidat = ((Memoire) document).getNomCandidat();
                            System.out.printf("| %-10d | %-30s | %-20s | %-20d | %-20s | %-20s |\n", document.getCode(), document.getTitre(), document.getLocalisation(), document.getNbExemplaires(), type, auteurEditeurCandidat);
                        }
                        for (Magazine document : documents2) {
                            type = "Magazine";
                            auteurEditeurCandidat = ((Magazine) document).getFrequenceParution();
                            
                            System.out.printf("| %-10d | %-30s | %-20s | %-20d | %-20s | %-20s |\n", document.getCode(), document.getTitre(), document.getLocalisation(), document.getNbExemplaires(), type, auteurEditeurCandidat);
                        }
                        for (Article document : documents4) {
                            type = "Article";
                            auteurEditeurCandidat = ((Article) document).getAuteur();
                            System.out.printf("| %-10d | %-30s | %-20s | %-20d | %-20s | %-20s |\n", document.getCode(), document.getTitre(), document.getLocalisation(), document.getNbExemplaires(), type, auteurEditeurCandidat);
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
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
                                try {
                                    // Fonction Ajouter un Emprunt
                                    System.out.println("=== Ajout d'un emprunt ===");
                                    // Demander et récupérer l'identifiant de l'adhérent
                                    System.out.print("Identifiant de l'adhérent : ");
                                    int idAdherent = scanner.nextInt();
                                    scanner.nextLine(); // Consommer le retour à la ligne
                                    
                                    // Vérifier si l'adhérent est éligible
                                    Adherent adherent = AdherentManager.rechercherAdherentParId(idAdherent);
                                    if (!estEligibleEmprunt(adherent)) {
                                        System.out.println("L'adhérent n'est pas éligible pour emprunter.");
                                        return;
                                    }
                                    
                                    // Demander et récupérer le code du document
                                    System.out.print("Code du document : ");
                                    int codeDocument = scanner.nextInt();
                                    scanner.nextLine(); // Consommer le retour à la ligne

                                    // Vérifier si le document est disponible
                                    Document document = DocumentManager.rechercherDocumentParId(codeDocument);
                                    if (!estDisponiblePourEmprunt(document)) {
                                        System.out.println("Le document n'est pas disponible pour l'emprunt.");
                                        return;
                                    }
                                    // Demander et récupérer les dates de début et de limite
                                    System.out.print("Date de début (YYYY-MM-DD) : ");
                                    String dateDebutStr = scanner.nextLine();
                                    java.util.Date dateDebut;
                                    try {
                                        dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse(dateDebutStr);
                                    } catch (ParseException e) {
                                        System.out.println("Format de date invalide. Utilisez le format YYYY-MM-DD.");
                                        return;
                                    }
                                    
                                    // Création de l'objet Emprunt
                                    Emprunt emprunt = new Emprunt(adherent, document, dateDebut);
                                    
                                    try {
                                        EmpruntManager.ajouterEmprunt(emprunt);
                                        System.out.println("Emprunt ajouté avec succès !");
                                    } catch (SQLException e) {
                                        System.out.println("Une erreur s'est produite lors de l'ajout de l'emprunt : " + e.getMessage());
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(Biblio.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                    case "2" -> {
                                try {
                                            // Fonction Modifier un Emprunt
                                            System.out.println("=== Modification d'un emprunt ===");
                                            // Demander et récupérer l'identifiant de l'emprunt à modifier
                                            System.out.print("Identifiant de l'emprunt : ");
                                            int idEmprunt = scanner.nextInt();
                                            scanner.nextLine(); // Consommer le retour à la ligne

                                            // Rechercher l'emprunt dans la base de données
                                            Emprunt emprunt = EmpruntManager.rechercherEmpruntParId(idEmprunt);
                                            if (emprunt == null) {
                                            System.out.println("Emprunt non trouvé.");
                                            return;
                                            }

                                            // Afficher les détails de l'emprunt à modifier
                                            System.out.println("Emprunt trouvé :");
                                            System.out.println("Adhérent : " + emprunt.getAdherent().getNom() + " " + emprunt.getAdherent().getPrenom());
                                            System.out.println("Document : " + emprunt.getDocument().getTitre());
                                            System.out.println("Date de début : " + emprunt.getDateDebut());
                                            System.out.println("Date limite : " + emprunt.getDateLimite());
                                            System.out.println("Date de retour : " + (emprunt.getDateRetour() != null ? emprunt.getDateRetour() : "non retourné"));

                                            // Demander et récupérer les nouvelles dates de début et de limite
                                            System.out.print("Nouvelle date de début (YYYY-MM-DD) : ");
                                            String nouvelleDateDebutStr = scanner.nextLine();
                                            java.util.Date nouvelleDateDebut;
                                            try {
                                            nouvelleDateDebut = new SimpleDateFormat("yyyy-MM-dd").parse(nouvelleDateDebutStr);
                                            
                                            } catch (ParseException e) {
                                            System.out.println("Format de date invalide. Utilisez le format YYYY-MM-DD.");
                                            return;
                                            }

                                            // Mettre à jour l'emprunt avec les nouvelles dates
                                            emprunt.setDateDebut(nouvelleDateDebut);
                                            // Appeler la méthode de modification dans EmpruntManager
                                            try {
                                            EmpruntManager.modifierEmprunt(emprunt);
                                            System.out.println("Emprunt modifié avec succès !");
                                            } catch (SQLException e) {
                                            System.out.println("Une erreur s'est produite lors de la modification de l'emprunt : " + e.getMessage());
                                            }
                                            } catch (SQLException ex) {
                                                      Logger.getLogger(Biblio.class.getName()).log(Level.SEVERE, null, ex);
                                                         }
                                        }

               
                    case "3" -> {
                        // Fonction Supprimer un Emprunt
                        System.out.println("=== Suppression d'un emprunt ===");
                        // Demander et récupérer l'identifiant de l'emprunt à supprimer
                        System.out.print("Identifiant de l'emprunt : ");
                        int idEmprunt = scanner.nextInt();
                        scanner.nextLine(); // Consommer le retour à la ligne

                        // Rechercher l'emprunt dans la base de données
                        Emprunt emprunt = null;
                    try {
                        emprunt = EmpruntManager.rechercherEmpruntParId(idEmprunt);
                    } catch (SQLException ex) {
                        Logger.getLogger(Biblio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        if (emprunt == null) {
                            System.out.println("Emprunt non trouvé.");
                            return;
                        }

                        // Afficher les détails de l'emprunt à supprimer
                        System.out.println("Emprunt trouvé :");
                        System.out.println("Adhérent : " + emprunt.getAdherent().getNom() + " " + emprunt.getAdherent().getPrenom());
                        System.out.println("Document : " + emprunt.getDocument().getTitre());
                        System.out.println("Date de début : " + emprunt.getDateDebut());
                        System.out.println("Date limite : " + emprunt.getDateLimite());
                        System.out.println("Date de retour : " + (emprunt.getDateRetour() != null ? emprunt.getDateRetour() : "non retourné"));

                        // Demander confirmation pour la suppression
                        System.out.print("Confirmer la suppression de cet emprunt (O/N) : ");
                        String confirmation = scanner.nextLine();
                        if (confirmation.equalsIgnoreCase("O")) {
                            // Appeler la méthode de suppression dans EmpruntManager
                            try {
                                EmpruntManager.supprimerEmprunt(idEmprunt);
                                System.out.println("Emprunt supprimé avec succès !");
                            } catch (SQLException e) {
                                System.out.println("Une erreur s'est produite lors de la suppression de l'emprunt : " + e.getMessage());
                            }
                        } else {
                            System.out.println("Suppression annulée.");
                        }
                    }

                    case "4" -> {
                        // Fonction Lister les Emprunts
                        List<Emprunt> emprunts1 = null;
                    try {
                        emprunts1 = EmpruntManager.listerEmprunts();
                    } catch (SQLException ex) {
                        Logger.getLogger(Biblio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        if (emprunts1 == null) {
                            System.out.println("Aucun emprunt trouvé.");
                            return;
                        }

                        System.out.println("=== Liste des emprunts ===");
                        // En-tête du tableau
                        System.out.println("+----+---------------------+----------------------+----------------------+----------------------+");
                        System.out.println("| ID |     Adhérent         |      Document        |      Date de début    |      Date limite     |");
                        System.out.println("+----+---------------------+----------------------+----------------------+----------------------+");

                        // Affichage des emprunts
                        for (Emprunt emprunt : emprunts1) {
                            String id = String.format("%-5s", emprunt.getId());
                            String adherent = String.format("%-20s", emprunt.getAdherent().getNom() + " " + emprunt.getAdherent().getPrenom());
                            String document = String.format("%-20s", emprunt.getDocument().getTitre());
                            String dateDebut = String.format("%-20s", convertDateToString(emprunt.getDateDebut()));
                            String dateLimite = String.format("%-20s", convertDateToString(emprunt.getDateLimite()));


                            System.out.println("| " + id + " | " + adherent + " | " + document + " | " + dateDebut + " | " + dateLimite + " |");
                        }

                        // Pied du tableau
                        System.out.println("+----+---------------------+----------------------+----------------------+----------------------+");
                    }

                          
                    case "5" -> {
                        // Fonction Effectuer une recherche
                        this.menuRechercheEmprunt();
                    }
                    case "6" -> {
                        // Fonction Enregistrer un retour
                        try {
                            System.out.print("Entrez l'identifiant de l'emprunt à retourner : ");
                            int idEmprunt = scanner.nextInt();
                            scanner.nextLine(); // Consommer le retour à la ligne

                            // Vérifier si l'emprunt existe
                            Emprunt emprunt = EmpruntManager.rechercherEmpruntParId(idEmprunt);
                            if (emprunt == null) {
                                System.out.println("Emprunt non trouvé. Veuillez vérifier l'identifiant.");
                                return;
                            }

                            System.out.print("Entrez la date de retour (YYYY-MM-DD) : ");
                            String dateRetourStr = scanner.nextLine();
                            java.util.Date dateRetour;
                            try {
                                dateRetour = new SimpleDateFormat("yyyy-MM-dd").parse(dateRetourStr);
                            } catch (ParseException e) {
                                System.out.println("Format de date invalide. Utilisez le format YYYY-MM-DD.");
                                return;
                            }

                            EmpruntManager.enregistrerRetour(idEmprunt, dateRetour);
                            System.out.println("Retour enregistré avec succès.");
                        } catch (InputMismatchException e) {
                            System.out.println("Veuillez entrer un identifiant valide (entier).");
                        } catch (SQLException e) {
                            System.out.println("Une erreur s'est produite lors de l'enregistrement du retour : " + e.getMessage());
                        }

                    }
                    case "7" -> {
                    // Fonction Lister les retardataires
                    List<Adherent> retardataires = EmpruntManager.listerAdherentsRetardataires();
                    if (retardataires.isEmpty()) {
                        System.out.println("Aucun retardataire.");
                        return;
                    }
                    System.out.println("Liste des retardataires :");
                    System.out.printf("%-15s %-25s %-25s\n", "Adhérent ID", "Nom", "Prénom");
                    for (Adherent adherent : retardataires) {
                        System.out.printf("%-15s %-25s %-25s\n", adherent.getIdentifiant(), adherent.getNom(), adherent.getPrenom());
                    }
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
                        // Actions pour Ajouter un Utilisateur
                        System.out.println("=== Ajouter un Utilisateur ===");
                        System.out.print("Nom : ");
                        String nom = scanner.nextLine();
                        System.out.print("Login : ");
                        String login = scanner.nextLine();
                        System.out.print("Password : ");
                        String password = scanner.nextLine();
                        System.out.print("Role  (1: Admin, 2: SuperAdmin,) : ");
                        int role = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer le retour à la ligne
                        
                        Utilisateur utilisateur = new Utilisateur(nom,login,password,role);
                        try {
                            // Appel de la méthode pour ajouter l'utilisateur à la base de données
                            UtilisateurManager.ajouterUtilisateur(utilisateur);
                            System.out.println("Utilisateur ajouté avec succès ! \n Appuyer sur Entrée pour continuer");
        
                        } catch (SQLException e) {
                                System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage() + " \n Appuyer sur Entrée pour continuer");
                            }
                        }
                    case "2" -> {
                        // Actions pour Modifier un Utilisateur
                        System.out.println("=== Modifier un utilisateur ===");
                        System.out.print("Identifiant de l'utilisateur à modifier : ");
                        int identifiant = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer le retour à la ligne

                        try {
                            Utilisateur utilisateur = UtilisateurManager.rechercherUtilisateurParId(identifiant);
                            if (utilisateur != null) {
                                System.out.print("Nouveau nom (laisser vide pour ne pas modifier) : ");
                                String nom = scanner.nextLine();
                                if (!nom.isEmpty()) {
                                    utilisateur.setNom(nom);
                                }
                                System.out.print("Nouveau role  (1: Admin, 2: SuperAdmin, laisser vide pour ne pas modifier) : ");
                                String roleStr = scanner.nextLine();
                                if (!roleStr.isEmpty()) {
                                    int role = Integer.parseInt(roleStr);
                                    utilisateur.setRole(role);
                                }

                                UtilisateurManager.modifierUtilisateur(utilisateur);
                                System.out.println("Utilisateur modifié avec succès !");
                            } else {
                                System.out.println("Aucun Utilisateur trouvé avec l'identifiant " + identifiant);
                            }
                        } catch (SQLException e) {
                            System.out.println("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
                        }
                    }
                    case "3" -> {
                        // Actions pour Supprimer un Adherent
                        System.out.println("=== Supprimer un utilisateur ===");
                        System.out.print("Identifiant de l'utilisateur à supprimer : ");
                        int identifiant = scanner.nextInt();
                        scanner.nextLine(); // Consommer le retour à la ligne

                        try {
                            Utilisateur utilisateur = UtilisateurManager.rechercherUtilisateurParId(identifiant);
                            if (utilisateur != null) {
                                UtilisateurManager.supprimerUtilisateur(identifiant);
                                System.out.println("Utilisateur supprimé avec succès !");
                            } else {
                                System.out.println("Aucun utilisateur trouvé avec l'identifiant " + identifiant);
                            }
                        } catch (SQLException e) {
                            System.out.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
                        }
                    }
                    case "4" -> {
                        // Actions pour Lister les Adherents
                        try {
                            List<Utilisateur> utilisateurs1 = UtilisateurManager.listerUtilisateurs();
                            System.out.println("=== Liste des utilisateur ===");
                            System.out.printf("%-10s | %-15s | %-15s | %-40s | %-15s | %-20s%n",
                                    "ID", "Nom", "Login", "Password", "Role", "Etat");
                            System.out.println("-".repeat(120));
                            for (Utilisateur utilisateur : utilisateurs1) {
                                System.out.printf("%-10d | %-15s | %-15s | %-40s | %-15d | %-20d%n",
                                        utilisateur.getIdentifiant(), utilisateur.getNom(), utilisateur.getLogin(),
                                        utilisateur.getPassword(), utilisateur.getRole(),
                                        utilisateur.getEtat());
                            }
                        } catch (SQLException e) {
                            System.out.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
                        }
                    }
                    case "5" -> {
                        // Fonction Effectuer une recherche
                        this.menuRechercheUtilisateur();
                    }
                    case "6" -> {
                        // Fonction Modifier Etat 
                        System.out.println("=== Modifier etat un utilisateur ===");
                        System.out.print("Identifiant de l'utilisateur à modifier : ");
                        int identifiant = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer le retour à la ligne

                        try {
                            Utilisateur utilisateur = UtilisateurManager.rechercherUtilisateurParId(identifiant);
                            if (utilisateur != null) {
                                System.out.print("Nouveau etat  (1.bloquer 0.debloquer, laisser vide pour ne pas modifier) : ");
                                int etat = scanner.nextInt();
                                if (etat == 0 || etat == 1) {
                                    utilisateur.setEtat(etat);
                                } else {
                                    System.out.println("Mauvais !");
                                }

                                UtilisateurManager.modifierEtatUtilisateur(utilisateur);
                                System.out.println("Utilisateur modifié avec succès !");
                            } else {
                                System.out.println("Aucun Utilisateur trouvé avec l'identifiant " + identifiant);
                            }
                        } catch (SQLException e) {
                            System.out.println("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
                        }
                    
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
                        System.out.println("=== Rechercher un adhérent par ID ===");
                        System.out.print("Entrez l'identifiant de l'adhérent : ");
                        int idRecherche = scanner.nextInt();
                        try {
                            Adherent adherent = AdherentManager.rechercherAdherentParId(idRecherche);
                            if (adherent == null) {
                                System.out.println("Aucun adhérent trouvé avec cet identifiant.");
                            } else {
                                System.out.println("Résultat de la recherche :");
                                System.out.printf("%-10s | %-15s | %-15s | %-40s | %-15s | %-20s | %-15s%n",
                                        "ID", "Nom", "Prénom", "Adresse", "Type", "Nombre Max Emprunts", "Durée Prêt");
                                System.out.println("-".repeat(120));
                                System.out.printf("%-10d | %-15s | %-15s | %-40s | %-15d | %-20d | %-15d%n",
                                        adherent.getIdentifiant(), adherent.getNom(), adherent.getPrenom(),
                                        adherent.getAdresse(), adherent.getTypeAdherent(),
                                        adherent.getNombreEmpruntsMax(), adherent.getDureePret());
                            }
                        } catch (SQLException e) {
                            System.out.println("Erreur lors de la recherche d'adhérent par ID : " + e.getMessage());
                        }
                    }
                    case "2" -> {
                        // Fonction de recherche par type
                            System.out.println("=== Rechercher des adhérents par Type ===");
                        System.out.print("Entrez le type d'adhérent (1: Etudiant, 2: Enseignant, 3: Visiteur) : ");
                        int typeRecherche = scanner.nextInt();
                        try {
                            List<Adherent> adherents = AdherentManager.rechercherAdherentParType(typeRecherche);
                            if (adherents.isEmpty()) {
                                System.out.println("Aucun adhérent trouvé avec ce type.");
                            } else {
                                System.out.println("Résultats de la recherche :");
                                System.out.printf("%-10s | %-15s | %-15s | %-40s | %-15s | %-20s | %-15s%n",
                                        "ID", "Nom", "Prénom", "Adresse", "Type", "Nombre Max Emprunts", "Durée Prêt");
                                System.out.println("-".repeat(120));
                                for (Adherent adherent : adherents) {
                                    System.out.printf("%-10d | %-15s | %-15s | %-40s | %-15d | %-20d | %-15d%n",
                                            adherent.getIdentifiant(), adherent.getNom(), adherent.getPrenom(),
                                            adherent.getAdresse(), adherent.getTypeAdherent(),
                                            adherent.getNombreEmpruntsMax(), adherent.getDureePret());
                                }
                            }
                        } catch (SQLException e) {
                            System.out.println("Erreur lors de la recherche d'adhérents par type : " + e.getMessage());
                        }
                    }
                    case "3" -> {
                        // Fonction de recherche par Nom
                        System.out.println("=== Rechercher un adhérent par nom===");
                        System.out.print("Entrez le nom (partiel ou complet) de l'adhérent : ");
                        String nomRecherche = scanner.nextLine();
                        try {
                            List<Adherent> adherents = AdherentManager.rechercherAdherentParNom(nomRecherche);
                            if (adherents.isEmpty()) {
                                System.out.println("Aucun adhérent trouvé avec ce nom.");
                            } else {
                                System.out.println("Résultats de la recherche :");
                                System.out.printf("%-10s | %-15s | %-15s | %-40s | %-15s | %-20s | %-15s%n",
                                        "ID", "Nom", "Prénom", "Adresse", "Type", "Nombre Max Emprunts", "Durée Prêt");
                                System.out.println("-".repeat(120));
                                for (Adherent adherent : adherents) {
                                    System.out.printf("%-10d | %-15s | %-15s | %-40s | %-15d | %-20d | %-15d%n",
                                            adherent.getIdentifiant(), adherent.getNom(), adherent.getPrenom(),
                                            adherent.getAdresse(), adherent.getTypeAdherent(),
                                            adherent.getNombreEmpruntsMax(), adherent.getDureePret());
                                }
                            }
                        } catch (SQLException e) {
                            System.out.println("Erreur lors de la recherche d'adhérents : " + e.getMessage());
                        }
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
            System.out.println("***Recherche Document Par Type****");
            System.out.println("1. Livre");
            System.out.println("2. Memoire");
            System.out.println("3. Magazine");
            System.out.println("3. Article");
            System.out.println("#. Revenir");
            System.out.println("00. MENU PRINCIPAL");
            System.out.println("0. Quitter");
            choix = scanner.next();
            List<Livre> documents1 = LivreManager.afficherListeLivres() ;
            List<Magazine> documents2 = MagazineManager.afficherListeMagazines();
            List<Memoire> documents3 = MemoireManager.afficherListeMemoires();
            List<Article> documents4 = ArticleManager.afficherListeArticles();
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-30s | %-20s | %-20s | %-20s | %-20s |\n", "ID", "Titre", "Localisation", "Nombre d'exemplaires", "Type", "Auteur/Editeur/Candidat");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            String type;
            String auteurEditeurCandidat;
                switch (choix) {
                    case "1" -> {
                        // Liste des livres
                        for (Livre document : documents1) {
                            type = "Livre";
                            auteurEditeurCandidat = ((Livre) document).getAuteur() + "/" + ((Livre) document).getEditeur();
                            System.out.printf("| %-10d | %-30s | %-20s | %-20d | %-20s | %-20s |\n", document.getCode(), document.getTitre(), document.getLocalisation(), document.getNbExemplaires(), type, auteurEditeurCandidat);
                        } 
                       
                    }
                    case "2" -> {
                        // Liste des memoire
                        for (Memoire document : documents3) {
                            type = "Mémoire";
                            auteurEditeurCandidat = ((Memoire) document).getNomCandidat();
                            System.out.printf("| %-10d | %-30s | %-20s | %-20d | %-20s | %-20s |\n", document.getCode(), document.getTitre(), document.getLocalisation(), document.getNbExemplaires(), type, auteurEditeurCandidat);
                        }
                    }
                    case "3" -> {
                        // Liste des magazine
                        for (Magazine document : documents2) {
                            type = "Magazine";
                            auteurEditeurCandidat = ((Magazine) document).getFrequenceParution();
                            
                            System.out.printf("| %-10d | %-30s | %-20s | %-20d | %-20s | %-20s |\n", document.getCode(), document.getTitre(), document.getLocalisation(), document.getNbExemplaires(), type, auteurEditeurCandidat);
                        }
                    }
                    case "4" -> {
                        // Liste des Archicle
                        for (Article document : documents4) {
                            type = "Article";
                            auteurEditeurCandidat = ((Article) document).getAuteur();
                            System.out.printf("| %-10d | %-30s | %-20s | %-20d | %-20s | %-20s |\n", document.getCode(), document.getTitre(), document.getLocalisation(), document.getNbExemplaires(), type, auteurEditeurCandidat);
                        }
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
                 System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            } while (!"0".equals(choix));
    }
    /**
     * MENU Recherche Emprunts
     */
    public void menuRechercheEmprunt(){
        String choix;
         do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("***Recherche Emprunt****");
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
                        System.out.println("=== Recherche par Id ===");
                        System.out.println("Entrez l'ID de l'emprunt à rechercher :");
                        int idEmprunt = scanner.nextInt();
                        try {
                        Emprunt emprunt = EmpruntManager.rechercherEmpruntParId(idEmprunt);
                        if (emprunt == null) {
                            System.out.println("Aucun emprunt trouvé avec l'ID " + idEmprunt);
                            return;
                        }

                        System.out.println("Emprunt trouvé :");
                        System.out.println("Adhérent : " + emprunt.getAdherent().getNom() + " " + emprunt.getAdherent().getPrenom());
                        System.out.println("Document : " + emprunt.getDocument().getTitre());
                        System.out.println("Date de début : " + emprunt.getDateDebut());
                        System.out.println("Date limite : " + emprunt.getDateLimite());
                        System.out.println("Date de retour : " + emprunt.getDateRetour());
                    } catch (SQLException e) {
                        System.out.println("Une erreur s'est produite lors de la recherche de l'emprunt : " + e.getMessage());
                    }
                    }
                    case "2" -> {
                        // Fonction de recherche par intervale Date limite
                        System.out.println("Recherche d'emprunts par intervalle de date limite");
                        System.out.print("Date de début (yyyy-MM-dd) : ");
                        String debutText = scanner.nextLine();
                        System.out.print("Date de fin (yyyy-MM-dd) : ");
                        String finText = scanner.nextLine();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            java.util.Date debut = dateFormat.parse(debutText);
                            java.util.Date fin = dateFormat.parse(finText);

                            List<Emprunt> emprunts2 = EmpruntManager.rechercherEmpruntsParIntervalleDateLimite(debut, fin);

                            System.out.println("=== Liste des emprunts ===");
                            // En-tête du tableau
                            System.out.println("+----+---------------------+----------------------+----------------------+----------------------+");
                            System.out.println("| ID |     Adhérent         |      Document        |      Date de début    |      Date limite     |");
                            System.out.println("+----+---------------------+----------------------+----------------------+----------------------+");

                            // Affichage des emprunts
                            for (Emprunt emprunt : emprunts2) {
                                String id = String.format("%-5s", emprunt.getId());
                                String adherent = String.format("%-20s", emprunt.getAdherent().getNom() + " " + emprunt.getAdherent().getPrenom());
                                String document = String.format("%-20s", emprunt.getDocument().getTitre());
                                String dateDebut = String.format("%-20s", convertDateToString(emprunt.getDateDebut()));
                                String dateLimite = String.format("%-20s", convertDateToString(emprunt.getDateLimite()));


                                System.out.println("| " + id + " | " + adherent + " | " + document + " | " + dateDebut + " | " + dateLimite + " |");
                            }

                            // Pied du tableau
                            System.out.println("+----+---------------------+----------------------+----------------------+----------------------+");
                        
                        } catch (ParseException ex) {
                            System.err.println("Erreur lors de la recherche : " + ex.getMessage());
                        } catch (SQLException ex) {
                    Logger.getLogger(Biblio.class.getName()).log(Level.SEVERE, null, ex);
                }
                     }
                    case "3" -> {
                        // Fonction de recherche par intervale date Debut
                        System.out.println("Recherche d'emprunts par intervalle de date debut");
                        System.out.print("Date de début (yyyy-MM-dd) : ");
                        String debutText = scanner.nextLine();
                        System.out.print("Date de fin (yyyy-MM-dd) : ");
                        String finText = scanner.nextLine();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            java.util.Date debut = dateFormat.parse(debutText);
                            java.util.Date fin = dateFormat.parse(finText);

                            List<Emprunt> emprunts2 = EmpruntManager.rechercherEmpruntsParIntervalleDateDebut(debut, fin);

                            System.out.println("=== Liste des emprunts ===");
                            // En-tête du tableau
                            System.out.println("+----+---------------------+----------------------+----------------------+----------------------+");
                            System.out.println("| ID |     Adhérent         |      Document        |      Date de début    |      Date limite     |");
                            System.out.println("+----+---------------------+----------------------+----------------------+----------------------+");

                            // Affichage des emprunts
                            for (Emprunt emprunt : emprunts2) {
                                String id = String.format("%-5s", emprunt.getId());
                                String adherent = String.format("%-20s", emprunt.getAdherent().getNom() + " " + emprunt.getAdherent().getPrenom());
                                String document = String.format("%-20s", emprunt.getDocument().getTitre());
                                String dateDebut = String.format("%-20s", convertDateToString(emprunt.getDateDebut()));
                                String dateLimite = String.format("%-20s", convertDateToString(emprunt.getDateLimite()));


                                System.out.println("| " + id + " | " + adherent + " | " + document + " | " + dateDebut + " | " + dateLimite + " |");
                            }

                            // Pied du tableau
                            System.out.println("+----+---------------------+----------------------+----------------------+----------------------+");
                        
                        } catch (ParseException ex) {
                            System.err.println("Erreur lors de la recherche : " + ex.getMessage());
                        } catch (SQLException ex) {
                    Logger.getLogger(Biblio.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }
                    case "4" -> {
                        // Fonction de recherche par intervale date Retour
                     
                        System.out.println("Recherche d'emprunts par intervalle de date retour");
                        System.out.print("Date de début (yyyy-MM-dd) : ");
                        String debutText = scanner.nextLine();
                        System.out.print("Date de fin (yyyy-MM-dd) : ");
                        String finText = scanner.nextLine();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            java.util.Date debut = dateFormat.parse(debutText);
                            java.util.Date fin = dateFormat.parse(finText);

                            List<Emprunt> emprunts2 = EmpruntManager.rechercherEmpruntsParIntervalleDateRetour(debut, fin);

                            System.out.println("=== Liste des emprunts ===");
                            // En-tête du tableau
                            System.out.println("+----+---------------------+----------------------+----------------------+----------------------+");
                            System.out.println("| ID |     Adhérent         |      Document        |      Date de début    |      Date limite     |");
                            System.out.println("+----+---------------------+----------------------+----------------------+----------------------+");

                            // Affichage des emprunts
                            for (Emprunt emprunt : emprunts2) {
                                String id = String.format("%-5s", emprunt.getId());
                                String adherent = String.format("%-20s", emprunt.getAdherent().getNom() + " " + emprunt.getAdherent().getPrenom());
                                String document = String.format("%-20s", emprunt.getDocument().getTitre());
                                String dateDebut = String.format("%-20s", convertDateToString(emprunt.getDateDebut()));
                                String dateLimite = String.format("%-20s", convertDateToString(emprunt.getDateLimite()));


                                System.out.println("| " + id + " | " + adherent + " | " + document + " | " + dateDebut + " | " + dateLimite + " |");
                            }

                            // Pied du tableau
                            System.out.println("+----+---------------------+----------------------+----------------------+----------------------+");
                        
                        } catch (ParseException ex) {
                            System.err.println("Erreur lors de la recherche : " + ex.getMessage());
                        } catch (SQLException ex) {
                    Logger.getLogger(Biblio.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                        System.out.println("=== Recherche un utilisateur par nom===");
                        System.out.print("Nom de l'utilisateur : ");
                        String nom = scanner.nextLine();
                        scanner.nextLine(); // Consommer le retour à la ligne
                        try {
                            List<Utilisateur> utilisateurs1 = UtilisateurManager.rechercherUtilisateurParNom(nom);
                            System.out.println("=== Liste des utilisateur ===");
                            System.out.printf("%-10s | %-15s | %-15s | %-40s | %-15s | %-20s%n",
                                    "ID", "Nom", "Login", "Password", "Role", "Etat");
                            System.out.println("-".repeat(120));
                            for (Utilisateur utilisateur : utilisateurs1) {
                                System.out.printf("%-10d | %-15s | %-15s | %-40s | %-15d | %-20d%n",
                                        utilisateur.getIdentifiant(), utilisateur.getNom(), utilisateur.getLogin(),
                                        utilisateur.getPassword(), utilisateur.getRole(),
                                        utilisateur.getEtat());
                            }
                        } catch (SQLException e) {
                            System.out.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
                        }
                        
                    }
                    case "2" -> {
                        // Fonction de recherche par identifiant
                        System.out.println("=== Recherche un utilisateur par id===");
                        System.out.print("Identifiant de l'utilisateur : ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consommer le retour à la ligne
                        try {
                            Utilisateur utilisateur = UtilisateurManager.rechercherUtilisateurParId(id);
                            System.out.println("=== Utilisateur ===");
                            System.out.printf("%-10s | %-15s | %-15s | %-40s | %-15s | %-20s%n",
                                    "ID", "Nom", "Login", "Password", "Role", "Etat");
                            System.out.println("-".repeat(120));
                                System.out.printf("%-10d | %-15s | %-15s | %-40s | %-15d | %-20d%n",
                                        utilisateur.getIdentifiant(), utilisateur.getNom(), utilisateur.getLogin(),
                                        utilisateur.getPassword(), utilisateur.getRole(),
                                        utilisateur.getEtat());
                           
                        } catch (SQLException e) {
                            System.out.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
                        }
                    }
                    case "#" -> {
                        // Revenir au menu Gestion User
                        this.menuGestUtilisateurs();
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
        try {
            //code pour afficher les stats
            System.out.println("**********************");
            System.out.println("****STATISTIQUES******");
            System.out.println("**********************");
            System.out.println("-> Adherents :");
            try {
                System.out.println(StatistiquesManager.calculerNombreEtudiants() + "  Etudiants");
            } catch (SQLException ex) {
                Logger.getLogger(Biblio.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(StatistiquesManager.calculerNombreEnseignants() + "  Enseignants");
            System.out.println( StatistiquesManager.calculerNombreVisiteurs() +"  Visiteurs");
            System.out.println("-> Emprunts :");
            System.out.println(StatistiquesManager.calculerNombreEmpruntsEnCours() +"  En cours");
            System.out.println(StatistiquesManager.calculerNombreEmpruntsDepasses() +"  Depassés");
            System.out.println(StatistiquesManager.calculerNombreEmpruntsRetournes() +"  Restitués");
            System.out.println("-> Documents :");
            System.out.println(StatistiquesManager.calculerNombreLivres()+"  Livres");
            System.out.println(StatistiquesManager.calculerNombreMagazines()+"  Magazines");
            System.out.println(StatistiquesManager.calculerNombreArticles()+"  Articles");
            System.out.println(StatistiquesManager.calculerNombreMemoires() + "  Memoires");
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
        } catch (SQLException ex) {
            Logger.getLogger(Biblio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Bon retour sur BiblioSur");
        Biblio bibliotheque = new Biblio();
        bibliotheque.menuPrincipal();
        
        
    }
        
}
    

