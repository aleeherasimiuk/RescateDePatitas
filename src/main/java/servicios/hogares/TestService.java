package servicios.hogares;

import java.io.IOException;
import java.util.List;
import dominio.hogares.Hogar;

public class TestService {
  public static void main(String[] args) throws IOException {
    HogaresService service = new HogaresServiceRefugioDDS();

    List<Hogar> hogares = service.getListadoHogares();

    hogares.forEach((hogar) -> {
      System.out.println(hogar.getNombre());
    });
  }
}
