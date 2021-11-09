import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.hogares.Hogar;
import dominio.mascota.Mascota;


class HogaresTest extends AbstractTest{
	
  Hogar somosHogarCarinioso;
  Hogar elHiltonParaGatos;
  Hogar elHiltonPerruno;  
  Hogar elPequenioHogarPerruno;
  Hogar unHogarAbandonado;

  Mascota pupi;
  Mascota felix;
  Mascota vladi;
  
	@BeforeEach
	@Override
  void setup() {
		PerThreadEntityManagers.getEntityManager().getTransaction().begin();
    Fixture fixture = new Fixture();
    somosHogarCarinioso = fixture.crearHogarCarinioso();
    elHiltonParaGatos = fixture.crearHiltonParaGatos();
    elHiltonPerruno = fixture.crearHiltonPerruno();
    elPequenioHogarPerruno= fixture.crearPequenioHogarPerruno();
    unHogarAbandonado= fixture.crearHogarAbandonado();

    pupi   = fixture.crearAPupi();
    felix  = fixture.crearAFelix();
    vladi  = fixture.crearAVladi();
	}
		
	@Test
	void unaMascotaEsAceptadaSoloEnUnHogarConCapacidad(){
		assertTrue(elHiltonParaGatos.aceptaMascota(pupi.getClase(), pupi.getTamanio()));
	}

	@Test
	void unHogarNoAceptaUnaMascotaSiNoEstaEnSusPreferencias() {		
		assertFalse(elHiltonParaGatos.aceptaMascota(felix.getClase(), felix.getTamanio()));
	}
	
	@Test
	void unHogarSinPatioNoAceptaCualquierTamanioDeMascota(){
		assertFalse(somosHogarCarinioso.aceptaMascota(felix.getClase(), felix.getTamanio()));		
	}
	
	@Test
	void unHogarSinCapacidadNoAceptaNingunPerro(){		
		assertFalse(unHogarAbandonado.aceptaMascota(felix.getClase(), felix.getTamanio()));
	}

	@Test
	void unHogarSinCapacidadNoAceptaNingunAnimalGatuno(){		
		assertFalse(unHogarAbandonado.aceptaMascota(pupi.getClase(), pupi.getTamanio()));
	}

	@Test
	void unHogarSinPatioQueNoAceptaMascotasPequeniasNoAceptaNingunaMascota(){		
		assertFalse(unHogarAbandonado.aceptaMascota(felix.getClase(), pupi.getTamanio()));		
	}
	
	@Test
	void unHogarGrandeSiempreAceptaMascotasPequenias() {
		assertFalse(elHiltonPerruno.aceptaMascota(pupi.getClase(), pupi.getTamanio()));		
	}
	
	@Test
	void unHogarAbandonadoSeLlamaDeIgualManera(){
		assertEquals("HogarAbandonado", unHogarAbandonado.getNombre());
	}

	@Test
	void unHogarPuedeNoTenerCaracteristicas(){
		assertEquals(null, unHogarAbandonado.getCaracteristicasEspecificas());
	}

	@Test
	void unHogarPuedeDecidirNoTenerPreferencias(){
		assertEquals(null, unHogarAbandonado.getPreferencias());
	}	
	
}
