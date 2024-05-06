/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio.services;

import biblio.Adherent;
import biblio.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LINJOUOM ALAIN P
 */
public class AdherentManager {
    
    // Méthode pour ajouter un adhérent à la base de données
    public static void ajouterAdherent(Adherent adherent) throws SQLException {
        String query = "INSERT INTO adherent (nom, prenom, adresse, typeAdherent, nombreEmpruntsMax, dureePret) VALUES ( ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètres pour l'ajout de l'adhérent
            preparedStatement.setString(1, adherent.getNom());
            preparedStatement.setString(2, adherent.getPrenom());
            preparedStatement.setString(3, adherent.getAdresse());
            preparedStatement.setInt(4, adherent.getTypeAdherent());
            preparedStatement.setInt(5, adherent.getNombreEmpruntsMax());
            preparedStatement.setInt(6, adherent.getDureePret());
            // Exécution de la requête d'insertion
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour supprimer un adhérent de la base de données par son identifiant
    public static void supprimerAdherent(int identifiant) throws SQLException {
        String query = "DELETE FROM adherent WHERE identifiant = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètre pour la suppression de l'adhérent
            preparedStatement.setInt(1, identifiant);
            // Exécution de la requête de suppression
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour modifier les informations d'un adhérent dans la base de données
    public static void modifierAdherent(Adherent adherent) throws SQLException {
        String query = "UPDATE adherent SET nom = ?, prenom = ?, adresse = ?, typeAdherent = ?, nombreEmpruntsMax = ?, dureePret = ? WHERE identifiant = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètres pour la modification de l'adhérent
            preparedStatement.setString(1, adherent.getNom());
            preparedStatement.setString(2, adherent.getPrenom());
            preparedStatement.setString(3, adherent.getAdresse());
            preparedStatement.setInt(4, adherent.getTypeAdherent());
            preparedStatement.setInt(5, adherent.getNombreEmpruntsMax());
            preparedStatement.setInt(6, adherent.getDureePret());
            preparedStatement.setInt(7, adherent.getIdentifiant());
            // Exécution de la requête de modification
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour afficher la liste des adhérents de la base de données
    public static List<Adherent> listerAdherents() throws SQLException {
        List<Adherent> adherents = new ArrayList<>();
        String query = "SELECT * FROM adherent";
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            // Parcours des résultats de la requête
            while (resultSet.next()) {
                // Création d'un objet Adherent à partir des résultats
                Adherent adherent = new Adherent();
                adherent.setIdentifiant(resultSet.getInt("identifiant"));
                adherent.setNom(resultSet.getString("nom"));
                adherent.setPrenom(resultSet.getString("prenom"));
                adherent.setAdresse(resultSet.getString("adresse"));
                adherent.setTypeAdherent(resultSet.getInt("typeAdherent"));
                adherent.setNombreEmpruntsMax(resultSet.getInt("nombreEmpruntsMax"));
                adherent.setDureePret(resultSet.getInt("dureePret"));
                // Ajout de l'adhérent à la liste
                adherents.add(adherent);
            }
        }
        return adherents;
    }

    // Méthode pour rechercher un adhérent par son identifiant
    public static Adherent rechercherAdherentParId(int identifiant) throws SQLException {
        Adherent adherent = null;
        String query = "SELECT * FROM adherent WHERE identifiant = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètre pour la recherche par identifiant
            preparedStatement.setInt(1, identifiant);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Si un résultat est trouvé, créer un objet Adherent
                if (resultSet.next()) {
                    adherent = new Adherent();
                    adherent.setIdentifiant(resultSet.getInt("identifiant"));
                    adherent.setNom(resultSet.getString("nom"));
                    adherent.setPrenom(resultSet.getString("prenom"));
                    adherent.setAdresse(resultSet.getString("adresse"));
                    adherent.setTypeAdherent(resultSet.getInt("typeAdherent"));
                    adherent.setNombreEmpruntsMax(resultSet.getInt("nombreEmpruntsMax"));
                    adherent.setDureePret(resultSet.getInt("dureePret"));
                }
            }
        }
        return adherent;
    }

