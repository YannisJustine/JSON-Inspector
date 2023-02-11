package fr.iutfbleau.SAE32_2022.model;

/**
 * Classe représentant un nombre dans un objet JSON.
 * Un nombre peut être stocké sous forme de chaîne de caractères et converti en différents types de nombres : double, float ou int.
 *
 */
public class JsonNumber extends JsonValue {
    /** La valeur du nombre sous forme de chaîne de caractères. */
    private String string;

    /**
     * Constructeur de la classe.
     * Crée une nouvelle instance d'un objet de type JsonNumber avec la valeur du nombre spécifiée en paramètre.
     *
     * @param string la valeur du nombre sous forme de chaîne de caractères.
     * @throws NullPointerException si la chaîne de caractères est null.
     */
    public JsonNumber(String string) {
      if (string == null) {
        throw new NullPointerException("string is null");
      }
      this.string = string;
    }

    @Override
		public String asString() {
			return string;
		}

    @Override
    public boolean isNumber() {
      return true;
    }

    @Override
    public double asDouble() {
    	return Double.parseDouble(string);
    }

    @Override
    public float asFloat() {
    	return Float.parseFloat(string);
    }

    @Override
    public int asInteger() {
    	return Integer.parseInt(string);
    }

    @Override
    public void buildString (StringBuilder sb, int spaces) {
        sb.append(string);
    }

}
