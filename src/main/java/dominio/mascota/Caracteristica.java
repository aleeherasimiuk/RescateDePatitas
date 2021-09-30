package dominio.mascota;

import javax.persistence.Entity;
import javax.persistence.Table;

import persistencia.PersistentEntity;

@Entity
@Table(name = "caracteristicas")
public class Caracteristica extends PersistentEntity{
  private String nombre;

  public Caracteristica(String nombre) {
    this.nombre = nombre;
  }

  protected Caracteristica(){}
  
  public String getNombre() {
    return nombre;
  }
}
