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
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validadorContrasenia("123456"));
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validadorContrasenia("123456789"));
	}
	
	@Test
	void noEsUnaClaveSeguraUsarNombresDeComics() {
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validadorContrasenia("batman"));
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validadorContrasenia("iceman"));
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validadorContrasenia("superman"));
	}
	
	@Test
	void noEsUnaClaveSeguraUsarNombreDeColores() {
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validadorContrasenia("orange"));
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validadorContrasenia("black"));
	}

	@Test
	void noEsUnaClaveSeguraUsarNombreDePersonas() {		
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validadorContrasenia("andrea"));
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validadorContrasenia("thomas"));
	}

	@Test
	void esUnaClaveSeguraUsarFrases() {		
		assertDoesNotThrow(() -> validadorContrasenia.validadorContrasenia("cuestionesdelavida"));
		assertDoesNotThrow(() -> validadorContrasenia.validadorContrasenia("unaensaladafria"));
		assertDoesNotThrow(() -> validadorContrasenia.validadorContrasenia("undiadeveranoquiendiria"));
	}

	@Test
	void esSeguraUnaClaveAlfanumericaConSimbolos() {		
		assertDoesNotThrow(() -> validadorContrasenia.validadorContrasenia("123asd123.0?"));
	}

}
