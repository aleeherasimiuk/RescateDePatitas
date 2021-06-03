package dominio.hogares;

import dominio.mascota.ClaseMascota;
import dominio.mascota.Tamanio;
import dominio.ubicacion.Coordenadas;
import dominio.util.Lista;
import servicios.hogares.HogaresService;

public class Hogar {
  private final String nombre;
  private final String telefono;
  private final Lista<ClaseMascota> preferencias;
  private final Boolean tienePatio;
  private final Lista<String> caracteristicasEspecificas;
  private final Coordenadas ubicacion;
  private Boolean tieneCapacidad;

  public Hogar(String nombre, String telefono, Lista<ClaseMascota> preferencias, Boolean tienePatio, Lista<String> caracteristicasEspecificas,
      Coordenadas ubicacion, Boolean tieneCapacidad) {
    this.nombre = nombre;
    this.telefono = telefono;
    this.preferencias = preferencias;
    this.tienePatio = tienePatio;
    this.caracteristicasEspecificas = caracteristicasEspecificas;
    this.ubicacion = ubicacion;
    this.tieneCapacidad = tieneCapacidad;
  }

  //TODO: WTF?
	public static Lista<Hogar> getHogares(HogaresService service) {
    return service.getListadoHogares();
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

  public Lista<ClaseMascota> getPreferencias() {
    return preferencias;
  }

  public Lista<String> getCaracteristicasEspecificas() {
    return caracteristicasEspecificas;
  }

  public Coordenadas getUbicacion() {
    return ubicacion;
  }



}
