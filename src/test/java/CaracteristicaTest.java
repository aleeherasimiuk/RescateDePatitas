import dominio.caracteristicas.*;
import dominio.usuarios.Administrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaracteristicaTest {

	Administrador administrador;
	RepositorioCaracteristicas repositorioCaracteristicas;

	@BeforeEach
	void setup() {

		repositorioCaracteristicas = new RepositorioCaracteristicas();

		administrador = new Administrador("UnUsuario", "UnaContraseña");
		administrador.agregarUnaCaracteristica(repositorioCaracteristicas, "COLORES-PRIMARIOS", "ROJO", "AZUL", "AMARILLO");

		administrador.agregarUnaCaracteristica(repositorioCaracteristicas, "CASTRADO", "SI", "NO");

	}

	@Test
	void losColoresPrimariosEsUnaCaracteristica() {
		Caracteristica coloresPrimarios = repositorioCaracteristicas.obtenerCaracteristica("colores-primarios");
		assertTrue(coloresPrimarios.tieneEstaOpcion("rojo"));
		assertTrue(coloresPrimarios.tieneEstaOpcion("azul"));
		assertTrue(coloresPrimarios.tieneEstaOpcion("amarillo"));
	}

	@Test
	void laCastracionEsUnaCaracteristica() {
		Caracteristica castracion = repositorioCaracteristicas.obtenerCaracteristica("castrado");

		assertTrue(castracion.tieneEstaOpcion("SI"));
		assertTrue(castracion.tieneEstaOpcion("NO"));
	}

	@Test
	void alAgregarUnaCaracteristicaConElMismoNombreDeUnaExistenteRompe() {

		Exception exception = assertThrows(RuntimeException.class,
				() -> administrador.agregarUnaCaracteristica(repositorioCaracteristicas, "castrado", "SI", "NO", "NO SE"));
		assertEquals(
				"Ya existe una caracteristica con ese titulo. Verifique si se trata de un error o intente con otro titulo",
				exception.getMessage());
	}

}