    // Méthode pour rechercher des adhérents par leur type (Etudiant, Enseignant, Visiteur)
    public static List<Adherent> rechercherAdherentParType(int typeAdherent) throws SQLException {
        List<Adherent> adherents = new ArrayList<>();
        String query = "SELECT * FROM adherent WHERE typeAdherent = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètre pour la recherche par type
            preparedStatement.setInt(1, typeAdherent);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Parcours des résultats et création des objets Adherent
                while (resultSet.next()) {
                    Adherent adherent = new Adherent();
                    adherent.setIdentifiant(resultSet.getInt("identifiant"));
                    adherent.setNom(resultSet.getString("nom"));
                    adherent.setPrenom(resultSet.getString("prenom"));
                    adherent.setAdresse(resultSet.getString("adresse"));
                    adherent.setTypeAdherent(resultSet.getInt("typeAdherent"));
                    adherent.setNombreEmpruntsMax(resultSet.getInt("nombreEmpruntsMax"));
                    adherent.setDureePret(resultSet.getInt("dureePret"));
                    adherents.add(adherent);
                }
            }
        }
        return adherents;
    }

    // Méthode pour rechercher des adhérents par leur nom (recherche partielle)
    public static List<Adherent> rechercherAdherentParNom(String nom) throws SQLException {
        List<Adherent> adherents = new ArrayList<>();
        String query = "SELECT * FROM adherent WHERE nom LIKE ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètre pour la recherche par nom (recherche partielle)
            preparedStatement.setString(1, "%" + nom + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Parcours des résultats et création des objets Adherent
                while (resultSet.next()) {
                    Adherent adherent = new Adherent();
                    adherent.setIdentifiant(resultSet.getInt("identifiant"));
                    adherent.setNom(resultSet.getString("nom"));
                    adherent.setPrenom(resultSet.getString("prenom"));
                    adherent.setAdresse(resultSet.getString("adresse"));
                    adherent.setTypeAdherent(resultSet.getInt("typeAdherent"));
                    adherent.setNombreEmpruntsMax(resultSet.getInt("nombreEmpruntsMax"));
                    adherent.setDureePret(resultSet.getInt("dureePret"));
                    adherents.add(adherent);
                }
            }
        }
        return adherents;
    }
    
    // Methode pour connaitre le nombre d'Emprunts en cours pour un adherent precis
    public static int nombreEmpruntsEnCours(Adherent adherent) {
        int nombreEmprunts = 0; // Initialisation du nombre d'emprunts à 0
        String query = "SELECT COUNT(*) AS count FROM emprunt WHERE adherent_id = ? AND dateRetour IS NULL";
        // Requête SQL pour compter le nombre d'emprunts en cours pour un adhérent donné
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, adherent.getIdentifiant()); // Remplacement du premier paramètre de la requête par l'identifiant de l'adhérent
            ResultSet resultSet = preparedStatement.executeQuery(); // Exécution de la requête
            if (resultSet.next()) {
                nombreEmprunts = resultSet.getInt("count"); // Récupération du résultat (nombre d'emprunts en cours)
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestion des exceptions
        }
        return nombreEmprunts; // Renvoi du nombre d'emprunts en cours
    }
    
    // Methode pour connaitre le nombre d'emprunts depassés
    public static int nombreEmpruntsDepasses(Adherent adherent) {
        int nombreEmprunts = 0; // Initialisation du nombre d'emprunts dépassés à 0
        String query = "SELECT COUNT(*) AS count FROM emprunt WHERE adherent_id = ? AND dateLimite < CURDATE() AND dateRetour IS NULL";
        // Requête SQL pour compter le nombre d'emprunts dépassés pour un adhérent donné
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, adherent.getIdentifiant()); // Remplacement du premier paramètre de la requête par l'identifiant de l'adhérent
            ResultSet resultSet = preparedStatement.executeQuery(); // Exécution de la requête
            if (resultSet.next()) {
                nombreEmprunts = resultSet.getInt("count"); // Récupération du résultat (nombre d'emprunts dépassés)
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestion des exceptions
        }
        return nombreEmprunts; // Renvoi du nombre d'emprunts dépassés
    }
    
    // Methode pour verifié si un adherent est éligible a un emprumt
    public static boolean estEligibleEmprunt(Adherent adherent) {
        boolean eligible = true;

        // Vérifier le nombre maximal d'emprunts en fonction du type de l'adhérent
        int nombreEmpruntsMax = 0;
        switch (adherent.getTypeAdherent()) {
            case 1 -> nombreEmpruntsMax = 2;
            case 2 -> nombreEmpruntsMax = 4;
            case 3 -> nombreEmpruntsMax = 1;
            default -> {
            }
        }

        // Vérifier si le nombre maximal d'emprunts est atteint
        if (nombreEmpruntsMax > 0 && nombreEmpruntsEnCours(adherent) >= nombreEmpruntsMax) {
            eligible = false;
        }

        // Vérifier si l'adhérent est en retard pour un emprunt précédent
        if (nombreEmpruntsDepasses(adherent) > 0) {
            eligible = false;
        }

        return eligible;
    }
    
}
