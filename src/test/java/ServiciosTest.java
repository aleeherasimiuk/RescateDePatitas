import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dominio.mascota.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.hogares.Hogar;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.rescate.DatosRescate;
import dominio.rescate.RescateSinChapita;
import servicios.hogares.HogaresAdapter;
import servicios.hogares.HogaresServiceRefugioDDS;
import servicios.hogares.modelos.Admision;
import servicios.hogares.modelos.HogarResponse;
import servicios.hogares.modelos.Pagina;
import servicios.hogares.modelos.Ubicacion;
public class ServiciosTest extends AbstractTest{

  private HogaresAdapter hogaresAdapter;
  private HogaresServiceRefugioDDS service;
  List<Hogar> hogares;


  @BeforeEach
  void setup(){

    PerThreadEntityManagers.getEntityManager().getTransaction().begin();
    RepositorioCaracteristicas.getINSTANCE().vaciar();
    service = mock(HogaresServiceRefugioDDS.class);
    when(service.obtenerUnaPagina(1)).thenReturn(buildPage("1", 40));
    when(service.obtenerUnaPagina(2)).thenReturn(buildPage("2", 40));
    when(service.obtenerUnaPagina(3)).thenReturn(buildPage("3", 40));
    when(service.obtenerUnaPagina(4)).thenReturn(buildPage("4", 40));

    hogaresAdapter = new HogaresAdapter();
    hogares = hogaresAdapter.hogares(service);
  }

  @Test
  void hay40Hogares(){
    assertEquals(40, hogares.size());
  }

  @Test
  @DisplayName("El hogar en la posicion 0 se llama 'Hogar: #0'")
  void elPrimerHogarEsEl0(){
    assertEquals("Hogar: #0", hogares.get(0).getNombre());
  }

  @Test
  @DisplayName("El hogar en la posicion 0 no tiene características específicas'")
  void primerHogarNoPoseeCaracteristicasEspecificas(){
    assertArrayEquals(new String[]{}, hogares.get(0).getCaracteristicasEspecificas().toArray());
  }

  @Test
  @DisplayName("El hogar en la posicion 1 desea una mascota calmada'")
  void segundoHogarSoloAceptaCalmados(){
    assertArrayEquals(new String[]{"CALMADO"}, hogares.get(1).getCaracteristicasEspecificas().toArray());
  }

  @Test
  @DisplayName("El hogar en la posicion 2 desea que la mascota sea calmada y no muerda")
  void tercerHogarAceptaCalmadosYQueNoMuerda(){
    assertArrayEquals(new String[]{"CALMADO", "NO MUERDE"}, hogares.get(2).getCaracteristicasEspecificas().toArray());
  }

  @Test
  @DisplayName("El hogar en la posición 3 no tiene capacidad ni patio")
  void elTerceroNoTienePatioNiCapacidad(){
    assertFalse(hogares.get(3).tieneCapacidad());
    assertFalse(hogares.get(3).tienePatio());
  }

  @Test
  @DisplayName("El hogar en la posición 7 tiene capacidad y no tiene patio")
  void elSeptimoTieneCapacidadPeroNoPatio(){
    assertTrue(hogares.get(7).tieneCapacidad());
    assertFalse(hogares.get(7).tienePatio());
  }

  @Test
  @DisplayName("El hogar en la posición 7 acepta solo perros")
  void elSeptimoAceptaSoloPerro(){
    assertArrayEquals(new ClaseMascota[]{ClaseMascota.PERRO},hogares.get(7).getPreferencias().toArray());
  }


  @Test
  @DisplayName("El hogar en la posición 7 acepta que sea calmado, no muerda y sea tierno")
  void elSeptimoAceptaCalmadoNoMuerdeyTierno(){
    assertArrayEquals(new String[]{"CALMADO", "NO MUERDE", "TIERNO"}, hogares.get(7).getCaracteristicasEspecificas().toArray());
  }


