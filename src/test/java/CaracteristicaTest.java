import dominio.exceptions.CaracteristicaInvalida;
import dominio.exceptions.CaracteristicaRepetida;
import dominio.mascota.*;
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
import dominio.usuarios.Admin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import dominio.usuarios.Duenio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

class CaracteristicaTest {
	RepositorioCaracteristicas repoCaracteristica = RepositorioCaracteristicas.getINSTANCE();

	@BeforeEach
	void setup() {
		Admin administrador;

		administrador = new Admin("UnUsuario", "UnaContraseña1");
		administrador.agregarUnaCaracteristica("COLOR-PRIMARIO-ROJO");
		administrador.agregarUnaCaracteristica("COLOR-PRIMARIO-AZUL");
		administrador.agregarUnaCaracteristica("COLOR-PRIMARIO-AMARILLO");
		administrador.agregarUnaCaracteristica("CASTRADO");
		administrador.agregarUnaCaracteristica("NO-CASTRADO");
	}

	
	@AfterEach
	void tearDown() {
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
