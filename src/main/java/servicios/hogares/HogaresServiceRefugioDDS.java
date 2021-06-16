package servicios.hogares;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import servicios.hogares.modelos.Pagina;

import java.io.IOException;

import config.Config;
import dominio.util.Lista;

public class HogaresServiceRefugioDDS{

  private final String apiToken;
  private final Retrofit retrofit;

  public HogaresServiceRefugioDDS() {
    Config config = new Config();
    
    retrofit = buildRetrofit(config);
    apiToken = config.getConfig("api.refugio.token");
  }

  private Response<Pagina> fetchPagina(int offset){
    RefugioDDSAPI refugioService = retrofit.create(RefugioDDSAPI.class);
    Response<Pagina> responsePagina;
    Call<Pagina> requestPagina = refugioService.hogares(apiToken, offset);
    try {
      responsePagina = requestPagina.execute();
    } catch (IOException e) {
      throw new RuntimeException("Hubo un error al buscar los hogares disponibles"); // TODO: Hacer la excepcion.
    }
    return responsePagina;
  }

  public Pagina obtenerUnaPagina(int offset){
    Response<Pagina> responseHogares = fetchPagina(offset);
    return responseHogares.body();
  }

  private Retrofit buildRetrofit(Config config) {
    Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
    retrofitBuilder.baseUrl(config.getConfig("api.refugio.url"));
    retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
    return retrofitBuilder.build();
  }
}

