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

  public Lista<Pagina> paginas(){
    final Lista<Pagina> paginas = new Lista<>();

    int i = 1;
    while (true) {
      
      Pagina unaPagina = obtenerUnaPagina(i++);
      if(unaPagina == null)
        break;
      paginas.add(unaPagina);
      
    }

    return paginas;
  }

  private Response<Pagina> fetchPaginas(int offset){
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

  private Pagina obtenerUnaPagina(int offset){
    Response<Pagina> responseHogares = fetchPaginas(offset);
    return responseHogares.body();
  }

  private Retrofit buildRetrofit(Config config) {
    Retrofit.Builder retrofit = new Retrofit.Builder();
    retrofit.baseUrl(config.getConfig("api.refugio.url"));
    retrofit.addConverterFactory(GsonConverterFactory.create());
    return retrofit.build();
  }
}

