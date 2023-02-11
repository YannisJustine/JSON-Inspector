package fr.iutfbleau.SAE32_2022.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import fr.iutfbleau.SAE32_2022.Constants;

/**
 * Classe représentant un objet JSON.
 * Un objet JSON est une collection de paires clé-valeur, où la clé est une chaîne de caractères et la valeur est un objet {@link JsonValue}.
 *
 */
public class JsonObject extends JsonValue {
    /** Le dictionnaire contenant les paires clé-valeur de l'objet JSON. */
    private Map<String, JsonValue> map;

    /**
     * Constructeur de la classe.
     * Crée une nouvelle instance d'un objet JSON vide.
     */
    public JsonObject() {
        this.map = new LinkedHashMap<String, JsonValue>();
    }
    
     /**
     * Ajoute une paire clé-valeur à l'objet JSON.
     *
     * @param key la clé de la paire.
     * @param value la valeur de la paire.
     * @return la valeur précédemment associée à la clé, ou null si aucune valeur n'était associée à la clé.
     */
    public JsonValue add(String key, JsonValue value) {
        return this.map.put(key, value);
    }

    /**
     * Supprime une paire clé-valeur de l'objet JSON.
     *
     * @param key la clé de la paire à supprimer.
     * @return la valeur associée à la clé avant suppression, ou null si aucune valeur n'était associée à la clé.
     */
    public JsonValue remove(String key) {
        return this.map.remove(key);
    }

     /**
     * Récupère la valeur associée à la clé dans l'objet JSON.
     *
     * @param key la clé de la valeur à récupérer.
     * @return la valeur associée à la clé, ou null si aucune valeur n'est associée à la clé.
     */
    public JsonValue getJsonValue(String key) {
        return this.map.get(key);
    }

    @Override
    public boolean isObject() {
        return true;
    }

    @Override
    public Set<Entry<String, JsonValue>> getEntry() {
        return this.map.entrySet();
    }

    @Override
    public void buildString (StringBuilder sb, int spaces) {
        sb.append("{\n");
        boolean first = true;
        for(Entry<String, JsonValue> entry : map.entrySet()){
            if(first)
                first = false;
            else
                sb.append(",\n");
            for(int i = 0; i < spaces + Constants.SPACES; i++)
                sb.append(" ");
            sb.append("\"" + entry.getKey() + "\": ");
            entry.getValue().buildString (sb,spaces + Constants.SPACES); 
        }
        sb.append("\n");
        for(int j = 0; j < spaces; j++)
            sb.append(" ");
        sb.append("}");
    }

}
