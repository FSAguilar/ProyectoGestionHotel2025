package hh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FormularioCheckIn extends JFrame {

    private HotelServicio servicio;
    private JTextField txtIdReserva, txtCi;

    public FormularioCheckIn(HotelServicio servicio) {
        this.servicio = servicio;

        setTitle("Realizar Check-In");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 5, 5));
        setResizable(false);
        
        txtIdReserva = new JTextField();
        txtCi = new JTextField();

        add(new JLabel("ID Reserva:"));
        add(txtIdReserva);
        add(new JLabel("CI Cliente:"));
        add(txtCi);

        JButton btnCheckIn = new JButton("Check-In");
        btnCheckIn.addActionListener(this::realizarCheckIn);
        add(btnCheckIn);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }

    private void realizarCheckIn(ActionEvent e) {
        String ci = txtCi.getText().trim();
        int idReserva;
        try {
            idReserva = Integer.parseInt(txtIdReserva.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID de reserva inválido.");
            return;
        }

        
        JOptionPane.showMessageDialog(this, servicio.checkIn(idReserva, ci));
        dispose();
    }
}

