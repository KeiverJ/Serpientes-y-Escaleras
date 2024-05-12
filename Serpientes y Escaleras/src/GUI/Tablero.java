package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tablero extends JPanel {

    private static final int BOARD_SIZE = 10;
    private static final int CELL_SIZE = 55;
    private Jugador jugador;
    private final int rows;
    private final int cols;
    private int[] serpientesInicio;
    private int[] serpientesFin;
    private int[] escalerasInicio;
    private int[] escalerasFin;
    private int jugadorActual;

    private List<Jugador> jugadores;

    public Tablero(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        jugadores = new ArrayList<>();
        configurarSerpientesYEscaleras();
        jugadorActual = 0;

    }

    private void configurarSerpientesYEscaleras() {
        if (rows == 10 && cols == 10) {
            serpientesInicio = new int[]{11, 42, 67, 73};
            serpientesFin = new int[]{7, 38, 60, 91};
            escalerasInicio = new int[]{2, 7, 8, 15, 21, 28, 36, 51, 71, 78, 87};
            escalerasFin = new int[]{39, 14, 31, 26, 42, 84, 44, 67, 91, 98, 94};
        } else if (rows == 13 && cols == 13) {
            serpientesInicio = new int[]{4, 23, 35, 47, 66, 81};
            serpientesFin = new int[]{1, 18, 27, 40, 52, 75};
            escalerasInicio = new int[]{5, 9, 14, 20, 28, 35, 43, 46, 50, 70, 73};
            escalerasFin = new int[]{11, 31, 34, 39, 42, 50, 69, 78, 91, 98, 99};
        } else if (rows == 15 && cols == 15) {
            serpientesInicio = new int[]{6, 22, 33, 42, 57, 66, 79, 92, 98};
            serpientesFin = new int[]{1, 10, 23, 28, 38, 53, 63, 84, 94};
            escalerasInicio = new int[]{2, 6, 13, 17, 22, 33, 37, 38, 43, 52, 56, 61, 68, 74, 82};
            escalerasFin = new int[]{38, 10, 23, 28, 41, 49, 54, 49, 57, 77, 63, 79, 79, 92, 94};
        } else {
            serpientesInicio = new int[0];
            serpientesFin = new int[0];
            escalerasInicio = new int[0];
            escalerasFin = new int[0];
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        crearTablero(g);
    }

    private void crearTablero(Graphics g) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        double CELL_SIZE_X = (double) panelWidth / cols;
        double CELL_SIZE_Y = (double) panelHeight / rows;

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, panelWidth, panelHeight);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = (int) (col * CELL_SIZE_X);
                int y = (int) ((rows - row - 1) * CELL_SIZE_Y);
                int cellValue = row * cols + col + 1;

                Color cellColor = Color.WHITE;
                Color textColor = Color.BLACK;

                boolean isSerpienteStart = false;
                boolean isSerpienteEnd = false;
                boolean isEscaleraStart = false;
                boolean isEscaleraEnd = false;

                for (int i = 0; i < serpientesInicio.length; i++) {
                    if (cellValue == serpientesInicio[i]) {
                        isSerpienteStart = true;
                    }
                    if (cellValue == serpientesFin[i]) {
                        isSerpienteEnd = true;
                    }
                }

                for (int i = 0; i < escalerasInicio.length; i++) {
                    if (cellValue == escalerasInicio[i]) {
                        isEscaleraStart = true;
                    }
                    if (cellValue == escalerasFin[i]) {
                        isEscaleraEnd = true;
                    }
                }

                if (isSerpienteStart && isSerpienteEnd) {
                    cellColor = Color.RED;
                } else if (isEscaleraStart && isEscaleraEnd) {
                    cellColor = Color.GREEN;
                } else if (isSerpienteStart || isSerpienteEnd) {
                    cellColor = Color.RED;
                } else if (isEscaleraStart || isEscaleraEnd) {
                    cellColor = Color.GREEN;
                }

                g.setColor(cellColor);
                g.fillRect(x, y, (int) CELL_SIZE_X, (int) CELL_SIZE_Y);
                g.setColor(Color.GRAY);
                g.drawRect(x, y, (int) CELL_SIZE_X, (int) CELL_SIZE_Y);

                if (cellColor.equals(Color.BLACK)) {
                    textColor = Color.WHITE;
                }

                g.setColor(textColor);
                g.drawString(String.valueOf(cellValue), x + (int) (CELL_SIZE_X / 4), y + (int) (3 * CELL_SIZE_Y / 4));
            }
        }

        for (Jugador jugador : jugadores) {
            ImageIcon icono = jugador.getFichaIcon();
            if (icono != null) {
                int position = jugador.getPosition() - 1;
                int row = (rows - 1) - position / cols;
                int col = position % cols;
                int x = (int) (col * CELL_SIZE_X + CELL_SIZE_X / 4);
                int y = (int) (row * CELL_SIZE_Y + CELL_SIZE_Y / 4);

                Image img = icono.getImage();
                g.drawImage(img, x, y, CELL_SIZE / 2, CELL_SIZE / 2, null);
            }
        }
    }

    public void moverJugador(Jugador jugador, int movimiento) {
        int viejaPos = jugador.getPosition();
        int nuevaPos = viejaPos + movimiento;

        if (nuevaPos > rows * cols) {
            nuevaPos = viejaPos;
        } else if (isSerpienteInicio(nuevaPos)) {
            nuevaPos = getSerpienteFin(nuevaPos);
            JOptionPane.showMessageDialog(this, "¡Oh no! El jugador cayó en una serpiente y retrocedió a la posición ");
        } else if (isEscaleraInicio(nuevaPos)) {
            nuevaPos = getEscaleraFin(nuevaPos);
            JOptionPane.showMessageDialog(this, "¡Bien! El jugador encontró una escalera y avanzó a la posición ");
        }

        jugador.setPosition(nuevaPos);

        if (nuevaPos == rows * cols) {

        }

        repaint();
    }

    private boolean isSerpienteInicio(int pos) {
        for (int i : serpientesInicio) {
            if (i == pos) {
                return true;
            }
        }
        return false;
    }

    private int getSerpienteFin(int pos) {
        for (int i = 0; i < serpientesInicio.length; i++) {
            if (serpientesInicio[i] == pos) {
                return serpientesFin[i];
            }
        }
        return pos;
    }

    private boolean isEscaleraInicio(int pos) {
        for (int i : escalerasInicio) {
            if (i == pos) {
                return true;
            }
        }
        return false;
    }

    private int getEscaleraFin(int pos) {
        for (int i = 0; i < escalerasInicio.length; i++) {
            if (escalerasInicio[i] == pos) {
                return escalerasFin[i];
            }
        }
        return pos;
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void removerJugador(Jugador jugador) {
        jugadores.remove(jugador);
    }

}
