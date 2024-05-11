package GUI;

import java.awt.Color;

/**
 *
 * @author keiver
 */
public class PanelTablero_SerpientesyEscaleras extends javax.swing.JFrame {

    Jugador jugador1 = new Jugador("");
    Jugador jugador2 = new Jugador("");
    Jugador jugador3 = new Jugador("");
    Jugador jugador4 = new Jugador("");

    public PanelTablero_SerpientesyEscaleras(Jugador jugador1, Jugador jugador2, Jugador jugador3, Jugador jugador4, int tamañoTablero) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugador3 = jugador3;
        this.jugador4 = jugador4;
        setUndecorated(true);
        initComponents();
        init(tamañoTablero);
        setResizable(false);
        setLocationRelativeTo(null);
        panelFondo.setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));

        lblNombreJ1.setText(jugador1.getNombre());
        lblNombreJ2.setText(jugador2.getNombre());
        lblNombreJ3.setText(jugador3.getNombre());
        lblNombreJ4.setText(jugador4.getNombre());

    }

    private void init(int tamañoTablero) {
        switch (tamañoTablero) {
            case 10:
                jTabbedPane1.setSelectedIndex(0);
                rbt3x3.setEnabled(false);
                rbt4x4.setEnabled(true);
                rbt5x5.setEnabled(true);
                break;
            case 13:
                jTabbedPane1.setSelectedIndex(1);
                rbt3x3.setEnabled(true);
                rbt4x4.setEnabled(false);
                rbt5x5.setEnabled(true);
                break;
            case 15:
                jTabbedPane1.setSelectedIndex(2);
                rbt3x3.setEnabled(true);
                rbt4x4.setEnabled(true);
                rbt5x5.setEnabled(false);
                break;
            default:
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelBoton1 = new javax.swing.JPanel();
        lblTerminarPartida = new javax.swing.JLabel();
        panelBoton = new javax.swing.JPanel();
        lblCambiarTablero = new javax.swing.JLabel();
        rbt5x5 = new javax.swing.JRadioButton();
        rbt3x3 = new javax.swing.JRadioButton();
        rbt4x4 = new javax.swing.JRadioButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4x4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5x5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel3x3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

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
        jPanel2.add(lblNombreJ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 100, 40));

        lblNombreJ2.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblNombreJ2.setForeground(new java.awt.Color(255, 170, 92));
        lblNombreJ2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreJ2.setText("NAME");
        jPanel2.add(lblNombreJ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 130, 40));

        lblNombreJ3.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblNombreJ3.setForeground(new java.awt.Color(255, 170, 92));
        lblNombreJ3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreJ3.setText("NAME");
        jPanel2.add(lblNombreJ3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 120, 40));

        lblNombreJ4.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblNombreJ4.setForeground(new java.awt.Color(255, 170, 92));
        lblNombreJ4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreJ4.setText("NAME");
        jPanel2.add(lblNombreJ4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 120, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ficha4.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 30, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ficha1.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 30, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ficha2.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 30, 50));

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
            .addGroup(panelBoton1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTerminarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCambiarTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(panelBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, 170, -1));

        btngTamaño.add(rbt5x5);
        rbt5x5.setForeground(new java.awt.Color(0, 0, 0));
        rbt5x5.setText("15x15");
        jPanel2.add(rbt5x5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 60, -1));

        btngTamaño.add(rbt3x3);
        rbt3x3.setForeground(new java.awt.Color(0, 0, 0));
        rbt3x3.setText("10x10");
        jPanel2.add(rbt3x3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 60, -1));

        btngTamaño.add(rbt4x4);
        rbt4x4.setForeground(new java.awt.Color(0, 0, 0));
        rbt4x4.setText("13x13");
        jPanel2.add(rbt4x4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, 70, -1));

        panelFondo.add(jPanel2);
        jPanel2.setBounds(700, 10, 300, 740);

        jTabbedPane1.setBackground(new java.awt.Color(240, 232, 216));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        jPanel4x4.setBackground(new java.awt.Color(240, 232, 216));

        jPanel7.setBackground(new java.awt.Color(162, 213, 242));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel5.setText("HGOLA");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(298, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(208, 208, 208))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel5)
                .addContainerGap(406, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4x4Layout = new javax.swing.GroupLayout(jPanel4x4);
        jPanel4x4.setLayout(jPanel4x4Layout);
        jPanel4x4Layout.setHorizontalGroup(
            jPanel4x4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4x4Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel4x4Layout.setVerticalGroup(
            jPanel4x4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4x4Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel4x4);

        jPanel5x5.setBackground(new java.awt.Color(240, 232, 216));

        jPanel8.setBackground(new java.awt.Color(162, 213, 242));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.setPreferredSize(new java.awt.Dimension(220, 220));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 596, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 596, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5x5Layout = new javax.swing.GroupLayout(jPanel5x5);
        jPanel5x5.setLayout(jPanel5x5Layout);
        jPanel5x5Layout.setHorizontalGroup(
            jPanel5x5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5x5Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel5x5Layout.setVerticalGroup(
            jPanel5x5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5x5Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel5x5);

        jPanel3x3.setBackground(new java.awt.Color(240, 232, 216));

        jPanel4.setBackground(new java.awt.Color(162, 213, 242));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setPreferredSize(new java.awt.Dimension(220, 220));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3x3Layout = new javax.swing.GroupLayout(jPanel3x3);
        jPanel3x3.setLayout(jPanel3x3Layout);
        jPanel3x3Layout.setHorizontalGroup(
            jPanel3x3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3x3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3x3Layout.setVerticalGroup(
            jPanel3x3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3x3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jTabbedPane1.addTab("", jPanel3x3);

        panelFondo.add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 10, 740, 720);

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
        if (rbt3x3.isSelected()) {
            jTabbedPane1.setSelectedIndex(0);
            rbt3x3.setEnabled(false);
            rbt4x4.setEnabled(true);
            rbt5x5.setEnabled(true);
        } else if (rbt4x4.isSelected()) {
            jTabbedPane1.setSelectedIndex(1);
            rbt3x3.setEnabled(true);
            rbt4x4.setEnabled(false);
            rbt5x5.setEnabled(true);
        } else if (rbt5x5.isSelected()) {
            jTabbedPane1.setSelectedIndex(2);
            rbt3x3.setEnabled(true);
            rbt4x4.setEnabled(true);
            rbt5x5.setEnabled(false);
        }
    }//GEN-LAST:event_lblCambiarTableroMousePressed

    private void lblTerminarPartidaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTerminarPartidaMouseEntered
        panelBoton1.setBackground(new Color(204, 183, 153));
    }//GEN-LAST:event_lblTerminarPartidaMouseEntered

    private void lblTerminarPartidaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTerminarPartidaMouseExited
        panelBoton1.setBackground(new Color(216, 195, 165));
    }//GEN-LAST:event_lblTerminarPartidaMouseExited

    private void lblTerminarPartidaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTerminarPartidaMousePressed
        this.dispose();
    }//GEN-LAST:event_lblTerminarPartidaMousePressed

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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3x3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel4x4;
    private javax.swing.JPanel jPanel5x5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCambiarTablero;
    private javax.swing.JLabel lblCierre;
    private javax.swing.JLabel lblNombreJ1;
    private javax.swing.JLabel lblNombreJ2;
    private javax.swing.JLabel lblNombreJ3;
    private javax.swing.JLabel lblNombreJ4;
    private javax.swing.JLabel lblTerminarPartida;
    private javax.swing.JPanel panelBoton;
    private javax.swing.JPanel panelBoton1;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JRadioButton rbt3x3;
    private javax.swing.JRadioButton rbt4x4;
    private javax.swing.JRadioButton rbt5x5;
    // End of variables declaration//GEN-END:variables
}
