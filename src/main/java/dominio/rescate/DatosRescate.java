package dominio.rescate;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import dominio.hogares.Hogar;
import dominio.ubicacion.Coordenadas;
import persistencia.PersistentEntity;
import persistencia.convertidores.ConvertidorLocalDate;

@Entity
@Table(name="rescates")
public class DatosRescate extends PersistentEntity{
  
  @ManyToOne(cascade = CascadeType.MERGE)
  private Rescatista rescatista;
  @ElementCollection
  @CollectionTable(name = "fotos_rescate", joinColumns=@JoinColumn(name="rescate_id"))
  @Column(name="url")
  private List<String> fotos_rescate;
  @Convert(converter = ConvertidorLocalDate.class)
  private LocalDate fecha;
  private String descripcion;
  @Embedded
  private Coordenadas lugar;
  @ManyToOne
  private Hogar hogar;

  @Column(name="encontrada")
  private boolean fueEncontrada;

  public DatosRescate(Rescatista rescatista, List<String> fotos, LocalDate fecha, String descripcion,
      Coordenadas lugar) {
    this.rescatista = rescatista;
    this.fotos_rescate = fotos;
    this.fecha = fecha;
    this.descripcion = descripcion;
    this.lugar = lugar;
    this.fueEncontrada = false;
  }

  protected DatosRescate(){}

  public boolean fueEncontrada() {
    return fueEncontrada;
  }

  public void confirmarEncuentro() {
    this.fueEncontrada = true;
  }

  public Hogar getHogar() {
    return hogar;
  }

  public void setHogar(Hogar hogar) {
    this.hogar = hogar;
  }

  public Rescatista getRescatista() {
    return rescatista;
  }

  public List<String> getFotos() {
    return fotos_rescate;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Coordenadas getLugar() {
    return lugar;
  }
}
