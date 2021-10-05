import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dominio.adopcion.DarEnAdopcion;
import dominio.adopcion.DarEnAdopcionBuilder;
import dominio.asociacion.Asociacion;
import dominio.hogares.Hogar;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Mascota;
import dominio.mascota.Sexo;
import dominio.mascota.Tamanio;
import dominio.personas.Contacto;
import dominio.personas.DatosPersona;
import dominio.personas.Documento;
import dominio.personas.TipoDeDocumento;
import dominio.preguntas.Pregunta;
import dominio.preguntas.PreguntaBinaria;
import dominio.preguntas.PreguntaCerrada;
import dominio.repositorio.RepositorioAdopcion;
import dominio.repositorio.RepositorioAsociaciones;
import dominio.repositorio.RepositorioDuenios;
import dominio.repositorio.RepositorioPreguntas;
import dominio.repositorio.RepositorioRescatesConChapita;
import dominio.repositorio.RepositorioRescatistas;
import dominio.rescate.DatosRescate;
import dominio.rescate.RescateSinChapita;
import dominio.rescate.RescateConChapita;
import dominio.rescate.Rescatista;
import dominio.ubicacion.Coordenadas;
import dominio.usuarios.Duenio;

public class Fixture {

  private final Coordenadas parqueChacabuco   = new Coordenadas(-34.63481134002147, -58.442202384019055);
  private final Coordenadas parqueAvellaneda  = new Coordenadas(-34.64388667313111, -58.47976161190845);

  public Mascota getPupi() {
    return crearAPupi();
  }

  public Mascota getFelix() {
    return crearAFelix();
  }

  public Mascota getVladi() {
    return crearAVladi();
  }

  public Duenio getCarlos() {
    return crearACarlos();
  }

  public Duenio getSamuel() {
    return crearASamuel();
  }
  
  public Duenio getSabato() {
    return crearASabato();
  }

  public Rescatista getPedro() {
    return crearAPedro();
  }

  public RescateConChapita getRescatePupi() {
    return rescatarAPupi();
  }

  public RescateConChapita getRescateFelix() {
    return rescatarAFelix();
  }

  public Asociacion getColaDeGato() {
    return asociacionColaDeGato();
  }

  public Asociacion getPatitasSucias() {
    return asociacionPatitasSucias();
  }

  public RescateSinChapita getPublicacionUTN() {
    return publicacionMascotaUTN();
  }

  public Hogar getHogarCarinio() {
  	return crearHogarCarinioso();
  }

  public Hogar getElHiltonParaGatos() {
  	return crearHiltonParaGatos();
  }
  public Hogar getElHiltonPerruno() {
  	return crearPequenioHogarPerruno();
  }

  public Hogar getElPequenioHogarPerruno() {
  	return crearHiltonPerruno();
  }

  public Hogar getHogarAbandonado() {
  	return crearHogarAbandonado();
  }

