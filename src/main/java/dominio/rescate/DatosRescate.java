package dominio.rescate;

import java.time.LocalDate;
import java.util.List;

import dominio.Ubicacion.Coordenadas;
import dominio.hogares.Hogar;

public class DatosRescate {
  private final Rescatista rescatista;
  private final List<String> fotos;
  private final LocalDate fecha;
  private final String descripcion;
  private final Coordenadas lugar;
  private Hogar hogar;

  public DatosRescate(Rescatista rescatista, List<String> fotos, LocalDate fecha, String descripcion,
      Coordenadas lugar) {
    this.rescatista = rescatista;
    this.fotos = fotos;
    this.fecha = fecha;
    this.descripcion = descripcion;
    this.lugar = lugar;
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
