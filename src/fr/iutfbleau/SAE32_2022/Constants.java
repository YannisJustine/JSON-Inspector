package fr.iutfbleau.SAE32_2022;

import java.awt.Color;
import java.awt.Font;

import fr.iutfbleau.SAE32_2022.model.JsonObject;
import fr.iutfbleau.SAE32_2022.model.Parser;

/**
 * Classe contenant les constantes de couleurs utilisées dans l'application.
 * Ces constantes sont utilisées pour colorer les différents éléments de l'interface graphique.
*/
public class Constants {

    /** Couleur utilisée pour les noms de propriétés dans les objets JSON. */
    public static Color NOM = Color.decode("#89ddff");
    /** Couleur utilisée pour les nombres dans les objets JSON. */
    public static Color NUMBER = Color.decode("#c3e88d");
    /** Couleur utilisée pour les chaînes de caractères dans les objets JSON. */
    public static Color CHAINE = Color.decode("#f78c6c");
    /** Couleur utilisée pour les séparateurs ("," et ":", "{}", "[]") dans les objets JSON. */
    public static Color SEPARATOR = Color.decode("#c792ea");
    /** Couleur utilisée pour les valeurs (true, false, null) dans les objets JSON. */
    public static Color VALUE = Color.decode("#2e95d3");
    /** Couleur de fond de l'interface graphique. */
    public static Color BACK = Color.decode("#292d3e");
    /** Nombre d'espaces après un saut de ligne dans un objet ou tableau */
    public static int SPACES = 2;
    /** Police de caractère des éléments JSON */
    public static Font FONT = new Font("Consolas",Font.PLAIN,18);
    /** Largeur par défaut de la fenêtre */
    public static int WIDTH = 500;
    /** Hauteur par défaut de la fenêtre */
    public static int HEIGHT = 500;
    /** Largeur par défaut de la fenêtre */
    public static int MINWIDTH = 500;
    /** Hauteur par défaut de la fenêtre */
    public static int MINHEIGHT = 500;

    public static void loadConfig() {
        try {
            Parser p = new Parser();
            JsonObject values = (JsonObject) p.parse(Parser.read("file:config.json"));
            JsonObject font = (JsonObject) values.getJsonValue("font");
            int size = font.getJsonValue("size").asInteger();
            String fontName = font.getJsonValue("name").asString();

            JsonObject colors = (JsonObject) values.getJsonValue("colors");
            String key = colors.getJsonValue("key").asString();
            String number = colors.getJsonValue("number").asString();
            String string = colors.getJsonValue("string").asString();
            String separator = colors.getJsonValue("separator").asString();
            String value = colors.getJsonValue("value").asString();
            String background = colors.getJsonValue("background").asString();

            int spaces = values.getJsonValue("spaces").asInteger();

            JsonObject frame = (JsonObject) values.getJsonValue("frame");
            /*Au cas où la taille est donnée sous format autre que int */
            int width = (int) frame.getJsonValue("width").asFloat();
            int height = (int) frame.getJsonValue("height").asFloat();
            int minwidth = (int) frame.getJsonValue("min-width").asFloat();
            int minheight = (int) frame.getJsonValue("min-height").asFloat();


            Constants.NOM = Color.decode(key);
            Constants.NUMBER = Color.decode(number);
            Constants.CHAINE = Color.decode(string);
            Constants.SEPARATOR = Color.decode(separator);
            Constants.VALUE = Color.decode(value);
            Constants.BACK = Color.decode(background);
            Constants.SPACES = spaces;
            Constants.FONT = new Font(fontName, Font.PLAIN, size);
            Constants.WIDTH = width;
            Constants.HEIGHT = height;
            Constants.MINWIDTH = minwidth;
            Constants.MINHEIGHT = minheight;

        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

}
