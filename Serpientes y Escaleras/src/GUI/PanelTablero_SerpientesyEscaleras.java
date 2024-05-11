package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author keiver
 */
public class PanelTablero_SerpientesyEscaleras extends javax.swing.JFrame {

    private List<Jugador> jugadores;
    private int jugadorActual;
    int tamañoTableroActual;

    Jugador jugador1 = new Jugador(0, "", new ImageIcon(getClass().getResource("/resources/ficha1.png")));
    Jugador jugador2 = new Jugador(1, "", new ImageIcon(getClass().getResource("/resources/ficha2.png")));
    Jugador jugador3 = new Jugador(2, "", new ImageIcon(getClass().getResource("/resources/ficha3.png")));
    Jugador jugador4 = new Jugador(3, "", new ImageIcon(getClass().getResource("/resources/ficha4.png")));

    Tablero tablero10x10 = new Tablero(10, 10);
    Tablero tablero13x13 = new Tablero(13, 13);
    Tablero tablero15x15 = new Tablero(15, 15);

    public PanelTablero_SerpientesyEscaleras(List<Jugador> jugadores, int tamañoTablero) {
        this.jugadores = jugadores;
        jugadorActual = 0;
        setUndecorated(true);
        initComponents();
        init(tamañoTablero);
        setResizable(false);
        setLocationRelativeTo(null);
        panelFondo.setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));

        tamañoTableroActual = tamañoTablero;

        if (jugadores.size() > 0) {
            lblNombreJ1.setText(jugadores.get(0).getNombre());
        }
        if (jugadores.size() > 1) {
            lblNombreJ2.setText(jugadores.get(1).getNombre());
        }
        if (jugadores.size() > 2) {
            lblNombreJ3.setText(jugadores.get(2).getNombre());
        }
        if (jugadores.size() > 3) {
            lblNombreJ4.setText(jugadores.get(3).getNombre());
        }

    }

    private void init(int tamañoTablero) {
        switch (tamañoTablero) {
            case 10:
                jTabbedPane1.setSelectedIndex(0);
                jPanel10x10.setLayout(new BorderLayout());
                jPanel10x10.add(tablero10x10, BorderLayout.CENTER);
                tablero10x10.revalidate();
                tablero10x10.repaint();
                rbt10x10.setEnabled(false);
                rbt13x13.setEnabled(true);
                rbt15x15.setEnabled(true);
                for (Jugador jugador : jugadores) {
                    tablero10x10.agregarJugador(jugador);
                }
                break;
            case 13:
                jTabbedPane1.setSelectedIndex(1);
                jPanel13x13.setLayout(new BorderLayout());
                jPanel13x13.add(tablero13x13, BorderLayout.CENTER);
                tablero13x13.revalidate();
                tablero13x13.repaint();
                rbt10x10.setEnabled(true);
                rbt13x13.setEnabled(false);
                rbt15x15.setEnabled(true);
                for (Jugador jugador : jugadores) {
                    tablero13x13.agregarJugador(jugador);
                }
                break;
            case 15:
                jTabbedPane1.setSelectedIndex(2);
                jPanel15x15.setLayout(new BorderLayout());
                jPanel15x15.add(tablero15x15, BorderLayout.CENTER);
                tablero15x15.revalidate();
                tablero15x15.repaint();
                rbt10x10.setEnabled(true);
                rbt13x13.setEnabled(true);
                rbt15x15.setEnabled(false);
                for (Jugador jugador : jugadores) {
                    tablero15x15.agregarJugador(jugador);
                }
                break;
            default:
                break;
        }
    }

    private int dado() {
        Random random = new Random();
        return random.nextInt(6) + 1;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelBoton1 = new javax.swing.JPanel();
        lblTerminarPartida = new javax.swing.JLabel();
        panelBoton = new javax.swing.JPanel();
        lblCambiarTablero = new javax.swing.JLabel();
        rbt15x15 = new javax.swing.JRadioButton();
        rbt10x10 = new javax.swing.JRadioButton();
        rbt13x13 = new javax.swing.JRadioButton();
        panelBoton3 = new javax.swing.JPanel();
        lblLanzarDado = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4x4 = new javax.swing.JPanel();
        jPanel10x10 = new javax.swing.JPanel();
        jPanel5x5 = new javax.swing.JPanel();
        jPanel13x13 = new javax.swing.JPanel();
        jPanel3x3 = new javax.swing.JPanel();
        jPanel15x15 = new javax.swing.JPanel();

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
        lblCierre.setBounds(990, 10, 16, 32);

        jPanel2.setBackground(new java.awt.Color(240, 232, 216));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreJ1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblNombreJ1.setForeground(new java.awt.Color(124, 195, 236));
        lblNombreJ1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreJ1.setText("NAME");
        jPanel2.add(lblNombreJ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 100, 40));

        lblNombreJ2.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblNombreJ2.setForeground(new java.awt.Color(255, 170, 92));
        lblNombreJ2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreJ2.setText("NAME");
        jPanel2.add(lblNombreJ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 130, 40));

        lblNombreJ3.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblNombreJ3.setForeground(new java.awt.Color(255, 170, 92));
        lblNombreJ3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreJ3.setText("NAME");
        jPanel2.add(lblNombreJ3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 120, 40));

        lblNombreJ4.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblNombreJ4.setForeground(new java.awt.Color(255, 170, 92));
        lblNombreJ4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreJ4.setText("NAME");
        jPanel2.add(lblNombreJ4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 120, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ficha4.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 30, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ficha1.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 30, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ficha2.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 30, 50));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ficha3.png"))); // NOI18N
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 30, 50));

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

        jPanel2.add(panelBoton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 590, -1, 50));

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
            .addGroup(panelBotonLayout.createSequentialGroup()
                .addComponent(lblCambiarTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBotonLayout.setVerticalGroup(
            panelBotonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonLayout.createSequentialGroup()
                .addComponent(lblCambiarTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(panelBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, 170, 50));

        btngTamaño.add(rbt15x15);
        rbt15x15.setForeground(new java.awt.Color(0, 0, 0));
        rbt15x15.setText("15x15");
        jPanel2.add(rbt15x15, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 60, -1));

        btngTamaño.add(rbt10x10);
        rbt10x10.setForeground(new java.awt.Color(0, 0, 0));
        rbt10x10.setText("10x10");
        jPanel2.add(rbt10x10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 60, -1));

        btngTamaño.add(rbt13x13);
        rbt13x13.setForeground(new java.awt.Color(0, 0, 0));
        rbt13x13.setText("13x13");
        jPanel2.add(rbt13x13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, 70, -1));

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

        panelFondo.add(jPanel2);
        jPanel2.setBounds(760, 10, 240, 750);

        jTabbedPane1.setBackground(new java.awt.Color(240, 232, 216));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        jPanel4x4.setBackground(new java.awt.Color(240, 232, 216));

        jPanel10x10.setBackground(new java.awt.Color(162, 213, 242));
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
                .addContainerGap(32, Short.MAX_VALUE))
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

        jPanel13x13.setBackground(new java.awt.Color(162, 213, 242));
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
                .addContainerGap(32, Short.MAX_VALUE))
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

        jPanel15x15.setBackground(new java.awt.Color(162, 213, 242));
        jPanel15x15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel15x15.setPreferredSize(new java.awt.Dimension(660, 660));

        javax.swing.GroupLayout jPanel15x15Layout = new javax.swing.GroupLayout(jPanel15x15);
        jPanel15x15.setLayout(jPanel15x15Layout);
        jPanel15x15Layout.setHorizontalGroup(
            jPanel15x15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 716, Short.MAX_VALUE)
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
                .addComponent(jPanel15x15, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
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
        jTabbedPane1.setBounds(0, 10, 770, 750);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
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

    private void lblCambiarTableroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCambiarTableroMouseEntered
        panelBoton.setBackground(new Color(183, 221, 200));
    }//GEN-LAST:event_lblCambiarTableroMouseEntered

    private void lblCambiarTableroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCambiarTableroMouseExited
        panelBoton.setBackground(new Color(140, 211, 179));
    }//GEN-LAST:event_lblCambiarTableroMouseExited

    private void lblCambiarTableroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCambiarTableroMousePressed
        if (rbt10x10.isSelected()) {
            jTabbedPane1.setSelectedIndex(0);
            rbt10x10.setEnabled(false);
            rbt13x13.setEnabled(true);
            rbt15x15.setEnabled(true);
        } else if (rbt13x13.isSelected()) {
            jTabbedPane1.setSelectedIndex(1);
            rbt10x10.setEnabled(true);
            rbt13x13.setEnabled(false);
            rbt15x15.setEnabled(true);
        } else if (rbt15x15.isSelected()) {
            jTabbedPane1.setSelectedIndex(2);
            rbt10x10.setEnabled(true);
            rbt13x13.setEnabled(true);
            rbt15x15.setEnabled(false);
        }
    }//GEN-LAST:event_lblCambiarTableroMousePressed

    private void lblTerminarPartidaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTerminarPartidaMouseEntered
        panelBoton1.setBackground(new Color(204, 183, 153));
    }//GEN-LAST:event_lblTerminarPartidaMouseEntered

    private void lblTerminarPartidaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTerminarPartidaMouseExited
        panelBoton1.setBackground(new Color(216, 195, 165));
    }//GEN-LAST:event_lblTerminarPartidaMouseExited

    private void lblTerminarPartidaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTerminarPartidaMousePressed
        System.exit(0);
    }//GEN-LAST:event_lblTerminarPartidaMousePressed

    private void lblLanzarDadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLanzarDadoMouseEntered
        panelBoton3.setBackground(new Color(183, 221, 200));
    }//GEN-LAST:event_lblLanzarDadoMouseEntered

    private void lblLanzarDadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLanzarDadoMouseExited
        panelBoton3.setBackground(new Color(140, 211, 179));
    }//GEN-LAST:event_lblLanzarDadoMouseExited

    private void lblLanzarDadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLanzarDadoMousePressed
        int resultado = dado();
        System.out.println(resultado);

        Jugador jugadorActual = jugadores.get(this.jugadorActual);

        switch (tamañoTableroActual) {
            case 10:
                tablero10x10.moverJugador(jugadorActual, resultado);
                this.jugadorActual = (this.jugadorActual + 1) % jugadores.size();
                break;
            case 13:
                tablero13x13.moverJugador(jugadorActual, resultado);
                this.jugadorActual = (this.jugadorActual + 1) % jugadores.size();
                break;
            case 15:
                tablero15x15.moverJugador(jugadorActual, resultado);
                this.jugadorActual = (this.jugadorActual + 1) % jugadores.size();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_lblLanzarDadoMousePressed

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public Jugador getJugador3() {
        return jugador3;
    }

    public void setJugador3(Jugador jugador3) {
        this.jugador3 = jugador3;
    }

    public Jugador getJugador4() {
        return jugador4;
    }

    public void setJugador4(Jugador jugador4) {
        this.jugador4 = jugador4;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btngTamaño;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel10x10;
    private javax.swing.JPanel jPanel13x13;
    private javax.swing.JPanel jPanel15x15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3x3;
    private javax.swing.JPanel jPanel4x4;
    private javax.swing.JPanel jPanel5x5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCambiarTablero;
    private javax.swing.JLabel lblCierre;
    private javax.swing.JLabel lblLanzarDado;
    private javax.swing.JLabel lblNombreJ1;
    private javax.swing.JLabel lblNombreJ2;
    private javax.swing.JLabel lblNombreJ3;
    private javax.swing.JLabel lblNombreJ4;
    private javax.swing.JLabel lblTerminarPartida;
    private javax.swing.JPanel panelBoton;
    private javax.swing.JPanel panelBoton1;
    private javax.swing.JPanel panelBoton3;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JRadioButton rbt10x10;
    private javax.swing.JRadioButton rbt13x13;
    private javax.swing.JRadioButton rbt15x15;
    // End of variables declaration//GEN-END:variables
}
