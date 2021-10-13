package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {
  public static ModelAndView view(Request req, Response res) {
		return new ModelAndView(null, "home.hbs");
	}
}
