package hh;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class FormularioReserva extends JFrame {

    private JTextField txtCI, txtNroHabitacion, txtEntrada, txtSalida, txtPersonas;
    // private HotelServicio servicio;

    public FormularioReserva(HotelServicio servicio) {
        // this.servicio = servicio;

        setTitle("Crear Reserva");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 5, 5));
        setResizable(false);
        
        txtCI = new JTextField();
        txtNroHabitacion = new JTextField();
        txtEntrada = new JTextField("2025-08-01");
        txtSalida = new JTextField("2025-08-03");
        txtPersonas = new JTextField();

        add(new JLabel("CI Cliente:"));
        add(txtCI);
        add(new JLabel("Nro Habitación:"));
        add(txtNroHabitacion);
        add(new JLabel("Fecha Entrada (YYYY-MM-DD):"));
        add(txtEntrada);
        add(new JLabel("Fecha Salida (YYYY-MM-DD):"));
        add(txtSalida);
        add(new JLabel("Cantidad de Personas:"));
        add(txtPersonas);

        JButton btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(e -> {
            
            JOptionPane.showMessageDialog(this, servicio.crearReserva(
                    txtCI.getText(),
                    Integer.parseInt(txtNroHabitacion.getText()),
                    LocalDate.parse(txtEntrada.getText()),
                    LocalDate.parse(txtSalida.getText()),
                    Integer.parseInt(txtPersonas.getText())
            ));
            dispose();
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        add(btnReservar);
        add(btnCancelar);
    }
}
