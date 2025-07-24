package hh;

import java.util.Objects;
import java.io.Serializable;

public class Persona implements Serializable {

    private int id;
    private String nombre;
    private String ci;
    private String correo;
    private String telefono;
    private String direccion;

    public Persona(int id, String nombre, String ci, String correo, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.ci = ci;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Getters y Setters (los nombres no cambian)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return nombre + " (" + correo + ")\nCI: " + ci;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ci);
    }
}