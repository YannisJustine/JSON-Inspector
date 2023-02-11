package fr.iutfbleau.SAE32_2022.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Cette classe représente un parseur de fichiers JSON.
 * Elle permet de lire un fichier JSON à partir d'une URL et de le convertir
 * en un objet de la classe {@link JsonValue}.
 */
public class Parser {
    
    /** Index global dans la chaîne de caractères JSON. */
    private int globalIndex;
    /** Index dans la ligne courante de la chaîne de caractères JSON. */
    private int index;
    /** Numéro de ligne dans la chaîne de caractères JSON. */
    private int line;
    /** Chaîne de caractères JSON à parser. */
    private String string;
    /** Caractère courant lu par le parseur. */
    private char currentChar;
    /** Est ce que le parser est en train de lire une chaine de caractère ? */
    private boolean readingString = false;

    /**
     * Constructeur par défaut.
     */
    public Parser(){
        
    }

    /**
     * Lit le contenu d'un fichier JSON à partir d'une URL et le retourne sous forme de chaîne de caractères.
     * @param url L'URL du fichier JSON à lire.
     * @return La chaîne de caractères contenant le contenu du fichier JSON.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture du fichier.
     * @throws NullPointerException Si la chaine de caractère est null
     */
    public static String read (String url) throws IOException, NullPointerException{
        Objects.requireNonNull(url);
        InputStream stream = new java.net.URL(url).openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append('\n');
        }
        reader.close();
        return sb.toString();

    }

    /**
     * Parse une chaîne de caractères JSON et retourne l'objet {@link JsonValue} correspondant.
     * @param jsonText La chaîne de caractères JSON à parser.
     * @return L'objet {@link JsonValue} correspondant à la chaîne de caractères JSON.
     */
    public JsonValue parse(String jsonText) {
        this.string = jsonText;
        this.globalIndex = 0;
        this.index = 0;
        this.line = 1;
        read();
        readBlank();
        JsonValue value = readJsonValue();
        readBlank();
        if(currentChar != (char)-1)
            throw new IllegalArgumentException("Expected EOF but get : " + currentChar );
        this.string = null;
        return value;
    }  

    /**
     * Parse une chaîne de caractères JSON et retourne l'objet {@link JsonValue} correspondant.
     *
     * @return L'objet {@link JsonValue} correspondant à la chaîne de caractères JSON.
     * @throws IllegalArgumentException Si la chaîne de caractères ne correspond pas à une valeur JSON valide.
     */
    private JsonValue readJsonValue(){
        JsonValue value = null;
        switch (currentChar) {
            case '"':
                value = this.readString();
                break;
            case '{':
                value = this.readObject();
                break;
            case '[':  
                value = this.readArray();
                break;
            case '-':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                value = this.readNumber();
                break;
            case 'f':
                value = this.readFalse();
                break;
            case 't':
                value = this.readTrue();
                break;
            case 'n':
                value = this.readNull();
                break;
            default :
                throw new IllegalArgumentException("Expected a value at index " + index + " line " + line);
        }
        return value;
    }

    /**
     * Lit une chaîne de caractères JSON et retourne l'objet {@link JsonString} correspondant.
     * @return L'objet {@link JsonString} correspondant à la chaîne de caractères JSON lue.
     */
    private JsonString readString(){
        readingString = true;
        read();
        StringBuilder word = new StringBuilder();
        while(currentChar != '"'){
            if( currentChar < 32 )
                throw new IllegalArgumentException("Invalid character at index " + index + " (" + currentChar + ")" + " line " + line);
            if( currentChar == (char)-1 )
                throw new IllegalArgumentException("Expected character but EOF");
            if(currentChar == '\\'){
                read();
                readEscape();
            }
            word.append(currentChar);
            read();
        }
        readingString = false;
        read();
        return new JsonString(word.toString());
    }

    /**
     * Lit une échappement dans une chaîne de caractères JSON et convertit le caractère échappé en son équivalent.
     * Cette fonction est utilisée lors de la lecture de chaînes de caractères JSON avec la fonction {@link #readString()}.
     */
    private void readEscape() {
        switch (currentChar) {
            case '"':
                currentChar = '\"';
                break;
            case 't':
                currentChar = '\t';
                break;
            case '\\':
                currentChar = '\\';
                break;
            case '/':
                currentChar = '/';
                break;
            case 'b':
                currentChar = '\b';
                break;
            case 'f':
                currentChar = '\f';
                break;
            case 'n':
                currentChar = '\n';
                break;
            case 'r':
                currentChar = '\r';
                break;
            case 'u':
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < 4; i ++){
                    read();
                    if(!(currentChar >= '0' && currentChar <= '9' || currentChar >= 'a' && currentChar <= 'f' || currentChar >= 'A' && currentChar <= 'F'))
                        throw new IllegalArgumentException("Expected Unicode character at index " + index + " line " + line);
                    sb.append(currentChar);
                }
                currentChar = (char)Integer.parseInt(sb.toString(), 16);
                break;
        } 
    }

    /**
     * Lit un objet JSON et retourne l'objet {@link JsonObject} correspondant.
     * @return L'objet {@link JsonObject} correspondant à l'objet JSON lu.
     */
    private JsonObject readObject(){
        JsonObject object = new JsonObject();
        read();
        readBlank();
        if(readChar('}')){
            return object;
        }
        do{
            readBlank();
            String key = readKey();
            readBlank();
            if(!readChar(':'))
                throw new IllegalArgumentException("Expected : at index " + index + " line " + line);
            readBlank();
            JsonValue value = readJsonValue();
            object.add(key, value);
        } while(readChar(','));
        readBlank();
        if(!readChar('}'))
            throw new IllegalArgumentException("Expected } or ',' at index " + index + " (" + currentChar + ")" + " line " + line);
        return object;
    }  
    
    /**
    * Lit un tableau JSON et retourne l'objet {@link JsonArray} correspondant.
    * @return L'objet {@link JsonArray} correspondant au tableau JSON lu.
    */
    private JsonArray readArray(){
        JsonArray array = new JsonArray();
        read();
        readBlank();
        if(readChar(']')){
            return array;
        }
        do{
            readBlank();
            array.add(readJsonValue());
            readBlank();
        } while(readChar(','));
        readBlank();
        if(!readChar(']'))
            throw new IllegalArgumentException("Expected ] or ',' at index " + index + " (" + currentChar + ")" + " line " + line);
        return array;
    }

    /**
     * Lit un nombre JSON et retourne l'objet {@link JsonNumber} correspondant.
     * @return L'objet {@link JsonNumber} correspondant au nombre JSON lu.
     */
    private JsonNumber readNumber() {
        JsonNumber number = null;
        StringBuilder sb = new StringBuilder();
        //On lit le caractère
        if(readChar('-'))
            sb.append('-');
        //On regarde si le caractère est un nombre
        if(!(currentChar >= '0' && currentChar <= '9'))
            throw new IllegalArgumentException("Expected digit at index " + index + " (" + currentChar + ")" + " line " + line);
        //Si le nombre ne commence par 0
        if(!readChar('0')) {
            while(currentChar >= '0' && currentChar <= '9') {
                sb.append(currentChar);
                read();
            }
        }
        else
            sb.append('0');

        readFraction(sb);
        readExponent(sb);
        number = new JsonNumber(sb.toString());
        return number;
    }

    /**
     * Lit la partie fractionnaire d'un nombre JSON et l'ajoute à la chaîne de caractères passée en paramètre.
     * Cette fonction est utilisée lors de la lecture de nombres JSON avec la fonction {@link #readNumber()}.
     * @param sb La chaîne de caractères à laquelle ajouter la partie fractionnaire du nombre JSON.
     */
    private void readFraction(StringBuilder sb) {
        if(!readChar('.')) {
            return;
        }
            
        sb.append('.');
        if(!(currentChar >= '0' && currentChar <= '9'))
            throw new IllegalArgumentException("Expected digit at index " + index + " (" + currentChar + ")" + " line " + line);
        while(currentChar >= '0' && currentChar <= '9') {
            sb.append(currentChar);
            read();
        }
    }

    /**
     * Lit la partie exponentielle d'un nombre JSON et l'ajoute à la chaîne de caractères passée en paramètre.
     * Cette fonction est utilisée lors de la lecture de nombres JSON avec la fonction {@link #readNumber()}.
     * @param sb La chaîne de caractères à laquelle ajouter la partie exponentielle du nombre JSON.
     */
    private void readExponent(StringBuilder sb) {
        if(!(currentChar == 'e' || currentChar == 'E')) {
            return;
        }
        sb.append(currentChar);
        read();

        if(currentChar == '+' || currentChar == '-') {
            sb.append(currentChar);
            read();
        }
        if(!(currentChar >= '0' && currentChar <= '9'))
            throw new IllegalArgumentException("Expected digit at index " + index + " line " + line);
        while(currentChar >= '0' && currentChar <= '9') {
            sb.append(currentChar);
            read();
        }
    } 
    
    /**
     * Lit la valeur booléenne "false" et retourne l'objet {@link JsonBooleanLiteral} correspondant.
     * @return L'objet {@link JsonBooleanLiteral} correspondant à la valeur "false".
     */

    private JsonBooleanLiteral readFalse() {
        if( !(readChar('f') && 
            readChar('a') &&
            readChar('l') &&
            readChar('s') &&
            readChar('e')))
            throw new IllegalArgumentException("Expected false at index " + index + " line " + line);
        return JsonBooleanLiteral.FALSE;
    }

    /**
     * Lit la valeur booléenne "true" et retourne l'objet {@link JsonBoolean} correspondant.
     * @return L'objet {@link JsonBooleanLiteral} correspondant à la valeur "true".
     */
    private JsonBooleanLiteral readTrue() {
        if(!(readChar('t') && 
            readChar('r') &&
            readChar('u') &&
            readChar('e')))
            throw new IllegalArgumentException("Expected true at index " + index + " line " + line);
        return JsonBooleanLiteral.TRUE;
    }

    /**
     * Lit la valeur "null" et retourne l'objet {@link JsonBooleanLiteral} correspondant.
     * @return L'objet {@link JsonBooleanLiteral} correspondant à la valeur "null".
     */
    private JsonBooleanLiteral readNull() {
        if(!(readChar('n') && 
            readChar('u') &&
            readChar('l') &&
            readChar('l')))
            throw new IllegalArgumentException("Expected null at index " + index + " line " + line );
        return JsonBooleanLiteral.NULL;
    }

    /**
     * Lit les caractères vides suivants dans la chaîne de caractères JSON.
     */
    private void readBlank(){
        while(Character.isWhitespace(currentChar)){
            read();
        }  
    }

    /**
     * Lit une clé d'un objet JSON et retourne sa valeur sous forme de chaîne de caractères.
     * Cette fonction est utilisée lors de la lecture d'objets JSON avec la fonction {@link #readObject()}.
     * @return La valeur de la clé de l'objet JSON sous forme de chaîne de caractères.
     */
    private String readKey(){
        if(currentChar != '"')
            throw new IllegalArgumentException("Expected \" at index " + index + " line " + line);
        return readString().asString();
    } 

    /**
     * Lit un caractère et vérifie qu'il correspond au caractère passé en paramètre.
     * @param c Le caractère attendu.
     * @return {@code true} si le caractère lu correspond au caractère attendu, {@code false} sinon.
     */
    private boolean readChar(char c) {
        if(currentChar != c)
            return false;
        read();
        return true;
    }


    /**
     * Lit le prochain caractère dans la chaîne de caractères JSON et l'assigne à {@link #currentChar}.
     */
    private void read(){
        if(globalIndex < string.length()) {
            if(this.currentChar == '\n' && !readingString) {
                this.line++;
                this.index = 0;
            }
            this.currentChar = string.charAt(globalIndex++);
            this.index++;
        }
        else
            this.currentChar = (char)-1;
    }
      
}

