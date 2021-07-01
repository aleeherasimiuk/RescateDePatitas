package dominio.exceptions;

public class ErrorAlBuscarHogaresDisponibles extends RuntimeException {
  public ErrorAlBuscarHogaresDisponibles() {
    super("Hubo un error al buscar los hogares disponibles");
  }
}
