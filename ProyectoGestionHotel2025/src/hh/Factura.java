// CLASE Factura
package hh;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.io.Serializable;

public class Factura implements Serializable {
    private int id;
    private Reserva reserva;
    private double monto;
    private MetodoPago metodoPago;
    private LocalDate fechaEmision = LocalDate.now();
    private String clienteCI;

    public Factura(int id, Reserva reserva, MetodoPago metodoPago, String clienteCI) {
        this.id = id;
        this.reserva = reserva;
        this.metodoPago = metodoPago;
        this.monto = calcularTotal();
        this.clienteCI = clienteCI;
    }

    public double calcularTotal() {

        if (reserva.getCheckOut() == null) {
            throw new IllegalStateException("No se ha realizado el check‑out aún");
        }

        //Check out tarde (Despues de medio dia) = 1 dia extra
        //Se toman en cuenta dias extras para el calculo de la factura

        long diasExtra = ChronoUnit.DAYS.between(
                reserva.getFechaSalida(),
                reserva.getCheckOut().toLocalDate());

        int extra = (int) Math.max(0, diasExtra);

        if (reserva.getCheckOut().toLocalTime().isAfter(LocalTime.NOON)) extra++;
        return (reserva.calcularDias() + extra) * reserva.getHabitacion().getPrecio();
    }

    public int getId() {
        return id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public double getMonto() {
        return monto;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public String getClienteCI() {
        return clienteCI;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
        this.monto = calcularTotal();
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        return "===== FACTURA =====\n" +
                "ID: " + id + "\n" +
                "Cliente: " + reserva.getCliente().getNombre() + "\n" +
                "CI: " + clienteCI + "\n" +
                "Habitación: " + reserva.getHabitacion().getNumero() + "\n" +
                "Días: " + reserva.calcularDias() + "\n" +
                "Método de Pago: " + metodoPago + "\n" +
                "Monto Total: Bs " + monto + "\n" +
                "Fecha de Emisión: " + fechaEmision + "\n" +
                "===================";
    }

}
