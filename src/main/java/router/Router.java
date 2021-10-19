package router;

import controllers.HomeController;
import controllers.LoginController;
import dominio.repositorio.RepositorioCaracteristicas;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
  public void setup() {
    HandlebarsTemplateEngine engineTemplate = new HandlebarsTemplateEngine();

		staticFiles.location("public");

    get("/", (request, response) -> HomeController.view(request, response), engineTemplate);
		get("/login",
				(request, response) -> LoginController.view(request, response), engineTemplate);
    post("/login",
        (request, response) -> LoginController.login(request, response), engineTemplate);
    get("/signup", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      model.put("error", false);
      return new ModelAndView(model, "signup.hbs");
    }, engineTemplate);

    get("/signup/step2", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      model.put("error", false);
      return new ModelAndView(model, "person_signup.hbs");
    }, engineTemplate);

    get("/blah", (request, response) -> {
      
      Map<String, Object> model = new HashMap<>();
      model.put("characteristics", RepositorioCaracteristicas.getINSTANCE().todos());
      return new ModelAndView(model, "add_pet.hbs");
    } , engineTemplate);
  }
}
