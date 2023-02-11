package fr.iutfbleau.SAE32_2022.view.Composant;

import javax.swing.JLabel;

import fr.iutfbleau.SAE32_2022.Constants;


/**
 * La classe Spaces est utilisée pour représenter graphiquement un espace dans l'interface de l'application
 */
public class Spaces extends JLabel{
    public Spaces(int spaces){
        super();
        this.setFont(Constants.FONT);
        this.setForeground(Constants.VALUE);
        String nom = new String();
        for(int i = 0; i < spaces; i++)
            nom += " ";
        this.setText(nom);
    }
}
