package app;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

import dominio.tareas.Recomendador;
import servicios.mail.JavaMail;

public class App {
  public static void main(String[] args) {
    if (args.length > 0 && args[0].equals("run_recomendaciones")) {
      runRecomendaciones();
      System.exit(0);
    }
    runServer();
  }

  private static void runRecomendaciones() {
    System.out.println("Recomenaciones running");
    try {
      Logger logger = Logger.getLogger("name");
      FileHandler fh = new FileHandler("/home/nicolas/Desktop/java.txt");
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
