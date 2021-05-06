package dominio.rescate;

public class Coordenadas {
  private Double latitud;
  private Double longitud;

  public Coordenadas(Double latitud, Double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public Double getLatitud() {
    return latitud;
  }

  public Double getLongitud() {
    return longitud;
  }
}
