package router;

import controllers.HomeController;
import controllers.LoginController;
import controllers.MascotaController;
import controllers.RescateController;
import controllers.UserController;

import static spark.Spark.*;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
  public void setup() {
    
    HandlebarsTemplateEngine engineTemplate = new HandlebarsTemplateEngine();

		staticFiles.location("public");

    get("/", HomeController::view, engineTemplate);
		get("/login", LoginController::view, engineTemplate);
    post("/login", LoginController::login, engineTemplate);

    get("/signup/step1", UserController::viewStep1, engineTemplate);

    post("/usuarios/registrar/paso1", UserController::signUpStep1, engineTemplate);
    post("/usuarios/registrar/paso2", UserController::signUpStep2);

    get("/signup/step2", UserController::viewStep2, engineTemplate);

    get("/mascotas/crear", MascotaController::view , engineTemplate);

    get("/rescates/crear", RescateController::viewWithoutBadge, engineTemplate);
    get("/rescates/crear/:id", RescateController::viewWithBadge, engineTemplate);

    post("/caracteristicas", (request, response) -> {
      System.out.println(request.queryParams("text"));
      return "success";
    });

    get("/caracteristicas", (request, response) -> {
      return new ModelAndView(null, "caracteristica.hbs");
    }, engineTemplate);

    delete("/caracteristicas/:texto", (request, response) -> {
      System.out.println(request.params("texto"));
      return "success";
    });
  }
}
