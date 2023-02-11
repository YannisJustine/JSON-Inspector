package fr.iutfbleau.SAE32_2022.view.Composant;

import javax.swing.JLabel;

import fr.iutfbleau.SAE32_2022.Constants;

/**
 * La classe Value est utilisée pour représenter graphiquement un objet de type boolean ou null dans l'interface de l'application
 */
public class Value extends JLabel {
    
    /**
     * La valeur JSON est représenté par une chaîne de caractères passée en paramètre lors de la création de l'objet.
     * La couleur de la chaîne de caractères est définie par la constante {@link Constants#VALUE}.
     * @param string la chaine a affichée
     */
    public Value(String string){
        super(string);
        this.setFont(Constants.FONT);
        this.setForeground(Constants.VALUE);
    }
}
