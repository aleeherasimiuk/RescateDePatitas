package servicios.hogares;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import servicios.hogares.modelos.HogarResponse;
import servicios.hogares.modelos.ListadoDeHogares;

import java.io.IOException;

import config.Config;
import dominio.hogares.Hogar;
import dominio.mascota.ClaseMascota;
import dominio.ubicacion.Coordenadas;
import dominio.util.Lista;

public class HogaresServiceRefugioDDS implements HogaresService {

  private String apiToken;
  private Retrofit retrofit;

  public HogaresServiceRefugioDDS() {
    Config config = new Config();
    
    retrofit = new Retrofit.Builder()
        .baseUrl(config.getConfig("api.refugio.url"))
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    apiToken = config.getConfig("api.refugio.token");
  }

  public Lista<Hogar> getListadoHogares(){
    Lista<Hogar> hogares = new Lista<>();

    int total = cantidadDeHogares();

    int i = 1;
    while (hogares.size() < total) {
      ListadoDeHogares listadoDeHogares = listadoDeHogares(i++);
      hogares.addAll(convertFromResponse(listadoDeHogares));
    }

    return hogares;
  }

  private Response<ListadoDeHogares> fetchHogares(int offset){
    RefugioDDSAPI refugioService = retrofit.create(RefugioDDSAPI.class);
    Response<ListadoDeHogares> responseHogares;
    Call<ListadoDeHogares> requestHogares = refugioService.hogares(apiToken, offset);
    try {
      responseHogares = requestHogares.execute();
    } catch (IOException e) {
      throw new RuntimeException("Hubo un error al buscar los hogares disponibles"); // TODO: Hacer la excepcion.
    }
    return responseHogares;
  }

  private ListadoDeHogares listadoDeHogares(int offset){
    Response<ListadoDeHogares> responseHogares = fetchHogares(offset);
    return responseHogares.body();
  }

  private int cantidadDeHogares(){
    ListadoDeHogares hogares = listadoDeHogares(1);
    return hogares.total;
  }


  private Lista<Hogar> convertFromResponse(ListadoDeHogares listadoDeHogares) {
    Lista<Hogar> hogares = listadoDeHogares.hogares.map((hogarResponse) -> {
      return convertFromHogarResponse(hogarResponse);
    });
    return hogares;
  }

  private Hogar convertFromHogarResponse(HogarResponse hogarResponse) {
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

