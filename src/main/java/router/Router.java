package router;

import controllers.CaracteristicasController;
import controllers.HomeController;
import controllers.LoginController;
import controllers.MascotaController;
import controllers.RescateController;
import controllers.UserController;

import static spark.Spark.*;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
  public void setup() {

    HandlebarsTemplateEngine engineTemplate = new HandlebarsTemplateEngine();

		staticFiles.location("public");

    get("/", HomeController::view, engineTemplate);
		get("/login", LoginController::view, engineTemplate);
    post("/login", LoginController::login, engineTemplate);
    get("/logout", LoginController::logout, engineTemplate); // TODO: pasar a POST/DELETE

    get("/signup/step1", UserController::viewStep1, engineTemplate);

    post("/usuarios/registrar/paso1", UserController::signUpStep1, engineTemplate);
    post("/usuarios/registrar/paso2", UserController::signUpStep2);

    get("/signup/step2", UserController::viewStep2, engineTemplate);

    get("/mascotas/crear", MascotaController::view , engineTemplate);
    post("/mascotas",MascotaController::create, engineTemplate);

    get("/rescates/crear", RescateController::viewWithoutBadge, engineTemplate);
    get("/rescates/crear/:id", RescateController::viewWithBadge, engineTemplate);


    get("/caracteristicas", CaracteristicasController::view, engineTemplate);

    post("/caracteristicas/borrar", CaracteristicasController::delete, engineTemplate);

    post("/caracteristicas/crear", CaracteristicasController::create, engineTemplate);

    after((request, response) -> {
      EntityTransaction transaction = PerThreadEntityManagers.getEntityManager().getTransaction();
      PerThreadEntityManagers.getEntityManager().clear();
      if(transaction.isActive()) {
        transaction.commit();
      }
    });

    before((request, response) -> {
      EntityTransaction transaction = PerThreadEntityManagers.getEntityManager().getTransaction();
      if(!transaction.isActive()) {
        transaction.begin();
      }
    });
  }
}
