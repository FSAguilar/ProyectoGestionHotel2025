package hh;

public enum TipoHabitacion {
    SIMPLE(1),
    DOBLE(2),
    TRIPLE(3),
    MATRIMONIAL(2);

    private final int capacidad;

    TipoHabitacion(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCapacidad() {
        return capacidad;
    }
}