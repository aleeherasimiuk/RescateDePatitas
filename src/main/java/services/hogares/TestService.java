package services.hogares;

import java.io.IOException;

public class TestService {
  public static void main(String[] args) throws IOException {
    HogaresService service = new HogaresServiceRefugioDDS();

    ListadoDeHogares hogares = service.getListadoHogares(1);

    hogares.hogares.forEach((hogar) -> {
      System.out.println(hogar.nombre);
    });
  }
}
