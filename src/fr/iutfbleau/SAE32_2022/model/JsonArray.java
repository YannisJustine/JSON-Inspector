package fr.iutfbleau.SAE32_2022.model;

import java.util.ArrayList;
import java.util.List;

import fr.iutfbleau.SAE32_2022.Constants;

/**
 * Classe représentant un tableau JSON.
 * Un tableau JSON est une liste d'objets JSON.
 *
 */
public class JsonArray extends JsonValue{

    private ArrayList<JsonValue> array;

    /**
     * Constructeur par défaut.
     * Initialise la liste d'objets JSON.
     */
    public JsonArray() {
        this.array = new ArrayList<JsonValue>();
    }

    /**
     * Ajoute un objet JSON à la fin de la liste.
     *
     * @param value l'objet JSON à ajouter à la liste
     * @return true si l'objet a été ajouté, false sinon
     */
    public boolean add(JsonValue value) {
        return this.array.add(value);
        
    }

    /**
     * Supprime l'objet JSON situé à l'index spécifié de la liste.
     *
     * @param index l'index de l'objet JSON à supprimer
     * @return l'objet JSON supprimé
     */
    public JsonValue remove(int index) {
        return this.array.remove(index);
    }
    
    /**
     * Renvoie l'objet JSON situé à l'index spécifié de la liste.
     *
     * @param index l'index de l'objet JSON à renvoyer
     * @return l'objet JSON situé à l'index spécifié
     */
    public JsonValue getValueAt(int index) {
        return this.array.get(index);
    }

    @Override
    public boolean isArray() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public List<JsonValue> getList() {
        return this.array;
    }

    @Override
    public void buildString (StringBuilder sb, int spaces) {
        sb.append("[\n");
        boolean first = true;
        for(JsonValue value : array){
            if(first)
                first = false;
            else
                sb.append(",\n");
            for(int j = 0; j < spaces + Constants.SPACES; j++)
                sb.append(" ");
            value.buildString (sb, spaces + Constants.SPACES);
        }
        sb.append("\n");
        for(int j = 0; j < spaces; j++)
                sb.append(" ");
        sb.append("]");
    }
}
