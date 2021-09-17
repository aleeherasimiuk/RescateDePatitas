package dominio.rescate;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import dominio.hogares.Hogar;
import dominio.ubicacion.Coordenadas;
import persistencia.convertidores.ConvertidorLocalDate;

@Embeddable
public class DatosRescate {
  @ManyToOne
  private final Rescatista rescatista;
  @ElementCollection
  @CollectionTable(name = "caracteristicas", joinColumns=@JoinColumn(name="mascota_id"))
  private final List<String> fotos;
  @Convert(converter = ConvertidorLocalDate.class)
  private final LocalDate fecha;
  private final String descripcion;
  @Embedded
  private final Coordenadas lugar;
  @ManyToOne
  private Hogar hogar;
  private boolean fueEncontrada;

  public DatosRescate(Rescatista rescatista, List<String> fotos, LocalDate fecha, String descripcion,
      Coordenadas lugar) {
    this.rescatista = rescatista;
    this.fotos = fotos;
    this.fecha = fecha;
    this.descripcion = descripcion;
    this.lugar = lugar;
    this.fueEncontrada = false;
  }

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
    return fotos;
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
