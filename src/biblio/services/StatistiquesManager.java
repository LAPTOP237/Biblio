/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio.services;

import biblio.DatabaseManager;
import java.sql.*;

/**
 *
 * @author LINJOUOM ALAIN P
 */
public class StatistiquesManager {
     // Méthode pour calculer le nombre de livres
    public static int calculerNombreLivres() throws SQLException {
        return calculerNombreDocuments("Livre");
    }

    // Méthode pour calculer le nombre de magazines
    public static int calculerNombreMagazines() throws SQLException {
        return calculerNombreDocuments("Magazine");
    }

    // Méthode pour calculer le nombre d'articles
    public static int calculerNombreArticles() throws SQLException {
        return calculerNombreDocuments("Article");
    }

    // Méthode pour calculer le nombre de mémoires
    public static int calculerNombreMemoires() throws SQLException {
        return calculerNombreDocuments("Memoire");
    }

    // Méthode pour calculer le nombre de documents en fonction du type
    private static int calculerNombreDocuments(String type) throws SQLException {
        int count;
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM document WHERE type = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, type);
                try (ResultSet resultSet = statement.executeQuery()) {
                    resultSet.next();
                    count = resultSet.getInt("count");
                }
            }
        }
        return count;
    }

    // Méthode pour calculer le nombre d'étudiants
    public static int calculerNombreEtudiants() throws SQLException {
        return calculerNombreAdherents(1);
    }

    // Méthode pour calculer le nombre d'enseignants
    public static int calculerNombreEnseignants() throws SQLException {
        return calculerNombreAdherents(2);
    }

    // Méthode pour calculer le nombre de visiteurs
    public static int calculerNombreVisiteurs() throws SQLException {
        return calculerNombreAdherents(3);
    }

    // Méthode pour calculer le nombre d'adhérents en fonction du type
    private static int calculerNombreAdherents(int type) throws SQLException {
        int count;
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM adherent WHERE typeAdherent = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, type);
                try (ResultSet resultSet = statement.executeQuery()) {
                    resultSet.next();
                    count = resultSet.getInt("count");
                }
            }
        }
        return count;
    }

    // Méthode pour calculer le nombre d'emprunts en cours
    public static int calculerNombreEmpruntsEnCours() throws SQLException {
        int count;
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM emprunt WHERE dateRetour IS NULL";
            try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }

    // Méthode pour calculer le nombre d'emprunts dépassés
    public static int calculerNombreEmpruntsDepasses() throws SQLException {
        int count;
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM emprunt WHERE dateLimite < CURDATE() AND dateRetour IS NULL";
            try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }

    // Méthode pour calculer le nombre d'emprunts déjà retournés
    public static int calculerNombreEmpruntsRetournes() throws SQLException {
        int count;
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM emprunt WHERE dateRetour IS NOT NULL";
            try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                count = resultSet.getInt("count");
            }
        }
        return count;
    }
    
}
