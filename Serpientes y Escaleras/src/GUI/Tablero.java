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

    private void configurarSerpientesYEscaleras(int numEscaleras, int numSerpientes) {
        if (numEscaleras <= 0 || numSerpientes <= 0) {
            System.err.println("El número de escaleras y serpientes debe ser positivo.");
            return;
        }

        Random random = new Random();
        int totalCasillas = rows * cols;
        int cantidadUbicaciones = numEscaleras + numSerpientes;

        if (totalCasillas < cantidadUbicaciones) {
            System.err.println("La cantidad de serpientes y escaleras excede el tamaño del tablero.");
            return;
        }

        serpientesInicio = new int[numSerpientes];
        serpientesFin = new int[numSerpientes];
        escalerasInicio = new int[numEscaleras];
        escalerasFin = new int[numEscaleras];

        List<Integer> casillasPosibles = new ArrayList<>();
        for (int i = 2; i < totalCasillas; i++) {
            casillasPosibles.add(i);
        }

        int maxIntentos = 100;

        // Ubicar serpientes
        for (int i = 0; i < numSerpientes; i++) {
            int inicioSerpiente = ubicarUbicacion(random, casillasPosibles);
            serpientesInicio[i] = inicioSerpiente;
            int finSerpiente = generarUbicacionFinalSerpiente(random, inicioSerpiente, casillasPosibles, maxIntentos);
            if (finSerpiente == -1) {
                System.err.println("No se pudo ubicar todas las serpientes y escaleras en el tablero.");
                return;
            }
            serpientesFin[i] = finSerpiente;
        }

        // Ubicar escaleras
        for (int i = 0; i < numEscaleras; i++) {
            int inicioEscalera = ubicarUbicacion(random, casillasPosibles);
            escalerasInicio[i] = inicioEscalera;
            int finEscalera = generarUbicacionFinalEscalera(random, inicioEscalera, casillasPosibles, maxIntentos);
            if (finEscalera == -1) {
                System.err.println("No se pudo ubicar todas las serpientes y escaleras en el tablero.");
                return;
            }
            escalerasFin[i] = finEscalera;
        }
    }

    private int generarUbicacionFinalSerpiente(Random random, int inicioUbicacion, List<Integer> casillasPosibles, int maxIntentos) {
        int intentos = 0;
        int finUbicacion;
        do {
            finUbicacion = random.nextInt(inicioUbicacion - 2) + 1;
            intentos++;
            if (intentos > maxIntentos) {
                return -1;
            }
        } while (!casillasPosibles.contains(finUbicacion));

        casillasPosibles.remove(Integer.valueOf(finUbicacion));
        return finUbicacion;
    }

    private int generarUbicacionFinalEscalera(Random random, int inicioUbicacion, List<Integer> casillasPosibles, int maxIntentos) {
        int intentos = 0;
        int finUbicacion;
        do {
            finUbicacion = random.nextInt(rows * cols - inicioUbicacion) + inicioUbicacion + 1; 
            intentos++;
            if (intentos > maxIntentos) {
                return -1;
            }
        } while (!casillasPosibles.contains(finUbicacion));

        casillasPosibles.remove(Integer.valueOf(finUbicacion));
        return finUbicacion;
    }

    private int ubicarUbicacion(Random random, List<Integer> casillasPosibles) {
        int index = random.nextInt(casillasPosibles.size());
        int ubicacion = casillasPosibles.get(index);
        casillasPosibles.remove(index);
        return ubicacion;
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
