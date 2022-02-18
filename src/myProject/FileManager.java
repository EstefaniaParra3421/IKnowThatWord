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
    private String PATH_USUARIOS="src/myProject/files/users.txt";
    private String PATH_PALABRAS="src/myProject/files/words.txt";


    public ArrayList<String> lecturaFileUsuarios() {
        ArrayList<String> usuarios = new ArrayList<String>();

        try {
            fileReader = new FileReader(PATH_USUARIOS);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null)
            {
                //int coma=line.indexOf(",");
                //String nombre=line.substring(0, coma);
                usuarios.add(line);
                line=input.readLine();
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
        return usuarios;
    }

    public ArrayList<String> lecturaFile() {
        ArrayList<String> palabras = new ArrayList<String>();

        try {
            fileReader = new FileReader(PATH_PALABRAS);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null)
            {
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
            fileWriter = new FileWriter(PATH_USUARIOS, true);
            int level=1;
            output = new BufferedWriter(fileWriter);
            output.write(linea); output.write(","+level);
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
    public void escribirTexto(ArrayList<String> usuarios, String nombreJugadorActual, int nivel) {
        try {
            fileWriter = new FileWriter(PATH_USUARIOS, true);
            output = new BufferedWriter(fileWriter);
            output.write(nombreJugadorActual); output.write(","+nivel);
            output.newLine();
            for(int i = 1; i < usuarios.size(); i++){
                int coma=usuarios.get(i).indexOf(",");
                if(!usuarios.get(i).substring(0,coma).equals(nombreJugadorActual)){
                    output.write(usuarios.get(i));
                    output.newLine();
                }
            }
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

    public void cleanText()
    {
        try
        {
            output = new BufferedWriter(new FileWriter(PATH_USUARIOS));
            output.write("");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}