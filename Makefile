# COMMANDES #
JAVAC = javac
# note $$ to get a single shell $
JAVAC_OPTIONS = -encoding UTF8 -d build -cp build -implicit:none -sourcepath src
JAVA = java
EXEC_JAR = ${JAVA} -jar

# CHEMINS RELATIFS
SRC = src/fr/iutfbleau/SAE32_2022
BUILD = build/fr/iutfbleau/SAE32_2022

# CHOIX NOMS
JAR = Inspecteur.jar

# BUTS FACTICES #
.PHONY : run clean doc doc_clean dir

# BUT PAR DEFAUT #
${JAR} : build ${BUILD}/Main.class 
	jar -cvfe ${JAR} fr.iutfbleau.SAE32_2022.Main -C build fr

run : ${JAR} 
	java -jar ${JAR} 

doc : 
	javadoc -d ./doc -sourcepath ./src -subpackages fr -noqualifier java.lang

clean_jar :
	rm ${JAR}

clean : 
	rm -R build/*

clean_doc :
	 rm -rf doc/*

build :
	mkdir build

# Modèle #

${BUILD}/model/JsonValue.class : ${SRC}/model/JsonValue.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/model/JsonObject.class : ${SRC}/model/JsonObject.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/model/JsonArray.class : ${SRC}/model/JsonArray.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/model/JsonNumber.class : ${SRC}/model/JsonNumber.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/model/JsonBooleanLiteral.class : ${SRC}/model/JsonBooleanLiteral.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/model/JsonString.class : ${SRC}/model/JsonString.java
	${JAVAC} ${JAVAC_OPTIONS} $<


${BUILD}/model/Parser.class : ${SRC}/model/Parser.java \
							  ${BUILD}/model/JsonValue.class \
							  ${BUILD}/model/JsonObject.class \
							  ${BUILD}/model/JsonArray.class \
							  ${BUILD}/model/JsonNumber.class \
							  ${BUILD}/model/JsonBooleanLiteral.class \
							  ${BUILD}/model/JsonString.class \

	${JAVAC} ${JAVAC_OPTIONS} $<


# Vue #

${BUILD}/view/Composant/Chaine.class : ${SRC}/view/Composant/Chaine.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/view/FlowPanel.class : ${SRC}/view/FlowPanel.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/view/Composant/Nom.class : ${SRC}/view/Composant/Nom.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/view/Separator/Separator.class : ${SRC}/view/Separator/Separator.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/view/Separator/CloseableSeparator.class : ${SRC}/view/Separator/CloseableSeparator.java \
													${BUILD}/view/Separator/Separator.class \
													${BUILD}/controller/CloseableListener.class
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/view/Composant/Number.class : ${SRC}/view/Composant/Number.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/view/Composant/Value.class : ${SRC}/view/Composant/Value.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/view/Composant/Spaces.class : ${SRC}/view/Composant/Spaces.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/view/WrapLayout.class : ${SRC}/view/WrapLayout.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/view/Layout.class: ${SRC}/view/Layout.java \
							${BUILD}/Constants.class \
							${BUILD}/view/Composant/Chaine.class \
							${BUILD}/view/Composant/Nom.class \
							${BUILD}/view/Separator/CloseableSeparator.class  \
							${BUILD}/view/Composant/Value.class \
							${BUILD}/view/Composant/Number.class \
							${BUILD}/model/JsonValue.class \
							${BUILD}/view/Composant/Spaces.class \
							${BUILD}/view/FlowPanel.class \
							${BUILD}/view/WrapLayout.class
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/view/Graphique.class : ${SRC}/view/Graphique.java \
								${BUILD}/view/Layout.class \
								${BUILD}/controller/RefreshListener.class \
								${BUILD}/controller/OpenListener.class \
								${BUILD}/controller/PhpParser.class \
								${BUILD}/controller/JSONChooser.class
	${JAVAC} ${JAVAC_OPTIONS} $<	

# Controlleur #

${BUILD}/controller/RefreshListener.class : ${SRC}/controller/RefreshListener.java \
											${BUILD}/model/Parser.class
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/controller/CloseableListener.class : ${SRC}/controller/CloseableListener.java 
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/controller/OpenListener.class : ${SRC}/controller/OpenListener.java 
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/controller/PhpParser.class : ${SRC}/controller/PhpParser.java 
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/controller/JSONChooser.class : ${SRC}/controller/JSONChooser.java 
	${JAVAC} ${JAVAC_OPTIONS} $<
# Main #

${BUILD}/Main.class : ${SRC}/Main.java \
						${BUILD}/view/Graphique.class \
						${BUILD}/model/Parser.class 
						
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/Constants.class : ${SRC}/Constants.java
	${JAVAC} ${JAVAC_OPTIONS} $<
