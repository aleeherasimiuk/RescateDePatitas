package dominio.hogares;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import dominio.mascota.ClaseMascota;
import dominio.mascota.Tamanio;
import dominio.ubicacion.Coordenadas;
import persistencia.PersistentEntity;

@Entity
public class Hogar extends PersistentEntity{
  private final String nombre;
  private final String telefono;
  @ElementCollection
  @CollectionTable(name = "preferencias", joinColumns=@JoinColumn(name="hogar_id"))
  private final List<ClaseMascota> preferencias;
  private final Boolean tienePatio;
  @ElementCollection
  @CollectionTable(name = "caracteristicas_especificas", joinColumns=@JoinColumn(name="hogar_id"))
  private final List<String> caracteristicasEspecificas;
  @Embedded
  private final Coordenadas ubicacion;
  private Boolean tieneCapacidad;

  public Hogar(String nombre, String telefono, List<ClaseMascota> preferencias, Boolean tienePatio, List<String> caracteristicasEspecificas,
      Coordenadas ubicacion, Boolean tieneCapacidad) {
    this.nombre = nombre;
    this.telefono = telefono;
    this.preferencias = preferencias;
    this.tienePatio = tienePatio;
    this.caracteristicasEspecificas = caracteristicasEspecificas;
    this.ubicacion = ubicacion;
    this.tieneCapacidad = tieneCapacidad;
  }

  public Boolean aceptaMascota(ClaseMascota claseMascota, Tamanio tamanio){
    return tieneCapacidad && aceptaClase(claseMascota) && aceptaTamanio(tamanio);
  }

  private Boolean aceptaClase(ClaseMascota claseMascota){
    return preferencias.contains(claseMascota);
  }

  private Boolean aceptaTamanio(Tamanio tamanio){
    return tienePatio || esChico(tamanio);
  }

  private boolean esChico(Tamanio tamanio) {
    return tamanio == Tamanio.CHICO;
  }

  public String getNombre() {
    return nombre;
  }

  public String getTelefono() {
    return telefono;
  }

  public List<ClaseMascota> getPreferencias() {
    return preferencias;
  }

  public List<String> getCaracteristicasEspecificas() {
    return caracteristicasEspecificas;
  }

  public Coordenadas getUbicacion() {
    return ubicacion;
  }

  public boolean tieneCapacidad() {
    return tieneCapacidad;
  }

  public boolean tienePatio() {
    return tienePatio;
  }

  public boolean matcheaCaracteristica(List<String> caracteristicas) {
    return caracteristicas.containsAll(caracteristicasEspecificas);
  }

}
