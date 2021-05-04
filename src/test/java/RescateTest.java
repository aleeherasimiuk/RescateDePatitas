import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.mascota.Clase;
import dominio.mascota.Mascota;
import dominio.mascota.Sexo;
import dominio.personas.Contacto;
import dominio.personas.Persona;
import dominio.personas.TipoDeDocumento;
import dominio.sistema.Registro;
import dominio.sistema.Rescate;
import dominio.usuarios.Usuario;

public class RescateTest {
  Registro registro;
  Mascota pupi;
  Rescate rescatePupi;
  Mascota felix;
  @BeforeEach
  void iniciarRegistro() {
    registro = new Registro();
    Usuario carlos = new Usuario("Perez", "Carlos", TipoDeDocumento.DNI, 21789654,
        new Contacto("Jimena", "Baron", 1180700542, "jmena@gmail.com"));
    Usuario user = new Usuario(carlos, LocalDate.now(), "carloskpo123", "pupitoteamo");

    pupi = new Mascota(0, user.getIdDuenio(), Clase.GATO, "Pupi", "Pupi", 3, Sexo.MACHO);

    pupi.setDescripcionFisica("Un gato siamés, marrón con manchas blancas");

    user.registrarUnaMascota(registro, pupi);
    Usuario pedro = new Usuario("Perez", "Pedro", TipoDeDocumento.DNI, 21789654,
        new Contacto("Federico", "Bal", 1180700542, "fedebal@gmail.com"));
    
    registro.registrarRescate(pedro, 0, null, "parece ser un gato siames",null, LocalDate.now());
    
    felix = new Mascota(1, user.getIdDuenio(), Clase.PERRO, "felix", "feli", 5, Sexo.MACHO);
    user.registrarUnaMascota(registro, felix);
    registro.registrarRescate(pedro, 1, null, "perro negro con mancha blanca en la panza",null, LocalDate.now().plusDays(-15));
    
    
  }

  @Test 
  void hayAlMenosUnaMascotaRegistrada() {
    assertTrue(registro.cantDeMascotasRegistradas()>0);
  }
  
  @Test
  void ayerSePerdioPupi() {
    assertTrue(registro.mascotasEncontradasEnLosUltimos10Dias().contains(pupi));
  }
  
  @Test
  void felixSePerdioHaceMucho() {
    assertFalse(registro.mascotasEncontradasEnLosUltimos10Dias().contains(felix));
  }
  
}
