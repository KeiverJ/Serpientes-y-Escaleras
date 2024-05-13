/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author keive
 */
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RegistroEventosDialog extends JDialog {

    private JTextArea txtRegistroEventos;

    public RegistroEventosDialog(Frame parent, List<EventoJuego> eventosJuego) {
        super(parent, "Registro de Eventos", true);
        setSize(400, 400);
        setLocationRelativeTo(parent);

        txtRegistroEventos = new JTextArea();
        txtRegistroEventos.setEditable(false);

        StringBuilder sb = new StringBuilder();
        for (EventoJuego evento : eventosJuego) {
            sb.append(evento.getDescripcion()).append("\n");
        }
        txtRegistroEventos.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(txtRegistroEventos);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
}
