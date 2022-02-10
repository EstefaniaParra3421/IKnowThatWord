package myProject;

import javax.swing.*;
import java.awt.*;

public class Titulos extends JLabel {
    public Titulos (String titulo, Color colorFondo) {
        this.setText(titulo);
        this.setBackground(colorFondo);
        this.setForeground(Color.WHITE);
        this.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        this.setOpaque(true);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
    }
}
