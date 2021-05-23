package dominio.rescate.Ubicacion;

import java.util.Comparator;

public class CoordenadasComparator implements Comparator<Coordenadas>{

  private Coordenadas coordsBase;
  

  public CoordenadasComparator(Coordenadas coordsBase) {
    this.coordsBase = coordsBase;
  }


  @Override
  public int compare(Coordenadas o1, Coordenadas o2) {
    double distancia1 = coordsBase.distanciaA(o1);
    double distancia2 = coordsBase.distanciaA(o2);

    if(distancia1 == distancia2) return  0;
    if(distancia1 <  distancia2) return -1;
    return 1;
  }
  
}
