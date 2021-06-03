package servicios.hogares;

import java.io.IOException;
import java.util.List;
import dominio.hogares.Hogar;

public class TestService {
  public static void main(String[] args) throws IOException {
    HogaresServiceRefugioDDS service = new HogaresServiceRefugioDDS();

    List<Hogar> hogares = service.getListadoHogaresMock();

    hogares.forEach((hogar) -> {
      System.out.println(hogar.getNombre());
    });
  }
}
