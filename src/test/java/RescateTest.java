import static org.junit.jupiter.api.Assertions.*;
import dominio.repositorio.RepositorioMascotas;
import dominio.repositorio.RepositorioRescates;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.rescate.Rescatista;
import dominio.mascota.Mascota;
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

    Fixture fixture = new Fixture();
    pedro  = fixture.getPedro();
    carlos = fixture.getCarlos();
    pupi   = fixture.getPupi();
    felix  = fixture.getFelix();

    carlos.registrarUnaMascota(pupi);
    carlos.registrarUnaMascota(felix);

    pedro.registrarRescate(fixture.getRescatePupi());
    pedro.registrarRescate(fixture.getRescateFelix());
  
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
}
