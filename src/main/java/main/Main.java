package main;

import java.net.ConnectException;
import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import dominio.personas.Contacto;
import dominio.personas.DatosPersona;
import dominio.personas.Documento;
import dominio.personas.TipoDeDocumento;
import dominio.repositorio.RepositorioDuenios;
import dominio.tareas.Recomendador;
import dominio.usuarios.Admin;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import servicios.mail.JavaMail;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {

  public static void main(String[] args) {

    System.out.println("**********************");
    System.out.println("**Iniciando Servidor**");
    System.out.println("**********************");

    if (args.length > 0 && args[0].equals("run_recomendaciones")) {

      System.out.println("****************************");
      System.out.println("**Enviando Recomendaciones**");
      System.out.println("****************************");

      runRecomendaciones();
      System.exit(0);
    }
    runServer();

    // Log only sql hibernate/jpa
    Logger logger = Logger.getLogger("org.hibernate");
    logger.setUseParentHandlers(false);

    EntityManager entityManager;

    try{
      entityManager = PerThreadEntityManagers.getEntityManager();
    }catch (Exception e){
      System.out.println("*************************************************************************************************");
      System.out.println("**Ocurrió un error al conectar con la base de datos. Verifique que el motor se encuentra activo**");
      System.out.println("*************************************************************************************************");
      return;
    }

    System.out.println("**************************************************");
    System.out.println("**Se estableció la conexión con la Base de Datos**");
    System.out.println("**************************************************");
  
    
    // Contacto contacto = new Contacto("Ian", "Crespi",12 ,"crespi.ian@gmail.com");
    // Documento documento = new Documento(TipoDeDocumento.DNI, "42255284");
    // DatosPersona datosPersona = new DatosPersona("Herasimiuk","Alexis", documento,contacto, LocalDate.now());

    // EntityTransaction transaction = entityManager.getTransaction();

    // transaction.begin();
    // entityManager.persist(datosPersona);
    // transaction.commit();
    // entityManager.close();

    // System.out.println(RepositorioDuenios.getInstance().todos());
    

    System.exit(0);
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
    
  }
}
