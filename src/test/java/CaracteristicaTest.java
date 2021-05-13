import dominio.mascota.*;
import dominio.personas.Contacto;
import dominio.personas.TipoDeDocumento;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.rescate.Rescatista;
import dominio.usuarios.Administrador;

import static org.junit.jupiter.api.Assertions.*;

import dominio.usuarios.Duenio;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class CaracteristicaTest {
	RepositorioCaracteristicas repoCaracteristica = RepositorioCaracteristicas.getINSTANCE();

	@BeforeAll
	static void setup() {
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

		String expected = "Ya existe una caracteristica con ese titulo. Verifique si se trata de un error o intente con otro titulo";
		Administrador administrador = new Administrador("UnAdministrador", "holaqtaltodomuyBarat10");
		Executable agregarCaracteristica = () -> administrador.agregarUnaCaracteristica("castrado", "SI", "NO", "NO SE");

		Exception exception = assertThrows(RuntimeException.class, agregarCaracteristica);
		assertEquals(expected,exception.getMessage());
	}

	@Test
	void seAgreganTresCaracteristicasSiendoDosInvalidas(){
		Duenio carlos = crearACarlos();
		Mascota felix = crearAFelix();
		carlos.registrarUnaMascota(felix);
		felix.agregarUnaCaracteristica("Colores-Primarios","rojo");
		assertEquals(felix.obtenerCaracteristica("colores-primarios"),"ROJO");
		assertThrows(CaracteristicaInvalida.class, () -> {felix.agregarUnaCaracteristica("castrada","si");});
		OpcionInvalida opcionInvalidaException = assertThrows(OpcionInvalida.class, () -> {felix.agregarUnaCaracteristica("colores-primarioS","gris");});
		assertEquals(opcionInvalidaException.getMessage(), "La opcion ingresada no forma parte de las opciones de la caracteristica COLORES-PRIMARIOS");
	}

	private Duenio crearACarlos(){
		return new Duenio("Perez", "Carlos", TipoDeDocumento.DNI, 21789654,
				new Contacto("Jimena", "Baron", 1180700542, "jmena@gmail.com"), "carlosKpo123", "Pupitoteamo1",
				stringAFecha("01/01/2002"));
	}

	private Mascota crearAPupi() {
		return new Mascota(Clase.GATO, "Pupi", "Pupi", 3, Sexo.MACHO);
	}

	private Mascota crearAFelix() {
		return new Mascota(Clase.PERRO, "felix", "feli", 5, Sexo.MACHO);
	}

	private LocalDate stringAFecha(String fecha) {
		return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/uuuu"));
	}
}
