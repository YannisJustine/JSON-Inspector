package fr.iutfbleau.SAE32_2022.view.Composant;

import javax.swing.JLabel;

import fr.iutfbleau.SAE32_2022.Constants;

/**
 * La classe Number est utilisée pour représenter graphiquement un objet JsonNumber dans l'interface de l'application
 */
public class Number extends JLabel {
    
     /**
     * Le nombre JSON est représenté par une chaîne de caractères passée en paramètre lors de la création de l'objet.
     * La couleur de la chaîne de caractères est définie par la constante {@link Constants#NUMBER}.
     * @param string la chaine a affichée
     */
    public Number(String string){
        super(string);
        this.setFont(Constants.FONT);
        this.setForeground(Constants.NUMBER);
    }
}
