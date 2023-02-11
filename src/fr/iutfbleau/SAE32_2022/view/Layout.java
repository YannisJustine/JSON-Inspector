package fr.iutfbleau.SAE32_2022.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.iutfbleau.SAE32_2022.Constants;
import fr.iutfbleau.SAE32_2022.model.JsonValue;
import fr.iutfbleau.SAE32_2022.view.Composant.Chaine;
import fr.iutfbleau.SAE32_2022.view.Composant.Nom;
import fr.iutfbleau.SAE32_2022.view.Composant.Number;
import fr.iutfbleau.SAE32_2022.view.Composant.Spaces;
import fr.iutfbleau.SAE32_2022.view.Composant.Value;
import fr.iutfbleau.SAE32_2022.view.Separator.CloseableSeparator;
import fr.iutfbleau.SAE32_2022.view.Separator.Separator;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Component;

import java.util.Map.Entry;


/**
 * Classe représentant le panneau dans la fenêtre principale de l'application.
 * Elle permet de mettre en forme les différents éléments du fichier JSON sous forme de séparateurs et de labels colorés.
 */
public class Layout {

    private JPanel panel;
    private JPanel line;
    private GridBagConstraints gbc;
    private JsonValue value;
    private boolean isPhp;
    private Component root;
    

    /**
     * Constructeur de la classe.
     * @param panel le panel sur lequel dessiner les composants
     *
     * 
     */
    public Layout(JPanel panel) {
        this.panel = panel;
        this.isPhp = false;
        panel.setBackground(Constants.BACK);
        this.panel.setLayout(new GridBagLayout());
        this.gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
    }

    /**
     * Indique si l'affichage est en mode PHP.
     * 
     * @return {@code true} si l'affichage est en mode PHP, {@code false} sinon
     */
    public boolean isPHP() {
        return isPhp;
    }

    /**
     * Renvoie l'objet JSON affiché par ce Layout.
     * 
     * @return l'objet JSON affiché par ce Layout
     */
    public JsonValue getValue() {
        return value;
    }

    public Component getLabel() {
        return root;
    }

    /**
     * Modifie l'objet JSON affiché par ce Layout.
     * 
     * @param value le nouvel objet JSON à afficher
     */
    public void setValue(JsonValue value) {
        this.value = value;
    }

    /**
     * Réinitialise l'affichage.
     */
    private void reset() {
        panel.removeAll();
        gbc.weighty = 0;
        gbc.gridy = 0;
        newLine();
    }

    /**
     * Ajoute un espace vide en bas du panneau pour remplir la fenêtre.
     */
    private void fillEmptySpace() {
        gbc.weighty = 1;
        gbc.gridy++;
        this.panel.add(new JLabel(),gbc);
    }

    /**
     * Affiche l'objet JSON avec son racine ouverte.
     */
    public void showJson() {
        reset();
        this.root = addComposant(value, 0);
        fillEmptySpace();
        if(this.root instanceof CloseableSeparator)
            ((CloseableSeparator)root).open();
        this.isPhp = false;
        panel.revalidate();
    }

    /**
     * Permet de fermer tous les {@link fr.iutfbleau.SAE32_2022.view.Separator.Separator} sauf le dernier.
     */
    public void closeAll() {
        if(root instanceof CloseableSeparator){
            ((CloseableSeparator)root).closeAll();
            ((CloseableSeparator)root).open();
        }
    }

    /**
     * Permet d'ouvrir tous les {@link fr.iutfbleau.SAE32_2022.view.Separator.Separator} sauf le dernier.
     */
    public void openAll() { 
        if(root instanceof CloseableSeparator){
            ((CloseableSeparator)root).openAll();
        }
    } 

    /**
     * Permet de "sauter une ligne".
     */
    private void newLine() {
        this.line = new FlowPanel();
        this.panel.add(line, gbc);
        gbc.gridy++;
    }

    /**
     * Permet d'effectuer l'affichage d'un objet JSON en fonction de son type.
     * @param value l'objet JSON
     * @param spaces le nombre d'espaces
     * @return Component pour l'ajouter au CloseableSeparator
     */
    private Component addComposant(JsonValue value, int spaces) {
        if (value.isObject())
            return this.addObject(value, spaces);
        else if (value.isString())
            return this.addString(value, spaces);
        else if (value.isArray())
            return this.addArray(value, spaces);
        else if (value.isNumber())
            return this.addNumber(value, spaces);
        else
            return this.addBoolean(value, spaces);
    }

    /**
     * Affichage d'un objet JSON Boolean.
     * @param value l'objet JSON
     * @param spaces le nombre d'espaces
     * @return un Component
     */
    private Component addBoolean(JsonValue value, int spaces) {
        Value v = new Value(value.asString());
        line.add(v);
        return v;
    }

    
    /**
     * Affichage d'un objet JSON Number.
     * @param value l'objet JSON
     * @param spaces le nombre d'espaces
     * @return un Component
     */
    private Component addNumber(JsonValue value, int spaces) {
        Number n = new Number(value.asString());
        line.add(n);
        return n;
    }

    
    /**
     * Affichage d'un objet JSON String.
     * @param value l'objet JSON
     * @param spaces le nombre d'espaces
     * @return un Component
     */
    private Component addString(JsonValue value, int spaces) {
        Chaine c = new Chaine("\"" + value.asString() + "\"");
        line.add(c);
        return c;
    }

