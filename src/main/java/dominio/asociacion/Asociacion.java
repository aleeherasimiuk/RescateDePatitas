package dominio.asociacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import dominio.preguntas.Pregunta;
import dominio.ubicacion.Coordenadas;
import persistencia.PersistentEntity;

@Entity
public class Asociacion extends PersistentEntity{

  private final String nombre;
  @Embedded
  private final Coordenadas ubicacion;
  @OneToMany
  private final List<Pregunta> preguntas;

  public Asociacion(String nombre, Coordenadas ubicacion) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.preguntas = new ArrayList<>();
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
    return preguntas.size();
  }

  public void agregarPregunta(Pregunta pregunta){
    preguntas.add(pregunta);
  }

  public List<Pregunta> getPreguntasAdopcion() {
    return preguntas;
  }
}
