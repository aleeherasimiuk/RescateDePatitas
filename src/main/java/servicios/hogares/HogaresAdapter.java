package servicios.hogares;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dominio.hogares.Hogar;
import dominio.mascota.ClaseMascota;
import dominio.rescate.RescateSinChapita;
import dominio.ubicacion.Coordenadas;
import servicios.hogares.modelos.HogarResponse;
import servicios.hogares.modelos.Pagina;

public class HogaresAdapter{

  /*TODO: Hacer una sola función y filtros con lambdas*/

  public List<Hogar> obtenerPosiblesHogaresPara(RescateSinChapita publicacion, HogaresServiceRefugioDDS service){
    final List<Hogar> hogares = paginas(service)
        .stream()
        .flatMap(this::obtenerHogaresDeLaPagina)
        .filter((hogar) -> hogar.aceptaMascota(publicacion.getClaseMascota(), publicacion.getTamanio()))
        .filter((hogar) -> hogar.matcheaCaracteristica(publicacion.getCaracteristicas()))
        .collect(Collectors.toList());

    return hogares;
  }

  public List<Hogar> obtenerPosiblesHogaresPara(RescateSinChapita publicacion){
   HogaresServiceRefugioDDS service = new HogaresServiceRefugioDDS();
   return obtenerPosiblesHogaresPara(publicacion, service);
  }

  public List<Hogar> hogares(){
    return hogares(new HogaresServiceRefugioDDS());
  }

  public List<Hogar> hogares(HogaresServiceRefugioDDS service){
    final List<Hogar> hogares = paginas(service)
        .stream()
        .flatMap(this::obtenerHogaresDeLaPagina)
        .collect(Collectors.toList());

    return hogares;
  }

  public List<Pagina> paginas(HogaresServiceRefugioDDS service){
    final List<Pagina> paginas = new ArrayList<>();

    int i = 1;
    while (true) {
      
      Pagina unaPagina = service.obtenerUnaPagina(i++);
      if(unaPagina == null)
        break;
      paginas.add(unaPagina);
      
    }

    return paginas;
  }

  private Stream<Hogar> obtenerHogaresDeLaPagina(Pagina pagina) {
    return pagina.hogares.stream().map(this::convertHogar);
  }

  private Hogar convertHogar(HogarResponse hogarResponse) {
    String nombre = hogarResponse.nombre;
    String telefono = hogarResponse.telefono;
    List<ClaseMascota> preferencias = new ArrayList<>();

    if (hogarResponse.admisiones.gatos) {
      preferencias.add(ClaseMascota.GATO);
    }

    if (hogarResponse.admisiones.perros) {
      preferencias.add(ClaseMascota.PERRO);
    }

    Boolean tienePatio = hogarResponse.patio;
    List<String> caracteristicasEspecificas = hogarResponse.caracteristicas;
    Boolean tieneCapacidad = hogarResponse.lugares_disponibles > 0;
    Coordenadas coordenadas = new Coordenadas(hogarResponse.ubicacion.latitud, hogarResponse.ubicacion.longitud);

    Hogar hogar = new Hogar(nombre, telefono, preferencias, tienePatio, caracteristicasEspecificas, coordenadas, tieneCapacidad);
    return hogar;
  }
}
