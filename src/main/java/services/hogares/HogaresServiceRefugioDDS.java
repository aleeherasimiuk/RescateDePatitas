package services.hogares;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

  public Lista<Hogar> getListadoHogares(long offset) throws IOException {
    RefugioDDSAPI refugioService = retrofit.create(RefugioDDSAPI.class);
    Call<ListadoDeHogares> requestHogares = refugioService.hogares(apiToken, offset);
    Response<ListadoDeHogares> responseHogares = requestHogares.execute();
    Lista<Hogar> hogares = convertFromResponse(responseHogares.body());
    return hogares;
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

