package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tablero extends JPanel {

    private static final int CELL_SIZE = 55;
    private final int rows;
    private final int cols;
    private int[] serpientesInicio;
    private int[] serpientesFin;
    private int[] escalerasInicio;
    private int[] escalerasFin;
    private int jugadorActual;

    private List<Jugador> jugadores;

    public Tablero(int rows, int cols, List<Jugador> jugadores, int numEscaleras, int numSerpientes) {
        this.rows = rows;
        this.cols = cols;
        this.jugadores = new ArrayList<>();
        configurarSerpientesYEscaleras(numEscaleras, numSerpientes);
        jugadorActual = 1;
    }

    private List<int[][]> generarCombinacionesUbicaciones(int numEscaleras, int numSerpientes) {
        List<int[][]> combinaciones = new ArrayList<>();
        int totalCasillas = rows * cols;
        int[] casillasPosibles = new int[totalCasillas - 1];
        for (int i = 0; i < casillasPosibles.length; i++) {
            casillasPosibles[i] = i + 2;
        }

        generarCombinacionesRecursivas(combinaciones, new int[numEscaleras][2], new int[numSerpientes][2], casillasPosibles, 0, 0, 0);
        return combinaciones;
    }

    private void generarCombinacionesRecursivas(List<int[][]> combinaciones, int[][] escalerasUbicaciones, int[][] serpientesUbicaciones, int[] casillasPosibles, int indiceEscalera, int indiceSerpiente, int indiceArray) {
        if (indiceEscalera == escalerasUbicaciones.length && indiceSerpiente == serpientesUbicaciones.length) {
            int[][] ubicaciones = new int[escalerasUbicaciones.length + serpientesUbicaciones.length][2];
            System.arraycopy(escalerasUbicaciones, 0, ubicaciones, 0, escalerasUbicaciones.length);
            System.arraycopy(serpientesUbicaciones, 0, ubicaciones, escalerasUbicaciones.length, serpientesUbicaciones.length);
            combinaciones.add(ubicaciones);
            return;
        }

        for (int i = indiceArray; i < casillasPosibles.length; i++) {
            int casilla = casillasPosibles[i];
            if (indiceEscalera < escalerasUbicaciones.length) {
                escalerasUbicaciones[indiceEscalera][0] = casilla;
                generarCombinacionesRecursivas(combinaciones, escalerasUbicaciones, serpientesUbicaciones, casillasPosibles, indiceEscalera + 1, indiceSerpiente, i + 1);
            } else {
                serpientesUbicaciones[indiceSerpiente][0] = casilla;
                generarCombinacionesRecursivas(combinaciones, escalerasUbicaciones, serpientesUbicaciones, casillasPosibles, indiceEscalera, indiceSerpiente + 1, i + 1);
            }
        }
    }

    private void configurarSerpientesYEscaleras(int numEscaleras, int numSerpientes) {
        if (numEscaleras <= 0 || numSerpientes <= 0) {
            System.err.println("El número de escaleras y serpientes debe ser positivo.");
            return;
        }

        int totalCasillas = rows * cols;
        int cantidadUbicaciones = numEscaleras + numSerpientes;

        if (totalCasillas < cantidadUbicaciones) {
            System.err.println("La cantidad de serpientes y escaleras excede el tamaño del tablero.");
            return;
        }

        List<int[][]> combinacionesUbicaciones = generarCombinacionesUbicaciones(numEscaleras, numSerpientes);
        Random random = new Random();
        int[][] ubicacionesValidas = null;

        for (int[][] ubicaciones : combinacionesUbicaciones) {
            if (sonUbicacionesValidas(ubicaciones, numEscaleras, numSerpientes)) {
                ubicacionesValidas = ubicaciones;
                break;
            }
        }

        if (ubicacionesValidas == null) {
            System.err.println("No se encontraron ubicaciones válidas para las serpientes y escaleras.");
            return;
        }

        serpientesInicio = new int[numSerpientes];
        serpientesFin = new int[numSerpientes];
        escalerasInicio = new int[numEscaleras];
        escalerasFin = new int[numEscaleras];

        for (int i = 0; i < numEscaleras; i++) {
            escalerasInicio[i] = ubicacionesValidas[i][0];
            escalerasFin[i] = generarUbicacionFinalEscalera(random, escalerasInicio[i], ubicacionesValidas, i, numEscaleras, numSerpientes);
        }

        for (int i = 0; i < numSerpientes; i++) {
            serpientesInicio[i] = ubicacionesValidas[numEscaleras + i][0];
            serpientesFin[i] = generarUbicacionFinalSerpiente(random, serpientesInicio[i], ubicacionesValidas, numEscaleras, numSerpientes);
        }
    }

    private boolean sonUbicacionesValidas(int[][] ubicaciones, int numEscaleras, int numSerpientes) {
        int[] escalerasInicio = new int[numEscaleras];
        int[] escalerasFin = new int[numEscaleras];
        int[] serpientesInicio = new int[numSerpientes];
        int[] serpientesFin = new int[numSerpientes];

        for (int i = 0; i < numEscaleras; i++) {
            escalerasInicio[i] = ubicaciones[i][0];
        }

        for (int i = 0; i < numSerpientes; i++) {
            serpientesInicio[i] = ubicaciones[numEscaleras + i][0];
        }

        for (int i = 0; i < numEscaleras; i++) {
            escalerasFin[i] = generarUbicacionFinalEscalera(new Random(), escalerasInicio[i], ubicaciones, i, numEscaleras, numSerpientes);
            if (escalerasFin[i] == -1) {
                return false;
            }
        }

        for (int i = 0; i < numSerpientes; i++) {
            serpientesFin[i] = generarUbicacionFinalSerpiente(new Random(), serpientesInicio[i], ubicaciones, numEscaleras, numSerpientes);
            if (serpientesFin[i] == -1) {
                return false;
            }
        }

        return true;
    }

    private int generarUbicacionFinalEscalera(Random random, int inicioUbicacion, int[][] ubicaciones, int indiceEscalera, int numEscaleras, int numSerpientes) {
        List<Integer> casillasPosibles = new ArrayList<>();
        for (int i = inicioUbicacion + 1; i <= rows * cols; i++) {
            boolean estaOcupada = false;
            for (int j = 0; j < numEscaleras + numSerpientes; j++) {
                if (ubicaciones[j][0] == i || (j < numEscaleras && ubicaciones[j][1] == i) || (j >= numEscaleras && j - numEscaleras < indiceEscalera && ubicaciones[j][1] == i)) {
                    estaOcupada = true;
                    break;
                }
            }
            if (!estaOcupada) {
                casillasPosibles.add(i);
            }
        }

        if (casillasPosibles.isEmpty()) {
            return -1;
        }

        int index = random.nextInt(casillasPosibles.size());
        return casillasPosibles.get(index);
    }

    private int generarUbicacionFinalSerpiente(Random random, int inicioUbicacion, int[][] ubicaciones, int numEscaleras, int numSerpientes) {
        List<Integer> casillasPosibles = new ArrayList<>();
        for (int i = 1; i < inicioUbicacion; i++) {
            boolean estaOcupada = false;
            for (int j = 0; j < numEscaleras + numSerpientes; j++) {
                if (ubicaciones[j][0] == i || (j >= numEscaleras && ubicaciones[j][1] == i)) {
                    estaOcupada = true;
                    break;
                }
            }
            if (!estaOcupada) {
                casillasPosibles.add(i);
            }
        }

        if (casillasPosibles.isEmpty()) {
            return -1;
        }

        int index = random.nextInt(casillasPosibles.size());
        return casillasPosibles.get(index);
    }

    public String obtenerUbicacionesSerpientesYEscaleras() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ubicaciones de las serpientes:\n");
        for (int i = 0; i < serpientesInicio.length; i++) {
            sb.append("Serpiente ").append(i + 1).append(": ").append(serpientesInicio[i]).append(" -> ").append(serpientesFin[i]).append("\n");
        }

        sb.append("\nUbicaciones de las escaleras:\n");
        for (int i = 0; i < escalerasInicio.length; i++) {
            sb.append("Escalera ").append(i + 1).append(": ").append(escalerasInicio[i]).append(" -> ").append(escalerasFin[i]).append("\n");
        }

        return sb.toString();
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
            int position = jugador.getPosition() - 1;
            int row = (rows - 1) - position / cols;
            int col = position % cols;
            int x = (int) (col * CELL_SIZE_X + CELL_SIZE_X / 4);
            int y = (int) (row * CELL_SIZE_Y + CELL_SIZE_Y / 4);

            ImageIcon jugadorIcon = jugador.getFichaIcon();
            Image img = jugadorIcon.getImage();

            g.drawImage(img, x, y, CELL_SIZE / 2, CELL_SIZE / 2, this);
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
            JOptionPane.showMessageDialog(this, "¡Felicidades! El jugador " + jugador.getNombre() + " ha ganado el juego.");
        }

        repaint();
    }

    private boolean isSerpienteInicio(int pos) {
        return contains(serpientesInicio, pos);
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
        return contains(escalerasInicio, pos);
    }

    private int getEscaleraFin(int pos) {
        for (int i = 0; i < escalerasInicio.length; i++) {
            if (escalerasInicio[i] == pos) {
                return escalerasFin[i];
            }
        }
        return pos;
    }

    private boolean contains(int[] array, int value) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void removerJugador(Jugador jugador) {
        jugadores.remove(jugador);
    }
}
