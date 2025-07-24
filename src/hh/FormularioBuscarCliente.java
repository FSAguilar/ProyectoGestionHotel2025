package hh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FormularioBuscarCliente extends JFrame {

    private JTextField txtCi;
    private HotelServicio servicio;

    public FormularioBuscarCliente(HotelServicio servicio) {
        this.servicio = servicio;

        setTitle("Buscar Cliente");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 5, 5));
        setResizable(false);


        txtCi = new JTextField();

        add(new JLabel("CI:"));
        add(txtCi);

        JButton btnRegistrar = new JButton("Buscar");
        btnRegistrar.addActionListener(this::registrar);
        add(btnRegistrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }

    private void registrar(ActionEvent e) {
        
        JOptionPane.showMessageDialog(this, servicio.buscarClientePorCi(txtCi.getText()).toString());
        dispose();
    }
}
