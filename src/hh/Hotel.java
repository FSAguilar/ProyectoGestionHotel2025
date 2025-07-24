package hh;

//CLASE Hotel
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.Serializable;

public class Hotel implements Serializable{
    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Habitacion> listaHabitaciones;
    private List<Reserva> listaReservas = new ArrayList<>();
    private List<Factura> listaFacturas = new ArrayList<>();
    private List<Empleado> listaEmpleados = new ArrayList<>();

    public Hotel() {
        Habitacion[] habitaciones = new Habitacion[] {
                // Planta 1
                new Habitacion(101, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(102, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(103, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(104, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.MANTENIMIENTO),
                new Habitacion(105, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(106, TipoHabitacion.DOBLE, 80.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(107, TipoHabitacion.DOBLE, 80.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(108, TipoHabitacion.DOBLE, 80.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(109, TipoHabitacion.MATRIMONIAL, 90.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(110, TipoHabitacion.MATRIMONIAL, 90.0, EstadoHabitacion.MANTENIMIENTO),

                // Planta 2
                new Habitacion(201, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(202, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(203, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(204, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(205, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.MANTENIMIENTO),
                new Habitacion(206, TipoHabitacion.DOBLE, 80.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(207, TipoHabitacion.DOBLE, 80.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(208, TipoHabitacion.DOBLE, 80.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(209, TipoHabitacion.MATRIMONIAL, 90.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(210, TipoHabitacion.MATRIMONIAL, 90.0, EstadoHabitacion.DISPONIBLE),
                
                // Planta 3            
                new Habitacion(301, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(302, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(303, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.MANTENIMIENTO),
                new Habitacion(304, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(305, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(306, TipoHabitacion.DOBLE, 80.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(307, TipoHabitacion.DOBLE, 80.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(308, TipoHabitacion.DOBLE, 80.0, EstadoHabitacion.MANTENIMIENTO),
                new Habitacion(309, TipoHabitacion.MATRIMONIAL, 90.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(310, TipoHabitacion.MATRIMONIAL, 90.0, EstadoHabitacion.DISPONIBLE),

                // Planta 4
                new Habitacion(401, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(402, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(403, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(404, TipoHabitacion.TRIPLE, 120.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(405, TipoHabitacion.TRIPLE, 120.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(406, TipoHabitacion.TRIPLE, 120.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(407, TipoHabitacion.TRIPLE, 120.0, EstadoHabitacion.MANTENIMIENTO),
                new Habitacion(408, TipoHabitacion.TRIPLE, 120.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(409, TipoHabitacion.MATRIMONIAL, 90.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(410, TipoHabitacion.MATRIMONIAL, 90.0, EstadoHabitacion.DISPONIBLE),

                // Planta 5
                new Habitacion(501, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(502, TipoHabitacion.SIMPLE, 50.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(503, TipoHabitacion.DOBLE, 80.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(504, TipoHabitacion.DOBLE, 80.0, EstadoHabitacion.MANTENIMIENTO),
                new Habitacion(505, TipoHabitacion.DOBLE, 80.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(506, TipoHabitacion.TRIPLE, 120.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(507, TipoHabitacion.TRIPLE, 120.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(508, TipoHabitacion.TRIPLE, 120.0, EstadoHabitacion.DISPONIBLE),
                new Habitacion(509, TipoHabitacion.MATRIMONIAL, 90.0, EstadoHabitacion.MANTENIMIENTO),
                new Habitacion(510, TipoHabitacion.MATRIMONIAL, 90.0, EstadoHabitacion.DISPONIBLE)
        };

        this.listaHabitaciones = Arrays.asList(habitaciones);

    }

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void agregarHabitacion(Habitacion habitacion) {
        listaHabitaciones.add(habitacion);
    }

    public void crearReserva(Reserva reserva) {
        listaReservas.add(reserva);
    }

    public void emitirFactura(Factura factura) {
        listaFacturas.add(factura);
    }

    public void mostrarClientes() {
        listaClientes.forEach(System.out::println);
    }

    public void mostrarHabitaciones() {
        listaHabitaciones.forEach(System.out::println);
    }

    public void mostrarReservas() {
        listaReservas.forEach(System.out::println);
    }

    public void mostrarFacturas() {
        listaFacturas.forEach(System.out::println);
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public List<Factura> getListaFacturas() {
        return listaFacturas;
    }
    
    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void mostrarEmpleados() {
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados contratados.");
            return;
        }
        System.out.println("=== Empleados contratados ===");
        for (Empleado e : listaEmpleados) {
            System.out.println(e);
        }
    }
}
