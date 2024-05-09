/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio;

import biblio.utils.DateUtils;
import static biblio.utils.DateUtils.convertDateToString;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author LINJOUOM ALAIN P
 */
public class Emprunt {
    // Default constructor
    public Emprunt(){}
     /**
     * 
     * @param adherent
     * @param document
     * @param dateDebut
     */
    public Emprunt(Adherent adherent, Document document, Date dateDebut) {
        this.adherent = adherent;
        this.document = document;
        this.dateDebut = dateDebut;
        try {
            this.dateLimite = DateUtils.getNext(convertDateToString(dateDebut),adherent.getDureePret() ) ;
        } catch (ParseException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dateRetour = null;
    }

    /**
     * 
     */
    public Adherent adherent;

    /**
     * 
     */
    public Document document;

    /**
     * 
     */
    public Date dateDebut;

    /**
     * 
     */
    public Date dateLimite;

    /**
     * 
     */
    public Date dateRetour;
    
    /**
     * 
     */
    private int id;
    
        /**
     * Debut Getters et Setters
     * @return 
     */

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
        try {
            this.dateLimite = DateUtils.getNext(convertDateToString(dateDebut),adherent.getDureePret() ) ;
        } catch (ParseException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * FIN Getters et Setters
     */

    /**
     * Marque le retour du document
     * @param dateRetour
     */
    public void marquerRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    /**
     * Vérifie si l'emprunt est en retard
     * @return 
     */
    public boolean estRetarde() {
        Date currentDate = new Date();
        return dateLimite.before(currentDate) && dateRetour == null;
    }
    
}
