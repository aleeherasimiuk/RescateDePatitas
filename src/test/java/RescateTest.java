import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import dominio.repositorio.RepositorioMascotas;
import dominio.repositorio.RepositorioRescatesConChapita;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.rescate.RescateConChapita;
import dominio.rescate.Rescatista;
import dominio.ubicacion.Coordenadas;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Mascota;
import dominio.mascota.Tamanio;
import dominio.usuarios.Duenio;


public class RescateTest {
  Fixture fixture = new Fixture();
  RepositorioRescatesConChapita repoRescates = RepositorioRescatesConChapita.getINSTANCE();
  RepositorioMascotas repoMascotas = RepositorioMascotas.getINSTANCE();
  RescateConChapita rescateFelix;
  RescateConChapita rescatePupi;
  Rescatista pedro;
  Duenio samuel;
  Mascota felix;
  Mascota vladi;
  Coordenadas utn;

  @BeforeEach
  void iniciarRegistro() {

    
    pedro  = fixture.getPedro();
    samuel = fixture.getSamuel();
    
    vladi  = fixture.getVladi();
    utn    = fixture.getUTN();

    samuel.registrarUnaMascota(vladi);
    
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
    rescatePupi = fixture.getRescatePupi();
    assertTrue(repoRescates.mascotasEncontradasEnLosUltimos10Dias().stream().anyMatch(mascota -> mascota.getApodo().equals("Pupi")));
  }

  @Test
  void felixSePerdioHaceMucho() {
    rescateFelix = fixture.getRescateFelix();
    assertFalse(repoRescates.mascotasEncontradasEnLosUltimos10Dias().stream().anyMatch(mascota -> mascota.getApodo().equals("Felix")));
  }

  @Test
  void unDuenioNoConoceLaMascotaDeOtroDuenio() {
    felix  = fixture.getFelix();
  	assertFalse(samuel.esMiMascota(felix));
  }

  @Test
  void siHoySeRescataUnaMascotaDebeEstarRegistradoConFechaDeHoy() {
    rescatePupi = fixture.getRescatePupi();
    assertEquals(LocalDate.now(), rescatePupi.getFecha());
  }

  @Test
  void elRescatistaEsPedro(){
    rescatePupi = fixture.getRescatePupi();
    assertEquals("Pedro", rescatePupi.getDatosDeRescatista().getNombre());
  }

  @Test
  void laMascotaEsChica(){
    rescatePupi = fixture.getRescatePupi();
    assertEquals(Tamanio.CHICO, rescatePupi.getMascota().getTamanio());
  }

  @Test
  void laMascotaEsGato(){
    rescatePupi = fixture.getRescatePupi();
    assertEquals(ClaseMascota.GATO, rescatePupi.getMascota().getClase());
  }

  @Test
  void laMascotaEstabaEnLaUTN(){
    rescatePupi = fixture.getRescatePupi();
    assertEquals(0, rescatePupi.getLugar().distanciaA(utn));
  }

  @Test
  void laMascotaPareceSerUnGatoSiames(){
    rescatePupi = fixture.getRescatePupi();
    assertEquals("parece ser un gato siames",rescatePupi.getDescripcion());
  }

  @Test
  void emailDeContacto(){
    rescatePupi = fixture.getRescatePupi();
    assertEquals("robertito@gmail.com",rescatePupi.datosDeContacto().getEmail());
  }
}
