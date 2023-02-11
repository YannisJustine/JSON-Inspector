package fr.iutfbleau.SAE32_2022.view.Composant;

import javax.swing.JLabel;

import fr.iutfbleau.SAE32_2022.Constants;

/**
 * La classe Nom est utilisée pour représenter graphiquement une clé d'un JsonObject dans l'interface de l'application
 */
public class Nom extends JLabel{
     
    /**
     * Le NOM JSON est représenté par une chaîne de caractères passée en paramètre lors de la création de l'objet.
     * La couleur de la chaîne de caractères est définie par la constante {@link Constants#NOM}.
     * @param key la chaine a affichée
     */
    public Nom(String key) {
        super(key);
        this.setFont(Constants.FONT);
        this.setForeground(Constants.NOM);
    }
    
}
