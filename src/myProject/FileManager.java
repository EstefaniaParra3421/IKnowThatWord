package myProject;

import java.io.*;
import java.util.ArrayList;

/**
 * Class that takes the words from the text file and converts it to an array
 */

public class FileManager {
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;


    public ArrayList<String> lecturaFile() {
        ArrayList<String> palabras = new ArrayList<String>();

        try {
            fileReader = new FileReader("src/myProject/files/words.txt");
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null) {
                palabras.add(line);//Agrega cada elemento del texto al arraylist
                line = input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return palabras;
    }

    public void escribirTexto(String linea) {
        try {
            fileWriter = new FileWriter("src/myProject/files/words.txt", true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}