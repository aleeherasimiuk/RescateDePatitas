package test.java;

import dominio.personas.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
import main.java.dominio.personas.Persona;
import main.java.dominio.personas.TipoDeDocumento;
import main.java.dominio.personas.Contacto;
import main.java.dominio.usuarios.*;
*/
class PersonaTest {

	@Test
	void elNombreDeSusanaNoEsSusano() {
		Persona susana = this.SusanaPersona();
		
		assertFalse(susana.getNombre().equals("Susano"));
	}
	
	@Test
	void elContactoDeSusanaEsSusanita() {
		Persona susana = this.SusanaPersona();
	
		assertEquals("Susanita", susana.getContacto().getNombre());
	}

	//
	private Contacto SusanitaContacto() {
		return new Contacto(
				"Susanita", "Gimenez",
				47732311, "susanita@gmail.com"
				);
	}
	
	private Persona SusanaPersona() {
		return new Persona(
				"Susana", "Gimenez",
				TipoDeDocumento.DNI, 25216110,
				this.SusanitaContacto()
				);
	}
}
