package router;

import controllers.HomeController;
import controllers.LoginController;
import static spark.Spark.*;
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
    get("/signup", (request, response) -> LoginController.signup(request, response), engineTemplate);

    get("/signup/step2", (request, response) -> LoginController.personSignup(request, response), engineTemplate);
  }
}
