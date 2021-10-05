import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dominio.mascota.ClaseMascota;
import dominio.mascota.Mascota;
import dominio.mascota.Sexo;
import dominio.mascota.Tamanio;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.usuarios.Admin;
import dominio.util.Lista;

@Disabled
public class ListaTest {

  Lista<Integer> unaLista;

  @BeforeEach
  void setup() {
    unaLista = new Lista<Integer>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
  }

  @Test
  void laSumaDa55() {
    assertEquals(unaLista.fold(Integer::sum), 55);
  }

  @Test
  void laSumaDeLosDoblesEs110() {
    assertEquals(unaLista.map(this::doble).fold(Integer::sum), 110);
  }

  @Test
  void hay5NumerosPares() {
    assertEquals(unaLista.count(this::esPar), 5);
  }

  @Test
  void losNumerosParesSon246810() {
    assertTrue(unaLista.filter(this::esPar).contains(2));
    assertTrue(unaLista.filter(this::esPar).contains(4));
    assertTrue(unaLista.filter(this::esPar).contains(6));
    assertTrue(unaLista.filter(this::esPar).contains(8));
    assertTrue(unaLista.filter(this::esPar).contains(10));

    assertFalse(unaLista.filter(this::esPar).contains(3));
    assertFalse(unaLista.filter(this::esPar).contains(5));
  }

  @Test
  void mascotasCastradas() {
    RepositorioCaracteristicas.getINSTANCE().vaciar();
    new Admin("username", "P4ssword").agregarUnaCaracteristica("CASTRADO");

    Mascota mascota1 = new Mascota(ClaseMascota.GATO, "Pupi", "Pupi", 3, Sexo.MACHO , Tamanio.CHICO);
    Mascota mascota2 = new Mascota(ClaseMascota.GATO, "Pupo", "Pupo", 3, Sexo.MACHO , Tamanio.CHICO);
    Mascota mascota3 = new Mascota(ClaseMascota.GATO, "Pupa", "Pupa", 3, Sexo.HEMBRA, Tamanio.CHICO);

    mascota1.agregarUnaCaracteristica("CASTRADO");
    mascota2.agregarUnaCaracteristica("CASTRADO");
    mascota3.agregarUnaCaracteristica("CASTRADO");

    Lista<Mascota> mascotas = new Lista<Mascota>(mascota1, mascota2, mascota3);

    assertEquals(mascotas.count(mascota -> mascota.tieneEstaCaracteristica("CASTRADO")), 3);

  }

  boolean esPar(int unNumero) {
    return unNumero % 2 == 0;
  }

  int doble(int unNumero) {
    return unNumero * 2;
  }

}
