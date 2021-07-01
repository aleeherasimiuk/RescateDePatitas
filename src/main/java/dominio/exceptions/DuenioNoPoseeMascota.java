package dominio.exceptions;

public class DuenioNoPoseeMascota extends RuntimeException {
  public DuenioNoPoseeMascota(String nombre) {
    super("El duenio no posee la mascota: " + nombre);
  }
}
