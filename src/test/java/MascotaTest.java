import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    pupi.setDescripcionFisica("Un gato siamés, marrón con manchas blancas");
  }

  @Test
  @DisplayName("Pupi es un gato macho de tres años")
  void pupiEsUnGatoDeTresAños(){
    assertEquals(pupi.getApodo(), "Pupi");
    assertEquals(pupi.getNombre(), "Pupi");
    assertEquals(pupi.getSexo(), Sexo.MACHO);
    assertEquals(pupi.getClase(), Clase.GATO);
    assertEquals(pupi.getEdad(), 3);
  }

  @Test
  @DisplayName("Pupi es un gato siamés, marrón con manchas blancas")
  void pupiEsUnSiamesMarronConManchas(){
    assertEquals(pupi.getDescripcionFisica(), "Un gato siamés, marrón con manchas blancas");
  }


  @Test
  @DisplayName("Pupi es el gato 0")
  void pupiEsElSuperGatoCosmico(){
    assertEquals(pupi.getId(), 0);
  }

  
}