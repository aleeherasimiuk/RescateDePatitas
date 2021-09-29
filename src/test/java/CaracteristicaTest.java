import dominio.exceptions.CaracteristicaInvalida;
import dominio.exceptions.CaracteristicaRepetida;
import dominio.mascota.*;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.usuarios.Admin;

import static org.junit.jupiter.api.Assertions.*;

import dominio.usuarios.Duenio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
@Disabled
class CaracteristicaTest {
	RepositorioCaracteristicas repoCaracteristica = RepositorioCaracteristicas.getINSTANCE();

	@BeforeEach
	void setup() {
		repoCaracteristica.vaciar();
		Admin administrador;

		administrador = new Admin("UnUsuario", "UnaContraseña1");
		administrador.agregarUnaCaracteristica("COLOR-PRIMARIO-ROJO");
		administrador.agregarUnaCaracteristica("COLOR-PRIMARIO-AZUL");
		administrador.agregarUnaCaracteristica("COLOR-PRIMARIO-AMARILLO");
		administrador.agregarUnaCaracteristica("CASTRADO");
		administrador.agregarUnaCaracteristica("NO-CASTRADO");
	}

	@Test
	void colorPrimarioAmarilloEsUnaCaracteristica() {
		String colorPrimarioAmarillo = "COLOR-PRIMARIO-AMARILLO";
		assertTrue(repoCaracteristica.existeCaracteristica(colorPrimarioAmarillo));
	}

	@Test
	void alAgregarUnaCaracteristicaConElMismoNombreDeUnaExistenteRompe() {
		Admin administrador = new Admin("UnAdministrador", "holaqtaltodomuyBarat10");
		Executable agregarCaracteristica = () -> administrador.agregarUnaCaracteristica("castrado");
		assertThrows(CaracteristicaRepetida.class, agregarCaracteristica);
	}

	@Test
	void unaMascotaDeColorRojaSeReconoceComoRoja(){
		Fixture fixture = new Fixture();
		Duenio carlos = fixture.getCarlos();
		Mascota felix = fixture.getFelix();
		carlos.registrarUnaMascota(felix);
		felix.agregarUnaCaracteristica("COLOR-PRIMARIO-ROJO");
		assertTrue(felix.getCaracteristicas().contains("COLOR-PRIMARIO-ROJO"));
	}

	@Test
	void unaMascotaRojaNoPuedeSerDeOtroColor(){
		Fixture fixture = new Fixture();
		Duenio carlos = fixture.getCarlos();
		Mascota felix = fixture.getFelix();
		carlos.registrarUnaMascota(felix);
		felix.agregarUnaCaracteristica("COLOR-PRIMARIO-ROJO");
		assertFalse(felix.getCaracteristicas().contains("COLOR-PRIMARIO-AMARILLO"));
	}

	@Test
	void caracteristicaInvalida(){
		Fixture fixture = new Fixture();
		Mascota felix = fixture.getFelix();
		assertThrows(CaracteristicaInvalida.class, () -> felix.agregarUnaCaracteristica("Panza Negra"));
	}


}
