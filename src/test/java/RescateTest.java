import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.Rescatista;
import dominio.mascota.Clase;
import dominio.mascota.Mascota;
import dominio.mascota.Sexo;
import dominio.personas.Contacto;
import dominio.personas.TipoDeDocumento;
import dominio.sistema.Registro;
import dominio.sistema.Rescate;
import dominio.usuarios.Duenio;


public class RescateTest {
  Registro registro;
  Mascota pupi;
  Rescate rescatePupi;
  Mascota felix;
  @BeforeEach
  void iniciarRegistro() {
    registro = new Registro();

    Duenio carlos = crearACarlos();

    pupi = crearAPupi();
    pupi.setDescripcionFisica("Un gato siamés, marrón con manchas blancas");

    carlos.registrarUnaMascota(registro, pupi);

    Rescatista pedro = crearAPedro();

    
    registro.registrarRescate(pedro, pupi, "parece ser un gato siames",null, LocalDate.now(), "https://unafoto.com");
    
    felix = crearAFelix();
    carlos.registrarUnaMascota(registro, felix);
    registro.registrarRescate(pedro, felix, "perro negro con mancha blanca en la panza", null, LocalDate.now().plusDays(-15), "https://otrafoto.com");
    
  }

  @Test 
  void hayAlMenosUnaMascotaRegistrada() {
    assertTrue(registro.cantDeMascotasRegistradas()>0);
  }
  
  @Test
  void ayerSePerdioPupi() {
    assertTrue(registro.mascotasEncontradasEnLosUltimos10Dias().contains(pupi));
  }
  
  @Test
  void felixSePerdioHaceMucho() {
    assertFalse(registro.mascotasEncontradasEnLosUltimos10Dias().contains(felix));
  }

  LocalDate stringAFecha(String fecha){
    return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/uuuu"));
  }

  private Duenio crearACarlos(){
    return new Duenio("Perez", "Carlos", TipoDeDocumento.DNI, 21789654,
      new Contacto("Jimena", "Baron", 1180700542, "jmena@gmail.com"), "carlosKpo123", "pupitoteamo",
      stringAFecha("01/01/2002"));
  }

  private Rescatista crearAPedro() {
    return new Rescatista("Perez", "Pedro", TipoDeDocumento.DNI, 21789654,
        new Contacto("Federico", "Bal", 1180700542, "fedebal@gmail.com"), stringAFecha("02/02/1996"),
        "Calle Falsa 123");
  }

  private Mascota crearAPupi() {
    return new Mascota(Clase.GATO, "Pupi", "Pupi", 3, Sexo.MACHO);
  }


  private Mascota crearAFelix() {
    return new Mascota(Clase.PERRO, "felix", "feli", 5, Sexo.MACHO);
  }
  
}
