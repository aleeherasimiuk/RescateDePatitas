import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dominio.asociacion.Asociacion;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Mascota;
import dominio.mascota.Sexo;
import dominio.personas.Contacto;
import dominio.personas.DatosPersona;
import dominio.personas.Documento;
import dominio.personas.TipoDeDocumento;
import dominio.rescate.DatosRescate;
import dominio.rescate.Publicacion;
import dominio.rescate.Rescate;
import dominio.rescate.Rescatista;
import dominio.Ubicacion.Coordenadas;
import dominio.usuarios.Duenio;
import dominio.util.Lista;

public class Fixture {

  private final Mascota pupi     = crearAPupi();
  private final Mascota felix    = crearAFelix();
  private final Mascota vladi    = crearAVladi();
  private final Duenio carlos    = crearACarlos();
  private final Duenio samuel    = crearASamuel();
  private final Rescatista pedro = crearAPedro();

  private final Rescate rescatePupi  = rescatarAPupi();
  private final Rescate rescateFelix = rescatarAFelix();

  private final Coordenadas UTN               = new Coordenadas(-34.65858825852768, -58.46736257475716);
  private final Coordenadas parqueChacabuco   = new Coordenadas(-34.63481134002147, -58.442202384019055);
  private final Coordenadas parqueAvellaneda  = new Coordenadas(-34.64388667313111, -58.47976161190845);


  private final Asociacion  colaDeGato     = asociacionColaDeGato();
  private final Asociacion  patitasSucias  = asociacionPatitasSucias();
  private final Publicacion publicacionUTN = publicacionMascotaUTN();


  public Mascota getPupi() {
    return pupi;
  }

  public Mascota getFelix() {
    return felix;
  }

  public Mascota getVladi() {
    return vladi;
  }

  public Duenio getCarlos() {
    return carlos;
  }

  public Duenio getSamuel() {
    return samuel;
  }

  public Rescatista getPedro() {
    return pedro;
  }

  public Rescate getRescatePupi() {
    return rescatePupi;
  }

  public Rescate getRescateFelix() {
    return rescateFelix;
  }

  public Asociacion getColaDeGato() {
    return colaDeGato;
  }

  public Asociacion getPatitasSucias() {
    return patitasSucias;
  }

  public Publicacion getPublicacionUTN() {
    return publicacionUTN;
  }

  private Duenio crearACarlos() {
    Documento documento = new Documento(TipoDeDocumento.DNI, "21789654");
    DatosPersona datosPersona = new DatosPersona("Perez", "Carlos", documento, unContacto(),
        stringAFecha("01/01/2002"));

    return new Duenio("carlosKpo123", "Pupitoteamo1", datosPersona);
  }

  private Duenio crearASamuel() {
    Documento documento = new Documento(TipoDeDocumento.DNI, "21789651");
    DatosPersona datosPersona = new DatosPersona("Perez", "Samuel", documento, unContacto(),
        stringAFecha("01/01/2001"));

    return new Duenio("samuKpo123", "Vladiteamo1", datosPersona);
  }

  private Rescatista crearAPedro() {
    Documento documento = new Documento(TipoDeDocumento.DNI, "21789654");
    DatosPersona datosPersona = new DatosPersona("Perez", "Pedro", documento, unContacto(), stringAFecha("02/02/1996"));

    return new Rescatista(datosPersona, "Calle Falsa 123");
  }

  private Mascota crearAPupi() {
    Mascota pupi = new Mascota(ClaseMascota.GATO, "Pupi", "Pupi", 3, Sexo.MACHO);
    pupi.setDescripcionFisica("Un gato siamés, marrón con manchas blancas");
    return pupi;
  }

  private Mascota crearAFelix() {
    return new Mascota(ClaseMascota.PERRO, "felix", "feli", 5, Sexo.MACHO);
  }

  private Mascota crearAVladi() {
    return new Mascota(ClaseMascota.PERRO, "vladi", "vla", 5, Sexo.MACHO);
  }

  private LocalDate stringAFecha(String fecha) {
    return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/uuuu"));
  }

  private Contacto unContacto() {
    return new Contacto("Federico", "Bal", 1180700542, "fedebal@gmail.com");
  }

  private Rescate rescatarAFelix() {
    DatosRescate datosRescate = new DatosRescate(pedro, new Lista<>(), LocalDate.now().plusDays(-15), "perro negro con mancha blanca en la panza", new Coordenadas(-55., -55.));
    Rescate rescateFelix = new Rescate(datosRescate, felix);
    return rescateFelix;
  }

  private Rescate rescatarAPupi() {
    DatosRescate datosRescate = new DatosRescate(pedro, new Lista<>(), LocalDate.now(), "parece ser un gato siames", new Coordenadas(-50., -50.));
    Rescate rescatePupi = new Rescate(datosRescate, pupi);
    return rescatePupi;
  }

  private Publicacion publicacionMascotaUTN(){
    DatosRescate datosRescate = new DatosRescate(pedro, new Lista<>(), LocalDate.now().minusDays(1), "parece ser un gato siames", UTN);
    return new Publicacion(datosRescate);
  }

  private Asociacion asociacionPatitasSucias(){
    return new Asociacion("Patitas Sucias", parqueAvellaneda);
  }

  private Asociacion asociacionColaDeGato(){
    return new Asociacion("Cola de Gato", parqueChacabuco);
  }
}
