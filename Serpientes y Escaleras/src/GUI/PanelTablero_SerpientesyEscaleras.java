/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author keiver
 */
public class PanelTablero_SerpientesyEscaleras extends javax.swing.JFrame {

    public List<Jugador> jugadores;
    private int jugadorActual;
    private int numSerpientes;
    private int numEscaleras;
    public int jugadorActualIndex = 0;
    private Tablero tableroActual;
    private int tableroAnterior = 0;
    public boolean dadoLanzado = false;
    public int jugadorGanadorIndex = -1;

    int tamañoTableroActual;

    Tablero tablero10x10;
    Tablero tablero13x13;
    Tablero tablero15x15;

    public PanelTablero_SerpientesyEscaleras(List<Jugador> jugadores, List<Integer> posicionesJugadores, int tamañoTablero, int numEscaleras, int numSerpientes) {
        this.jugadores = jugadores;
        this.numEscaleras = numEscaleras;
        this.numSerpientes = numSerpientes;
        jugadorActual = 0;
        setUndecorated(true);
        initComponents();
        init(tamañoTablero, numEscaleras, numSerpientes);
        setResizable(false);
        setLocationRelativeTo(null);
        panelFondo.setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));

        tamañoTableroActual = tamañoTablero;

        asignarNombres(jugadores);
        bordeTurnoJugador(0);


    }

    private void asignarNombres(List<Jugador> jugadores) {
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            ImageIcon fichaTablero = jugador.getFichaTableroIcon();

            switch (i) {
                case 0:
                    lblNombreJ1.setText(jugador.getNombre());
                    Ficha1.setIcon(fichaTablero);
                    break;
                case 1:
                    lblNombreJ2.setText(jugador.getNombre());
                    Ficha2.setIcon(fichaTablero);
                    break;
                case 2:
                    lblNombreJ3.setText(jugador.getNombre());
                    Ficha3.setIcon(fichaTablero);
                    break;
                case 3:
                    lblNombreJ4.setText(jugador.getNombre());
                    Ficha4.setIcon(fichaTablero);
                    break;
                default:
                    break;
            }
        }
    }


    private void init(int tamañoTablero, int numEscaleras, int numSerpientes) {
        limpiarTableroAnterior(tamañoTablero);
        inicializarNuevoTablero(tamañoTablero, numEscaleras, numSerpientes);
    }

    private void limpiarTableroAnterior(int nuevoTamañoTablero) {
        switch (tamañoTableroActual) {
            case 10:
                if (tablero10x10 != null) {
                    tablero10x10.limpiarImagenesSerpientesYEscaleras(jPanel10x10);
                }
                break;
            case 13:
                if (tablero13x13 != null) {
                    tablero13x13.limpiarImagenesSerpientesYEscaleras(jPanel13x13);
                }
                break;
            case 15:
                if (tablero15x15 != null) {
                    tablero15x15.limpiarImagenesSerpientesYEscaleras(jPanel15x15);
                }
                break;
        }
    }

    private void inicializarNuevoTablero(int tamañoTablero, int numEscaleras, int numSerpientes) {
        switch (tamañoTablero) {
            case 10:
                jTabbedPane1.setSelectedIndex(0);
                jPanel10x10.setLayout(new BorderLayout());
                tablero10x10 = new Tablero(10, 10, jugadores, numEscaleras, numSerpientes);
                jPanel10x10.add(tablero10x10, BorderLayout.CENTER);
                tablero10x10.revalidate();
                tablero10x10.repaint();
                rbt10x10.setEnabled(false);
                rbt13x13.setEnabled(true);
                rbt15x15.setEnabled(true);
                configurarJugadores(tablero10x10);
                tableroActual = tablero10x10;
                tamañoTableroActual = 10;
                break;
            case 13:
                jTabbedPane1.setSelectedIndex(1);
                jPanel13x13.setLayout(new BorderLayout());
                tablero13x13 = new Tablero(13, 13, jugadores, numEscaleras, numSerpientes);
                jPanel13x13.add(tablero13x13, BorderLayout.CENTER);
                tablero13x13.revalidate();
                tablero13x13.repaint();
                rbt10x10.setEnabled(true);
                rbt13x13.setEnabled(false);
                rbt15x15.setEnabled(true);
                configurarJugadores(tablero13x13);
                tableroActual = tablero13x13;
                tamañoTableroActual = 13;
                break;
            case 15:
                jTabbedPane1.setSelectedIndex(2);
                jPanel15x15.setLayout(new BorderLayout());
                tablero15x15 = new Tablero(15, 15, jugadores, numEscaleras, numSerpientes);
                jPanel15x15.add(tablero15x15, BorderLayout.CENTER);
                tablero15x15.revalidate();
                tablero15x15.repaint();
                rbt10x10.setEnabled(true);
                rbt13x13.setEnabled(true);
                rbt15x15.setEnabled(false);
                configurarJugadores(tablero15x15);
                tableroActual = tablero15x15;
                tamañoTableroActual = 15;
                break;
        }

        String ubicaciones = tableroActual.obtenerUbicacionesSerpientesYEscaleras();
        txtASerpientesyEscaleras.setText(ubicaciones);
    }

    private void configurarJugadores(Tablero tablero) {
        for (Jugador jugador : jugadores) {
            tablero.agregarJugador(jugador);
            jugador.setPosition(jugador.getPosition());
        }
    }

    private boolean validarCantidadSerpientesEscaleras(int tamañoTablero, int numSerpientes, int numEscaleras) {
        switch (tamañoTablero) {
            case 10:
                if (numSerpientes <= 20 && numEscaleras <= 20 && numEscaleras > 0 && numSerpientes > 0) {
                    return true;
                }
                JOptionPane.showMessageDialog(this, "El número máximo de serpientes y escaleras para un tablero de 10x10 debe ser menor o igual que 20 y mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case 13:
                if (numSerpientes <= 35 && numEscaleras <= 35 && numEscaleras > 0 && numSerpientes > 0) {
                    return true;
                }
                JOptionPane.showMessageDialog(this, "El número máximo de serpientes y escaleras para un tablero de 13x13 debe ser menor o igual que 35 y mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case 15:
                if (numSerpientes <= 40 && numEscaleras <= 40 && numEscaleras > 0 && numSerpientes > 0) {
                    return true;
                }
                JOptionPane.showMessageDialog(this, "El número máximo de serpientes y escaleras para un tablero de 15x15 debe ser menor o igual que 40 y mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
        return false;
    }

    private int obtenerTamañoTablero() {
        if (rbt10x10.isSelected()) {
            return 10;
        } else if (rbt13x13.isSelected()) {
            return 13;
        } else if (rbt15x15.isSelected()) {
            return 15;
        }
        return 0;
    }

    private void init2(int tamañoTablero, int numEscaleras, int numSerpientes) {
        switch (tamañoTablero) {
            case 10:
                init(10, numEscaleras, numSerpientes);
                tablero10x10.reiniciarPosicionJugadores();
                jugadorActualIndex = 0;
                bordeTurnoJugador(jugadorActualIndex);
                break;
            case 13:
                init(13, numEscaleras, numEscaleras);
                tablero13x13.reiniciarPosicionJugadores();
                jugadorActualIndex = 0;
                bordeTurnoJugador(jugadorActualIndex);
                break;
            case 15:
                init(15, numEscaleras, numSerpientes);
                tablero15x15.reiniciarPosicionJugadores();
                jugadorActualIndex = 0;
                bordeTurnoJugador(jugadorActualIndex);
                break;
        }
    }

    public void bordeTurnoJugador(int jugadorActualIndex) {
        lblNombreJ1.setBorder(null);
        lblNombreJ2.setBorder(null);
        lblNombreJ3.setBorder(null);
        lblNombreJ4.setBorder(null);

        Jugador jugadorActual = jugadores.get(jugadorActualIndex);

        switch (jugadorActualIndex) {
            case 0:
                lblNombreJ1.setBorder(BorderFactory.createLineBorder(new Color(220, 20, 60), 2));
                break;
            case 1:
                lblNombreJ2.setBorder(BorderFactory.createLineBorder(new Color(65, 105, 225), 2));
                break;
            case 2:
                lblNombreJ3.setBorder(BorderFactory.createLineBorder(new Color(80, 200, 120), 2));
                break;
            case 3:
                lblNombreJ4.setBorder(BorderFactory.createLineBorder(new Color(204, 153, 255), 2));
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngTamaño = new javax.swing.ButtonGroup();
        panelFondo = new modelo.PanelRedondeado();
        lblCierre = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNombreJ1 = new javax.swing.JLabel();
        lblNombreJ2 = new javax.swing.JLabel();
        lblNombreJ3 = new javax.swing.JLabel();
        lblNombreJ4 = new javax.swing.JLabel();
        Ficha4 = new javax.swing.JLabel();
        Ficha1 = new javax.swing.JLabel();
        Ficha2 = new javax.swing.JLabel();
        Ficha3 = new javax.swing.JLabel();
        panelBoton1 = new javax.swing.JPanel();
        lblTerminarPartida = new javax.swing.JLabel();
        panelBoton = new javax.swing.JPanel();
        lblCambiarTablero = new javax.swing.JLabel();
        rbt15x15 = new javax.swing.JRadioButton();
        rbt10x10 = new javax.swing.JRadioButton();
        rbt13x13 = new javax.swing.JRadioButton();
        panelBoton3 = new javax.swing.JPanel();
        lblLanzarDado = new javax.swing.JLabel();
        lblDado = new javax.swing.JLabel();
        panelBotonH = new javax.swing.JPanel();
        lblHistorial = new javax.swing.JLabel();
        jpanelReiniciar = new javax.swing.JPanel();
        lblReiniciar = new javax.swing.JLabel();
        lblTurno = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4x4 = new javax.swing.JPanel();
        jPanel10x10 = new javax.swing.JPanel();
        jPanel5x5 = new javax.swing.JPanel();
        jPanel13x13 = new javax.swing.JPanel();
        jPanel3x3 = new javax.swing.JPanel();
        jPanel15x15 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtASerpientesyEscaleras = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelFondo.setBackground(new java.awt.Color(240, 232, 216));
        panelFondo.setPreferredSize(new java.awt.Dimension(400, 500));
        panelFondo.setLayout(null);

        lblCierre.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        lblCierre.setForeground(new java.awt.Color(0, 0, 0));
        lblCierre.setText("X");
        lblCierre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCierre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCierreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCierreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCierreMouseExited(evt);
            }
        });
        panelFondo.add(lblCierre);
        lblCierre.setBounds(1320, 0, 16, 32);

        jPanel2.setBackground(new java.awt.Color(240, 232, 216));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreJ1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblNombreJ1.setForeground(new java.awt.Color(220, 20, 60));
        lblNombreJ1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lblNombreJ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 100, 40));

        lblNombreJ2.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblNombreJ2.setForeground(new java.awt.Color(65, 105, 225));
        lblNombreJ2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lblNombreJ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 90, 40));

        lblNombreJ3.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblNombreJ3.setForeground(new java.awt.Color(80, 200, 120));
        lblNombreJ3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lblNombreJ3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 120, 40));

        lblNombreJ4.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblNombreJ4.setForeground(new java.awt.Color(204, 153, 255));
        lblNombreJ4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreJ4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lblNombreJ4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 120, 40));
        jPanel2.add(Ficha4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 60, 70));
        jPanel2.add(Ficha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 60, 70));
        jPanel2.add(Ficha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 60, 70));

        Ficha3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(Ficha3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 60, 70));

        panelBoton1.setBackground(new java.awt.Color(216, 195, 165));

        lblTerminarPartida.setBackground(new java.awt.Color(255, 255, 255));
        lblTerminarPartida.setFont(new java.awt.Font("Montserrat", 1, 20)); // NOI18N
        lblTerminarPartida.setForeground(new java.awt.Color(0, 0, 0));
        lblTerminarPartida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTerminarPartida.setText("Terminar partida");
        lblTerminarPartida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTerminarPartida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTerminarPartidaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTerminarPartidaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblTerminarPartidaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelBoton1Layout = new javax.swing.GroupLayout(panelBoton1);
        panelBoton1.setLayout(panelBoton1Layout);
        panelBoton1Layout.setHorizontalGroup(
            panelBoton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTerminarPartida, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );
        panelBoton1Layout.setVerticalGroup(
            panelBoton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoton1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTerminarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(panelBoton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 630, 166, 50));

        panelBoton.setBackground(new java.awt.Color(140, 211, 179));

        lblCambiarTablero.setBackground(new java.awt.Color(255, 255, 255));
        lblCambiarTablero.setFont(new java.awt.Font("Montserrat", 1, 20)); // NOI18N
        lblCambiarTablero.setForeground(new java.awt.Color(0, 0, 0));
        lblCambiarTablero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCambiarTablero.setText("Cambiar tablero");
        lblCambiarTablero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCambiarTablero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCambiarTableroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCambiarTableroMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblCambiarTableroMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonLayout = new javax.swing.GroupLayout(panelBoton);
        panelBoton.setLayout(panelBotonLayout);
        panelBotonLayout.setHorizontalGroup(
            panelBotonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCambiarTablero, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );
        panelBotonLayout.setVerticalGroup(
            panelBotonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonLayout.createSequentialGroup()
                .addComponent(lblCambiarTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(panelBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 570, 166, 50));

        btngTamaño.add(rbt15x15);
        rbt15x15.setForeground(new java.awt.Color(0, 0, 0));
        rbt15x15.setText("15x15");
        jPanel2.add(rbt15x15, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, 60, -1));

        btngTamaño.add(rbt10x10);
        rbt10x10.setForeground(new java.awt.Color(0, 0, 0));
        rbt10x10.setText("10x10");
        jPanel2.add(rbt10x10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 70, -1));

        btngTamaño.add(rbt13x13);
        rbt13x13.setForeground(new java.awt.Color(0, 0, 0));
        rbt13x13.setText("13x13");
        jPanel2.add(rbt13x13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, 80, -1));

        panelBoton3.setBackground(new java.awt.Color(140, 211, 179));

        lblLanzarDado.setBackground(new java.awt.Color(255, 255, 255));
        lblLanzarDado.setFont(new java.awt.Font("Montserrat", 1, 20)); // NOI18N
        lblLanzarDado.setForeground(new java.awt.Color(0, 0, 0));
        lblLanzarDado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLanzarDado.setText("Lanzar dado");
        lblLanzarDado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLanzarDado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLanzarDadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLanzarDadoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblLanzarDadoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelBoton3Layout = new javax.swing.GroupLayout(panelBoton3);
        panelBoton3.setLayout(panelBoton3Layout);
        panelBoton3Layout.setHorizontalGroup(
            panelBoton3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoton3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblLanzarDado, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBoton3Layout.setVerticalGroup(
            panelBoton3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoton3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblLanzarDado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(panelBoton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, 50));
        jPanel2.add(lblDado, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 120, 120));

        panelBotonH.setBackground(new java.awt.Color(255, 250, 193));
        panelBotonH.setPreferredSize(new java.awt.Dimension(166, 50));

        lblHistorial.setBackground(new java.awt.Color(255, 255, 255));
        lblHistorial.setFont(new java.awt.Font("Montserrat", 1, 20)); // NOI18N
        lblHistorial.setForeground(new java.awt.Color(0, 0, 0));
        lblHistorial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHistorial.setText("Historial");
        lblHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHistorialMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHistorialMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblHistorialMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonHLayout = new javax.swing.GroupLayout(panelBotonH);
        panelBotonH.setLayout(panelBotonHLayout);
        panelBotonHLayout.setHorizontalGroup(
            panelBotonHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonHLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBotonHLayout.setVerticalGroup(
            panelBotonHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonHLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(panelBotonH, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, -1, -1));

        jpanelReiniciar.setBackground(new java.awt.Color(177, 156, 217));

        lblReiniciar.setBackground(new java.awt.Color(255, 255, 255));
        lblReiniciar.setFont(new java.awt.Font("Montserrat", 1, 20)); // NOI18N
        lblReiniciar.setForeground(new java.awt.Color(0, 0, 0));
        lblReiniciar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReiniciar.setText("Reiniciar partida");
        lblReiniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblReiniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblReiniciarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblReiniciarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblReiniciarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jpanelReiniciarLayout = new javax.swing.GroupLayout(jpanelReiniciar);
        jpanelReiniciar.setLayout(jpanelReiniciarLayout);
        jpanelReiniciarLayout.setHorizontalGroup(
            jpanelReiniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelReiniciarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblReiniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpanelReiniciarLayout.setVerticalGroup(
            jpanelReiniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelReiniciarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jpanelReiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 690, 166, 50));

        lblTurno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblTurno.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(lblTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 220, 20));

        panelFondo.add(jPanel2);
        jPanel2.setBounds(740, 10, 260, 750);

        jTabbedPane1.setBackground(new java.awt.Color(240, 232, 216));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        jPanel4x4.setBackground(new java.awt.Color(240, 232, 216));

        jPanel10x10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10x10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel10x10.setPreferredSize(new java.awt.Dimension(220, 220));

        javax.swing.GroupLayout jPanel10x10Layout = new javax.swing.GroupLayout(jPanel10x10);
        jPanel10x10.setLayout(jPanel10x10Layout);
        jPanel10x10Layout.setHorizontalGroup(
            jPanel10x10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 676, Short.MAX_VALUE)
        );
        jPanel10x10Layout.setVerticalGroup(
            jPanel10x10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 676, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4x4Layout = new javax.swing.GroupLayout(jPanel4x4);
        jPanel4x4.setLayout(jPanel4x4Layout);
        jPanel4x4Layout.setHorizontalGroup(
            jPanel4x4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4x4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel10x10, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel4x4Layout.setVerticalGroup(
            jPanel4x4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4x4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel10x10, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel4x4);

        jPanel5x5.setBackground(new java.awt.Color(240, 232, 216));

        jPanel13x13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13x13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel13x13.setPreferredSize(new java.awt.Dimension(220, 220));

        javax.swing.GroupLayout jPanel13x13Layout = new javax.swing.GroupLayout(jPanel13x13);
        jPanel13x13.setLayout(jPanel13x13Layout);
        jPanel13x13Layout.setHorizontalGroup(
            jPanel13x13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 676, Short.MAX_VALUE)
        );
        jPanel13x13Layout.setVerticalGroup(
            jPanel13x13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 676, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5x5Layout = new javax.swing.GroupLayout(jPanel5x5);
        jPanel5x5.setLayout(jPanel5x5Layout);
        jPanel5x5Layout.setHorizontalGroup(
            jPanel5x5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5x5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel13x13, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel5x5Layout.setVerticalGroup(
            jPanel5x5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5x5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel13x13, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel5x5);

        jPanel3x3.setBackground(new java.awt.Color(240, 232, 216));

        jPanel15x15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15x15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel15x15.setPreferredSize(new java.awt.Dimension(660, 660));

        javax.swing.GroupLayout jPanel15x15Layout = new javax.swing.GroupLayout(jPanel15x15);
        jPanel15x15.setLayout(jPanel15x15Layout);
        jPanel15x15Layout.setHorizontalGroup(
            jPanel15x15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
        );
        jPanel15x15Layout.setVerticalGroup(
            jPanel15x15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 716, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3x3Layout = new javax.swing.GroupLayout(jPanel3x3);
        jPanel3x3.setLayout(jPanel3x3Layout);
        jPanel3x3Layout.setHorizontalGroup(
            jPanel3x3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3x3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel15x15, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3x3Layout.setVerticalGroup(
            jPanel3x3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3x3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15x15, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel3x3);

        panelFondo.add(jTabbedPane1);
        jTabbedPane1.setBounds(10, 10, 760, 750);

        txtASerpientesyEscaleras.setEditable(false);
        txtASerpientesyEscaleras.setBackground(new java.awt.Color(240, 232, 216));
        txtASerpientesyEscaleras.setColumns(20);
        txtASerpientesyEscaleras.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        txtASerpientesyEscaleras.setRows(5);
        jScrollPane1.setViewportView(txtASerpientesyEscaleras);

        panelFondo.add(jScrollPane1);
        jScrollPane1.setBounds(1030, 30, 270, 710);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 1350, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCierreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCierreMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblCierreMouseClicked

    private void lblCierreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCierreMouseEntered
        lblCierre.setForeground(Color.RED);
    }//GEN-LAST:event_lblCierreMouseEntered

    private void lblCierreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCierreMouseExited
        lblCierre.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_lblCierreMouseExited

    private void lblTerminarPartidaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTerminarPartidaMouseEntered
        panelBoton1.setBackground(new Color(204, 183, 153));
    }//GEN-LAST:event_lblTerminarPartidaMouseEntered

    private void lblTerminarPartidaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTerminarPartidaMouseExited
        panelBoton1.setBackground(new Color(216, 195, 165));
    }//GEN-LAST:event_lblTerminarPartidaMouseExited

    private void lblTerminarPartidaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTerminarPartidaMousePressed
        PanelMain_SerpientesyEscaleras main = new PanelMain_SerpientesyEscaleras();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblTerminarPartidaMousePressed

    private void lblLanzarDadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLanzarDadoMouseEntered
        panelBoton3.setBackground(new Color(183, 221, 200));
    }//GEN-LAST:event_lblLanzarDadoMouseEntered

    private void lblLanzarDadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLanzarDadoMouseExited
        panelBoton3.setBackground(new Color(140, 211, 179));
    }//GEN-LAST:event_lblLanzarDadoMouseExited

    private void lblLanzarDadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLanzarDadoMousePressed
        if (dadoLanzado) {
            JOptionPane.showMessageDialog(this, "Espere a que el jugador actual termine su movimiento.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (tableroActual.hayGanador()) {
            JOptionPane.showMessageDialog(this, "La partida ha finalizado. No pueden haber más intentos.", "Partida finalizada", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        dadoLanzado = true;
        lblLanzarDado.setEnabled(false);

        Dado dado = new Dado();
        int resultado = dado.getValorDado();

        Timer timer = new Timer(50, null);
        timer.start();

        long tiempoInicio = System.currentTimeMillis();
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
                if (tiempoTranscurrido < 1000) {
                    ImageIcon iconoAleatorio = dado.obtenerImagenDadoAleatorioRedimensionado(120, 120);
                    lblDado.setIcon(iconoAleatorio);
                } else {
                    timer.stop();
                    ImageIcon iconoResultado = dado.obtenerImagenDadoRedimensionada(120, 120);
                    lblDado.setIcon(iconoResultado);

                    Jugador jugadorActual = jugadores.get(jugadorActualIndex);
                    tableroActual.moverJugador(jugadorActual, resultado);
                }
            }
        });
    }//GEN-LAST:event_lblLanzarDadoMousePressed

    private void lblCambiarTableroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCambiarTableroMousePressed
        int tableroActual = 0;
        boolean continuar;

        do {
            continuar = false;
            int nuevoTamañoTablero = obtenerTamañoTablero();

            if (nuevoTamañoTablero == tableroAnterior) {
                JOptionPane.showMessageDialog(this, "Estás en el mismo tablero, por favor, selecciona uno diferente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                String inputSerpientes = JOptionPane.showInputDialog(this, "Ingrese la cantidad de serpientes");

                if (inputSerpientes != null && !inputSerpientes.isEmpty()) {
                    try {
                        int numSerpientes = Integer.parseInt(inputSerpientes);

                        String inputEscaleras = JOptionPane.showInputDialog(this, "Ingrese la cantidad de escaleras");

                        if (inputEscaleras != null && !inputEscaleras.isEmpty()) {
                            try {
                                int numEscaleras = Integer.parseInt(inputEscaleras);

                                if (validarCantidadSerpientesEscaleras(nuevoTamañoTablero, numSerpientes, numEscaleras)) {
                                    init2(nuevoTamañoTablero, numEscaleras, numSerpientes);
                                    tableroActual = nuevoTamañoTablero;
                                    tableroAnterior = tableroActual;
                                    continuar = false;
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(this, "Entrada inválida para escaleras. Por favor, ingrese un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Entrada inválida para serpientes. Por favor, ingrese un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    continuar = false;
                }
            }
        } while (continuar);
    }//GEN-LAST:event_lblCambiarTableroMousePressed

    private void lblCambiarTableroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCambiarTableroMouseExited
        panelBoton.setBackground(new Color(140, 211, 179));
    }//GEN-LAST:event_lblCambiarTableroMouseExited

    private void lblCambiarTableroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCambiarTableroMouseEntered
        panelBoton.setBackground(new Color(183, 221, 200));
    }//GEN-LAST:event_lblCambiarTableroMouseEntered

    private void lblHistorialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHistorialMouseEntered
        panelBotonH.setBackground(new Color(255, 250, 212));
    }//GEN-LAST:event_lblHistorialMouseEntered

    private void lblHistorialMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHistorialMouseExited
        panelBotonH.setBackground(new Color(255, 250, 193));
    }//GEN-LAST:event_lblHistorialMouseExited

    private void lblHistorialMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHistorialMousePressed
        List<EventoJuego> eventosJuego = tableroActual.getEventosJuego();
        RegistroEventos historial = new RegistroEventos(this, eventosJuego);
        historial.setVisible(true);
    }//GEN-LAST:event_lblHistorialMousePressed

    private void lblReiniciarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReiniciarMouseEntered
        jpanelReiniciar.setBackground(new Color(214, 176, 255));
    }//GEN-LAST:event_lblReiniciarMouseEntered

    private void lblReiniciarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReiniciarMouseExited
        jpanelReiniciar.setBackground(new Color(177, 156, 217));
    }//GEN-LAST:event_lblReiniciarMouseExited

    private void lblReiniciarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReiniciarMousePressed
        boolean continuar;

        do {
            continuar = false;
            String inputSerpientes = JOptionPane.showInputDialog(this, "Ingrese la cantidad de serpientes");

            if (inputSerpientes != null && !inputSerpientes.isEmpty()) {
                try {
                    int numSerpientes = Integer.parseInt(inputSerpientes);

                    String inputEscaleras = JOptionPane.showInputDialog(this, "Ingrese la cantidad de escaleras");

                    if (inputEscaleras != null && !inputEscaleras.isEmpty()) {
                        try {
                            int numEscaleras = Integer.parseInt(inputEscaleras);

                            if (validarCantidadSerpientesEscaleras(tamañoTableroActual, numSerpientes, numEscaleras)) {
                                if (tableroActual != null) {
                                    tableroActual.limpiarTablero();
                                }

                                init(tamañoTableroActual, numEscaleras, numSerpientes);
                                tableroActual.reiniciarPosicionJugadores();

                                if (jugadorGanadorIndex != -1) {
                                    jugadorActualIndex = jugadorGanadorIndex;
                                } else {
                                    jugadorActualIndex = 0;
                                }

                                bordeTurnoJugador(jugadorActualIndex);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Entrada inválida para escaleras. Por favor, ingrese un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Entrada inválida para serpientes. Por favor, ingrese un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                continuar = false;
            }
        } while (continuar);
    }//GEN-LAST:event_lblReiniciarMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ficha1;
    private javax.swing.JLabel Ficha2;
    private javax.swing.JLabel Ficha3;
    private javax.swing.JLabel Ficha4;
    private javax.swing.ButtonGroup btngTamaño;
    private javax.swing.JPanel jPanel10x10;
    private javax.swing.JPanel jPanel13x13;
    private javax.swing.JPanel jPanel15x15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3x3;
    private javax.swing.JPanel jPanel4x4;
    private javax.swing.JPanel jPanel5x5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpanelReiniciar;
    private javax.swing.JLabel lblCambiarTablero;
    private javax.swing.JLabel lblCierre;
    private javax.swing.JLabel lblDado;
    private javax.swing.JLabel lblHistorial;
    public javax.swing.JLabel lblLanzarDado;
    private javax.swing.JLabel lblNombreJ1;
    private javax.swing.JLabel lblNombreJ2;
    private javax.swing.JLabel lblNombreJ3;
    private javax.swing.JLabel lblNombreJ4;
    private javax.swing.JLabel lblReiniciar;
    private javax.swing.JLabel lblTerminarPartida;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JPanel panelBoton;
    private javax.swing.JPanel panelBoton1;
    private javax.swing.JPanel panelBoton3;
    private javax.swing.JPanel panelBotonH;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JRadioButton rbt10x10;
    private javax.swing.JRadioButton rbt13x13;
    private javax.swing.JRadioButton rbt15x15;
    public javax.swing.JTextArea txtASerpientesyEscaleras;
    // End of variables declaration//GEN-END:variables
}
