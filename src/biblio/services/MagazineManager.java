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
         String query = "DELETE FROM magazine WHERE code = ?";
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
        String query = "SELECT * FROM magazine";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                 Document document = DocumentManager.rechercherDocumentParId(resultSet.getInt("code_doc"));
                int code = resultSet.getInt("code");
                String titre = document.getTitre();
                String localisation = document.getLocalisation();
                int nbExemplaires = document.getNbExemplaires();
                String frequenceParution = resultSet.getString("frequenceParution");
                Magazine magazine = new Magazine(titre, localisation, nbExemplaires, frequenceParution);
                magazine.setCode(code);
                magazines.add(magazine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return magazines;
    }
    
}
