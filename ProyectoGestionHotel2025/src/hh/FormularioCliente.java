package hh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FormularioCliente extends JFrame {

    private JTextField txtNombre, txtCi, txtCorreo, txtTelefono, txtDireccion;
    private HotelServicio servicio;

    public FormularioCliente(HotelServicio servicio) {
        this.servicio = servicio;

        setTitle("Registrar Cliente");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 5, 5));
        setResizable(false);
        
        txtNombre = new JTextField();
        txtCi = new JTextField();
        txtCorreo = new JTextField();
        txtTelefono = new JTextField();
        txtDireccion = new JTextField();

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

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(this::registrar);
        add(btnRegistrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }

    private void registrar(ActionEvent e) {
        
        JOptionPane.showMessageDialog(this, servicio.registrarCliente(
                txtNombre.getText(),
                txtCi.getText(),
                txtCorreo.getText(),
                txtTelefono.getText(),
                txtDireccion.getText()
        ));
        dispose();
    }
}
