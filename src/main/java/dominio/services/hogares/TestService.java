package dominio.services.hogares;

import java.io.IOException;

public class TestService {
  public static void main(String[] args) throws IOException {
    RefugioService service = RefugioService.getInstance();

    String hogares = service.getListadoHogares();

    System.out.println(hogares);
  }
}
