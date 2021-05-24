package dominio.services.hogares;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RefugioService {

  private static RefugioService instancia = null;
  private static int offset = 10;
  private static final String urlApi = "https://api.refugiosdds.com.ar/api";
  private Retrofit retrofit;

  private RefugioService() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static RefugioService getInstance(){
    if(instancia== null){
      instancia = new RefugioService();
    }
    return instancia;
  }
/*
  public ListadoDeHogares listadoDeProvincias() throws IOException {
    RefugioDDS refugioService = this.retrofit.create(RefugioDDS.class);
    Call<ListadoDeHogares> requestHogares = refugioService.hogares();
    Response<ListadoDeHogares> responseHogares = requestHogares.execute();
    return responseHogares.body();
  }*/
}

