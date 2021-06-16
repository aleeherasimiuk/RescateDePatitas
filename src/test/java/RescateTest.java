import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import dominio.repositorio.RepositorioMascotas;
import dominio.repositorio.RepositorioRescates;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.rescate.Rescate;
import dominio.rescate.Rescatista;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Mascota;
import dominio.mascota.Tamanio;
import dominio.usuarios.Duenio;


public class RescateTest {
  RepositorioRescates repoRescates = RepositorioRescates.getINSTANCE();
  RepositorioMascotas repoMascotas = RepositorioMascotas.getINSTANCE();
  Rescate rescateFelix;
  Rescate rescatePupi;
  Rescatista pedro;
  Duenio carlos;
  Duenio samuel;
  Mascota pupi;
  Mascota felix;
  Mascota vladi;

  @BeforeEach
  void iniciarRegistro() {

    Fixture fixture = new Fixture();
    pedro  = fixture.getPedro();
    carlos = fixture.getCarlos();
    samuel = fixture.getSamuel();
    pupi   = fixture.getPupi();
    felix  = fixture.getFelix();
    vladi  = fixture.getVladi();

    carlos.registrarUnaMascota(pupi);
    carlos.registrarUnaMascota(felix);

    samuel.registrarUnaMascota(vladi);

    pedro.registrarRescate(fixture.getRescatePupi());
    pedro.registrarRescate(fixture.getRescateFelix());
    
    rescateFelix = fixture.getRescateFelix();
    rescatePupi = fixture.getRescatePupi();
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

  @Test
  void unDuenioNoConoceLaMascotaDeOtroDuenio() {
  	assertFalse(samuel.esMiMascota(felix));
  }

  @Test
  void siHoySeRescataUnaMascotaDebeEstarRegistradoConFechaDeHoy() {
    Rescate rescatePupi = new Fixture().getRescatePupi();
    assertEquals(LocalDate.now(), rescatePupi.getFecha());
  }

  @Test
  void elRescatistaEsPedro(){
    assertEquals("Pedro", rescatePupi.getDatosDeRescatista().getNombre());
  }

  @Test
  void laMascotaEsChica(){
    assertEquals(Tamanio.CHICO, rescatePupi.getMascota().getTamanio());
  }

  @Test
  void laMascotaEsGato(){
    assertEquals(ClaseMascota.GATO, rescatePupi.getMascota().getClase());
  }

  @Test
  void laMascotaEstabaEnLaUTN(){
    assertEquals(0, rescatePupi.getLugar().distanciaA(new Fixture().getUTN()));
  }

  @Test
  void laMascotaPareceSerUnGatoSiames(){
    assertEquals("parece ser un gato siames",rescatePupi.getDescripcion());
  }

  @Test
  void emailDeContacto(){
    //assertEquals("fedebal@gmail.com",rescatePupi.emailDeContacto());
    assertEquals("fedebal@gmail.com",rescatePupi.datosDeContacto().getEmail());
  }
}
