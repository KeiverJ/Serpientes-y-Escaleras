package GUI;

import java.awt.Color;
import java.util.Random;
import javax.swing.ImageIcon;

public class Jugador {

    private String nombre;
    private ImageIcon fichaIcon;
    private int id;
    private int position;
    private Color color;

    public Jugador(int id, String nombre, ImageIcon fichaIcon) {
        this.nombre = nombre;
        this.fichaIcon = fichaIcon;
        this.position = 1;
        this.id = id;
        this.color = getRandomColor();
    }

    public String getNombre() {
        return nombre;
    }

    public ImageIcon getFichaIcon() {
        return fichaIcon;
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private Color getRandomColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b);
    }
}

// Este método no es necesario ya que el constructor ya configura el jugador
/*
    public void configurarJugador(Jugador jugador, String nombre, ImageIcon fichaIcon, JLabel labelNombre) {
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tienes que poner un nombre de jugador.", "Alerta", JOptionPane.WARNING_MESSAGE);
        } else {
            jugador.setNombre(nombre);
            jugador.setFichaIcon(fichaIcon);
            jugador.getColor();
            labelNombre.setText(jugador.getNombre());
        }
    }
 */
