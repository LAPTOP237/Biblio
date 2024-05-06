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
public class Livre extends Document{
    
     /**
     * Default constructor
     * @param titre
     * @param localisation
     * @param nbExemplaires
     * @param auteur
     * @param editeur
     * @param dateEdition
     */
    public Livre(String titre, String localisation, int nbExemplaires, String auteur, String editeur, Date dateEdition) {
        super(titre, localisation, nbExemplaires);
        this.auteur = auteur;
        this.editeur = editeur;
        this.dateEdition = dateEdition;
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
    public String editeur;

    /**
     * 
     */
    public Date dateEdition;
     /**
     * 
     */
    private int code_doc;
     /**
     * Debut Getters et Setters
     * @return
     */

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public Date getDateEdition() {
        return dateEdition;
    }

    public void setDateEdition(Date dateEdition) {
        this.dateEdition = dateEdition;
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
    /**
     * FIN Getters et Setters
     */

}
