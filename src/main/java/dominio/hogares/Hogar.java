package dominio.hogares;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dominio.Ubicacion.Coordenadas;
import dominio.mascota.Caracteristica;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Mascota;
import dominio.mascota.Tamanio;

import java.util.List;
import java.util.Map;

public class Hogar {
  private List<ClaseMascota> preferencias;
  private Boolean tienePatio;
  private List<String> caracteristicasEspecificas;
  private Coordenadas ubicacion;
  private Boolean tieneCapacidad;

  public Boolean aceptaMascota(Mascota mascota){
    return aceptaClase(mascota.getClase()) && aceptaTamanio(mascota.getTamanio());
  }

  private Boolean aceptaClase(ClaseMascota claseMascota){
    return preferencias.contains(claseMascota);
  }

  private Boolean aceptaTamanio(Tamanio tamanio){
    if(!tienePatio && tamanio!= Tamanio.CHICO){
      return false;
    }else{
      return true;
    }
  }
}
