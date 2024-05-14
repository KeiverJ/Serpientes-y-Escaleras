/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author keiver
 */
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RegistroEventos extends JDialog {

    private JTextArea txtRegistroEventos;

    public RegistroEventos(Frame parent, List<EventoJuego> eventosJuego) {
        setUndecorated(true);
        setSize(400, 400);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 232, 216));

        txtRegistroEventos = new JTextArea();
        txtRegistroEventos.setEditable(false);
        txtRegistroEventos.setBackground(new Color(240, 232, 216));
        txtRegistroEventos.setOpaque(true);
        txtRegistroEventos.setLineWrap(true);
        txtRegistroEventos.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(txtRegistroEventos);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        StringBuilder sb = new StringBuilder();
        for (EventoJuego evento : eventosJuego) {
            sb.append(evento.getDescripcion()).append("\n");
        }
        txtRegistroEventos.setText(sb.toString());

        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        panel.add(btnCerrar, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

}
