package services.hogares;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import config.Config;

public class RefugioService {

  private static RefugioService instancia = null;
  private String apiToken;
  private Retrofit retrofit;

  private RefugioService() {
    retrofit = new Retrofit.Builder()
        .baseUrl(Config.getInstance().getConfig("api.refugio.url"))
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    apiToken = Config.getInstance().getConfig("api.refugio.token");
  }

  public static RefugioService getInstance(){
    if (instancia == null){
      instancia = new RefugioService();
    }
    return instancia;
  }

  public ListadoDeHogares getListadoHogares(long offset) throws IOException {
    RefugioAPI refugioService = retrofit.create(RefugioAPI.class);
    Call<ListadoDeHogares> requestHogares = refugioService.hogares(apiToken, offset);
    Response<ListadoDeHogares> responseHogares = requestHogares.execute();
    return responseHogares.body();
  }
}

