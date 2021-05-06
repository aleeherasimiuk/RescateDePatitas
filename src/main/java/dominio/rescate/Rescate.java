package dominio.rescate;

import java.time.LocalDate;

import dominio.rescate.Rescatista;
import dominio.mascota.Mascota;
import dominio.ubicacion.Coordenadas;
import dominio.util.Lista;

import java.time.temporal.ChronoUnit;

public class Rescate {
  private Rescatista rescatista;
  private Mascota mascota;
  private Lista<String> fotos;
  private String descripcion;
  private Coordenadas lugar;
  private LocalDate fecha;

  public Rescate(Rescatista rescatista, Mascota mascota, String descripcion, LocalDate fecha) {
    this.rescatista = rescatista;
    this.mascota = mascota;
    this.descripcion = descripcion;
    this.fecha = fecha;
    this.fotos = new Lista<String>();
  }

  public void agregarUnaFoto(String url) {
    fotos.add(url);
  }

  public void setLugar(Coordenadas lugar) {
    this.lugar = lugar;
  }

  public Boolean sucedioDentroDeLosUltimos10Dias() {
    return Math.abs(ChronoUnit.DAYS.between(LocalDate.now(), fecha)) <= 10;
  }

  public Mascota getMascota() {
    return this.mascota;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Coordenadas getLugar() {
    return lugar;
  }

  public int telefonoDeContacto() {
    return rescatista.getContacto().getTelefono();
  }

}
