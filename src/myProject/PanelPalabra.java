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
        palabra="";
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

        g.setColor(new Color(255,202,202));
        g.fillRect(125, 0, WIDTH, HEIGTH);
        g.setFont(new Font("Berlin Sans FB", Font.PLAIN+Font.BOLD,30));
        g.setColor(new Color(46,150,215));
        g.drawString(palabra, 150, 30);
    }
}
