package dominio.ubicacion;

import java.util.Comparator;

public class CoordenadasComparator implements Comparator<Coordenadas>{

  private Coordenadas coordsBase;
  

  public CoordenadasComparator(Coordenadas coordsBase) {
    this.coordsBase = coordsBase;
  }


  @Override
  public int compare(Coordenadas o1, Coordenadas o2) {
    Double distancia1 = coordsBase.distanciaA(o1);
    Double distancia2 = coordsBase.distanciaA(o2);

    return distancia1.compareTo(distancia2);
  }
  
}
