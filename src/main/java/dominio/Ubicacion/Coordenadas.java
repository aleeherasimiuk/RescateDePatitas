package dominio.Ubicacion;

public class Coordenadas{
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

  /* TODO: Implementar aquí (o en el comparator) una api de maps. Calcular la distancia cartesiana no tiene sentido en un planeta esfétrico*/

  public double distanciaA(Coordenadas coordenadas){
    return Math.hypot(latitud - coordenadas.getLatitud(), longitud - coordenadas.getLongitud());
  }

  public Coordenadas elMasCercano(Coordenadas unasCoordenadas, Coordenadas otrasCoordenadas){
    int comparacion = new CoordenadasComparator(this).compare(unasCoordenadas, otrasCoordenadas);

    return comparacion <= 0? unasCoordenadas : otrasCoordenadas;
  }

}
