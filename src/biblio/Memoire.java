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
public class Memoire extends Document{
     /**
     * Default constructor
     * @param titre
     * @param localisation
     * @param nbExemplaires
     * @param nomCandidat
     * @param titreMemoire
     * @param dateSoutenance
     */
    public Memoire(String titre, String localisation, int nbExemplaires, String nomCandidat, String titreMemoire, Date dateSoutenance) {
        super(titre, localisation, nbExemplaires);
        this.nomCandidat = nomCandidat;
        this.titreMemoire = titreMemoire;
        this.dateSoutenance = dateSoutenance;
        this.code_doc = super.getCode();
    }
    /**
     * 
     */
    private int code;
    /**
     * 
     */
    public String nomCandidat;

    /**
     * 
     */
    public String titreMemoire;

    /**
     * 
     */
    public Date dateSoutenance;
     /**
     * 
     */
    private int code_doc;
     /**
     * Debut Getters et Setters
     * @return 
     */

    public String getNomCandidat() {
        return nomCandidat;
    }

    public void setNomCandidat(String nomCandidat) {
        this.nomCandidat = nomCandidat;
    }

    public String getTitreMemoire() {
        return titreMemoire;
    }

    public void setTitreMemoire(String titreMemoire) {
        this.titreMemoire = titreMemoire;
    }

    public Date getDateSoutenance() {
        return dateSoutenance;
    }

    public void setDateSoutenance(Date dateSoutenance) {
        this.dateSoutenance = dateSoutenance;
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