  public Coordenadas getUTN() {
    return buildUTN();
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
  
  private Duenio crearASabato() {
    Documento documento = new Documento(TipoDeDocumento.DNI, "21789651");
    DatosPersona datosPersona = new DatosPersona("Perez", "Sabato", documento, unContacto(),
        stringAFecha("01/01/2001"));

    return new Duenio("sabato", "Vladiteamo1", datosPersona);
  }

  private Rescatista crearAPedro() {
    Documento documento = new Documento(TipoDeDocumento.DNI, "21789654");
    DatosPersona datosPersona = new DatosPersona("Perez", "Pedro", documento, otroContacto(), stringAFecha("02/02/1996"));

    Rescatista rescatista = new Rescatista(datosPersona, "Calle Falsa 123");
    RepositorioRescatistas.getInstance().registrar(rescatista);

    return rescatista;
  }

  private Mascota crearAPupi() {
    Mascota pupi = new Mascota(ClaseMascota.GATO, "Pupi", "Pupi", 3, Sexo.MACHO, Tamanio.CHICO);
    pupi.setDescripcionFisica("Un gato siamés, marrón con manchas blancas");
    return pupi;
  }

  private Mascota crearAFelix() {
    return new Mascota(ClaseMascota.PERRO, "felix", "feli", 5, Sexo.MACHO, Tamanio.GRANDE);
  }

  private Mascota crearAVladi() {
    return new Mascota(ClaseMascota.PERRO, "vladi", "vla", 5, Sexo.MACHO, Tamanio.MEDIANO);
  }

  private LocalDate stringAFecha(String fecha) {
    return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/uuuu"));
  }

  private Contacto unContacto() {
    return new Contacto("Federico", "Bal", 1180700542, "fedebal@gmail.com");
  }

  private Contacto otroContacto() {
    return new Contacto("Roberto", "Gimenez", 1180700543, "robertito@gmail.com");
  }

  private RescateConChapita rescatarAFelix() {
    DatosRescate datosRescate = new DatosRescate(crearAPedro(), new ArrayList<>(), LocalDate.now().plusDays(-15), "perro negro con mancha blanca en la panza", new Coordenadas(-55., -55.));
    RescateConChapita rescateFelix = new RescateConChapita(datosRescate, crearAFelix());
    return rescateFelix;
  }

  private RescateConChapita rescatarAPupi() {
    Duenio carlos = crearACarlos();
    Mascota pupi = crearAPupi();
    carlos.registrarUnaMascota(pupi);
    RepositorioDuenios.getInstance().registrar(carlos);

    Rescatista pedro = crearAPedro();

    DatosRescate datosRescate = new DatosRescate(pedro, new ArrayList<>(), LocalDate.now(), "parece ser un gato siames", buildUTN());
    RescateConChapita rescatePupi = new RescateConChapita(datosRescate, pupi);
    RepositorioRescatesConChapita.getINSTANCE().registrar(rescatePupi);
    return rescatePupi;
  }

  private RescateSinChapita publicacionMascotaUTN(){
    DatosRescate datosRescate = new DatosRescate(crearAPedro(), new ArrayList<>(), LocalDate.now().minusDays(1), "parece ser un gato siames", buildUTN());
    return new RescateSinChapita(datosRescate, Tamanio.CHICO, ClaseMascota.GATO);
  }

  private Asociacion asociacionPatitasSucias(){
    return new Asociacion("Patitas Sucias", parqueAvellaneda);
  }

  private Asociacion asociacionColaDeGato(){
    return new Asociacion("Cola de Gato", parqueChacabuco);
  }

  private Hogar crearHogarAbandonado() {  	
		return new Hogar("HogarAbandonado", "0800-999-111", null, false, null, buildUTN(), false);  	
  }
  
  private Hogar crearHogarCarinioso() {  	
  	List<ClaseMascota> prefierenCualquierMascota = Arrays.asList(ClaseMascota.PERRO, ClaseMascota.GATO);
  	  	
		return new Hogar("somosHogarCarinioso", "0800-999-111", prefierenCualquierMascota, false, new ArrayList<>(), buildUTN(), true);
  }

  private Hogar crearHiltonPerruno() {  	  	
		return new Hogar("elHiltonPerruno", "0800-999-112", Arrays.asList(ClaseMascota.PERRO), true, new ArrayList<>(), buildUTN(), true);
  }

  private Hogar crearHiltonParaGatos() {  	  	
		return new Hogar("elHiltonParaMascotasGatunas", "0800-999-112", Arrays.asList(ClaseMascota.GATO), true, new ArrayList<>(), buildUTN(), true);
  }

  private Hogar crearPequenioHogarPerruno() {  	  	
		return new Hogar("elPequenioHogarParaPerritos", "0800-999-112", Arrays.asList(ClaseMascota.PERRO), false, new ArrayList<>(), buildUTN(), false);
  }

  private Coordenadas buildUTN(){
    return new Coordenadas(-34.65858825852768, -58.46736257475716);
  }

  private Pregunta[] tresPreguntasTipicasDeAdopcion() {
    Pregunta preguntas[] = new Pregunta[]{
        new PreguntaBinaria("¿Necesita Patio?", "¿Tiene patio?"),
        new PreguntaCerrada("¿Que clase de mascota es?", "¿Que clase de mascota desea?", "PERRO", "GATO"),
        new Pregunta("¿Qué enfermedades tiene la mascota?", null)
      };
    
    return preguntas;
  }
  
  private Pregunta tipicaPreguntaGlobal() {
    return new PreguntaBinaria("¿Duerme en la cama?", "¿Puede dormir en la cama?", true);    
  }
  
  public DarEnAdopcion publicacionSabatoDaEnAdopcionAPupi(){
    Asociacion asociacion = getColaDeGato();
    Pregunta preguntas[] = tresPreguntasTipicasDeAdopcion();
    Pregunta global = tipicaPreguntaGlobal();
    
    RepositorioPreguntas.getInstance().registrar(global);
    for (Pregunta pregunta : preguntas) {
      asociacion.agregarPregunta(pregunta);
    }
    
    RepositorioAsociaciones.getInstance().registrar(asociacion);
    Duenio sabato = getSabato();
    Mascota felix = getFelix();

    sabato.registrarUnaMascota(felix);
    RepositorioDuenios.getInstance().registrar(sabato);

    DarEnAdopcionBuilder builder = new DarEnAdopcionBuilder(sabato, felix);
    builder.setAsociacion(asociacion);
    builder.responderPregunta(preguntas[0], "SI");
    builder.responderPregunta(preguntas[1], "PERRO");
    builder.responderPregunta(preguntas[2], "Tiene convulsiones");
    builder.responderPregunta(global, "SI");
    DarEnAdopcion publicacion = builder.build();
    
    RepositorioAdopcion.getInstance().registrar(publicacion);
    
    return publicacion;    
  }
}
