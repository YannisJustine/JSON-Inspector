package fr.iutfbleau.SAE32_2022.model;

import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Classe abstraite représentant une valeur JSON.
 * 
 * Une valeur JSON peut être de différents types:
 * <ul>
 *   <li>un objet ({@link JsonObject});</li>
 *   <li>un tableau ({@link JsonArray});</li>
 *   <li>un nombre ({@link JsonNumber});</li>
 *   <li>une chaîne de caractères ({@link JsonString});</li>
 *   <li>null ({@link JsonBooleanLiteral});</li>
 *   <li>true ({@link JsonBooleanLiteral});</li>
 *   <li>false ({@link JsonBooleanLiteral}).</li>
 * </ul>
 */
public abstract class JsonValue {

  /**
   * Écrit cette valeur JSON dans un {@code StringBuilder}.
   * 
   * @param sb le {@code StringBuilder} dans lequel écrire cette valeur JSON
   * @param spaces le nombre d'espaces à ajouter avant chaque ligne d'écriture
   */
    public void buildString  (StringBuilder sb, int spaces) {
        
    }

    /**
     * Renvoie {@code true} si et seulement si cette valeur JSON est un objet ({@code JsonObject}).
     * 
     * @return {@code true} si cette valeur JSON est un objet, {@code false} sinon
     */
    public boolean isObject() {
        return false;
    }
    
    /**
     * Renvoie {@code true} si et seulement si cette valeur JSON est un tableau ({@code JsonArray}).
     * 
     * @return {@code true} si cette valeur JSON est un tableau, {@code false} sinon
     */
    public boolean isArray() {
        return false;
    }

    /**
     * Renvoie {@code true} si et seulement si cette valeur JSON est un nombre ({@code JsonNumber}).
     * 
     * @return {@code true} si cette valeur JSON est un nombre, {@code false} sinon
     **/
    public boolean isNumber() {
        return false;
    }

    /**
     * Méthode permettant de savoir si la valeur JSON est une chaîne de caractères.
     * 
     * @return true si la valeur JSON est une chaîne de caractères, false sinon
     */
    public boolean isString() {
        return false;
    }

    /**
     * Méthode permettant de savoir si la valeur JSON est la valeur null.
     * 
     * @return true si la valeur JSON est la valeur null, false sinon
     */
    public boolean isNull() {
        return false;
    }   

    /**
     * Méthode permettant de savoir si la valeur JSON est la valeur true.
     * 
     * @return true si la valeur JSON est la valeur true, false sinon
     */
    public boolean isTrue() {
        return false;
    }

    /**
     * Méthode permettant de savoir si la valeur JSON est la valeur false.
     * 
     * @return true si la valeur JSON est la valeur false, false sinon
     */
    public boolean isFalse() {
        return false;
    }
        
    /**
     * Retourne l'ensemble des entrées (clé-valeur) de cet objet JSON s'il s'agit d'un objet, ou lance une exception sinon.
     * @return l'ensemble des entrées de cet objet JSON
     * @throws UnsupportedOperationException si cette valeur JSON n'est pas un objet
     */
    public Set<Entry<String, JsonValue>> getEntry() {
        throw new UnsupportedOperationException("This isn't an object");
    }

    /**
     * Retourne la liste des valeurs de cet objet JSON s'il s'agit d'un tableau, ou lance une exception sinon.
     * @return la liste des valeurs de cet objet JSON
     * @throws UnsupportedOperationException si cette valeur JSON n'est pas un tableau
     */
    public List<JsonValue> getList() {
        throw new UnsupportedOperationException("This isn't an array");
    }

    /**
     * Renvoie la valeur de l'objet sous forme d'entier.
     *
     * @return la valeur de l'objet sous forme d'entier
     * @throws UnsupportedOperationException si l'objet n'est pas un entier
     */
    public int asInteger() {
        throw new UnsupportedOperationException("This isn't a integer: ");
    }
    /**
    * Tentative de récupérer l'objet sous forme de nombre à virgule flottante double précision.
    * @return le nombre à virgule flottante double précision représentant l'objet
    * @throws UnsupportedOperationException si l'objet n'est pas un nombre à virgule flottante double précision
    */
    public double asDouble() {
        throw new UnsupportedOperationException("This isn't a double: ");
    }

    /**
    * Tentative de récupérer l'objet sous forme de nombre à virgule flottante simple précision.
    * @return  le nombre à virgule flottante simple précision représentant l'objet
    * @throws UnsupportedOperationException si l'objet n'est pas un nombre à virgule flottante
    */
    public float asFloat() {
        throw new UnsupportedOperationException("This isn't a float: ");
    }

    /**
    * Tentative de récupérer l'objet sous forme de chaîne de caractères. 
    * @return la chaîne de caractères représentant l'objet
    * @throws UnsupportedOperationException si l'objet n'est pas une chaîne de caractères
    */
    public String asString() {
        throw new UnsupportedOperationException("This isn't a String: ");
    }

    /**
    * Tentative de récupérer l'objet sous forme de booléen
    * @return le boolean représentant l'objet
    * @throws UnsupportedOperationException si l'objet n'est pas un boolean
    */
    public boolean asBoolean() {
        throw new UnsupportedOperationException("This isn't a boolean: ");
    }

}