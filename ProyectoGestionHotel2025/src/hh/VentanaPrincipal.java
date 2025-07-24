package hh;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

    private HotelServicio servicio;

    public VentanaPrincipal() {
        servicio = new HotelServicio();

        setTitle("Sistema de Reservas - Hotel");
        setResizable(false);
        setSize(480, 920);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        // Título principal
        JLabel lblTitulo = new JLabel("Sistema de Reservas - Hotel", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel contenedor con margen
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelCentral.setBackground(new Color(245, 245, 245));
        add(panelCentral, BorderLayout.CENTER);

        Font btnFont = new Font("Segoe UI", Font.PLAIN, 16);
        Color btnBg = new Color(173, 216, 230);
        Color btnFg = new Color(30, 30, 30);

        // --- Panel Clientes ---
        JPanel panelClientes = new JPanel(new GridLayout(3, 1, 10, 10));
        panelClientes.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLUE.darker(), 1, true),
                "Gestión de Clientes",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                Color.BLUE.darker()));
        panelClientes.setBackground(Color.WHITE);

        JButton btnRegistrarCliente = new JButton("Registrar Cliente");
        JButton btnVerClientes = new JButton("Ver Clientes");
        JButton btnBuscarCliente = new JButton("Buscar Clientes");

        for (JButton b : new JButton[]{btnRegistrarCliente, btnVerClientes, btnBuscarCliente}) {
            b.setFont(btnFont);
            b.setBackground(btnBg);
            b.setForeground(btnFg);
            b.setFocusPainted(false);
            panelClientes.add(b);
        }
        panelCentral.add(panelClientes);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 15)));

        // --- Panel Habitaciones y Reservas ---
        JPanel panelReservas = new JPanel(new GridLayout(4, 1, 10, 10));
        panelReservas.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GREEN.darker(), 1, true),
                "Habitaciones y Reservas",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 100, 0)));
        panelReservas.setBackground(Color.WHITE);

        JButton btnVerHabitaciones = new JButton("Ver Habitaciones");
        JButton btnCrearReserva = new JButton("Crear Reserva");
        JButton btnCancelarReserva = new JButton("Cancelar Reserva");
        JButton btnVerReservas = new JButton("Ver Reservas");

        for (JButton b : new JButton[]{btnVerHabitaciones, btnCrearReserva, btnCancelarReserva, btnVerReservas}) {
            b.setFont(btnFont);
            b.setBackground(btnBg);
            b.setForeground(btnFg);
            b.setFocusPainted(false);
            panelReservas.add(b);
        }
        panelCentral.add(panelReservas);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 15)));

        // --- Panel Check-in/Check-out ---
        JPanel panelCheck = new JPanel(new GridLayout(4, 1, 10, 10));
        panelCheck.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.ORANGE.darker(), 1, true),
                "Check-In y Check-Out",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(255, 140, 0)));
        panelCheck.setBackground(Color.WHITE);

        JButton btnCheckIn = new JButton("Realizar Check-In");
        JButton btnVerFacturas = new JButton("Ver Facturas");
        JButton btnEmitirFactura = new JButton("Imprimir Factura");
        JButton btnCheckOut = new JButton("Realizar Check-Out");

        for (JButton b : new JButton[]{btnCheckIn, btnVerFacturas, btnEmitirFactura, btnCheckOut}) {
            b.setFont(btnFont);
            b.setBackground(btnBg);
            b.setForeground(btnFg);
            b.setFocusPainted(false);
            panelCheck.add(b);
        }
        panelCentral.add(panelCheck);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 15)));

        // --- Panel Empleados y salida ---
        JPanel panelEmpleados = new JPanel(new GridLayout(3, 1, 10, 10)); // Actualizado a 3 filas
        panelEmpleados.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.MAGENTA.darker(), 1, true),
                "Empleados y Salir",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(139, 0, 139)));
        panelEmpleados.setBackground(Color.WHITE);

        JButton btnRegistrarEmpleado = new JButton("Registrar Empleado");
        JButton btnVerEmpleados = new JButton("Ver Empleados"); // NUEVO BOTÓN
        JButton btnSalir = new JButton("Salir");

        for (JButton b : new JButton[]{btnRegistrarEmpleado, btnVerEmpleados, btnSalir}) {
            b.setFont(btnFont);
            b.setBackground(btnBg);
            b.setForeground(btnFg);
            b.setFocusPainted(false);
            panelEmpleados.add(b);
        }
        panelCentral.add(panelEmpleados);

        // --- Acciones de botones ---
        btnRegistrarCliente.addActionListener(this::abrirFormularioCliente);
        btnBuscarCliente.addActionListener(this::abrirFormularioBuscarCliente);
        btnVerClientes.addActionListener(e -> mostrarLista("Clientes", servicio.getHotel().getListaClientes()));
        btnVerHabitaciones.addActionListener(e -> mostrarLista("Habitaciones", servicio.getHotel().getListaHabitaciones()));
        btnCrearReserva.addActionListener(this::abrirFormularioReserva);
        btnCancelarReserva.addActionListener(this::abrirFormularioCancelarReserva);
        btnVerReservas.addActionListener(e -> mostrarLista("Reservas", servicio.getHotel().getListaReservas()));
        btnCheckIn.addActionListener(this::abrirFormularioCheckIn);
        btnVerFacturas.addActionListener(e -> mostrarLista("Facturas", servicio.getHotel().getListaFacturas()));
        btnEmitirFactura.addActionListener(this::abrirFormularioFactura);
        btnCheckOut.addActionListener(this::abrirFormularioCheckOut);
        btnRegistrarEmpleado.addActionListener(e -> new FormularioEmpleado(servicio).setVisible(true));
        btnVerEmpleados.addActionListener(e -> mostrarLista("Empleados", servicio.getListaEmpleados())); // NUEVA ACCIÓN
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void abrirFormularioCliente(ActionEvent e) {
        new FormularioCliente(servicio).setVisible(true);
    }

    private void abrirFormularioBuscarCliente(ActionEvent e) {
        new FormularioBuscarCliente(servicio).setVisible(true);
    }

    private void abrirFormularioReserva(ActionEvent e) {
        new FormularioReserva(servicio).setVisible(true);
    }

    private void abrirFormularioCancelarReserva(ActionEvent e) {
        new FormularioCancelarReserva(servicio).setVisible(true);
    }

    private void abrirFormularioCheckIn(ActionEvent e) {
        new FormularioCheckIn(servicio).setVisible(true);
    }

    private void abrirFormularioFactura(ActionEvent e) {
        new FormularioFactura(servicio).setVisible(true);
    }

    private void abrirFormularioCheckOut(ActionEvent e) {
        new FormularioCheckOut(servicio).setVisible(true);
    }

    private void mostrarLista(String titulo, java.util.List<?> lista) {
        JTextArea area = new JTextArea(15, 30);
        area.setEditable(false);
        for (Object obj : lista) {
            area.append(obj.toString() + "\n\n");
        }
        JScrollPane scroll = new JScrollPane(area);
        JOptionPane.showMessageDialog(this, scroll, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}

