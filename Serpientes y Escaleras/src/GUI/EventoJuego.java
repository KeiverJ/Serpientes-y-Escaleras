/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author keive
 */
public class EventoJuego {

    private String descripcion;
    private String nombreJugador;
    private int posicionAnterior;
    private int posicionNueva;

    public EventoJuego(String descripcion, String nombreJugador, int posicionAnterior, int posicionNueva) {
        this.descripcion = descripcion;
        this.nombreJugador = nombreJugador;
        this.posicionAnterior = posicionAnterior;
        this.posicionNueva = posicionNueva;
    }

    public String getDescripcion() {
        return "Jugador " + nombreJugador + ": " + descripcion;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPosicionAnterior() {
        return posicionAnterior;
    }

    public void setPosicionAnterior(int posicionAnterior) {
        this.posicionAnterior = posicionAnterior;
    }

    public int getPosicionNueva() {
        return posicionNueva;
    }

    public void setPosicionNueva(int posicionNueva) {
        this.posicionNueva = posicionNueva;
    }

}
