import static org.junit.jupiter.api.Assertions.*;

import dominio.usuarios.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.exceptions.password.CommonPasswordException;
import dominio.passwords.CommonPassword;
import dominio.passwords.LowerChar;
import dominio.passwords.NumberChar;
import dominio.passwords.PasswordLength;
import dominio.passwords.UpperChar;
import dominio.repositorio.RepositorioValidaciones;
import dominio.usuarios.Administrador;
import org.mindrot.jbcrypt.BCrypt;

class ValidadorContraseniaTest {
	RepositorioValidaciones repositorioValidaciones = RepositorioValidaciones.getInstance();

	@BeforeEach
	void setup() {
		repositorioValidaciones.vaciar();
		repositorioValidaciones.registrar(new CommonPassword());
		repositorioValidaciones.registrar(new PasswordLength());
		repositorioValidaciones.registrar(new LowerChar());
		repositorioValidaciones.registrar(new UpperChar());
		repositorioValidaciones.registrar(new NumberChar());

	}

	@Test
	void noEsUnaClaveSeguraSoloPonerNumeros() {
		assertThrows(RuntimeException.class, () -> validar("123456"));
		assertThrows(RuntimeException.class, () -> validar("123456789"));
	}

	@Test
	void noEsUnaClaveSeguraUsarNombresDeComics() {
		assertThrows(CommonPasswordException.class, () -> validar("batman"));
		assertThrows(CommonPasswordException.class, () -> validar("iceman"));
		assertThrows(CommonPasswordException.class, () -> validar("superman"));
	}

	@Test
	void noEsUnaClaveSeguraUsarNombreDeColores() {
		assertThrows(CommonPasswordException.class, () -> validar("orange"));
		assertThrows(CommonPasswordException.class, () -> validar("black"));
	}

	@Test
	void noEsUnaClaveSeguraUsarNombreDePersonas() {
		assertThrows(CommonPasswordException.class, () -> validar("andrea"));
		assertThrows(CommonPasswordException.class, () -> validar("thomas"));
	}

	@Test
	void esUnaClaveSeguraUsarFrases() {
		assertDoesNotThrow(() -> validar("Cuestionesdelavida2"));
		assertDoesNotThrow(() -> validar("Unaensaladafria3"));
		assertDoesNotThrow(() -> validar("Undiadeveranoquiendiria8"));
	}

	@Test
	void esSeguraUnaClaveAlfanumericaConSimbolos() {
		assertDoesNotThrow(() -> validar("123Asd123.0?"));

	}

	@Test
	void testHashPassword(){
		Usuario usuario = new Administrador("JorgeLanata", "ensaladA10");
		assertTrue (BCrypt.checkpw("ensaladA10",usuario.getPassword()));
	}


	void validar(String password){
		repositorioValidaciones.validatePassword(password);
	}
}
