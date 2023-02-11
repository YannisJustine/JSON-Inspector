package fr.iutfbleau.SAE32_2022.view.Separator;

import javax.swing.JLabel;

import fr.iutfbleau.SAE32_2022.Constants;

/**
 * Classe représentant un séparateur.
 */
public class Separator extends JLabel{
     /** Le texte à afficher pour ce séparateur. */
    protected String text;

    
    /**
     * Constructeur de la classe.
      * La couleur de la chaîne de caractères est définie par la constante {@link Constants#SEPARATOR}.
     * @param string la chaine a affichée
     */
    public Separator(String string) {
        super(string);
        this.setFont(Constants.FONT);
        this.setForeground(Constants.SEPARATOR);
        this.text = string;
    }

}
