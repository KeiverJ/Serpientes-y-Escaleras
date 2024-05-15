/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.JOptionPane;

/**
 *
 * @author keive
 */
public class FichaAnimada extends Thread {

    private Jugador jugador;
    private int movimiento;
    private Tablero tablero;
    private int delay;
    private OnFinishListener onFinishListener;

    public FichaAnimada(Jugador jugador, int movimiento, Tablero tablero, int delay) {
        this.jugador = jugador;
        this.movimiento = movimiento;
        this.tablero = tablero;
        this.delay = delay;
    }

    @Override
    public void run() {
        int viejaPos = jugador.getPosition();
        int nuevaPos = viejaPos + movimiento;
        int destino = nuevaPos;

        if (nuevaPos > tablero.rows * tablero.cols) {
            nuevaPos = viejaPos;
        } else {
            int incremento = movimiento > 0 ? 1 : -1;
            for (int i = viejaPos; i != nuevaPos; i += incremento) {
                jugador.setPosition(i);
                tablero.repaint();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (tablero.isSerpienteInicio(nuevaPos)) {
            int serpienteFin = tablero.getSerpienteFin(nuevaPos);
            nuevaPos = serpienteFin;
            EventoJuego evento = new EventoJuego("se movió de la posición " + viejaPos + " a la posición " + destino + ", " + "cayó en una serpiente y retrocedió a la posición " + nuevaPos, jugador.getNombre(), viejaPos, nuevaPos);
            tablero.eventosJuego.add(evento);
            JOptionPane.showMessageDialog(tablero, "El jugador " + jugador.getNombre() + " se encontró con una serpiente y retrocedió desde la posición " + destino + " hasta la posición " + nuevaPos + ".", "Serpiente", JOptionPane.INFORMATION_MESSAGE);
        } else if (tablero.isEscaleraInicio(nuevaPos)) {
            int escaleraFin = tablero.getEscaleraFin(nuevaPos);
            nuevaPos = escaleraFin;
            EventoJuego evento = new EventoJuego("se movió de la posición " + viejaPos + " a la posición " + destino + ", " + "encontró una escalera y avanzó a la posición " + nuevaPos, jugador.getNombre(), viejaPos, nuevaPos);
            tablero.eventosJuego.add(evento);
            JOptionPane.showMessageDialog(tablero, "El jugador " + jugador.getNombre() + " se encontró con una escalera y avanzó desde la posición " + destino + " hasta la posición " + nuevaPos + ".", "Escalera", JOptionPane.INFORMATION_MESSAGE);
        } else {
            EventoJuego evento = new EventoJuego("se movió de la posición " + viejaPos + " a la posición " + nuevaPos, jugador.getNombre(), viejaPos, nuevaPos);
            tablero.eventosJuego.add(evento);
        }

        jugador.setPosition(nuevaPos);

        if (nuevaPos == tablero.rows * tablero.cols) {
            EventoJuego evento = new EventoJuego("ha ganado el juego.", jugador.getNombre(), viejaPos, nuevaPos);
            tablero.eventosJuego.add(evento);
            JOptionPane.showMessageDialog(tablero, evento.getDescripcion(), "Ganador", JOptionPane.INFORMATION_MESSAGE);
        }

        tablero.repaint();

        if (onFinishListener != null) {
            onFinishListener.onFinish();
        }
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    public interface OnFinishListener {

        void onFinish();
    }
}
