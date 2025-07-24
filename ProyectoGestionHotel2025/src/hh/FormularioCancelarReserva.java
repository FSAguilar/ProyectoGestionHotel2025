package hh;

import javax.swing.*;
import java.awt.*;

public class FormularioCancelarReserva extends JFrame {

    private JTextField txtID;
    // private HotelServicio servicio;

    public FormularioCancelarReserva(HotelServicio servicio) {
        // this.servicio = servicio;

        setTitle("Cancelar Reserva");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 5, 5));
        setResizable(false);
        
        txtID = new JTextField();

        add(new JLabel(" CI Cliente:"));
        add(txtID);

        JButton btnReservar = new JButton("Eliminar Reserva");
        btnReservar.addActionListener(e -> {
            
            JOptionPane.showMessageDialog(this, servicio.cancelarReserva(
                Integer.valueOf(txtID.getText())
            ));
            dispose();
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        add(btnReservar);
        add(btnCancelar);
    }
}
