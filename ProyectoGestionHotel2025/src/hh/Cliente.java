// hh/Cliente.java
package hh;
import java.io.Serializable;

public class Cliente extends Persona implements Serializable {

    public Cliente(int id, String nombre, String ci, String correo, String telefono, String direccion) {
        super(id, nombre, ci, correo, telefono, direccion);
    }
    
    

    // No hace falta redeclarar getters/setters:
    // Hereda getId(), setId(), getNombre(), setNombre(), etc.

    @Override
    public String toString() {
        return getNombre() + " (" + getCorreo() + ")\n" +
               "CI: " + getCi() + "\n" +
               "Teléfono: " + getTelefono() + "\n" +
               "Dirección: " + getDireccion();
    }

}
