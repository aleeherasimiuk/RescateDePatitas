package dominio.exceptions;

public class NoHayAsociacionesDisponibles extends RuntimeException{

  public NoHayAsociacionesDisponibles() {
    super("No hay asociaciones disponibles en este momento");
  }

  
  
}
