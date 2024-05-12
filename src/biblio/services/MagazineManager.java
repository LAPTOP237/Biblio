/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio.services;

import biblio.Magazine;
import biblio.Document;
import biblio.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author LINJOUOM ALAIN P
 */
public class MagazineManager {
    public static void ajouterMagazine(Magazine magazine) {
        String query = "INSERT INTO magazine (frequenceParution, code_doc) VALUES (?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, magazine.getFrequenceParution());
            preparedStatement.setInt(2, magazine.getCodeDoc());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifierMagazine(Magazine magazine) {
        String query = "UPDATE magazine SET frequenceParution = ? WHERE code = ?";
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, magazine.getFrequenceParution());
            preparedStatement.setInt(2, magazine.getCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void supprimerMagazine(int idMagazine) {
         String query = "DELETE FROM magazine WHERE code_doc = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idMagazine);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Magazine> afficherListeMagazines() {
        List<Magazine> magazines = new ArrayList<>();
        String query = "SELECT * FROM magazine JOIN document on magazine.code_doc = document.code;";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                // Document document = DocumentManager.rechercherDocumentParId(resultSet.getInt("magazine.code_doc"));
                int code = resultSet.getInt("magazine.code");
                String titre = resultSet.getString("document.titre");
                String localisation = resultSet.getString("document.localisation");
                int nbExemplaires = resultSet.getInt("document.nbExemplaires");
                String frequenceParution = resultSet.getString("frequenceParution");
                Magazine magazine = new Magazine(titre, localisation, nbExemplaires, frequenceParution);
                magazine.setCode(code);
                int codeDoc = resultSet.getInt("magazine.code_doc");
                magazine.setCodeDoc(codeDoc);
                magazines.add(magazine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return magazines;
    }
        //Recherche Magazine par code document
    public static Magazine rechercherMagazineParId(int idMagazine) throws SQLException {
        Magazine magazine = null;
        String query = "SELECT * FROM magazine WHERE code_doc = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idMagazine);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Récupération des informations du magazine depuis la base de données
                    String titre = resultSet.getString("titre");
                    String localisation = resultSet.getString("localisation");
                    int nbExemplaires = resultSet.getInt("nbExemplaires");
                    String frequenceParution = resultSet.getString("frequenceParution");

                    // Création d'un objet Magazine
                    magazine = new Magazine(titre, localisation, nbExemplaires, frequenceParution);
                    magazine.setCodeDoc(resultSet.getInt("code_doc"));
                    magazine.setCode(resultSet.getInt("code"));
                }
            }
        }
        return magazine;
    }
    
}
