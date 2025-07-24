package hh;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FormularioEmpleado extends JFrame {

    private HotelServicio servicio;

    private JTextField txtNombre = new JTextField();
    private JTextField txtCi = new JTextField();
    private JTextField txtCorreo = new JTextField();
    private JTextField txtTelefono = new JTextField();
    private JTextField txtDireccion = new JTextField();
    private JTextField txtPuesto = new JTextField();
    private JTextField txtSalario = new JTextField();
    private JTextField txtFechaContratacion = new JTextField(); // yyyy-mm-dd

    public FormularioEmpleado(HotelServicio servicio) {
        this.servicio = servicio;

        setTitle("Registrar Empleado");
        setSize(350, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(9, 2, 5, 5));
        setResizable(false);
        
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("CI:"));
        add(txtCi);
        add(new JLabel("Correo:"));
        add(txtCorreo);
        add(new JLabel("Teléfono:"));
        add(txtTelefono);
        add(new JLabel("Dirección:"));
        add(txtDireccion);
        add(new JLabel("Puesto:"));
        add(txtPuesto);
        add(new JLabel("Salario:"));
        add(txtSalario);
        add(new JLabel("Fecha Contratación (yyyy-mm-dd):"));
        add(txtFechaContratacion);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> registrarEmpleado());
        add(btnRegistrar);
    }

    private void registrarEmpleado() {
        try {
            String nombre = txtNombre.getText().trim();
            String ci = txtCi.getText().trim();
            String correo = txtCorreo.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String puesto = txtPuesto.getText().trim();
            double salario = Double.parseDouble(txtSalario.getText().trim());
            LocalDate fechaContratacion = LocalDate.parse(txtFechaContratacion.getText().trim());

            if (nombre.isEmpty() || ci.isEmpty() || puesto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre, CI y Puesto son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            JOptionPane.showMessageDialog(this, servicio.registrarEmpleado(nombre, ci, correo, telefono, direccion, puesto, salario, fechaContratacion));
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Salario debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

