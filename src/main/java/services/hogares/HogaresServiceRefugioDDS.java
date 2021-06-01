package services.hogares;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.hogares.modelos.HogarResponse;
import services.hogares.modelos.ListadoDeHogares;

import java.io.IOException;

import config.Config;
import dominio.Ubicacion.Coordenadas;
import dominio.hogares.Hogar;
import dominio.mascota.ClaseMascota;
import dominio.util.Lista;

public class HogaresServiceRefugioDDS implements HogaresService {

  private String apiToken;
  private Retrofit retrofit;

  public HogaresServiceRefugioDDS() {
    retrofit = new Retrofit.Builder()
        .baseUrl(Config.getInstance().getConfig("api.refugio.url"))
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    apiToken = Config.getInstance().getConfig("api.refugio.token");
  }

  public Lista<Hogar> getListadoHogares(){
    try {
      RefugioDDSAPI refugioService = retrofit.create(RefugioDDSAPI.class);
      Lista<Hogar> hogares = new Lista<>();

      Response<ListadoDeHogares> responseHogares;

      int i = 1;
      Call<ListadoDeHogares> requestHogares = refugioService.hogares(apiToken, i);
      responseHogares = requestHogares.execute();
      ListadoDeHogares listadoDeHogares = responseHogares.body();
      hogares.addAll(convertFromResponse(listadoDeHogares));
      int total = listadoDeHogares.total;

      while (hogares.size() < total) {
        requestHogares = refugioService.hogares(apiToken, i++);
        responseHogares = requestHogares.execute();
        listadoDeHogares = responseHogares.body();
        hogares.addAll(convertFromResponse(listadoDeHogares));
      }

      return hogares;
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Error al obtener el listado de hogares");
    }
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

