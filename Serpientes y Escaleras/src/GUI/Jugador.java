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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Jugador {

    private String nombre;


    public Jugador() {
        
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void configurarJugador(Jugador jugador, String nombre, String colorSeleccionado, JLabel labelNombre) {

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tienes que poner un nombre de jugador.", "Alerta", JOptionPane.WARNING_MESSAGE);
        } else {
            jugador.setNombre(nombre);
            labelNombre.setText(jugador.getNombre());
        }
    }

}
