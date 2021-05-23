package dominio.asociacion;

import dominio.rescate.Coordenadas;

public class Asociacion {

  private final String nombre;
  private final Coordenadas ubicacion;


  public Asociacion(String nombre, Coordenadas ubicacion) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
  }

  public double distanciaA(Coordenadas coordenadas){
    return ubicacion.distanciaA(coordenadas);
  }
  
  public String getNombre() {
    return nombre;
  }
}
