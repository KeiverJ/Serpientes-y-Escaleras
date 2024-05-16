/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author keiver
 */
public class Tablero extends JPanel {

    ImageIcon serpienteInicioIcon = new ImageIcon(getClass().getResource("/resources/serpienteinicio.png"));
    ImageIcon serpienteFinIcon = new ImageIcon(getClass().getResource("/resources/serpientefin.png"));
    ImageIcon escaleraInicioIcon = new ImageIcon(getClass().getResource("/resources/escalerainicio.png"));
    ImageIcon escaleraFinIcon = new ImageIcon(getClass().getResource("/resources/escalerafin.png"));

    private static final int CELL_SIZE = 55;
    public final int rows;
    public final int cols;
    private int[] serpientesInicio;
    private int[] serpientesFin;
    private int[] escalerasInicio;
    private int[] escalerasFin;
    private int jugadorActual;

    private List<Jugador> jugadores;
    public List<EventoJuego> eventosJuego;

    public Tablero(int rows, int cols, List<Jugador> jugadores, int numEscaleras, int numSerpientes) {
        this.rows = rows;
        this.cols = cols;
        this.jugadores = new ArrayList<>();
        this.eventosJuego = new ArrayList<>();
        configurarSerpientesYEscaleras(numEscaleras, numSerpientes);
        jugadorActual = 1;
    }

    public void reiniciarPosicionJugadores() {
        for (Jugador jugador : jugadores) {
            jugador.setPosition(1);
        }
        repaint();
    }

    public void limpiarTablero() {
        Iterator<Jugador> iterator = jugadores.iterator();
        while (iterator.hasNext()) {
            Jugador jugador = iterator.next();
            iterator.remove();
        }
        repaint();
    }

    public boolean hayGanador() {
        for (Jugador jugador : jugadores) {
            if (jugador.getPosition() == rows * cols) {
                return true;
            }
        }
        return false;
    }

    public void limpiarImagenesSerpientesYEscaleras(JPanel panelTablero) {
        panelTablero.removeAll();
        panelTablero.revalidate();
        panelTablero.repaint();
    }

    private void configurarSerpientesYEscaleras(int numEscaleras, int numSerpientes) {
        boolean generacionExitosa = false;
        int maxIntentos = 10;
        int intentosRealizados = 0;

        while (!generacionExitosa && intentosRealizados < maxIntentos) {
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

            int maxIntentosUbicacion = 10000;
            generacionExitosa = true;

            for (int i = 0; i < numSerpientes && generacionExitosa; i++) {
                int inicioSerpiente = ubicarfichasSE(random, casillasPosibles);
                serpientesInicio[i] = inicioSerpiente;
                int finSerpiente = generarUbicacionFinalSerpiente(random, inicioSerpiente, casillasPosibles, maxIntentosUbicacion);
                if (finSerpiente == -1) {
                    generacionExitosa = false;
                    break;
                }
                serpientesFin[i] = finSerpiente;
            }

            if (generacionExitosa) {
                for (int i = 0; i < numEscaleras && generacionExitosa; i++) {
                    int inicioEscalera = ubicarfichasSE(random, casillasPosibles);
                    escalerasInicio[i] = inicioEscalera;
                    int finEscalera = generarUbicacionFinalEscalera(random, inicioEscalera, casillasPosibles, maxIntentosUbicacion);
                    if (finEscalera == -1) {
                        generacionExitosa = false;
                        break;
                    }
                    escalerasFin[i] = finEscalera;
                }
            }

            intentosRealizados++;
        }

        if (!generacionExitosa) {
            System.err.println("No se pudo ubicar todas las serpientes y escaleras en el tablero después de " + maxIntentos + " intentos.");
        }
    }

