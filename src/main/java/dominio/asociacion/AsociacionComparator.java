package dominio.asociacion;

import java.util.Comparator;

import dominio.ubicacion.Coordenadas;

public class AsociacionComparator implements Comparator<Asociacion>{

  private Coordenadas coordenadas;

  public AsociacionComparator(Coordenadas coordenadas) {
    this.coordenadas = coordenadas;
  }

  @Override
  public int compare(Asociacion o1, Asociacion o2) {
    Double distanciaA = coordenadas.distanciaA(o1.getUbicacion());
    Double distanciaB = coordenadas.distanciaA(o2.getUbicacion());

    return distanciaA.compareTo(distanciaB);

  }
}
