package services.hogares;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RefugioDDSAPI {

  @GET("hogares")
  Call<ListadoDeHogares> hogares(@Header("Authorization") String authHeader, @Query("offset") long offset);
}
