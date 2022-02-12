package myProject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class diccionario will store a list inside the variable "diccionario" and will use a method "getPalabras"
 * to return the word contained inside a text file randomly
 * @author Brandon Perdomo brandon.perdomo@correunivalle.edu.co
 * @version v.1.0.0 12/02/2022
 */

public class Diccionario {

    private ArrayList<String> diccionario = new ArrayList<String>();

    /**
     * This class stores the list in "diccionario"
     */
    public Diccionario(){
        FileManager fileManager = new FileManager();
        diccionario = fileManager.lecturaFile();
    }

    /**
     * This class gets one of the words contained within the array randomly.
     * @return a word from the array
     */
    public String getPalabras(){
        Random aleatorio = new Random();

        return diccionario.get(aleatorio.nextInt(diccionario.size()));
    }
}
