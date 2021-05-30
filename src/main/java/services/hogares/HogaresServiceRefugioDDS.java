package services.hogares;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import config.Config;

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

  public ListadoDeHogares getListadoHogares(long offset) throws IOException {
    RefugioDDSAPI refugioService = retrofit.create(RefugioDDSAPI.class);
    Call<ListadoDeHogares> requestHogares = refugioService.hogares(apiToken, offset);
    Response<ListadoDeHogares> responseHogares = requestHogares.execute();
    return responseHogares.body();
  }
}

