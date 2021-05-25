import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.asociacion.Asociacion;
import dominio.repositorio.RepositorioAsociaciones;
import dominio.repositorio.RepositorioPublicaciones;
import dominio.rescate.Publicacion;

public class PublicacionesTest {

  private final RepositorioPublicaciones repoPublicaciones = RepositorioPublicaciones.getINSTANCE();
  private final RepositorioAsociaciones repoAsociaciones = RepositorioAsociaciones.getInstance();
  private Asociacion patitasSucias;
  private Asociacion colaDeGato;
  private Publicacion publicacionUTN;

  @BeforeEach
  void setup(){
    Fixture fixture = new Fixture();

    repoPublicaciones.vaciar();
    patitasSucias = fixture.getPatitasSucias();
    colaDeGato = fixture.getColaDeGato();
    repoAsociaciones.registrar(patitasSucias);
    repoAsociaciones.registrar(colaDeGato);
    publicacionUTN = fixture.getPublicacionUTN();
    publicacionUTN.asignarAsociacion();

  }

  @Test
  void patitasSuciasQuedaMasCerca() {
    assertEquals(patitasSucias, publicacionUTN.getAsociacionAsignada());
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
    assertThrows(RuntimeException.class, () -> publicacionUTN.asignarAsociacion());
  }

  @Test
  void noSePuedePedirUnaAsociacionSiNuncaSeAsigno() {
    Fixture fixture = new Fixture();
    assertThrows(RuntimeException.class, () -> fixture.getPublicacionUTN().getAsociacionAsignada());
  }

  @Test
  void seLlamaPatitasSucias() {
    assertEquals("Patitas Sucias", patitasSucias.getNombre());
  }


  @Test
  void seLlamaColaDeGato() {
    assertEquals("Cola de Gato", colaDeGato.getNombre());
  }

}

