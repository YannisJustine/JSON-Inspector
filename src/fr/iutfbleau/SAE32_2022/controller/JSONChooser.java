package fr.iutfbleau.SAE32_2022.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Cette classe représente un écouteur d'événement pour le bouton dossier.
 */
public class JSONChooser  implements ActionListener{ 

    /** Champ de texte contenant l'URL du fichier JSON à afficher. */
    private JTextField URL;
    /** Le fileChooser */
    private JFileChooser choose;

    /**
     * Le constructeur de la classe.
     * @param URL le champ de texte à remplir
     */
    public JSONChooser(JTextField URL) {
        this.URL = URL;
        this.choose = new JFileChooser();
        this.choose.setCurrentDirectory(new File("."));
        this.choose.setDialogTitle("Choisissez un fichier JSON: ");
        this.choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.choose.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier JSON", "json");
        this.choose.addChoosableFileFilter(filter);
    }
    
    /**
     * Méthode appelée lorsqu'un clic sur le bouton de dossier est détecté.
     * @param e Objet contenant les informations sur l'événement déclenché.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int res = this.choose.showOpenDialog(URL.getParent().getParent());
        if(res == JFileChooser.APPROVE_OPTION) {
            if(this.choose.getSelectedFile().isFile()) 
                URL.setText(this.choose.getSelectedFile().getAbsolutePath());
        }
    }
    
}

