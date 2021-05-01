import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.mascota.Clase;
import dominio.mascota.Mascota;
import dominio.mascota.Sexo;

public class MascotaTest {

  private Mascota pupi;

  @BeforeEach
  void setup(){
    pupi = new Mascota(
      0,
      Clase.GATO,
      "Pupi",
      "Pupi",
      3,
      Sexo.MACHO
    );
  }

  @Test
  void pupiEsUnGatoDeTresAños(){
    assertEquals(pupi.getApodo(), "Pupi");
    assertEquals(pupi.getSexo(), Sexo.MACHO);
    assertEquals(pupi.getClase(), Clase.GATO);
  }
  
}
