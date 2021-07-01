package dominio.exceptions;

public class YaHayUnaAsociacionAsignada extends RuntimeException {
  public YaHayUnaAsociacionAsignada() {
    super("La publicación ya tiene una asociación asignada");
  }
}
