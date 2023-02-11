package fr.iutfbleau.SAE32_2022.model;

/**
 * Classe représentant une valeur booléenne (true, false ou null) dans un objet JSON.
 *
 */
public class JsonBooleanLiteral extends JsonValue {
    /** La valeur booléenne sous forme de chaîne de caractères. */
    private String value;
    /** Représentation de la valeur booléenne null. */
    public static JsonBooleanLiteral NULL = new JsonBooleanLiteral("null");
    /** Représentation de la valeur booléenne true. */
    public static JsonBooleanLiteral TRUE = new JsonBooleanLiteral("true");
    /** Représentation de la valeur booléenne false. */
    public static JsonBooleanLiteral FALSE = new JsonBooleanLiteral("false");

    /**
     * Constructeur privé de la classe.
     * Le constructeur est privé pour empêcher la création de nouvelles instances de cet objet.
     *
     * @param value la valeur booléenne sous forme de chaîne de caractères.
     */
    private JsonBooleanLiteral(String value) {
        this.value = value;
    }

    @Override
    public boolean isNull() {
        return "null" == value;
    }

    @Override
    public boolean isTrue() {
        return "true" == value;
    }

    @Override
    public boolean isFalse() {
        return "false" == value;
    }

    @Override
    public boolean asBoolean() {
        return isNull() ? false : isTrue();
    }

    @Override
    public String asString() {
        return value;
    }

    @Override
    public void buildString (StringBuilder sb, int spaces) {
        sb.append(value);
    }
}