  @Test
  @DisplayName("El hogar en la posición 7 acepta una mascota calmada, que no muerda, y tierna")
  void elSeptimoAceptaARobert(){

    Hogar hogar = hogares.get(7);
    Mascota robert = buildRobert();

    assertTrue(hogar.matcheaCaracteristica(robert.getCaracteristicas()));
    assertTrue(hogar.aceptaMascota(robert.getClase(), robert.getTamanio()));
  }

  @Test
  @DisplayName("El hogar en la posición 7 está entre los hogares posibles")
  void elSeptimoAceptaLaPublicacionDeRobert(){

    Fixture f = new Fixture();
    RescateSinChapita publicacion = new RescateSinChapita(new DatosRescate(f.crearAPedro(), new ArrayList<>(), LocalDate.now(), "", f.buildUTN()), Tamanio.CHICO, ClaseMascota.PERRO, Sexo.MACHO);
    for (String caracteristica : buildRobert().getCaracteristicas()) {
      publicacion.agregarUnaCaracteristica(caracteristica);
    }
    List<Hogar> hogaresPosibles = hogaresAdapter.obtenerPosiblesHogaresPara(publicacion,service);

    //assertTrue(hogaresPosibles.contains(hogar -> hogar.getNombre().equals("Hogar: #7")));
    assertTrue(hogaresPosibles.stream().anyMatch(hogar -> hogar.getNombre().equals("Hogar: #7")));
  }

  Pagina buildPage(String offset, int total){
    Pagina pagina = new Pagina();
    pagina.total = total;
    pagina.offset = offset;
    pagina.hogares = new ArrayList<>();

    for(int i = 0; i < 10; i++){
      pagina.hogares.add(buildHogar(Integer.parseInt(offset) * i));
    }

    return pagina;

  }

  HogarResponse buildHogar(int unNumero){
    HogarResponse hogarResponse = new HogarResponse();
    hogarResponse.nombre = "Hogar: #" + unNumero;
    hogarResponse.lugares_disponibles = unNumero % 3;
    Admision admision = new Admision();
    admision.gatos = unNumero % 2 == 0;
    admision.perros = unNumero % 2 == 1;
    hogarResponse.admisiones = admision;
    hogarResponse.patio = unNumero % 2 == 0;
    hogarResponse.telefono = "4444-4444";
    Ubicacion ubicacion = new Ubicacion();
    ubicacion.latitud = unNumero + 2.0;
    ubicacion.latitud = unNumero - 1.0;
    ubicacion.direccion = "Calle falsa " + unNumero;
    hogarResponse.ubicacion = ubicacion;
    String[] caracteristicas = new String[]{"CALMADO", "NO MUERDE", "TIERNO", "AGRESIVO", "PEQUEÑO",  "DUERME MUCHO"};
    hogarResponse.caracteristicas = new ArrayList<>();
    for(int i = 0; i < unNumero % 4; i++){
      hogarResponse.caracteristicas.add(caracteristicas[i % 6]);
    }

    return hogarResponse;

  }

  Mascota buildRobert(){
    Mascota robert = new Mascota(ClaseMascota.PERRO, "Roberto", "Robert", 4, Sexo.MACHO, Tamanio.CHICO);
    RepositorioCaracteristicas.getINSTANCE().registrar(new Caracteristica("CALMADO"));
    RepositorioCaracteristicas.getINSTANCE().registrar(new Caracteristica("NO MUERDE"));
    RepositorioCaracteristicas.getINSTANCE().registrar(new Caracteristica("TIERNO"));
    RepositorioCaracteristicas.getINSTANCE().registrar(new Caracteristica("SE COME LAS MEDIAS"));
    robert.agregarUnaCaracteristica("CALMADO");
    robert.agregarUnaCaracteristica("No muerde");
    robert.agregarUnaCaracteristica("tierno");
    robert.agregarUnaCaracteristica("se come las medias");
    return robert;
  }

}
