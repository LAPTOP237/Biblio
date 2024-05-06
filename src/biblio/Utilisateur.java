/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio;

/**
 *
 * @author LINJOUOM ALAIN P
 */
public class Utilisateur {
    
    // Default constructor
    public Utilisateur(){
        
    }
     /**
     * @param nom
     * @param login
     * @param password
     * @param role
     */
    public Utilisateur(String nom, String login, String password, int role) {
        this.nom = nom;
        this.login = login;
        this.password = password;
        this.role = role;
        this.etat = 0;
    }

    /**
     * 
     */
    private int identifiant;

    /**
     * 
     */
    private String nom;

    /**
     * 
     */
    private String login;

    /**
     * 
     */
    public String password;

    /**
     * 
     */
    private int role;
    
    /**
     * 
     */
    private int etat;

    /**
     * Debut Getters et Setters
     * @return 
     */

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    public int getEtat() {
        return role;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    /**
     * FIN Getters et Setters
     */
}
