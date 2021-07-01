package dominio.exceptions;

public class NoSePuedeLeerArchivoConfig extends RuntimeException {
  public NoSePuedeLeerArchivoConfig() {
    super("No se ha podido leer el archivo de configuracion");
  }
}
