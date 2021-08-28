package app;

import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import dominio.personas.Contacto;
import dominio.personas.DatosPersona;
import dominio.personas.Documento;
import dominio.personas.TipoDeDocumento;
import dominio.tareas.Recomendador;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import servicios.mail.JavaMail;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class App {
  public static void main(String[] args) {
    if (args.length > 0 && args[0].equals("run_recomendaciones")) {
      runRecomendaciones();
      System.exit(0);
    }
    runServer();


    Contacto contacto = new Contacto("Ian", "Crespi",12 ,"crespi.ian@gmail.com");
    Documento documento = new Documento(TipoDeDocumento.DNI, "42255284");
    DatosPersona datosPersona = new DatosPersona("Herasimiuk","Alexis", documento,contacto, LocalDate.now());

    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    transaction.begin();
    entityManager.persist(datosPersona);
    transaction.commit();
  }

  private static void runRecomendaciones() {
    System.out.println("Recomenaciones running");
    try {
      Logger logger = Logger.getLogger("name");
      FileHandler fh = new FileHandler("<PATH_TO_LOG_FILE>");
      logger.addHandler(fh);
      logger.info("asd");
    } catch (Exception e) {

    }
    (new Recomendador()).enviarRecomendaciones(new JavaMail());
  }

  private static void runServer() {
    System.out.println("Server running");
  }
}
