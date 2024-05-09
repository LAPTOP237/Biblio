/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio.services;

import biblio.DatabaseManager;
import biblio.Utilisateur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LINJOUOM ALAIN P
 */
public class UtilisateurManager {
    // Méthode pour ajouter un utilisateur à la base de données
    public static void ajouterUtilisateur(Utilisateur utilisateur) throws SQLException {
        String query = "INSERT INTO utilisateur (nom, login, password, role, etat) VALUES ( ?, ?, ?,?,?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètres pour l'ajout du utilisateur
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getLogin());
            preparedStatement.setString(3, utilisateur.getPassword());
            preparedStatement.setInt(4, utilisateur.getRole());
            preparedStatement.setInt(5, utilisateur.getEtat());
            // Exécution de la requête d'insertion
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour supprimer un utilisateur de la base de données par son code
    public static void supprimerUtilisateur(int identifiant) throws SQLException {
        String query = "DELETE FROM utilisateur WHERE identifiant = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètre pour la suppression du utilisateur
            preparedStatement.setInt(1, identifiant);
            // Exécution de la requête de suppression
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour modifier les informations du utilisateur dans la base de données
    public static void modifierUtilisateur(Utilisateur utilisateur) throws SQLException {
        String query = "UPDATE utilisateur SET nom = ?, role = ? WHERE identifiant = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètres pour la modification du utilisateur
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setInt(2, utilisateur.getRole());
            preparedStatement.setInt(3, utilisateur.getIdentifiant());
            // Exécution de la requête de modification
            preparedStatement.executeUpdate();
        }
    }
    
    // Méthode pour modifier les informations du utilisateur dans la base de données
    public static void modifierEtatUtilisateur(Utilisateur utilisateur) throws SQLException {
        String query = "UPDATE utilisateur SET etat = ? WHERE identifiant = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètres pour la modification du utilisateur
            preparedStatement.setInt(1, utilisateur.getEtat());
            preparedStatement.setInt(2, utilisateur.getIdentifiant());
            // Exécution de la requête de modification
            preparedStatement.executeUpdate();
        }
    }
    
    
    // Méthode pour afficher la liste des utilisateurs de la base de données
    public static List<Utilisateur> listerUtilisateurs() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateur";
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            // Parcours des résultats de la requête
            while (resultSet.next()) {
                // Création d'un objet utilisateur à partir des résultats
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setNom(resultSet.getString("nom"));
                utilisateur.setLogin(resultSet.getString("login"));
                utilisateur.setPassword(resultSet.getString("password"));
                utilisateur.setRole(resultSet.getInt("role"));
                utilisateur.setEtat(resultSet.getInt("etat"));
                // Ajout du utilisateur à la liste
                utilisateurs.add(utilisateur);
            }
        }
        return utilisateurs;
    }

    // Méthode pour rechercher un utilisateur  par son identifiant
    public static Utilisateur rechercherUtilisateurParId(int identifiant) throws SQLException {
        Utilisateur utilisateur = null;
        String query = "SELECT * FROM utilisateur WHERE identifiant = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètre pour la recherche par identifiant
            preparedStatement.setInt(1, identifiant);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Si un résultat est trouvé, créer un objet Utilisateur
                if (resultSet.next()) {
                    utilisateur = new Utilisateur();
                    utilisateur.setIdentifiant(resultSet.getInt("identifiant"));
                    utilisateur.setNom(resultSet.getString("nom"));
                    utilisateur.setLogin(resultSet.getString("login"));
                    utilisateur.setPassword(resultSet.getString("password"));
                    utilisateur.setRole(resultSet.getInt("role"));
                    utilisateur.setEtat(resultSet.getInt("etat"));
                }
            }
        }
        return utilisateur;
    }
    
    // Méthode pour rechercher un utilisateur par son nom
    public static List<Utilisateur> rechercherUtilisateurParNom(String nom) throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateur WHERE nom LIKE ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètre pour la recherche par nom (recherche partielle)
            preparedStatement.setString(1, "%" + nom + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Parcours des résultats et création des objets Adherent
                while (resultSet.next()) {
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setIdentifiant(resultSet.getInt("identifiant"));
                    utilisateur.setNom(resultSet.getString("nom"));
                    utilisateur.setLogin(resultSet.getString("login"));
                    utilisateur.setPassword(resultSet.getString("password"));
                    utilisateur.setRole(resultSet.getInt("role"));
                    utilisateur.setEtat(resultSet.getInt("etat"));
                    utilisateurs.add(utilisateur);
                }
            }
        }
        return utilisateurs;
    }
    
}
