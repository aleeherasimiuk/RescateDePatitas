package dominio.exceptions;

public class NoHayAsociacionAsignadaAlRescate extends RuntimeException {
  public NoHayAsociacionAsignadaAlRescate() {
    super("No se ha asignado ninguna asociación");
  }
}
