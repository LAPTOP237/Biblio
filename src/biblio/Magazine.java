/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio;

/**
 *
 * @author LINJOUOM ALAIN P
 */
public class Magazine extends Document{
      /**
     * Default constructor
     * @param titre
     * @param localisation
     * @param nbExemplaires
     * @param frequenceParution
     */
    public Magazine(String titre, String localisation, int nbExemplaires, String frequenceParution) {
        super(titre, localisation,nbExemplaires);
        this.frequenceParution = frequenceParution;
        this.code_doc = super.getCode();
    }
    /**
     * 
     */
    private int code;
    /**
     * 
     */
    public String frequenceParution;
     /**
     * 
     */
    private int code_doc;
     /**
     * Debut Getters et Setters
     * @return 
     */

    public String getFrequenceParution() {
        return frequenceParution;
    }
     /**
     * @param frequenceParution
     */
    public void setFrequenceParution(String frequenceParution) {
        this.frequenceParution = frequenceParution;
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
