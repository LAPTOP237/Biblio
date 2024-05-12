/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblio.utils;

import java.util.Scanner;

/**
 *
 * @author LINJOUOM ALAIN P
 */
public class ScannerUtils {
    
    // une fonction qui prends en entrée  un tableau d'entiers des valeurs accepter. la fonction va demander a l'utilisateur d'entrée un entier entre ces valeurs et va retour ce que l'utilisateur aura taper au clavier. La fonction doit verifier si l'utilisateur entre vraiment un entier compris entre ces nombre sinon continuer a le demander jusqu'a ce qu'il entre le bon.
    public static int demanderEntier(int[] valeursAcceptees) {
        int valeur;
        Scanner scanner = new Scanner(System.in);
            valeur = 0;
            boolean saisieValide = false;
            while (!saisieValide) {
                try {
                    System.out.println("Entrez un entier parmi les valeurs suivantes : ");
                    for (int i = 0; i < valeursAcceptees.length; i++) {
                        System.out.print(valeursAcceptees[i]);
                        if (i < valeursAcceptees.length - 1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.print(": ");
                    valeur = scanner.nextInt();
                    
                    for (int i = 0; i < valeursAcceptees.length; i++) {
                        if (valeur == valeursAcceptees[i]) {
                            saisieValide = true;
                            break;
                        }
                    }
                    
                    if (!saisieValide) {
                        System.out.println("Erreur : veuillez saisir une valeur valide.");
                    }
                    
                } catch (Exception e) {
                    System.out.println("Erreur : veuillez saisir un entier valide.");
                    scanner.nextLine(); // Pour vider la ligne incorrecte
                }
            }
        return valeur;
    }
    
    public static int demanderEntier() {
        int valeur;
        Scanner scanner = new Scanner(System.in);
            valeur = 0;
            boolean saisieValide = false;
            while (!saisieValide) {
                try {
                    System.out.println("Entrez un entier: ");
                    valeur = scanner.nextInt();
                    saisieValide = true;
                } catch (Exception e) {
                    System.out.println("Erreur : veuillez saisir un entier valide.");
                    scanner.nextLine(); // Pour vider la ligne incorrecte
                }
            }
       
        return valeur;
    }
    
}
