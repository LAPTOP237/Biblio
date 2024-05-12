/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio.services;

import biblio.Memoire;
import biblio.Document;
import biblio.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author LINJOUOM ALAIN P
 */
public class MemoireManager {
    public static void ajouterMemoire(Memoire memoire) {
        String query = "INSERT INTO memoire (nomCandidat, titreMemoire, dateSoutenance,code_doc) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, memoire.getNomCandidat());
            preparedStatement.setString(2, memoire.getTitreMemoire());
            preparedStatement.setDate(3, new java.sql.Date(memoire.getDateSoutenance().getTime()));
            preparedStatement.setInt(4, memoire.getCodeDoc());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifierMemoire(Memoire memoire) {
        String query = "UPDATE memoire SET nomCandidat = ?, titreMemoire = ?, dateSoutenance = ? WHERE code = ?";
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, memoire.getNomCandidat());
            preparedStatement.setString(2, memoire.getTitreMemoire());
            preparedStatement.setDate(3, new java.sql.Date(memoire.getDateSoutenance().getTime()));
            preparedStatement.setInt(4, memoire.getCode());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void supprimerMemoire(int idMemoire) {
        String query = "DELETE FROM memoire WHERE code_doc = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idMemoire);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Memoire> afficherListeMemoires() {
        List<Memoire> memoires = new ArrayList<>();
        String query = "SELECT * FROM memoire JOIN document ON memoire.code_doc = document.code";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                //Document document = DocumentManager.rechercherDocumentParId(resultSet.getInt("memoire.code_doc"));
                int code = resultSet.getInt("memoire.code");
                String titre = resultSet.getString("document.titre");
                String localisation = resultSet.getString("document.localisation");
                int nbExemplaires = resultSet.getInt("document.nbExemplaires");
                String nomCandidat = resultSet.getString("nomCandidat");
                String titreMemoire = resultSet.getString("titreMemoire");
                Date dateSoutenance = resultSet.getDate("dateSoutenance");
                Memoire memoire = new Memoire(titre, localisation, nbExemplaires, nomCandidat, titreMemoire, dateSoutenance);
                memoire.setCode(code);
                int codeDoc = resultSet.getInt("memoire.code_doc");
                memoire.setCodeDoc(codeDoc);
                memoires.add(memoire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memoires;
    }

  public static Memoire rechercherMemoireParId(int idMemoire) throws SQLException {
    Memoire memoire = null;
    String query = "SELECT * FROM memoire WHERE code_doc = ?";
    try (Connection connection = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, idMemoire);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                // Récupération des informations du mémoire depuis la base de données
                String titre = resultSet.getString("titre");
                String localisation = resultSet.getString("localisation");
                int nbExemplaires = resultSet.getInt("nbExemplaires");
                String candidat = resultSet.getString("nomCandidat");
                String titreMemoire = resultSet.getString("titreMemoire");
                Date dateSoutenance = resultSet.getDate("dateSoutenance");

                // Création d'un objet Memoire
                memoire = new Memoire(titre, localisation, nbExemplaires, candidat, titreMemoire, dateSoutenance);
                memoire.setCodeDoc(resultSet.getInt("code_doc"));
                memoire.setCode(resultSet.getInt("code"));
            }
        }
    }
    return memoire;
}

    
}
