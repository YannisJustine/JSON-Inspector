package fr.iutfbleau.SAE32_2022.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import javax.swing.JTextField;

import fr.iutfbleau.SAE32_2022.model.JsonValue;
import fr.iutfbleau.SAE32_2022.model.Parser;
import fr.iutfbleau.SAE32_2022.view.Layout;

/**
 * Cette classe représente un écouteur d'événement permettant de rafraîchir l'affichage du JSON.
 * Lorsqu'un clic sur le bouton de rafraîchissement est détecté, la chaîne de caractères contenue
 * dans le champ de texte est lue et convertie en un objet {@link fr.iutfbleau.SAE32_2022.model.JsonValue} grâce à la classe {@link fr.iutfbleau.SAE32_2022.model.Parser}.
 * L'objet ainsi obtenu est ensuite affiché dans le panneau de visualisation du JSON.
 */
public class RefreshListener implements ActionListener{
    /** Champ de texte contenant l'URL du fichier JSON à afficher. */
    private JTextField URL;
    /** Panneau de visualisation du JSON. */
    private Layout layout;
    /** Parser. */
    private Parser parser;

    /**
     * Constructeur de l'écouteur.
     * @param URL Champ de texte contenant l'URL du fichier JSON à afficher.
     * @param layout Panneau de visualisation du JSON.
     */
    public RefreshListener(JTextField URL, Layout layout){
        this.URL = URL;
        this.layout = layout;
        this.parser = new Parser();
    }

    /**
     * Méthode appelée lorsqu'un clic sur le bouton de rafraîchissement est détecté.
     * @param e Objet contenant les informations sur l'événement déclenché.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            show(parser.parse(Parser.read(URL.getText())));
        } catch (IOException ex) {
            try {
                show(parser.parse(Parser.read("file:" + URL.getText())));
            }
            catch(IOException ex1){
                JOptionPane.showMessageDialog(URL.getRootPane(),ex.getMessage(), "Impossible de charger le fichier",JOptionPane.ERROR_MESSAGE);
            } catch(Exception ex2) {
                JOptionPane.showMessageDialog(URL.getRootPane(), ex2.getMessage(), "Erreur de syntaxe",JOptionPane.ERROR_MESSAGE);
            } 
        } catch(Exception ex2) {
            JOptionPane.showMessageDialog(URL.getRootPane(), ex2.getMessage(), "Erreur de syntaxe",JOptionPane.ERROR_MESSAGE);
        } 
    }

    private void show(JsonValue value) {
        layout.setValue(value);
        if(layout.isPHP())
            layout.showPhp();
        else
            layout.showJson();
    }
    
}
