package fr.iutfbleau.SAE32_2022.view.Composant;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import fr.iutfbleau.SAE32_2022.Constants;
/**
 * La classe Chaine est utilisée pour représenter graphiquement un objet JsonString dans l'interface de l'application
 */
public class Chaine extends JTextArea{
    
    /**
     * La chaine JSON est représenté par une chaîne de caractères passée en paramètre lors de la création de l'objet.
     * La couleur de la chaîne de caractères est définie par la constante {@link Constants#CHAINE}.
     * @param asString la chaine a affichée
     */
    public Chaine(String asString) {
        super(asString);
        this.setOpaque(false);
        this.setHighlighter(null);
        this.setEditable(false);
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        this.setFont(Constants.FONT);
        this.setForeground(Constants.CHAINE);
        this.setBackground(Constants.BACK); // Probleme avec setOpaque et le LookAndFeel Nimbus
    }
    
}
