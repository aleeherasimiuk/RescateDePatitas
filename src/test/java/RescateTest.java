import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dominio.repositorio.RepositorioMascotas;
import dominio.repositorio.RepositorioRescates;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.rescate.Rescatista;
import dominio.mascota.Clase;
import dominio.mascota.Mascota;
import dominio.mascota.Sexo;
import dominio.personas.Contacto;
import dominio.personas.DatosPersona;
import dominio.personas.Documento;
import dominio.personas.TipoDeDocumento;
import dominio.rescate.Coordenadas;
import dominio.rescate.Rescate;
import dominio.usuarios.Duenio;

public class RescateTest {
  RepositorioRescates repoRescates = RepositorioRescates.getINSTANCE();
  RepositorioMascotas repoMascotas = RepositorioMascotas.getINSTANCE();
  Rescatista pedro;
  Duenio carlos;
  Mascota pupi;
  Mascota felix;

  @BeforeEach
  void iniciarRegistro() {
    pedro = crearAPedro();
    pupi = crearAPupi();
    felix = crearAFelix();

    carlos = crearACarlos();
    carlos.registrarUnaMascota(pupi);
    carlos.registrarUnaMascota(felix);

    Rescate rescatePupi = rescatarAPupi();
    Rescate rescateFelix = rescatarAFelix();

    pedro.registrarRescate(rescatePupi);
    pedro.registrarRescate(rescateFelix);
  }

  private Rescate rescatarAFelix() {
    Rescate rescateFelix = new Rescate(pedro, felix, "perro negro con mancha blanca en la panza",
        LocalDate.now().plusDays(-15));
    rescateFelix.setLugar(new Coordenadas(-55., -55.));
    return rescateFelix;
  }

  private Rescate rescatarAPupi() {
    Rescate rescatePupi = new Rescate(pedro, pupi, "parece ser un gato siames", LocalDate.now().minusDays(1));
    rescatePupi.setLugar(new Coordenadas(-50., -50.));
    return rescatePupi;
  }

  @AfterEach
  void resetear() {
    repoRescates.vaciar();
    repoMascotas.vaciar();
  }

  @Test
  void hayAlMenosUnaMascotaRegistrada() {
    assertTrue(repoMascotas.cantidadRegistros() > 0);
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

  private Duenio crearACarlos() {
    Documento documento = new Documento(TipoDeDocumento.DNI, "21789654");
    DatosPersona datosPersona = new DatosPersona("Perez", "Carlos", documento, unContacto(),
        stringAFecha("01/01/2002"));

    return new Duenio("carlosKpo123", "Pupitoteamo1", datosPersona);
  }

  private Rescatista crearAPedro() {
    Documento documento = new Documento(TipoDeDocumento.DNI, "21789654");
    DatosPersona datosPersona = new DatosPersona("Perez", "Pedro", documento, unContacto(), stringAFecha("02/02/1996"));

    return new Rescatista(datosPersona, "Calle Falsa 123");
  }

  private Mascota crearAPupi() {
    Mascota pupi = new Mascota(Clase.GATO, "Pupi", "Pupi", 3, Sexo.MACHO);
    pupi.setDescripcionFisica("Un gato siamés, marrón con manchas blancas");
    return pupi;
  }

  private Mascota crearAFelix() {
    return new Mascota(Clase.PERRO, "felix", "feli", 5, Sexo.MACHO);
  }

  private Contacto unContacto() {
    return new Contacto("Federico", "Bal", 1180700542, "fedebal@gmail.com");
  }
}
