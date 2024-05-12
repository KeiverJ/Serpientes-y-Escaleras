/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Dado {

    private static final String RUTA_RECURSOS = "/resources/";
    private int valorDado;
    private ImageIcon imagenDado;

    public Dado() {
        lanzar();
    }

    public void lanzar() {
        Random random = new Random();
        valorDado = random.nextInt(6) + 1;
        imagenDado = obtenerImagenDado();
    }

    public int getValorDado() {
        return valorDado;
    }

    public ImageIcon getImagenDado() {
        return imagenDado;
    }

    private ImageIcon obtenerImagenDado() {
        String nombreArchivo = valorDado + ".png";
        ImageIcon imagenDado = new ImageIcon(Dado.class.getResource(RUTA_RECURSOS + nombreArchivo));
        return imagenDado;
    }

    public ImageIcon obtenerImagenDadoRedimensionada(int ancho, int alto) {
        Image imagenRedimensionada = imagenDado.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }

    public ImageIcon obtenerImagenDadoAleatorioRedimensionado(int ancho, int alto) {
        Random random = new Random();
        int valorAleatorio = random.nextInt(6) + 1;
        String nombreArchivo = valorAleatorio + ".png";
        ImageIcon imagenDadoAleatorio = new ImageIcon(Dado.class.getResource(RUTA_RECURSOS + nombreArchivo));
        Image imagenRedimensionada = imagenDadoAleatorio.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }
}
