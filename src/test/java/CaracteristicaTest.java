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
	
}
