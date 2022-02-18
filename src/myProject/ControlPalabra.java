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
    private int palabrasMemorizar;
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
    public int nivel(int niveles) {
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

    public ArrayList<String> listadoPalabras(int nivelUsuario) {
        nivel(nivelUsuario);
        controlPalabra = new ControlPalabra();
        totalPalabras = new ArrayList<String>();
        for (int i = 1; i <= palabrasMemorizar * 2; i++) {
            fraseSeleccionada = controlPalabra.getPalabra();
            totalPalabras.add(fraseSeleccionada);
        }
        return totalPalabras;
    }

    public void palabrasAleatoriasMostrar(ArrayList palabrasMemorizadas, ArrayList totalPalabras) {
        String palabraSeleccionada = palabrasMemorizadas.get(0).toString();
        palabraSeleccionada = palabrasMemorizadas.get(9).toString();
    }

    public Boolean estadoJuego(int puntos, int nivel){
        boolean gano = false;

        if(nivel == 1 && puntos >= 14){
            return gano = true;
        }else if(nivel == 2 && puntos >= 28){
            return gano = true;
        }else if(nivel == 3 && puntos >= 38){
            return gano = true;
        }else if(nivel == 4 && puntos >= 48){
            return gano = true;
        }else if(nivel == 5 && puntos >= 56){
            return gano = true;
        }else if(nivel == 6 && puntos >= 68){
            return gano = true;
        }else if(nivel == 7 && puntos >= 90){
            return gano = true;
        }else if(nivel == 8 && puntos >= 108){
            return gano = true;
        }else if(nivel == 9 && puntos >= 133){
            return gano = true;
        }else if(nivel == 10 && puntos >= 200){
            return gano = true;
        }

        return gano;
    }

    public ArrayList<String> getTotalPalabras() {
        return totalPalabras;
    }

    public int getPalabrasMemorizar() {
        return palabrasMemorizar;
    }

}
