import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import dominio.repositorio.RepositorioDuenios;
import dominio.repositorio.RepositorioMascotas;
import dominio.repositorio.RepositorioRescatesConChapita;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.rescate.RescateConChapita;
import dominio.rescate.Rescatista;
import dominio.ubicacion.Coordenadas;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Mascota;
import dominio.mascota.Tamanio;
import dominio.usuarios.Duenio;

public class RescateTest extends AbstractTest{
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
  void setup() {

    PerThreadEntityManagers.getEntityManager().getTransaction().begin();

    
    pedro  = fixture.crearAPedro();
    samuel = fixture.crearASamuel();
    
    vladi  = fixture.crearAVladi();
    utn    = fixture.buildUTN();

    samuel.registrarUnaMascota(vladi);
    RepositorioDuenios.getInstance().registrar(samuel);
    
  }

  @Test
  void hayAlMenosUnaMascotaRegistrada() {
    assertTrue(repoMascotas.cantidadRegistros() > 0);
  }

  @Test
  void ayerSePerdioPupi() {
    rescatePupi = fixture.rescatarAPupi();
    assertTrue(repoRescates.mascotasEncontradasEnLosUltimos10Dias().stream().anyMatch(mascota -> mascota.getApodo().equals("Pupi")));
  }

  @Test
  void felixSePerdioHaceMucho() {
    rescateFelix = fixture.rescatarAFelix();
    assertFalse(repoRescates.mascotasEncontradasEnLosUltimos10Dias().stream().anyMatch(mascota -> mascota.getApodo().equals("Felix")));
  }

  @Test
  void unDuenioNoConoceLaMascotaDeOtroDuenio() {
    felix  = fixture.crearAFelix();
  	assertFalse(samuel.esMiMascota(felix));
  }

  @Test
  void siHoySeRescataUnaMascotaDebeEstarRegistradoConFechaDeHoy() {
    rescatePupi = fixture.rescatarAPupi();
    assertEquals(LocalDate.now(), rescatePupi.getFecha());
  }

  @Test
  void elRescatistaEsPedro(){
    rescatePupi = fixture.rescatarAPupi();
    assertEquals("Pedro", rescatePupi.getDatosDeRescatista().getNombre());
  }

  @Test
  void laMascotaEsChica(){
    rescatePupi = fixture.rescatarAPupi();
    assertEquals(Tamanio.CHICO, rescatePupi.getMascota().getTamanio());
  }

  @Test
  void laMascotaEsGato(){
    rescatePupi = fixture.rescatarAPupi();
    assertEquals(ClaseMascota.GATO, rescatePupi.getMascota().getClase());
  }

  @Test
  void laMascotaEstabaEnLaUTN(){
    rescatePupi = fixture.rescatarAPupi();
    assertEquals(0, rescatePupi.getLugar().distanciaA(utn));
  }

  @Test
  void laMascotaPareceSerUnGatoSiames(){
    rescatePupi = fixture.rescatarAPupi();
    assertEquals("parece ser un gato siames",rescatePupi.getDescripcion());
  }

  @Test
  void emailDeContacto(){
    rescatePupi = fixture.rescatarAPupi();
    assertEquals("robertito@gmail.com",rescatePupi.datosDeContacto().getEmail());
  }
}
