package dominio.exceptions;

public class HogarNoAceptaMascota extends RuntimeException {
  public HogarNoAceptaMascota() {
    super("El hogar solicitado no acepta a la mascota");
  }
}
