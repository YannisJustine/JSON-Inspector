package fr.iutfbleau.SAE32_2022.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.iutfbleau.SAE32_2022.view.Separator.CloseableSeparator;

/**
 * Classe permettant de gérer les événements de souris sur un séparateur pouvant être ouvert ou fermé.
 * Cette classe utilise le design pattern Singleton pour s'assurer qu'il n'y a qu'une seule instance de cet objet.
 *
 */
public class CloseableListener extends MouseAdapter {

    /** L'instance unique de cette classe. */
    private static CloseableListener instance;

    /**
     * Constructeur privé de la classe.
     * Le constructeur est privé pour empêcher la création d'autres instances de cet objet.
     */
    private CloseableListener() {

    }

    /**
     * Retourne l'instance unique de cette classe.
     * Si l'instance n'a pas encore été créée, elle est créée à cet appel.
     *
     * @return l'instance unique de cette classe.
     */
    public static CloseableListener getInstance() {
        if(CloseableListener.instance == null) {
            instance = new CloseableListener();
        }
        return CloseableListener.instance;
    }

    /**
     * Gère l'événement de souris lorsque l'utilisateur clique sur un séparateur.
     * Si le séparateur était fermé, il est ouvert.
     * Si le séparateur était ouvert, il est fermé.
     *
     * @param e l'événement de souris.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        CloseableSeparator cs = (CloseableSeparator) e.getSource();
        if(cs.isClosed())
            cs.open();
        else
            cs.close();
    }
    
}
