import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.usuarios.PasswordValidator;

class ValidadorContraseniaTest {
	PasswordValidator validadorContrasenia;

	@BeforeEach
	void setup() {
		validadorContrasenia = new PasswordValidator();
	}

	@Test
	void noEsUnaClaveSeguraSoloPonerNumeros() {
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validarContrasenia("123456"));
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validarContrasenia("123456789"));
	}

	@Test
	void noEsUnaClaveSeguraUsarNombresDeComics() {
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validarContrasenia("batman"));
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validarContrasenia("iceman"));
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validarContrasenia("superman"));
	}

	@Test
	void noEsUnaClaveSeguraUsarNombreDeColores() {
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validarContrasenia("orange"));
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validarContrasenia("black"));
	}

	@Test
	void noEsUnaClaveSeguraUsarNombreDePersonas() {
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validarContrasenia("andrea"));
		assertThrows(RuntimeException.class, () -> validadorContrasenia.validarContrasenia("thomas"));
	}

	@Test
	void esUnaClaveSeguraUsarFrases() {
		assertDoesNotThrow(() -> validadorContrasenia.validarContrasenia("cuestionesdelavida"));
		assertDoesNotThrow(() -> validadorContrasenia.validarContrasenia("unaensaladafria"));
		assertDoesNotThrow(() -> validadorContrasenia.validarContrasenia("undiadeveranoquiendiria"));
	}

	@Test
	void esSeguraUnaClaveAlfanumericaConSimbolos() {
		assertDoesNotThrow(() -> validadorContrasenia.validarContrasenia("123asd123.0?"));

	}

}
