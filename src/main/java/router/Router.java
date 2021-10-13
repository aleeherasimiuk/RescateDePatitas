package router;

import controllers.LoginController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
  public void setup() {
    HandlebarsTemplateEngine engineTemplate = new HandlebarsTemplateEngine();

		Spark.staticFiles.location("public");
		Spark.get("/login",
				(request, response) -> LoginController.view(request, response),
				engineTemplate);
  }
}
