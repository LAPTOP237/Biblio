/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio.services;

import biblio.DatabaseManager;
import biblio.Utilisateur;
import java.sql.*;
/**
 *
 * @author LINJOUOM ALAIN P
 */
public class Auth {
    
   // Méthode pour se connecter à l'application
    public static Utilisateur seConnecter(String login, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;
        try {
            connection = DatabaseManager.getConnection();
            String query = "SELECT * FROM utilisateur WHERE login = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                utilisateur = new Utilisateur();
                utilisateur.setIdentifiant(resultSet.getInt("id"));
                utilisateur.setNom(resultSet.getString("nom"));
                utilisateur.setLogin(resultSet.getString("login"));
                utilisateur.setPassword(resultSet.getString("password"));
                utilisateur.setRole(resultSet.getInt("role"));
                utilisateur.setEtat(resultSet.getInt("etat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return utilisateur;
    }
    
}
