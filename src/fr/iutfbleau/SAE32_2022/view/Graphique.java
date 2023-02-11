package fr.iutfbleau.SAE32_2022.view;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;


import fr.iutfbleau.SAE32_2022.Constants;
import fr.iutfbleau.SAE32_2022.controller.JSONChooser;
import fr.iutfbleau.SAE32_2022.controller.OpenListener;
import fr.iutfbleau.SAE32_2022.controller.PhpParser;
import fr.iutfbleau.SAE32_2022.controller.RefreshListener;

import java.awt.GridBagLayout;

import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;


/**
 * Classe représentant la fenêtre principale de l'application.
 * Cette fenêtre contient un champ de saisie permettant d'entrer l'URL d'un fichier JSON,
 * un bouton permettant de rafraîchir l'affichage du fichier JSON,
 * un bouton permettant de déplier tous les séparateurs de l'affichage du fichier JSON,
 * un bouton permettant de passer en mode PHP,
 */
public class Graphique extends JFrame{

    /**
     * Constructeur de la classe.
     * Initialise la fenêtre principale de l'application.
     */
    public Graphique(){
        super("Inspecteur JSON");
        JPanel choix = new JPanel(new GridBagLayout());
        JPanel coloration = new JPanel();
        JPanel panelButton = new JPanel(new WrapLayout());
        JButton button = new JButton("Rafraichir");
        JButton file = new JButton();
        file.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        JButton open = new JButton("Tout déplier");

        JScrollPane scroll = new JScrollPane(coloration);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
     
        Layout layout = new Layout(coloration);
        OpenListener listener = new OpenListener(layout, open);

        JLabel titre = new JLabel("Entrez l'URL du fichier", JLabel.CENTER);
        JTextField URL = new JTextField();
       

        button.addActionListener(new RefreshListener(URL, layout));
        file.addActionListener(new JSONChooser(URL));

        open.addActionListener(listener);
        panelButton.add(open);

        JButton php = new JButton("Passer en PHP");
        php.addActionListener(new PhpParser(layout,php));
        panelButton.add(php);


        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        choix.add(titre,gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        choix.add(URL,gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        choix.add(button,gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        choix.add(file,gbc);

     
        this.add(panelButton,BorderLayout.SOUTH);

        this.add(choix,BorderLayout.NORTH);
        this.add(scroll,BorderLayout.CENTER);

        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setMinimumSize(new Dimension(Constants.MINWIDTH,Constants.MINHEIGHT));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}