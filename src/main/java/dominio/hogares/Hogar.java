package dominio.hogares;

import dominio.Ubicacion.Coordenadas;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Mascota;
import dominio.mascota.Tamanio;
import dominio.util.Lista;
import services.hogares.HogaresService;

public class Hogar {
  private String nombre;
  private String telefono;
  private Lista<ClaseMascota> preferencias = new Lista<ClaseMascota>();
  private Boolean tienePatio;
  private Lista<String> caracteristicasEspecificas = new Lista<String>();
  private Coordenadas ubicacion;
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

  public static Lista<Hogar> getHogares(HogaresService service) {
    return service.getListadoHogares();
  }

  public Boolean aceptaMascota(Mascota mascota){
    return aceptaClase(mascota.getClase()) && aceptaTamanio(mascota.getTamanio());
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
}
