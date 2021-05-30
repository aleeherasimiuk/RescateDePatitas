package services.hogares;

import java.io.IOException;

public interface HogaresService {
  public ListadoDeHogares getListadoHogares(long offset) throws IOException;
}
