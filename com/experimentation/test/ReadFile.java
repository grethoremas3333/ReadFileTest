package com.experimentation.test;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.io.FileReader;
import java.io.FileWriter;

public class ReadFile {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("Version:v0.1.0"  +"\n"+ "Initialisation...");

        BufferedReader reader = new BufferedReader (new FileReader("symptoms.txt")); //lecture du fichier symptoms.txt
        String line = reader.readLine(); //insertion du contenu du fichier symptoms.txt dans la variable line

        int i = 0; //notre compteur

        List<String> myliste=new ArrayList<String>(); //la collection (Liste ordonnée) qui va contenir toutes les donéées du fichier symptoms.txt
        Set<String> symptomes = new HashSet<String>(); //la collection ensemble (SET) qui va contenir uniquement les symptomes

        while (line != null) {
            i++;
            myliste.add(line);
            symptomes.add(line);
            line = reader.readLine();
        }

        /* *Petit test de vérification pour confirmer que les données ont bien été lues* */
        System.out.println("ma Liste contient: "+myliste.size()+" éléments.");
        System.out.println("les symptômes sont au nombre de: "+symptomes.size()+" au total!!!");

        /* *On crée un second fichier qui va contenir uniquement les symptomes ça nous sera utile pour la suite* */
        FileWriter mywriter = new FileWriter ("symptomsonly.txt");

        int compteur = 1;
        for(String symptome : symptomes) {
            System.out.println(compteur+")- "+symptome);
            mywriter.write(symptome+ "\n");
            compteur++;
        }
        mywriter.close();

        /* *On va parcourir le fichier symptomsonly pour y récupérer les éléments un-à-un pour voir leur nombre d'occurences dans la Liste ensuite renseigner dans le fichier result.out le nombre obtenu* */
        BufferedReader myreader = new BufferedReader (new FileReader("symptomsonly.txt"));
        String myline;

        FileWriter writer = new FileWriter ("result.out");
        int nombreOccurence = 0;

        while ((myline = myreader.readLine()) != null) {
            nombreOccurence = 0;
            for (int cp = 0; cp < myliste.size(); cp++) {
                if (myline.equals(myliste.get(cp))) {
                    nombreOccurence++;
                }
            }
            writer.write(myline + ": " + nombreOccurence + "\n");
        }
        writer.close();

        System.out.println("...Fin!!!");
    }
}