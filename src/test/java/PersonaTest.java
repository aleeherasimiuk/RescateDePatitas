import dominio.personas.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PersonaTest {

	@Test
	void elNombreDeSusanaNoEsSusano() {
		Persona susana = this.SusanaPersona();
		
		assertNotEquals(susana.getNombre(), "Susano");
	}

	@Test
	void elContactoDeSusanaEsSusanita() {
		Persona susana = this.SusanaPersona();

		assertEquals("Susanita", susana.getContacto().getNombre());
	}
	
	private Contacto SusanitaContacto() {
		return new Contacto("Susanita", "Gimenez", 47732311, "susanita@gmail.com");
	}

	private Persona SusanaPersona() {
		return new Persona("Susana", "Gimenez", TipoDeDocumento.DNI, 25216110, this.SusanitaContacto());
	}
}
