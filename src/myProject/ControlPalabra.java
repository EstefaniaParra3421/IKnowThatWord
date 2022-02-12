package myProject;

/**
 * Class "ControlPalabra" Store and return the random word
 * @author Brandon Perdomo brandon.perdomo@correounivalle.edu.co
 * @version v.1.0.0 12/02/2022
 */

public class ControlPalabra {
    private Diccionario palabras;
    private String palabra;

    /**
     * Get the words from the "diccionario"
     */
    public ControlPalabra(){
        palabras = new Diccionario();
    }

    /**
     * Store the random word
     * @return the randow word (string)
     */
    public String getPalabra(){

        palabra = palabras.getPalabras();
        return palabra;
    }
}
