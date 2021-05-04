package dominio.ubicacion;

public class Coordenadas {
  private String latitud;
  private String longitud;

  public Coordenadas(String latitud, String longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public String getLatitud() {
    return latitud;
  }

  public String getLongitud() {
    return longitud;
  }
}
