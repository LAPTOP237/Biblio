/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio.services;

import biblio.Emprunt;
import biblio.Adherent;
import biblio.Document;
import biblio.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author LINJOUOM ALAIN P
 */
public class EmpruntManager {
    
    // Méthode pour ajouter un emprunt dans la base de données
    public static void ajouterEmprunt(Emprunt emprunt) throws SQLException {
        String query = "INSERT INTO emprunt (adherent_id, document_code, dateDebut, dateLimite) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) { 
                preparedStatement.setInt(1, emprunt.getAdherent().getIdentifiant());
                preparedStatement.setInt(2, emprunt.getDocument().getCode());
                preparedStatement.setDate(3, new java.sql.Date(emprunt.getDateDebut().getTime()));
                preparedStatement.setDate(4, new java.sql.Date(emprunt.getDateLimite().getTime()));
                preparedStatement.executeUpdate();
           
        }
    }

    // Méthode pour supprimer un emprunt de la base de données
    public static void supprimerEmprunt(int idEmprunt) throws SQLException {
        String query = "DELETE FROM emprunt WHERE id = ?";
       try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idEmprunt);
                preparedStatement.executeUpdate();  
        }
    }
    // Méthode pour modifier un emprunt dans la base de données
    public static void modifierEmprunt(Emprunt emprunt) throws SQLException {
        String query = "UPDATE emprunt SET adherent_id = ?, document_code = ?, dateDebut = ?, dateLimite = ?, dateRetour = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, emprunt.getAdherent().getIdentifiant());
                preparedStatement.setInt(2, emprunt.getDocument().getCode());
                preparedStatement.setDate(3, new java.sql.Date(emprunt.getDateDebut().getTime()));
                preparedStatement.setDate(4, new java.sql.Date(emprunt.getDateLimite().getTime()));
                preparedStatement.setDate(5, emprunt.getDateRetour() != null ? new java.sql.Date(emprunt.getDateRetour().getTime()) : null);
                preparedStatement.setInt(6, emprunt.getId());
                preparedStatement.executeUpdate();
        }
    }

    // Méthode pour afficher la liste des emprunts
    public static List<Emprunt> listerEmprunts() throws SQLException {
        List<Emprunt> emprunts = new ArrayList<>();
        String query = "SELECT * FROM emprunt";
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery(query);
                while (resultSet.next()) { 
                    Adherent adherent = AdherentManager.rechercherAdherentParId(resultSet.getInt("adherent_id"));
                    Document document = DocumentManager.rechercherDocumentParId(resultSet.getInt("document_code"));
                    Date dateDebut = resultSet.getDate("dateDebut");
                    Date dateLimite = resultSet.getDate("dateLimite");
                    Date dateRetour = resultSet.getDate("dateRetour");
                    Emprunt emprunt = new Emprunt(adherent, document, dateDebut, dateLimite);
                    emprunt.setDateRetour(dateRetour);
                    emprunts.add(emprunt);
                }
                return emprunts;
        }
    }

    // Méthode pour rechercher un emprunt par son identifiant
    public static Emprunt rechercherEmpruntParId(int idEmprunt) throws SQLException {
        String query = "SELECT * FROM emprunt WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idEmprunt);
            ResultSet resultSet = preparedStatement.executeQuery();
            Emprunt emprunt = null;
            if (resultSet.next()) {
                Adherent adherent = AdherentManager.rechercherAdherentParId(resultSet.getInt("adherent_id"));
                Document document = DocumentManager.rechercherDocumentParId(resultSet.getInt("document_code"));
                Date dateDebut = resultSet.getDate("dateDebut");
                Date dateLimite = resultSet.getDate("dateLimite");
                Date dateRetour = resultSet.getDate("dateRetour");
                emprunt = new Emprunt(adherent, document, dateDebut, dateLimite);
                emprunt.setId(resultSet.getInt("id"));
                emprunt.setDateRetour(dateRetour);
            }
            return emprunt;
        }
    }
    
    // Méthode pour lister les retardataires (emprunts en retards)
    public static List<Emprunt> listerRetardataires() throws SQLException {
        List<Emprunt> retardataires = new ArrayList<>();
        String query = "SELECT * FROM emprunt WHERE dateLimite < CURDATE() AND dateRetour IS NULL ORDER BY adherent_id";
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                Adherent adherent = AdherentManager.rechercherAdherentParId(resultSet.getInt("adherent_id"));
                Document document = DocumentManager.rechercherDocumentParId(resultSet.getInt("document_code"));
                Date dateDebut = resultSet.getDate("dateDebut");
                Date dateLimite = resultSet.getDate("dateLimite");
                Date dateRetour = resultSet.getDate("dateRetour");
                Emprunt emprunt = new Emprunt(adherent, document, dateDebut, dateLimite);
                emprunt.setId(resultSet.getInt("id"));
                emprunt.setDateRetour(dateRetour);
                retardataires.add(emprunt);
            }
            return retardataires;
        }
    }
    
    //Methode pour la liste des Adherents retardataires
    public static List<Adherent> listerAdherentsRetardataires() {
        List<Adherent> adherentsRetardataires = new ArrayList<>();
        String query = "SELECT DISTINCT e.adherent_id, a.nom, a.prenom FROM emprunt e " +
                       "INNER JOIN adherent a ON e.adherent_id = a.id " +
                       "WHERE e.dateRetour IS NULL AND e.dateLimite < CURDATE()";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
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
                adherentsRetardataires.add(adherent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adherentsRetardataires;
    }


    // Méthode pour enregistrer un retour
    public static void enregistrerRetour(int idEmprunt, Date dateRetour) throws SQLException {
        String query = "UPDATE emprunt SET dateRetour = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(dateRetour.getTime()));
            preparedStatement.setInt(2, idEmprunt);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour rechercher des emprunts par intervalle de date Limite
    public static List<Emprunt> rechercherEmpruntsParIntervalleDateLimite(Date debut, Date fin) throws SQLException {
        List<Emprunt> emprunts = new ArrayList<>();
        String query = "SELECT * FROM emprunt WHERE dateLimite BETWEEN ? AND ?";
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(debut.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(fin.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Adherent adherent = AdherentManager.rechercherAdherentParId(resultSet.getInt("adherent_id"));
                Document document = DocumentManager.rechercherDocumentParId(resultSet.getInt("document_code"));
                Date dateDebut = resultSet.getDate("dateDebut");
                Date dateLimite = resultSet.getDate("dateLimite");
                Date dateRetour = resultSet.getDate("dateRetour");
                Emprunt emprunt = new Emprunt(adherent, document, dateDebut, dateLimite);
                emprunt.setId(resultSet.getInt("id"));
                emprunt.setDateRetour(dateRetour);
                emprunts.add(emprunt);
            }
            return emprunts;
        }
    }

    // Méthode pour rechercher des emprunts par intervalle de date Retour
    public static List<Emprunt> rechercherEmpruntsParIntervalleDateRetour(Date debut, Date fin) throws SQLException {
        List<Emprunt> emprunts = new ArrayList<>();
        String query = "SELECT * FROM emprunt WHERE dateRetour BETWEEN ? AND ?";
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(debut.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(fin.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Adherent adherent = AdherentManager.rechercherAdherentParId(resultSet.getInt("adherent_id"));
                Document document = DocumentManager.rechercherDocumentParId(resultSet.getInt("document_code"));
                Date dateDebut = resultSet.getDate("dateDebut");
                Date dateLimite = resultSet.getDate("dateLimite");
                Date dateRetour = resultSet.getDate("dateRetour");
                Emprunt emprunt = new Emprunt(adherent, document, dateDebut, dateLimite);
                emprunt.setId(resultSet.getInt("id"));
                emprunt.setDateRetour(dateRetour);
                emprunts.add(emprunt);
            }
            return emprunts;
        }
    }

    // Méthode pour rechercher des emprunts par intervalle de date Debut
    public static List<Emprunt> rechercherEmpruntsParIntervalleDateDebut(Date debut, Date fin) throws SQLException {
        List<Emprunt> emprunts = new ArrayList<>();
        String query = "SELECT * FROM emprunt WHERE dateDebut BETWEEN ? AND ?";
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(debut.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(fin.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Adherent adherent = AdherentManager.rechercherAdherentParId(resultSet.getInt("adherent_id"));
                Document document = DocumentManager.rechercherDocumentParId(resultSet.getInt("document_code"));
                Date dateDebut = resultSet.getDate("dateDebut");
                Date dateLimite = resultSet.getDate("dateLimite");
                Date dateRetour = resultSet.getDate("dateRetour");
                Emprunt emprunt = new Emprunt(adherent, document, dateDebut, dateLimite);
                emprunt.setId(resultSet.getInt("id"));
                emprunt.setDateRetour(dateRetour);
                emprunts.add(emprunt);
            }
            return emprunts;
        }
    }
    
    //Methode qui retourne le nombre d'emprunts d'un documents
    public static int getNombreEmprunts(Document document) {
        int nombreEmprunts = 0;
        String query = "SELECT COUNT(*) AS nombre_emprunts FROM emprunt WHERE document_code = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, document.getCode());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nombreEmprunts = resultSet.getInt("nombre_emprunts");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreEmprunts;
    }
}
