import dominio.mascota.Caracteristica;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.usuarios.Administrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class CaracteristicaTest {
	RepositorioCaracteristicas repoCaracteristica = RepositorioCaracteristicas.getINSTANCE();

	@BeforeAll
	static void setup() {
		Administrador administrador;

		administrador = new Administrador("UnUsuario", "UnaContraseña1");
		administrador.agregarUnaCaracteristica("COLORES-PRIMARIOS", "ROJO", "AZUL", "AMARILLO");

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

		String expected = "Ya existe una caracteristica con ese titulo. Verifique si se trata de un error o intente con otro titulo";
		Administrador administrador = new Administrador("UnAdministrador", "holaqtaltodomuyBarat10");
		Executable agregarCaracteristica = () -> administrador.agregarUnaCaracteristica("castrado", "SI", "NO", "NO SE");

		Exception exception = assertThrows(RuntimeException.class, agregarCaracteristica);
		assertEquals(expected,exception.getMessage());
	}

}
