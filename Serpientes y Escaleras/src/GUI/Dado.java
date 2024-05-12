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

    public static int lanzar() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    public static ImageIcon obtenerImagenDado(int resultado) {
        String nombreArchivo = resultado + ".png";
        ImageIcon imagenDado = new ImageIcon(Dado.class.getResource(RUTA_RECURSOS + nombreArchivo));
        return imagenDado;
    }

    public static ImageIcon obtenerImagenDadoRedimensionada(int resultado, int ancho, int alto) {
        ImageIcon imagenDado = obtenerImagenDado(resultado);
        Image imagenRedimensionada = imagenDado.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }
}

