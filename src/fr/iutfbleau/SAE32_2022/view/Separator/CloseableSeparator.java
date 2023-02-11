package fr.iutfbleau.SAE32_2022.view.Separator;

import java.util.LinkedList;
import java.util.List;

import java.awt.Component;

import fr.iutfbleau.SAE32_2022.controller.CloseableListener;

/**
 * Classe permettant de représenter un séparateur pouvant être ouvert ou fermé.
 * Lorsqu'il est fermé, les éléments enfants de cet objet ne sont plus visibles.
 */
public class CloseableSeparator extends Separator {

    /** Indique si le séparateur est fermé ou non. */
    private boolean isClosed = true;
    /** La liste des éléments enfants du séparateur. */
    private List<Component> listComponents;
    /** Le séparateur est-il suivi d'une virgule */
    private boolean comma = false; 

    /**
     * Contructeur statique permettant de créer uniquement des [
     * @return une nouvelle instance de CloseableSeparator
     */
    public static CloseableSeparator object() {
        return new CloseableSeparator("[");
    }

    /**
     * Contructeur statique permettant de créer uniquement des {
     * @return une nouvelle instance de CloseableSeparator
     */
    public static CloseableSeparator array() {
        return new CloseableSeparator("{");
    }

    /**
     * Contructeur statique permettant de créer uniquement des (
     * @return une nouvelle instance de CloseableSeparator
     */
    public static CloseableSeparator phpArray() {
        return new CloseableSeparator("(");
    }

    /**
     * Constructeur de la classe.
     *
     * @param string le texte à afficher pour ce séparateur.
     */
    private CloseableSeparator(String string) {
        super(string);
        this.listComponents = new LinkedList<>();
        this.addMouseListener(CloseableListener.getInstance());
        this.changeText();
    }

    /**
     * Ajoute un élément enfant à ce séparateur.
     *
     * @param comp l'élément enfant à ajouter.
     */
    public void addChild(Component comp) {
        this.listComponents.add(comp);
        comp.setVisible(!isClosed);
    }

    /**
     * Ajoute une virgule à ce séparateur.
     *
     * @param comp la virgule à ajouter.
     */
    public void addComma(Component comp) {
        addChild(comp);
        this.comma = true;
        this.changeText();
    }

    /**
     * Ferme le séparateur.
     */
    public void close() {
        this.isClosed = true;
        changeText();
        this.hideChild();
    }

    /**
     * Ouvre le séparateur.
     */
    public void open() {
        // TODO Auto-generated method stub
        this.isClosed = false;
        changeText();
        this.showChild();
    }

    /**
     * Cache les éléments enfants de ce séparateur.
     */
    protected void hideChild() {
        for (Component label : listComponents) {
            label.setVisible(false);
            if(label instanceof CloseableSeparator)
                ((CloseableSeparator)label).hideChild();
        }
      
    }

    /**
     * Affiche les éléments enfants de ce séparateur, si le séparateur est ouvert.
     */
    protected void showChild() {
        for (Component label : listComponents) {
            label.setVisible(!isClosed);
            if(label instanceof CloseableSeparator)
                ((CloseableSeparator)label).showChild();
        }
    }

    /**
     * Ouvre cet élément et tous ces fils
     */
    public void openAll(){
        if(isClosed)
            open();
        for(Component element : listComponents) {
            if(element instanceof CloseableSeparator)
                ((CloseableSeparator)element).openAll();
        }
    }   

    /**
     * Ferme cet élément et tous ces fils
     */
    public void closeAll(){
        if(!isClosed)
            close();
        for(Component element : listComponents) {
            if(element instanceof CloseableSeparator)
                ((CloseableSeparator)element).closeAll();
        }
    }   

    /**
     * Change le texte du JLabel
     */
    private void changeText() {
        if(!isClosed) {
            this.setText(text);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(text);
        sb.append("...");
        sb.append(Character.toString((char) (text.charAt(0) + 2)));
        if(comma)
            sb.append(",");

        this.setText(sb.toString()); // { => } [ => ]
    }

    /**
     * Indique si le séparateur est fermé ou non.
     *
     * @return true si le séparateur est fermé, false sinon.
     */
    public boolean isClosed() {
        return isClosed;
    }
    
}
