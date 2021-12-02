package main;
import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import dominio.adopcion.DarEnAdopcion;
import dominio.adopcion.DarEnAdopcionBuilder;
import dominio.adopcion.SolicitudAdopcion;
import dominio.adopcion.SolicitudAdopcionBuilder;
import dominio.asociacion.Asociacion;
import dominio.mascota.Caracteristica;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Mascota;
import dominio.mascota.Sexo;
import dominio.mascota.Tamanio;
import dominio.passwords.CommonPassword;
import dominio.passwords.NumberChar;
import dominio.passwords.UpperChar;
import dominio.personas.Contacto;
import dominio.personas.DatosPersona;
import dominio.personas.Documento;
import dominio.personas.TipoDeDocumento;
import dominio.preguntas.Pregunta;
import dominio.preguntas.PreguntaBinaria;
import dominio.preguntas.PreguntaCerrada;
import dominio.repositorio.RepositorioAdministradores;
import dominio.repositorio.RepositorioAdopcion;
import dominio.repositorio.RepositorioAsociaciones;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.repositorio.RepositorioDuenios;
import dominio.repositorio.RepositorioPreguntas;
import dominio.repositorio.RepositorioSolicitudesAdopcion;
import dominio.repositorio.RepositorioValidaciones;
import dominio.tareas.Recomendador;
import dominio.ubicacion.Coordenadas;
import dominio.usuarios.Admin;
import dominio.usuarios.Duenio;
import router.Router;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import servicios.mail.JavaMail;
import spark.Spark;

import javax.persistence.Entity;
import javax.persistence.EntityManager;


public class Main {

  public static void main(String[] args) {


    System.out.println("**********************");
    System.out.println("**Iniciando Servidor**");
    System.out.println("**********************");


    if (args.length > 0 && args[0].equals("run_recomendaciones")) {
      runRecomendaciones();
      System.exit(0);
    }

    if(args.length > 0 && args[0].equals("bootstrap")){
      bootstrap();
    }

    Spark.port(9000);
    new Router().setup();

    // Log only sql hibernate/jpa
    Logger logger = Logger.getLogger("org.hibernate");
    logger.setUseParentHandlers(false);

    EntityManager entityManager = getEntityManager();

    if(entityManager == null){
      System.exit(1);
    }
    

    System.out.println("**************************************************");
    System.out.println("**Se estableció la conexión con la Base de Datos**");
    System.out.println("**************************************************");

  }

  private static void runRecomendaciones() {
    System.out.println("****************************");
    System.out.println("**Enviando Recomendaciones**");
    System.out.println("****************************");
    (new Recomendador()).enviarRecomendaciones(new JavaMail());
  }

  private static EntityManager getEntityManager() {
    EntityManager entityManager;

    try{
      entityManager = PerThreadEntityManagers.getEntityManager();
    }catch (Exception e){
      System.out.println("*************************************************************************************************");
      System.out.println("**Ocurrió un error al conectar con la base de datos. Verifique que el motor se encuentra activo**");
      System.out.println("*************************************************************************************************");
      return null;
    }

    return entityManager;
  }

