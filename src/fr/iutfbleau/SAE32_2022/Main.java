package fr.iutfbleau.SAE32_2022;

import java.io.IOException;

import javax.swing.UIManager;

import fr.iutfbleau.SAE32_2022.model.JsonValue;
import fr.iutfbleau.SAE32_2022.model.Parser;
import fr.iutfbleau.SAE32_2022.view.Graphique;

/**
 * Classe principale de l'application.
 * Elle permet de lire et de parser un fichier JSON à partir de son URL passée en argument
 * et d'afficher sa représentation en utilisant la fonction {@link #prettyPrint(JsonValue)}.
 */
public class Main {

    /**
     * Fonction principale de l'application.
     * @param args L'URL du fichier JSON à lire et parser.
     */
    public static void main(String[] args) {

        if(args.length < 1){
            try {
                if(UIManager.getSystemLookAndFeelClassName() == "javax.swing.plaf.metal.MetalLookAndFeel") 
                   UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                else 
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Constants.loadConfig();
            Graphique vue = new Graphique();
            vue.setLocationRelativeTo(null);
            vue.setVisible(true);
        }
            
        else {
            Parser o = new Parser();
            try {
                JsonValue value = o.parse(Parser.read(args[0]));
                System.out.println(prettyPrint(value));
            } catch(IOException e) {
                try {
                    JsonValue value = o.parse(Parser.read("file:" + args[0]));
                    System.out.println(prettyPrint(value));
                } catch (IOException e1) {
                    System.err.println(e.getMessage());
                }
                catch(Exception ex) {
                    System.err.println(ex.getMessage());
                }
            } catch(Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        
    }
    
    /**
     * Retourne une chaîne de caractères contenant la représentation "jolie" de l'objet {@link JsonValue} passé en paramètre.
     * Cette fonction est utilisée pour afficher de manière lisible le contenu d'un fichier JSON lu et parsé.
     * @param value L'objet {@link JsonValue} à afficher de manière lisible.
     * @return La représentation "jolie" de l'objet {@link JsonValue}.
     */
    public static String prettyPrint(JsonValue value){
        StringBuilder sb = new StringBuilder();
        value.buildString (sb,0);
        return sb.toString();
    }
}
