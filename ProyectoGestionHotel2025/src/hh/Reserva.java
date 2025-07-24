
package hh;

//CLASE Reserva
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.io.Serializable;

public class Reserva implements Serializable{
    private int id;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private int cantidadPersonas;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public Reserva(int id, Cliente cliente, Habitacion habitacion, LocalDate fechaEntrada, LocalDate fechaSalida,
            int cantidadPersonas) {
        this.id = id;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.cantidadPersonas = cantidadPersonas;
    }

    public int calcularDias() {
        return (int) ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Reserva ID: " + id + "\n" +
               "Cliente: " + cliente.getNombre() + "\n" +
               "Habitación: " + habitacion.getNumero() + " - " + habitacion.getTipo() + "\n" +
               "Fecha Entrada: " + fechaEntrada + "\n" +
               "Fecha Salida: " + fechaSalida + "\n" +
               "Cantidad de Personas: " + cantidadPersonas + "\n" +
               "Días reservados: " + calcularDias() + "\n" +
               (checkIn != null ? "Check-In: " + checkIn + "\n" : "") +
               (checkOut != null ? "Check-Out: " + checkOut + "\n" : "");
    }

}