  private static void bootstrap(){

    System.out.println("***********************");
    System.out.println("**Inicializando Datos**");
    System.out.println("***********************");

    EntityManager entityManager = getEntityManager();
    if(entityManager == null){
      System.exit(1);
    }
    entityManager.getTransaction().begin();

    Contacto contacto = new Contacto("Alexis", "Herasimiuk","123456789" ,"aleeherasimiuk@protonmail.com");
    Documento documento = new Documento(TipoDeDocumento.DNI, "42255284");
    DatosPersona datosPersona = new DatosPersona("Herasimiuk","Alexis", documento,contacto, LocalDate.now().minusYears(21));
    Duenio duenio = new Duenio("alee", "alee", datosPersona);

    RepositorioDuenios.getInstance().registrar(duenio);

    Contacto contacto2 = new Contacto("Alexis", "Herasimiuk","123456789" ,"aleeherasimiuk@gmail.com");
    Documento documento2 = new Documento(TipoDeDocumento.DNI, "42255284");
    DatosPersona datosPersona2 = new DatosPersona("Herasimiuk","Alexis", documento2, contacto2, LocalDate.now().minusYears(21));
    Duenio duenio2 = new Duenio("alee2", "alee2", datosPersona2);

    RepositorioDuenios.getInstance().registrar(duenio2);

    Contacto contacto3 = new Contacto("Fede", "Kiwo","133456789" ,"roberroberxD@gmail.com");
    Documento documento3 = new Documento(TipoDeDocumento.DNI, "4355384");
    DatosPersona datosPersona3 = new DatosPersona("Kiwo","Fede", documento3, contacto3, LocalDate.now().minusYears(25));
    Duenio duenio3 = new Duenio("fede", "fede", datosPersona3);

    RepositorioDuenios.getInstance().registrar(duenio3);

    Mascota pupi = new Mascota(ClaseMascota.GATO, "Pupi", "Pupi", 3, Sexo.MACHO, Tamanio.CHICO);
    pupi.setDescripcionFisica("Un gato siamés, blanco con manchas negras");
    pupi.agregarUnaFoto("https://estaticos.muyinteresante.es/media/cache/1140x_thumb/uploads/images/gallery/591ec9b95bafe8a5c53c986a/gato-siames_0.jpg");

    RepositorioCaracteristicas.getINSTANCE().registrar(new Caracteristica("COME MUCHO"), new Caracteristica("ES MUY AMIGABLE"), new Caracteristica("ES MUY RUIDOSO"));
    pupi.agregarUnaCaracteristica("Come mucho");
    pupi.agregarUnaCaracteristica("Es muy amigable");
    pupi.agregarUnaCaracteristica("Es muy ruidoso");


    duenio.registrarUnaMascota(pupi);

    Asociacion asociacion = new Asociacion("Rescate De Patitas", new Coordenadas(80.457123, 90.14256));
    RepositorioAsociaciones.getInstance().registrar(asociacion);

    Pregunta[] preguntas = new Pregunta[]{
      new PreguntaBinaria("¿Necesita Patio?", "¿Tiene patio?"),
      new PreguntaCerrada("¿Que clase de mascota es?", "¿Que clase de mascota desea?", "PERRO", "GATO"),
      new Pregunta("¿Qué enfermedades tiene la mascota?", null)
    };

    for (Pregunta pregunta : preguntas) {
      asociacion.agregarPregunta(pregunta);
    }

    Pregunta global = new PreguntaBinaria("¿Duerme en la cama?", "¿Puede dormir en la cama?", true);
    RepositorioPreguntas.getInstance().registrar(global);

    RepositorioAsociaciones.getInstance().registrar(asociacion);


    DarEnAdopcionBuilder builder = new DarEnAdopcionBuilder(duenio, pupi);
    builder.setAsociacion(asociacion);
    builder.responderPregunta(preguntas[0], "SI");
    builder.responderPregunta(preguntas[1], "PERRO");
    builder.responderPregunta(preguntas[2], "Tiene convulsiones");
    builder.responderPregunta(global, "SI");
    DarEnAdopcion publicacion = builder.build();
    RepositorioAdopcion.getInstance().registrar(publicacion);

    SolicitudAdopcionBuilder solicitudBuilder = new SolicitudAdopcionBuilder(duenio2);
    solicitudBuilder.setAsociacion(asociacion);
    solicitudBuilder.responderPregunta(preguntas[0], "Si");
    solicitudBuilder.responderPregunta(preguntas[1], "PERRO");
    solicitudBuilder.responderPregunta(global, "SI");
    SolicitudAdopcion solicitud = solicitudBuilder.build();
    RepositorioSolicitudesAdopcion.getInstance().registrar(solicitud);

    SolicitudAdopcionBuilder solicitudBuilder2 = new SolicitudAdopcionBuilder(duenio3);
    solicitudBuilder2.setAsociacion(asociacion);
    solicitudBuilder2.responderPregunta(preguntas[0], "Si");
    solicitudBuilder2.responderPregunta(preguntas[1], "PERRO");
    solicitudBuilder2.responderPregunta(global, "SI");
    SolicitudAdopcion solicitud2 = solicitudBuilder2.build();
    RepositorioSolicitudesAdopcion.getInstance().registrar(solicitud2);

    RepositorioAdministradores.getInstance().registrar(new Admin("admin", "admin"));
    RepositorioValidaciones.getInstance().registrar(new CommonPassword(), new UpperChar(), new NumberChar());

    entityManager.getTransaction().commit();
  }
}
