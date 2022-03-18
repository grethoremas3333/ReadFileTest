package com.experimentation.test;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class ReadFilev010 {

    /**
     *
     * @param déclarations des attributs myliste qui est une liste
     * qui va contenir toutes les donéées du fichier symptoms.txt
     * lu ligne âr ligne. && la variable sumptomes qui
     * est un ensemble (Set) qui va contenir uniquement les symptomes
     */
    static List<String> myliste=new ArrayList<String>(); //la collection (Liste ordonnée) qui va contenir toutes les donéées du fichier symptoms.txt
    static Set<String> symptomes = new HashSet<String>(); //la collection ensemble (SET) qui va contenir uniquement les symptomes


    public static void main(String[] args) throws Exception {

        System.out.println("Version:v0.1.0"  +"\n"+ "Initialisation du Programme...");

        insertionCollections(); //fonction/méthode qui lit un fichier en entrée et insert les données dans deux collections : List(pour avoir toutes les lignes du fichier) && Set(pour avoir la liste des symptomes distincts sans doublons)

        String tableauSymptoms[] = insertionTableau(symptomes); //fonction qui retourne sous forme de tableau classé en ordre Alphabétique les symptomes extrait de la collection Set

        parcoursSymptoms(tableauSymptoms); //fonction qui parcours le tableau des symptomes un-à-un et compte le nombre d'occurence à partir de la liste des symptomes

        System.out.println("...Fin du Programme !!!");
    }

    /**
     *
     * @param fonction/méthode qui lit un fichier en entrée et
     * insert les données dans deux collections :
     * List(pour avoir toutes les lignes du fichier) &&
     *  Set(pour avoir la liste des symptomes distincts sans doublons)
     */
    public static void insertionCollections() throws Exception{

        BufferedReader reader = new BufferedReader (new FileReader("symptoms.txt")); //lecture du fichier symptoms.txt
        String line = reader.readLine(); //insertion du contenu du fichier symptoms.txt dans la variable line

        /* *Remplir la liste myliste et la collection Set avec les éléments du fichier* */

        while (line != null) {
            myliste.add(line);
            symptomes.add(line); //pas de doublons possible avec la collection SET
            line = reader.readLine();
        }
        reader.close();
    }

    /**
     *
     * @param fonction qui retourne sous forme
     * de tableau classé en ordre Alphabétique les
     *  symptomes extrait de la collection Set
     */
    public static String[] insertionTableau(Set<String> symptomes) {

        int nombreSymptoms = symptomes.size();

        /* *On va créer un tableau de String qui va contenir uniquement les symptomes ça nous sera utile pour la suite* */

        String tableauSymptoms[] = new String [nombreSymptoms];

        int compteur = 0;
        for(String symptome : symptomes) {
            tableauSymptoms[compteur] = symptome;
            compteur++;
        }
        /* *On classe en ordre Alphabétique les éléments du tableau* */
        Arrays.sort(tableauSymptoms);
        return tableauSymptoms;
    }

    /**
     *
     * @param fonction qui parcours le tableau des symptomes
     *  un-à-un et compte le nombre d'occurence
     *  à partir de la liste des symptomes
     */
    public static void parcoursSymptoms(String tableauSymptoms[]) throws Exception{
        /* *On va parcourir le tableau des Symptomes un-à-un puis compter le nombre d'occurences pour chacun dans la Liste...* */
        FileWriter writer = new FileWriter ("result.out");
        int nombreOccurence = 0;

        for (int j=0; j <tableauSymptoms.length; j++) {
            nombreOccurence = 0;

            for (int cp = 0; cp < myliste.size(); cp++) {
                if (tableauSymptoms[j].equals(myliste.get(cp))) {
                    nombreOccurence++;
                }
            }
            /* *...ensuite renseigner dans le fichier result.out le nombre obtenu* */
            writer.write(tableauSymptoms[j] + ": " + nombreOccurence + "\n");
        }
        writer.close();
    }

}