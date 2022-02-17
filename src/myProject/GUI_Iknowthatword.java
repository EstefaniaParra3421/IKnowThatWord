package myProject;

import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RGBImageFilter;
import java.util.ArrayList;
import java.util.Random;

public class GUI_Iknowthatword extends JFrame {

    //Declaracion de botones
    private JButton botonPlay, botonHTPlay;

    //Contenedor
    private JPanel panelBotones, panelDatos, panelLogo, panelHTPlay;
    private Header titulo;
    private ImageIcon logo;
    private JLabel labelLogo;
    private Escucha escucha;
    private JTextArea textoHTPlay;
    private PanelPalabra panelPalabra;
    private ControlPalabra controlPalabra;
    private Timer timer, timerTwo;
    private FileManager fileManager;

    //Clase contructor
    public GUI_Iknowthatword() {
        initGUI();

        //Configuracion por defecto de la ventana
        this.setTitle("I KNOW THAT WORD");
        //this.setSize(600, 500);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //Color de la ventana
        this.getContentPane().setBackground(new Color(255,202,202));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * The class stores everything related to the window to show the "How to play", it shows the text and the play button
     */

    private void ventanaHTPlay(){
        panelBotones.remove(botonHTPlay);
        panelPalabra.setVisible(false);
        //Creacion del panelHTPlay
        panelHTPlay = new JPanel();
        panelHTPlay.setBorder(BorderFactory.createTitledBorder(null, "HOW TO PLAY",TitledBorder.CENTER,
                TitledBorder.CENTER, new Font("Berlin Sans FB", Font.PLAIN,40), new Color(46,150,215)));
        textoHTPlay.setText("A sequence of words appears (in Spanish), one after another. Memorize them all.\n\n" +
                "After the round of words to memorize, the game will present you with a list with double of words.\n\n" +
                "If the word belongs to the list you have memorized, click on the “SI” button, otherwise, click on the “NO” button.\n\n" +
                "If you acert to hit the vast majority of words, you will go to the next level.\n\n" +
                "Are you ready?");
        textoHTPlay.setBackground(null);
        textoHTPlay.setFont(new Font("Berlin Sans FB", Font.PLAIN,30));
        this.add(panelHTPlay);
        panelHTPlay.add(textoHTPlay);
        panelHTPlay.setBackground(new Color(255,202,202));
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
        fileManager=new FileManager();
        escucha = new Escucha();

        //Set up JComponents
        titulo = new Header("I KNOW THAT WORD", Color.BLACK);
        //this.add(titulo,BorderLayout.NORTH);

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
        panelBotones.add(botonPlay);
        panelBotones.add(botonHTPlay);
        panelBotones.setBackground(new Color(255,202,202));

        this.add(panelBotones, BorderLayout.SOUTH);

        //Creacion del panelDatos
        panelDatos = new JPanel();
        panelDatos.setBorder(BorderFactory.createTitledBorder(null, "",
        TitledBorder.CENTER, TitledBorder.CENTER, new Font("calibri", Font.BOLD,20), Color.BLUE));

        this.add(panelDatos, BorderLayout.CENTER);
        textoHTPlay = new JTextArea(15, 20);

        //Creacion de panelLogo
        labelLogo = new JLabel();
        logo = new ImageIcon(getClass().getResource("/recursos/logo.png"));
        labelLogo.setIcon(logo);
        panelLogo = new JPanel();
        panelLogo.add(labelLogo);
        panelLogo.setBackground(new Color(255,202,202));

        this.add(panelLogo, BorderLayout.NORTH);

        //Creacion del controlPalabra
        controlPalabra = new ControlPalabra();

        //Creacion del panelPalabra
        panelPalabra = new PanelPalabra(controlPalabra.getPalabra());
        this.add(panelPalabra, BorderLayout.CENTER);
        panelPalabra.setBackground(new Color(255,202,202));
        //panelPalabra.setVisible(false);

        timer = new Timer(1000, escucha);
        timer.start();
        timerTwo = new Timer(5000, escucha);
        timerTwo.start();
    }

    private class Escucha implements ActionListener {

        private int counter = 0;
        private boolean flagPressed = false, flagWords = false;
        private String palabra;
        private ArrayList<String> palabrasMemorizadas = new ArrayList<String>();
        private ArrayList<String> totalPalabras = new ArrayList<String>();
        private Random aleatorio = new Random();
        private int flagBienvenida=1, nivel =1;
        private ArrayList<String> usuarios=new ArrayList<String>();


        @Override
        public void actionPerformed(ActionEvent objectEvent) {
            //panelHTPlay.removeAll();

            if (objectEvent.getSource() == botonHTPlay)
            {
                ventanaHTPlay();
            }

            if (objectEvent.getSource() == botonPlay)
            {
                //panelHTPlay.setVisible(false);
                //panelLogo.setVisible(false);
                if(flagBienvenida==1)
                {
                    int usuarioNuevo=0;
                    usuarios= fileManager.lecturaFileUsuarios();

                    String nombre = JOptionPane.showInputDialog("Bienvenido, introduzca su nombre");
                    for(int i=0; i < usuarios.size(); i++)//For para leer los usuarios
                    {
                        int coma=usuarios.get(i).indexOf(",");
                        if(usuarios.get(i).substring(0,coma).equals(nombre))
                        {
                            nivel = Integer.parseInt(usuarios.get(i).substring(coma+1));
                            JOptionPane.showMessageDialog(null, "Bienvenido de nuevo "+nombre);
                            usuarioNuevo=1;
                            //nivelEncontrado= usuarios.get(i).
                            break;
                        }
                    }

                    if(nombre!=null && usuarioNuevo==0)
                    {
                        usuarios.add(nombre);
                        fileManager.escribirTexto(nombre);
                        JOptionPane.showMessageDialog(null, "Bienvenido a I Know That Word "+nombre);
                    }

                    while(nombre==null || nombre=="")
                    {
                        nombre=JOptionPane.showInputDialog("Tiene que introducir un nombre para jugar.");
                        for(int i=0; i < usuarios.size(); i++)//For para leer los usuarios
                        {
                            int coma=usuarios.get(i).indexOf(",");
                            if(usuarios.get(i).substring(0,coma).equals(nombre))
                            {
                                nivel = Integer.parseInt(usuarios.get(i).substring(coma+1));
                                JOptionPane.showMessageDialog(null, "Bienvenido de nuevo "+nombre);
                                usuarioNuevo=1;
                                break;
                            }
                        }

                        if(nombre!=null && usuarioNuevo==0)
                        {
                            usuarios.add(nombre);
                            fileManager.escribirTexto(nombre);
                            JOptionPane.showMessageDialog(null, "Bienvenido a I Know That Word "+nombre);
                        }
                    }
                    flagBienvenida=0;
                }

                botonPlay.setEnabled(false);
                flagPressed = true;
                controlPalabra.nivel(nivel);
                System.out.println("entra al play");
            }

            if (objectEvent.getSource() == timer && flagPressed == true) {
                if (counter < controlPalabra.getPalabrasMemorizar())
                {
                    if (counter == 0) {
                        totalPalabras = controlPalabra.listadoPalabras(nivel);
                    }
                    palabra = totalPalabras.get(aleatorio.nextInt(totalPalabras.size()));
                    palabrasMemorizadas.add(palabra);
                    panelPalabra.setPalabra(palabra);
                    panelPalabra.updateUI();
                    counter++;
                }
                else
                {
                    timer.stop();
                    System.out.println(palabrasMemorizadas);
                    JOptionPane.showMessageDialog(null, "Seguro que ya memorizaste tus palabras. Desmuestralo!");
                    flagWords = true;
                    counter = 1;
                }
            }

            if (objectEvent.getSource() == timerTwo && flagWords == true)
            {

                if (counter < totalPalabras.size()) {
                    String fraseSeleccionada = totalPalabras.get(counter);
                    panelPalabra.setPalabra(fraseSeleccionada);
                    panelPalabra.updateUI();
                    System.out.println(palabrasMemorizadas);
                    Boolean flag=false;
                    int option = JOptionPane.showConfirmDialog(panelPalabra, "", "", JOptionPane.YES_NO_OPTION);

                    for(int i = 0; i < palabrasMemorizadas.size(); i++){
                        if(fraseSeleccionada.equals(palabrasMemorizadas.get(i))){
                            flag=true;
                            break;
                        }else{
                            flag=false;
                        }
                    }

                    Boolean tiempo = timerTwo.getDelay() < 1000;

                    System.out.println("Tiempo: "+timerTwo.getInitialDelay());
                    System.out.println(tiempo);


                    if(flag==true && option==JOptionPane.YES_OPTION /*&& tiempo==false*/){
                        JOptionPane.showMessageDialog(null,"Acertaste!");
                    }else if(flag==false && option==JOptionPane.YES_OPTION /*|| tiempo==true*/){
                        JOptionPane.showMessageDialog(null, "Fallaste!");
                    }else if(flag==true && option==JOptionPane.NO_OPTION /*|| tiempo==true*/){
                        JOptionPane.showMessageDialog(null, "Fallaste!");
                    }else if(flag==false && option==JOptionPane.NO_OPTION /*&& tiempo==false*/){
                        JOptionPane.showMessageDialog(null,"Acertaste!");
                    }
                    counter++;
                }
            }
            revalidate();
            repaint();
        }
    }
}
