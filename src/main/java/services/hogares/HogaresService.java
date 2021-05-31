package services.hogares;

import java.io.IOException;

import dominio.util.Lista;
import dominio.hogares.Hogar;
public interface HogaresService {
  public Lista<Hogar> getListadoHogares(long offset) throws IOException;
}
