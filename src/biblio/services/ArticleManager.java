/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio.services;

import biblio.Article;
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
public class ArticleManager {
    // Méthode pour ajouter un article
    public static void ajouterArticle(Article article) {
        String query = "INSERT INTO article (auteur, datePublication,code_doc) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, article.getAuteur());
            preparedStatement.setDate(2, new java.sql.Date(article.getDatePublication().getTime()));
            preparedStatement.setInt(3, article.getCodeDoc());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour modifier un article
    public static void modifierArticle(Article article) {
        String query = "UPDATE article SET auteur = ?, datePublication = ? WHERE code = ?";
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, article.getAuteur());
            preparedStatement.setDate(2, new java.sql.Date(article.getDatePublication().getTime()));
            preparedStatement.setInt(3, article.getCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un article
    public static void supprimerArticle(int idArticle) {
        String query = "DELETE FROM article WHERE code_doc = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idArticle);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour afficher la liste des articles
    public static List<Article> afficherListeArticles() {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT * FROM article JOIN document ON article.code_doc = document.code";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                //Document document = DocumentManager.rechercherDocumentParId(resultSet.getInt("article.code_doc"));
                int code = resultSet.getInt("article.code");
                String titre = resultSet.getString("document.titre");
                String localisation = resultSet.getString("document.localisation");
                int nbExemplaires = resultSet.getInt("document.nbExemplaires");
                String auteur = resultSet.getString("auteur");
                Date datePublication = resultSet.getDate("datePublication");
                Article article = new Article(titre, localisation, nbExemplaires, auteur, datePublication);
                article.setCode(code);
                int codeDoc = resultSet.getInt("article.code_doc");
                article.setCodeDoc(codeDoc);
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

//Rechercher Article par code du codocument
    public static Article rechercherArticleParId(int idArticle) throws SQLException {
    Article article = null;
    String query = "SELECT * FROM article WHERE code_doc = ?";
    try (Connection connection = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, idArticle);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                // Récupération des informations de l'article depuis la base de données
                String titre = resultSet.getString("titre");
                String localisation = resultSet.getString("localisation");
                int nbExemplaires = resultSet.getInt("nbExemplaires");
                String auteur = resultSet.getString("auteur");
                Date datePublication = resultSet.getDate("datePublication");

                // Création d'un objet Article
                article = new Article(titre, localisation, nbExemplaires, auteur, datePublication);
                article.setCodeDoc(resultSet.getInt("code_doc"));
                article.setCode(resultSet.getInt("code"));
            }
        }
    }
    return article;
}
    
}
