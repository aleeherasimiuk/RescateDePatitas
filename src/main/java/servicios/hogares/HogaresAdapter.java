package servicios.hogares;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dominio.hogares.Hogar;
import dominio.mascota.ClaseMascota;
import dominio.rescate.Publicacion;
import dominio.ubicacion.Coordenadas;
import dominio.util.Lista;
import servicios.hogares.modelos.HogarResponse;
import servicios.hogares.modelos.Pagina;

public class HogaresAdapter {

  public List<Hogar> obtenerPosiblesHogaresPara(Publicacion publicacion){
    HogaresService service = new HogaresServiceRefugioDDS();
    final List<Hogar> hogares = service.paginas()
        .stream()
        .flatMap(this::obtenerHogarDeLaPagina)
        .filter((hogar) -> hogar.aceptaMascota(publicacion.getClaseMascota(), publicacion.getTamanio()))
        .filter((hogar) -> hogar.matcheaCaracteristica(publicacion.getCaracteristicas()))
        .collect(Collectors.toList());

    return hogares;
  }

  public List<Hogar> obtenerHogares(){
    HogaresService service = new HogaresServiceRefugioDDS();
    return service.paginas()
        .stream()
        .flatMap(this::obtenerHogarDeLaPagina)
        .collect(Collectors.toList());
  }

  private Stream<Hogar> obtenerHogarDeLaPagina(Pagina pagina) {
    return pagina.hogares.stream().map(this::convertHogar);
  }

  private Hogar convertHogar(HogarResponse hogarResponse) {
    String nombre = hogarResponse.nombre;
    String telefono = hogarResponse.telefono;
    Lista<ClaseMascota> preferencias = new Lista<>();

    if (hogarResponse.admisiones.gatos) {
      preferencias.add(ClaseMascota.GATO);
    }

    if (hogarResponse.admisiones.perros) {
      preferencias.add(ClaseMascota.PERRO);
    }

    Boolean tienePatio = hogarResponse.patio;
    Lista<String> caracteristicasEspecificas = hogarResponse.caracteristicas;
    Boolean tieneCapacidad = hogarResponse.lugares_disponibles > 0;
    Coordenadas coordenadas = new Coordenadas(hogarResponse.ubicacion.latitud, hogarResponse.ubicacion.longitud);

    Hogar hogar = new Hogar(nombre, telefono, preferencias, tienePatio, caracteristicasEspecificas, coordenadas, tieneCapacidad);
    return hogar;
  }
}
