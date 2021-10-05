import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import dominio.exceptions.NoHayAsociacionAsignadaAlRescate;
import dominio.exceptions.YaHayUnaAsociacionAsignada;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.asociacion.Asociacion;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Tamanio;
import dominio.repositorio.RepositorioAdopcion;
import dominio.repositorio.RepositorioAsociaciones;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.repositorio.RepositorioDuenios;
import dominio.repositorio.RepositorioMascotas;
import dominio.repositorio.RepositorioPreguntas;
import dominio.repositorio.RepositorioRescatesConChapita;
import dominio.repositorio.RepositorioRescatesSinChapita;
import dominio.repositorio.RepositorioRescatistas;
import dominio.repositorio.RepositorioRespuestas;
import dominio.repositorio.RepositorioSolicitudesAdopcion;
import dominio.rescate.RescateSinChapita;


public class PublicacionesTest {

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
    patitasSucias = fixture.getPatitasSucias();
    colaDeGato = fixture.getColaDeGato();
    repoAsociaciones.registrar(patitasSucias);
    repoAsociaciones.registrar(colaDeGato);
    publicacionUTN = fixture.getPublicacionUTN();
    publicacionUTN.asignarAsociacion();
  }

  @AfterEach
  void tearDown(){
    RepositorioRescatesConChapita.getINSTANCE().vaciar();
    RepositorioRescatesSinChapita.getINSTANCE().vaciar();
    RepositorioAdopcion.getInstance().vaciar();
    RepositorioRespuestas.getInstance().vaciar();
    RepositorioAsociaciones.getInstance().vaciar();
    RepositorioMascotas.getINSTANCE().vaciar();
    RepositorioDuenios.getInstance().vaciar();
    RepositorioSolicitudesAdopcion.getInstance().vaciar();
    RepositorioCaracteristicas.getINSTANCE().vaciar();
    PerThreadEntityManagers.getEntityManager().getTransaction().begin();
    PerThreadEntityManagers.getEntityManager().createNativeQuery("DELETE FROM OPCION").executeUpdate();
    PerThreadEntityManagers.getEntityManager().createNativeQuery("DELETE FROM RESCATES").executeUpdate();
    PerThreadEntityManagers.getEntityManager().getTransaction().commit();
    RepositorioPreguntas.getInstance().vaciar();
    RepositorioRescatistas.getInstance().vaciar();
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
    assertThrows(NoHayAsociacionAsignadaAlRescate.class, () -> fixture.getPublicacionUTN().getAsociacionAsignada());
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
    assertEquals(0, new Fixture().getUTN().distanciaA(publicacionUTN.getDatosRescate().getLugar()));
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

