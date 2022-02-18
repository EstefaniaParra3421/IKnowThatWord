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
    private JButton botonPlay, botonHTPlay;

    //Contenedor
    private JPanel panelBotones, panelLogo, panelHTPlay;
    private ImageIcon logo;
    private JLabel labelLogo;
    private Escucha escucha;
    private JTextArea textoHTPlay;
    private PanelPalabra panelPalabra;
    private ControlPalabra controlPalabra;
    private Timer timer, timerTwo;
    private FileManager fileManager;

    /**
     * Constructor of GUI_Iknowthatword class
     */
    public GUI_Iknowthatword() {
        initGUI();

        //Configuracion por defecto de la ventana
        this.setTitle("I KNOW THAT WORD");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //Color de la ventana
        this.getContentPane().setBackground(new Color(255,202,202));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/recursos/icono.png")).getImage());
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
        //Configuracion para el textoHTPlay
        textoHTPlay.setText("A sequence of words appears (in Spanish), one after another. Memorize them all.\n\n" +
                "After the round of words to memorize, the game will present you with a list with double of words.\n\n" +
                "If the word belongs to the list you have memorized, click on the “SI” button, otherwise, click on the “NO” button.\n\n" +
                "If you acert to hit the vast majority of words, you will go to the next level.\n\n" +
                "Are you ready?");
        textoHTPlay.setBackground(null);
        textoHTPlay.setFont(new Font("Berlin Sans FB", Font.PLAIN,30));
        this.add(panelHTPlay);
        //Agregar el texto al panel
        panelHTPlay.add(textoHTPlay);
        panelHTPlay.setBackground(new Color(255,202,202));
        this.pack();

    }

    /**
     * Main process of the Java program
     *
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI_Iknowthatword miGUI = new GUI_Iknowthatword();
            }
        });
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI_Iknowthatword class
     */
    private void initGUI() {

        //Set up Container and layout

        //Cretate Listener and Control objects
        fileManager = new FileManager();
        escucha = new Escucha();

        //Set up JComponents
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
        //Mostrar panelBotones
        this.add(panelBotones, BorderLayout.SOUTH);

        textoHTPlay = new JTextArea(15, 20);

        //Creacion de panelLogo
        labelLogo = new JLabel();
        logo = new ImageIcon(getClass().getResource("/recursos/logo.png"));
        labelLogo.setIcon(logo);
        panelLogo = new JPanel();
        panelLogo.add(labelLogo);
        panelLogo.setBackground(new Color(255,202,202));
        //Mostrar el panelLogo
        this.add(panelLogo, BorderLayout.NORTH);

        //Creacion del controlPalabra
        controlPalabra = new ControlPalabra();

        //Creacion del panelPalabra
        panelPalabra = new PanelPalabra(controlPalabra.getPalabra());
        //Mostrar panelPalabra
        this.add(panelPalabra, BorderLayout.CENTER);
        panelPalabra.setBackground(new Color(255,202,202));

        //Configuracion e inicio del timer
        timer = new Timer(5000, escucha);
        timer.start();
        timerTwo = new Timer(7000, escucha);
        timerTwo.start();
    }

    private class Escucha implements ActionListener {

        private int counter = 0, puntos= 0;
        private boolean flagPressed = false, flagWords = false, aciertaNivel = false;
        private String palabra, nombre;
        private ArrayList<String> palabrasMemorizadas = new ArrayList<String>();
        private ArrayList<String> totalPalabras = new ArrayList<String>();
        private Random aleatorio = new Random();
        private int flagBienvenida=1, nivel =1;
        private ArrayList<String> usuarios=new ArrayList<String>();

        @Override
        public void actionPerformed(ActionEvent objectEvent) {

            if (objectEvent.getSource() == botonHTPlay)
            {
                ventanaHTPlay();
            }

            if (objectEvent.getSource() == botonPlay)
            {
                panelPalabra.setVisible(true);
                panelHTPlay.setVisible(false);

                if(flagBienvenida==1)
                {
                    int usuarioNuevo=0;
                    usuarios= fileManager.lecturaFileUsuarios();
                    nombre = JOptionPane.showInputDialog("Bienvenido, introduzca su nombre");
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


                    if(flag==true && option==JOptionPane.YES_OPTION){
                        puntos ++;
                        JOptionPane.showMessageDialog(null,"Acertaste!");
                    }else if(flag==false && option==JOptionPane.YES_OPTION){
                        JOptionPane.showMessageDialog(null, "Fallaste!");
                    }else if(flag==true && option==JOptionPane.NO_OPTION){
                        JOptionPane.showMessageDialog(null, "Fallaste!");
                    }else if(flag==false && option==JOptionPane.NO_OPTION){
                        puntos ++;
                        JOptionPane.showMessageDialog(null,"Acertaste!");
                    }
                    counter++;

                }else{
                    timerTwo.stop();
                    aciertaNivel = controlPalabra.estadoJuego(puntos, nivel);

                    if(!aciertaNivel){
                        JOptionPane.showMessageDialog(null, "Perdiste, no haz superado el porcentaje de aciertos");
                        puntos = 0;
                        counter = 0;
                        flagPressed = false;
                        flagWords = false;
                        aciertaNivel = false;
                        botonPlay.setEnabled(true);
                        timer.start();
                        timerTwo.start();
                    }
                    else if(aciertaNivel){
                        JOptionPane.showMessageDialog(null, "HAZ GANADO!!");
                        nivel++;
                        fileManager.cleanText();
                        fileManager.escribirTexto(usuarios,nombre,nivel);
                        puntos = 0;
                        counter = 0;
                        flagPressed = false;
                        flagWords = false;
                        aciertaNivel = false;
                        botonPlay.setEnabled(true);
                        timer.start();
                        timerTwo.start();
                        JOptionPane.showMessageDialog(null, "Si quieres seguir jugando presiona de nuevo el botón play," +
                                " Haz pasado al nivel "+nivel);

                    }


                }
            }
            revalidate();
            repaint();
        }
    }
}
