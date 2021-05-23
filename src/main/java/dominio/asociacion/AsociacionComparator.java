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
    double distanciaA = rescate.getLugar().distanciaA(o1.getUbicacion());
    double distanciaB = rescate.getLugar().distanciaA(o2.getUbicacion());

    if(distanciaA == distanciaB) return 0;
    if(distanciaA <  distanciaB) return -1;
    return 1;
  }
}
