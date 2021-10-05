import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import dominio.exceptions.NoHayAsociacionAsignadaAlRescate;
import dominio.exceptions.YaHayUnaAsociacionAsignada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.asociacion.Asociacion;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Tamanio;
import dominio.repositorio.RepositorioAsociaciones;
import dominio.repositorio.RepositorioRescatesSinChapita;
import dominio.rescate.RescateSinChapita;


public class PublicacionesTest extends AbstractTest{

  private final RepositorioRescatesSinChapita repoPublicaciones = RepositorioRescatesSinChapita.getINSTANCE();
  private final RepositorioAsociaciones repoAsociaciones = RepositorioAsociaciones.getInstance();
  private Asociacion patitasSucias;
  private Asociacion colaDeGato;
  private RescateSinChapita publicacionUTN;

  @BeforeEach
  void setup(){
    repoPublicaciones.vaciar();
    repoAsociaciones.vaciar();

    Fixture fixture = new Fixture();
    patitasSucias = fixture.asociacionPatitasSucias();
    colaDeGato = fixture.asociacionColaDeGato();
    repoAsociaciones.registrar(patitasSucias);
    repoAsociaciones.registrar(colaDeGato);
    publicacionUTN = fixture.publicacionMascotaUTN();
    publicacionUTN.asignarAsociacion();
  }

  @Test
  void patitasSuciasQuedaMasCerca() {
    assertEquals(patitasSucias.getNombre(), publicacionUTN.getAsociacionAsignada().getNombre());
  }


  @Test
  void porDefectoLaPublicacionEstaPendiente() {
    assertTrue(publicacionUTN.estaPendiente());
  }
  

  @Test
  void unVoluntarioApruebaLaPublicacion() {
    publicacionUTN.aprobar();
    assertTrue(publicacionUTN.estaAprobada());
  }


  @Test
  void unVoluntarioRechazaLaPublicacion() {
    publicacionUTN.rechazar();
    assertFalse(publicacionUTN.estaAprobada());
  }
  

  @Test
  void noSePuedeAsignarDosVecesUnaAsociacion() {
    assertThrows(YaHayUnaAsociacionAsignada.class, () -> publicacionUTN.asignarAsociacion());
  }

  @Test
  void noSePuedePedirUnaAsociacionSiNuncaSeAsigno() {
    Fixture fixture = new Fixture();
    assertThrows(NoHayAsociacionAsignadaAlRescate.class, () -> fixture.publicacionMascotaUTN().getAsociacionAsignada());
  }

  @Test
  void seLlamaPatitasSucias() {
    assertEquals("Patitas Sucias", patitasSucias.getNombre());
  }


  @Test
  void seLlamaColaDeGato() {
    assertEquals("Cola de Gato", colaDeGato.getNombre());
  }

  @Test
  void elRescatistaEsPedro(){
    assertEquals("Pedro", publicacionUTN.getDatosRescate().getRescatista().getDatosPersona().getNombre());
  }

  @Test
  void laMascotaDesconocidaEsChica(){
    assertEquals(Tamanio.CHICO, publicacionUTN.getTamanio());
  }

  @Test
  void laMascotaDesconocidaEsGato(){
    assertEquals(ClaseMascota.GATO, publicacionUTN.getClaseMascota());
  }

  @Test
  void laMascotaDesconocidaEstabaEnLaUTN(){
    assertEquals(0, new Fixture().buildUTN().distanciaA(publicacionUTN.getDatosRescate().getLugar()));
  }

  @Test
  void laMascotaPareceSerUnGatoSiames(){
    assertEquals("parece ser un gato siames",publicacionUTN.getDatosRescate().getDescripcion());
  }

  @Test
  void laMascotaTodaviaNoTieneHogarAsignado(){
    assertNull(publicacionUTN.getDatosRescate().getHogar());
  }

  @Test
  void laMascotaLaEncontraronAyer(){
    assertEquals(LocalDate.now().minusDays(1),publicacionUTN.getDatosRescate().getFecha());
  }

}

