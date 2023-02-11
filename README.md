# Projet BUT2
## Inspecteur de JSON
Ce programme permet de visualiser un fichier JSON de manière lisible (prettyPrint)

## Développement 

Ce projet a été réalisé par :
- [Yannis JUSTINE](https://github.com/YannisJustine)
- [Lucas JUSTINE](https://github.com/LucasJustine)

## Installation

L'inspecteur de JSON a besoin de [Java](https://www.oracle.com/java/technologies/downloads/) v8+ pour être lancé.

Importez le dépôt sur votre ordinateur :
```sh
git clone https://dwarves.iut-fbleau.fr/gitiut/justiney/SAE32_2022.git
````

Pour compiler le programme :
```sh
make
```

Pour lancer le programme (Interface graphique) :
```sh
make run
```

## Fonctionnalités

Ce programme à deux fonctionnalités principales : 
- Lancé sans argument une fenêtre graphique s'ouvrira
- Lancé avec un argument, le fichier JSON sera affiché formaté dans la console

```sh
java -jar Inspecteur.jar (URL_OF_FILE)
```

Vous pouvez modifier les couleurs de la partie graphique ainsi que la taille par défaut de la fenêtre dans le fichier config.json.
```json
{
    "frame" : {
    	"width" : 500,
	"height" : 500,
        "minheight" : 0,
        "minheight" : 0
    },
    "font" : {
        "size" : 20,
        "name" : "Consolas"
    },
    "colors" : {
        "key" : "#89ddff",
        "number" : "#c3e88d",
        "string" : "#f78c6c",
        "separator" : "#c792ea",
        "value" : "#2e95d3",
        "background" : "#292d3e"
    },
    "spaces" : 2
}
```

## Commandes supplémentaires

Créer le dossier pour la javadoc
```sh
make doc
```

Supprimer les .class
```sh
make clean
```

Supprimer la documentation
```sh
make clean_doc
```

Supprimer l'archive
```sh
make clean_jar
```
