import dominio.exceptions.CaracteristicaInvalida;
import dominio.exceptions.CaracteristicaRepetida;
import dominio.exceptions.OpcionInvalida;
import dominio.mascota.*;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.usuarios.Administrador;

import static org.junit.jupiter.api.Assertions.*;

import dominio.usuarios.Duenio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class CaracteristicaTest {
	RepositorioCaracteristicas repoCaracteristica = RepositorioCaracteristicas.getINSTANCE();

	@BeforeEach
	void setup() {
		repoCaracteristica.vaciar();
		Administrador administrador;

		administrador = new Administrador("UnUsuario", "UnaContraseña1");
		administrador.agregarUnaCaracteristica("COLORES-PRIMARIOS", "rojo", "AZUL", "AMARILLO");

		administrador.agregarUnaCaracteristica("CASTRADO", "SI", "NO");

	}


	@Test
	void losColoresPrimariosEsUnaCaracteristica() {
		Caracteristica coloresPrimarios = repoCaracteristica.obtenerCaracteristica("colores-primarios");
		assertTrue(coloresPrimarios.tieneEstaOpcion("rojo"));
		assertTrue(coloresPrimarios.tieneEstaOpcion("azul"));
		assertTrue(coloresPrimarios.tieneEstaOpcion("amarillo"));
	}

	@Test
	void laCastracionEsUnaCaracteristica() {
		Caracteristica castracion = repoCaracteristica.obtenerCaracteristica("castrado");

		assertTrue(castracion.tieneEstaOpcion("SI"));
		assertTrue(castracion.tieneEstaOpcion("NO"));
	}

	@Test
	void alAgregarUnaCaracteristicaConElMismoNombreDeUnaExistenteRompe() {
		Administrador administrador = new Administrador("UnAdministrador", "holaqtaltodomuyBarat10");
		Executable agregarCaracteristica = () -> administrador.agregarUnaCaracteristica("castrado", "SI", "NO", "NO SE");
		assertThrows(CaracteristicaRepetida.class, agregarCaracteristica);
	}

	@Test
	void seAgreganTresCaracteristicasSiendoDosInvalidas(){
		Fixture fixture = new Fixture();
		Duenio carlos = fixture.getCarlos();
		Mascota felix = fixture.getFelix();
		carlos.registrarUnaMascota(felix);
		felix.agregarUnaCaracteristica("Colores-Primarios","rojo");
		assertEquals(felix.obtenerCaracteristica("colores-primarios"),"ROJO");
		assertThrows(CaracteristicaInvalida.class, () -> felix.agregarUnaCaracteristica("castrada","si"));
		assertThrows(OpcionInvalida.class, () -> felix.agregarUnaCaracteristica("colores-primarioS","gris"));
	}

}
