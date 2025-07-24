package hh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FormularioFactura extends JFrame {

    private HotelServicio servicio;
    private JTextField txtCI, txtIdReserva;

    public FormularioFactura(HotelServicio servicio) {
        this.servicio = servicio;

        setTitle("Imprimir Factura");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 5, 5));
        setResizable(false);
        
        txtCI = new JTextField();
        txtIdReserva = new JTextField();

        add(new JLabel("CI Cliente:"));
        add(txtCI);
        add(new JLabel("ID Reserva:"));
        add(txtIdReserva);

        JButton btnEmitir = new JButton("Emitir Factura");
        btnEmitir.addActionListener(this::emitirFactura);
        add(btnEmitir);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }

    private void emitirFactura(ActionEvent e) {
        String ci = txtCI.getText().trim();
        int idReserva;
        try {
            idReserva = Integer.parseInt(txtIdReserva.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID de reserva inválido.");
            return;
        }
        
        JOptionPane.showMessageDialog(this, servicio.imprimirFactura(idReserva, ci));
        dispose();
    }
}

