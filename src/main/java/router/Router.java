package router;

import controllers.HomeController;
import controllers.LoginController;
import dominio.mascota.Caracteristica;
import dominio.repositorio.RepositorioCaracteristicas;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.github.jknack.handlebars.Handlebars;

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
      List<Caracteristica> caracteristicas = RepositorioCaracteristicas.getINSTANCE().todos();
      model.put("characteristicsEven", caracteristicas.subList(0, (caracteristicas.size() + 1) / 2));
      model.put("characteristicsOdd", caracteristicas.subList((caracteristicas.size() + 1) / 2, (caracteristicas.size())));
      return new ModelAndView(model, "add_pet.hbs");
    } , engineTemplate);
  }
}
