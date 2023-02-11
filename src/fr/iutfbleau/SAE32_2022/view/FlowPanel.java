package fr.iutfbleau.SAE32_2022.view;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import fr.iutfbleau.SAE32_2022.Constants;

/**
 * Classe d'un JPanel avec un WrapLayout et des marges nulles
 */
public class FlowPanel extends JPanel{

    /**
     * Constructeur
     */
    public FlowPanel(){
        super();
        FlowLayout fl = new WrapLayout(FlowLayout.LEFT);
        fl.setHgap(0);
        fl.setVgap(0);
        this.setLayout(fl);
        this.setBackground(Constants.BACK);
    }
}