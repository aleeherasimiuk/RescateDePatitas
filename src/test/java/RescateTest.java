import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dominio.repositorio.RepositorioMascotas;
import dominio.repositorio.RepositorioRescates;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.rescate.Rescatista;
import dominio.mascota.Clase;
import dominio.mascota.Mascota;
import dominio.mascota.Sexo;
import dominio.personas.Contacto;
import dominio.personas.TipoDeDocumento;
import dominio.rescate.Coordenadas;
import dominio.rescate.Rescate;
import dominio.usuarios.Duenio;

public class RescateTest {
  RepositorioRescates repoRescates = RepositorioRescates.getINSTANCE();
  RepositorioMascotas repoMascotas = RepositorioMascotas.getINSTANCE();
  Mascota pupi;
  Mascota felix;

  @BeforeEach
  void iniciarRegistro() {
    Rescatista pedro = crearAPedro();
    pupi = crearAPupi();
    pupi.setDescripcionFisica("Un gato siamés, marrón con manchas blancas");

    Rescate rescatePupi = new Rescate(pedro, pupi, "parece ser un gato siames",LocalDate.now().minusDays(1));
    rescatePupi.setLugar(new Coordenadas(-50., -50.));
    Duenio carlos = crearACarlos();

    carlos.registrarUnaMascota(pupi);

    felix = crearAFelix();
    carlos.registrarUnaMascota(felix);
    Rescate rescateFelix = new Rescate(pedro, felix, "perro negro con mancha blanca en la panza",LocalDate.now().plusDays(-15));
    rescateFelix.setLugar(new Coordenadas(-55., -55.));

    pedro.registrarRescate(rescatePupi);
    pedro.registrarRescate(rescateFelix);
  }

  @Test
  void hayAlMenosUnaMascotaRegistrada() {
    assertTrue(repoMascotas.cantDeMascotasRegistradas() > 0);
  }

  @Test
  void ayerSePerdioPupi() {
    assertTrue(repoRescates.mascotasEncontradasEnLosUltimos10Dias().contains(pupi));
  }

  @Test
  void felixSePerdioHaceMucho() {
    assertFalse(repoRescates.mascotasEncontradasEnLosUltimos10Dias().contains(felix));
  }

  LocalDate stringAFecha(String fecha) {
    return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/uuuu"));
  }

  private Duenio crearACarlos(){
    return new Duenio("Perez", "Carlos", TipoDeDocumento.DNI, 21789654,
      new Contacto("Jimena", "Baron", 1180700542, "jmena@gmail.com"), "carlosKpo123", "Pupitoteamo1",
      stringAFecha("01/01/2002"));
  }

  private Rescatista crearAPedro() {
    return new Rescatista("Perez", "Pedro", TipoDeDocumento.DNI, 21789654,
        new Contacto("Federico", "Bal", 1180700542, "fedebal@gmail.com"), stringAFecha("02/02/1996"),
        "Calle Falsa 123");
  }

  private Mascota crearAPupi() {
    return new Mascota(Clase.GATO, "Pupi", "Pupi", 3, Sexo.MACHO);
  }


  private Mascota crearAFelix() {
    return new Mascota(Clase.PERRO, "felix", "feli", 5, Sexo.MACHO);
  }
  
}
