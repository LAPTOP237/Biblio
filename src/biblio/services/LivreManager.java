/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio.services;

import biblio.Livre;
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
public class LivreManager {
    public static void ajouterLivre(Livre livre) {
        String query = "INSERT INTO livre (auteur, editeur, dateEdition, code_doc) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, livre.getAuteur());
            preparedStatement.setString(2, livre.getEditeur());
            preparedStatement.setDate(3, new java.sql.Date(livre.getDateEdition().getTime()));
            preparedStatement.setInt(4, livre.getCodeDoc());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifierLivre(Livre livre) {
        String query = "UPDATE livre SET auteur = ?, editeur = ?, dateEdition = ? WHERE code = ?";
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, livre.getAuteur());
            preparedStatement.setString(2, livre.getEditeur());
            preparedStatement.setDate(3, new java.sql.Date(livre.getDateEdition().getTime()));
            preparedStatement.setInt(4, livre.getCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void supprimerLivre(int idLivre) {
        String query = "DELETE FROM livre WHERE code = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idLivre);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Livre> afficherListeLivres() {
        List<Livre> livres = new ArrayList<>();
        String query = "SELECT * FROM livre";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Document document = DocumentManager.rechercherDocumentParId(resultSet.getInt("code_doc"));
                int code = resultSet.getInt("code");
                String titre = document.getTitre();
                String localisation = document.getLocalisation();
                int nbExemplaires = document.getNbExemplaires();
                String auteur = resultSet.getString("auteur");
                String editeur = resultSet.getString("editeur");
                Date dateEdition = resultSet.getDate("dateEdition");
                Livre livre = new Livre(titre, localisation, nbExemplaires, auteur, editeur, dateEdition);
                livre.setCode(code);
                livres.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }
    
}
