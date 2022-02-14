package myProject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class "PanelPalabra" draws and organizes the panel where the words will be
 *
 * @author Brandon Perdomo brandon.perdomo@correounivalle.edu.co
 * @version v.1.0.0 12/02/2022
 */

public class PanelPalabra extends JPanel {
    public static final int WIDTH = 150;
    public static final int HEIGTH = 50;

    private String palabra, frase, fraseSeleccionada;
    private ControlPalabra controlPalabra;
    private int contador = 1;
    private Random aleatorio = new Random();

    /**
     * This class brings the random words
     *
     * @param frase
     */
    public PanelPalabra(String frase) {
        this.frase = frase;
        setPreferredSize(new Dimension(WIDTH, HEIGTH));
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    /**
     * Dibuja la palabra y el fondo
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGTH);
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 17));
        g.setColor(Color.BLACK);
        g.drawString(palabra, 4, 30);
    }


}
