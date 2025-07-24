package hh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FormularioCheckOut extends JFrame {

    private HotelServicio servicio;
    private JTextField txtIdReserva, txtCi;
    private JComboBox<MetodoPago> comboPago;

    public FormularioCheckOut(HotelServicio servicio) {
        this.servicio = servicio;

        setTitle("Realizar Check-Out");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 5, 5));
        setResizable(false);
        
        txtIdReserva = new JTextField();
        txtCi = new JTextField();
        comboPago = new JComboBox<>(MetodoPago.values());

        add(new JLabel("ID Reserva:"));
        add(txtIdReserva);
        add(new JLabel("CI Cliente:"));
        add(txtCi);
        add(new JLabel("Método de Pago:"));
        add(comboPago);

        JButton btnCheckOut = new JButton("Check-Out");
        btnCheckOut.addActionListener(this::realizarCheckOut);
        add(btnCheckOut);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }

    private void realizarCheckOut(ActionEvent e) {
        String ci = txtCi.getText().trim();
        int idReserva;
        try {
            idReserva = Integer.parseInt(txtIdReserva.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID de reserva inválido.");
            return;
        }

        MetodoPago metodoPago = (MetodoPago) comboPago.getSelectedItem();

        
        JOptionPane.showMessageDialog(this, servicio.checkOut(idReserva, ci, metodoPago));
        dispose();
    }
}

