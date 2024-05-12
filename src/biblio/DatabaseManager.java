/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio;

import java.sql.*;

/**
 *
 * @author LINJOUOM ALAIN P
 */
public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/biblio";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        try{ 
             connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e){
            System.out.println("Erreur lors de la connexion a la BD, verifiez si le server est lancé et relancez l'app ");
        }
        return connection;
    }
    
}
