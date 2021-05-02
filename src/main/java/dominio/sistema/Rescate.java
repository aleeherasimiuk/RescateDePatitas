package dominio.sistema;

import java.time.LocalDate;
import java.util.List;

import dominio.mascota.Mascota;
import dominio.personas.Persona;
import dominio.ubicacion.Coordenadas;

public class Rescate {
  private Persona persona; // TODO chequear si esto no deberia ser Rescatista
  private Mascota mascota;
  private List<String> fotos;
  private String descripcion;
  private Coordenadas lugar;
  private LocalDate fecha;

  public Rescate(Persona persona, Mascota mascota, List<String> fotos, String descripcion,  Coordenadas lugar) {
    this.persona = persona;
    this.mascota = mascota;
    this.fotos = fotos;
    this.descripcion = descripcion;
    this.lugar = lugar;
    this.fecha = LocalDate.now();
  }
}
