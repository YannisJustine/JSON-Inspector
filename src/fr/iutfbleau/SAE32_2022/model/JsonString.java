package fr.iutfbleau.SAE32_2022.model;

/**
 * Classe représentant une chaîne de caractères dans un objet JSON.
 *
 */
public class JsonString extends JsonValue{

    /** La valeur de la chaîne de caractères. */
    private String string;

    /**
     * Constructeur de la classe.
     * Crée une nouvelle instance d'un objet de type JsonString avec la valeur de la chaîne de caractères spécifiée en paramètre.
     *
     * @param string la valeur de la chaîne de caractères.
     */
    public JsonString(String string) {
        this.string = string;
    }

    @Override
    public String asString() {
      return string;
    }

    @Override
    public boolean isString() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void buildString  (StringBuilder sb, int spaces) {
        sb.append("\""+string+"\"");
    }
    
}
