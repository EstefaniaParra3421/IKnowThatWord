package myProject;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Iknowthatword extends JFrame {

    //Declaracion de botones
    private JButton botonContinue, botonPlay, botonHTPlay;

    //Contenedor
    private JPanel panelBotones, panelDatos, panelLogo;
    private Titulos titulo;
    private ImageIcon logo;
    private JLabel labelLogo;
    private Escucha escucha;
    private JTextArea textoPrueba;

    //Clase contructor
    public GUI_Iknowthatword(){
        initGUI();

        //Configuracion por defecto de la ventana
        this.setTitle("I KNOW THAT WORD");
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Ejecucion del programa (main)
    public static void main (String[] args) {
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
        titulo = new Titulos("I KNOW THAT WORD", Color.BLACK);
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

        this.add(panelBotones,BorderLayout.SOUTH);

        //Creacion del panelDatos
        panelDatos = new JPanel();
        //panelDatos.setBorder(BorderFactory.createTitledBorder(null, "",
                //TitledBorder.CENTER, TitledBorder.CENTER, new Font("calibri", Font.BOLD,20), Color.BLUE));

        this.add(panelDatos,BorderLayout.CENTER);

        //Creacion de panelLogo
        labelLogo = new JLabel();
        logo = new ImageIcon(getClass().getResource("/recursos/logo.png"));
        labelLogo.setIcon(logo);
        panelLogo = new JPanel();
        panelLogo.add(labelLogo);

        this.add(panelLogo,BorderLayout.NORTH);

        textoPrueba = new JTextArea(15,20);
    }

    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent objectEvent) {
            panelDatos.removeAll();
            if(objectEvent.getSource()==botonContinue){

                textoPrueba.setText("AQUI VA EL PLAY (CONTINUE)");
                textoPrueba.setBackground(null);
                textoPrueba.setFont(new Font("arial", Font.BOLD,27));
                panelDatos.add(textoPrueba);
            }else {
                if(objectEvent.getSource()==botonPlay){

                    textoPrueba.setText("AQUI VA EL PLAY (NORMAL)");
                    textoPrueba.setBackground(null);
                    textoPrueba.setFont(new Font("arial", Font.BOLD,27));
                    panelDatos.add(textoPrueba);
                }
                else{

                    textoPrueba.setText("AQUI VA EL HOW TO PLAY");
                    textoPrueba.setBackground(null);
                    textoPrueba.setFont(new Font("arial", Font.BOLD,27));
                    panelDatos.add(textoPrueba);
                }
            }
            revalidate();
            repaint();
        }
    }
}
