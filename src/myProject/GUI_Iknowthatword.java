package myProject;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GUI_Iknowthatword extends JFrame {

    //Declaracion de botones
    private JButton botonContinue, botonPlay, botonHTPlay;

    //Contenedor
    private JPanel panelBotones, panelDatos, panelLogo;
    private Header titulo;
    private ImageIcon logo;
    private JLabel labelLogo;
    private Escucha escucha;
    private JTextArea textoPrueba;
    private PanelPalabra panelPalabra;
    private ControlPalabra controlPalabra;
    private Timer timer, timerTwo;

    //Clase contructor
    public GUI_Iknowthatword() {
        initGUI();

        //Configuracion por defecto de la ventana
        this.setTitle("I KNOW THAT WORD");
        //this.setSize(600, 500);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Ejecucion del programa (main)
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI_Iknowthatword miGUI = new GUI_Iknowthatword();
            }
        });
    }

    private void initGUI() {

        //Set up Container and layout

        //Cretate Listener and Control objects
        escucha = new Escucha();

        //Set up JComponents
        titulo = new Header("I KNOW THAT WORD", Color.BLACK);
        //this.add(titulo,BorderLayout.NORTH);

        //---Boton Continue---
        botonContinue = new JButton("CONTINUE");
        //Escucha del boton
        botonContinue.addActionListener(escucha);

        //---Boton Play---
        botonPlay = new JButton("PLAY");
        //Escucha del boton
        botonPlay.addActionListener(escucha);

        //---Boton How to play---
        botonHTPlay = new JButton("HOW TO PLAY");
        //Escucha del boton
        botonHTPlay.addActionListener(escucha);

        //Creacion del panelBotones
        panelBotones = new JPanel();
        panelBotones.add(botonContinue);
        panelBotones.add(botonPlay);
        panelBotones.add(botonHTPlay);

        this.add(panelBotones, BorderLayout.SOUTH);

        //Creacion del panelDatos
        panelDatos = new JPanel();
        //panelDatos.setBorder(BorderFactory.createTitledBorder(null, "",
        //TitledBorder.CENTER, TitledBorder.CENTER, new Font("calibri", Font.BOLD,20), Color.BLUE));

        this.add(panelDatos, BorderLayout.CENTER);

        //Creacion de panelLogo
        labelLogo = new JLabel();
        logo = new ImageIcon(getClass().getResource("/recursos/logo.png"));
        labelLogo.setIcon(logo);
        panelLogo = new JPanel();
        panelLogo.add(labelLogo);

        this.add(panelLogo, BorderLayout.NORTH);

        textoPrueba = new JTextArea(15, 20);

        controlPalabra = new ControlPalabra();

        panelPalabra = new PanelPalabra(controlPalabra.getPalabra());

        timer = new Timer(1000, escucha);
        timer.start();
        timerTwo = new Timer(7000, escucha);
        timerTwo.start();
    }

    private class Escucha implements ActionListener {

        private int counter = 0;
        private boolean flagPressed = false, flagWords = false;
        private String palabra;
        private ArrayList<String> palabrasMemorizadas = new ArrayList<String>();
        private ArrayList<String> totalPalabras = new ArrayList<String>();
        private Random aleatorio = new Random();

        @Override
        public void actionPerformed(ActionEvent objectEvent) {
            panelDatos.removeAll();

            if (objectEvent.getSource() == botonContinue) {
                panelLogo.removeAll();
                remove(panelPalabra);
                textoPrueba.setText("AQUI VA EL PLAY (CONTINUE)");
                textoPrueba.setBackground(null);
                textoPrueba.setFont(new Font("arial", Font.BOLD, 27));
                panelDatos.add(textoPrueba);
            }
            else {
                if (objectEvent.getSource() == botonPlay) {
                    panelLogo.removeAll();
                    flagPressed = true;
                    controlPalabra.nivel();
                    System.out.println("entra al play");
                }
                if (objectEvent.getSource() == timer && flagPressed == true) {

                    panelPalabra.updateUI();
                    panelLogo.removeAll();
                    botonPlay.setEnabled(false);

                    if (counter <= controlPalabra.getPalabrasMemorizar()) {
                        if (counter == 0) {
                            totalPalabras = controlPalabra.listadoPalabras();
                        }
                        palabra = totalPalabras.get(aleatorio.nextInt(totalPalabras.size()));
                        palabrasMemorizadas.add(palabra);
                        panelPalabra.setPalabra(palabra);
                        add(panelPalabra, BorderLayout.CENTER);
                        panelPalabra.updateUI();
                        counter++;
                        if (counter == controlPalabra.getPalabrasMemorizar() + 1) {
                            timer.stop();
                            System.out.println(palabrasMemorizadas);
                            remove(panelPalabra);
                            JOptionPane.showMessageDialog(null, "Seguro que ya memorizaste tus palabras. Desmuestralo!");
                            flagWords = true;
                            counter = 0;
                            panelPalabra.updateUI();
                            //JOptionPane.showConfirmDialog(panelPalabra,"jeje","",JOptionPane.YES_NO_OPTION);
                        }
                    }


                }
                if (objectEvent.getSource() == timerTwo && flagWords == true) {
                    if (counter <= totalPalabras.size()) {
                        String fraseSeleccionada = totalPalabras.get(counter);
                        panelPalabra.setPalabra(fraseSeleccionada);
                        int option = JOptionPane.showConfirmDialog(panelPalabra, "", "", JOptionPane.YES_NO_OPTION);
                        for (int i = 0; i <= palabrasMemorizadas.size(); i++) {
                            if (fraseSeleccionada.equals(palabrasMemorizadas.get(i)) && option == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Has Acerado!");
                                break;
                            } else if (!fraseSeleccionada.equals(palabrasMemorizadas.get(i)) && option == JOptionPane.NO_OPTION) {
                                JOptionPane.showMessageDialog(null, "Has Acerado!");
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Fallaste!");
                            }
                        }
                        counter++;
                    }
                } else {
                    remove(panelPalabra);
                    panelLogo.add(labelLogo);
                    textoPrueba.setText("AQUI VA EL HOW TO PLAY");
                    textoPrueba.setBackground(null);
                    textoPrueba.setFont(new Font("arial", Font.BOLD, 27));
                    panelDatos.add(textoPrueba);
                }
            }

            revalidate();
            repaint();
        }
    }
}
