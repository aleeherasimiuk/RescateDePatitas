package dominio.sistema;

import java.time.LocalDate;
import java.util.List;

import dominio.mascota.Mascota;
import dominio.personas.Persona;
import dominio.ubicacion.Coordenadas;
import java.time.temporal.ChronoUnit;

public class Rescate {
  private Persona persona; // TODO chequear si esto no deberia ser Rescatista
  private Mascota mascota;
  private List<String> fotos;
  private String descripcion;
  private Coordenadas lugar;
  private LocalDate fecha;

  public Rescate(Persona persona, Mascota mascota, String descripcion,LocalDate fecha) {
    this.persona = persona;
    this.mascota = mascota;
    this.descripcion = descripcion;
    this.fecha = fecha;
  }

  public void setFotos(List<String> fotos) {
    this.fotos = fotos;
  }


  public void setLugar(Coordenadas lugar) {
    this.lugar = lugar;
  }


  public Boolean sucedioDentroDeLosUltimos10Dias() {
    return Math.abs(ChronoUnit.DAYS.between(LocalDate.now(),fecha)) <= 10 ;
  }

  public Mascota getMascota() {
    return this.mascota;
  }

}
