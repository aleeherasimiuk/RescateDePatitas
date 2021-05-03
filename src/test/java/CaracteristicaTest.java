package test.java;
//import main.java.dominio.caracteristicas.*;
import dominio.caracteristicas.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaracteristicaTest {
	
	@Test
	void losColoresPrimariosEsUnaCaracteristica() {	
		Caracteristica coloresPrimarios = this.coloresPrimarios();
		
		assertTrue(coloresPrimarios.tieneEstaOpcion("rojo"));
		assertTrue(coloresPrimarios.tieneEstaOpcion("azul"));
		assertTrue(coloresPrimarios.tieneEstaOpcion("amarillo"));
	}

	@Test
	void laCastracionEsUnaCaracteristica() {
		Caracteristica castracion = new Caracteristica("castracion", this.respuestaSiNo());
		
		assertTrue(castracion.tieneEstaOpcion("s"));
		assertTrue(castracion.tieneEstaOpcion("n"));
	}
	
	private Caracteristica coloresPrimarios(){
		List<String> colores = Arrays.asList("rojo", "azul", "amarillo");

		return new Caracteristica("colores-primarios", colores);
	}
	private List<String> respuestaSiNo(){
		return Arrays.asList("s", "n");
	}

	@Test
	void alAgregarUnaCaracteristicaConElMismoNombreDeUnaExistenteRompe(){
		Caracteristicas caracteristicas = new Caracteristicas();

		List<String> si_no = Arrays.asList(new String[]{"SI", "NO"});

		Caracteristica caracteristica1 = new Caracteristica("Castrado", si_no);
		Caracteristica caracteristica2 = new Caracteristica("CASTRADO", si_no);

		caracteristicas.agregarCaracteristica(caracteristica1);

		Exception exception = assertThrows(RuntimeException.class, () -> caracteristicas.agregarCaracteristica(caracteristica2));
		assertEquals("Ya existe una caracteristica con ese titulo. Verifique si se trata de un error o intente con otro titulo", exception.getMessage());
	}


}
