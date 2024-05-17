/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.ImageIcon;

/**
 *
 * @author keiver
 */
public class Jugador {

    private String nombre;
    private ImageIcon fichaTableroIcon;
    private int id;
    private int position;

    public Jugador(int id, String nombre, ImageIcon fichaTableroIcon) {
        this.nombre = nombre;
        this.fichaTableroIcon = fichaTableroIcon;
        this.position = 1;
    }

    public void setFichaTableroIcon(ImageIcon fichaTableroIcon) {
        this.fichaTableroIcon = fichaTableroIcon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public ImageIcon getFichaTableroIcon() {
        return fichaTableroIcon;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
