/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author keiver
 */
import java.awt.Color;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Jugador {

    private String nombre;
    private ImageIcon fichaIcon;
    private int id;
    private int position;

    public Jugador() {
    }

    public Jugador(int id, String nombre, ImageIcon fichaIcon) {
        this.nombre = nombre;
        this.fichaIcon = fichaIcon;
        this.position = 1;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ImageIcon getFichaIcon() {
        return fichaIcon;
    }

    public void setFichaIcon(ImageIcon fichaIcon) {
        this.fichaIcon = fichaIcon;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void configurarJugador(Jugador jugador, String nombre, ImageIcon fichaIcon, JLabel labelNombre) {
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tienes que poner un nombre de jugador.", "Alerta", JOptionPane.WARNING_MESSAGE);
        } else {
            jugador.setNombre(nombre);
            jugador.setFichaIcon(fichaIcon);
            labelNombre.setText(jugador.getNombre());
        }
    }
}
