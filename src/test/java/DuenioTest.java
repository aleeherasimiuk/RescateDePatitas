import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dominio.personas.Contacto;
import dominio.personas.TipoDeDocumento;
import dominio.usuarios.Duenio;

public class DuenioTest {

  @Test
  void losIdsSeAsignanBien(){

    Duenio duenio0 = new Duenio("Perez", "Carlos", TipoDeDocumento.DNI, 12123123,
        new Contacto("Carlos", "Perez", 44444444, "carlos@gmail.com"), "carlosKpo123", "carlosCapo",
        LocalDate.now().minusYears(30));

    Duenio duenio1 = new Duenio("Ramirez", "Paco", TipoDeDocumento.DNI, 12123124,
        new Contacto("Ramirez", "Paco", 44444445, "paco@gmail.com"), "pacoKpo123", "pacoCapo",
        LocalDate.now().minusYears(31));

    Duenio duenio2 = new Duenio("Fernandez", "Pepe", TipoDeDocumento.DNI, 12123125,
        new Contacto("Fernandez", "Pepe", 44444446, "Pepe@gmail.com"), "PepeKpo123", "PepeCapo",
        LocalDate.now().minusYears(32));
    
    assertEquals(duenio0.getIdDuenio(), 0);
    assertEquals(duenio1.getIdDuenio(), 1);
    assertEquals(duenio2.getIdDuenio(), 2);
  }
  
}
