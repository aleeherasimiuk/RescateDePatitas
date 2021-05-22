import static org.junit.jupiter.api.Assertions.*;

import dominio.usuarios.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dominio.exceptions.password.CommonPasswordException;
import dominio.exceptions.password.PasswordLengthException;
import dominio.exceptions.password.PasswordWithLowerCharException;
import dominio.exceptions.password.PasswordWithNumberCharException;
import dominio.exceptions.password.PasswordWithUpperCharException;
import dominio.passwords.CommonPassword;
import dominio.passwords.LowerChar;
import dominio.passwords.NumberChar;
import dominio.passwords.PasswordLength;
import dominio.passwords.UpperChar;
import dominio.passwords.Validation;
import dominio.repositorio.RepositorioValidaciones;
import dominio.usuarios.Administrador;
import org.mindrot.jbcrypt.BCrypt;

class ValidadorContraseniaTest {
	RepositorioValidaciones repositorioValidaciones = RepositorioValidaciones.getInstance();

	@DisplayName("1234 es una contraseña muy corta")
	@Test
	void testPasswordLength(){
		usarEstasValidaciones(new PasswordLength());
		assertThrows(PasswordLengthException.class, () -> validar("1234"));
	}

	@DisplayName("La contraseña tiene que tener al menos una mayúscula")
	@Test
	void testPasswordUpperCase(){
		usarEstasValidaciones(new UpperChar());
		assertThrows(PasswordWithUpperCharException.class, () -> validar("password"));
	}

	@DisplayName("La contraseña tiene que tener al menos una minúscula")
	@Test
	void testPasswordLowerCase(){
		usarEstasValidaciones(new LowerChar());
		assertThrows(PasswordWithLowerCharException.class, () -> validar("PASSWORD"));
	}

	@DisplayName("La contraseña tiene que tener al menos un número")
	@Test
	void testPasswordNumber(){
		usarEstasValidaciones(new NumberChar());
		assertThrows(PasswordWithNumberCharException.class, () -> validar("PASSWORD"));
	}

	@Test
	void batmanNoEsUnaClaveSegura() {
		usarEstasValidaciones(new CommonPassword());
		assertThrows(CommonPasswordException.class, () -> validar("batman"));
	}

	@Test
	void icemannNoEsUnaClaveSegura() {
		usarEstasValidaciones(new CommonPassword());
		assertThrows(CommonPasswordException.class, () -> validar("iceman"));
	}

	@Test
	void supermanNoEsUnaClaveSegura() {
		usarEstasValidaciones(new CommonPassword());
		assertThrows(CommonPasswordException.class, () -> validar("superman"));
	}

	@Test
	void orangeNoEsUnaClaveSegura() {
		usarEstasValidaciones(new CommonPassword());
		assertThrows(CommonPasswordException.class, () -> validar("orange"));
	}

	@Test
	void blackNoEsUnaClaveSegura() {
		usarEstasValidaciones(new CommonPassword());
		assertThrows(CommonPasswordException.class, () -> validar("black"));
	}

	@Test
	void andreaNoEsUnaClaveSegura() {
		usarEstasValidaciones(new CommonPassword());
		assertThrows(CommonPasswordException.class, () -> validar("andrea"));
	}

	@Test
	void thomasNoEsUnaClaveSegura() {
		usarEstasValidaciones(new CommonPassword());
		assertThrows(CommonPasswordException.class, () -> validar("thomas"));
	}

	@Test
	void esSeguraUnaClaveAlfanumericaConSimbolos() {
		usarEstasValidaciones(new CommonPassword(), new PasswordLength(), new LowerChar(), 
		new UpperChar(), new NumberChar());
		assertDoesNotThrow(() -> validar("123Asd123.0?"));
	}

	@Test
	void testHashPassword(){
		Usuario usuario = new Administrador("JorgeLanata", "ensaladA10");
		assertTrue (BCrypt.checkpw("ensaladA10",usuario.getPassword()));
	}

	void usarEstasValidaciones(Validation... validaciones){
		repositorioValidaciones.vaciar();
		repositorioValidaciones.registrar(validaciones);
	}

	void validar(String password){
		repositorioValidaciones.validatePassword(password);
	}
}
