/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio;

import java.util.*;
/**
 *
 * @author LINJOUOM ALAIN P
 */
public class Article extends Document{
    // Default constructor
    public Article(){}
    /**
     * 
     * @param titre
     * @param localisation
     * @param nbExemplaires
     * @param auteur
     * @param datePublication
     */
    public Article(String titre, String localisation,int nbExemplaires, String auteur, Date datePublication) {
        super(titre, localisation, nbExemplaires);
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.code_doc = super.getCode();
    }

    /**
     * 
     */
    private int code;
    /**
     * 
     */
    public String auteur;

    /**
     * 
     */
    public Date datePublication;
    /**
     * 
     */
    private int code_doc;
    
    /**
     * Debut Getters et Setters
     */
    /**
     * @return
     */
    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    

    public int getCodeDoc() {
        return code_doc;
    }

    public void setCodeDoc(int code_doc) {
        this.code_doc = code_doc;
    }
    
    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    /**
     * FIN Getters et Setters
     */

}
