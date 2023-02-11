package fr.iutfbleau.SAE32_2022.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.iutfbleau.SAE32_2022.view.Layout;

/**
 * Cette classe représente un écouteur d'événement pour l'action de rouvrir tous les séparateurs de la liste.
 */
public class OpenListener implements ActionListener{ 

    /** La liste de séparateurs à rouvrir ou fermer lors de l'événement. */
    private Layout layout;
    /** Le bouton qui change de nom à chaque évènement. */
    private JButton button;
    private boolean isOpen = false;
    /**
     * Constructeur par défaut.
     */
    public OpenListener(Layout layout ,JButton button){
        this.button = button;
        this.layout = layout;
        
    }

    /**
     * Méthode appelée lors de l'événement de rouvrir les séparateurs.
     * Elle rouvre tous les séparateurs de la liste.
     * @param e L'événement déclencheur.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(layout.getValue() == null || layout.isPHP())   
            return;
        if(!isOpen){
            layout.openAll();
            button.setText("Tout plier"); 
            this.isOpen = true;
        }
        else {
            layout.closeAll();
            button.setText("Tout deplier"); 
            this.isOpen = false; 
        }
    }
        
}
