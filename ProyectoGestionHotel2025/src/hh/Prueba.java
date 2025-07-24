package hh;

import java.time.LocalDate;

public class Prueba {
    public static void main(String[] args) {
        HotelServicio servicio = new HotelServicio();
        System.out.println("✅ Datos cargados correctamente.");

        // Mostrar clientes antes
        System.out.println("=== CLIENTES ANTES ===");
        servicio.mostrarClientes();

        // Registrar clientes (si no existen)
        System.out.println("\nRegistrando clientes...");
        String[][] clientes = {
            {"Mateo Persistente", "12345678", "mateo@email.com", "7654321", "La Paz"},
            {"Ana Rivera", "87654321", "ana@email.com", "7123456", "El Alto"},
            {"Carlos Invasor", "99999999", "carlos@email.com", "7111222", "Cochabamba"}
        };

        for (String[] datos : clientes) {
            if (servicio.buscarClientePorCi(datos[1]) == null) {
                servicio.registrarCliente(datos[0], datos[1], datos[2], datos[3], datos[4]);
                System.out.println("✅ Cliente registrado correctamente: " + datos[0]);
            } else {
                System.out.println("❌ Ya existe un cliente con el CI: " + datos[1]);
            }
        }

        // Mostrar clientes después
        System.out.println("\n=== CLIENTES DESPUÉS ===");
        servicio.mostrarClientes();

        // Mostrar habitaciones disponibles
        System.out.println("\n=== HABITACIONES DISPONIBLES ===");
        servicio.getHotel().getListaHabitaciones().stream()
            .filter(Habitacion::estaDisponible)
            .forEach(System.out::println);

        // Verificar si Mateo ya tiene reserva en habitación 101
        boolean reservaMateoExiste = servicio.getHotel().getListaReservas().stream()
            .anyMatch(r -> r.getCliente().getCi().equals("12345678") && r.getHabitacion().getNumero() == 101);

        if (!reservaMateoExiste) {
            LocalDate entrada = LocalDate.now().plusDays(1);
            LocalDate salida = entrada.plusDays(2);
            System.out.println("\nCreando reserva para Mateo...");
            servicio.crearReserva("12345678", 101, entrada, salida, 1);
        } else {
            System.out.println("\n❌ Ya existe una reserva para Mateo en habitación 101, no se vuelve a crear.");
        }

        // Mostrar reservas actuales
        System.out.println("\n=== RESERVAS ACTUALES ===");
        servicio.mostrarReservas();

        // Intentar crear reserva para Carlos en habitación 101 (debería fallar)
        LocalDate entradaCarlos = LocalDate.now().plusDays(1);
        LocalDate salidaCarlos = entradaCarlos.plusDays(2);
        System.out.println("\nIntentando crear reserva para Carlos en habitación 101...");
        servicio.crearReserva("99999999", 101, entradaCarlos, salidaCarlos, 1);

        // Mostrar reservas finales
        System.out.println("\n=== RESERVAS FINALES ===");
        servicio.mostrarReservas();

        // Emitir factura solo si no existe factura para Mateo
        boolean facturaMateoExiste = servicio.getHotel().getListaFacturas().stream()
            .anyMatch(f -> f.getClienteCI().equals("12345678"));

        if (!facturaMateoExiste) {
            Reserva reservaMateo = servicio.getHotel().getListaReservas().stream()
                .filter(r -> r.getCliente().getCi().equals("12345678"))
                .findFirst().orElse(null);

            if (reservaMateo != null) {
                reservaMateo.setCheckOut(reservaMateo.getFechaSalida().atTime(13, 0));
                System.out.println("\nEmitiendo factura para Mateo...");
                servicio.emitirFactura(reservaMateo.getId(), MetodoPago.EFECTIVO, "12345678");
            }
        } else {
            System.out.println("\n❌ Ya existe una factura para Mateo, no se emite nuevamente.");
        }

        System.out.println("\n=== FACTURAS ===");
        servicio.mostrarFacturas();

        // Guardar todo
        System.out.println("\nGuardando datos...");
        PersistenciaDatos.guardarHotel(servicio.getHotel());
        servicio.cancelarReserva(0);
    }
}

