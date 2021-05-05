import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.usuarios.ValidadorContrasenia;

class ValidadorContraseniaTest {
	ValidadorContrasenia validadorContrasenia;
	
	@BeforeEach
	void setup() {
		validadorContrasenia = new ValidadorContrasenia();		
	}
	
	@Test
	void noEsUnaClaveSeguraSoloPonerNumeros() {		
		assertTrue(validadorContrasenia.esVulnerableLaContrasenia("123456"));
		assertTrue(validadorContrasenia.esVulnerableLaContrasenia("123456789"));
	}
	
	@Test
	void noEsUnaClaveSeguraUsarNombresDeComics() {		
		assertTrue(validadorContrasenia.esVulnerableLaContrasenia("batman"));
		assertTrue(validadorContrasenia.esVulnerableLaContrasenia("iceman"));
		assertTrue(validadorContrasenia.esVulnerableLaContrasenia("superman"));
	}
	
	@Test
	void noEsUnaClaveSeguraUsarNombreDeColores() {		
		assertTrue(validadorContrasenia.esVulnerableLaContrasenia("orange"));
		assertTrue(validadorContrasenia.esVulnerableLaContrasenia("black"));
	}

	@Test
	void noEsUnaClaveSeguraUsarNombreDePersonas() {		
		assertTrue(validadorContrasenia.esVulnerableLaContrasenia("andrea"));
		assertTrue(validadorContrasenia.esVulnerableLaContrasenia("thomas"));
	}

	@Test
	void esUnaClaveSeguraUsarFrases() {		
		assertFalse(validadorContrasenia.esVulnerableLaContrasenia("cuestionesdelavida"));
		assertFalse(validadorContrasenia.esVulnerableLaContrasenia("unaensaladafria"));
		assertFalse(validadorContrasenia.esVulnerableLaContrasenia("undiadeveranoquiendiria"));
	}

	@Test
	void esSeguraUnaClaveAlfanumericaConSimbolos() {		
		assertFalse(validadorContrasenia.esVulnerableLaContrasenia("123asd123.0?"));
	}

}
