package servicios.hogares;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import servicios.hogares.modelos.Pagina;

public interface RefugioDDSAPI {

  @GET("hogares")
  Call<Pagina> hogares(@Header("Authorization") String authHeader, @Query("offset") long offset);
}
