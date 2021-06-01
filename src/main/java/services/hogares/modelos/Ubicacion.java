package services.hogares.modelos;

import com.google.gson.annotations.SerializedName;

public class Ubicacion {
  public String direccion;

  @SerializedName("lat")
  public Double latitud;

  @SerializedName("long")
  public Double longitud;
}
