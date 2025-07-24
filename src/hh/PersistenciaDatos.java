package hh;

import java.io.*;

public class PersistenciaDatos {

    private static final String ARCHIVO = "hotel-datos.dat";

    public static void guardarHotel(Hotel hotel) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            out.writeObject(hotel);
            System.out.println("✅ Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar los datos: " + e.getMessage());
        }
    }

    public static Hotel cargarHotel() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            System.out.println("⚠️ No se encontró archivo de datos, se creará uno nuevo.");
            return new Hotel();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            Hotel hotel = (Hotel) in.readObject();
            System.out.println("✅ Datos cargados correctamente.");
            return hotel;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Error al cargar los datos: " + e.getMessage());
            return new Hotel();
        }
    }
}

