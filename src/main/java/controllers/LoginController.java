package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
  public static ModelAndView view(Request req, Response res) {
		return new ModelAndView(null, "login.hbs");
	}
}
