package hh;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HotelServicio {

    private Hotel hotel;
    private int idReserva;

    public HotelServicio() {
        hotel = PersistenciaDatos.cargarHotel();
        if (hotel == null) {
            hotel = new Hotel();
        }
        idReserva = hotel.getListaReservas().isEmpty()
                ? 0
                : hotel.getListaReservas().get(hotel.getListaReservas().size() - 1).getId() + 1;
    }

    // --- Clientes ---
    public String registrarCliente(String nombre, String ci, String correo, String telefono, String direccion) {
        if (buscarClientePorCi(ci) != null) {
            return "❌ Ya existe un cliente con el CI: " + ci;
        }
        Cliente nuevo = new Cliente(
                hotel.getListaClientes().size(),
                nombre,
                ci,
                correo,
                telefono,
                direccion);
        hotel.agregarCliente(nuevo);
        PersistenciaDatos.guardarHotel(hotel);
        return "✅ Cliente registrado correctamente";
    }

    public Cliente buscarClientePorCi(String ci) {
        return hotel.getListaClientes().stream()
                .filter(c -> c.getCi().equals(ci))
                .findFirst()
                .orElse(null);
    }

    public void mostrarClientes() {
        hotel.mostrarClientes();
    }

    // --- Reservas ---
    public String crearReserva(String ci, int nroHabitacion, LocalDate fechaEntrada, LocalDate fechaSalida,
            int cantidadPersonas) {
        Cliente cliente = buscarClientePorCi(ci);
        if (cliente == null) {
            return "❌ Cliente no encontrado";
        }

        Habitacion habitacion = hotel.getListaHabitaciones().stream()
                .filter(h -> h.getNumero() == nroHabitacion)
                .findFirst()
                .orElse(null);

        if (habitacion == null) {
            return "❌ Habitación no encontrada";
        }

        if (!habitacion.estaDisponible()) {
            return "❌ Habitación no disponible";
        }

        boolean yaReservada = hotel.getListaReservas().stream()
                .anyMatch(r -> r.getHabitacion().getNumero() == nroHabitacion &&
                        fechasSeSolapan(r.getFechaEntrada(), r.getFechaSalida(), fechaEntrada, fechaSalida));

        if (yaReservada) {
            return "❌ Ya existe una reserva activa para esta habitación en esas fechas";
        }

        if (cantidadPersonas < 1 || cantidadPersonas > habitacion.getTipo().getCapacidad()) {
            return "❌ La cantidad de personas excede la capacidad de la habitación";
        }

        Reserva reserva = new Reserva(idReserva++, cliente, habitacion, fechaEntrada, fechaSalida, cantidadPersonas);
        hotel.crearReserva(reserva);
        PersistenciaDatos.guardarHotel(hotel);
        return "✅ Reserva creada correctamente";
    }

    private boolean fechasSeSolapan(LocalDate inicio1, LocalDate fin1, LocalDate inicio2, LocalDate fin2) {
        return !inicio1.isAfter(fin2) && !inicio2.isAfter(fin1);
    }

    public void mostrarReservas() {
        hotel.mostrarReservas();
    }

    public String cancelarReserva(int id) {
        Reserva reserva = hotel.getListaReservas().stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);

        if (reserva == null) {
            return "❌ Reserva no encontrada";
        }
        if (reserva.getCheckIn() != null) {
            return "❌ No se puede cancelar una reserva tras hacer Check-In";
        }

        // Liberar habitación
        reserva.getHabitacion().liberar();

        hotel.getListaReservas().remove(reserva);
        PersistenciaDatos.guardarHotel(hotel);
        return "✅ Reserva eliminada y habitación liberada";
    }

    // --- Facturas ---
    public String emitirFactura(int idReserva, MetodoPago metodoPago, String clienteCI) {
        Reserva reserva = hotel.getListaReservas().stream()
                .filter(r -> r.getId() == idReserva)
                .findFirst()
                .orElse(null);

        if (reserva == null) {
            return "❌ Reserva no encontrada";
        }

        Factura factura = new Factura(
                hotel.getListaFacturas().size(),
                reserva,
                metodoPago,
                clienteCI);

        hotel.emitirFactura(factura);
        PersistenciaDatos.guardarHotel(hotel);
        return "✅ Factura emitida:\n" + factura;
    }

    public void mostrarFacturas() {
        hotel.mostrarFacturas();
    }

    public String imprimirFactura(int reservaId, String ci) {
    // Buscar la factura que coincida con la reserva y el CI
    for (Factura factura : hotel.getListaFacturas()) {
        if (factura.getReserva().getId() == reservaId && factura.getClienteCI().equals(ci)) {

            // Validaciones adicionales
            if (factura.getReserva() == null) {
                return "❌ Error: La factura no tiene reserva asociada.";
            }

            if (factura.getReserva().getCheckOut() == null) {
                return "❌ Error: Aún no se realizó el Check-Out para esta reserva.";
            }

            if (factura.getMonto() <= 0) {
                return "❌ Error: El monto de la factura no es válido.";
            }

            return factura.toString(); // Todo correcto, devolver la factura
        }
    }

    return "❌ Error: No se encontró una factura para la reserva ID " + reservaId + " y CI " + ci + ".";
}

    // --- Check-in y Check-out ---
    
    public String checkIn(int idReserva, String ci) {
        Reserva reserva = hotel.getListaReservas().stream()
                .filter(r -> r.getId() == idReserva)
                .findFirst()
                .orElse(null);

        if (reserva == null) {
            return "❌ No existe reserva con ID " + idReserva;
        }

        Cliente cliente = buscarClientePorCi(ci);
        if (cliente == null) {
            return "❌ Cliente con CI " + ci + " no encontrado";
        }

        if (!reserva.getCliente().equals(cliente)) {
            return "❌ La reserva no pertenece al cliente con CI " + ci;
        }

        LocalDate hoy = LocalDate.now();
        if (hoy.isBefore(reserva.getFechaEntrada()) || hoy.isAfter(reserva.getFechaSalida())) {
            return "❌ La fecha actual no coincide con el rango de la reserva";
        }

        if (reserva.getCheckIn() != null) {
            return "❌ Check-in ya realizado para esta reserva";
        }

        reserva.setCheckIn(LocalDateTime.now());
        reserva.getHabitacion().ocupar();
        PersistenciaDatos.guardarHotel(hotel);
        return "✅ Check-in realizado para reserva " + idReserva + ", habitación "
                + reserva.getHabitacion().getNumero();
    }

    public String checkOut(int idReserva, String ci, MetodoPago metodoPago) {
        Reserva reserva = hotel.getListaReservas().stream()
                .filter(r -> r.getId() == idReserva)
                .findFirst()
                .orElse(null);

        if (reserva == null) {
            return "❌ No existe reserva con ID " + idReserva;
        }

        Cliente cliente = buscarClientePorCi(ci);
        if (cliente == null) {
            return "❌ Cliente con CI " + ci + " no encontrado";
        }

        if (!reserva.getCliente().equals(cliente)) {
            return "❌ La reserva no pertenece al cliente con CI " + ci;
        }

        if (reserva.getCheckIn() == null) {
            return "❌ No se puede hacer check-out sin hacer check-in";
        }

        if (reserva.getCheckOut() != null) {
            return "❌ Check-out ya realizado para esta reserva";
        }

        reserva.setCheckOut(LocalDateTime.now());
        reserva.getHabitacion().liberar();
        PersistenciaDatos.guardarHotel(hotel);

        emitirFactura(idReserva, metodoPago, ci);
        return "✅ Check-out realizado para reserva " + idReserva + ", habitación "
                + reserva.getHabitacion().getNumero();

        // Emitir factura automáticamente al hacer check-out
    }

      // --- Empleados ---

    public String registrarEmpleado(String nombre, String ci, String correo, String telefono, String direccion,
            String puesto, double salario, LocalDate fechaContratacion) {
        int nuevoId = hotel.getListaEmpleados().size();
        Empleado nuevo = new Empleado(nuevoId, nombre, ci, correo, telefono, direccion, puesto, salario,
                fechaContratacion);
        hotel.getListaEmpleados().add(nuevo);
        PersistenciaDatos.guardarHotel(hotel);
        return "✅ Empleado registrado correctamente";
    }

    public java.util.List<Empleado> getListaEmpleados() {
        return hotel.getListaEmpleados();
    }

    public Hotel getHotel() {
        return hotel;
    }
}