    /**
     * Affichage d'un objet JSON Array.
     * @param value l'objet JSON
     * @param spaces le nombre d'espaces
     * @return un Component
     */
    private Component addArray(JsonValue value, int spaces) {
        CloseableSeparator cs = CloseableSeparator.object();
        line.add(cs);
        newLine();
        boolean first = true;
        Component finalchild = null;
        for (JsonValue child : value.getList()) {
            if(!first) {
                Separator comma = new Separator(",");
                if(finalchild instanceof CloseableSeparator)
                    ((CloseableSeparator)finalchild).addComma(comma);
                else
                    cs.addChild(comma);
                line.add(comma);
                newLine();
            }
            if(first) first = false;
            line.add(new Spaces(spaces + Constants.SPACES));
            cs.addChild(line);
            finalchild = addComposant(child, spaces + Constants.SPACES);
            cs.addChild(finalchild);
        }
        newLine();
        line.add(new Spaces(spaces));
        line.add(new Separator("]"));
        cs.addChild(line);
        return cs;
    }


    /**
     * Affichage d'un objet JSON Object.
     * @param value l'objet JSON
     * @param spaces le nombre d'espaces
     * @return un Component
     */
    private Component addObject(JsonValue value, int spaces) {
        CloseableSeparator cs = CloseableSeparator.array();
        line.add(cs);
        newLine();
        boolean first = true;
        Component finalchild = null;
        for (Entry<String, JsonValue> entry : value.getEntry()) {
            if(!first) {
                Separator comma = new Separator(",");
                if(finalchild instanceof CloseableSeparator)
                    // Si le dernier enfant ajouté est un CloseableSeparator, on lui ajoute la virgule pour l'efface lorsque qu'il est fermer
                    ((CloseableSeparator)finalchild).addComma(comma); 
                else
                    // Sinon on ajoute la virgule au CloseableSeparator courant 
                    cs.addChild(comma);
                line.add(comma);
                newLine();
            }
            if(first) first = false;
            line.add(new Spaces(spaces + Constants.SPACES));
            line.add(new Nom("\"" + entry.getKey() + "\""));
            line.add(new Separator(":"));
            line.add(new Spaces(1));
            cs.addChild(line);
            finalchild = addComposant(entry.getValue(), spaces + Constants.SPACES);
            cs.addChild(finalchild);
        }
        newLine();
        line.add(new Spaces(spaces));
        line.add(new Separator("}"));
        cs.addChild(line);
        return cs;
    }


    // Meme fonction mais pour le PHP

    public void showPhp() {
        reset();
        addComposantPhp(value, 0);
        fillEmptySpace();
        panel.revalidate();
        this.isPhp = true;
    }

    private void addComposantPhp(JsonValue value, int spaces){
        if (value.isArray())
            this.addArrayPhp(value, spaces);
        else if (value.isObject())
            this.addObjectPhp(value, spaces);
        else if (value.isString())
            line.add(new Chaine(value.asString()));
        else if (value.isNumber())
            line.add(new Number(value.asString()));
        else
            line.add(new Value(value.asString()));
    }

    private void addArrayPhp(JsonValue value, int spaces) {
        line.add(new Nom("Array"));
        newLine();
        line.add(new Spaces(spaces + Constants.SPACES));
        line.add(new Separator("("));
        newLine();
        int cpt = 0;
        for (JsonValue child : value.getList()) {
            line.add(new Spaces(spaces + 4));
            line.add(new Nom("[" + cpt + "]"));
            line.add(new Spaces(1));
            line.add(new Separator("=>"));
            line.add(new Spaces(1));   
            addComposantPhp(child, spaces + 4);
            cpt++;
            newLine();
        }
        newLine();
        line.add(new Spaces(spaces + Constants.SPACES));
        line.add(new Separator(")"));
        return ;
    }

    private void addObjectPhp(JsonValue value, int spaces) {
        line.add(new Nom("Array"));
        newLine();
        line.add(new Spaces(spaces + Constants.SPACES));
        line.add(new Separator("("));
        newLine();

        for (Entry<String, JsonValue> entry : value.getEntry()) {
            line.add(new Spaces(spaces + 4));
            line.add(new Nom("[" + entry.getKey() + "]"));
            line.add(new Spaces(1));
            line.add(new Separator("=>"));
            line.add(new Spaces(1));
            addComposantPhp(entry.getValue(), spaces + 4);
            newLine();
        }
        newLine();
        line.add(new Spaces(spaces + Constants.SPACES));
        line.add(new Separator(")"));
    }
}
