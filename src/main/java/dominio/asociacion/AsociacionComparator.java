package dominio.asociacion;

import java.util.Comparator;

import dominio.rescate.Rescate;

public class AsociacionComparator implements Comparator<Asociacion>{

  private Rescate rescate;

  public AsociacionComparator(Rescate rescate) {
    this.rescate = rescate;
  }

  @Override
  public int compare(Asociacion o1, Asociacion o2) {
    Double distanciaA = rescate.getLugar().distanciaA(o1.getUbicacion());
    Double distanciaB = rescate.getLugar().distanciaA(o2.getUbicacion());

    return distanciaA.compareTo(distanciaB);

  }
}
