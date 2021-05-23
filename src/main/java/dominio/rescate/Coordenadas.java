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

  /* TODO: Implementar aquí una api de maps. Calcular la distancia cartesiana no tiene sentido en un planeta esfétrico*/


  public double distanciaA(Coordenadas coordenadas){
    return Math.hypot(latitud - coordenadas.getLatitud(), longitud - coordenadas.getLongitud());
  }

  public Coordenadas elMasCercano(Coordenadas unasCoordenadas, Coordenadas otrasCoordenadas){
    double distancia1 = this.distanciaA(unasCoordenadas);
    double distancia2 = this.distanciaA(otrasCoordenadas);

    return distancia1 < distancia2? unasCoordenadas : otrasCoordenadas;
  }
}
