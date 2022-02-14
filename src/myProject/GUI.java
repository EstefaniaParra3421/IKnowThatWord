package myProject;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used for ...
 *
 * @version v.1.0.0 date:21/11/2021
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 */
public class GUI extends JFrame {

    //Declaracion de los botones
    private JButton botonContinue, botonPlay, botonHTPlay;

    //Panel donde estaran los botones y demas objetos
    private JPanel panelBotones;

    private Header headerProject;

    /**
     * Constructor of GUI class
     */
    public GUI() {
        initGUI();

        //Default JFrame configuration
        this.setTitle("I KNOW  THAT WORD");
        this.setSize(200, 100);
        //this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object

        //Set up JComponents

        //Creacion de botones
        botonContinue = new JButton("CONTINUE");
        botonPlay = new JButton("PLAY");
        botonHTPlay = new JButton("HOW TO PLAY");

        //Agregar los botones al panel del contenedor, se agrega el panelBotones que es el que contiene a dichos botones
        this.add(panelBotones, BorderLayout.SOUTH);

        headerProject = new Header("Header ...", Color.BLACK);

        this.add(headerProject, BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout
    }

    /**
     * Main process of the Java program
     *
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args) {
        //Lanzar una interfraz grafica de escucha
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI miGUI = new GUI();
            }
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha {

    }
}
