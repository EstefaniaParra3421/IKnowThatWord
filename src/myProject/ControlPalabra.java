package myProject;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Class "ControlPalabra" Store and return the random word
 *
 * @author Brandon Perdomo brandon.perdomo@correounivalle.edu.co
 * @version v.1.0.0 12/02/2022
 */

public class ControlPalabra {
    private Diccionario palabras;
    private String palabra, fraseSeleccionada;
    private int niveles = 1, palabrasMemorizar;
    private ArrayList<String> totalPalabras;
    private ControlPalabra controlPalabra;

    /**
     * Get the words from the "diccionario"
     */
    public ControlPalabra() {
        palabras = new Diccionario();
    }

    /**
     * Store the random word
     *
     * @return the randow word (string)
     */
    public String getPalabra() {

        palabra = palabras.getPalabras();
        return palabra;
    }

    /**
     * Dependiendo del nivel en que se encuentre el jugador, retorna la cantidad de palabras a memorizar
     *
     * @return int palabrasMemorizar
     */
    public int nivel() {
        switch (niveles) {
            case 1:
                palabrasMemorizar = 10;
                break;
            case 2:
                palabrasMemorizar = 20;
                break;
            case 3:
                palabrasMemorizar = 25;
                break;
            case 4:
                palabrasMemorizar = 30;
                break;
            case 5:
                palabrasMemorizar = 35;
                break;
            case 6:
                palabrasMemorizar = 40;
                break;
            case 7:
                palabrasMemorizar = 50;
                break;
            case 8:
                palabrasMemorizar = 60;
                break;
            case 9:
                palabrasMemorizar = 70;
                break;
            case 10:
                palabrasMemorizar = 100;
                break;
        }
        return palabrasMemorizar;
    }

    public ArrayList<String> listadoPalabras() {
        nivel();
        controlPalabra = new ControlPalabra();
        totalPalabras = new ArrayList<String>();
        System.out.println("entra al listado de palabras " + palabrasMemorizar);
        System.out.println(getPalabrasMemorizar());
        for (int i = 1; i <= palabrasMemorizar * 2; i++) {
            fraseSeleccionada = controlPalabra.getPalabra();
            totalPalabras.add(fraseSeleccionada);
        }
        return totalPalabras;
    }

    public void palabrasAleatoriasMostrar(ArrayList palabrasMemorizadas, ArrayList totalPalabras) {
        System.out.println("entra a palabras aleatorias");
        String palabraSeleccionada = palabrasMemorizadas.get(0).toString();
        System.out.println(palabraSeleccionada + palabrasMemorizadas.size());
        palabraSeleccionada = palabrasMemorizadas.get(9).toString();
        System.out.println(palabraSeleccionada);
    }

    public ArrayList<String> getTotalPalabras() {
        return totalPalabras;
    }

    public int getPalabrasMemorizar() {
        return palabrasMemorizar;
    }

}
