import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.hogares.Hogar;
import dominio.mascota.Mascota;

class HogaresTest {
	
  Hogar somosHogarCarinioso;
  Hogar elHiltonParaGatos;
  Hogar elHiltonPerruno;  
  Hogar elPequenioHogarPerruno;
  Hogar unHogarAbandonado;

  Mascota pupi;
  Mascota felix;
  Mascota vladi;
  
	@BeforeEach
  void iniciarRegistro() {
    Fixture fixture = new Fixture();
    somosHogarCarinioso = fixture.getHogarCarinio();
    elHiltonParaGatos = fixture.getElHiltonParaGatos();
    elHiltonPerruno = fixture.getElHiltonPerruno();
    elPequenioHogarPerruno= fixture.getElPequenioHogarPerruno();
    unHogarAbandonado= fixture.getHogarAbandonado();

    pupi   = fixture.getPupi();
    felix  = fixture.getFelix();
    vladi  = fixture.getVladi();
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
}
