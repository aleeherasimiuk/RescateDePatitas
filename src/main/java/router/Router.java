package router;

import controllers.Auth;
import controllers.CaracteristicasController;
import controllers.HomeController;
import controllers.LoginController;
import controllers.MascotaController;
import controllers.RescateController;
import controllers.UserController;
import dominio.usuarios.Duenio;

import static spark.Spark.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
  public void setup() {

    HandlebarsTemplateEngine engineTemplate = new HandlebarsTemplateEngine();

		staticFiles.location("public");
    File uploadDir = new File("uploads");
    if (!uploadDir.exists()) {
      uploadDir.mkdir();
    }
    staticFiles.externalLocation("uploads");

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
    post("/rescates/crear", RescateController::withoutBadge, engineTemplate);
    
    get("/rescates/crear/:id", RescateController::viewWithBadge, engineTemplate);
    post("rescates/crear/:id", RescateController::withBadge, engineTemplate);

    get("/caracteristicas", CaracteristicasController::view, engineTemplate);

    post("/caracteristicas/borrar", CaracteristicasController::delete, engineTemplate);

    post("/caracteristicas/crear", CaracteristicasController::create, engineTemplate);

    notFound((req, res) ->  {
      res.redirect("/404");
      return new ModelAndView(null, "");
    });

    get("/404", (req, res) ->  {
      
      final Map<String, Object> model = new HashMap<>();
      model.put("error", false);
      model.put("logged", Auth.authorize(req) != null? true : false);
      return new ModelAndView(model, "404.hbs");
    }, engineTemplate);

    after((request, response) -> {
      EntityTransaction transaction = PerThreadEntityManagers.getEntityManager().getTransaction();
      PerThreadEntityManagers.getEntityManager().flush();
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
