package fr.iutfbleau.SAE32_2022.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.iutfbleau.SAE32_2022.view.Layout;

/**
 * La classe PhpParser est un écouteur d'événements qui permet de basculer entre l'affichage en Java et en PHP d'un objet {@link fr.iutfbleau.SAE32_2022.model.JsonValue}.
 * Elle est utilisée par le bouton "Passer en PHP" dans l'interface graphique de l'application.
*/
public class PhpParser implements ActionListener{
    /** L'objet {@link fr.iutfbleau.SAE32_2022.view.Layout} associé à l'interface graphique de l'application. */
    private Layout layout;
    /* Le bouton "Passer en PHP". */
    private JButton button;

    /**
     * Constructeur de la classe PhpParser.
     * @param layout L'objet {@link  fr.iutfbleau.SAE32_2022.view.Layout} associé à l'interface graphique de l'application.
     * @param button Le bouton "Passer en PHP".
     */
    public PhpParser(Layout layout, JButton button){
        this.layout = layout;
        this.button = button;
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur le bouton "Passer en PHP".
     * Si l'objet {@link fr.iutfbleau.SAE32_2022.model.JsonValue} est affiché en Java, il est affiché en PHP. Sinon, il est affiché en Java.
     * @param e L'événement déclenché par le clic sur le bouton "Passer en PHP".
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(layout.getValue() == null)
            return;
        if(!layout.isPHP()){
            this.layout.showPhp();
            this.button.setText("Passer en JSON");
        }
        else {
            this.layout.showJson();
            this.button.setText("Passer en PHP");
        }
       
    }
    
}
