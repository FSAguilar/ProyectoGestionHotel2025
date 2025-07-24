package hh;
import java.io.Serializable;

//CLASE Habitacion
public class Habitacion implements Serializable{
    private int numero;
    private TipoHabitacion tipo;
    private double precio;
    private EstadoHabitacion estado;

    public Habitacion(int numero, TipoHabitacion tipo, double precio, EstadoHabitacion estado) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
    }

    public int getNumero() { return numero; }
    public TipoHabitacion getTipo() { return tipo; }
    public double getPrecio() { return precio; }
    public EstadoHabitacion getEstado() { return estado; }

    public void setNumero(int numero) { this.numero = numero; }
    public void setTipo(TipoHabitacion tipo) { this.tipo = tipo; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setEstado(EstadoHabitacion estado) { this.estado = estado; }

    public boolean estaDisponible() {
        return estado == EstadoHabitacion.DISPONIBLE;
    }

    public void ocupar() {
        estado = EstadoHabitacion.OCUPADA;
    }

    public void liberar() {
        estado = EstadoHabitacion.DISPONIBLE;
    }

    @Override
    public String toString() {
        return "Habitación " + numero + " - " + tipo + " - " + estado;
    }
}