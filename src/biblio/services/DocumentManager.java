/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio.services;

import biblio.Document;
import biblio.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author LINJOUOM ALAIN P
 */
public class DocumentManager {
    // Méthode pour ajouter un document à la base de données
    public static void ajouterDocument(Document document) throws SQLException {
        String query = "INSERT INTO document (titre, localisation, nbExemplaires) VALUES ( ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètres pour l'ajout du document
            preparedStatement.setString(1, document.getTitre());
            preparedStatement.setString(2, document.getLocalisation());
            preparedStatement.setInt(3, document.getNbExemplaires());
            // Exécution de la requête d'insertion
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour supprimer un document de la base de données par son code
    public static void supprimerDocument(int code) throws SQLException {
        String query = "DELETE FROM document WHERE code = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètre pour la suppression du document
            preparedStatement.setInt(1, code);
            // Exécution de la requête de suppression
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour modifier les informations du document dans la base de données
    public static void modifierDocument(Document document) throws SQLException {
        String query = "UPDATE document SET titre = ?, localisation = ?, nbExemplaires = ? WHERE code = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètres pour la modification du document
            preparedStatement.setString(1, document.getTitre());
            preparedStatement.setString(2, document.getLocalisation());
            preparedStatement.setInt(3, document.getNbExemplaires());
            preparedStatement.setInt(4, document.getCode());
            // Exécution de la requête de modification
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour afficher la liste des documents de la base de données
    public static List<Document> listerDocuments() throws SQLException {
        List<Document> documents = new ArrayList<>();
        String query = "SELECT * FROM document";
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            // Parcours des résultats de la requête
            while (resultSet.next()) {
                // Création d'un objet Document à partir des résultats
                Document document = new Document();
                document.setCode(resultSet.getInt("code"));
                document.setTitre(resultSet.getString("titre"));
                document.setLocalisation(resultSet.getString("localisation"));
                document.setNbExemplaires(resultSet.getInt("nbExemplaires"));
                // Ajout du document à la liste
                documents.add(document);
            }
        }
        return documents;
    }

    // Méthode pour rechercher un document par son code
    public static Document rechercherDocumentParId(int code) throws SQLException {
        Document document = null;
        String query = "SELECT * FROM document WHERE code = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètre pour la recherche par code
            preparedStatement.setInt(1, code);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Si un résultat est trouvé, créer un objet Document
                if (resultSet.next()) {
                    document = new Document();
                    document.setCode(resultSet.getInt("code"));
                    document.setTitre(resultSet.getString("titre"));
                    document.setLocalisation(resultSet.getString("localisation"));
                    document.setNbExemplaires(resultSet.getInt("nbExemplaires"));
                }
            }
        }
        return document;
    }
    
    // Méthode pour rechercher un document par son code
    public static List<Document> rechercherDocumentParTitre(String titre) throws SQLException {
        List<Document> documents = new ArrayList<>();
        String query = "SELECT * FROM document WHERE titre LIKE ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramètre pour la recherche par titre (recherche partielle)
            preparedStatement.setString(1, "%" + titre + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Parcours des résultats et création des objets Documents
                while (resultSet.next()) {
                    Document document = new Document();
                    document.setCode(resultSet.getInt("code"));
                    document.setTitre(resultSet.getString("titre"));
                    document.setLocalisation(resultSet.getString("localisation"));
                    document.setNbExemplaires(resultSet.getInt("nbExemplaires"));
                    documents.add(document);
                }
            }
        }
        return documents;
    }
    
    public static boolean estDisponiblePourEmprunt(Document document) {
        boolean disponible = true;

        // Récupérer le nombre d'exemplaires du document
        int nbExemplaires = document.getNbExemplaires();

        // Vérifier le nombre d'exemplaires disponibles (non empruntés)
        if (nbExemplaires <= 0) {
            disponible = false; // Aucun exemplaire disponible
        } else {
            // Récupérer le nombre d'emprunts en cours pour ce document
            int nbEmpruntsEnCours = nombreEmpruntsDocumentEnCours(document);

            // Vérifier si tous les exemplaires sont empruntés
            if (nbEmpruntsEnCours >= nbExemplaires) {
                disponible = false; // Tous les exemplaires sont empruntés
            }
        }

        return disponible;
    }
    
    // Methode pour connaitre le nombre d'emprunts en cours pour un document
    public static int nombreEmpruntsDocumentEnCours(Document document) {
    int nombreEmprunts = 0;
    String query = "SELECT COUNT(*) AS count FROM emprunt WHERE document_code = ? AND dateRetour IS NULL";
    try (Connection connection = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, document.getCode());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            nombreEmprunts = resultSet.getInt("count");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return nombreEmprunts;
}


    //Methode pour connaitre le nombre d'emprumts complet d'un document 
    public static int nombreEmpruntsDocument(Document document) {
        int nombreEmprunts = 0;
        String query = "SELECT COUNT(*) AS count FROM emprunt WHERE document_code = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, document.getCode());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nombreEmprunts = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreEmprunts;
    }


    
}
