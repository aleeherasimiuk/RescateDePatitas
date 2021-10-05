import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.mascota.ClaseMascota;
import dominio.mascota.Mascota;
import dominio.mascota.Sexo;
import dominio.mascota.Tamanio;
import dominio.personas.Contacto;
import dominio.personas.DatosPersona;
import dominio.personas.Documento;
import dominio.personas.TipoDeDocumento;
import dominio.repositorio.RepositorioDuenios;
import dominio.repositorio.RepositorioMascotas;
import dominio.usuarios.Duenio;

public class MascotaTest extends AbstractTest{


  Duenio angelGallardo;
  Duenio rodolfoDonofrio;

  Mascota firulais;
  Mascota toto;
  Mascota pityMartinez;
  Mascota leoPonzio;
  Mascota franquitoArmani;

  @BeforeEach
  void setUp(){

    firulais = new Mascota(ClaseMascota.PERRO, "Firulais", "Firu", 6, Sexo.MACHO, Tamanio.GRANDE);
    toto = new Mascota(ClaseMascota.GATO, "Toto", "Tot", 3, Sexo.MACHO, Tamanio.MEDIANO);
    pityMartinez = new Mascota(ClaseMascota.PERRO, "Pity Martinez", "Pity", 5, Sexo.HEMBRA, Tamanio.MEDIANO);
    leoPonzio = new Mascota(ClaseMascota.PERRO, "Leo Ponzio", "Leo", 4, Sexo.MACHO, Tamanio.MEDIANO);
    franquitoArmani = new Mascota(ClaseMascota.PERRO, "Fraco Armani", "Pulpo", 3, Sexo.MACHO, Tamanio.MEDIANO);

    angelGallardo = new Duenio("Gallardo", "9diciembre2018", new DatosPersona("Gallardo", "Marcelo", new Documento(TipoDeDocumento.DNI, "44444444"), new Contacto("Rodolfo", "D'Onofrio", 444444, "rodolf@gmail.com"), LocalDate.now()));
    rodolfoDonofrio = new Duenio("Donofrio", "9diciembre2018", new DatosPersona("Donofrio", "Rodolfo", new Documento(TipoDeDocumento.DNI, "55555555"), new Contacto("Rodolfo", "D'Onofrio", 555555, "rodolf@gmail.com"), LocalDate.now()));

    angelGallardo.registrarUnaMascota(firulais);
    angelGallardo.registrarUnaMascota(toto);
    angelGallardo.registrarUnaMascota(pityMartinez);
    rodolfoDonofrio.registrarUnaMascota(leoPonzio);
    rodolfoDonofrio.registrarUnaMascota(franquitoArmani);

    RepositorioDuenios repo = RepositorioDuenios.getInstance();

    repo.registrar(angelGallardo, rodolfoDonofrio);
  }


  @Test
  void firulaisEsDeGallardo(){
    assertEquals(angelGallardo.getUsername(), RepositorioDuenios.getInstance().duenioDe(firulais).getUsername());
  }

  @Test
  void totoEsDeGallardo(){
    assertEquals(angelGallardo.getUsername(), RepositorioDuenios.getInstance().duenioDe(toto).getUsername());
  }

  @Test
  void pityMartinezEsDeGallardo(){
    assertEquals(angelGallardo.getUsername(), RepositorioDuenios.getInstance().duenioDe(pityMartinez).getUsername());
  }

  @Test
  void leoPonzioEsDeDonofrio(){
    assertEquals(rodolfoDonofrio.getUsername(), RepositorioDuenios.getInstance().duenioDe(leoPonzio).getUsername());
  }

  @Test
  void franquitoArmaniEsDeDonofrio(){
    assertEquals(rodolfoDonofrio.getUsername(), RepositorioDuenios.getInstance().duenioDe(franquitoArmani).getUsername());
  }

  @Test
  void hay5mascotasRegistradas(){
    assertEquals(5, RepositorioMascotas.getINSTANCE().cantidadRegistros());
  }

  @Test
  void hay2DueniosRegistrados(){
    assertEquals(2, RepositorioDuenios.getInstance().cantidadRegistros());
  }

}
