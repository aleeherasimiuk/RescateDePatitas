import dominio.exceptions.CaracteristicaInvalida;
import dominio.exceptions.CaracteristicaRepetida;
import dominio.exceptions.OpcionInvalida;
import dominio.mascota.*;
import dominio.personas.Contacto;
import dominio.personas.DatosPersona;
import dominio.personas.Documento;
import dominio.personas.TipoDeDocumento;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.usuarios.Administrador;

import static org.junit.jupiter.api.Assertions.*;

import dominio.usuarios.Duenio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
		Duenio carlos = crearACarlos();
		Mascota felix = crearAFelix();
		carlos.registrarUnaMascota(felix);
		felix.agregarUnaCaracteristica("Colores-Primarios","rojo");
		assertEquals(felix.obtenerCaracteristica("colores-primarios"),"ROJO");
		assertThrows(CaracteristicaInvalida.class, () -> felix.agregarUnaCaracteristica("castrada","si"));
		assertThrows(OpcionInvalida.class, () -> felix.agregarUnaCaracteristica("colores-primarioS","gris"));
	}

	private Duenio crearACarlos() {
    Documento documento = new Documento(TipoDeDocumento.DNI, "21789654");
    DatosPersona datosPersona = new DatosPersona("Perez", "Carlos", documento, unContacto(),
        stringAFecha("01/01/2002"));

    return new Duenio("carlosKpo123", "Pupitoteamo1", datosPersona);
  }
	
	private Contacto unContacto() {
    return new Contacto("Federico", "Bal", 1180700542, "fedebal@gmail.com");
  }


	private Mascota crearAFelix() {
		return new Mascota(Clase.PERRO, "felix", "feli", 5, Sexo.MACHO);
	}

	private LocalDate stringAFecha(String fecha) {
		return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/uuuu"));
	}
}
