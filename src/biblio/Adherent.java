/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio;

/**
 *
 * @author LINJOUOM ALAIN P
 */
public class Adherent {
    
    //Default constructor
    public Adherent() {
    }

     /**
     * @param nom
     * @param prenom
     * @param adresse
     * @param typeAdherent
     */
    public Adherent(String nom, String prenom, String adresse, int typeAdherent) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.typeAdherent = typeAdherent;
        switch (typeAdherent){
                case 1 -> {
                    this.nombreEmpruntsMax = 2;
                    this.dureePret = 7;
            }
                case 2 -> {
                    this.nombreEmpruntsMax = 4;
                    this.dureePret = 21;
            }
                case 3 -> {
                    this.nombreEmpruntsMax = 1;
                    this.dureePret = 7;
            }
                default -> {
                    this.nombreEmpruntsMax = 1;
                    this.dureePret = 7;
            }
            }
        
    }


    /**
     * 
     */
    public String nom;

    /**
     * 
     */
    public String prenom;

    /**
     * 
     */
    private int identifiant;

    /**
     * 
     */
    public String adresse;

    /**
     * 
     */
    public int typeAdherent;

    /**
     * 
     */
    public int nombreEmpruntsMax;

    /**
     * 
     */
    public int dureePret;

    /**
     * Debut Getters et Setters
     */
    
    /**
     * @return 
     */
    public String getNom() {
        return nom;
    }
    /**
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     * @return 
     */
    public String getPrenom() {
        return prenom;
    }
    /**
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    /**
     * @return 
     */
    public int getIdentifiant() {
        return identifiant;
    }
    /**
     * @param identifiant
     */
    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }
    /**
     * @return
     */
    public String getAdresse() {
        return adresse;
    }
    /**
     * @param adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    /**
     * @return 
     */
    public int getTypeAdherent() {
        return this.typeAdherent;
    }
    /**
     * @param typeAdherent
     */
    public void setTypeAdherent(int typeAdherent) {
        this.typeAdherent = typeAdherent;
        switch (typeAdherent){
                case 1 -> {
                    this.nombreEmpruntsMax = 2;
                    this.dureePret = 7;
            }
                case 2 -> {
                    this.nombreEmpruntsMax = 4;
                    this.dureePret = 21;
            }
                case 3 -> {
                    this.nombreEmpruntsMax = 1;
                    this.dureePret = 7;
            }
                default -> {
                    this.nombreEmpruntsMax = 1;
                    this.dureePret = 7;
            }
            }
    }
    /**
     * @return 
     */
    public int getNombreEmpruntsMax() {
        return nombreEmpruntsMax;
    }
    /**
     * @param nombreEmpruntsMax
     */
    public void setNombreEmpruntsMax(int nombreEmpruntsMax) {
        this.nombreEmpruntsMax = nombreEmpruntsMax;
    }
    /**
     * @return 
     */
    public int getDureePret() {
        return dureePret;
    }
    /**
     * @param dureePret
     */
    public void setDureePret(int dureePret) {
        this.dureePret = dureePret;
    }
    /**
     * FIN Getters et Setters
     */
    
    
}
