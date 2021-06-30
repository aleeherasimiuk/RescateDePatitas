package dominio.asociacion;

import dominio.preguntas.Pregunta;
import dominio.ubicacion.Coordenadas;
import dominio.util.Lista;

public class Asociacion{

  private final String nombre;
  private final Coordenadas ubicacion;
  private final Lista<Pregunta> preguntasAdopcion;

  public Asociacion(String nombre, Coordenadas ubicacion) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.preguntasAdopcion = new Lista<>();
  }

  public double distanciaA(Coordenadas coordenadas){
    return ubicacion.distanciaA(coordenadas);
  }

  public String getNombre() {
    return nombre;
  }

  public Coordenadas getUbicacion() {
    return ubicacion;
  }

  public int cantidadDePreguntas() {
    return preguntasAdopcion.size();
  }
}
