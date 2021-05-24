package dominio.services.hogares;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RefugioDDS {
  @GET("hogares")
  Call<ListadoDeHogares> hogares();
}
