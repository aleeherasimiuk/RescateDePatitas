package dominio.asociacion;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import dominio.preguntas.Pregunta;
import dominio.ubicacion.Coordenadas;
import persistencia.PersistentEntity;

@Entity
@Table(name="asociacion")
public class Asociacion extends PersistentEntity{

  private String nombre;
  @Embedded
  private Coordenadas ubicacion;
  @OneToMany
  private List<Pregunta> preguntas;

  protected Asociacion() {}

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
