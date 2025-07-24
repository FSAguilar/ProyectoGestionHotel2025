package hh;

import java.time.LocalDate;
import java.io.Serializable;

public class Empleado extends Persona implements Serializable{
    private String puesto;
    private double salario;
    private LocalDate fechaContratacion;

    public Empleado(int id,
                    String nombre,
                    String ci,
                    String correo,
                    String telefono,
                    String direccion,
                    String puesto,
                    double salario,
                    LocalDate fechaContratacion) {
        super(id, nombre, ci, correo, telefono, direccion);
        this.puesto = puesto;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
    }

    // Getters y Setters
    public String getPuesto() {
        return puesto;
    }
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }
    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nPuesto: " + puesto +
               "\nSalario: " + salario +
               "\nFecha de contratación: " + fechaContratacion;
    }
}
