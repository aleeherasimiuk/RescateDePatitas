package services.hogares;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RefugioService {

  private static RefugioService instancia = null;
  private static final String urlApi = "https://api.refugiosdds.com.ar/api/";
  private Retrofit retrofit;

  private RefugioService() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static RefugioService getInstance(){
    if (instancia == null){
      instancia = new RefugioService();
    }
    return instancia;
  }

  public ListadoDeHogares getListadoHogares(long offset) throws IOException {
    RefugioAPI refugioService = this.retrofit.create(RefugioAPI.class);
    final String bearer = "Bearer ";
    final String token = "token"; // TODO obtener este token de un archivo .config
    Call<ListadoDeHogares> requestHogares = refugioService.hogares(bearer + token, offset);
    Response<ListadoDeHogares> responseHogares = requestHogares.execute();
    return responseHogares.body();
  }
}