    private int generarUbicacionFinalSerpiente(Random random, int inicioUbicacion, List<Integer> casillasPosibles, int maxIntentos) {
        int intentos = 0;
        int finUbicacion = -1;

        do {
            try {
                finUbicacion = random.nextInt(Math.max(1, inicioUbicacion - 1)) + 1;
            } catch (IllegalArgumentException e) {
                if (intentos >= maxIntentos) {
                    return -1;
                }
                intentos++;
                continue;
            }

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

    private int ubicarfichasSE(Random random, List<Integer> casillasPosibles) {
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
        dibujarSerpientesYEscaleras(g);
        dibujarFicha(g);
    }

    private void dibujarFicha(Graphics g) {
        double CELL_SIZE_X = (double) getWidth() / cols;
        double CELL_SIZE_Y = (double) getHeight() / rows;

        for (Jugador jugador : jugadores) {
            int position = jugador.getPosition() - 1;
            int row, col;

            if (position / cols % 2 == 0) {
                row = position / cols;
                col = position % cols;
            } else {
                row = position / cols;
                col = cols - 1 - (position % cols);
            }

            int x = (int) (col * CELL_SIZE_X + CELL_SIZE_X / 4);
            int y = (int) ((rows - row - 1) * CELL_SIZE_Y + CELL_SIZE_Y / 4);

            ImageIcon jugadorIcon = jugador.getFichaIcon();
            Image img = jugadorIcon.getImage();

            g.drawImage(img, x, y, CELL_SIZE / 2, CELL_SIZE / 2, this);
        }
    }

    private void dibujarSerpientesYEscaleras(Graphics g) {
        double CELL_SIZE_X = (double) getWidth() / cols;
        double CELL_SIZE_Y = (double) getHeight() / rows;
        double iconSize = Math.min(CELL_SIZE_X * 0.8, CELL_SIZE_Y * 0.8);

        for (int i = 0; i < serpientesInicio.length; i++) {
            int row = (serpientesInicio[i] - 1) / cols;
            int col = (serpientesInicio[i] - 1) % cols;

            int x, y;
            if (row % 2 == 0) {
                x = (int) ((col * CELL_SIZE_X) + (CELL_SIZE_X / 4));
                y = (int) (((rows - row - 1) * CELL_SIZE_Y) + (CELL_SIZE_Y / 4));
            } else {
                x = (int) (((cols - col - 1) * CELL_SIZE_X) + (CELL_SIZE_X / 4));
                y = (int) (((rows - row - 1) * CELL_SIZE_Y) + (CELL_SIZE_Y / 4));
            }

            g.drawImage(serpienteInicioIcon.getImage(), x, y, (int) (CELL_SIZE_X / 2), (int) (CELL_SIZE_Y / 2), this);
        }

        for (int i = 0; i < serpientesFin.length; i++) {
            int row = (serpientesFin[i] - 1) / cols;
            int col = (serpientesFin[i] - 1) % cols;

            int x, y;
            if (row % 2 == 0) {
                x = (int) ((col * CELL_SIZE_X) + (CELL_SIZE_X / 4));
                y = (int) (((rows - row - 1) * CELL_SIZE_Y) + (CELL_SIZE_Y / 4));
            } else {
                x = (int) (((cols - col - 1) * CELL_SIZE_X) + (CELL_SIZE_X / 4));
                y = (int) (((rows - row - 1) * CELL_SIZE_Y) + (CELL_SIZE_Y / 4));
            }

            g.drawImage(serpienteFinIcon.getImage(), x, y, (int) iconSize, (int) iconSize, this);
        }

        for (int i = 0; i < escalerasInicio.length; i++) {
            int row = (escalerasInicio[i] - 1) / cols;
            int col = (escalerasInicio[i] - 1) % cols;

            int x, y;
            if (row % 2 == 0) {
                x = (int) ((col * CELL_SIZE_X) + (CELL_SIZE_X / 4));
                y = (int) (((rows - row - 1) * CELL_SIZE_Y) + (CELL_SIZE_Y / 4));
            } else {
                x = (int) (((cols - col - 1) * CELL_SIZE_X) + (CELL_SIZE_X / 4));
                y = (int) (((rows - row - 1) * CELL_SIZE_Y) + (CELL_SIZE_Y / 4));
            }

            g.drawImage(escaleraInicioIcon.getImage(), x, y, (int) (CELL_SIZE_X / 2), (int) (CELL_SIZE_Y / 2), this);
        }

        for (int i = 0; i < escalerasFin.length; i++) {
            int row = (escalerasFin[i] - 1) / cols;
            int col = (escalerasFin[i] - 1) % cols;

            int x, y;
            if (row % 2 == 0) {
                x = (int) ((col * CELL_SIZE_X) + (CELL_SIZE_X / 4));
                y = (int) (((rows - row - 1) * CELL_SIZE_Y) + (CELL_SIZE_Y / 4));
            } else {
                x = (int) (((cols - col - 1) * CELL_SIZE_X) + (CELL_SIZE_X / 4));
                y = (int) (((rows - row - 1) * CELL_SIZE_Y) + (CELL_SIZE_Y / 4));
            }

            g.drawImage(escaleraFinIcon.getImage(), x, y, (int) (CELL_SIZE_X / 2), (int) (CELL_SIZE_Y / 2), this);
        }
    }

    private void crearTablero(Graphics g) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        double CELL_SIZE_X = (double) panelWidth / cols;
        double CELL_SIZE_Y = (double) panelHeight / rows;
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, panelWidth, panelHeight);

        for (int row = 0; row < rows; row++) {
            if (row % 2 == 0) {
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
                        cellColor = new Color(220, 220, 255);
                    } else if (isEscaleraStart && isEscaleraEnd) {
                        cellColor = new Color(255, 245, 220);
                    } else if (isSerpienteStart || isSerpienteEnd) {
                        cellColor = new Color(220, 220, 255);
                    } else if (isEscaleraStart || isEscaleraEnd) {
                        cellColor = new Color(255, 245, 220);
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
            } else {
                for (int col = cols - 1; col >= 0; col--) {
                    int x = (int) (col * CELL_SIZE_X);
                    int y = (int) ((rows - row - 1) * CELL_SIZE_Y);
                    int cellValue = row * cols + (cols - col);

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
                        cellColor = new Color(220, 220, 255);
                    } else if (isEscaleraStart && isEscaleraEnd) {
                        cellColor = new Color(255, 245, 220);
                    } else if (isSerpienteStart || isSerpienteEnd) {
                        cellColor = new Color(220, 220, 255);
                    } else if (isEscaleraStart || isEscaleraEnd) {
                        cellColor = new Color(255, 245, 220);
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
        }

    }

    public void moverJugador(Jugador jugador, int movimiento) {
        int nuevaPosicion = jugador.getPosition() + movimiento;
        if (nuevaPosicion == rows * cols) {
            PanelTablero_SerpientesyEscaleras panelTablero = (PanelTablero_SerpientesyEscaleras) SwingUtilities.getWindowAncestor(this);
            panelTablero.jugadorGanadorIndex = jugadores.indexOf(jugador);
        } else {
            FichaAnimada animacion = new FichaAnimada(jugador, movimiento, this, 300);
            animacion.start();

            int jugadorActualIndex = jugadores.indexOf(jugador);
            int siguienteJugadorIndex = (jugadorActualIndex + 1) % jugadores.size();

            animacion.setOnFinishListener(new FichaAnimada.OnFinishListener() {
                @Override
                public void onFinish() {
                    PanelTablero_SerpientesyEscaleras panelTablero = (PanelTablero_SerpientesyEscaleras) SwingUtilities.getWindowAncestor(Tablero.this);
                    panelTablero.bordeTurnoJugador(siguienteJugadorIndex);
                    panelTablero.jugadorActualIndex = siguienteJugadorIndex;
                    panelTablero.dadoLanzado = false;
                    panelTablero.lblLanzarDado.setEnabled(true);
                }
            });
        }
    }

    public boolean isSerpienteInicio(int pos) {
        return contains(serpientesInicio, pos);
    }

    public int getSerpienteFin(int pos) {
        for (int i = 0; i < serpientesInicio.length; i++) {
            if (serpientesInicio[i] == pos) {
                return serpientesFin[i];
            }
        }
        return pos;
    }

    public boolean isEscaleraInicio(int pos) {
        return contains(escalerasInicio, pos);
    }

    public int getEscaleraFin(int pos) {
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

    public List<EventoJuego> getEventosJuego() {
        return eventosJuego;
    }
}
